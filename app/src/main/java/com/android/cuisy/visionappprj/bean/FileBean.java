package com.android.cuisy.visionappprj.bean;

import com.zhy.tree.bean.TreeNodeId;
import com.zhy.tree.bean.TreeNodeLabel;
import com.zhy.tree.bean.TreeNodePid;


public class FileBean {
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name) {
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

	public int get_id() {
		return _id;
	}

	public int getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public long getLength() {
		return length;
	}

	public String getDesc() {
		return desc;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

