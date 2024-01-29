package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.MemberController;
import nst.springboot.restexample01.controller.domain.ActiveHead;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.ActiveHeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveHeadConverter implements DtoEntityConverter<ActiveHeadDTO, ActiveHead> {
    @Autowired
    HeadHistoryConverter headHistoryConverter;
    @Autowired
    MemberConverter memberConverter;
    @Autowired
    DepartmentConverter departmentConverter;
    @Override
    public ActiveHeadDTO toDto(ActiveHead activeHead) {
        return new ActiveHeadDTO(activeHead.getId(),headHistoryConverter.toDto(activeHead.getHeadHistory()),memberConverter.toDto(activeHead.getMember()),departmentConverter.toDto(activeHead.getDepartment()) );
    }

    @Override
    public ActiveHead toEntity(ActiveHeadDTO activeHeadDTO) {
        return new ActiveHead(activeHeadDTO.getId(), headHistoryConverter.toEntity(activeHeadDTO.getHeadHistory()),memberConverter.toEntity(activeHeadDTO.getMemberDTO()),departmentConverter.toEntity(activeHeadDTO.getDepartmentDto()));
    }
}
