package com.qorakol.ilm.ziyo.service.interfaces;


import com.qorakol.ilm.ziyo.model.dto.SubjectDto;

public interface StaticService {
    Object getAllLang();

    void addSubject(SubjectDto subjectDto);

    Object getMainImages();

    Object getAllSubjects();
}
