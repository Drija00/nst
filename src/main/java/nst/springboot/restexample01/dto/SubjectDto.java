/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 *
 * @author Dules
 */

public class SubjectDto implements Serializable {

    private Long id;
    private String name;
    private int esbp;
    private DepartmentDto department;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, int esbp, DepartmentDto department) {
        this.id = id;
        this.name = name;
        this.esbp = esbp;
        this.department = department;
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

    public int getEsbp() {
        return esbp;
    }

    public void setEsbp(int esbp) {
        this.esbp = esbp;
    }

    public DepartmentDto getDepartmentDto() {
        return department;
    }

    public void setDepartmentDto(DepartmentDto department) {
        this.department = department;
    }

   
    
}
