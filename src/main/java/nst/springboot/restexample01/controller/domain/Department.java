/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author student2
 */
@Entity
@Table(name = "tbl_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Skraceno ime je obavezno polje")
    @Size(min = 2, max = 10, message = "Broj znakova je od 2 do 10")
    @Column(name = "shortname")
    private String shortname;
    @OneToMany
    @Transient
    List<Member> members;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Transient
    List<HeadHistory> headHistories;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Transient
    List<SecretaryHistory> secretaryHistories;

    public Department() {
    }

    public Department(Long id, String name, String shortname, List<Member> members, List<HeadHistory> headHistories, List<SecretaryHistory> secretaryHistories) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.members = members;
        this.headHistories = headHistories;
        this.secretaryHistories = secretaryHistories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<HeadHistory> getHeadHistories() {
        return headHistories;
    }

    public void setHeadHistories(List<HeadHistory> headHistories) {
        this.headHistories = headHistories;
    }

    public List<SecretaryHistory> getSecretaryHistories() {
        return secretaryHistories;
    }

    public void setSecretaryHistories(List<SecretaryHistory> secretaryHistories) {
        this.secretaryHistories = secretaryHistories;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}
