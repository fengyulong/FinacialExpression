package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.Dict;
import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;


public interface DictService {

	void deleteByPrimaryKey(String dictCode);

	void add(Dict record);

	Dict getByPrimaryKey(String dictCode);

	void updateSelective(Dict dict);

	void update(Dict dict);

	List<Dict> getDictTree();
	
	List<DictItem> getItems(String dictId);
	
	DictItem getItem(DictItemKey key);
	
	void updateItem(DictItem dictItem);
	
	void deleteItem(DictItemKey key);
	
	void addItem(DictItem dictItem);

}
