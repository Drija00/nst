/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.PostConstruct;
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

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Member head;

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Member secretary;
    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Member> otherMembers;

    public Department() {
    }

    public Department(Long id, String name, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
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

    public Member getHead() {
        return head;
    }

    public void setHead(Member head) {
        if(head.getAcademicTitle().getId().equals(7L))
            this.head = head;
    }

    public Member getSecretary() {
        return secretary;
    }

    public void setSecretary(Member secretary) {
        this.secretary = secretary;
    }

    public List<Member> getOtherMembers() {
        return otherMembers;
    }

    public void setOtherMembers(List<Member> otherMembers) {
        this.otherMembers = otherMembers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }

    public void setAllMembers(){
        head = null;
        secretary=null;
        for(Member m : otherMembers){
            if(m.getAcademicTitle().getId().equals(7L)){
                head = m;
            }else if(m.getAcademicTitle().getId().equals(6L)){
                secretary = m;
            }
        }
    }

    public void addMember(Member member) {
        if(member.getAcademicTitle().getId().equals(7L)){
            head = member;
        }else if(member.getAcademicTitle().getId().equals(6L)){
            secretary = member;
        }else {
            otherMembers.add(member);
        }
        member.setDepartment(this);
    }
}
