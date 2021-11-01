package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.Images;
import com.qorakol.ilm.ziyo.model.entity.MainImage;
import com.qorakol.ilm.ziyo.model.entity.Subjects;
import com.qorakol.ilm.ziyo.repository.ImagesRepository;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.repository.MainImagesRepository;
import com.qorakol.ilm.ziyo.repository.SubjectsRepository;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class StaticServiceImpl implements StaticService {
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;
    private final MainImagesRepository mainImagesRepository;
    private final ImagesRepository imagesRepository;

    public StaticServiceImpl(LanguageRepository languageRepository, SubjectsRepository subjectsRepository, MainImagesRepository mainImagesRepository, ImagesRepository imagesRepository) {
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
        this.mainImagesRepository = mainImagesRepository;
        this.imagesRepository = imagesRepository;
    }

    @Override
    public Object getAllLang() {
        return languageRepository.findAll();
    }

    @Override
    public Object getAllSubjects() {
        return subjectsRepository.findAllByDeleteIsFalse();
    }

    @Override
    public void addSubject(SubjectDto subjectDto) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectDto, subjects);
        subjectsRepository.save(subjects);
    }

    @Override
    public Object putSubject(Long id, SubjectDto subjectDto) {
        Subjects subject = subjectsRepository.findByIdAndDeleteIsFalse(id);
        if (subject !=null) {
            BeanUtils.copyProperties(subjectDto, subjectDto);
            subjectsRepository.save(subject);
            return "SUCCESS";
        }else return "ERROR";
    }

    @Override
    public Object deleteSubject(Long id) {
        Subjects subjects = subjectsRepository.findByIdAndDeleteIsFalse(id);
        subjects.setDelete(true);
        subjectsRepository.save(subjects);
        return true;
    }


    @Override
    public ResponseEntity images(Long id) throws MalformedURLException {
        Images images=imagesRepository.findById(id).get();
        Path path= Paths.get(images.getUploadPath());
        Resource resource= new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(images.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName="+resource.getFilename())
                .body(resource);
    }


    @Override
    public Object getMainImages() {
        Pageable pageable= PageRequest.of(0, 3);
        Page<MainImage> imagePage = mainImagesRepository.findAllByDeleteIsFalse(pageable);
        return imagePage;
    }


}
