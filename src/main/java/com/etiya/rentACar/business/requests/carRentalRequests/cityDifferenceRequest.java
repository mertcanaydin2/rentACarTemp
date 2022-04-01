package com.etiya.rentACar.business.requests.carRentalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cityDifferenceRequest {

    private int id;

    private String rentCity;

    private String returnCity;

    private int carId;
}
