package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.AcademicTitleController;
import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.repository.AcademicTitleRepository;
import nst.springboot.restexample01.controller.service.AcademicTitleService;
import nst.springboot.restexample01.converter.impl.AcademicTitleConverter;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {
    private AcademicTitleConverter academicTitleConverter;
    private AcademicTitleRepository academicTitleRepository;

    public AcademicTitleServiceImpl(AcademicTitleConverter academicTitleConverter, AcademicTitleRepository academicTitleRepository) {
        this.academicTitleConverter = academicTitleConverter;
        this.academicTitleRepository = academicTitleRepository;
    }

    @Override
    public AcademicTitleDTO save(AcademicTitleDTO academicTitleDTO) throws Exception {
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            AcademicTitle title = academicTitleConverter.toEntity(academicTitleDTO);
            title = academicTitleRepository.save(title);
            return academicTitleConverter.toDto(title);
    }

    @Override
    public List<AcademicTitleDTO> getAll() {
        return academicTitleRepository
                .findAll()
                .stream().map(entity -> academicTitleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AcademicTitle> title = academicTitleRepository.findById(id);
        if (title.isPresent()) {
            AcademicTitle title1 = title.get();
            academicTitleRepository.delete(title1);
        } else {
            throw new Exception("Academic title does not exist!");
        }
    }

    @Override
    public void update(AcademicTitleDTO academicTitleDTO) throws Exception {
        throw new Exception();
    }

    @Override
    public AcademicTitleDTO findById(Long id) throws Exception {
        Optional<AcademicTitle> title = academicTitleRepository.findById(id);
        if (title.isPresent()) {
            AcademicTitle title1 = title.get();
            return academicTitleConverter.toDto(title1);
        } else {
            throw new Exception("Academic title does not exist!");
        }
    }
}
