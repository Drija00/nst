package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.controller.domain.ScientificField;


public class MemberDTO {

    Long id;
    @NotNull
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    String firstname;
    @NotNull
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    String lastname;

    AcademicTitleDTO academicTitle;

    EducationTitleDTO educationTitle;

    ScientificFieldDTO scientificField;

    DepartmentDto department;

    public MemberDTO(){}

    public MemberDTO(Long id, String firstname, String lastname, AcademicTitleDTO academicTitle, EducationTitleDTO educationTitle, ScientificFieldDTO scientificField, DepartmentDto department) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.scientificField = scientificField;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public AcademicTitleDTO getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitleDTO academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitleDTO getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitleDTO educationTitle) {
        this.educationTitle = educationTitle;
    }

    public ScientificFieldDTO getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificFieldDTO scientificField) {
        this.scientificField = scientificField;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", academicTitle=" + academicTitle +
                ", educationTitle=" + educationTitle +
                ", scientificField=" + scientificField +
                ", department=" + department +
                '}';
    }
}
