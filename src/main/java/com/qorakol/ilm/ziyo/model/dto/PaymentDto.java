package com.qorakol.ilm.ziyo.model.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


@Data
public class PaymentDto {

    public Long id;

    public double summa;

    public Long studentId;

    public Long groupId;


}
