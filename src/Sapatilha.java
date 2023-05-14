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

    // SAPATILHA NOVA

    public Sapatilha(String nome, String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, int tamanho, boolean atacadores, String cor) {
        super(codBarras, stock, dataLancamento, trans, precoBase, marca, descricao, desconto, nome);
        this.tamanho = tamanho;
        this.temAtacadores = atacadores;
        this.cor = cor;
    }

    // SAPATILHA USADA

    public Sapatilha(String nome, String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, int tamanho, boolean atacadores, String cor, int numDonos, int avalEstado) {
        super(codBarras, stock, dataLancamento, trans, precoBase, marca, descricao, desconto, nome, numDonos, avalEstado);
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

        sb.append("Informações da Sapatilha: \n");
        sb.append(super.toString());
        sb.append("Tamanho: ").append(this.tamanho).append("\n");
        sb.append("Tem atacadores? ").append(this.temAtacadores).append("\n");
        sb.append("Cor: ").append(this.cor).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toStringTxt());
        sb.append(this.tamanho).append(":");
        sb.append(this.temAtacadores).append(":");
        sb.append(this.cor).append("\n");

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