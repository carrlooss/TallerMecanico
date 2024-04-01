package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import com.sun.jdi.ObjectCollectedException;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private List<Revision> coleccionRevisiones;

    public Revisiones(){
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get(){
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente){
        ArrayList<Revision> nuevaColeccionRevisiones = new ArrayList<>();
        for (Revision revision : coleccionRevisiones)
        {
            if (revision.getCliente().equals(cliente)){
                nuevaColeccionRevisiones.add(revision);
            }
        }
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Vehiculo vehiculo){
        ArrayList<Revision> nuevaColeccionRevisiones = new ArrayList<>();
        for (Revision revision : coleccionRevisiones)
        {
            if (revision.getVehiculo().equals(vehiculo)){
                nuevaColeccionRevisiones.add(revision);
            }
        }
        return new ArrayList<>(coleccionRevisiones);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Revision revision : coleccionRevisiones){
            if (revision.getCliente().equals(cliente)) {
                if (!revision.estaCerrada()) {
                    throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
                }else{
                    if (revision.getFechaFin().isAfter(fechaRevision)){
                        throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
                    }
                }
            }

            if (revision.getVehiculo().equals(vehiculo)){
                if (!revision.estaCerrada()){
                    throw new OperationNotSupportedException("El vehículo tiene otra revisión en curso.");
                }else {
                    if (revision.getFechaFin().isAfter(fechaRevision)) {
                        throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
                    }
                }
            }
        }
    }

    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        if (revision == null){
            throw new NullPointerException("La revisión no puede ser nula");
        }
            if (!coleccionRevisiones.contains(revision)){
                throw new OperationNotSupportedException("No existe ninguna revisión igual");
            }
            return revision;
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision,"No se puede insertar una revision nula");

        Cliente cliente = revision.getCliente();
        Vehiculo vehiculo = revision.getVehiculo();
        LocalDate fechaRevision = revision.getFechaInicio();

        comprobarRevision(cliente, vehiculo, fechaRevision);
        coleccionRevisiones.add(revision);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        getRevision(revision).anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "El precio del material no puede ser nulo");
        getRevision(revision).anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "La fecha fin no puede sere nula");
        getRevision(revision).cerrar(fechaFin);
    }
    public Revision buscar(Revision revision){
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");
        if (!coleccionRevisiones.contains(revision)){
            return null;
        }
        String dni = revision.getCliente().getDni();
        return revision;
    }
    public void borrar(Revision revision) throws OperationNotSupportedException {
        coleccionRevisiones.remove(getRevision(revision));
    }


}
