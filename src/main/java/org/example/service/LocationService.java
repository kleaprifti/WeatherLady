package org.example.service;

import org.example.entity.Location;
import org.example.entity.WeatherParameters;

import javax.persistence.EntityManager;
import java.util.List;

public class LocationService {
        private EntityManager entityManager;

        public LocationService(EntityManager entityManager) {
            this.entityManager = entityManager;
        }


        public List<WeatherParameters> findByName(String name) {
            return entityManager.createQuery("SELECT wp FROM WeatherParameters wp, WindEntity WHERE wp.locationEntity.city = :name", WeatherParameters.class)
                    .setParameter("name", name)
                    .getResultList();
        }

        public void deleteById (int id) {
            entityManager.createQuery("delete from LocationEntity l where l.id = :id", Location.class )
                    .setParameter("id",id).executeUpdate();
            entityManager.refresh(Location.class);
        }


        public void selectAll() {
            List<WeatherParameters> list = entityManager.createQuery(" FROM WeatherParametersEntity wp ",WeatherParameters.class)
                    .getResultList();
            System.out.println(list+"\n");
        }
    }

