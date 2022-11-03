package com.example.wineapi.data.dao;


import com.example.wineapi.data.entity.Container;

import java.util.List;

public interface ContainerDAO {
    Container insertContainer(Container container);
    Container selectContainer(Long id);
    void deleteContainer(Long user_id, Long wine_id);

    List<Long> selectMyContainers(Long user_id);
}
