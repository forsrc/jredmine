-- t_user
INSERT INTO t_user (username, password, enabled)
SELECT *
FROM (SELECT 'forsrc' username, '$2a$10$Wzme7qZtAsJZspQpNx3ee.qTu/IqRHiTb0jORWUOXCxptAkG3kf8e' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM t_user WHERE username = 'forsrc');

INSERT INTO t_user (username, password, enabled)
SELECT *
FROM (SELECT 'user' username, '$2a$10$SNKOBpTBuCbWukZ3Rc5DpuIHRP585Ss02fULAIX/m1NmFpWeJ8ic2' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM t_user WHERE username = 'user');

INSERT INTO t_user (username, password, enabled)
SELECT *
FROM (SELECT 'tcc' username, '$2a$10$lFUTwK/W3S3U8NI3cnqJPeVD3cZj6udLbW2W5GMvybtJw70N4WqFC' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM t_user WHERE username = 'tcc');

INSERT INTO t_user (username, password, enabled)
SELECT *
FROM (SELECT 'test' username, '$2a$10$uCchlP6N1q7ZOEMMifeZyOEOgqpddiVEIiIrM4k/76ftgLxtBaSXq' password, 1 enabled) AS T
WHERE NOT EXISTS(SELECT username FROM t_user WHERE username = 'test');

-- t_authority
INSERT INTO t_authority (username, authority)
SELECT *
FROM (SELECT 'forsrc' username, 'ROLE_ADMIN' t_authority) AS T
WHERE NOT EXISTS(SELECT username FROM t_authority WHERE username = 'forsrc' and authority = 'ROLE_ADMIN');

INSERT INTO t_authority (username, authority)
SELECT *
FROM (SELECT 'forsrc' username, 'ROLE_USER' t_authority) AS T
WHERE NOT EXISTS(SELECT username FROM t_authority WHERE username = 'forsrc' and authority = 'ROLE_USER');

INSERT INTO t_authority (username, authority)
SELECT *
FROM (SELECT 'user' username, 'ROLE_USER' t_authority) AS T
WHERE NOT EXISTS(SELECT username FROM t_authority WHERE username = 'user');

INSERT INTO t_authority (username, authority)
SELECT *
FROM (SELECT 'tcc' username, 'ROLE_TCC' t_authority) AS T
WHERE NOT EXISTS(SELECT username FROM t_authority WHERE username = 'tcc');

INSERT INTO t_authority (username, authority)
SELECT *
FROM (SELECT 'test' username, 'ROLE_TEST' t_authority) AS T
WHERE NOT EXISTS(SELECT username FROM t_authority WHERE username = 'test');