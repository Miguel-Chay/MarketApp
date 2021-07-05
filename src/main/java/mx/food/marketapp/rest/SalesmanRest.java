package mx.food.marketapp.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.food.marketapp.model.SalesmanModel;
import mx.food.marketapp.model.request.RegisterSalesmanRequest;
import mx.food.marketapp.model.request.SalesmanRequest;
import mx.food.marketapp.service.SalesmanService;

@RestController
@RequestMapping("/api")
public class SalesmanRest {
// PUT, GET, GET by ID, DELETE para entidad Salesman
    @Autowired
    private SalesmanService salesmanService;


    @PostMapping("/salesmen/register")
    public ResponseEntity<SalesmanModel> registrarSalesman(@Valid @RequestBody RegisterSalesmanRequest request) {
        SalesmanModel u = salesmanService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping("/salesmen/{id}")
    public ResponseEntity<SalesmanModel> actualizarSalesman(@PathVariable Integer id, @Valid @RequestBody SalesmanRequest request) {
        SalesmanModel u = salesmanService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/salesmen")
    public ResponseEntity<List<SalesmanModel>> obtenerSalesman() {
        List<SalesmanModel> salesmans = salesmanService.getSalesmans();
        return ResponseEntity.ok(salesmans);
    }

    @GetMapping("/salesmen/{id}")
    public ResponseEntity<SalesmanModel> getSalesman(@PathVariable Integer id) {
        SalesmanModel u = salesmanService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    // @DeleteMapping("/salesmen/{id}")
    // public ResponseEntity<Void> deleteSalesman(@PathVariable Integer id) {
    //     salesmanService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Salesman> getLoggedSalesman(){
    //     Salesman salesman = (Salesman) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(salesman); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Salesman> registrarSalesman(@RequestBody SalesmanRequest request) {
    //     Salesman u = salesmanService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}