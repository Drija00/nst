package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.converter.impl.AcademicTitleConverter;
import nst.springboot.restexample01.converter.impl.EducationTitleConverter;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.converter.impl.ScientificFieldConverter;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ATHConverter implements DtoEntityConverter<ATHDto,AcademicTitleHistory> {

    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private MemberConverter memberConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public ATHDto toDto(AcademicTitleHistory entity) {
        return new ATHDto(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                memberConverter.toDto(entity.getMember()),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                scientificFieldConverter.toDto(entity.getScientificField()));
    }

    @Override
    public AcademicTitleHistory toEntity(ATHDto dto) {
        return new AcademicTitleHistory(
                dto.getId(),
                dto.getStartDate(),
                dto.getEndDate(),
                memberConverter.toEntity(dto.getMember()),
                academicTitleConverter.toEntity(dto.getAcademicTitle()),
                scientificFieldConverter.toEntity(dto.getScientificField()));
    }
}
