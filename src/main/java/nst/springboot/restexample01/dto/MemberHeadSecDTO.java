package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class MemberHeadSecDTO implements Serializable {

    Long id;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    String firstname;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    String lastname;
    @NotNull
    AcademicTitleDTO academicTitle;
    @NotNull
    EducationTitleDTO educationTitle;
    @NotNull
    ScientificFieldDTO scientificField;
    @NotNull
    Long department;
    @JsonIgnore
    RoleDTO roleDTO;

    public MemberHeadSecDTO(){}

    public MemberHeadSecDTO(Long id, String firstname, String lastname, AcademicTitleDTO academicTitle, EducationTitleDTO educationTitle, ScientificFieldDTO scientificField, Long department, RoleDTO roleDTO) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.scientificField = scientificField;
        this.department = department;
        this.roleDTO = roleDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
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

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "MemberHeadSecDTO{" +
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
