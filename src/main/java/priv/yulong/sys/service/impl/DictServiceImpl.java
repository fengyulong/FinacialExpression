package priv.yulong.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.sys.mapper.DictMapper;
import priv.yulong.sys.model.Dict;
import priv.yulong.sys.service.DictService;

@Service
public class DictServiceImpl implements DictService {

	@Resource
	private DictMapper dictMapper;

	@Override
	public void deleteByPrimaryKey(String dictCode) {
		dictMapper.deleteByPrimaryKey(dictCode);
	}

	@Override
	public void add(Dict record) {
		dictMapper.insert(record);
	}

	@Override
	public Dict getByPrimaryKey(String dictCode) {
		return dictMapper.selectByPrimaryKey(dictCode);
	}

	@Override
	public void updateSelective(Dict record) {
		dictMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void update(Dict record) {
		dictMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Dict> getDictTree() {
		return dictMapper.selectDictTree();
	}

}
