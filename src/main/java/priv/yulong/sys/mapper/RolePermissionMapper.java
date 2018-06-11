package priv.yulong.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.RolePermission;
@Mapper
public interface RolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
    
    int deleteByRoleId(String roleId);
}