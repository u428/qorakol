package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.model.entity.Groups;
import com.qorakol.ilm.ziyo.repository.GroupsRepository;
import com.qorakol.ilm.ziyo.repository.LanguageRepository;
import com.qorakol.ilm.ziyo.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupsRepository groupsRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public GroupServiceImpl(GroupsRepository groupsRepository, LanguageRepository languageRepository) {
        this.groupsRepository = groupsRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public void save(NewGroup newGroup) {
        Groups groups = new Groups();
        groups.setName(newGroup.getName());
        groups.setLanguageId(newGroup.getLanguageId());
        groups.setSubjectId(newGroup.getSubjectId());
        groups.setBegin(newGroup.getBegin());
        groups.setFinish(newGroup.getFinish());
        groups.setTeacherId(newGroup.getTeacherId());
        groupsRepository.save(groups);
    }
}
