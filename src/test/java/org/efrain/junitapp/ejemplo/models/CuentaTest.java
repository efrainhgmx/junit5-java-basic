package org.efrain.junitapp.ejemplo.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Efrain", new BigDecimal("10005.45"));
        String esperado = "Efrain";
        String real = cuenta.getPersona();
        //Assertions.assertEquals(esperado, real);
        assertEquals(esperado, real);
        assertTrue(real.equals("Efrain"));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Efrain", new BigDecimal("10005.12345"));
        assertEquals(10005.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);

    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta =  new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuentaDos =  new Cuenta("John Doe", new BigDecimal("8900.9997"));

        //assertNotEquals(cuentaDos, cuenta);
        assertEquals(cuentaDos, cuenta);
    }
}