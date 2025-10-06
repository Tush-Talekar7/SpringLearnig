package com.example.vishwatej.service;

import com.example.vishwatej.dto.ProductDto;
import com.example.vishwatej.model.Product;
import com.example.vishwatej.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public String saveProduct(ProductDto productDto) {

        Optional<Product> existingProduct = productRepository.findBySku(productDto.getSku());

        if(existingProduct.isPresent()){
            return "Product already present";
        }

        Product product = new Product(null,productDto.getName(),productDto.getDescription(),productDto.getVendor()
                ,productDto.getPrice(),productDto.getStock(),productDto.getCurrency(),productDto.getSku());

        productRepository.save(product);
        return "Product saved successfully";


    }

    public List<ProductDto> getAllProdcutOfVendor(String vendorName) {

        List<Product> productList = productRepository.findByVendor(vendorName);

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product:productList){

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setVendor(product.getVendor());
            productDto.setSku(product.getSku());
            productDto.setDescription(product.getDescription());
            productDto.setCurrency(product.getCurrency());
            productDto.setPrice(product.getPrice());

            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    public String updateData(Integer stock, Integer price,Integer id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            Product newProduct = product.get();

            newProduct.setPrice(price);
            newProduct.setStock(stock);

            productRepository.save(newProduct);

            return "Product Updated Successfully";
        }
        return "Product Does NOT exist";
    }

    public void productsPriceGreater(Integer price){

    }
}
