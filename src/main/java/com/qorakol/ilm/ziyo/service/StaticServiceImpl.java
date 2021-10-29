package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.MainImage;
import com.qorakol.ilm.ziyo.model.entity.Subjects;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.repository.MainImagesRepository;
import com.qorakol.ilm.ziyo.repository.SubjectsRepository;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StaticServiceImpl implements StaticService {
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;
    private final MainImagesRepository mainImagesRepository;

    public StaticServiceImpl(LanguageRepository languageRepository, SubjectsRepository subjectsRepository, MainImagesRepository mainImagesRepository) {
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
        this.mainImagesRepository = mainImagesRepository;

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

    @Override
    public Object getMainImages() {
        Pageable pageable= PageRequest.of(0, 3);
        Page<MainImage> imagePage = mainImagesRepository.findAll(pageable);
        return imagePage;
    }

    @Override
    public Object getAllSubjects() {
        return subjectsRepository.findAll();
    }
}
