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

    AcademicTitleDTO academicTitleDTO;

    EducationTitleDTO educationTitleDTO;

    ScientificFieldDTO scientificFieldDTO;

    DepartmentDto departmentDTO;

    public MemberDTO(){}

    public MemberDTO(Long id, String firstname, String lastname, AcademicTitleDTO academicTitleDTO, EducationTitleDTO educationTitleDTO, ScientificFieldDTO scientificFieldDTO, DepartmentDto departmentDTO) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.academicTitleDTO = academicTitleDTO;
        this.educationTitleDTO = educationTitleDTO;
        this.scientificFieldDTO = scientificFieldDTO;
        this.departmentDTO = departmentDTO;
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

    public AcademicTitleDTO getAcademicTitleDTO() {
        return academicTitleDTO;
    }

    public void setAcademicTitleDTO(AcademicTitleDTO academicTitleDTO) {
        this.academicTitleDTO = academicTitleDTO;
    }

    public EducationTitleDTO getEducationTitleDTO() {
        return educationTitleDTO;
    }

    public void setEducationTitleDTO(EducationTitleDTO educationTitleDTO) {
        this.educationTitleDTO = educationTitleDTO;
    }

    public ScientificFieldDTO getScientificFieldDTO() {
        return scientificFieldDTO;
    }

    public void setScientificFieldDTO(ScientificFieldDTO scientificFieldDTO) {
        this.scientificFieldDTO = scientificFieldDTO;
    }

    public DepartmentDto getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDto departmentDTO) {
        this.departmentDTO = departmentDTO;
    }
}
