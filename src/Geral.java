import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Geral {
    private Map<String, Utilizador> listaUsers;
    private Map<String, Artigo> listaArtigos;
    private Map<String, Transportadora> listaTransportadoras; // key -> nome da transportadora

    // Métodos úteis

    private boolean existeUser(Utilizador user) {
        return this.listaUsers.containsKey(user.getCodigoSistema());
    }

    private boolean existeArtigo(Artigo art) {
        return this.listaArtigos.containsKey(art.getCodBarras());
    }

    private boolean existeTransportadora(Transportadora trans) {
        return this.listaTransportadoras.containsKey(trans.getNome());
    }

    public void addUser(Utilizador user) {
        if(!this.existeUser(user)) {
            this.listaUsers.put(user.getCodigoSistema(), user.clone());
        }
    }

    public void addArtigo(Artigo art) {
        if(this.existeArtigo(art)) {
            Artigo article = this.listaArtigos.get(art.getCodBarras());
            article.setStock(article.getStock()+1);
            this.listaArtigos.put(article.getCodBarras(), article.clone());
        } else {
            this.listaArtigos.put(art.getCodBarras(), art.clone());
        }
    }

    public void addTransportadora(Transportadora trans) {
        if(!this.existeTransportadora(trans)) {
            this.listaTransportadoras.put(trans.getNome(), trans.clone());
        }
    }

    public List<Artigo> artigosPorCategoria(String categoria) {
        ArrayList<Artigo> ret = new ArrayList<>();
        for(Artigo art : this.listaArtigos.values()) {
            if(art.getClass().getSimpleName().equals(categoria)) {
                ret.add(art);
            }
        }
        return ret;
    }

    // Construtores e métodos equals, clone e toString;
    public Geral() {
        this.listaUsers = new HashMap<String, Utilizador>();
        this.listaArtigos = new HashMap<String, Artigo>();
        this.listaTransportadoras = new HashMap<String, Transportadora>();
    }

    public Geral(Map<String, Utilizador> listaUsers, Map<String, Artigo> listaArtigos, Map<String, Transportadora> listaTransportadoras) {
        this.listaUsers = listaUsers.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
        this.listaArtigos = listaArtigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
        this.listaTransportadoras = listaTransportadoras.values().stream().collect(Collectors.toMap(Transportadora::getNome, Transportadora::clone));
    }

    public Geral(Geral ger) {
        this.listaUsers = ger.getListaUsers();
        this.listaArtigos = ger.getListaArtigos();
        this.listaTransportadoras = ger.getListaTransportadoras();
    }

    public Geral clone() {
        return new Geral(this);
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;

        Geral ger = (Geral) o;
        return this.listaUsers.equals(ger.getListaUsers()) &&
                this.listaArtigos.equals(ger.getListaArtigos()) &&
                this.listaTransportadoras.equals(ger.getListaTransportadoras());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de Utilizadores: ");
        for(Utilizador user : this.listaUsers.values()) {
            sb.append(user.toString());
        }
        sb.append("Lista de Artigos: ");
        for(Artigo art : this.listaArtigos.values()) {
            sb.append(art.toString());
        }
        sb.append("Lista de Transportadoras: ");
        for(Transportadora trans : this.listaTransportadoras.values()) {
            sb.append(trans.toString());
        }

        return sb.toString();
    }

    // getters e setters
    public Map<String, Utilizador> getListaUsers() {
        return this.listaUsers.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
    }

    public void setListaUsers(Map<String, Utilizador> listaUsers) {
        this.listaUsers = listaUsers.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
    }

    public Map<String, Artigo> getListaArtigos() {
        return this.listaArtigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public void setListaArtigos(Map<String, Artigo> listaArtigos) {
        this.listaArtigos = listaArtigos.values().stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return this.listaTransportadoras.values().stream().collect(Collectors.toMap(Transportadora::getNome, Transportadora::clone));
    }

    public void setListaTransportadoras(Map<String, Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras.values().stream().collect(Collectors.toMap(Transportadora::getNome, Transportadora::clone));
    }
}
