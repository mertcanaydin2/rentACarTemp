package com.etiya.rentACar.business.requests.cityRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
    @JsonIgnore
    private int id;

    @NotNull
    @Length(min = 2, max = 50)
    private String cityName;

    @NotNull
    @Length(min = 2, max = 2)
    private String cityPlate;
}
