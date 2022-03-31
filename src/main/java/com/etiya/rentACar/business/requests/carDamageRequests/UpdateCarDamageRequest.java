package com.etiya.rentACar.business.requests.carDamageRequests;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {

	
	private int id;

	@NotNull
	private int carId;

	@NotNull
	private LocalDate accidentDate;

	@NotNull
	@Length(min = 2 ,max = 50)
	private String description;

}
