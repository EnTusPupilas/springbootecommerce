package com.ecommerce.backend.exception;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Producto no encontrado con el ID: " + id);
    }
}
