package com.forsrc.jredmine.server.model;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@SelectBeforeUpdate(true)
@DynamicUpdate(true)
@DynamicInsert(true)
public abstract class BaseModel<PK> implements Cacheable<PK>, Serializable{


	private static final long serialVersionUID = 2189112624069016553L;

	public abstract PK getPk();
	
	public abstract void setPk(PK pk);
	
	public BaseModel() {
	}
}
