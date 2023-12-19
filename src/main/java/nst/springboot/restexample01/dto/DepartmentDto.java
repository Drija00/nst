/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author student2
 */
public class DepartmentDto implements Serializable{
    private Long id;
    
    @NotNull
    @Size(min = 2,max = 10, message = "Broj znakova [2-20]")
    private String name;

    @NotNull
    @Size(min = 2,max = 10, message = "Broj znakova [2-10]")
    private String shortname;
    private MemberDTO head;
    private MemberDTO secretary;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String name, String shortname, MemberDTO head, MemberDTO secretary) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.head = head;
        this.secretary = secretary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public MemberDTO getHead() {
        return head;
    }

    public void setHead(MemberDTO head) {
        this.head = head;
    }

    public MemberDTO getSecretary() {
        return secretary;
    }

    public void setSecretary(MemberDTO secretary) {
        this.secretary = secretary;
    }
}
