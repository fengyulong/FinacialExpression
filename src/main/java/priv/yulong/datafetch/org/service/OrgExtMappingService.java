package priv.yulong.datafetch.org.service;

import priv.yulong.datafetch.org.model.OrgMapping;

import java.util.List;

public interface OrgExtMappingService {

	/**
	 * 根据主键查询
	 * 
	 * @param code
	 * @return
	 */
	OrgMapping get(String code);

	/**
	 * 删除
	 * 
	 * @param code
	 */
	void delete(String code);

	/**
	 * 新增
	 * 
	 * @param record
	 */
	void add(OrgMapping record);

	/**
	 * 更新
	 * 
	 * @param record
	 */
	void update(OrgMapping record);

	/**
	 * 获取机构树
	 * 
	 * @return
	 */
	OrgMapping getTree();

	/**
	 * 获取所有数据
	 * 
	 * @return
	 */
	List<OrgMapping> getAll();

	/**
	 * 根据父节点获取数据
	 * 
	 * @return
	 */
	List<OrgMapping> getByParentCode(String parentCode);

	/**
	 * 查询当前以及下级
	 * 
	 * @return
	 */
	List<OrgMapping> getWithNextLevelChildren(String code);

	/**
	 * 根据报表机构代码查询
	 * 
	 * @param repCode
	 * @return
	 */
	OrgMapping getByRepCode(String repCode);

}
