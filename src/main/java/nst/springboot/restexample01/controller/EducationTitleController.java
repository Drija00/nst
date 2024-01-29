package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.EducationTitleService;
import nst.springboot.restexample01.dto.EducationTitleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-title")
public class EducationTitleController {

    private EducationTitleService educationTitleService;

    public EducationTitleController(EducationTitleService educationTitleService) {
        this.educationTitleService = educationTitleService;
    }

    @PostMapping
    public ResponseEntity<EducationTitleDTO> save(@Valid @RequestBody EducationTitleDTO educationTitleDTO) throws Exception {
        //ResponseEntity
        EducationTitleDTO titleDTO = educationTitleService.save(educationTitleDTO);
        return new ResponseEntity<>(titleDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EducationTitleDTO>> getAll() {
        List<EducationTitleDTO> titles = educationTitleService.getAll();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public EducationTitleDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return educationTitleService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public EducationTitleDTO queryById(@RequestParam("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return educationTitleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        educationTitleService.delete(id);
        return new ResponseEntity<>("Education title removed!", HttpStatus.OK);

    }
}
