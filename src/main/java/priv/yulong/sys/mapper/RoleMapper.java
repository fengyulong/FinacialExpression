package priv.yulong.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.Role;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectAll();
    
    List<Role> selectByUserId(String userId);
}