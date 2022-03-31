package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private int id;

    @Column(name = "cityName")
    private String cityName;

    @Column(name = "cityPlate")
    private String cityPlate;

    @OneToMany(mappedBy = "city")
    private List<Car> cars;

}
