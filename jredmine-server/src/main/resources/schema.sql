
DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority
(
    username   varchar(50)    NOT NULL,
    authority  varchar(50)    NOT NULL,
    version    int            NOT NULL DEFAULT 0,
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_authority_username (username,authority)
); -- ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
    username    varchar(50)   NOT NULL,
    password    varchar(200)  NOT NULL,
    enabled     tinyint(1)    NOT NULL DEFAULT 0,
    version     int           NOT NULL DEFAULT 0,
    jwt_token   varchar(1000) ,
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (username)
); -- ENGINE=InnoDB DEFAULT CHARSET=latin1;
