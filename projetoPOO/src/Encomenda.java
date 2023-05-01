import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {
    public enum StatusEncomenda {
        PENDENTE,
        FINALIZADO,
        EXPEDIDO,
    }

    private Utilizador user;
    private Map<String, Artigo> artigos;
    private String tamanho;
    private double precoFinal;
    private StatusEncomenda status;
    private LocalDate data;

    public Encomenda() {
        this.user = null;
        this.artigos = new HashMap<>();
        this.tamanho = "";
        this.precoFinal = 0.0;
        this.status = null;
        this.data = LocalDate.now();
    }

    public Encomenda(Utilizador user, Map<String, Artigo> artigos, String tamanho, double precoFinal, StatusEncomenda status, LocalDate data) {
        this.user = user.clone();
        this.artigos = this.artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
        this.tamanho = tamanho;
        this.precoFinal = precoFinal;
        this.status = status;
        this.data = data;
    }

    public Encomenda(Encomenda umaEncomenda) {
        this.user = umaEncomenda.getUser();
        this.artigos = umaEncomenda.getArtigos();
        this.tamanho = umaEncomenda.getTamanhoEncomenda();
        this.precoFinal = umaEncomenda.getPrecoFinal();
        this.status = umaEncomenda.getStatus();
        this.data = umaEncomenda.getData();

    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Encomenda e = (Encomenda) o;
        return this.user.equals(e.getUser()) && this.artigos.equals(e.getArtigos()) &&
                this.tamanho.equals(e.getTamanhoEncomenda()) && this.precoFinal == e.getPrecoFinal() &&
                this.status.equals(e.getStatus()) && this.data.equals(e.getData());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome do utilizador: ").append(this.user.getNome()).append("\n");
        sb.append("Lista de artigos da encomenda: \n");
        for(Artigo artigo : this.artigos.values()) {
            sb.append(artigo.toString());
        }
        sb.append("Tamanho da Encomenda: ").append(this.tamanho).append("\n");
        sb.append("Preço Total: ").append(this.precoFinal).append("\n");
        sb.append("Estado da encomenda: ").append(this.status).append("\n");
        sb.append("Data da encomenda: ").append(this.data).append("\n");

        return sb.toString();
    }

    // METHODS

    /*
    public double taxaDeSatisfacao() {
        if(this.estado.equals(Estado.NOVO)) return 0.5;
        else return 0.25;
    }
    public String dimensaoEncomendaString() {
        if(this.artigos.size() < 1) return "SEM ARTIGOS";
        if(this.artigos.size() == 1) return "PEQUENA";
        if(this.artigos.size() >= 2 && this.artigos.size() <= 5) return "MÉDIA";
        else return "GRANDE";
    }
    */

    /* --------> VAI TER DE SER REPENSADA
    public double custoTotalExpedicao() {
        double count = 0.0;

        for(Artigo artigo : this.artigos.values()) {
            count += artigo.getTransportadora().valorExpedicao(artigo.getTransportadora().getValorBase(),artigo.getTransportadora().getMargemLucro());
        }

        return count;
    }

     */

    public double custoTotal() {
        double custo = 0.0;

        for (Map.Entry<String, Artigo> entry : this.artigos.entrySet()) {
            String key = entry.getKey();
            Artigo value = entry.getValue();
            String className = value.getClass().getSimpleName();

            custo += value.getPrecoBase();

            if (className.equals("MalaUsada") || className.equals("SapatilhaUsada") || className.equals("TshirtUsada")) {
                custo += 0.25;
            }
            if (className.equals("Mala") || className.equals("Sapatilha") || className.equals("Tshirt")) {
                custo += 0.5;
            }
        }
        return custo;
    }

    public int DimensaoEncomenda() {
        return this.artigos.size();
    }

    // GETTERS AND SETTERs

    public Utilizador getUser() {
        return this.user.clone();
    }

    public void setUser(Utilizador user) {
        this.user = user.clone();
    }

    public Map<String, Artigo> getArtigos() {
        return this.artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setArtigos(Map<String, Artigo> artigos) {
        this.artigos = artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public String getTamanhoEncomenda() {

        return this.tamanho;
    }
    public void setTamanhoEncomenda() {
        if (DimensaoEncomenda() == 1) this.tamanho = "Small";
        if (DimensaoEncomenda() <= 5 && DimensaoEncomenda() >= 2) this.tamanho = "Medium";
        if (DimensaoEncomenda() > 5) this.tamanho = "Large";
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }
    public void setPrecoFinal() {
        this.precoFinal = custoTotal() + 0; // REPENSAR A FUNÇÃO TODA custoTotalExpedicao();
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
}
