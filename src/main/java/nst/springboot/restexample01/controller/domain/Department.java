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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id")
    private Member head;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secretary_id")
    private Member secretary;

    public Department() {
    }

    public Department(Long id, String name, String shortname, Member head, Member secretary) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.head = head;
        this.secretary = secretary;
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
        this.head = head;
    }

    public Member getSecretary() {
        return secretary;
    }

    public void setSecretary(Member secretary) {
        this.secretary = secretary;
    }
}
