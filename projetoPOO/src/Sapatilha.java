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

    public Sapatilha(String codBarras, Transportadora transp, int stock, int numOwners, int avlEstado, double precoBase, double precoAtual, LocalDate dataLancamento, int tamanho, boolean atacadores, String cor, boolean premium) {
        super(codBarras, transp, stock, numOwners, avlEstado, precoBase, precoAtual, dataLancamento);
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
