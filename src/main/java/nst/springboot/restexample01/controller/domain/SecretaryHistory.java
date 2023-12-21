package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_secretary_history")
public class SecretaryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start_date")
    Date startDate;
    @Column(name = "end_date")
    Date endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member secretary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public SecretaryHistory(){}

    public SecretaryHistory(Long id, Date startDate, Date endDate, Member secretary, Department department) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.secretary = secretary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Member getSecretary() {
        return secretary;
    }

    public void setSecretary(Member secretary) {
        this.secretary = secretary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
