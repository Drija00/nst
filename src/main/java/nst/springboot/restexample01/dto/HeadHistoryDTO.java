package nst.springboot.restexample01.dto;

import jakarta.persistence.*;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;

import java.util.Date;

public class HeadHistoryDTO {

    Long id;
    Date startDate;
    Date endDate;
    MemberHeadSecDTO head;
    DepartmentDto department;

    public HeadHistoryDTO(){}

    public HeadHistoryDTO(Long id, Date startDate, Date endDate, MemberHeadSecDTO head, DepartmentDto department) {
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

    public MemberHeadSecDTO getHead() {
        return head;
    }

    public void setHead(MemberHeadSecDTO head) {
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
