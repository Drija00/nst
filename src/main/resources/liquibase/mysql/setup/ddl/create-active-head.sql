CREATE TABLE tbl_active_head (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    active_history_id BIGINT UNSIGNED,
    member_id BIGINT UNSIGNED,
    department_id BIGINT UNSIGNED,
    PRIMARY KEY (id),
    CONSTRAINT active_history_fk FOREIGN KEY (active_history_id) REFERENCES tbl_head_history(id),
    CONSTRAINT ah_member_fk FOREIGN KEY (member_id) REFERENCES tbl_member(id),
    CONSTRAINT ah_department_fk FOREIGN KEY (department_id) REFERENCES tbl_department(id)
)
