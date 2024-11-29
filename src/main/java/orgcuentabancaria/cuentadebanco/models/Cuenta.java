package orgcuentabancaria.cuentadebanco.models;


public class Cuenta {
    protected float saldo;
    protected int numConsignaciones = 0;
    protected int numRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
    }

    public void consignar(float cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
            this.numConsignaciones++;
        }
    }

    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            this.saldo -= cantidad;
            this.numRetiros++;
        }
    }

    public void calcularInteresMensual() {
        float interesMensual = (this.tasaAnual / 12) / 100 * saldo;
        this.saldo += interesMensual;
    }

    public void extractoMensual() {
        this.saldo -= this.comisionMensual;
        this.calcularInteresMensual();
    }

    public String imprimir() {
        return String.format("Saldo: %.2f, Consignaciones: %d, Retiros: %d, Comisión Mensual: %.2f", 
        this.saldo, this.numConsignaciones, this.numRetiros, this.comisionMensual);
    }

    public float getSaldo() {
        return saldo;
    }

    public float getTasaAnual() {
        return tasaAnual;
    }
}
