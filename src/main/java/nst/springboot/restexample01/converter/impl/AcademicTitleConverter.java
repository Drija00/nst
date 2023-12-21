package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleConverter implements DtoEntityConverter<AcademicTitleDTO, AcademicTitle> {
    @Override
    public AcademicTitleDTO toDto(AcademicTitle entity) {
        return new AcademicTitleDTO(
                entity.getId(),
                entity.getName());
    }

    @Override
    public AcademicTitle toEntity(AcademicTitleDTO dto) {
        return new AcademicTitle(
                dto.getId(),
                dto.getName());
    }

}
