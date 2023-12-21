package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeadHistoryConverter implements DtoEntityConverter<HeadHistoryDTO, HeadHistory> {
    @Autowired
    private MemberHeadSecConverter memberHeadSecConverter;
    @Autowired
    private DepartmentConverter departmentConverter;


    @Override
    public HeadHistoryDTO toDto(HeadHistory headHistory) {
        return new HeadHistoryDTO(
                headHistory.getId(),
                headHistory.getStartDate(),
                headHistory.getEndDate(),
                memberHeadSecConverter.toDto(headHistory.getMember()),
                departmentConverter.toDto(headHistory.getDepartment()));
    }

    @Override
    public HeadHistory toEntity(HeadHistoryDTO headHistoryDTO) {
        return new HeadHistory(
                headHistoryDTO.getId(),
                headHistoryDTO.getStartDate(),
                headHistoryDTO.getEndDate(),
                memberHeadSecConverter.toEntity(headHistoryDTO.getHead()),
                departmentConverter.toEntity(headHistoryDTO.getDepartment()));
    }
}
