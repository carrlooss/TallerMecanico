package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        fechaFin = revision.fechaFin;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
        fechaInicio = revision.fechaInicio;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");

            }
        }


    public int getHoras() {
        return this.horas;
    }

    public void anadirHoras(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas no pueden ser negativas");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return this.precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material no puede ser negativo");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada() {
        return this.fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) {
        if (estaCerrada()) {
            throw new IllegalArgumentException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        float precioTotal = (PRECIO_DIA * getDias()) + (PRECIO_HORA * getHoras());
        float precioMaterial = PRECIO_MATERIAL * getPrecioMaterial();
        return precioTotal + precioMaterial;
    }

    private float getDias() {
        return (estaCerrada()) ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Float.compare(precioMaterial, revision.precioMaterial) == 0 && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(fechaFin, revision.fechaFin) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, fechaFin, horas, precioMaterial, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return "Revision{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horas=" + horas +
                ", precioMaterial=" + precioMaterial +
                ", cliente=" + cliente +
                ", vehiculo=" + vehiculo +
                '}';
    }
}


