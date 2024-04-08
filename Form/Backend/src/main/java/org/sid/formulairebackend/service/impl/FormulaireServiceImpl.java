package org.sid.formulairebackend.service.impl;

import org.sid.formulairebackend.bean.Formulaire;
import org.sid.formulairebackend.dao.FormulaireDao;
import org.sid.formulairebackend.service.facade.FormulaireService;
import org.sid.formulairebackend.ws.converter.FormulaireConverter;
import org.sid.formulairebackend.ws.dto.FormulaireDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class FormulaireServiceImpl implements FormulaireService {


    @Override
    public ResponseEntity<FormulaireDto>   findByCode(String code) {
        return ResponseEntity.ok(formulaireConverter.toDto(formulaireDao.findByCode(code))) ;
    }

    @Transactional
    @Override
    public int deleteByCode(String code) {
        return formulaireDao.deleteBycode(code);
    }


    @Override
    public ResponseEntity<List<FormulaireDto>>  findAll() {
        return ResponseEntity.ok(formulaireConverter.toDto(formulaireDao.findAll())) ;
    }


    @Override
    public ResponseEntity<FormulaireDto> save(FormulaireDto formulaireDto) {
        Formulaire formulaire = formulaireConverter.toBean(formulaireDto);
        if (Objects.equals(formulaireDto.getCode(), "")) {
            return ResponseEntity.badRequest().build();
        } else {
            if (formulaireDao.findByCode(formulaire.getCode()) != null) {
                update(formulaireDto, formulaire);
                formulaireDao.save(formulaire);
                return ResponseEntity.ok(formulaireDto);
            } else {
                formulaireDao.save(formulaire);
                return ResponseEntity.ok(formulaireDto);
            }
        }
    }


    @Override
    public int update(FormulaireDto formulaireDto,Formulaire formulaire) {
        BeanUtils.copyProperties(formulaireDto, formulaire);
        return 1;
    }

    @Autowired
    private FormulaireDao formulaireDao;
    @Autowired
    private FormulaireConverter formulaireConverter;

}
