package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.ScientificField;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.ScientificFieldDTO;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DtoEntityConverter<ScientificFieldDTO, ScientificField> {
    @Override
    public ScientificFieldDTO toDto(ScientificField entity) {
        return new ScientificFieldDTO(
                entity.getId(),
                entity.getName());
    }

    @Override
    public ScientificField toEntity(ScientificFieldDTO dto) {
        return new ScientificField(
                dto.getId(),
                dto.getName());
    }
}
