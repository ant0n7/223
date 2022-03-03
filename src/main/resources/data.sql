
insert into tbl_role (id, name) values ('2f1e573f-6290-4581-b54b-7e3ba2c98832', 'DEFAULT');
insert into tbl_role (id, name) values ('0629910c-f847-4672-a11c-a80718f18026', 'ADMIN');

insert into tbl_authority (id, name) values ('aebe836e-83a6-4be7-84c5-1eda33a933fe', 'READ');
insert into tbl_authority (id, name) values ('dc1cb5ac-e142-4b6a-aa87-ce509cc7b5f0', 'CREATE');
insert into tbl_authority (id, name) values ('a0f02331-1baa-4428-984b-5cd4328f6f7b', 'UPDATE');
insert into tbl_authority (id, name) values ('c02e84f2-f069-483a-bd47-85833e7d1334', 'DELETE');

insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'aebe836e-83a6-4be7-84c5-1eda33a933fe');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'dc1cb5ac-e142-4b6a-aa87-ce509cc7b5f0');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'a0f02331-1baa-4428-984b-5cd4328f6f7b');
insert into tbl_role_authorities (role_id, authority_id) values ('0629910c-f847-4672-a11c-a80718f18026', 'c02e84f2-f069-483a-bd47-85833e7d1334');
insert into tbl_role_authorities (role_id, authority_id) values ('2f1e573f-6290-4581-b54b-7e3ba2c98832', 'aebe836e-83a6-4be7-84c5-1eda33a933fe');

insert into tbl_user (id, username, email, password) values ('621334c0-c9c2-4e50-9b9a-50e0b86c8ef8', 'rtrapp0', 'mshew0@omniture.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('5a04f167-4435-4cc5-9a4d-3b44f3a8b1e9', 'fiwaszkiewicz1', 'staborre1@miitbeian.gov.cn', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('67916d13-fb3d-42cc-8073-444a0fdcd816', 'ssaye2', 'cromeril2@jigsy.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('a89de9cf-92cd-4113-92ac-5488297e85d1', 'awiffill3', 'cmcindrew3@samsung.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('1978282b-63cb-4335-b3cf-b06a9e0cb27d', 'dpennyman4', 'sleafe4@tumblr.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('59037d10-5693-49e8-b7da-8bc27943b809', 'mroskilly5', 'mphythian5@over-blog.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('58012c35-a241-493e-941a-17fa569ed06e', 'omatthensen6', 'jlubbock6@google.com.br', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('9349f52a-3404-45be-840e-c4ae50bfa15c', 'tmetts7', 'rosbiston7@squarespace.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('7c2e1f3e-d9d1-4e7a-803e-6e36653146e2', 'dlenthall8', 'gdur8@edublogs.org', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('2f311e0a-e539-4d45-acd0-95b86fe17d1e', 'lrandales9', 'dsanday9@gov.uk', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');
insert into tbl_user (id, username, email, password) values ('e74d2176-e7d7-4003-8543-369057a4279e', 'remo', 'remo@admin.com', '$2a$10$pSRN6DPaV8zMl0mop.xK0ev.DC8ukengwqhIEDRSz0wODLX2uiVOK');
insert into tbl_user (id, username, email, password) values ('2c61dac7-29fa-4baa-95e5-9053c7879d41', 'anton', 'anton@admin.com', '$2a$10$pSRN6DPaV8zMl0mop.xK0ev.DC8ukengwqhIEDRSz0wODLX2uiVOK');
insert into tbl_user (id, username, email, password) values ('9da0c2ba-98c5-4504-821a-259942a298b2', 'bob', 'bob@user.com', '$2a$10$M4RMvNN.WPHgc8DYy788HuMwR1Mu4H2AwMU0A6Njbc4Qv8IDSSVq6');

insert into tbl_user_role (user_id, role_id) values ('e74d2176-e7d7-4003-8543-369057a4279e', '0629910c-f847-4672-a11c-a80718f18026');
insert into tbl_user_role (user_id, role_id) values ('2c61dac7-29fa-4baa-95e5-9053c7879d41', '0629910c-f847-4672-a11c-a80718f18026');

insert into tbl_user_role (user_id, role_id) values ('621334c0-c9c2-4e50-9b9a-50e0b86c8ef8', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('5a04f167-4435-4cc5-9a4d-3b44f3a8b1e9', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('67916d13-fb3d-42cc-8073-444a0fdcd816', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('a89de9cf-92cd-4113-92ac-5488297e85d1', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('1978282b-63cb-4335-b3cf-b06a9e0cb27d', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('59037d10-5693-49e8-b7da-8bc27943b809', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('58012c35-a241-493e-941a-17fa569ed06e', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('9349f52a-3404-45be-840e-c4ae50bfa15c', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('7c2e1f3e-d9d1-4e7a-803e-6e36653146e2', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('2f311e0a-e539-4d45-acd0-95b86fe17d1e', '2f1e573f-6290-4581-b54b-7e3ba2c98832');
insert into tbl_user_role (user_id, role_id) values ('9da0c2ba-98c5-4504-821a-259942a298b2', '2f1e573f-6290-4581-b54b-7e3ba2c98832');

insert into tbl_group (id, groupname, motto) values ('03537b4a-a306-4420-98e6-a7b2f553f9cb', 'Fiveclub', 'nibh quisque id justo sit amet');
insert into tbl_group (id, groupname, motto) values ('bfa5b4aa-7366-4ec9-9c0d-9dd5c2ab0484', 'Zooxo', 'at nunc commodo placerat');
insert into tbl_group (id, groupname, motto) values ('fbff3f51-4a8b-4cea-9d83-9e5569b34e7a', 'BlogXS', 'ullamcorper purus sit amet');
insert into tbl_group (id, groupname, motto) values ('e11c08a8-0f34-4fc9-8aa0-bbb023f442e5', 'Voonte', 'condimentum neque sapien placerat ante');

insert into tbl_group_users (tbl_group_id, users_id) values ('03537b4a-a306-4420-98e6-a7b2f553f9cb', '621334c0-c9c2-4e50-9b9a-50e0b86c8ef8');
insert into tbl_group_users (tbl_group_id, users_id) values ('03537b4a-a306-4420-98e6-a7b2f553f9cb', '5a04f167-4435-4cc5-9a4d-3b44f3a8b1e9');
insert into tbl_group_users (tbl_group_id, users_id) values ('03537b4a-a306-4420-98e6-a7b2f553f9cb', '67916d13-fb3d-42cc-8073-444a0fdcd816');
insert into tbl_group_users (tbl_group_id, users_id) values ('e11c08a8-0f34-4fc9-8aa0-bbb023f442e5', 'a89de9cf-92cd-4113-92ac-5488297e85d1');
insert into tbl_group_users (tbl_group_id, users_id) values ('fbff3f51-4a8b-4cea-9d83-9e5569b34e7a', '1978282b-63cb-4335-b3cf-b06a9e0cb27d');
insert into tbl_group_users (tbl_group_id, users_id) values ('fbff3f51-4a8b-4cea-9d83-9e5569b34e7a', '59037d10-5693-49e8-b7da-8bc27943b809');
insert into tbl_group_users (tbl_group_id, users_id) values ('e11c08a8-0f34-4fc9-8aa0-bbb023f442e5', '58012c35-a241-493e-941a-17fa569ed06e');
insert into tbl_group_users (tbl_group_id, users_id) values ('e11c08a8-0f34-4fc9-8aa0-bbb023f442e5', '9349f52a-3404-45be-840e-c4ae50bfa15c');
insert into tbl_group_users (tbl_group_id, users_id) values ('03537b4a-a306-4420-98e6-a7b2f553f9cb', '7c2e1f3e-d9d1-4e7a-803e-6e36653146e2');
insert into tbl_group_users (tbl_group_id, users_id) values ('bfa5b4aa-7366-4ec9-9c0d-9dd5c2ab0484', '2f311e0a-e539-4d45-acd0-95b86fe17d1e');

