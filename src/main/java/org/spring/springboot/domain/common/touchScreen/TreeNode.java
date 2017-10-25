package org.spring.springboot.domain.common.touchScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * easyui使用的tree模型
 * 
 * @author jack.li
 * 
 */
public class TreeNode {

	private String id;
	private String pid;
	private String text;
	private Boolean checked = Boolean.FALSE;
	private Map<String, String[]> attributes;
	private List<TreeNode> children = new ArrayList<TreeNode>();
	private String state = "open";// open,closed

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, String[]> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String[]> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
