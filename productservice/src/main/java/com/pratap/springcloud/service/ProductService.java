package com.pratap.springcloud.service;

import com.pratap.springcloud.shared.ProductDTO;

public interface ProductService {

	ProductDTO create(ProductDTO productDTO);

	ProductDTO getProductDetails(Long id);
}
