package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_head_history")
public class HeadHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start_date")
    Date startDate;
    @Column(name = "end_date")
    Date endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member head;
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public HeadHistory(){}

    public HeadHistory(Long id, Date startDate, Date endDate, Member head, Department department) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.head = head;
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

    public Member getMember() {
        return head;
    }

    public void setMember(Member head) {
        this.head = head;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "HeadHistory{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", head=" + head +
                ", department=" + department +
                '}';
    }
}
