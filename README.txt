** QUICK START **

INSERT INTO `test_task`.`system_user` (`id`, `is_active`, `password`, `login`) VALUES ('1', '1', 'admin', 'admin');
INSERT INTO `test_task`.`roles` (`id`, `name`, `user_id`) VALUES ('1', 'ROLE_ADMIN', '1');

http://localhost:8080/token?login=admin&password=admin

