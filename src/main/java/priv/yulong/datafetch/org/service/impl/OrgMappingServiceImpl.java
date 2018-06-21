package priv.yulong.datafetch.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.datafetch.org.mapper.OrgMappingMapper;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgMappingService;

@Service
public class OrgMappingServiceImpl implements OrgMappingService {

	@Resource
	private OrgMappingMapper orgMappingMapper;

	@Override
	public void delete(String code) {
		List<OrgMapping> children = getByParentCode(code);
		if (!children.isEmpty()) {
			for (OrgMapping child : children) {
				delete(child.getCode());
			}
		}
		orgMappingMapper.deleteByPrimaryKey(code);
	}

	@Override
	public void add(OrgMapping record) {
		orgMappingMapper.insert(record);
	}

	@Override
	public OrgMapping getTree() {
		OrgMapping orgMapping = new OrgMapping();
		orgMapping.setCode(OrgMapping.ROOT_CODE);
		orgMapping.setName(OrgMapping.ROOT_NAME);
		orgMapping.setChildren(orgMappingMapper.selectTree());
		return orgMapping;
	}

	@Override
	public void update(OrgMapping record) {
		orgMappingMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrgMapping> getAll() {
		return orgMappingMapper.selectAll();
	}

	@Override
	public List<OrgMapping> getByParentCode(String parentCode) {
		return orgMappingMapper.selectByParentCode(parentCode);
	}

	@Override
	public OrgMapping get(String code) {
		return orgMappingMapper.selectByPrimaryKey(code);
	}

	@Override
	public List<OrgMapping> getWithNextLevelChildren(String code) {
		return orgMappingMapper.selectWithNextLevelChildren(code);
	}

}
