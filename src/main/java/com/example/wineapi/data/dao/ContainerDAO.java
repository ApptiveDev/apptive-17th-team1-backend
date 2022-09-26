package com.example.wineapi.data.dao;


import com.example.wineapi.data.entity.Container;

public interface ContainerDAO {
    Container insertContainer(Container container);
    Container selectContainer(Long id);
    void deleteContainer(Long id) throws Exception;
}
