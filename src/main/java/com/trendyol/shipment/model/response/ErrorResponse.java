package com.trendyol.shipment.model.response;

import com.trendyol.shipment.model.ErrorModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private List<ErrorModel> errorMessage;
}