package nst.springboot.restexample01.dto;

import jakarta.persistence.*;
import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.domain.ScientificField;

import java.util.Date;

public class ATHDto {

    Long id;

    MemberDTO member;

    Date startDate;

    Date endDate;

    AcademicTitleDTO academicTitle;

    ScientificFieldDTO scientificField;

    public ATHDto(){}

    public ATHDto(Long id, Date startDate, Date endDate, MemberDTO member, AcademicTitleDTO academicTitle, ScientificFieldDTO scientificField) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
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

    public AcademicTitleDTO getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitleDTO academicTitle) {
        this.academicTitle = academicTitle;
    }

    public ScientificFieldDTO getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificFieldDTO scientificField) {
        this.scientificField = scientificField;
    }

    @Override
    public String toString() {
        return "ATHDto{" +
                "id=" + id +
                ", member=" + member +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", academicTitle=" + academicTitle +
                ", scientificField=" + scientificField +
                '}';
    }
}
