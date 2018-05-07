package priv.yulong.sys.model;

import java.util.List;

public class Dict {
	private String id;

	private String text;

	private String parentId;

	private String remark;

	private String isLeaf;

	private List<Dict> children;

	public List<Dict> getChildren() {
		return children;
	}

	public void setChildren(List<Dict> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf == null ? null : isLeaf.trim();
	}
}