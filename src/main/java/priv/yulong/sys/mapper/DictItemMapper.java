package priv.yulong.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;

@Mapper
public interface DictItemMapper {
    int deleteByPrimaryKey(DictItemKey key);

    int insert(DictItem record);

    int insertSelective(DictItem record);

    DictItem selectByPrimaryKey(DictItemKey key);

    int updateByPrimaryKeySelective(DictItem record);

    int updateByPrimaryKey(DictItem record);
    
    List<DictItem> selectItems(String dictId);
    
    int deleteItems(String dictId);
}