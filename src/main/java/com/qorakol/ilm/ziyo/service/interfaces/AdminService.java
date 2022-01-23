package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.*;

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

    void removeStudentFromGroup(Long studentId, Long groupId) throws Exception;

    void addEvent(EventDto eventDto) throws Exception;

    void changeEvent(EventDto eventDto, long id) throws Exception;

    void deleteEvent(long id) throws Exception;
}
