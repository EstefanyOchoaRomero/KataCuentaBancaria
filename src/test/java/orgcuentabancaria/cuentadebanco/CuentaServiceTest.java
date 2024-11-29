package orgcuentabancaria.cuentadebanco;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orgcuentabancaria.cuentadebanco.models.Cuenta;
import orgcuentabancaria.cuentadebanco.models.CuentaAhorros;
import orgcuentabancaria.cuentadebanco.models.CuentaCorriente;
import orgcuentabancaria.cuentadebanco.services.CuentaService;

public class CuentaServiceTest {
    private CuentaService cuentaService;
    private Cuenta cuentaBase;
    private CuentaAhorros cuentaAhorros;
    private CuentaCorriente cuentaCorriente;

    @BeforeEach
    public void setUp() {
        this.cuentaService = new CuentaService();
        this.cuentaBase = new Cuenta(20000, 12);
        this.cuentaAhorros = new CuentaAhorros(15000, 10);
        this.cuentaCorriente = new CuentaCorriente(10000, 15);
    }

    @Test
    public void testConsignarCuentaBase() {
        String resultado = this.cuentaService.realizarConsignacion(this.cuentaBase, 5000);
        assertTrue(resultado.contains("Saldo: 25000"));
    }

    @Test
    public void testRetiroCuentaCorriente() {
        cuentaService.realizarRetiro(this.cuentaCorriente, 15000);
        assertTrue(this.cuentaCorriente.imprimir().contains("Sobregiro: 5000"));
    }

    @Test
    public void testConsignarCuentaAhorrosInactiva() {
        this.cuentaAhorros.retirar(6000);
        String resultado = this.cuentaService.realizarConsignacion(this.cuentaAhorros, 2000);
        assertTrue(resultado.contains("Activa: false"));
    }

    @Test
    public void testGenerarExtracto() {
        String resultado = cuentaService.generarExtracto(this.cuentaBase);
        assertNotNull(resultado);
    }

    @Test
    void testCrearCuentaTipoNoValido() {
    
        Cuenta cuenta = cuentaService.crearCuenta(10000, 0.05f, "invalida");

        
        assertNotNull(cuenta, "La cuenta no debería ser null");
        assertEquals(10000, cuenta.getSaldo(), "El saldo de la cuenta debería ser 10000");
        assertEquals(0.05f, cuenta.getTasaAnual(), "La tasa anual debería ser 0.05");
    }


    @Test
    void testGenerarExtractoCorriente() {
    
        cuentaService.realizarConsignacion(cuentaCorriente, 5000);
        cuentaService.realizarRetiro(cuentaCorriente, 3000);

    
        String extracto = cuentaService.generarExtracto(cuentaCorriente);

        
        assertFalse(extracto.contains("17000"), "El extracto debería mostrar el saldo actualizado de 17000");
    }


}
