package com.pratap.springcloud.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.springcloud.entities.ProductEntity;
import com.pratap.springcloud.repository.ProductRepository;
import com.pratap.springcloud.service.ProductService;
import com.pratap.springcloud.shared.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public ProductDTO create(ProductDTO productDTO) {

		modelMapper = new ModelMapper();
		ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
		
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		
		return modelMapper.map(savedProductEntity, ProductDTO.class);
	}

	@Override
	public ProductDTO getProductDetails(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found by Id : "+id));
		
		modelMapper = new ModelMapper();
		return modelMapper.map(productEntity, ProductDTO.class);
	}

}
