package orgcuentabancaria.cuentadebanco.services;




import org.springframework.stereotype.Service;

import orgcuentabancaria.cuentadebanco.models.Cuenta;
import orgcuentabancaria.cuentadebanco.models.CuentaAhorros;
import orgcuentabancaria.cuentadebanco.models.CuentaCorriente;

@Service
public class CuentaService {
    public Cuenta crearCuenta(float saldo, float tasaAnual, String tipoCuenta) {
        if (tipoCuenta.equalsIgnoreCase("ahorros")) {
            return new CuentaAhorros(saldo, tasaAnual);
        } else if (tipoCuenta.equalsIgnoreCase("corriente")) {
            return new CuentaCorriente(saldo, tasaAnual);
        }
        return new Cuenta(saldo, tasaAnual);
    }

    public String realizarConsignacion(Cuenta cuenta, float cantidad) {
        cuenta.consignar(cantidad);
        return cuenta.imprimir();
    }

    public String realizarRetiro(Cuenta cuenta, float cantidad) {
        cuenta.retirar(cantidad);
        return cuenta.imprimir();
    }

    public String generarExtracto(Cuenta cuenta) {
        cuenta.extractoMensual();
        return cuenta.imprimir();
    }
}

