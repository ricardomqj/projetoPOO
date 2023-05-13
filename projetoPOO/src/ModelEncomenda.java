import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ModelEncomenda {

    private Map<String, Encomenda> listaEncomendas;

    //methods
    public ModelEncomenda() {
        this.listaEncomendas = new HashMap<String, Encomenda>();
    }

    public void criaEncomenda(String codSistemaUtlizador, Map<String, Artigo> lstArtigos) {

        String codigoEncomenda = gerarCodigoSistema(this.listaEncomendas);

        Encomenda enc = new Encomenda(codigoEncomenda, codSistemaUtlizador, lstArtigos);

        this.listaEncomendas.put(enc.getCodSistema(), enc.clone());
    }

    public String loadEncomendas() {
        String filePath = ("src/encomendas.txt");

        File file = new File(filePath);

        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);

                StringBuilder stringBuilder = new StringBuilder();

                // Loop para ler todas as linhas do arquivo
                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();
                    stringBuilder.append(line);

                    // Divide a linha em campos usando o separador ":"
                    String[] fields = line.split(":");

                    Encomenda encomenda = new Encomenda();

                    encomenda.setCodSistema(fields[0]);
                    encomenda.setcodSistemaUtlizador(fields[1]);

                    String[] encprods = fields[2].split("/");

                    for (String prod : encprods) {

                        String[] prodencfields = prod.split(".");

                        if (prodencfields[9].equals("sapatilha")) {
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

                        if (prodencfields[9].equals("mala")) {
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
                        if (prodencfields[9].equals("tshirt")) {
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
                    encomenda.setStatus(Encomenda.StatusEncomenda.valueOf(fields[3])); // NAO SEI
                    encomenda.setData(LocalDate.parse(fields[4]));
                    encomenda.setVintageProfit();

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

    public void addArtigoEncomenda (Encomenda enc, Artigo artigo) {
        enc.insereUmArtigo(artigo);
    }

    /*
    public Artigo getArtigoByCod(String codBarras) {

        Artigo art = null;

        for (Artigo artigo: this.listaArtigos.values()) {
            if(artigo.getCodBarras().equals(codBarras)) art = artigo;
        }

        return art;
    }
     */

    public String addEncomenda(List<Artigo> lstArt, Utilizador user) {
        Map<String, Artigo> lstArtigos = new HashMap<String, Artigo>();
        lstArtigos = lstArt.stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
        String codEncomenda = gerarCodigoSistema(listaEncomendas);
        Encomenda enc = new Encomenda(codEncomenda, user.getCodigoSistema(), lstArtigos);
        this.listaEncomendas.put(enc.getCodSistema(), enc.clone());
        return codEncomenda;
    }

    public Map<String, Encomenda> getListaTodasEncomendas() {
        return this.listaEncomendas.values().stream().collect(Collectors.toMap(Encomenda::getCodSistema, Encomenda::clone));
    }

    public String infoTodasAsEncomendas() {
        StringBuilder sb = new StringBuilder();

        for(Encomenda enc : this.listaEncomendas.values()) {
            sb.append(enc.toString()).append("\n__________________________________\n");
        }

        return sb.toString();
    }

    private String gerarCodigoSistema(Map<String, Encomenda> listaEncomendas)
    {
        String codigoSistema = UUID.randomUUID().toString();
        while (listaEncomendas.containsKey(codigoSistema))
        {
            codigoSistema = UUID.randomUUID().toString();
        }
        return codigoSistema;
    }

    /*
    public void criaEncomendaModel(String emailUser) {
        Encomenda enc = new Encomenda();

        Utilizador user = ModelUser.getUserByEmail(emailUser);
        //Map<String, Artigo> artigos = new HashMap<>();

        enc.setUser(user);

    }

    public void addArtigoEncomendaModel(Map<String, Artigo> artigos, String codBarras) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Artigo> listaArtigos = Model.getListaArtigos();

        Artigo artigo = listaArtigos.get(codBarras);

        artigos.put(codBarras, artigo);

        // codigo: Barras fazer hash
        // stock: if getStock == null dizer que nao tem stock
        // datalanc: pedir
        // transp: PEDIR / se nao existir, "transportadora nao encontrada"
        // precobase: pedir

        // PEDIR APENAS CODIGO DE BARRAS, e ir buscar tudo com ele
        // Se nao existir retornar "codigo nao encontrado tente novamente".

        //System.out.println("Digite o código de barras do Artigo que deseja adicionar: ");
        //String codBarras = scanner.next();

        //Model.addArtigoEncomenda(codBarras, enc);

        //System.out.println("Deseja adicionar mais algum artigo? (S/N)");
        //String simnao = scanner.next();

        //arrayCodBarras["sdasd", "sdas", "dsadsa123"];

        //if (simnao.equals("S")) addArtigoEncomendaModel();
    }

     */
}
