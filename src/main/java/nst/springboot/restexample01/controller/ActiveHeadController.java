package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.ActiveHeadService;
import nst.springboot.restexample01.dto.ActiveHeadDTO;
import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/active-head")
public class ActiveHeadController {

    private final ActiveHeadService activeHeadService;

    public ActiveHeadController(ActiveHeadService activeHeadService) {
        this.activeHeadService = activeHeadService;
    }

    /*@PostMapping
    public ResponseEntity<ActiveHeadDTO> save(@Valid @RequestBody ActiveHeadDTO activeHeadDTO) throws Exception {
        //ResponseEntity
        ActiveHeadDTO activeHeadDTO1 = activeHeadService.save(activeHeadDTO);
        return new ResponseEntity<>(activeHeadDTO1, HttpStatus.CREATED);
    }*/

    //vrati sve
    @GetMapping
    public ResponseEntity<List<ActiveHeadDTO>> getAll() {
        List<ActiveHeadDTO> dtos = activeHeadService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public ActiveHeadDTO findById(@PathVariable("id") Long id) throws Exception {
        return activeHeadService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public ActiveHeadDTO queryById(@RequestParam("id") Long id) throws Exception {
        return activeHeadService.findById(id);
    }

    //azuriraj

    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        activeHeadService.delete(id);
        return new ResponseEntity<>("Active head removed!", HttpStatus.OK);

    }

}
