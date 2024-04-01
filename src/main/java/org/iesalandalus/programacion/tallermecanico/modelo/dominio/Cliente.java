package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$";
    private static final String ER_DNI = "(\\d{8}[A-Z])";
    private static final String ER_TELEFONO = "(\\d{9}$)";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono){
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente){
        Objects.requireNonNull(cliente,"No es posible copiar un cliente nulo.");
        setNombre(cliente.nombre);
        setDni(cliente.dni);
        setTelefono(cliente.telefono);
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)){
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }
    public String getDni(){
        return dni;
    }
    private void setDni(String dni){
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }
    private static boolean comprobarLetraDni(String dni){
        char letra = dni.charAt(dni.length()- 1);
        int dniNumber = Integer.parseInt(dni.substring(0, dni.length() - 1));
        int resto = dniNumber % 23;
        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        char miLetra = letras[resto];
        if (letra != miLetra)
            return false;

        return true;
    }

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public static Cliente get(String dni){
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI))
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        if (!comprobarLetraDni(dni))
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        return new Cliente("", dni, "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
    }
