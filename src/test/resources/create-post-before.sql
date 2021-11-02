delete from posts_images;
delete from post;
delete from image;

insert into t_users(password, username, id) values
('$2a$10$oVmiKRPWXFF63MXI1KB5QuxYmTzmFSLTqIIV1oe6SoEeV0WUjlEYm', 'Someone', 1),
('$2a$10$oVmiKRPWXFF63MXI1KB5QuxYmTzmFSLTqIIV1oe6SoEeV0WUjlEYm', 'Someone2', 2),
('$2a$10$oVmiKRPWXFF63MXI1KB5QuxYmTzmFSLTqIIV1oe6SoEeV0WUjlEYm', 'Someone3', 3);

insert into post(head, text, user_id, id) values
('firsttest', 'firstmessage', 1, 1),
('secondttest', 'secondmessage', 2, 2),
('thirdtest', 'thirdmessage', 3, 3);

alter sequence hibernate_sequence restart with 10;