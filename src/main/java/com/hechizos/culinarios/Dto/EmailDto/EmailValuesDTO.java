package com.hechizos.culinarios.Dto.EmailDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailValuesDTO {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;
    private String tokenPassword;
}
