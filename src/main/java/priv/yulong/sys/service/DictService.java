package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.Dict;


public interface DictService {

	void deleteByPrimaryKey(String dictCode);

	void add(Dict record);

	Dict getByPrimaryKey(String dictCode);

	void updateSelective(Dict dict);

	void update(Dict dict);

	List<Dict> getDictTree();

}
