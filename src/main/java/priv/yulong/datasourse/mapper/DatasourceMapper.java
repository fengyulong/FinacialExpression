package priv.yulong.datasourse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.datasourse.model.Datasource;

@Mapper
public interface DatasourceMapper {

	List<Datasource> selectAll();

	int deleteByPrimaryKey(String code);

	int insert(Datasource record);

	int insertSelective(Datasource record);

	Datasource selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(Datasource record);

	int updateByPrimaryKey(Datasource record);
}