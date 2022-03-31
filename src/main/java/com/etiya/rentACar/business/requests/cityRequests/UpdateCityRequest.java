package com.etiya.rentACar.business.requests.cityRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {


    private int id;

    @Length(min = 2, max = 50)
    private String cityName;

    @Length(min = 2, max = 2)
    private String cityPlate;
}
