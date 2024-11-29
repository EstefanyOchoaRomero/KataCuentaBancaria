package orgcuentabancaria.cuentadebanco.models;



public class CuentaCorriente extends Cuenta {
    protected float sobregiro = 0;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            super.retirar(cantidad);
        } else {
            sobregiro += (cantidad - saldo);
            saldo = 0;
            numRetiros++;
        }
    }

    @Override
    public void consignar(float cantidad) {
        if (sobregiro > 0) {
            if (cantidad >= sobregiro) {
                cantidad -= sobregiro;
                sobregiro = 0;
            } else {
                sobregiro -= cantidad;
                cantidad = 0;
            }
        }
        super.consignar(cantidad);
    }

    @Override
    public String imprimir() {
        return String.format("%s, Sobregiro: %.2f", super.imprimir(), sobregiro);
    }
}

