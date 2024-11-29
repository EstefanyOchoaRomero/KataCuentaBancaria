package orgcuentabancaria.cuentadebanco.models;



public class CuentaAhorros extends Cuenta {
    protected boolean activa;

    public CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        this.activa = saldo >= 10000;
    }

    @Override
    public void consignar(float cantidad) {
        if (activa) {
            super.consignar(cantidad);
        }
        actualizarEstado();
    }

    @Override
    public void retirar(float cantidad) {
        if (activa) {
            super.retirar(cantidad);
        }
        actualizarEstado();
    }

    @Override
    public void extractoMensual() {
        if (numRetiros > 4) {
            comisionMensual += (numRetiros - 4) * 1000;
        }
        super.extractoMensual();
        actualizarEstado();
    }

    private void actualizarEstado() {
        activa = saldo >= 10000;
    }

    @Override
    public String imprimir() {
        return String.format("%s, Activa: %b", super.imprimir(), activa);
    }
}
