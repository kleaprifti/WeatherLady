package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "weather_parameters")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer weather_id;
    @Column
    private Double temperature;
    @Column
    private Double pressure;
    @Column
    private Double humidity;

    public WeatherParameters(WindEntity response, Location location) {
    }
}


