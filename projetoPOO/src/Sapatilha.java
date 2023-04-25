import java.time.LocalDate;

public class Sapatilha extends Artigo{
    private int tamanho;
    private boolean temAtacadores;
    private String cor;
    
    public Sapatilha() {
        super();
        this.tamanho = 0;
        this.temAtacadores = false;
        this.cor = "n/a";
    }

    public Sapatilha(String codBarras, int stock, LocalDate dataLancamento, Transportadora trans, double precoBase, int tamanho, boolean atacadores, String cor) {
        super(codBarras, stock, dataLancamento, trans, precoBase);
        this.tamanho = tamanho;
        this.temAtacadores = atacadores;
        this.cor = cor;
    }

    public Sapatilha(Sapatilha umaSapatilha) {
        super(umaSapatilha);
        this.tamanho = umaSapatilha.getTamanho();
        this.temAtacadores = umaSapatilha.getAtacadores();
        this.cor = umaSapatilha.getCor();
    }

    public Sapatilha clone() {
        return new Sapatilha(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass()!=this.getClass()) return false;

        Sapatilha tilha = (Sapatilha) o;
        return super.equals(tilha) && this.tamanho == tilha.getTamanho() &&
                this.temAtacadores == tilha.getAtacadores() && this.cor.equals(tilha.getCor());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.getCodBarras());
        sb.append("Tamanho: ").append(this.tamanho).append("\n");
        sb.append("Tem atacadores? ").append(this.temAtacadores).append("\n");
        sb.append("Cor: ").append(this.cor).append("\n");

        return sb.toString();
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean getAtacadores() {
        return this.temAtacadores;
    }

    public void setTemAtacadores(boolean temAtacadores) {
        this.temAtacadores = temAtacadores;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}