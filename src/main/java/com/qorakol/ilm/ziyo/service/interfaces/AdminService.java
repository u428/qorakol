package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.constant.AddController;
import com.qorakol.ilm.ziyo.constant.ChangePasswordAdmin;
import com.qorakol.ilm.ziyo.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdminService {
    void addMainImage(MainImageDto mainImageDto) throws IOException;
    void save(NewGroup newGroup) throws IOException;

    void putImage(MainImageDto mainImageDto) throws IOException;

    void paying(PaymentDto paymentDto) throws Exception;

    void changePayment(PaymentDto paymentDto) throws Exception;

    Object deleteImage(Long id);

    void createTeacher(RegTeacherDto regTeacherDto) throws Exception;

    void changeGroup(NewGroup newGroup) throws IOException;

    void removeStudentFromGroup(Long studentId, Long groupId) throws Exception;

    void addEvent(EventDto eventDto) throws Exception;

    void changeEvent(EventDto eventDto, long id) throws Exception;

    void deleteEvent(long id) throws Exception;

    void deleteGroup(Long id) throws Exception;

    void deleteTeacher(Long id) throws Exception;

    Long addImage(MultipartFile multipartFile) throws IOException;

    void changeTeacher(RegTeacherDto regTeacherDto) throws Exception;

    Object getAllControllers();

    Object getController(Long id);
    Object changePassword(ChangePasswordAdmin changePasswordAdmin);

    Object addController(AddController addController);
}
