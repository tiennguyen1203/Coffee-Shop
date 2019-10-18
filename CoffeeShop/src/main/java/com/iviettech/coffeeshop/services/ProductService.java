/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.coffeeshop.services;

import com.iviettech.coffeeshop.entities.ImageEntity;
import com.iviettech.coffeeshop.entities.ProductEntity;
import com.iviettech.coffeeshop.entities.PromotionEntity;
import com.iviettech.coffeeshop.repositories.ImageRepository;
import com.iviettech.coffeeshop.repositories.ProductRepository;
import com.iviettech.coffeeshop.repositories.PromotionRepository;
import com.iviettech.coffeeshop.repositories.VoteRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * <<<<<<< HEAD @a
 *
 *
 * uthor PC
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    PromotionRepository promotionRepository;

    public List<ProductEntity> getProducts(){
        
        List<ProductEntity> products = (List<ProductEntity>) productRepository.getAll();
        
        for (ProductEntity product : products) {
            product.setImages(imageRepository.getImagesByProductId(product.getId()));
            product.setVotes(voteRepository.getVotesByProductId(product.getId()));
            product.setPromotions(promotionRepository.getPromotionsByProductId(product.getId(), new Date()));
        }
        return products;
    }
    public List<ProductEntity> getProducts(int CategoryId) {
        return productRepository.getProductsByCategoryId(CategoryId);
    }

    public List<ProductEntity> getProducts(String name) {
        List<ProductEntity> products = productRepository.getProductsByCategoryName(name);
        for (ProductEntity product : products) {
            product.setImages(imageRepository.getImagesByProductId(product.getId()));
            product.setVotes(voteRepository.getVotesByProductId(product.getId()));
            product.setPromotions(promotionRepository.getPromotionsByProductId(product.getId(), new Date()));
        }
        return products;
    }

    public List<ProductEntity> getBestProducts() {
        List<Integer> productIds = productRepository.getBestProducts();
        List<ProductEntity> products = new ArrayList<ProductEntity>();

        for (Integer productId : productIds) {
            ProductEntity product = new ProductEntity();
            product = productRepository.getProductById(productId);
            products.add(product);
        }

        for (ProductEntity product : products) {
            product.setImages(imageRepository.getImagesByProductId(product.getId()));
            product.setVotes(voteRepository.getVotesByProductId(product.getId()));
            product.setPromotions(promotionRepository.getPromotionsByProductId(product.getId(), new Date()));
        }
        return products;
    }

    public ProductEntity addProduct(ProductEntity product){
        List<ImageEntity> listImages = product.getImages();
        product.setImages(null);
        ProductEntity newProduct = productRepository.save(product);
        for(ImageEntity image : listImages){
            image.setProduct(newProduct);
            imageRepository.save(image);
        }
        newProduct.setImages(listImages);
        return newProduct;
    }

    public List<ProductEntity> findProducts() {
        return (List<ProductEntity>) productRepository.findAll();
    }
}
