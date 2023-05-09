import jdk.jshell.execution.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
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

    public void loadUtilizadores() {
        /*
         - VER O QUE SE PASSA COM O PATH DO FICHEIRO QUE TA MAL

         Em principio se fizermos isto, deve funcionar e podemos aplicar o mesmo processo para os restantes artigos

        */

        String filePath = System.getProperty("user.dir") + "data/users.txt"; // VER ISTO DEPOIS
        File file = new File(filePath);

        try {
            // Cria um objeto Scanner para ler as informações do arquivo
            Scanner scanner = new Scanner(file);

            // Loop para ler todas as linhas do arquivo
            while (scanner.hasNextLine()) {
                // Lê a próxima linha do arquivo
                String line = scanner.nextLine();

                // Divide a linha em campos usando o separador ":"
                String[] fields = line.split(":");

                // Cria um novo objeto Utilizador e preenche suas informações
                Utilizador utilizador = new Utilizador();
                utilizador.setCodigoSistema(fields[0]);
                utilizador.setEmail(fields[1]);
                utilizador.setNome(fields[2]);
                utilizador.setMorada(fields[3]);
                utilizador.setNif(fields[4]);
                utilizador.setProfit();

                // ------------------------- PRODUTOS A VENDA -------------------------// done
                // ------------------------- PRODUTOS A VENDA -------------------------// done

                // Preenche a lista de produtos vendidos
                List<String> produtosAVenda = new ArrayList<>();
                String[] produtosAVendaArray = fields[6].split(";");
                for (String codigoBarras : produtosAVendaArray) {
                    produtosAVenda.add(codigoBarras);
                }
                utilizador.setProdutosAVendaCodBarras(produtosAVenda);

                //<--------------------------------------------------------------------------->
                //<--------------------------------------------------------------------------->

                // ------------------------- ENCOMENDAS FEITAS -------------------------//
                // ------------------------- ENCOMENDAS FEITAS -------------------------//

                List<String> encomendasFeitas = new ArrayList<>();
                String[] encFeitas = fields[7].split(";");

                for (String enc : encFeitas) {

                    String[] encfields = enc.split(",");

                    Encomenda encomenda = new Encomenda();

                    encomenda.setCodSistema(encfields[0]);
                    encomenda.setUser(utilizador); // PROBLEMA USER

                    String[] encprods = encfields[2].split(".");

                    for (String prod : encprods) {

                        String[] prodencfields = prod.split("/");

                        if (prodencfields[10].equals("sapatilha")) {
                            Sapatilha tilha = new Sapatilha();

                            tilha.setCodBarras(prodencfields[0]);
                            tilha.setStock(Integer.parseInt(prodencfields[1]));
                            tilha.setDataLancamento(LocalDate.parse(prodencfields[2]));
                            tilha.setTransportadora(prodencfields[3]);
                            tilha.setPrecoBase(Double.parseDouble(prodencfields[4]));
                            tilha.setMarca(prodencfields[5]);
                            tilha.setDescricao(prodencfields[6]);
                            tilha.setEstado(prodencfields[7]);
                            tilha.setDesconto(Integer.parseInt(prodencfields[8]));
                            tilha.setNome(prodencfields[9]);
                            tilha.setTamanho(Integer.parseInt(prodencfields[10]));
                            tilha.setTemAtacadores(Boolean.parseBoolean(prodencfields[11]));
                            tilha.setCor(prodencfields[12]);

                            if (prodencfields.length > 13) {
                                //tilha.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                                //tilha.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            }

                            encomenda.insereUmArtigo(tilha);
                        }

                        if (prodencfields[10].equals("mala")) {
                            Mala mala = new Mala();

                            mala.setCodBarras(prodencfields[0]);
                            mala.setStock(Integer.parseInt(prodencfields[1]));
                            mala.setDataLancamento(LocalDate.parse(prodencfields[2]));
                            mala.setTransportadora(prodencfields[3]);
                            mala.setPrecoBase(Double.parseDouble(prodencfields[4]));
                            mala.setMarca(prodencfields[5]);
                            mala.setDescricao(prodencfields[6]);
                            mala.setEstado(prodencfields[7]);
                            mala.setDesconto(Integer.parseInt(prodencfields[8]));
                            mala.setNome(prodencfields[9]);
                            mala.setTamanho(prodencfields[10]);
                            mala.setMaterial(prodencfields[11]);
                            mala.setAnoColecao(Integer.parseInt(prodencfields[12]));

                            if (prodencfields.length > 13) {
                                //mala.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                                //mala.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            }

                            encomenda.insereUmArtigo(mala);
                        }
                        if (prodencfields[10].equals("tshirt")) {
                            TShirt tshirt = new TShirt();

                            tshirt.setCodBarras(prodencfields[0]);
                            tshirt.setStock(Integer.parseInt(prodencfields[1]));
                            tshirt.setDataLancamento(LocalDate.parse(prodencfields[2]));
                            tshirt.setTransportadora(prodencfields[3]);
                            tshirt.setPrecoBase(Double.parseDouble(prodencfields[4]));
                            tshirt.setMarca(prodencfields[5]);
                            tshirt.setDescricao(prodencfields[6]);
                            tshirt.setEstado(prodencfields[7]);
                            tshirt.setDesconto(Integer.parseInt(prodencfields[8]));
                            tshirt.setNome(prodencfields[9]);
                            tshirt.setTam(TShirt.Tamanho.valueOf(prodencfields[10]));
                            tshirt.setPadrao(TShirt.Padrao.valueOf(prodencfields[11]));

                            if (prodencfields.length > 12) {
                                //tshirt.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                                //tshirt.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            }

                            encomenda.insereUmArtigo(tshirt);
                        }
                    }

                    encomenda.setTamanhoEncomenda();
                    encomenda.setPrecoFinal();
                    encomenda.setStatus(Encomenda.StatusEncomenda.valueOf(encfields[5])); // NAO SEI
                    encomenda.setData(LocalDate.parse(encfields[6]));
                    encomenda.setVintageProfit();

                }

                //<--------------------------------------------------------------------------->//
                //<--------------------------------------------------------------------------->//

                // ------------------------- ARTIGOS CARRINHO -------------------------//
                // ------------------------- ARTIGOS CARRINHO -------------------------//

                // Preenche a lista de ARTIGOS CARRINHO
                List<Artigo> artigosCarrinho = new ArrayList<>();
                String[] artigosCarrinhoArray = fields[8].split(";");

                for (String prod : artigosCarrinhoArray) {

                    String[] prodfields = prod.split(",");

                    if (prodfields[10].equals("sapatilha")) {
                        Sapatilha tilha = new Sapatilha();

                        tilha.setCodBarras(prodfields[0]);
                        tilha.setStock(Integer.parseInt(prodfields[1]));
                        tilha.setDataLancamento(LocalDate.parse(prodfields[2]));
                        tilha.setTransportadora(prodfields[3]);
                        tilha.setPrecoBase(Double.parseDouble(prodfields[4]));
                        tilha.setMarca(prodfields[5]);
                        tilha.setDescricao(prodfields[6]);
                        tilha.setEstado(prodfields[7]);
                        tilha.setDesconto(Integer.parseInt(prodfields[8]));
                        tilha.setNome(prodfields[9]);
                        tilha.setTamanho(Integer.parseInt(prodfields[10]));
                        tilha.setTemAtacadores(Boolean.parseBoolean(prodfields[11]));
                        tilha.setCor(prodfields[12]);

                        if (prodfields.length > 13) {
                            //tilha.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //tilha.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoCarrinho(tilha);
                    }

                    if (prodfields[10].equals("mala")) {
                        Mala mala = new Mala();

                        mala.setCodBarras(prodfields[0]);
                        mala.setStock(Integer.parseInt(prodfields[1]));
                        mala.setDataLancamento(LocalDate.parse(prodfields[2]));
                        mala.setTransportadora(prodfields[3]);
                        mala.setPrecoBase(Double.parseDouble(prodfields[4]));
                        mala.setMarca(prodfields[5]);
                        mala.setDescricao(prodfields[6]);
                        mala.setEstado(prodfields[7]);
                        mala.setDesconto(Integer.parseInt(prodfields[8]));
                        mala.setNome(prodfields[9]);
                        mala.setTamanho(prodfields[10]);
                        mala.setMaterial(prodfields[11]);
                        mala.setAnoColecao(Integer.parseInt(prodfields[12]));

                        if (prodfields.length > 13) {
                            //mala.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //mala.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoCarrinho(mala);
                    }
                    if (prodfields[10].equals("tshirt")) {
                        TShirt tshirt = new TShirt();

                        tshirt.setCodBarras(prodfields[0]);
                        tshirt.setStock(Integer.parseInt(prodfields[1]));
                        tshirt.setDataLancamento(LocalDate.parse(prodfields[2]));
                        tshirt.setTransportadora(prodfields[3]);
                        tshirt.setPrecoBase(Double.parseDouble(prodfields[4]));
                        tshirt.setMarca(prodfields[5]);
                        tshirt.setDescricao(prodfields[6]);
                        tshirt.setEstado(prodfields[7]);
                        tshirt.setDesconto(Integer.parseInt(prodfields[8]));
                        tshirt.setNome(prodfields[9]);
                        tshirt.setTam(TShirt.Tamanho.valueOf(prodfields[10]));
                        tshirt.setPadrao(TShirt.Padrao.valueOf(prodfields[11]));

                        if (prodfields.length > 12) {
                            //tshirt.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //tshirt.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoCarrinho(tshirt);
                    }
                }

                //utilizador.setArtigosCarrinho(artigosCarrinho);

                //<--------------------------------------------------------------------------->//
                //<--------------------------------------------------------------------------->//

                // ------------------------- PRODUTOS VENDIDOS -------------------------// done
                // ------------------------- PRODUTOS VENDIDOS -------------------------// done

                Map<String, Artigo> produtosVendidos = new HashMap<>();
                String[] soldprods = fields[9].split(";");

                // 13 - mala
                // 15 - mala usada
                // 12 - tshirt
                // 14 - tshirt usada
                // 13 - sap
                // 15 - sap usada

                for (String prod : soldprods) {

                    String[] prodfields = prod.split(",");

                    if (prodfields[10].equals("sapatilha")) {
                        Sapatilha tilha = new Sapatilha();

                        tilha.setCodBarras(prodfields[0]);
                        tilha.setStock(Integer.parseInt(prodfields[1]));
                        tilha.setDataLancamento(LocalDate.parse(prodfields[2]));
                        tilha.setTransportadora(prodfields[3]);
                        tilha.setPrecoBase(Double.parseDouble(prodfields[4]));
                        tilha.setMarca(prodfields[5]);
                        tilha.setDescricao(prodfields[6]);
                        tilha.setEstado(prodfields[7]);
                        tilha.setDesconto(Integer.parseInt(prodfields[8]));
                        tilha.setNome(prodfields[9]);
                        tilha.setTamanho(Integer.parseInt(prodfields[10]));
                        tilha.setTemAtacadores(Boolean.parseBoolean(prodfields[11]));
                        tilha.setCor(prodfields[12]);

                        if (prodfields.length > 13) {
                            //tilha.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //tilha.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoToProdutosVendidos(tilha);
                    }

                    if (prodfields[10].equals("mala")) {
                        Mala mala = new Mala();

                        mala.setCodBarras(prodfields[0]);
                        mala.setStock(Integer.parseInt(prodfields[1]));
                        mala.setDataLancamento(LocalDate.parse(prodfields[2]));
                        mala.setTransportadora(prodfields[3]);
                        mala.setPrecoBase(Double.parseDouble(prodfields[4]));
                        mala.setMarca(prodfields[5]);
                        mala.setDescricao(prodfields[6]);
                        mala.setEstado(prodfields[7]);
                        mala.setDesconto(Integer.parseInt(prodfields[8]));
                        mala.setNome(prodfields[9]);
                        mala.setTamanho(prodfields[10]);
                        mala.setMaterial(prodfields[11]);
                        mala.setAnoColecao(Integer.parseInt(prodfields[12]));

                        if (prodfields.length > 13) {
                            //mala.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //mala.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoToProdutosVendidos(mala);
                    }
                    if (prodfields[10].equals("tshirt")) {
                        TShirt tshirt = new TShirt();

                        tshirt.setCodBarras(prodfields[0]);
                        tshirt.setStock(Integer.parseInt(prodfields[1]));
                        tshirt.setDataLancamento(LocalDate.parse(prodfields[2]));
                        tshirt.setTransportadora(prodfields[3]);
                        tshirt.setPrecoBase(Double.parseDouble(prodfields[4]));
                        tshirt.setMarca(prodfields[5]);
                        tshirt.setDescricao(prodfields[6]);
                        tshirt.setEstado(prodfields[7]);
                        tshirt.setDesconto(Integer.parseInt(prodfields[8]));
                        tshirt.setNome(prodfields[9]);
                        tshirt.setTam(TShirt.Tamanho.valueOf(prodfields[10]));
                        tshirt.setPadrao(TShirt.Padrao.valueOf(prodfields[11]));

                        if (prodfields.length > 12) {
                            //tshirt.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                            //tshirt.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        }

                        utilizador.addArtigoToProdutosVendidos(tshirt);
                    }
                }

                //<--------------------------------------------------------------------------->
                //<--------------------------------------------------------------------------->

                listaUtilizadores.put(utilizador.getCodigoSistema(), utilizador);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void registarArtigoNoUtlizador(Utilizador user, String codBarras) {
        Utilizador utiRet = this.listaUtilizadores.get(user.getCodigoSistema());

        utiRet.addArtigoToListaAVendaCodBarras(codBarras);
        this.listaUtilizadores.put(utiRet.getCodigoSistema(), utiRet.clone());
    }

    public List<String> percorreUsers() {
        List<String> todosProdutos = new ArrayList<>();

        for(Map.Entry<String, Utilizador> entry : this.listaUtilizadores.entrySet()) {
            Utilizador utilizador = entry.getValue();
            List<String> produtosAVenda = utilizador.getProdutosAVenda();
            todosProdutos.addAll(produtosAVenda);
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
            for(String codBarras : user.getProdutosAVenda()) {
                sb.append(codBarras).append("\n____________________\n");
            }
        }

        return sb.toString();
    }

    /*
    public String toStringArtigosVendaUser(String email) {
        StringBuilder sb = new StringBuilder();
        Utilizador uti = getObjectUserByEmail(email);
        for(Artigo art : uti.getProdutosAVenda()) {
            sb.append(art.toString());
            sb.append("\n________________________\n");
        }

        return sb.toString();
    }
    */

    public String infoTodosUsers() {
        StringBuilder sb = new StringBuilder();

        for(Utilizador user : this.listaUtilizadores.values()) {
            sb.append(user.toString()).append("\n___________________________\n");
        }

        return sb.toString();
    }

    /*
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

    /*
     */
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

    public void removeVariosArtigosUser(Utilizador user, List<String> lst) {
        List<String> lstArtigosNova = user.getProdutosAVenda();
        for(String codBarras : lst) {
            lstArtigosNova.remove(codBarras);
        }
        user.setProdutosAVendaCodBarras(lstArtigosNova);
        this.listaUtilizadores.put(user.getCodigoSistema(), user.clone());
    }

    /*
    public Utilizador getUserByArtigoAVenda(Artigo art) {
        Utilizador ret = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(Artigo article : uti.getProdutosAVenda().val) {
                if(article.getCodBarras().equals(art.getCodBarras())) {
                    ret = null;
                    break;
                }
            }
        }
        return ret;
    }
    */
    public void criaUtilizador2(Utilizador user) {
        this.listaUtilizadores.put(user.getCodigoSistema(), user.clone());
    }
    /*
    public Artigo getArtigoAVendaByUser(Utilizador user, String codBarras) {
        Artigo ret = null;

        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(Artigo art : uti.getProdutosAVenda().) {
                if(art.getCodBarras().equals(codBarras)) {
                    ret = art;
                    break;
                }
            }
        }
        return ret;
    }
    */
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
    /*
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
    */
    public boolean userTemArtigo(String codBarras) {
        for(Utilizador uti : this.listaUtilizadores.values()) {
            for(String art : uti.getProdutosAVenda()) {
                if(art.equals(codBarras))
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
