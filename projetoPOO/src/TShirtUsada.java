import java.time.LocalDate;

public class TShirtUsada extends TShirt {
    private int numDonos;
    private int avalEstado;
    private int desconto;

    public TShirtUsada() {
        super();
        this.numDonos = 0;
        this.avalEstado = 0;
        this.desconto = 0;
    }
    public TShirtUsada(String codBarras, int stock, LocalDate dataLancamento, Transportadora transportadora, Tamanho tamanho, Padrao padrao,double precoBase,int numDonos, int avalEstado, int desconto) {
        super(codBarras, stock, dataLancamento, transportadora, precoBase, tamanho, padrao);
        this.numDonos = numDonos;
        this.avalEstado = avalEstado;
        this.desconto = desconto;
    }
    public TShirtUsada(TShirtUsada shirt)
    {
        super(shirt);
        this.numDonos = shirt.getNumDonos();
        this.avalEstado = shirt.getAvalEstado();
        this.desconto = shirt.getDesconto();
    }

    public TShirtUsada clone() { return new TShirtUsada(this);}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass())   return false;

        TShirtUsada ts = (TShirtUsada) o;
        return  super.equals(ts) && this.numDonos == ts.getNumDonos() &&
                this.avalEstado == ts.getAvalEstado() && this.desconto == ts.getDesconto();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Número de donos anteriores: ").append(this.numDonos).append("\n");
        sb.append("Avaliação do estado da t-Shirt: ").append(this.avalEstado).append("\n");
        sb.append("Desconto: ").append(this.desconto).append("\n");

        return sb.toString();
    }

    public int getNumDonos() {
        return numDonos;
    }
    public void setNumDonos(int numbDonos) {
        this.numDonos = numbDonos;
    }
    public int getAvalEstado() {
        return avalEstado;
    }
    public void setAvalEstado(int avalEstado) {
        this.avalEstado = avalEstado;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
}

