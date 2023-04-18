import java.time.LocalDate;

public abstract class Artigo {
    // public abstract clone()???
    private static final int IMPOSTO = 12;
    private String codBarras;
    private Transportadora transportadora;
    private int stock; // numero de itens deste artigo
    private int numDonos; // se for novo -> 0
    private int avalEstado; // 1 a 5, sendo 1 novo
    private double precoBase;
    private double precoAtual;
    private int desconto; // 0 a 100 -> 0 sem desconto
    private LocalDate dataLancamento;

    public Artigo() {
        this.codBarras = "n/a";
        this.transportadora = new Transportadora();
        this.stock = 0;
        this.numDonos = 0;
        this.avalEstado = 1;
        this.precoBase = 0.0;
        this.precoAtual = 0.0;
        this.desconto = 0;
        this.dataLancamento = LocalDate.now();
    }

    public Artigo(String codBarras, Transportadora transp, int stock, int numOwners, int avlEstado, double precoBase, double precoAtual, int desconto,LocalDate dataLancamento) {
        this.codBarras = codBarras;
        this.transportadora = transp;
        this.stock = stock;
        this.numDonos = numOwners;
        this.avalEstado = avlEstado;
        this.precoBase = precoBase;
        this.precoAtual = precoAtual;
        this.desconto = desconto;
        this.dataLancamento = dataLancamento;
    }

    public Artigo(Artigo umArtigo) {
        this.codBarras = umArtigo.getCodBarras();
        this.transportadora = umArtigo.getTransportadora();
        this.stock = umArtigo.getStock();
        this.numDonos = umArtigo.getNumDonos();
        this.avalEstado = umArtigo.getAvalEstado();
        this.precoBase = umArtigo.getPrecoBase();
        this.precoAtual = umArtigo.getPrecoAtual();
        this.desconto = umArtigo.getDesconto();
        this.dataLancamento = umArtigo.getDataLancamento();
    }

    public abstract Artigo clone();

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Artigo a = (Artigo) o;
        return this.codBarras.equals(a.getCodBarras()) && this.transportadora.equals(a.getTransportadora()) &&
                this.stock == a.getStock() && this.numDonos == a.getNumDonos() &&
                this.avalEstado == a.getAvalEstado() && this.precoBase == a.getPrecoBase() &&
                this.precoAtual == a.getPrecoAtual() && this.dataLancamento.equals(a.getDataLancamento());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código de barras: ").append(this.codBarras).append("\n");
        sb.append("Nome da Transportadora: ").append(this.transportadora).append("\n"); // alterar para this.transportadora.getNome()
        sb.append("Stock: ").append(this.stock).append("\n");
        sb.append("Número de donos: ").append(this.numDonos).append("\n");
        sb.append("Avaliação do estado(1 a 5): ").append(this.avalEstado).append("\n");
        sb.append("Preço base: ").append(this.precoBase).append("€\n");
        sb.append("Desconto(%): ").append(this.desconto).append("%\n");
        sb.append("Preço atual: ").append(this.precoAtual).append("€\n");
        sb.append("Data de lançamento: ").append(this.dataLancamento).append("\n");

        return sb.toString();
    }

    // getters e setters
    public String getCodBarras() {
        return this.codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Transportadora getTransportadora() {
        return this.transportadora.clone();
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora.clone();
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getNumDonos() {
        return this.numDonos;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }

    public int getAvalEstado() {
        return this.avalEstado;
    }

    public void setAvalEstado(int avalEstado) {
        this.avalEstado = avalEstado;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getPrecoAtual() {
        return this.precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getImposto() {
        return IMPOSTO;
    }
}
