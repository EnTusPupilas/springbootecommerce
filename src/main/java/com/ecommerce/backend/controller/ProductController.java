package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.exception.ProductNotFoundException;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.ProductRepository;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Crear un nuevo producto
    @PostMapping
    public Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
    
    // Obtener todos los productos
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setStock(newProduct.getStock());
                    product.setCategory(newProduct.getCategory());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    // Eliminar un producto
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return "Producto eliminado exitosamente";
    }
    
    // Buscar productos por categoría
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    
    // Buscar productos por nombre (contiene)
    @GetMapping("/search/{name}")
    public List<Product> searchProductsByName(@PathVariable String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Opcional: Paginación y ordenación
    // Puedes implementar métodos que acepten parámetros Pageable si lo deseas
}
