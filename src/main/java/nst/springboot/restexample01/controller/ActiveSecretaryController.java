package nst.springboot.restexample01.controller;

import nst.springboot.restexample01.controller.service.ActiveSecretaryService;
import nst.springboot.restexample01.dto.ActiveHeadDTO;
import nst.springboot.restexample01.dto.ActiveSecretaryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/active-secretary")
public class ActiveSecretaryController {

    private final ActiveSecretaryService activeSecretaryService;

    public ActiveSecretaryController(ActiveSecretaryService activeSecretaryService) {
        this.activeSecretaryService = activeSecretaryService;
    }

    //vrati sve
    @GetMapping
    public ResponseEntity<List<ActiveSecretaryDTO>> getAll() {
        List<ActiveSecretaryDTO> dtos = activeSecretaryService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public ActiveSecretaryDTO findById(@PathVariable("id") Long id) throws Exception {
        return activeSecretaryService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public ActiveSecretaryDTO queryById(@RequestParam("id") Long id) throws Exception {
        return activeSecretaryService.findById(id);
    }

    //azuriraj

    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        activeSecretaryService.delete(id);
        return new ResponseEntity<>("Active secretary removed!", HttpStatus.OK);

    }

}
