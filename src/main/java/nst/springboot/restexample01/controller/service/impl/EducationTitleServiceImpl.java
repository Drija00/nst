package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.controller.repository.EducationTitleRepository;
import nst.springboot.restexample01.controller.service.EducationTitleService;
import nst.springboot.restexample01.converter.impl.EducationTitleConverter;
import nst.springboot.restexample01.dto.EducationTitleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationTitleServiceImpl implements EducationTitleService {

    private EducationTitleConverter educationTitleConverter;
    private EducationTitleRepository educationTitleRepository;

    public EducationTitleServiceImpl(EducationTitleConverter educationTitleConverter, EducationTitleRepository educationTitleRepository) {
        this.educationTitleConverter = educationTitleConverter;
        this.educationTitleRepository = educationTitleRepository;
    }

    @Override
    public EducationTitleDTO save(EducationTitleDTO educationTitleDTO) throws Exception {
        EducationTitle title = educationTitleConverter.toEntity(educationTitleDTO);
        title = educationTitleRepository.save(title);
        return educationTitleConverter.toDto(title);
    }

    @Override
    public List<EducationTitleDTO> getAll() {
        return educationTitleRepository
                .findAll()
                .stream().map(entity -> educationTitleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<EducationTitle> title = educationTitleRepository.findById(id);
        if (title.isPresent()) {
            EducationTitle title1 = title.get();
            educationTitleRepository.delete(title1);
        } else {
            throw new Exception("Education title does not exist!");
        }
    }

    @Override
    public void update(EducationTitleDTO educationTitleDTO) throws Exception {
        throw new Exception();
    }

    @Override
    public EducationTitleDTO findById(Long id) throws Exception {
        Optional<EducationTitle> title = educationTitleRepository.findById(id);
        if (title.isPresent()) {
            EducationTitle title1 = title.get();
            return educationTitleConverter.toDto(title1);
        } else {
            throw new Exception("Education title does not exist!");
        }
    }
}
