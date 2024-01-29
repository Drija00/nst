package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_active_secretary")
public class ActiveSecretary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "active_history_id")
    SecretaryHistory secretaryHistory;

    @OneToOne
    @JoinColumn(name = "member_id")
    Member member;

    @OneToOne
    @JoinColumn(name = "department_id")
    Department department;

    public ActiveSecretary() {
    }

    public ActiveSecretary(Long id, SecretaryHistory secretaryHistory, Member member, Department department) {
        this.id = id;
        this.secretaryHistory = secretaryHistory;
        this.member = member;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecretaryHistory getSecretaryHistory() {
        return secretaryHistory;
    }

    public void setSecretaryHistory(SecretaryHistory secretaryHistory) {
        this.secretaryHistory = secretaryHistory;
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

