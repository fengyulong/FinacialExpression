package priv.yulong.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.Dict;

@Mapper
public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
    
    List<Dict> selectDictTree();
}