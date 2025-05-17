package com.ecomarket.ecomarket.service;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Obtener un cliente por ID
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    // Crear o actualizar un cliente
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Eliminar un cliente por ID
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}