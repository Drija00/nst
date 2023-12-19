ALTER TABLE tbl_department
ADD COLUMN member_head_id bigint unsigned,
ADD COLUMN member_sec_id bigint unsigned,
ADD CONSTRAINT member_head_fk FOREIGN KEY (member_head_id) REFERENCES tbl_member(id),
ADD CONSTRAINT member_sec_fk FOREIGN KEY (member_sec_id) REFERENCES tbl_member(id);
