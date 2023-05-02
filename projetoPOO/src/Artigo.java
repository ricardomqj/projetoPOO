import java.time.LocalDate;

public abstract class Artigo {
    private String codBarras;
    private int stock;
    private LocalDate dataLancamento;
    private String transportadora;
    private double precoBase;
    private String marca;
    private String descricao;
    private Estado estado;

    public Artigo() {
        this.codBarras = "n/a";
        this.stock = 0;
        this.dataLancamento = LocalDate.now();
        this.transportadora = "n/a";
        this.marca = "n/a";
        this.descricao = "n/a";
        this.precoBase = 0.0;
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int numDonos, int avalEstado) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.marca = marca;
        this.descricao = marca;
        this.estado = new Usado();
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.marca = marca;
        this.descricao = marca;
        this.estado = new Novo();
    }

    public Artigo(Artigo umArtigo) {
        this.codBarras = umArtigo.getCodBarras();
        this.stock = umArtigo.getStock();
        this.dataLancamento = umArtigo.getDataLancamento();
        this.transportadora = umArtigo.getTransportadora();
        this.precoBase = umArtigo.getPrecoBase();
        this.marca = umArtigo.getMarca();
        this.descricao = umArtigo.getDescricao();
    }

    public abstract Artigo clone();

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Artigo art = (Artigo) o;
        return this.codBarras.equals(art.getCodBarras()) && this.stock==art.getStock() &&
                this.dataLancamento.equals(art.getDataLancamento()) &&
                this.transportadora.equals(art.getTransportadora()) &&
                this.precoBase == art.getPrecoBase();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código de barras: ").append(this.codBarras).append("\n");
        sb.append("Stock: ").append(this.stock).append("\n");
        sb.append("Data de lançamento: ").append(this.dataLancamento).append("\n");
        sb.append("Preço base: ").append(this.precoBase).append("\n");

        return sb.toString();
    }

    // getters e setters
    public String getCodBarras() {
        return this.codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTransportadora() {
        return this.transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
