package com.parcial.capassb.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetalles {

    private LocalDateTime fecha;
    private String mensaje;
    private String detalles;

}
