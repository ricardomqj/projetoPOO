import jdk.jshell.execution.Util;

import java.util.*;
import java.util.stream.Collectors;

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

    public void addEncomendaUser(Utilizador user, Map<String, Artigo> lst) {
        Utilizador uti = this.listaUtilizadores.get(user.getCodigoSistema());
        Encomenda enc = new Encomenda(user.getCodigoSistema(), user, lst);
        uti.addEncomendaListaEncomendas(enc);
        this.listaUtilizadores.put(uti.getCodigoSistema(), uti.clone());
    }

    public void removeVariosArtigosUser(Utilizador user, Map<String, Artigo> lst) {
        Map<String, Artigo> lstArtigosNova = user.getProdutosAVenda();
        for(Artigo art : lst.values()) {
            lstArtigosNova.remove(art.getCodBarras());
        }
        user.setProdutosAVenda(lstArtigosNova);
        this.listaUtilizadores.put(user.getCodigoSistema(), user.clone());
    }

    public Utilizador getUserByArtigoAVenda(Artigo art) {
        Utilizador ret = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(Artigo article : uti.getProdutosAVenda().values()) {
                if(article.getCodBarras().equals(art.getCodBarras())) {
                    ret = null;
                    break;
                }
            }
        }
        return ret;
    }

    public void criaUtilizador2(Utilizador user) {
        this.listaUtilizadores.put(user.getCodigoSistema(), user.clone());
    }

    public Artigo getArtigoAVendaByUser(Utilizador user, String codBarras) {
        Artigo ret = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(Artigo art : uti.getProdutosAVenda().values()) {
                if(art.getCodBarras().equals(codBarras)) {
                    ret = art;
                    break;
                }
            }
        }
        return ret;
    }

    public void removeArtigoCarrinho(Utilizador user, String codBarras) {
        Utilizador uti = new Utilizador();
        for(Utilizador elem : this.listaUtilizadores.values()) {
            if(elem.getEmail().equals(user.getEmail())) {
                uti = elem;
                break;
            }
        }
        List<Artigo> newCarrinho = uti.getArtigosCarrinho();
        for(Artigo art : newCarrinho) {
            if(art.getCodBarras().equals(codBarras)) {
                newCarrinho.remove(art);
                break;
            }
        }
        uti.setArtigosCarrinho(newCarrinho);
        this.listaUtilizadores.put(uti.getCodigoSistema(), uti.clone());
    }

    public void addArtigoCarrinho(String email, Artigo art) {
        Utilizador ret = new Utilizador();
        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(email)) {
                ret = uti;
                break;
            }
        }
        List<Artigo> carrinho = new ArrayList<>();
        carrinho.addAll(ret.getArtigosCarrinho());
        carrinho.add(art);
        ret.setArtigosCarrinho(carrinho);
        this.listaUtilizadores.put(ret.getCodigoSistema(), ret.clone());
    }

    public void addCarrinhoToEncomendas(String email, Map<String, Encomenda> lstEncomendas) {
        Utilizador ret = new Utilizador();
        for(Utilizador uti : this.listaUtilizadores.values()) {
            if(uti.getEmail().equals(email)) {
                ret = uti;
                break;
            }
        }
        List<Artigo> carrinho = new ArrayList<>();
        carrinho.addAll(ret.getArtigosCarrinho());
        String codEnc = gerarCodigoEncomenda(lstEncomendas);
        Map<String, Artigo> carrinhoMap = carrinho.stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
        Encomenda enc = new Encomenda(codEnc, ret, carrinhoMap);
        ret.addEncomendaListaEncomendas(enc);
        this.listaUtilizadores.put(ret.getCodigoSistema(),ret.clone());
    }

    public String getInfoCarrinho(Utilizador user) {
        StringBuilder sb = new StringBuilder();

        for(Artigo art : user.getArtigosCarrinho()) {
            sb.append(art.toString()).append("\n_____________________________\n");
        }

        return sb.toString();
    }

    private String gerarCodigoEncomenda(Map<String, Encomenda> listaEncomendas) {
        String codigoSistema = UUID.randomUUID().toString();
        while(listaEncomendas.containsKey(codigoSistema)) {
            codigoSistema = UUID.randomUUID().toString();
        }
        return codigoSistema;
    }

    public void setDiscountUser(String codSis, String codBarras, int desconto) {
        Utilizador user = this.listaUtilizadores.get(codSis);
        Map<String, Artigo> lstArtVenda = user.getProdutosAVenda();
        Artigo art = lstArtVenda.get(codBarras);
        double precoAtual = art.getPrecoAtual();
        art.setDesconto(desconto);
        art.setPrecoAtual(precoAtual * (1-(desconto/100)));
        lstArtVenda.put(art.getCodBarras(), art.clone());
        user.setProdutosAVenda(lstArtVenda);
        this.listaUtilizadores.put(user.getCodigoSistema(), user.clone());
    }

    public boolean userTemArtigo(String codBarras) {
        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(Artigo art : uti.getProdutosAVenda().values()) {
                if(art.getCodBarras().equals(codBarras))
                    return true;
            }
        }
        return false;
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
                ret = user.clone();
                break;
            }
        }
        return ret;
    }

}
