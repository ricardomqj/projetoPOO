public class Transportadora {
    // fazer getValorObtido usando apenas valor base e margemLucro
    private String nome;
    private double valorBase; // valor base para preco de expedição
    private double margemLucro; // percentagem margem de lucro para preço de expedição

    public Transportadora() {
        this.nome = "n/a";
        this.valorBase = 0.0;
        this.margemLucro = 0.0;
    }

    public Transportadora(String name, double baseValue, double margemLuc) {
        this.nome = name;
        this.valorBase = baseValue;
        this.margemLucro = margemLuc;
    }

    public Transportadora(Transportadora umaTransportadora) {
        this.nome = umaTransportadora.getNome();
        this.valorBase = umaTransportadora.getValorBase();
        this.margemLucro = umaTransportadora.getMargemLucro();
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Transportadora t = (Transportadora) o;
        return this.nome.equals(t.getNome()) && this.valorBase == t.getValorBase() &&
                this.margemLucro == t.getMargemLucro();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome da transportadora: ").append(this.nome).append("\n");
        sb.append("Valor de expedição base: ").append(this.valorBase).append("€\n");
        sb.append("Margem de lucro da transportadora: ").append(this.margemLucro).append("%\n");
        sb.append("Valor de expedição total: ").append(this.valorExpedicao()).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.nome).append(":");
        sb.append(this.valorBase).append(":");
        sb.append(this.margemLucro).append("\n");

        //sb.append("Valor de expedição total: ").append(this.valorExpedicao(this.valorBase,this.margemLucro)).append("\n");

        return sb.toString();
    }

    public double valorExpedicao() {
        return (this.valorBase * (1+(this.margemLucro/100))*(1+(12/100))*0.9);
    }

    // getters e setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorBase() {
        return this.valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getMargemLucro() {
        return this.margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }
}