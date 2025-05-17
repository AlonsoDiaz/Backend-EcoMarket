package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> existingCliente = clienteService.getClienteById(id);
        if (existingCliente.isPresent()) {
            cliente.setId(id);
            Cliente updatedCliente = clienteService.saveCliente(cliente);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }    
    // Actualizar parcialmente un cliente existente
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> partialUpdateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> existingCliente = clienteService.getClienteById(id);
        if (existingCliente.isPresent()) {
            Cliente updatedCliente = existingCliente.get();
            if (cliente.getNombre() != null) {
                updatedCliente.setNombre(cliente.getNombre());
            }
            if (cliente.getEmail() != null) {
                updatedCliente.setEmail(cliente.getEmail());
            }
            if (cliente.getTelefono() != null) {
                updatedCliente.setTelefono(cliente.getTelefono());
            }
            if (cliente.getDireccion() != null) {
                updatedCliente.setDireccion(cliente.getDireccion());
            }
            clienteService.saveCliente(updatedCliente);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
