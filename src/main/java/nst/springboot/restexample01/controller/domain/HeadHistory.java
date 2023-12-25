package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_head_history")
public class HeadHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member head;
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public HeadHistory(){}

    public HeadHistory(Long id, LocalDate startDate, LocalDate endDate, Member head, Department department) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
