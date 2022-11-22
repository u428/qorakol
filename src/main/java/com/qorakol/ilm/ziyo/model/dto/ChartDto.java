package com.qorakol.ilm.ziyo.model.dto;

import java.util.Date;

public class ChartDto {

    public Long count;

    public Date date;

    public ChartDto(Long count, Date date) {
        this.count = count;
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
