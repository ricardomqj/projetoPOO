import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ModelEncomenda {

    private Map<String, Encomenda> listaEncomendas;

    //methods
    public ModelEncomenda() {
        this.listaEncomendas = new HashMap<String, Encomenda>();
    }

    public Encomenda criaEncomenda(String codSistemaUtlizador, List<Artigo> lstArtigos,double preco,double profitVi) {

        String codigoEncomenda = gerarCodigoSistema();

        Encomenda encomenda = new Encomenda(codigoEncomenda,codSistemaUtlizador,lstArtigos,preco,profitVi);

        this.listaEncomendas.put(codigoEncomenda,encomenda.clone());

        return encomenda;
    }

    public String loadEncomendas() {

        this.listaEncomendas.clear();

        String filePath = "src/encomendas.txt"; // VER ISTO DEPOIS
        //System.out.println(System.getProperty("user.dir"));
        File file = new File(filePath);

        if (file.exists() && file.length() > 1) {
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

                        String[] prodencfields = prod.split("\\.");

                        if (fields.length >= 12 && fields[11].equals("sapatilha")) {
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

                        if (fields.length >= 12 && fields[11].equals("mala")) {
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
                        if (fields.length >= 12 && fields[11].equals("tshirt")) {
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
                    //encomenda.setStatus(Encomenda.StatusEncomenda.valueOf(fields[3]));

                    if (fields[3].equals("PENDENTE")) encomenda.setStatus(Encomenda.StatusEncomenda.PENDENTE);
                    if (fields[3].equals("FINALIZADO")) encomenda.setStatus(Encomenda.StatusEncomenda.FINALIZADO);
                    if (fields[3].equals("EXPEDIDO")) encomenda.setStatus(Encomenda.StatusEncomenda.EXPEDIDO);




                    encomenda.setData(LocalDate.parse(fields[4]));
                    encomenda.setVintageProfit();

                    stringBuilder.append("\n");
                }

                return stringBuilder.toString();

            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
        else System.out.println("File is empty or doesn't exist");

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
        String codEncomenda = gerarCodigoSistema();
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

    private String gerarCodigoSistema()
    {
        String codigoSistema = UUID.randomUUID().toString();
        while (this.listaEncomendas.containsKey(codigoSistema))
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
