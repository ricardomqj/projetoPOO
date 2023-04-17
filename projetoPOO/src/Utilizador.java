import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizador {
    private String codigoSistema;
    private String email;
    private String nome;
    private String morada;
    private String nif;
    private Map<String, Artigo> produtosAVenda; // identificado pelo codigo de barras(como key)
    private Map<String, Artigo> produtosAdquiridos;

    public Utilizador() {
        this.codigoSistema = "n/a";
        this.email = "n/a";
        this.nome = "n/a";
        this.morada = "n/a";
        this.nif = "n/a";
        this.produtosAdquiridos = new HashMap<>();
        this.produtosAVenda = new HashMap<>();
    }

    public Utilizador(String codSis, String email, String name, String morada, String nif, Map<String, Artigo> produtosAVendaArg, Map<String, Artigo> produtosAdquiridosArg) {
        this.codigoSistema = codSis;
        this.email = email;
        this.nome = name;
        this.morada = morada;
        this.nif = nif;
        this.produtosAVenda = produtosAVendaArg;
        this.produtosAdquiridos = produtosAdquiridosArg;
    }

    public Utilizador(Utilizador umUtilizador) {
        this.codigoSistema = umUtilizador.getCodigoSistema();
        this.email = umUtilizador.getEmail();
        this.nome = umUtilizador.getNome();
        this.morada = umUtilizador.getMorada();
        this.nif = umUtilizador.getNif();
        this.produtosAVenda = umUtilizador.getProdutosAVenda();
        this.produtosAdquiridos = umUtilizador.getProdutosAdquiridos();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || o.getClass()!=this.getClass()) return false;

        Utilizador user = (Utilizador) o;
        return this.codigoSistema.equals(user.getCodigoSistema()) && this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) && this.morada.equals(user.getMorada()) &&
                this.nif.equals(user.getNif()) && this.produtosAVenda.equals(user.getProdutosAVenda()) &&
                this.produtosAdquiridos.equals(user.getProdutosAdquiridos());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Codigo do sistema atribuído: ").append(this.codigoSistema).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Morada: ").append(this.morada).append("\n");
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Lista de produtos à venda: ").append("\n");
        for(Artigo art : this.produtosAVenda.values()) {
            sb.append(art.toString()).append("\n");
        }
        sb.append("Lista de produtos adquiridos: ").append("\n");
        for(Artigo art : this.produtosAdquiridos.values()) {
            sb.append(art.toString()).append("\n");
        }

        return sb.toString();
    }

    // getters e setters
    public String getCodigoSistema() {
        return this.codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Map<String, Artigo> getProdutosAVenda() {
        return this.produtosAVenda.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setProdutosAVenda(Map<String, Artigo> produtosAVenda) {
        this.produtosAVenda = produtosAVenda.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public Map<String, Artigo> getProdutosAdquiridos() {
        return this.produtosAdquiridos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setProdutosAdquiridos(Map<String, Artigo> produtosAdquiridos) {
        this.produtosAdquiridos = produtosAdquiridos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

}
