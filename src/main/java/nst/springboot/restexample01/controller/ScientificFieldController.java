package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.ScientificFieldService;
import nst.springboot.restexample01.dto.ScientificFieldDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scientific_field")
public class ScientificFieldController {
    private ScientificFieldService scientificFieldService;

    public ScientificFieldController(ScientificFieldService scientificFieldService) {
        this.scientificFieldService = scientificFieldService;
    }

    @PostMapping
    public ResponseEntity<ScientificFieldDTO> save(@Valid @RequestBody ScientificFieldDTO scientificFieldDTO) throws Exception {
        //ResponseEntity
        ScientificFieldDTO fieldDTO = scientificFieldService.save(scientificFieldDTO);
        return new ResponseEntity<>(fieldDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScientificFieldDTO>> getAll() {
        List<ScientificFieldDTO> fields = scientificFieldService.getAll();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public ScientificFieldDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return scientificFieldService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public ScientificFieldDTO queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        System.out.println("Controller: " + id);
        return scientificFieldService.findById(id);
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

        scientificFieldService.delete(id);
        return new ResponseEntity<>("Scientific field removed!", HttpStatus.OK);

    }
}
