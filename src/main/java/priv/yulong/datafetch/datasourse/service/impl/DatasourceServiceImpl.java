package priv.yulong.datafetch.datasourse.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import priv.yulong.datafetch.datasourse.mapper.DatasourceMapper;
import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.datafetch.datasourse.service.DatasourceService;

@Service
@Transactional
public class DatasourceServiceImpl implements DatasourceService {

	private final static Logger logger = LoggerFactory.getLogger(DatasourceServiceImpl.class);

	private static Map<String, DataSource> druidMap = new HashMap<String, DataSource>();

	@PostConstruct
	private void init() {
		List<Datasource> dsList = getAll();
		for (Datasource ds : dsList) {
			try {
				init(ds);
			} catch (Exception e) {
				logger.error("初始化数据库连接池出现异常", e);
			}
		}
	}

	@Resource
	private DatasourceMapper datasourceMapper;

	@Override
	@Cacheable(value = "datasource", key = "#code")
	public Datasource getDataSource(String code) {
		return datasourceMapper.selectByPrimaryKey(code);
	}

	@Override
	@CachePut(value = "datasource", key = "#datasource.code")
	public Datasource addDatasource(Datasource datasource) {
		datasourceMapper.insert(datasource);
		try {
			init(datasource);
		} catch (Exception e) {
			logger.error("新建数据库连接池出现异常", e);
		}
		return datasource;
	}

	@Override
	@CachePut(value = "datasource", key = "#datasource.code")
	public Datasource updateDatasource(Datasource datasource) {
		datasourceMapper.updateByPrimaryKey(datasource);
		close(datasource);
		try {
			init(datasource);
		} catch (Exception e) {
			logger.error("更新数据库连接池出现异常", e);
		}
		return datasource;
	}

	@Override
	@CacheEvict(value = "datasource", key = "#datasource.code")
	public void deleteDatasource(Datasource datasource) {
		datasourceMapper.deleteByPrimaryKey(datasource.getCode());
		close(datasource);
	}

	@Override
	public List<Datasource> getAll() {
		return datasourceMapper.selectAll();
	}

	@Override
	public Connection getConnection(String datasourceCode) throws Exception {
		if (druidMap.get(datasourceCode) == null) {
			synchronized (this) {
				if (druidMap.get(datasourceCode) == null) {
					Datasource ds = getDataSource(datasourceCode);
					init(ds);
				}
			}
		}
		return druidMap.get(datasourceCode).getConnection();
	}

	private static void init(Datasource ds) throws Exception {
		Properties prop = new Properties();
		prop.put(DruidDataSourceFactory.PROP_INITIALSIZE, "5");
		prop.put(DruidDataSourceFactory.PROP_MINIDLE, "5");
		prop.put(DruidDataSourceFactory.PROP_MAXACTIVE, "50");
		prop.put(DruidDataSourceFactory.PROP_MAXWAIT, "60000");
		prop.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, "60000");
		prop.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, "300000");
		prop.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, ds.getDbType().getValidationQuery());
		prop.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, "true");
		prop.put(DruidDataSourceFactory.PROP_TESTONBORROW, "false");
		prop.put(DruidDataSourceFactory.PROP_TESTONRETURN, "false");
		prop.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, "true");
		prop.put(DruidDataSourceFactory.PROP_FILTERS, "wall,stat");
		prop.put(DruidDataSourceFactory.PROP_URL, ds.getUrl());
		prop.put(DruidDataSourceFactory.PROP_USERNAME, ds.getUserName());
		prop.put(DruidDataSourceFactory.PROP_PASSWORD, ds.getUserPassword());
		DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
		druidMap.put(ds.getCode(), dataSource);
	}

	private void close(Datasource ds) {
		DataSource dataSource = druidMap.get(ds.getCode());
		if (dataSource != null && dataSource instanceof DruidDataSource) {
			((DruidDataSource) dataSource).close();
		}
	}

}
