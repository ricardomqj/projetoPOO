/*
import java.time.LocalDate;

public class MalaUsada extends Mala{
    private int numDonos;
    private int avalEstado;

    public MalaUsada() {
        super();
        this.numDonos = 0;
        this.avalEstado = 0;
    }

    public MalaUsada(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String tamanho, String material, int anoColecao,
                     int numDonos, int avalEstado, int desconto) {
        super(codBarras, stock, dataLancamento, trans, precoBase, tamanho, material, anoColecao, desconto);
        this.numDonos = numDonos;
        this.avalEstado = avalEstado;
    }

    public MalaUsada(MalaUsada umaMala) {
        super(umaMala);
        this.numDonos = umaMala.getNumDonos();
        this.avalEstado = umaMala.getAvalEstado();
    }

    public MalaUsada clone() {
        return new MalaUsada(this);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        MalaUsada m = (MalaUsada) o;
        return super.equals(m) &&
                this.numDonos == m.getNumDonos() && this.avalEstado == m.getAvalEstado();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Número de Donos: ").append(this.numDonos).append("\n");
        sb.append("Estado: ").append(this.avalEstado).append("\n");

        return sb.toString();
    }

    //getters

    public int getNumDonos() {return this.numDonos;}
    public int getAvalEstado() {return this.avalEstado;}

    //setters

    public void setNumDonos(int numDonos) {this.numDonos = numDonos;}
    public void setAvalEstado(int avalEstado) {this.avalEstado = avalEstado;}

}

 */
