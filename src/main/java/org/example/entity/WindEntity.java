package org.example.entity;/*
package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
    @Table(name = "wind")
    @NoArgsConstructor
    @AllArgsConstructor
   @Data
    public class WindEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Integer id;
        @Column
        private Integer direction;
        @Column
        private Double speed;
        @OneToOne (mappedBy = "windEntity")
        @ToString.Exclude
        private WeatherParameters weatherParameters;

      //  public WindEntity(WeatherResponse weatherResponse) {
         //   this.direction = weatherResponse.getWind().getDeg();
           // this.speed = weatherResponse.getWind().getSpeed();
       // }

    }

*/


import lombok.Data;

@Data
public class WindEntity {
    private double temp;
    private int humidity;

    public WindEntity() {
    }

    public WindEntity(double temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

