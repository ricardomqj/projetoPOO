import jdk.jshell.execution.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ModelUtlizador {
    private Map<String,Utilizador> listaUtilizadores;
    public ModelUtlizador()
    {
        this.listaUtilizadores = new HashMap<String,Utilizador>();
    }
    public Utilizador criaUtlizador(String email,String nome,String morada,String nif)
    {
        String codigoSistema = gerarCodigoSistema(this.listaUtilizadores);

        Utilizador utilizador = new Utilizador(codigoSistema,email,nome,morada,nif);

        this.listaUtilizadores.put(utilizador.getCodigoSistema(),utilizador.clone());

        return utilizador;
    }

    public Map<String, Utilizador> getListaUtilizadores() {
        return this.listaUtilizadores.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
    }

    public String loadUtilizadores() {

        this.listaUtilizadores.clear();

        String filePath = "projetoPOO-Rui/projetoPOO/src/utilizadores.txt"; // VER ISTO DEPOIS
        File file = new File(filePath);

        if (file.exists()) {
            try {
                // Cria um objeto Scanner para ler as informações do arquivo
                Scanner scanner = new Scanner(file);

                StringBuilder stringBuilder = new StringBuilder();

                // Loop para ler todas as linhas do arquivo
                while (scanner.hasNextLine()) {

                    // Lê a próxima linha do arquivo
                    String line = scanner.nextLine();
                    stringBuilder.append(line);

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
                    String[] produtosAVendaArray = fields[5].split(";");
                    for (String codigoBarras : produtosAVendaArray) {
                        produtosAVenda.add(codigoBarras);
                    }
                    utilizador.setProdutosAVendaCodBarras(produtosAVenda);

                    //<--------------------------------------------------------------------------->
                    //<--------------------------------------------------------------------------->

                    // ------------------------- ENCOMENDAS FEITAS -------------------------//
                    // ------------------------- ENCOMENDAS FEITAS -------------------------//

                    //List<String> encomendasFeitas = new ArrayList<>();
                    String[] encFeitas = fields[6].split(";");

                    for (String enc : encFeitas) {

                        String[] encfields = enc.split(",");

                        Encomenda encomenda = new Encomenda();

                        encomenda.setCodSistema(encfields[0]);
                        encomenda.setcodSistemaUtlizador(utilizador.getCodigoSistema()); // PROBLEMA USER

                        String[] encprods = encfields[1].split(".");

                        for (String prod : encprods) {

                            String[] prodencfields = prod.split("/");

                            if (prodencfields[11].equals("sapatilha")) {
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
                                tilha.setNumdonos(Integer.parseInt(prodencfields[9]));
                                tilha.setAvalestado(Integer.parseInt(prodencfields[10]));
                                tilha.setNome(prodencfields[11]);
                                tilha.setTamanho(Integer.parseInt(prodencfields[12]));
                                tilha.setTemAtacadores(Boolean.parseBoolean(prodencfields[13]));
                                tilha.setCor(prodencfields[14]);

                                encomenda.insereUmArtigo(tilha);
                            }

                            if (prodencfields[11].equals("mala")) {
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
                                mala.setNumdonos(Integer.parseInt(prodencfields[9]));
                                mala.setAvalestado(Integer.parseInt(prodencfields[10]));
                                mala.setNome(prodencfields[11]);
                                mala.setTamanho(prodencfields[12]);
                                mala.setMaterial(prodencfields[13]);
                                mala.setAnoColecao(Integer.parseInt(prodencfields[14]));

                                encomenda.insereUmArtigo(mala);
                            }
                            if (prodencfields[11].equals("tshirt")) {
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
                                tshirt.setNumdonos(Integer.parseInt(prodencfields[9]));
                                tshirt.setAvalestado(Integer.parseInt(prodencfields[10]));
                                tshirt.setNome(prodencfields[11]);
                                tshirt.setTam(TShirt.Tamanho.valueOf(prodencfields[12]));
                                tshirt.setPadrao(TShirt.Padrao.valueOf(prodencfields[13]));

                                encomenda.insereUmArtigo(tshirt);
                            }
                        }

                        encomenda.setTamanhoEncomenda();
                        encomenda.setPrecoFinal();
                        encomenda.setStatus(Encomenda.StatusEncomenda.valueOf(encfields[2])); // NAO SEI
                        encomenda.setData(LocalDate.parse(encfields[3]));
                        encomenda.setVintageProfit();

                    }

                    //<--------------------------------------------------------------------------->//
                    //<--------------------------------------------------------------------------->//

                    // ------------------------- ARTIGOS CARRINHO -------------------------//
                    // ------------------------- ARTIGOS CARRINHO -------------------------//

                    // Preenche a lista de ARTIGOS CARRINHO
                    String[] artigosCarrinhoArray = fields[7].split(";");

                    for (String prod : artigosCarrinhoArray) {

                        String[] prodfields = prod.split(",");

                        if (prodfields[11].equals("sapatilha")) {
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
                            tilha.setNumdonos(Integer.parseInt(prodfields[9]));
                            tilha.setAvalestado(Integer.parseInt(prodfields[10]));
                            tilha.setNome(prodfields[11]);
                            tilha.setTamanho(Integer.parseInt(prodfields[12]));
                            tilha.setTemAtacadores(Boolean.parseBoolean(prodfields[13]));
                            tilha.setCor(prodfields[14]);

                            utilizador.addArtigoCarrinho(tilha);
                        }

                        if (prodfields[11].equals("mala")) {
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
                            mala.setNumdonos(Integer.parseInt(prodfields[9]));
                            mala.setAvalestado(Integer.parseInt(prodfields[10]));
                            mala.setNome(prodfields[11]);
                            mala.setTamanho(prodfields[12]);
                            mala.setMaterial(prodfields[13]);
                            mala.setAnoColecao(Integer.parseInt(prodfields[14]));

                            utilizador.addArtigoCarrinho(mala);
                        }
                        if (prodfields[11].equals("tshirt")) {
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
                            tshirt.setNumdonos(Integer.parseInt(prodfields[9]));
                            tshirt.setAvalestado(Integer.parseInt(prodfields[10]));
                            tshirt.setNome(prodfields[11]);
                            tshirt.setTam(TShirt.Tamanho.valueOf(prodfields[12]));
                            tshirt.setPadrao(TShirt.Padrao.valueOf(prodfields[13]));

                            utilizador.addArtigoCarrinho(tshirt);
                        }
                    }

                    //utilizador.setArtigosCarrinho(artigosCarrinho);

                    //<--------------------------------------------------------------------------->//
                    //<--------------------------------------------------------------------------->//

                    // ------------------------- PRODUTOS VENDIDOS -------------------------// done
                    // ------------------------- PRODUTOS VENDIDOS -------------------------// done

                    Map<String, Artigo> produtosVendidos = new HashMap<>();
                    String[] soldprods = fields[8].split(";");


                    for (String prod : soldprods) {

                        String[] prodfields = prod.split(",");

                        if (prodfields[11].equals("sapatilha")) {
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
                            tilha.setNumdonos(Integer.parseInt(prodfields[9]));
                            tilha.setAvalestado(Integer.parseInt(prodfields[10]));
                            tilha.setNome(prodfields[11]);
                            tilha.setTamanho(Integer.parseInt(prodfields[12]));
                            tilha.setTemAtacadores(Boolean.parseBoolean(prodfields[13]));
                            tilha.setCor(prodfields[14]);


                            utilizador.addArtigoToProdutosVendidos(tilha);
                        }

                        if (prodfields[11].equals("mala")) {
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
                            mala.setNumdonos(Integer.parseInt(prodfields[9]));
                            mala.setAvalestado(Integer.parseInt(prodfields[10]));
                            mala.setNome(prodfields[11]);
                            mala.setTamanho(prodfields[12]);
                            mala.setMaterial(prodfields[13]);
                            mala.setAnoColecao(Integer.parseInt(prodfields[14]));

                            utilizador.addArtigoToProdutosVendidos(mala);
                        }
                        if (prodfields[11].equals("tshirt")) {
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
                            tshirt.setNumdonos(Integer.parseInt(prodfields[9]));
                            tshirt.setAvalestado(Integer.parseInt(prodfields[10]));
                            tshirt.setNome(prodfields[11]);
                            tshirt.setTam(TShirt.Tamanho.valueOf(prodfields[12]));
                            tshirt.setPadrao(TShirt.Padrao.valueOf(prodfields[13]));

                            utilizador.addArtigoToProdutosVendidos(tshirt);
                        }
                    }

                    //<--------------------------------------------------------------------------->
                    //<--------------------------------------------------------------------------->

                    listaUtilizadores.put(utilizador.getCodigoSistema(), utilizador);

                    stringBuilder.append("\n");
                }

                return stringBuilder.toString();

            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
        else System.out.println("File does not exist!");

        return "";
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
        Encomenda enc = new Encomenda(user.getCodigoSistema(), user.getCodigoSistema(), lst); // ESTA MERDA TA MAL QUE FODE PUTA QUE PARIU
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

    public boolean addArtigoCarrinho(Utilizador utilizador, Artigo art) {
        for(Artigo aux_art : utilizador.getArtigosCarrinho())
            if(aux_art.equals(art))
                return false;

        utilizador.addArtigoCarrinho(art);
        return true;
    }

    public List<Artigo> getArtigosCarrinho(Utilizador utilizador)
    {
        return utilizador.getArtigosCarrinho();
    }

    public String getCodSistemaUtlizador(Utilizador utilizador)
    {
        return utilizador.getCodigoSistema();
    }

    public void retiraArtigoDeVenda(String codBarras,Artigo artigo,String codSistemaComprador)
    {
        int key = 0;

        for(Map.Entry<String,Utilizador> utilizador : this.listaUtilizadores.entrySet())
        {
            List<String> lista = utilizador.getValue().getProdutosAVenda();;

            for(String artigoParaVenda : lista)
            {
                if(artigoParaVenda.equals(codBarras))
                {
                    key = 1;
                    lista.remove(artigoParaVenda);
                    utilizador.getValue().addArtigoToProdutosVendidos(artigo);
                    break;
                }
            }
            if(key == 1) {
                utilizador.getValue().setProdutosAVendaCodBarras(lista);
                break;
            }
        }

        Utilizador utilizador = this.listaUtilizadores.get(codSistemaComprador);
        utilizador.addArtigoToProdutosVendidos(artigo);
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
        Encomenda enc = new Encomenda(codEnc, ret.getCodigoSistema(), carrinhoMap);
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
