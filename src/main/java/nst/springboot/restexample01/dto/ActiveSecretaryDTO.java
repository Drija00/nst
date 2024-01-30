package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ActiveSecretaryDTO {
    Long id;

    SecretaryHistoryDTO secretaryHistory;
    @JsonIgnore
    MemberDTO memberDTO;
    @JsonIgnore
    DepartmentDto departmentDto;

    public ActiveSecretaryDTO() {
    }

    public ActiveSecretaryDTO(Long id, SecretaryHistoryDTO secretaryHistory, MemberDTO memberDTO, DepartmentDto departmentDto) {
        this.id = id;
        this.secretaryHistory = secretaryHistory;
        this.memberDTO = memberDTO;
        this.departmentDto = departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecretaryHistoryDTO getSecretaryHistory() {
        return secretaryHistory;
    }

    public void setSecretaryHistory(SecretaryHistoryDTO secretaryHistory) {
        this.secretaryHistory = secretaryHistory;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }
}

