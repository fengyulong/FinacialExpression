package priv.yulong.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.sys.mapper.DictItemMapper;
import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;
import priv.yulong.sys.service.DictItemService;

@Service
public class DictItemServiceImpl implements DictItemService {
	@Resource
	private DictItemMapper dictItemMapper;

	@Override
	public void deleteByPrimaryKey(DictItemKey key) {
		dictItemMapper.deleteByPrimaryKey(key);
	}

	@Override
	public void add(DictItem dictItem) {
		dictItemMapper.insert(dictItem);
	}

	@Override
	public DictItem getByPrimaryKey(DictItemKey key) {
		return dictItemMapper.selectByPrimaryKey(key);
	}

	@Override
	public void updateSelective(DictItem dictItem) {
		dictItemMapper.updateByPrimaryKeySelective(dictItem);
	}

	@Override
	public void update(DictItem dictItem) {
		dictItemMapper.updateByPrimaryKey(dictItem);
	}

	@Override
	public List<DictItem> getItems(String dictId) {
		return dictItemMapper.selectItems(dictId);
	}

}
