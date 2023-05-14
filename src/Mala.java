import java.time.LocalDate;

public class Mala extends Artigo {

    private String tamanho;
    private String material;
    private int anoColecao;

    public Mala() {
        super();
        this.tamanho = "";
        this.material = "";
        this.anoColecao = 0;
    }

    // MALA NOVA

    public Mala(String nome, String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String tamanho, String material, int anoColecao) {
        super(codBarras, stock, dataLancamento, trans, precoBase, marca, descricao, desconto, nome);
        this.tamanho = tamanho;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    // MALA USADA

    public Mala(String nome, String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String tamanho, String material, int anoColecao, int numDonos, int avalEstado) {
        super(codBarras, stock, dataLancamento, trans, precoBase, marca, descricao, desconto, nome, numDonos, avalEstado);
        this.tamanho = tamanho;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    public Mala(Mala umaMala) {
        super(umaMala);
        this.tamanho = umaMala.getTamanho();
        this.material = umaMala.getMaterial();
        this.anoColecao = umaMala.getAnoColecao();
    }

    public Mala clone() {
        return new Mala(this);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Mala m = (Mala) o;
        return super.equals(m) &&
                this.tamanho == m.getTamanho() && this.material == m.getMaterial() &&
                this.anoColecao == m.getAnoColecao();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Informações da Mala: \n");
        sb.append(super.toString());
        sb.append("Tamanho: ").append(this.tamanho).append("\n");
        sb.append("Material: ").append(this.material).append("\n");
        sb.append("Ano da Coleção: ").append(this.anoColecao).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toStringTxt());
        sb.append(this.tamanho).append(":");
        sb.append(this.material).append(":");
        sb.append(this.anoColecao).append("\n");

        return sb.toString();

    }

    //getters

    public String getTamanho() {
        return this.tamanho;
    }
    public String getMaterial() {
        return this.material;
    }
    public int getAnoColecao() {return this.anoColecao;}

    //setters

    public void setTamanho(String tamanho) {this.tamanho = tamanho;}
    public void setMaterial(String material) {this.material = material;}
    public void setAnoColecao(int anoColecao) {this.anoColecao = anoColecao;}

}