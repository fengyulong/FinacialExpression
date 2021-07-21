package priv.yulong.datafetch.org.mapper;

import org.apache.ibatis.annotations.Mapper;
import priv.yulong.datafetch.org.model.OrgMapping;

import java.util.List;

@Mapper
public interface OrgExtMappingMapper {
	int deleteByPrimaryKey(String code);

	int insert(OrgMapping record);

	int insertSelective(OrgMapping record);

	OrgMapping selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(OrgMapping record);

	int updateByPrimaryKey(OrgMapping record);

	List<OrgMapping> selectTree();

	List<OrgMapping> selectAll();

	List<OrgMapping> selectByParentCode(String parentCode);
	
	OrgMapping selectByRepCode(String repCode);
	
	List<OrgMapping> selectWithNextLevelChildren(String code);

}