package com.example.vishwatej.controller;


import com.example.vishwatej.dto.ProductDto;
import com.example.vishwatej.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProdcut(@RequestBody ProductDto productDto){

        String str = productService.saveProduct(productDto);

        if(str.equals("Product saved successfully")){
            return ResponseEntity.ok().body(str);
        }
        return ResponseEntity.badRequest().body(str);
    }

    @GetMapping("/")
    public ResponseEntity<?> getProdcutByVendeor(@RequestParam String vendorName){

        List<ProductDto> productDtoList = productService.getAllProdcutOfVendor(vendorName);

        if(productDtoList.isEmpty()){
            return ResponseEntity.badRequest().body("No products available");
        }
        return ResponseEntity.ok().body(productDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,
                                           @RequestParam Integer stock,
                                           @RequestParam Integer price){

        String str = productService.updateData(stock,price,id);

        if(str.equals("Product Updated Successfully")){
            return ResponseEntity.ok().body(str);
        }
        return ResponseEntity.badRequest().body(str);
    }

}
