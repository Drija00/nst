package nst.springboot.restexample01.dto;

import jakarta.persistence.*;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;

import java.util.Date;

public class SecretaryHistoryDTO {
    Long id;
    Date startDate;
    Date endDate;
    MemberHeadSecDTO secretary;
    DepartmentDto department;

    public SecretaryHistoryDTO(){}

    public SecretaryHistoryDTO(Long id, Date startDate, Date endDate, MemberHeadSecDTO secretary, DepartmentDto department) {
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

    public MemberHeadSecDTO getSecretary() {
        return secretary;
    }

    public void setSecretary(MemberHeadSecDTO secretary) {
        this.secretary = secretary;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }
}
