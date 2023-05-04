/*
import java.time.LocalDate;

public class SapatilhaUsada extends Sapatilha{
    private int numDonos;
    private int avalEstado;
    private int desconto;

    public SapatilhaUsada() {
        super();
        this.numDonos = 0;
        this.avalEstado = 0;
        this.desconto = 0;
    }

    public SapatilhaUsada(String codBarras, int stock, LocalDate dataLancamento, String transportadora, double precoBase, int tamanho, boolean temAtacadores, String cor, int numDonos, int avalEstado, int desconto) {
        super(codBarras, stock, dataLancamento, transportadora, precoBase, tamanho, temAtacadores, cor);
        this.numDonos = numDonos;
        this.avalEstado = avalEstado;
        this.desconto = desconto;
    }

    public SapatilhaUsada(SapatilhaUsada umaSapUsada) {
        super(umaSapUsada);
        this.numDonos = umaSapUsada.getNumDonos();
        this.avalEstado = umaSapUsada.getAvalEstado();
        this.desconto = umaSapUsada.getDesconto();
    }

    public SapatilhaUsada clone() {
        return new SapatilhaUsada(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        SapatilhaUsada su = (SapatilhaUsada) o;
        return super.equals(su) && this.numDonos==su.getNumDonos() &&
                this.avalEstado==su.getAvalEstado() && this.desconto==su.getDesconto();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Número de donos anteriores: ").append(this.numDonos).append("\n");
        sb.append("Avaliação do estado da sapatilha: ").append(this.avalEstado).append("\n");
        sb.append("Desconto: ").append(this.desconto).append("\n");

        return sb.toString();
    }

    // getters e setters
    public int getNumDonos() {
        return this.numDonos;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }

    public int getAvalEstado() {
        return this.avalEstado;
    }

    public void setAvalEstado(int avalEstado) {
        this.avalEstado = avalEstado;
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
}
*/