package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar(){
        System.out.println("El modelo ha terminado");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente ));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        revisiones.insertar(new Revision(cliente, vehiculo, revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente){
        return clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        return revisiones.buscar(revision);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente,nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, 10);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(cliente)){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(vehiculo)){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }
    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes(){
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()){
            listaClientes.add(new Cliente(cliente));
        }
        return listaClientes;
    }
    public List<Vehiculo> getVehiculos(){
        return new ArrayList<>(vehiculos.get());
    }

    public List<Revision> getRevisiones(){
        ArrayList<Revision> listaRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get()){
            listaRevisiones.add(new Revision(revision));
        }
        return listaRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente){
        ArrayList<Revision> listaRevisionesIgualClientes = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)){
            listaRevisionesIgualClientes.add(new Revision(revision));
        }
        return listaRevisionesIgualClientes;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo){
        ArrayList<Revision> listaRevisionesIgualVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)){
            listaRevisionesIgualVehiculo.add(new Revision(revision));
        }
        return listaRevisionesIgualVehiculo;
    }

}
