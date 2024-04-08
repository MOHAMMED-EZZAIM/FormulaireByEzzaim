package org.sid.formulairebackend.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormulaireDto {
    private String code;
    private String email;
    private String name ;
    private String prenom;
    private String number;
    private String educational;

}
