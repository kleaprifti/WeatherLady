package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.WindEntity;
@Data
public class WeatherResponse {
     private String name;
    private WindEntity main;
    public WeatherResponse() {
    }

    public WeatherResponse(String name, WindEntity current) {
        this.name = name;
        this.main = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindEntity getCurrent() {
        return main;
    }

    public void setCurrent(WindEntity current) {
        this.main = current;
    }
}
