package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.domain.Role;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.MemberHeadSecDTO;
import nst.springboot.restexample01.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberHeadSecConverter implements DtoEntityConverter<MemberHeadSecDTO, Member> {
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;
    @Autowired
    private RoleConverter roleConverter;

    @Override
    public MemberHeadSecDTO toDto(Member entity) {
        RoleDTO r = new RoleDTO();
        return new MemberHeadSecDTO(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                educationTitleConverter.toDto(entity.getEducationTitle()),
                scientificFieldConverter.toDto(entity.getScientificField()),
                entity.getDepartment().getId(),
                roleConverter.toDto(entity.getRole()));
    }

    @Override
    public Member toEntity(MemberHeadSecDTO dto) {
        Department d = new Department();
        d.setId(dto.getDepartment());
        return new Member(
                dto.getId(),
                dto.getFirstname(),
                dto.getLastname(),
                academicTitleConverter.toEntity(dto.getAcademicTitle()),
                educationTitleConverter.toEntity(dto.getEducationTitle()),
                scientificFieldConverter.toEntity(dto.getScientificField()),
                d,
                roleConverter.toEntity(dto.getRoleDTO())
                );
    }
}
