package org.efrain.junitapp.ejemplo.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta(new BigDecimal("10005.45"),"Efrain");
        String esperado = "Efrain";
        String real = cuenta.getPersona();
        //Assertions.assertEquals(esperado, real);
        assertEquals(esperado, real);
        assertTrue(real.equals("Efrain"));
    }
}