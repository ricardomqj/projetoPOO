import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Utilizador {
    private String codigoSistema;
    private String email;
    private String nome;
    private String morada;
    private String nif;
    private double profit; // falta acabar - para fazer preciso de produtosVendidos - tendo isso fazer qq coisa para ir buscar o precobase de cada produto vendido
    private Map<String, Artigo> produtosAVenda; // identificado pelo codigo de barras(como key)
    private List<Encomenda> encomendasFeitas;
    private Map<String, Artigo> produtosVendidos; // falta acabar

    public Utilizador() {
        this.codigoSistema = "n/a";
        this.email = "n/a";
        this.nome = "n/a";
        this.morada = "n/a";
        this.nif = "n/a";
        this.profit = 0.0;
        this.encomendasFeitas = new ArrayList<>();
        this.produtosAVenda = new HashMap<>();
        this.produtosVendidos = new HashMap<>();
    }

    public Utilizador(String codSis, String email, String name, String morada, String nif, int profit, Map<String, Artigo> produtosAVendaArg, List<Encomenda> encomendasFeitasArg,
                      Map<String, Artigo> produtosVendidosArg) {
        this.codigoSistema = codSis;
        this.email = email;
        this.nome = name;
        this.morada = morada;
        this.nif = nif;
        this.profit = profit;
        this.produtosAVenda = produtosAVendaArg;
        this.encomendasFeitas = encomendasFeitasArg;
        this.produtosVendidos = produtosVendidosArg;
    }

    public Utilizador(String codSis, String email, String name, String morada, String nif) {
        this.codigoSistema = codSis;
        this.email = email;
        this.nome = name;
        this.morada = morada;
        this.nif = nif;
        this.profit = 0;
        this.encomendasFeitas = new ArrayList<>();
        this.produtosAVenda = new HashMap<>();
        this.produtosVendidos = new HashMap<>();
    }

    public Utilizador(Utilizador umUtilizador) {
        this.codigoSistema = umUtilizador.getCodigoSistema();
        this.email = umUtilizador.getEmail();
        this.nome = umUtilizador.getNome();
        this.morada = umUtilizador.getMorada();
        this.nif = umUtilizador.getNif();
        this.profit = umUtilizador.getProfit();
        this.produtosAVenda = umUtilizador.getProdutosAVenda();
        this.encomendasFeitas = umUtilizador.getEncomendasFeitas();
        this.produtosVendidos = umUtilizador.getProdutosVendidos();
    }

    public Utilizador clone() {return new Utilizador(this);}

    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || o.getClass()!=this.getClass()) return false;

        Utilizador user = (Utilizador) o;
        return this.codigoSistema.equals(user.getCodigoSistema()) && this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) && this.morada.equals(user.getMorada()) &&
                this.nif.equals(user.getNif()) && this.profit == user.getProfit() && this.produtosAVenda.equals(user.getProdutosAVenda()) &&
                this.encomendasFeitas.equals(user.getEncomendasFeitas()) && this.produtosVendidos.equals(user.getProdutosVendidos());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Codigo do sistema atribuído: ").append(this.codigoSistema).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Morada: ").append(this.morada).append("\n");
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Profit: ").append(this.profit).append("\n");
        sb.append("Lista de produtos à venda: ").append("\n");
        for(Artigo art : this.produtosAVenda.values()) {
            sb.append(art.toString()).append("\n");
        }
        sb.append("Lista de produtos adquiridos: ").append("\n");
        for(Encomenda art : this.encomendasFeitas) {
            sb.append(art.toString()).append("\n");
        }
        sb.append("Lista de produtos vendidos: ").append("\n");
        for(Artigo art : this.produtosVendidos.values()) {
            sb.append(art.toString()).append("\n");
        }

        return sb.toString();
    }

    // METHODS

    public double profitTotal;

    public void addArtigoToListaAVenda(Artigo artigo) {
        this.produtosAVenda.put(artigo.getCodBarras(), artigo.clone());
    }

    public void addEncomendaListaEncomendas(Encomenda enc) {
        this.encomendasFeitas.add(enc);
    }

    public void removeArtigoAVenda(Artigo art) {
        this.produtosAVenda.remove(art.getCodBarras());
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

    public double getProfit() {
        return this.profit;
    }
    public void setProfit() {
        double localprofit = 0.0;

        for (Map.Entry<String, Artigo> entry : this.produtosVendidos.entrySet()) {
            String key = entry.getKey();
            Artigo value = entry.getValue();

            localprofit += value.getPrecoBase();
        }
    }

    public Map<String, Artigo> getProdutosAVenda() {
        return this.produtosAVenda.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setProdutosAVenda(Map<String, Artigo> produtosAVenda) {
        this.produtosAVenda = produtosAVenda.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public List<Encomenda> getEncomendasFeitas() {
        return this.encomendasFeitas.stream().map(Encomenda :: clone).collect(Collectors.toList());
    }

    public void setEncomendasFeitas(List<Encomenda> encomendasFeitas) {
        this.encomendasFeitas = encomendasFeitas.stream().map(Encomenda :: clone).collect(Collectors.toList());
    }

    public Map<String, Artigo> getProdutosVendidos() {
        return this.produtosVendidos.values().stream().collect(Collectors.toMap(Artigo :: getCodBarras, Artigo :: clone));
    }

    public void setProdutosVendidos(Map<String, Artigo> produtosVendidos) {
        this.produtosVendidos = produtosVendidos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }
}