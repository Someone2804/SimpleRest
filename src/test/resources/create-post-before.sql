delete from posts_images;
delete from post;
delete from image;

insert into post(head, text, id) values
('firsttest', 'firstmessage', 1),
('secondttest', 'secondmessage', 2),
('thirdtest', 'thirdmessage', 3);

alter sequence hibernate_sequence restart with 10;