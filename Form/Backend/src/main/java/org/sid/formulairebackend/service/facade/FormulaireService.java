package org.sid.formulairebackend.service.facade;

import org.sid.formulairebackend.bean.Formulaire;
import org.sid.formulairebackend.ws.dto.FormulaireDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormulaireService {


     ResponseEntity<FormulaireDto>   findByCode(String code);

    int deleteByCode(String code);

     ResponseEntity<List<FormulaireDto>>  findAll();

    ResponseEntity<FormulaireDto> save(FormulaireDto formulaireDto);

    int update(FormulaireDto formulaireDto,Formulaire formulaire);
}
