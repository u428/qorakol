package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.Subjects;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.repository.SubjectsRepository;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;

@Service
public class StaticServiceImpl implements StaticService {
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;

    public StaticServiceImpl(LanguageRepository languageRepository, SubjectsRepository subjectsRepository) {
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public Object getAllLang() {
        return languageRepository.findAll();
    }

    @Override
    public void addSubject(SubjectDto subjectDto) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectDto, subjects);
        subjectsRepository.save(subjects);
    }
}
