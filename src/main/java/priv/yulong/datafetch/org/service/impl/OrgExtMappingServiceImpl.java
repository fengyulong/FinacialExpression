package priv.yulong.datafetch.org.service.impl;

import org.springframework.stereotype.Service;
import priv.yulong.datafetch.org.mapper.OrgExtMappingMapper;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgExtMappingService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgExtMappingServiceImpl implements OrgExtMappingService {

	@Resource
	private OrgExtMappingMapper orgExtMappingMapper;

	@Override
	public void delete(String code) {
		List<OrgMapping> children = getByParentCode(code);
		if (!children.isEmpty()) {
			for (OrgMapping child : children) {
				delete(child.getCode());
			}
		}
		orgExtMappingMapper.deleteByPrimaryKey(code);
	}

	@Override
	public void add(OrgMapping record) {
		orgExtMappingMapper.insert(record);
	}

	@Override
	public OrgMapping getTree() {
		OrgMapping orgMapping = new OrgMapping();
		orgMapping.setCode(OrgMapping.ROOT_CODE);
		orgMapping.setName(OrgMapping.ROOT_NAME);
		orgMapping.setChildren(orgExtMappingMapper.selectTree());
		return orgMapping;
	}

	@Override
	public void update(OrgMapping record) {
		orgExtMappingMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrgMapping> getAll() {
		return orgExtMappingMapper.selectAll();
	}

	@Override
	public List<OrgMapping> getByParentCode(String parentCode) {
		return orgExtMappingMapper.selectByParentCode(parentCode);
	}

	@Override
	public OrgMapping get(String code) {
		return orgExtMappingMapper.selectByPrimaryKey(code);
	}

	@Override
	public List<OrgMapping> getWithNextLevelChildren(String code) {
		return orgExtMappingMapper.selectWithNextLevelChildren(code);
	}

	@Override
	public OrgMapping getByRepCode(String repCode) {
		return orgExtMappingMapper.selectByRepCode(repCode);
	}

}
