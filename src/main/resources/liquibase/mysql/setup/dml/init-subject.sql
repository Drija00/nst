insert into tbl_department(id,name,shortname) values (9,"department-9","dept-9");

insert into tbl_subject(id,name,espb,department_id)
values (1,"subject-1", 10, 8);

insert into tbl_subject(id,name,espb,department_id)
values (2,"subject-2", 10, (select (id) from tbl_department WHERE name="department-9"));

insert into tbl_subject(id,name,espb,department_id)
values (3,"subject-3", 5, (select (id) from tbl_department WHERE name="department-1"));
insert into tbl_subject(id,name,espb,department_id)
values (4,"subject-4", 5, (select (id) from tbl_department WHERE name="department-2"));
insert into tbl_subject(id,name,espb,department_id)
values (5,"subject-5", 5, (select (id) from tbl_department WHERE name="department-1"));
