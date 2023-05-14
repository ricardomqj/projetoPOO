import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {

    public enum StatusEncomenda {
        PENDENTE,
        FINALIZADO,
        EXPEDIDO,
    }

    private String codSistema;
    private String codSistemaUtlizador;
    private List<Artigo> artigos;
    private String tamanho;
    private double precoFinal;
    private StatusEncomenda status;
    private LocalDate data;
    private double vintageProfit;

    public Encomenda() {
        this.codSistema = "";
        this.codSistemaUtlizador = null;
        this.artigos = new ArrayList<>();
        this.tamanho = "";
        this.precoFinal = 0.0;
        this.status = null;
        this.data = LocalDate.now();
        this.vintageProfit = 0.0;
    }

    public Encomenda(String codSistema, String codSistemaUtlizador, List<Artigo> artigos,double precoFinal, double vintageProfit) {
        this.codSistema = codSistema;
        this.codSistemaUtlizador = codSistemaUtlizador;
        this.artigos = artigos;
        this.tamanho = dimensaoEncomendaString(artigos);
        this.precoFinal = precoFinal;
        this.status = StatusEncomenda.PENDENTE;
        this.data = LocalDate.now();
        this.vintageProfit = vintageProfit;
    }

    public Encomenda(String codSistema, String codSistemaUtlizador, Map<String, Artigo> artigos) {
        this.status = StatusEncomenda.PENDENTE;
        this.codSistema = codSistema;
        this.codSistemaUtlizador = codSistemaUtlizador;
        this.tamanho = getTamanhoEncomendaString(artigos);
        this.precoFinal = artigos.values().stream().mapToDouble(Artigo::getPrecoTotalArtigo).sum();
        this.data = LocalDate.now();
        this.vintageProfit = artigos.values().stream().mapToDouble(Artigo::getProfitVintage).sum();
    }


    public Encomenda(Encomenda umaEncomenda) {
        this.codSistema = umaEncomenda.getCodSistema();
        this.codSistemaUtlizador = umaEncomenda.getcodSistemaUtlizador();
        this.artigos = umaEncomenda.getArtigos();
        this.tamanho = umaEncomenda.getTamanhoEncomenda();
        this.precoFinal = umaEncomenda.getPrecoFinal();
        this.status = umaEncomenda.getStatus();
        this.data = umaEncomenda.getData();
        this.vintageProfit = umaEncomenda.getVintageProfit();

    }

    // Methods

    public Encomenda clone() {
        return new Encomenda(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Encomenda e = (Encomenda) o;
        return this.codSistemaUtlizador.equals(e.getcodSistemaUtlizador()) && this.artigos.equals(e.getArtigos()) &&
                this.tamanho.equals(e.getTamanhoEncomenda()) && this.precoFinal == e.getPrecoFinal() &&
                this.status.equals(e.getStatus()) && this.data.equals(e.getData()) &&
                this.vintageProfit == e.getVintageProfit();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("O código de Sistema do utlizador é: ").append(this.codSistemaUtlizador).append("\n");
        sb.append("Lista de artigos da encomenda: \n");
        for(Artigo artigo : this.artigos) {
            sb.append(artigo.toString());
        }
        sb.append("Tamanho da Encomenda: ").append(this.tamanho).append("\n");
        sb.append("Preço Total: ").append(this.precoFinal).append("\n");
        sb.append("Estado da encomenda: ").append(this.status).append("\n");
        sb.append("Data da encomenda: ").append(this.data).append("\n");

        return sb.toString();
    }

    public String toStringTxt() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.codSistema).append(":");
        sb.append(this.codSistemaUtlizador).append(":");
        //sb.append("Lista de artigos da encomenda: \);
        for(Artigo artigo : this.artigos) {
            if(artigo instanceof Sapatilha) {
                Sapatilha sapatilha = (Sapatilha) artigo;
                String str = sapatilha.toStringTxt().replace(":",".");
                sb.append(str);
            }
            if(artigo instanceof TShirt) {
                TShirt tshirt = (TShirt) artigo;
                String str = tshirt.toStringTxt().replace(":",".");
                sb.append(str);
            }
            if(artigo instanceof Mala) {
                Mala mala = (Mala) artigo;
                String str = mala.toStringTxt().replace(":",".");
                sb.append(str);
            }

            if (this.artigos.size() > 1) sb.append("/");
        }

        sb.append(this.tamanho).append(":");
        sb.append(this.precoFinal).append(":");
        sb.append(this.status).append(":");
        sb.append(this.data).append("\n");

        return sb.toString();
    }

    // METHODS

    public void insereUmArtigo(Artigo artigo)
    {
        this.artigos.add(artigo);
    }
    /*
    public double taxaDeSatisfacao() {
        if(this.estado.equals(Estado.NOVO)) return 0.5;
        else return 0.25;
    }
    */

    public String dimensaoEncomendaString(List<Artigo> listArtigos) {
        if(listArtigos.size() <= 5) return "PEQUENA";
        if(listArtigos.size() <= 10 ) return "MÉDIA";
        else return "GRANDE";
    }




    /* --------> VAI TER DE SER REPENSADA
    public double custoTotalExpedicao() {
        double count = 0.0;

        for(Artigo artigo : this.artigos.values()) {
            count += artigo.getTransportadora().valorExpedicao(artigo.getTransportadora().getValorBase(),artigo.getTransportadora().getMargemLucro());
        }

        return count;
    }

     */

    public double custoProdutos() {

        double custo = 0.0;

        for (Artigo entry : this.artigos) {
            Artigo value = entry;

            custo += value.getPrecoBase();
        }
        return custo;
    }

    public double custoTotalEncomenda(Map<String, Artigo> lstArtigos) {
        double count = 0.0;

        for(Artigo art : lstArtigos.values()) {
            count += art.getPrecoTotalArtigo();
        }

        return count;
    }

    public double vintageProfit() {
        double lucro = 0.0;

        for (Artigo entry : this.artigos) {
            Artigo value = entry;

            if (value.getEstado().toLowerCase().equals("usado")) {
                lucro += 0.25;
            }
            if (value.getEstado().toLowerCase().equals("novo")) {
                lucro += 0.5;
            }
        }

        return lucro;
    }

    public int DimensaoEncomenda() {
        return this.artigos.size();
    }

    // GETTERS AND SETTERs

    public String getCodSistema() {
        return codSistema;
    }

    public void setCodSistema(String codSistema) {
        this.codSistema = codSistema;
    }

    public String getcodSistemaUtlizador() {
        return this.codSistemaUtlizador;
    }

    public void setcodSistemaUtlizador(String codSistemaUtlizador) {
        this.codSistemaUtlizador = codSistemaUtlizador;
    }

    public List<Artigo> getArtigos() {
        return this.artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public String getTamanhoEncomenda() {

        return this.tamanho;
    }

    public void setTamanhoEncomenda() {
        if (DimensaoEncomenda() == 1) this.tamanho = "Small";
        if (DimensaoEncomenda() <= 5 && DimensaoEncomenda() >= 2) this.tamanho = "Medium";
        if (DimensaoEncomenda() > 5) this.tamanho = "Large";
    }

    public String getTamanhoEncomendaString(Map<String, Artigo> listaArtigos) {
        String ret = null;
        if (listaArtigos.size()==1) ret = "Small";
        if (listaArtigos.size() <= 5 && listaArtigos.size() >= 2) ret = "Medium";
        if (listaArtigos.size() > 5) ret = "Large";
        return ret;
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }
    public void setPrecoFinal() {
        this.precoFinal = custoProdutos() + this.vintageProfit; // REPENSAR A FUNÇÃO TODA custoTotalExpedicao();
    }

    public StatusEncomenda getStatus() {
        return this.status;
    }
    public void setStatus(StatusEncomenda status) {
        this.status = status;
    }

    public LocalDate getData() {
        return this.data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getVintageProfit() {
        return this.vintageProfit;
    }
    public void setVintageProfit() {
        this.vintageProfit = vintageProfit();
    }
}