package com.qorakol.ilm.ziyo.service.interfaces;

public interface TeacherService {
    Object getGroups(String login);

    Object getGroupStudents(String login, Long id);
}
