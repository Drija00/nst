package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EducationTitleDTO {
    Long id;

    @NotNull
    @Size(min = 2, max = 20, message = "Broj znakova je od 2 do 20")
    String name;

    public EducationTitleDTO(){}

    public EducationTitleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
