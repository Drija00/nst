package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.controller.domain.ScientificField;
import nst.springboot.restexample01.controller.repository.ScientificFieldRepository;
import nst.springboot.restexample01.controller.service.ScientificFieldService;
import nst.springboot.restexample01.converter.impl.ScientificFieldConverter;
import nst.springboot.restexample01.dto.ScientificFieldDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScientificFieldServiceImpl implements ScientificFieldService {

    private ScientificFieldConverter scientificFieldConverter;
    private ScientificFieldRepository scientificFieldRepository;

    public ScientificFieldServiceImpl(ScientificFieldConverter scientificFieldConverter, ScientificFieldRepository scientificFieldRepository) {
        this.scientificFieldConverter = scientificFieldConverter;
        this.scientificFieldRepository = scientificFieldRepository;
    }

    @Override
    public ScientificFieldDTO save(ScientificFieldDTO scientificFieldDTO) throws Exception {
        ScientificField field = scientificFieldConverter.toEntity(scientificFieldDTO);
        field = scientificFieldRepository.save(field);
        return scientificFieldConverter.toDto(field);
    }

    @Override
    public List<ScientificFieldDTO> getAll() {
        return scientificFieldRepository
                .findAll()
                .stream().map(entity -> scientificFieldConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ScientificField> field = scientificFieldRepository.findById(id);
        if (field.isPresent()) {
            ScientificField title1 = field.get();
            scientificFieldRepository.delete(title1);
        } else {
            throw new Exception("Scientific field does not exist!");
        }
    }

    @Override
    public void update(ScientificFieldDTO scientificFieldDTO) throws Exception {
        throw new Exception();
    }

    @Override
    public ScientificFieldDTO findById(Long id) throws Exception {
        Optional<ScientificField> field = scientificFieldRepository.findById(id);
        if (field.isPresent()) {
            ScientificField field1 = field.get();
            return scientificFieldConverter.toDto(field1);
        } else {
            throw new Exception("Scientific field does not exist!");
        }
    }
}
