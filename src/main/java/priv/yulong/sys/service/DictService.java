package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.Dict;


public interface DictService {

	void deleteByPrimaryKey(String dictCode);

	void add(Dict record);

	Dict selectByPrimaryKey(String dictCode);

	void updateSelective(Dict record);

	void update(Dict record);

	List<Dict> selectDictTree();

}
