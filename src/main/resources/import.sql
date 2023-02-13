create view v_top_worst_quotes as  select t.* from (select q.quote_id, q.content, q.create_date, q.user_id, ifnull(sum(v.val), 0) as rating from quotes q left join votes v on q.quote_id = v.quote_id group by q.quote_id, q.content, q.create_date, q.user_id) t order by rating limit 10;

insert into users(name, email, password, create_date) values ('First', 'one@ema.il', '1Pass', date'2020-02-22');
insert into users(name, email, password, create_date) values ('Second', 'two@ema.il', '1Pass', date'2022-03-24');
insert into users(user_id, name, email, password, create_date) values (3, '', '', '', null);
insert into users(user_id, name, email, password, create_date) values (99, 'NinetyNine', 'nn@ema.il', '99Pass', date'2022-03-24');

insert into quotes(content, create_date, user_id) values ('great quote 2020', date'2020-01-01', 1);
insert into quotes(content, create_date, user_id) values ('great quote 2019', date'2019-01-01', 1);
insert into quotes(content, create_date, user_id) values ('great quote 2013', date'2013-01-01', 1);
insert into quotes(content, create_date, user_id) values ('great quote 2051', date'2051-01-01', 1);
insert into quotes(content, create_date, user_id) values ('great quote 2010', date'2010-01-01', 1);
insert into quotes(content, create_date, user_id) values ('great quote 1900', date'1900-01-01', 99);
insert into quotes(content, create_date, user_id) values ('great quote 1990', date'1990-01-01', 99);
insert into quotes(content, create_date, user_id) values ('great quote 2000', date'2000-01-01', 99);
insert into quotes(content, create_date, user_id) values ('great quote 2002', date'2002-01-01', 99);
insert into quotes(content, create_date, user_id) values ('great quote 2222', date'2222-01-01', 99);
insert into quotes(content, create_date, user_id) values ('great quote 1999', date'1999-01-01', 2);
insert into quotes(content, create_date, user_id) values ('great quote 1703', date'1703-01-01', 2);
insert into quotes(content, create_date, user_id) values ('great quote 9999', date'9999-01-01', 2);
insert into quotes(content, create_date, user_id) values ('great quote 2023', date'2023-01-01', 2);

insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 1, 1);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 2, 1);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 3, 1);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 1, 2);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 3, 2);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 1, 3);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 1, 4);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 2, 4);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 3, 4);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 1, 5);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 1, 7);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', 1, 1, 6);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 2, 6);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 3, 6);
insert into votes(date, val, user_id, quote_id) values (date'2000-01-01', -1, 99, 6);