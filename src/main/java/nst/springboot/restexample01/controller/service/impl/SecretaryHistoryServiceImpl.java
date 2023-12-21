package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import nst.springboot.restexample01.controller.repository.SecretaryHistoryRepository;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.converter.impl.SecretaryHistoryConverter;
import nst.springboot.restexample01.dto.SecretaryHistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecretaryHistoryServiceImpl implements SecretaryHistoryService {

    private SecretaryHistoryRepository repository;

    private SecretaryHistoryConverter secretaryHistoryConverter;

    public SecretaryHistoryServiceImpl(SecretaryHistoryRepository repository, SecretaryHistoryConverter secretaryHistoryConverter) {
        this.repository = repository;
        this.secretaryHistoryConverter = secretaryHistoryConverter;
    }


    @Override
    public SecretaryHistoryDTO save(SecretaryHistoryDTO secretaryHistoryDTO) throws Exception {
        SecretaryHistory history = secretaryHistoryConverter.toEntity(secretaryHistoryDTO);
        history = repository.save(history);
        return secretaryHistoryConverter.toDto(history);
    }

    @Override
    public List<SecretaryHistoryDTO> getAll() {
        return repository
                .findAll()
                .stream().map(entity -> secretaryHistoryConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<SecretaryHistory> history = repository.findById(id);
        if (history.isPresent()) {
            SecretaryHistory history1 = history.get();
            repository.delete(history1);
        } else {
            throw new Exception("Secretary history does not exist!");
        }
    }

    @Override
    public void update(SecretaryHistoryDTO secretaryHistoryDTO) throws Exception {

    }

    @Override
    public SecretaryHistoryDTO findById(Long id) throws Exception {
        Optional<SecretaryHistory> history = repository.findById(id);
        if (history.isPresent()) {
            SecretaryHistory history1 = history.get();
            return secretaryHistoryConverter.toDto(history1);
        } else {
            throw new Exception("Secretary history not exist!");
        }
    }
}
