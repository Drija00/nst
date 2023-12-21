/*package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.Member2DTO;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Member2Converter implements DtoEntityConverter<Member2DTO, Member> {

    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private Department2Converter department2Converter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;
    public Member2DTO toDto(Member entity) {
        return new Member2DTO(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                educationTitleConverter.toDto(entity.getEducationTitle()),
                scientificFieldConverter.toDto(entity.getScientificField()),
                department2Converter.toDto(entity.getDepartment())
        );
    }

    @Override
    public Member toEntity(Member2DTO dto) {
        return new Member(
                dto.getId(),
                dto.getFirstname(),
                dto.getLastname(),
                academicTitleConverter.toEntity(dto.getAcademicTitle()),
                educationTitleConverter.toEntity(dto.getEducationTitle()),
                scientificFieldConverter.toEntity(dto.getScientificField()),
                department2Converter.toEntity(dto.getDepartment()));
    }
}*/
