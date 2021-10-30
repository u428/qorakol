package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.MainImageDto;
import com.qorakol.ilm.ziyo.model.dto.NewGroup;

public interface AdminService {
    Object addMainImage(MainImageDto mainImageDto);
    void save(NewGroup newGroup);
}
