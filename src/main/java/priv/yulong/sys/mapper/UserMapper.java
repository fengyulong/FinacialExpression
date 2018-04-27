package priv.yulong.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import priv.yulong.sys.model.User;

@Mapper
public interface UserMapper {
	int deleteByPrimaryKey(String id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String id);
	
	User selectByUsername(String username);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
	
	
}