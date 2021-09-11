package com.forsrc.jredmine.server.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Cacheable<PK> extends Serializable {
	
	@JsonIgnore
    PK getPk();
}
