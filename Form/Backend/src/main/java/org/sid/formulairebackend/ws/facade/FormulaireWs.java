package org.sid.formulairebackend.ws.facade;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.sid.formulairebackend.bean.Formulaire;
import org.sid.formulairebackend.service.facade.FormulaireService;
import org.sid.formulairebackend.ws.dto.FormulaireDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/formulaire/")
@CrossOrigin("*")
public class FormulaireWs {

    public FormulaireWs(FormulaireService formulaireService) {
        this.formulaireService = formulaireService;
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<FormulaireDto> findBycCode(@PathVariable  String code) {
        return formulaireService.findByCode(code);
    }

    @GetMapping
    public ResponseEntity<List<FormulaireDto>> findAll() {
        return formulaireService.findAll();
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return formulaireService.deleteByCode(code);
    }

    @PostMapping
    public ResponseEntity<FormulaireDto> save(@RequestBody  FormulaireDto formulaireDto) {
        return formulaireService.save(formulaireDto);
    }

    private FormulaireService  formulaireService;

}
