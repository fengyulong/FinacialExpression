package priv.yulong.datasourse.service;

import priv.yulong.datasourse.model.Datasource;

public interface DatasourceService {

	/**
	 * 获取数据源
	 * 
	 * @param datasourceCode
	 * @return
	 */
	Datasource getDataSource(String datasourceCode);

	/**
	 * 新增数据源
	 * 
	 * @param datasource
	 */
	Datasource addDatasource(Datasource datasource);

	/**
	 * 更新数据源
	 * 
	 * @param datasource
	 */
	Datasource updateDatasource(Datasource datasource);

	/**
	 * 删除数据源
	 * 
	 * @param datasource
	 */
	void deleteDatasource(Datasource datasource);

}
