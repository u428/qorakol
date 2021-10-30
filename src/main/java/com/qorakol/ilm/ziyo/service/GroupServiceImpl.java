package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.model.entity.Groups;
import com.qorakol.ilm.ziyo.model.entity.Images;
import com.qorakol.ilm.ziyo.repository.GroupsRepository;
import com.qorakol.ilm.ziyo.repository.ImagesRepository;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.service.interfaces.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class GroupServiceImpl implements GroupService {


}
