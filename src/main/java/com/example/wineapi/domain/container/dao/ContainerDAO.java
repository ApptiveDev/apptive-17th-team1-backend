package com.example.wineapi.domain.container.dao;


import com.example.wineapi.domain.container.entity.Container;

import java.util.List;
import java.util.Optional;

public interface ContainerDAO {
    Container insertContainer(Container container);
    Container selectContainer(Long id);

    Optional<Container> selectContainer(Long userId, Long wineId);
    void deleteContainer(Long user_id, Long wine_id);

    void deleteContainers(Long user_id);
    List<Container> selectMyContainers(Long user_id);

    List<Container> selectMyLikedContainers(Long user_id);
}
