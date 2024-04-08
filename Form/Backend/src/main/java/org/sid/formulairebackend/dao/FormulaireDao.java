package org.sid.formulairebackend.dao;

import org.sid.formulairebackend.bean.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaireDao extends JpaRepository<Formulaire,Long> {
    int deleteBycode(String code);

    List<Formulaire> findAll();

    Formulaire findByCode(String code);

    int deleteByCode(String code);
}
