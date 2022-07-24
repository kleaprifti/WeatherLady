package org.example.dao;

import java.util.List;
import java.util.Map;

public interface WeatherDao  {
    <T> void insert(T object);

    <T> List<T> retrieveByField(Class<T> clazz, Map<String, Object> criterias);

    <T> T retrievebyId(Class<T> clazz, int id);

    <T> List<T> retrieveByQuery(Class<T> clazz, int id, Map<String, Object> criterias);

    <T> List<T> retrieveAll(Class<T> clazz);

    <T> void delete(Class<T> clazz, int id);

}

