import java.time.LocalDate;

public class Mala extends Artigo {

    private String tamanho;
    private String material;
    private int anoColecao;
    private int desconto;

    public Mala() {
        super();
        this.tamanho = "";
        this.material = "";
        this.anoColecao = 0;
        this.desconto = 0;
    }

    // MALA NOVA

    public Mala(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String tamanho, String material, int anoColecao) {
        super(codBarras, stock, dataLancamento, trans, precoBase);
        this.tamanho = tamanho;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    // MALA USADA

    public Mala(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String tamanho, String material, int anoColecao, int numDonos, int avalEstado) {
        super(codBarras, stock, dataLancamento, trans, precoBase, numDonos, avalEstado);
        this.tamanho = tamanho;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    public Mala(Mala umaMala) {
        super(umaMala);
        this.tamanho = umaMala.getTamanho();
        this.material = umaMala.getMaterial();
        this.anoColecao = umaMala.getAnoColecao();
        this.desconto = umaMala.getDesconto();
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
                this.anoColecao == m.getAnoColecao() && this.desconto == m.getDesconto();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Tamanho: ").append(this.tamanho).append("\n");
        sb.append("Material: ").append(this.material).append("\n");
        sb.append("Ano da Coleção: ").append(this.anoColecao).append("\n");
        sb.append("Desconto: ").append(this.desconto).append("\n");

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
    public int getDesconto() {return this.desconto;}

    //setters

    public void setTamanho(String tamanho) {this.tamanho = tamanho;}
    public void setMaterial(String material) {this.material = material;}
    public void setAnoColecao(int anoColecao) {this.anoColecao = anoColecao;}
    public void setDesconto(int desc) {this.desconto = desc;}

    //funçoes

    public double calculaPrecoFinal() {
        double preco = getPrecoBase();
        if (this.desconto > 0) {
            preco = preco - (preco * this.desconto / 100);
        }
        return preco;
    }
}