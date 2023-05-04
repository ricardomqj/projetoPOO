import jdk.jshell.execution.Util;

import java.util.*;

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
        Utilizador utiRet = null;
        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(sapatilha);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public void registarSapatilhaUsadaUser(Utilizador user, Sapatilha sapatilha) {
        Utilizador utiRet = null;
        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(sapatilha);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public void registarTShirtUser(Utilizador user, TShirt tshirt) {
        Utilizador utiRet = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(tshirt);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public void registarTShirtUsadaUser (Utilizador user, TShirt tshirt) {
        Utilizador utiRet = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(tshirt);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public void registarMalaUser(Utilizador user, Mala mala) {
        Utilizador utiRet = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(mala);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public void registarMalaUsadaUser (Utilizador user, Mala mala) {
        Utilizador utiRet = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(user.getEmail())) {
                utiRet = uti;
                break;
            }
        }
        utiRet.addArtigoToListaAVenda(mala);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
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

    public Utilizador getObjectUserByEmail(String email) {
        Utilizador uti = null;

        for(Utilizador user : this.listaUtilizadores.values()) {
            if(user.getEmail().equals(email)) {
                uti = user;
            }
        }
        return uti;
    }

    public String infoUserByEmail(String email) {
        String ret = null;

        for(Utilizador user : this.listaUtilizadores.values()) {
            if(user.getEmail().equals(email)) {
                ret = user.toString();
                break;
            }
        }
        return ret;
    }

    public String infoTodosArtigosAVenda() {
        StringBuilder sb = new StringBuilder();

        for (Utilizador user : this.listaUtilizadores.values()) {
            for(Artigo article : user.getProdutosAVenda().values()) {
                sb.append(article.toString()).append(article.getClass().getSimpleName()).append("\n____________________\n");
            }
        }

        return sb.toString();
    }


    public String toStringArtigosVendaUser(String email) {
        StringBuilder sb = new StringBuilder();
        Utilizador uti = getObjectUserByEmail(email);
        for(Artigo art : uti.getProdutosAVenda().values()) {
            sb.append(art.toString());
            sb.append("\n________________________\n");
        }

        return sb.toString();
    }

    public String infoTodosUsers() {
        StringBuilder sb = new StringBuilder();

        for(Utilizador user : this.listaUtilizadores.values()) {
            sb.append(user.toString()).append("\n___________________________\n");
        }

        return sb.toString();
    }

    public String toStringArtigoAVendaByType(String type) {
        StringBuilder sb = new StringBuilder();

        for(Utilizador user : this.listaUtilizadores.values()) {
            for(Artigo art : user.getProdutosAVenda().values()) {
                if(art.getClass().getSimpleName().equals(type)) {
                    sb.append(art.toString());
                    sb.append("\n_________________\n");
                }
            }
        }

        return sb.toString();
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
