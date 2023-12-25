package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    AcademicTitle academicTitle;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    ScientificField scientificField;

    public AcademicTitleHistory(){}

    public AcademicTitleHistory(Long id,LocalDate startDate, LocalDate endDate,Member member, AcademicTitle academicTitle, ScientificField scientificField) {
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }


}
