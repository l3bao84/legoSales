package com.LeBao.sales.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private int code;
    private String message;
    private Object data;
    private String link;
}
