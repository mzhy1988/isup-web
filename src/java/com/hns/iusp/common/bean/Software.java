package com.hns.iusp.common.bean;

import java.sql.Timestamp;

public class Software {
	
    private Integer id;

    private String downName;

    private String descinfo;

    private String uploader;

    private String downaddr;

    private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDownName() {
		return downName;
	}

	public void setDownName(String downName) {
		this.downName = downName;
	}

	public String getDescinfo() {
		return descinfo;
	}

	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getDownaddr() {
		return downaddr;
	}

	public void setDownaddr(String downaddr) {
		this.downaddr = downaddr;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
    
    
}
