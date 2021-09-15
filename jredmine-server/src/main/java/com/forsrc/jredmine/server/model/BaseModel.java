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
public interface BaseModel<PK> extends Cacheable<PK>, Serializable{

	public abstract PK getPk();
	
	public abstract void setPk(PK pk);

}
