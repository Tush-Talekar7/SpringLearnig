package com.example.vishwatej.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    String name;
    String description;
    String vendor;
    Integer price;
    Integer stock;
    String currency;
    String sku;
}
