package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import javax.naming.OperationNotSupportedException;
import java.io.Console;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo");
        this.controlador = controlador;
    }

    public  void comenzar() throws OperationNotSupportedException {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        }while (opcion != Opcion.SALIR);

    }
    public void terminar() {
        System.out.println("Fin");
    }

    private void ejecutar(Opcion opcion) throws OperationNotSupportedException {
        switch (opcion) {
            case BORRAR_CLIENTE -> borrarCliente();
            case SALIR -> salir();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_REVISION -> borrarRevision();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case BUSCAR_REVISION -> buscarRevision();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case CERRAR_REVISION -> cerrarRevision();
            case LISTAR_CLIENTES -> listarClientes();
            case INSERTAR_CLIENTE -> insertarCliente();
            case LISTAR_VEHICULOS -> listarVehiculo();
            case INSERTAR_REVISION -> insertarRevision();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case LISTAR_REVISIONES -> listarRevision();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case LISTAR_REVISIONES_CLIENTES -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULOS -> listarRevisionesVehiculo();
            case ANADIR_PRECIO_MATERIAL_REVISON -> anadirPrecioMaterial();
        }
    }
    private void borrarCliente() throws OperationNotSupportedException {
        controlador.borrar(Consola.leerCliente());
    }
    private void salir() throws OperationNotSupportedException{
        controlador.terminar();
    }
    private void buscarCliente() throws OperationNotSupportedException{
        controlador.buscar(Consola.leerCliente());
    }
    private void borrarRevision() throws OperationNotSupportedException{
        controlador.borrar(Consola.leerRevision());
    }
    private void borrarVehiculo() throws OperationNotSupportedException{
        controlador.borrar(Consola.leerVehiculo());
    }
    private void buscarRevision() throws OperationNotSupportedException{
        controlador.buscar(Consola.leerRevision());
    }
    private void buscarVehiculo() throws OperationNotSupportedException{
        controlador.buscar(Consola.leerVehiculo());
    }
    private void cerrarRevision() throws OperationNotSupportedException{
        controlador.cerrar(Consola.leerRevision(),Consola.leerFechaCierre());
    }
    private void listarClientes() throws OperationNotSupportedException{
        controlador.getClientes();
    }
    private void insertarCliente() throws OperationNotSupportedException{
        controlador.insertar(Consola.leerCliente());
    }
    private void listarVehiculo() throws OperationNotSupportedException{
        controlador.getVehiculos();
    }
    private void insertarRevision() throws OperationNotSupportedException{
        controlador.insertar(Consola.leerRevision());
    }
    private void insertarVehiculo() throws OperationNotSupportedException{
        controlador.insertar(Consola.leerVehiculo());
    }
    private void listarRevision() throws OperationNotSupportedException{
        controlador.getRevisiones();
    }
    private void  modificarCliente() throws OperationNotSupportedException {
        controlador.modificar(Consola.leerClieneDni(),Consola.leerNuevoNombre(),Consola.leerNuevoTelefono());
    }
    private void anadirHoras() throws OperationNotSupportedException {
        controlador.anadirHoras(Consola.leerRevision(),Consola.leerHoras());
        //** no SE
    }
    private void anadirPrecioMaterial() throws OperationNotSupportedException {
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
    }
    private void listarRevisionesCliente() throws OperationNotSupportedException{
        controlador.getRevisiones(Consola.leerCliente());
    }
    private void listarRevisionesVehiculo() throws OperationNotSupportedException{
        controlador.getRevisiones(Consola.leerVehiculo());
    }
}
