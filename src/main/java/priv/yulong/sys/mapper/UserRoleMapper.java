package priv.yulong.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.UserRole;

@Mapper
public interface UserRoleMapper {
	int deleteByPrimaryKey(String id);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);

	List<UserRole> selectByUserId(String userId);

	int deleteByUserId(String userId);
}