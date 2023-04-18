import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {
    // pequena(1 art), medias (2 a 5 art), grandes (>5 arts)
    // get precoFinal, tendo em conta taxaSatisfacaoServico(0,5€ novo e 0,25€ usado) e custos de expedicao
    // get Dimensão (pequena, media ou grande)
    public enum Estado{
        NOVO,
        USADO
    }
    public enum StatusEncomenda {
        PENDENTE,
        FINALIZADO,
        EXPEDIDO
    }
    private Utilizador user;
    private Estado estado;
    private StatusEncomenda status;
    private LocalDate data;
    private Map<String, Artigo> artigos;

    public Encomenda() {
        this.estado = null;
        this.status = null;
        this.data = LocalDate.now();
        this.artigos = new HashMap<>();
    }

    public Encomenda(Utilizador user, Estado novoUsed, StatusEncomenda status, LocalDate data, Map<String, Artigo> artigos) {
        this.user = user.clone();
        this.estado = novoUsed;
        this.status = status;
        this.data = data;
        this.artigos = this.artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public Encomenda(Encomenda umaEncomenda) {
        this.user = umaEncomenda.getUser();
        this.estado = umaEncomenda.getEstado();
        this.status = umaEncomenda.getStatus();
        this.data = umaEncomenda.getData();
        this.artigos = umaEncomenda.getArtigos();
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Encomenda e = (Encomenda) o;
        return this.user.equals(e.getUser()) && this.estado.equals(e.getEstado()) &&
                this.status.equals(e.getStatus()) && this.data.equals(e.getData()) &&
                this.artigos.equals(e.getArtigos());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome do utilizador: ").append(this.user.getNome()).append("\n");
        sb.append("Estado(Novo ou usado): ").append(this.estado).append("\n");
        sb.append("Estado da encomenda: ").append(this.status).append("\n");
        sb.append("Data da encomenda: ").append(this.data).append("\n");
        sb.append("Lista de artigos da encomenda: \n");
        for(Artigo artigo : this.artigos.values()) {
            sb.append(artigo.toString());
        }

        return sb.toString();
    }

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

    public int getDimensaoEncomenda() {
        return this.artigos.size();
    }

    public double custoTotalExpedicao() {
        double count = 0.0;

        for(Artigo artigo : this.artigos.values()) {
            count += artigo.getTransportadora().valorExpedicao(artigo.getTransportadora().getValorBase(),artigo.getTransportadora().getMargemLucro());
        }

        return count;
    }

    // getters e setters
    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public Map<String, Artigo> getArtigos() {
        return this.artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setArtigos(Map<String, Artigo> artigos) {
        this.artigos = artigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public Utilizador getUser() {
        return this.user.clone();
    }

    public void setUser(Utilizador user) {
        this.user = user.clone();
    }
}
