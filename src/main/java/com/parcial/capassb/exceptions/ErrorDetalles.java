package com.parcial.capassb.exceptions;



import java.time.LocalDateTime;


public record ErrorDetalles(LocalDateTime fecha, String mensaje, String detalles)
{}


