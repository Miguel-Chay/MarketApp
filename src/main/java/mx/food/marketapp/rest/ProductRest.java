package mx.food.marketapp.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.ProductModel;
import mx.food.marketapp.model.request.ProductRequest;
import mx.food.marketapp.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRest {
// PUT, GET, GET by ID, DELETE para entidad Product
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> registrarProducto(@Valid @RequestBody ProductRequest request) {
        ProductModel u = productService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> actualizarProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest request) {
        ProductModel u = productService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> obtenerProduct() {
        List<ProductModel> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Integer id) {
        ProductModel u = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Product> getLoggedProduct(){
    //     Product product = (Product) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(product); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Product> registrarProduct(@RequestBody ProductRequest request) {
    //     Product u = productService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}