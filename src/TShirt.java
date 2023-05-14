import java.time.LocalDate;
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

    public TShirt() {
        this.tam = null;
        this.padrao = null;
    }

    //public TShirt(String codBarras, int stock, LocalDate dataLancamento, String transportadora,double precoBase, Tamanho tam, Padrao pattern) {
    //    super(codBarras, stock, dataLancamento, transportadora, precoBase);
    //    this.tam = tam;
    //    this.padrao = pattern;
    //}

    //TSHIRT NOVA

    public TShirt(String nome, String codBarras, int stock, LocalDate dataLancamento, String transportadora,double precoBase, String marca, String descricao, int desconto, int tam, int pattern) {
        super(codBarras, stock, dataLancamento, transportadora, precoBase, marca, descricao, desconto, nome);
        if(tam == 1)
        {
            this.tam = Tamanho.S;
        }
        if(tam == 2)
        {
            this.tam = Tamanho.M;
        }
        if(tam == 3)
        {
            this.tam = Tamanho.L;
        }
        if(tam == 4)
        {
            this.tam = Tamanho.XL;
        }

        if(pattern == 1)
        {
            this.padrao = Padrao.LISO;
        }
        if(pattern == 2)
        {
            this.padrao = Padrao.RISCAS;
        }
        if(pattern == 3)
        {
            this.padrao = Padrao.PALMEIRAS;
        }
    }

    //TSHIRT USADA

    public TShirt(String nome, String codBarras, int stock, LocalDate dataLancamento, String transportadora, double precoBase, String marca, String descricao, int desconto, int tam, int pattern, int numDonos, int avalEstado) {
        super(codBarras, stock, dataLancamento, transportadora, precoBase, marca, descricao, desconto, nome, numDonos, avalEstado);

        if(tam == 1)
        {
            this.tam = Tamanho.S;
        }
        if(tam == 2)
        {
            this.tam = Tamanho.M;
        }
        if(tam == 3)
        {
            this.tam = Tamanho.L;
        }
        if(tam == 4)
        {
            this.tam = Tamanho.XL;
        }

        if(pattern == 1)
        {
            this.padrao = Padrao.LISO;
        }
        if(pattern == 2)
        {
            this.padrao = Padrao.RISCAS;
        }
        if(pattern == 3)
        {
            this.padrao = Padrao.PALMEIRAS;
        }

    }

    public TShirt(TShirt umaTShirt) {
        super(umaTShirt);
        this.tam = umaTShirt.getTam();
        this.padrao = umaTShirt.getPadrao();
    }

    public TShirt clone() {
        return new TShirt(this);
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass()!=this.getClass()) return false;

        TShirt t = (TShirt) o;
        return super.equals(t) && this.padrao.equals(t.getPadrao()) &&
                this.tam.equals(t.getTam());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Informações da TShirt: \n");
        sb.append(super.toString());
        sb.append("Tamanho da T-Shirt: ").append(this.tam).append("\n");
        sb.append("Padrão da T-Shirt: ").append(this.padrao).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toStringTxt());
        sb.append(this.tam).append(":");
        sb.append(this.padrao).append("\n");

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

}
