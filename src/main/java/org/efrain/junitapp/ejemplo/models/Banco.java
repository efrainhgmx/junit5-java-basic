package org.efrain.junitapp.ejemplo.models;

import java.math.BigDecimal;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void tranferir(Cuenta origen, Cuenta destino, BigDecimal monto){
        origen.debito(monto);
        destino.credito(monto);
    }

    public void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }
}
