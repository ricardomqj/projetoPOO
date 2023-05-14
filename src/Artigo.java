import java.time.LocalDate;

public abstract class Artigo {
    private String codBarras;
    private int stock;
    private LocalDate dataLancamento;
    private String transportadora;
    private double precoBase;
    private String marca;
    private String descricao;
    private String estado;
    private String nome;
    private LocalDate dataComprado; // se nao tiver sido comprado == null
    private int numdonos;
    private int avalestado;

    public Artigo() {
        this.codBarras = "n/a";
        this.stock = 0;
        this.dataLancamento = LocalDate.now();
        this.transportadora = "";
        this.precoBase = 0.0;
        this.marca = "n/a";
        this.descricao = "n/a";
        this.nome = "";
        this.dataComprado = null;
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String nome,int numDonos, int avalEstado) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.marca = marca;
        this.descricao = descricao;
        this.nome = nome;
        this.estado = "Usado";
        this.numdonos = numDonos;
        this.avalestado = avalEstado;
        this.dataComprado = null;
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String nome) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.marca = marca;
        this.nome = nome;
        this.descricao = descricao;
        this.estado = "Novo";
        this.numdonos = 0;
        this.avalestado = 0;
        this.dataComprado = null;
    }

    public Artigo(Artigo umArtigo) {
        this.codBarras = umArtigo.getCodBarras();
        this.stock = umArtigo.getStock();
        this.dataLancamento = umArtigo.getDataLancamento();
        this.transportadora = umArtigo.getNomeTransportadora();
        this.precoBase = umArtigo.getPrecoBase();
        this.marca = umArtigo.getMarca();
        this.descricao = umArtigo.getDescricao();
        this.estado = umArtigo.getEstado();
        this.nome = umArtigo.getNome();
    }

    public abstract Artigo clone();

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Artigo art = (Artigo) o;
        return this.codBarras.equals(art.getCodBarras()) && this.stock==art.getStock() &&
                this.dataLancamento.equals(art.getDataLancamento()) &&
                this.transportadora.equals(art.getNomeTransportadora()) &&
                this.precoBase == art.getPrecoBase() && this.marca.equals(art.getDescricao()) && this.nome.equals(art.getNome()) &&
                this.descricao.equals(art.getDescricao());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código de barras: ").append(this.codBarras).append("\n");
        sb.append("Stock: ").append(this.stock).append("\n");
        sb.append("Data de lançamento: ").append(this.dataLancamento).append("\n");
        sb.append("Preço base: ").append(this.precoBase).append("\n");
        sb.append("Marca: ").append(this.marca).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        sb.append("Estado: ").append(this.estado).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.codBarras).append(":");
        sb.append(this.stock).append(":");
        sb.append(this.dataLancamento).append(":");
        sb.append(this.transportadora).append(":");
        sb.append(this.precoBase).append(":");
        sb.append(this.marca).append(":");
        sb.append(this.descricao).append(":");
        sb.append(this.estado).append(":");
        sb.append(this.numdonos).append(":");
        sb.append(this.avalestado).append(":");
        sb.append(this.nome).append(":");

        return sb.toString();
    }

    // Métodos

    public double getProfitVintage() { // neste caso assumo que a vintage fica com 5% do valor da compra
        return this.precoBase * 0.05;
    }

    public double getPrecoTotalArtigo() {
        return this.precoBase * 1.05;
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

    public String getNomeTransportadora() {
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
    public String getMarca() {
        return this.marca;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getEstado() {return this.estado;}
    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataComprado() {
        return this.dataComprado;
    }
    public void setDataComprado(LocalDate dataComprado) {
        this.dataComprado = dataComprado;
    }

    public int getNumdonos() {
        return this.numdonos;
    }

    public void setNumdonos(int numdonos) {
        this.numdonos = numdonos;
    }

    public int getAvalestado() {
        return this.avalestado;
    }

    public void setAvalestado(int avalestado) {
        this.avalestado = avalestado;
    }
}
