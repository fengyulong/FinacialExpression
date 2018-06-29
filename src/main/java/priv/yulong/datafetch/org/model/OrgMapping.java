package priv.yulong.datafetch.org.model;

import java.util.List;

public class OrgMapping {

	private String code;

	private String name;

	private String remark;

	private String datasourceCode;

	private String parentCode;

	private String repCode;

	private String repName;

	private String bookCode;

	private List<OrgMapping> children;

	public static final String ROOT_CODE = "ROOT";
	public static final String ROOT_NAME = "组织机构";

	public static String convertToTreeField(String field) {
		if ("code".equals(field)) {
			return "id";
		} else if ("name".equals(field)) {
			return "text";
		} else {
			return field;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getDatasourceCode() {
		return datasourceCode;
	}

	public void setDatasourceCode(String datasourceCode) {
		this.datasourceCode = datasourceCode == null ? null : datasourceCode.trim();
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode == null ? null : repCode.trim();
	}

	public List<OrgMapping> getChildren() {
		return children;
	}

	public void setChildren(List<OrgMapping> children) {
		this.children = children;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

}