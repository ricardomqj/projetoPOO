import java.time.LocalDate;

public class Mala extends Artigo{
    private double largura;
    private double altura;
    private double profundidade;
    private boolean isPremium;
    public Mala() {
        super();
        this.largura = 0.0;
        this.altura = 0.0;
        this.profundidade = 0.0;
        this.isPremium = false;
    }

    public Mala(String codBarras, Transportadora transp, int stock, int numOwners, int avlEstado, double precoBase, double precoAtual, int desconto, LocalDate dataLancamento, double largura, double altura, double profundidade, boolean premium) {
        super(codBarras, transp, stock, numOwners, avlEstado, precoBase, precoAtual, desconto, dataLancamento);
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.isPremium = premium;
    }

    public Mala(Mala umaMala) {
        super(umaMala);
        this.largura = umaMala.getLargura();
        this.altura = umaMala.getAltura();
        this.profundidade = umaMala.getProfundidade();
        this.isPremium = umaMala.isPremium();
    }

    public Mala clone() {
        return new Mala(this);
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass()!=this.getClass()) return false;

        Mala m = (Mala) o;
        return super.equals(m) && this.altura == m.getAltura() &&
                this.largura == m.getLargura() && this.profundidade == m.getProfundidade() &&
                this.isPremium == m.isPremium();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Largura: ").append(this.largura).append("\n");
        sb.append("Altura: ").append(this.altura).append("\n");
        sb.append("Profundidade: ").append(this.profundidade).append("\n");
        sb.append("A mala é premium? ").append(this.isPremium).append("\n");

        return sb.toString();
    }

    // getters e setters
    public double getLargura() {
        return this.largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getProfundidade() {
        return this.profundidade;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    public boolean isPremium() {
        return this.isPremium;
    }

    public void setPremium(boolean premium) {
        this.isPremium = premium;
    }
}
