import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModelUtlizador {
    private Map<String,Utilizador> listaUtilizadores;

    public ModelUtlizador()
    {
        this.listaUtilizadores = new HashMap<String,Utilizador>();
    }
    public void criaUtlizador(String email,String nome,String morada,String nif)
    {
        String codigoSistema = gerarCodigoSistema(this.listaUtilizadores);

        Utilizador utilizador = new Utilizador(codigoSistema,email,nome,morada,nif);

        this.listaUtilizadores.put(utilizador.getCodigoSistema(),utilizador.clone());
    }

    public void registarSapatilhaUser(Utilizador user, Sapatilha sapatilha) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(sapatilha.getCodBarras(),sapatilha);
    }

    public void registarSapatilhaUsadaUser(Utilizador user, Sapatilha sapatilha) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(sapatilha.getCodBarras(),sapatilha);
    }

    public void registarTShirtUser(Utilizador user, TShirt tshirt) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(tshirt.getCodBarras(),tshirt);
    }

    public void registarTShirtUsadaUser (Utilizador user, TShirt tshirt) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(tshirt.getCodBarras(),tshirt);
    }

    public void registarMalaUser(Utilizador user, Mala mala) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(mala.getCodBarras(),mala);
    }

    public void registarMalaUsadaUser (Utilizador user, Mala mala) {
        Map<String, Artigo> produtos = this.listaUtilizadores.get(user.getCodigoSistema()).getProdutosAVenda();
        produtos.put(mala.getCodBarras(),mala);
    }

    public Map<String, Artigo> percorreUsers() {
        Map<String, Artigo> todosProdutos = new HashMap<>();

        for(Map.Entry<String, Utilizador> entry : this.listaUtilizadores.entrySet()) {
            Utilizador utilizador = entry.getValue();
            Map<String, Artigo> produtosAVenda = utilizador.getProdutosAVenda();
            todosProdutos.putAll(produtosAVenda);
        }
        return todosProdutos;
    }

    public Utilizador criaUtlizadorSemNada()
    {
        Utilizador utlizador = new Utilizador();
        return utlizador;
    }
    private String gerarCodigoSistema(Map<String, Utilizador> listaUtilizadores)
    {
        String codigoSistema = UUID.randomUUID().toString();
        while (listaUtilizadores.containsKey(codigoSistema))
        {
            codigoSistema = UUID.randomUUID().toString();
        }
        return codigoSistema;
    }

    public Utilizador loginUtlizador(String email)
    {
        Utilizador utilizador = getUserByEmail(email);
        if(utilizador == null)
        {
            return null;
        }
        else
        {
            return utilizador;
        }
    }
    public Utilizador getUserByEmail(String email) {
        Utilizador ret = null;
        for(Utilizador user : this.listaUtilizadores.values()) {
            if(user.getEmail().equals(email)) {
                ret = user;
                break;
            }
        }
        return ret;
    }

}
