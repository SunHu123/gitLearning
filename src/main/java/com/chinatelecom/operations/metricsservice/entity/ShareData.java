package com.chinatelecom.operations.metricsservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.chinatelecom.udp.core.dataaccess.ormapping.DataObject;


public class ShareData extends DataObject implements Serializable{
	
	private String dataid;
	private String content;
	private String creator;
	private Timestamp  create_time;
	
	public ShareData() {
		
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}
