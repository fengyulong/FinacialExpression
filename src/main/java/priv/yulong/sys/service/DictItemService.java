package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;

public interface DictItemService {
	
	void deleteByPrimaryKey(DictItemKey key);

	void add(DictItem dictItem);

	DictItem getByPrimaryKey(DictItemKey key);

	void updateSelective(DictItem dictItem);

	void update(DictItem dictItem);
	
	List<DictItem> getItems(String dictId);
}
