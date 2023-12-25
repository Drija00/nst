package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import nst.springboot.restexample01.dto.SecretaryHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecretaryHistoryConverter implements DtoEntityConverter<SecretaryHistoryDTO, SecretaryHistory> {
    @Autowired
    private MemberConverter memberConverter;
    @Autowired
    private DepartmentConverter departmentConverter;
    @Override
    public SecretaryHistoryDTO toDto(SecretaryHistory secretaryHistory) {
        return new SecretaryHistoryDTO(
                secretaryHistory.getId(),
                secretaryHistory.getStartDate(),
                secretaryHistory.getEndDate(),
                memberConverter.toDto(secretaryHistory.getMember()),
                departmentConverter.toDto(secretaryHistory.getDepartment()));
    }

    @Override
    public SecretaryHistory toEntity(SecretaryHistoryDTO secretaryHistoryDTO) {
        return new SecretaryHistory(
                secretaryHistoryDTO.getId(),
                secretaryHistoryDTO.getStartDate(),
                secretaryHistoryDTO.getEndDate(),
                memberConverter.toEntity(secretaryHistoryDTO.getSecretary()),
                departmentConverter.toEntity(secretaryHistoryDTO.getDepartment()));
    }
}
