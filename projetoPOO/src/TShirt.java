import java.util.Objects;

public class TShirt extends Artigo{
    public enum Tamanho{
        S,
        M,
        L,
        XL
    }
    public enum Padrao{
        LISO,
        RISCAS,
        PALMEIRAS
    }
    private Tamanho tam;
    private Padrao padrao;
    private boolean usada;
    private int desconto;

    public TShirt() {
        this.tam = null;
        this.padrao = null;
        this.usada = false;
        this.desconto = 0;
    }

    public TShirt(Tamanho tam, Padrao pattern,boolean usada) {
        this.tam = tam;
        this.padrao = pattern;
        this.usada = usada;
        if(this.usada)
        {
            if(this.padrao.equals("LISO"))
            {
                this.desconto = 0;
            }
            else
            {
                this.desconto = 50;
            }
        }
        else
        {
            this.desconto = 0;
        }
    }

    public TShirt(TShirt umaTShirt) {
        this.tam = umaTShirt.getTam();
        this.padrao = umaTShirt.getPadrao();
        this.usada = umaTShirt.getUsada();
    }

    public TShirt clone() {
        return new TShirt(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TShirt tShirt)) return false;
        if (!super.equals(o)) return false;
        return usada == tShirt.usada && desconto == tShirt.desconto && tam == tShirt.tam && padrao == tShirt.padrao;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Tamanho da T-Shirt: ").append(this.tam).append("\n");
        sb.append("Padrão da T-Shirt: ").append(this.padrao).append("\n");

        return sb.toString();
    }

    // getters e setters
    public Tamanho getTam() {
        return this.tam;
    }

    public void setTam(Tamanho tam) {
        this.tam = tam;
    }

    public Padrao getPadrao() {
        return this.padrao;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    public boolean getUsada()
    {
        return this.usada;
    }

    public void setUsada(boolean usada)
    {
        this.usada = usada;
    }
}
