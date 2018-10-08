package com.finleap.forecast.dto;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto {
    private String cod;
    private float message;
    private float cnt;
    @Builder.Default
    private ArrayList<Items> list = new ArrayList<>();
    private City city;
}


