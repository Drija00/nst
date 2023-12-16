package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.EducationTitleDTO;
import org.springframework.stereotype.Component;

@Component
public class EducationTitleConverter implements DtoEntityConverter<EducationTitleDTO,EducationTitle> {

    @Override
    public EducationTitleDTO toDto(EducationTitle entity) {
        return new EducationTitleDTO(
                entity.getId(),
                entity.getName());
    }

    @Override
    public EducationTitle toEntity(EducationTitleDTO dto) {
        return new EducationTitle(
                dto.getId(),
                dto.getName());
    }
}
