package com.galaxy.exchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * administrator
 * 
 * @author jinjun06
 * 
 */
@Entity
@Table(name = "logdic")
public class LogDic {

	private Long id;

	private String typeValue;

	private Long typeId;

	@Id
	@GeneratedValue(generator = "NATIVE")
	@GenericGenerator(strategy = "native", name = "NATIVE")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type_value")
	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	@Column(name = "type_id")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		String str = "{typeValue:" + typeValue + ",typeId" + typeId + "}";
		return str;
	}

}
