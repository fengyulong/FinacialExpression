package priv.yulong.datasourse.mapper;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.datasourse.model.Datasource;

@Mapper
public interface DatasourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Datasource record);

    int insertSelective(Datasource record);

    Datasource selectByPrimaryKey(String id);
    
    Datasource selectByDatasourceCode(String code);

    int updateByPrimaryKeySelective(Datasource record);

    int updateByPrimaryKey(Datasource record);
}