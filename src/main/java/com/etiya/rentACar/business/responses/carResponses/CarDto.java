package com.etiya.rentACar.business.responses.carResponses;

import java.time.LocalDate;


import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private int damageId;

	private int carId;

	private LocalDate date;

	private String description;

	private String carDescription;
	
	private CarStates carState;

	private String cityName;

	private int cityId;

	private double dailyPrice;



}
