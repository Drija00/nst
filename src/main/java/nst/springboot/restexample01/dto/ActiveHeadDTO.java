package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import nst.springboot.restexample01.controller.domain.HeadHistory;

public class ActiveHeadDTO {
    Long id;

    HeadHistoryDTO headHistory;
    @JsonIgnore
    MemberDTO memberDTO;
    @JsonIgnore
    DepartmentDto departmentDto;

    public ActiveHeadDTO() {
    }

    public ActiveHeadDTO(Long id, HeadHistoryDTO headHistory, MemberDTO memberDTO, DepartmentDto departmentDto) {
        this.id = id;
        this.headHistory = headHistory;
        this.memberDTO = memberDTO;
        this.departmentDto = departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeadHistoryDTO getHeadHistory() {
        return headHistory;
    }

    public void setHeadHistory(HeadHistoryDTO headHistory) {
        this.headHistory = headHistory;
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
