package com.forsrc.jredmine.server.model;

import java.io.Serializable;

public interface Cacheable<PK> extends Serializable {
    PK getPk();
}
