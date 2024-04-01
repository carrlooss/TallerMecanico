package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente)throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono)throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        boolean modificado = false;
        if (telefono != null && !telefono.isBlank()) {
            cliente.setTelefono(telefono);
            modificado = true;
        }
        if (nombre != null && !nombre.isBlank()){
            cliente.setNombre(nombre);
            modificado = true;
        }
        return modificado;
    }

    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        if (coleccionClientes.contains(cliente))
            return cliente;
        return null;
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (buscar(cliente) == null){
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
