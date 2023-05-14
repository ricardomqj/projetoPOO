import java.sql.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Utilizador {
    private String codigoSistema;
    private String email;
    private String nome;
    private String morada;
    private String nif;
    private double profit; // falta acabar - para fazer preciso de produtosVendidos - tendo isso fazer qq coisa para ir buscar o precobase de cada produto vendido
    private List<String> produtosAVendaCodBarras;
    private List<Encomenda> encomendasFeitas;
    private List<Artigo> artigosCarrinho;
    private Map<String, Artigo> produtosVendidos; // falta acabar

    public Utilizador() {
        this.codigoSistema = "n/a";
        this.email = "n/a";
        this.nome = "n/a";
        this.morada = "n/a";
        this.nif = "n/a";
        this.profit = 0.0;
        this.encomendasFeitas = new ArrayList<>();
        this.produtosAVendaCodBarras = new ArrayList<>();
        this.produtosVendidos = new HashMap<>();
        this.artigosCarrinho = new ArrayList<>();
    }

    public Utilizador(String codSis, String email, String name, String morada, String nif, int profit, List<String> produtosAVendaArg, List<Encomenda> encomendasFeitasArg,
                      Map<String, Artigo> produtosVendidosArg, List<Artigo> artsCarrinho) {
        this.codigoSistema = codSis;
        this.email = email;
        this.nome = name;
        this.morada = morada;
        this.nif = nif;
        this.profit = profit;
        this.produtosAVendaCodBarras = produtosAVendaArg;
        this.encomendasFeitas = encomendasFeitasArg;
        this.produtosVendidos = produtosVendidosArg;
        this.artigosCarrinho = artsCarrinho;
    }

    public Utilizador(String codSis, String email, String name, String morada, String nif) {
        this.codigoSistema = codSis;
        this.email = email;
        this.nome = name;
        this.morada = morada;
        this.nif = nif;
        this.profit = 0;
        this.encomendasFeitas = new ArrayList<>();
        this.produtosAVendaCodBarras = new ArrayList<>();
        this.produtosVendidos = new HashMap<>();
        this.artigosCarrinho = new ArrayList<Artigo>();
    }

    public Utilizador(Utilizador umUtilizador) {
        this.codigoSistema = umUtilizador.getCodigoSistema();
        this.email = umUtilizador.getEmail();
        this.nome = umUtilizador.getNome();
        this.morada = umUtilizador.getMorada();
        this.nif = umUtilizador.getNif();
        this.profit = umUtilizador.getProfit();
        this.produtosAVendaCodBarras = umUtilizador.getProdutosAVenda();
        this.encomendasFeitas = umUtilizador.getEncomendasFeitas();
        this.produtosVendidos = umUtilizador.getProdutosVendidos();
        this.artigosCarrinho = umUtilizador.getArtigosCarrinho();
    }

    public Utilizador clone() {return new Utilizador(this);}

    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || o.getClass()!=this.getClass()) return false;

        Utilizador user = (Utilizador) o;
        return this.codigoSistema.equals(user.getCodigoSistema()) && this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) && this.morada.equals(user.getMorada()) &&
                this.nif.equals(user.getNif()) && this.profit == user.getProfit() && this.produtosAVendaCodBarras.equals(user.getProdutosAVenda()) &&
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
        sb.append("Lista de produtos à venda CodBarras: ").append("\n");
        for(String art : this.produtosAVendaCodBarras) {
            sb.append(art).append("\n");
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

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.codigoSistema).append(":");
        sb.append(this.email).append(":");
        sb.append(this.nome).append(":");
        sb.append(this.morada).append(":");
        sb.append(this.nif).append(":");
        //sb.append(this.profit).append("\n");
        //sb.append("Lista de produtos à venda CodBarras: ").append(";");
        int count = 0;
        for(String art : this.produtosAVendaCodBarras) {
            sb.append(art);
            if (++count < this.produtosAVendaCodBarras.size()) {
                sb.append(";");
            }
        }
        sb.append(":");
        //FAZER O toStringTxt da Encomenda
        sb.append("Lista de produtos adquiridos: ").append("\n");
        for(Encomenda art : this.encomendasFeitas) {
            sb.append(art.toString()).append("\n");
        }
        //FAZER O toStringTxt do Artigo
        sb.append("Lista de produtos vendidos: ").append("\n");
        for(Artigo art : this.produtosVendidos.values()) {
            sb.append(art.toString()).append("\n");
        }

        return sb.toString();
    }

    // METHODS

    public double profitTotal;

    public void addArtigoToListaAVendaCodBarras(String codBarras) {
        this.produtosAVendaCodBarras.add(codBarras);
    }
    public void addArtigoCarrinho(Artigo artigo) {
        this.artigosCarrinho.add(artigo);
    }
    public void addEncomendaListaEncomendas(Encomenda enc) {
        this.encomendasFeitas.add(enc);
    }
    public void addArtigoToProdutosVendidos(Artigo artigo) {
        this.produtosVendidos.put(artigo.getCodBarras(), artigo);
    }
    public void removeArtigoAVenda(String codBarras) {
        this.produtosAVendaCodBarras.remove(codBarras);
    }

    public double dinheiroGasto() {
        double ret = 0.0;

        for(Encomenda enc : this.encomendasFeitas) {
            if(enc.getStatus().equals(Encomenda.StatusEncomenda.FINALIZADO)) {
                for(Artigo art : enc.getArtigos()) {
                    ret += art.getPrecoTotalArtigo();
                }
            }
        }

        return ret;
    }

    public void retirarProdutoAVenda(String codBarras) {
        for(String cb : this.produtosAVendaCodBarras) {
            if(cb.equals(codBarras)) {
                this.produtosAVendaCodBarras.remove(cb);
                break;
            }
        }
    }

    public double dinheiroGanho(){
        double ret = 0.0;

        for(Artigo art : this.produtosVendidos.values()) {
            ret += art.getPrecoBase();
        }

        return ret;
    }

    public double dinheiroGanho(LocalDate di, LocalDate df) {
        double ret = 0.0;

        for(Artigo art : this.produtosVendidos.values()) {
            if(art.getDataComprado().isAfter(di) && art.getDataComprado().isBefore(df)) {
                ret += art.getPrecoBase();
            }
        }

        return ret;
    }

    public double dinheiroGasto(LocalDate di, LocalDate df) {
        double ret = 0.0;

        for(Encomenda enc : this.encomendasFeitas) {
            if(enc.getStatus().equals(Encomenda.StatusEncomenda.FINALIZADO) && enc.getData().isAfter(di) && enc.getData().isBefore(df)) {
                for(Artigo art : enc.getArtigos()) {
                    ret += art.getPrecoTotalArtigo();
                }
            }
        }

        return ret;
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

    public List<String> getProdutosAVenda() {
        return this.produtosAVendaCodBarras;
    }
    public void setProdutosAVendaCodBarras(List<String> produtosAVendaCodBarras) {
        this.produtosAVendaCodBarras = produtosAVendaCodBarras;
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

    public List<Artigo> getArtigosCarrinho() {
        return this.artigosCarrinho.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setArtigosCarrinho(List<Artigo> artigosCarrinho) {
        this.artigosCarrinho = artigosCarrinho.stream().map(Artigo::clone).collect(Collectors.toList());
    }
}