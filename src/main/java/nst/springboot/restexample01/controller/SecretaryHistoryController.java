package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import nst.springboot.restexample01.controller.repository.SecretaryHistoryRepository;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.dto.SecretaryHistoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretary")
public class SecretaryHistoryController {
    private SecretaryHistoryService service;

    public SecretaryHistoryController(SecretaryHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SecretaryHistoryDTO> save(@Valid @RequestBody SecretaryHistoryDTO secretaryHistory) throws Exception {
        //ResponseEntity
        SecretaryHistoryDTO history = service.save(secretaryHistory);
        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SecretaryHistoryDTO>> getAll() {
        List<SecretaryHistoryDTO> histories = service.getAll();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public SecretaryHistoryDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return service.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public SecretaryHistoryDTO queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        System.out.println("Controller: " + id);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        /*
        try {
            departmentService.delete(id);
            return new ResponseEntity<>("Department removed!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(">>" + e.getMessage(), HttpStatus.NOT_FOUND);
        }*/

        service.delete(id);
        return new ResponseEntity<>("Head history removed!", HttpStatus.OK);

    }
}
