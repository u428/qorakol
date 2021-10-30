package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.MainImageDto;
import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.model.entity.Groups;
import com.qorakol.ilm.ziyo.model.entity.Images;
import com.qorakol.ilm.ziyo.model.entity.MainImage;
import com.qorakol.ilm.ziyo.repository.GroupsRepository;
import com.qorakol.ilm.ziyo.repository.ImagesRepository;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.repository.MainImagesRepository;
import com.qorakol.ilm.ziyo.service.interfaces.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AdminServiceImpl implements AdminService {


    private final GroupsRepository groupsRepository;
    private final LanguageRepository languageRepository;
    private final ImagesRepository imagesRepository;
    private final MainImagesRepository mainImagesRepository;

    private Path fileStoragePath;

    @Autowired
    public AdminServiceImpl(GroupsRepository groupsRepository, LanguageRepository languageRepository, ImagesRepository imagesRepository, MainImagesRepository mainImagesRepository) {
        this.groupsRepository = groupsRepository;
        this.languageRepository = languageRepository;
        this.imagesRepository = imagesRepository;
        this.mainImagesRepository = mainImagesRepository;
        fileStoragePath = Paths.get("java/java_code").toAbsolutePath().normalize();
    }

    @Override
    public Object addMainImage(MainImageDto mainImageDto) {
        MainImage mainImage = new MainImage();
        mainImage.setDescryption(mainImageDto.getDescryption());
        mainImage.setName(mainImageDto.getName());
        try {
            MultipartFile multipartFile = mainImageDto.getFiles();
            Images images = new Images();
            images.setContentType(multipartFile.getContentType());
            images.setName(multipartFile.getOriginalFilename());
            images.setFileSize(multipartFile.getSize());
            imagesRepository.save(images);
            String AA = multipartFile.getOriginalFilename();
            String fileName = String.valueOf(images.getId()) + AA.substring(AA.length() - 4, AA.length());
            images.setExtention(AA.substring(AA.length() - 4));
            System.out.println(fileStoragePath);
            Path filePath = Paths.get(fileStoragePath + "//" + fileName);
            images.setUploadPath(String.valueOf(filePath));
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            imagesRepository.save(images);
            mainImage.setImagesId(images.getId());
            mainImagesRepository.save(mainImage);
            return "SUCCESS";
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void save(NewGroup newGroup) {
        Groups groups = new Groups();
        BeanUtils.copyProperties(newGroup, groups);
        groups.setName(newGroup.getName());
        groups.setLanguageId(newGroup.getLanguageId());
        groups.setSubjectId(newGroup.getSubjectId());
        groups.setBegin(newGroup.getBegin());
        groups.setFinish(newGroup.getFinish());
//        groups.setTeacherId(newGroup.getTeacherId());
        groups.setPrice(newGroup.getPrice());

        try {
            MultipartFile multipartFile = newGroup.getFiles();
            Images images = new Images();
            images.setContentType(multipartFile.getContentType());
            images.setName(multipartFile.getOriginalFilename());
            images.setFileSize(multipartFile.getSize());
            imagesRepository.save(images);
            String AA = multipartFile.getOriginalFilename();
            String fileName = String.valueOf(images.getId()) + AA.substring(AA.length() - 4, AA.length());
            images.setExtention(AA.substring(AA.length() - 4));
            System.out.println(fileStoragePath);
            Path filePath = Paths.get(fileStoragePath + "//" + fileName);
            images.setUploadPath(String.valueOf(filePath));
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            imagesRepository.save(images);
            groups.setImagesId(images.getId());
            groupsRepository.save(groups);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
