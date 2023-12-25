package nst.springboot.restexample01.dto;

import jakarta.persistence.*;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class SecretaryHistoryDTO implements Serializable {
    Long id;
    LocalDate startDate;
    LocalDate endDate;
    MemberHeadSecDTO secretary;
    DepartmentDto department;

    public SecretaryHistoryDTO(){}

    public SecretaryHistoryDTO(Long id, LocalDate startDate, LocalDate endDate, MemberHeadSecDTO secretary, DepartmentDto department) {
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
