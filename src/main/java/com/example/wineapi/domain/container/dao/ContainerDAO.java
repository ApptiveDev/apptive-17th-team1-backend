package com.example.wineapi.domain.container.dao;


import com.example.wineapi.domain.container.entity.Container;

import java.util.List;

public interface ContainerDAO {
    Container insertContainer(Container container);
    Container selectContainer(Long id);
    void deleteContainer(Long user_id, Long wine_id);

    List<Container> selectMyContainers(Long user_id);
}
