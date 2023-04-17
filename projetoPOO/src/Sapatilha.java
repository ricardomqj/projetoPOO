import java.time.LocalDate;

public class Sapatilha extends Artigo{
    private int tamanho;
    private boolean temAtacadores;
    private String cor;
    private boolean premium; // se é premium ou não

    public Sapatilha() {
        super();
        this.tamanho = 0;
        this.temAtacadores = false;
        this.cor = "n/a";
        this.premium = false;
    }

    public Sapatilha(String codBarras, Transportadora transp, int stock, int numOwners, int avlEstado, double precoBase, double precoAtual, LocalDate dataLancamento, int discount,int tamanho, boolean atacadores, String cor, boolean premium) {
        super(codBarras, transp, stock, numOwners, avlEstado, precoBase, precoAtual, discount,dataLancamento);
        this.tamanho = tamanho;
        this.temAtacadores = atacadores;
        this.cor = cor;
        this.premium = premium;
    }

    public Sapatilha(Sapatilha umaSapatilha) {
        super(umaSapatilha);
        this.tamanho = umaSapatilha.getTamanho();
        this.temAtacadores = umaSapatilha.isTemAtacadores();
        this.cor = umaSapatilha.getCor();
        this.premium = umaSapatilha.isPremium();
    }

    public Sapatilha clone() {
        return new Sapatilha(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null | o.getClass()!= this.getClass()) return false;

        Sapatilha s = (Sapatilha) o;
        return super.equals(s) && this.tamanho == s.getTamanho() &&
                this.temAtacadores == ((Sapatilha) o).isTemAtacadores() &&
                this.cor.equals(s.getCor()) && this.premium == s.isPremium();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Tamanho da sapatilha: ").append(this.tamanho).append("\n");
        sb.append("Tem atacadores? ").append(this.isTemAtacadores()).append("\n");
        sb.append("Cor da sapatilha: ").append(this.cor).append("\n");
        sb.append("Sapatilha é premium? ").append(this.premium).append("\n");

        return sb.toString();
    }

    // getters e setters
    public int getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isTemAtacadores() {
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

    public boolean isPremium() {
        return this.premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
