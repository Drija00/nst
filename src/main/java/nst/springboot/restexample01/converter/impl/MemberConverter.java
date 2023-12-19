package nst.springboot.restexample01.converter.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.domain.Subject;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DtoEntityConverter<MemberDTO, Member> {

    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    @JsonIgnore
    public MemberDTO toDto(Member entity) {
        return new MemberDTO(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                educationTitleConverter.toDto(entity.getEducationTitle()),
                scientificFieldConverter.toDto(entity.getScientificField()),
                departmentConverter.toDto(entity.getDepartment())
        );
    }

    @Override
    @JsonIgnore
    public Member toEntity(MemberDTO dto) {
        return new Member(
                dto.getId(),
                dto.getFirstname(),
                dto.getLastname(),
                academicTitleConverter.toEntity(dto.getAcademicTitleDTO()),
                educationTitleConverter.toEntity(dto.getEducationTitleDTO()),
                scientificFieldConverter.toEntity(dto.getScientificFieldDTO()),
                departmentConverter.toEntity(dto.getDepartmentDTO()));
    }
}
