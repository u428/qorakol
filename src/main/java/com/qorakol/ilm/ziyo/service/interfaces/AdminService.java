package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.MainImageDto;
import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.model.dto.PaymentDto;
import com.qorakol.ilm.ziyo.model.dto.RegTeacherDto;

import java.io.IOException;

public interface AdminService {
    void addMainImage(MainImageDto mainImageDto) throws IOException;
    void save(NewGroup newGroup) throws IOException;

    void putImage(MainImageDto mainImageDto) throws IOException;

    void paying(PaymentDto paymentDto) throws Exception;

    void changePayment(PaymentDto paymentDto) throws Exception;

    Object deleteImage(Long id);

    void createTeacher(RegTeacherDto regTeacherDto) throws IOException;

    void changeGroup(NewGroup newGroup, Long id) throws IOException;
}
