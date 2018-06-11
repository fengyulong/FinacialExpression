package priv.yulong.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.Permission;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectAll();
    
    List<Permission> selectByType(String type);
    
    List<Permission> selectPermissionTree();
    
    Permission selectPermissionTreeById(String id);
    
    List<Permission> selectMenuTree();
    
    List<Permission> selectPermissionsByRoleId(String roleId);
    
    List<Permission> selectPermissionsByUserId(String userId);
}