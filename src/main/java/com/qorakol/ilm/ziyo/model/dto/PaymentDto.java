package com.qorakol.ilm.ziyo.model.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class PaymentDto {

    public double summa;

    public Long studentId;

    public Long groupId;


}
