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
    private MemberHeadSecConverter memberHeadSecConverter;
    @Autowired
    private DepartmentConverter departmentConverter;
    @Override
    public SecretaryHistoryDTO toDto(SecretaryHistory secretaryHistory) {
        return new SecretaryHistoryDTO(
                secretaryHistory.getId(),
                secretaryHistory.getStartDate(),
                secretaryHistory.getEndDate(),
                memberHeadSecConverter.toDto(secretaryHistory.getSecretary()),
                departmentConverter.toDto(secretaryHistory.getDepartment()));
    }

    @Override
    public SecretaryHistory toEntity(SecretaryHistoryDTO secretaryHistoryDTO) {
        return new SecretaryHistory(
                secretaryHistoryDTO.getId(),
                secretaryHistoryDTO.getStartDate(),
                secretaryHistoryDTO.getEndDate(),
                memberHeadSecConverter.toEntity(secretaryHistoryDTO.getSecretary()),
                departmentConverter.toEntity(secretaryHistoryDTO.getDepartment()));
    }
}
