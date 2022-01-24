insert into company(id, name) values (1, 'tesla') on conflict do nothing;
insert into company(id, name) values (2, 'apple') on conflict do nothing;
insert into company(id, name) values (3, 'samsung') on conflict do nothing;
insert into company(id, name) values (4, 'sony') on conflict do nothing;
insert into company(id, name) values (5, 'microsoft') on conflict do nothing;

insert into person(id, name, company_id) values (1, 'Alex', 1) on conflict do nothing;
insert into person(id, name, company_id) values (2, 'Jhon', 2) on conflict do nothing;
insert into person(id, name, company_id) values (3, 'Mike', 3) on conflict do nothing;
insert into person(id, name, company_id) values (4, 'Nick', 4) on conflict do nothing;
insert into person(id, name, company_id) values (5, 'Den', 5) on conflict do nothing;
insert into person(id, name, company_id) values (6, 'Mark', 1) on conflict do nothing;
insert into person(id, name, company_id) values (7, 'Jack', 2) on conflict do nothing;
insert into person(id, name, company_id) values (8, 'Max', 3) on conflict do nothing;
insert into person(id, name, company_id) values (9, 'Todd', 4) on conflict do nothing;
insert into person(id, name, company_id) values (10, 'Rick', 5) on conflict do nothing;
insert into person(id, name, company_id) values (11, 'Carl', 1) on conflict do nothing;
insert into person(id, name, company_id) values (12, 'Ken', 4) on conflict do nothing;