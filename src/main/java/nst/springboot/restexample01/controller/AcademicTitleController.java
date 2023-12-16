package nst.springboot.restexample01.controller;


import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.AcademicTitleService;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic_title")
public class AcademicTitleController {
    private AcademicTitleService academicTitleService;

    public AcademicTitleController(AcademicTitleService academicTitleService) {
        this.academicTitleService = academicTitleService;
    }

    @PostMapping
    public ResponseEntity<AcademicTitleDTO> save(@Valid @RequestBody AcademicTitleDTO academicTitleDTO) throws Exception {
        //ResponseEntity
        AcademicTitleDTO titleDTO = academicTitleService.save(academicTitleDTO);
        return new ResponseEntity<>(titleDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcademicTitleDTO>> getAll() {
        List<AcademicTitleDTO> titles = academicTitleService.getAll();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public AcademicTitleDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return academicTitleService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public AcademicTitleDTO queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        System.out.println("Controller: " + id);
        return academicTitleService.findById(id);
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

        academicTitleService.delete(id);
        return new ResponseEntity<>("Academic title removed!", HttpStatus.OK);

    }
}
