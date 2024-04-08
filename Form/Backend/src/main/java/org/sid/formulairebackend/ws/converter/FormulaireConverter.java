package org.sid.formulairebackend.ws.converter;

import org.sid.formulairebackend.bean.Formulaire;
import org.sid.formulairebackend.ws.dto.FormulaireDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormulaireConverter {
    public Formulaire toBean(FormulaireDto dto) {
        Formulaire bean = new Formulaire();
        bean.setCode(dto.getCode());
        bean.setEmail(dto.getEmail());
        bean.setName(dto.getName());
        bean.setEducational(dto.getEducational());
        bean.setPrenom(dto.getPrenom());
        bean.setNumber(dto.getNumber());
        return bean;
    }

    public FormulaireDto toDto(Formulaire bean) {
        FormulaireDto dto = new FormulaireDto();
        dto.setCode(bean.getCode());
        dto.setEmail(bean.getEmail());
        dto.setName(bean.getName());
        dto.setEducational(bean.getEducational());
        dto.setPrenom(bean.getPrenom());
        dto.setNumber(bean.getNumber());
        return dto;
    }

    public List<Formulaire> toBean(List<FormulaireDto> dtos) {
        List<Formulaire> beans = new ArrayList<>();
        for (FormulaireDto dto : dtos) {
            beans.add(toBean(dto));
        }
        return beans;
    }

    public List<FormulaireDto> toDto(List<Formulaire> beans) {
        List<FormulaireDto> dtos = new ArrayList<>();
        for (Formulaire dto : beans) {
            dtos.add(toDto(dto));
        }
        return dtos;
    }

}
