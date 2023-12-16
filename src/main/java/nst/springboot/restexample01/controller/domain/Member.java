package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    @Column(name = "firstname")
    String firstname;
    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    @Column(name = "lastname")
    String lastname;

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    AcademicTitle academicTitle;

    @ManyToOne
    @JoinColumn(name = "education_title_id")
    EducationTitle educationTitle;

    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    ScientificField scientificField;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public Member(){}

    public Member(Long id, String firstname, String lastname, AcademicTitle academicTitle, EducationTitle educationTitle, ScientificField scientificField, Department department) {
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

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitle getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitle educationTitle) {
        this.educationTitle = educationTitle;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
