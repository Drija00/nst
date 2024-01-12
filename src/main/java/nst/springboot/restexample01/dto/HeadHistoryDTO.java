package nst.springboot.restexample01.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class HeadHistoryDTO implements Serializable {

    Long id;
    LocalDate startDate;
    LocalDate endDate;
    MemberDTO head;
    DepartmentDto department;

    public HeadHistoryDTO(){}

    public HeadHistoryDTO(Long id, LocalDate startDate, LocalDate endDate, MemberDTO head, DepartmentDto department) {
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

    public MemberDTO getHead() {
        return head;
    }

    public void setHead(MemberDTO head) {
        this.head = head;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "HeadHistoryDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", head=" + head +
                ", department=" + department +
                '}';
    }
}
