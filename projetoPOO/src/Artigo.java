import java.time.LocalDate;

public abstract class Artigo {
    private String codBarras;
    private int stock;
    private LocalDate dataLancamento;
    private String transportadora;
    private double precoBase;
    private double precoAtual;
    private String marca;
    private String descricao;
    private Estado estado;
    private int desconto; // 0 a 100
    private String nome;

    public Artigo() {
        this.codBarras = "n/a";
        this.stock = 0;
        this.dataLancamento = LocalDate.now();
        this.transportadora = "";
        this.precoBase = 0.0;
        this.marca = "n/a";
        this.descricao = "n/a";
        this.desconto = 0;
        this.nome = "";
        this.precoAtual = 0;
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String nome,int numDonos, int avalEstado) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.precoAtual = precoBase;
        this.marca = marca;
        this.descricao = descricao;
        this.desconto = desconto;
        this.nome = nome;
        this.estado = new Usado(numDonos, avalEstado);
    }

    public Artigo(String codBarras, int stock, LocalDate dataLancamento, String trans, double precoBase, String marca, String descricao, int desconto, String nome) {
        this.codBarras = codBarras;
        this.stock = stock;
        this.dataLancamento = dataLancamento;
        this.transportadora = trans;
        this.precoBase = precoBase;
        this.marca = marca;
        this.desconto = desconto;
        this.nome = nome;
        this.descricao = descricao;
        this.estado = new Novo();
        this.precoAtual = precoBase;
    }

    public Artigo(Artigo umArtigo) {
        this.codBarras = umArtigo.getCodBarras();
        this.stock = umArtigo.getStock();
        this.dataLancamento = umArtigo.getDataLancamento();
        this.transportadora = umArtigo.getNomeTransportadora();
        this.precoBase = umArtigo.getPrecoBase();
        this.marca = umArtigo.getMarca();
        this.descricao = umArtigo.getDescricao();
        this.nome = umArtigo.getNome();
        this.precoAtual = umArtigo.getPrecoAtual();
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
                this.descricao.equals(art.getDescricao()) && this.precoAtual == art.getPrecoAtual();

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Código de barras: ").append(this.codBarras).append("\n");
        sb.append("Stock: ").append(this.stock).append("\n");
        sb.append("Data de lançamento: ").append(this.dataLancamento).append("\n");
        sb.append("Preço base: ").append(this.precoBase).append("\n");
        sb.append("Preço atual: ").append(this.precoAtual).append("\n");
        sb.append("Marca: ").append(this.marca).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        if(this.estado!=null) {
            sb.append(this.estado.toString()).append("\n");
        }

        return sb.toString();
    }

    // Métodos

    public void changePrecoComDesconto (int desconto){
        double desc = (double)desconto/100;
        this.desconto = desconto;
        this.precoAtual = this.getPrecoBase() * (1-desc);
    }

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

    public Estado getEstado() {return this.estado;}
    public void setEstado(String estado){
        if (estado.toLowerCase().equals("novo")) new Novo();
        if (estado.toLowerCase().equals("usado")) new Usado();
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
