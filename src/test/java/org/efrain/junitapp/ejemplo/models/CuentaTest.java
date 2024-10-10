package org.efrain.junitapp.ejemplo.models;

import org.efrain.junitapp.ejemplo.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {
    Cuenta cuenta;

    @BeforeEach
    void initMetodoTescuenta () {
            cuenta = new Cuenta("Efrain", new BigDecimal("1000.00"));
            System.out.println("Iniciando el metodo");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Finalizando!");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando test");
    }

    @Test
    @DisplayName("Probando nombre de la cuenta!")
    void testNombreCuenta() {
        String esperado = "Efrain";
        String real = cuenta.getPersona();
        //Assertions.assertEquals(esperado, real);
        assertEquals(esperado, real, () -> "El nombre en cuenta no es el que se esperaba: Se esperaba " + esperado);
        assertTrue(real.equals("Efrain"), () -> "Nombre esperado debe ser igual al real");
    }

    @Test
    @DisplayName("Prueba saldo de cuenta")
    void testSaldoCuenta() {
        assertEquals(1000.00, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);

    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuentaDos =  new Cuenta("John Doe", new BigDecimal("8900.9997"));

        //assertNotEquals(cuentaDos, cuenta);
        assertEquals(cuentaDos, cuenta);
    }

    @Test
    void testDebitoCuenta() {
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.00", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.00", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1001));
        });
        String real = exception.getMessage();
        String esperado = "Dinero insuficiente";

        assertEquals(esperado, real);
    }

    @Test
    void testTransferirDineroCuentas() {
        Cuenta cuentaOrigen = new Cuenta("John Doe", new BigDecimal("2500.00"));
        Cuenta cuentaDestino = new Cuenta("Andres Doe", new BigDecimal("5000.00"));

        Banco banco = new Banco();
        banco.setNombre("Banko");
        banco.tranferir(cuentaOrigen, cuentaDestino, new BigDecimal(1500));

        assertEquals("6500.00", cuentaDestino.getSaldo().toPlainString());
        assertEquals("1000.00", cuentaOrigen.getSaldo().toPlainString());
    }

    @Test
    @DisplayName("prueba de relacion de cuentas y banco")
    @Disabled
    void testRelacionBancoCuentas() {
        Cuenta cuentaOrigen = new Cuenta("John Doe", new BigDecimal("2500.00"));
        Cuenta cuentaDestino = new Cuenta("Andres Doe", new BigDecimal("5000.00"));

        Banco banco = new Banco();
        banco.addCuenta(cuentaOrigen);
        banco.addCuenta(cuentaDestino);
        banco.setNombre("Banko");
        banco.tranferir(cuentaOrigen, cuentaDestino, new BigDecimal(1500));

        assertAll(() -> {
                    assertEquals("Andres Doe", banco.getCuentas().stream().filter(cuenta -> cuenta.getPersona().equals("Andres Doe"))
                            .findFirst()
                            .get()
                            .getPersona());
                },
                () -> {
                    assertEquals("6500.00", cuentaDestino.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals("1000.00", cuentaOrigen.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals(2, banco.getCuentas().size());

                },
                () -> {
                    assertEquals("Banko", cuentaOrigen.getBanco().getNombre());

                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(cuenta -> cuenta.getPersona().equals("Andres Doe")));
                });
    }
}