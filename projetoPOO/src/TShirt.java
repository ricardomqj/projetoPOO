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

    public TShirt(Tamanho tam, Padrao pattern) {
        this.tam = tam;
        this.padrao = pattern;
    }

    public TShirt(TShirt umaTShirt) {
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
}
