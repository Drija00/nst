package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.MemberController;
import nst.springboot.restexample01.controller.domain.ActiveHead;
import nst.springboot.restexample01.controller.domain.ActiveSecretary;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.ActiveHeadDTO;
import nst.springboot.restexample01.dto.ActiveSecretaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveSecretaryConverter implements DtoEntityConverter<ActiveSecretaryDTO, ActiveSecretary> {
    @Autowired
    SecretaryHistoryConverter secretaryHistoryConverter;
    @Autowired
    MemberConverter memberConverter;
    @Autowired
    DepartmentConverter departmentConverter;
    @Override
    public ActiveSecretaryDTO toDto(ActiveSecretary activeSecretary) {
        return new ActiveSecretaryDTO(activeSecretary.getId(),secretaryHistoryConverter.toDto(activeSecretary.getSecretaryHistory()),memberConverter.toDto(activeSecretary.getMember()),departmentConverter.toDto(activeSecretary.getDepartment()) );
    }

    @Override
    public ActiveSecretary toEntity(ActiveSecretaryDTO activeSecretaryDTO) {
        return new ActiveSecretary(activeSecretaryDTO.getId(), secretaryHistoryConverter.toEntity(activeSecretaryDTO.getSecretaryHistory()),memberConverter.toEntity(activeSecretaryDTO.getMemberDTO()),departmentConverter.toEntity(activeSecretaryDTO.getDepartmentDto()));
    }
}

