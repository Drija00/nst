package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_active_head")
public class ActiveHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "active_history_id", unique = true)
    HeadHistory headHistory;

    @OneToOne
    @JoinColumn(name = "member_id", unique = true)
    Member member;

    @OneToOne
    @JoinColumn(name = "department_id", unique = true)
    Department department;

    public ActiveHead() {
    }

    public ActiveHead(Long id, HeadHistory headHistory, Member member, Department department) {
        this.id = id;
        this.headHistory = headHistory;
        this.member = member;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeadHistory getHeadHistory() {
        return headHistory;
    }

    public void setHeadHistory(HeadHistory headHistory) {
        this.headHistory = headHistory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
