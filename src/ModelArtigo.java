import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ModelArtigo {
    private Map<String, Artigo> listaArtigos;
    //private Map<String, Sapatilha> listaSapatilhas;
    //private Map<String, Mala> listaMalas;
    //private Map<String, TShirt> listaTShirts;

    public ModelArtigo() {
        this.listaArtigos = new HashMap<String, Artigo>();
    }

    public Artigo getArtigoByCod(String codBarras) {
        Artigo artigo = this.listaArtigos.get(codBarras);
        if (artigo instanceof Sapatilha) {
            return getSapatilhaByCod(codBarras);
        } else if (artigo instanceof Mala) {
            return getMalaByCod(codBarras);
        } else if (artigo instanceof TShirt) {
            return getTShirtByCod(codBarras);
        } else {
            return null;
        }
    }

    public Sapatilha getSapatilhaByCod(String codBarras) {
        Artigo artigo = this.listaArtigos.get(codBarras);
        if (artigo instanceof Sapatilha) {
            return (Sapatilha) artigo;
        } else {
            return null;
        }
    }

    public Mala getMalaByCod(String codBarras) {
        Artigo artigo = this.listaArtigos.get(codBarras);
        if (artigo instanceof Mala) {
            return (Mala) artigo;
        } else {
            return null;
        }
    }

    public TShirt getTShirtByCod(String codBarras) {
        Artigo artigo = this.listaArtigos.get(codBarras);
        if (artigo instanceof TShirt) {
            return (TShirt) artigo;
        } else {
            return null;
        }
    }



    public String loadArtigos() {

        this.listaArtigos.clear();

        String filePath = "src/artigos.txt"; // VER ISTO DEPOIS
        //System.out.println(System.getProperty("user.dir"));
        File file = new File(filePath);

        if(file.exists() && file.length() > 1) {
            try {
                Scanner scanner = new Scanner(file);

                StringBuilder stringBuilder = new StringBuilder();

                // Loop para ler todas as linhas do arquivo
                while (scanner.hasNextLine()) {

                    //Map<String, Artigo> produtosVendidos = new HashMap<>();
                    String line = scanner.nextLine();
                    stringBuilder.append(line);

                    // Divide a linha em campos usando o separador ":"
                    String[] fields = line.split(":");

                    if (fields.length >= 11 && fields[10].equals("sapatilha")) {
                        Sapatilha tilha = new Sapatilha();

                        tilha.setCodBarras(fields[0]);
                        tilha.setStock(Integer.parseInt(fields[1]));
                        tilha.setDataLancamento(LocalDate.parse(fields[2]));
                        tilha.setTransportadora(fields[3]);
                        tilha.setPrecoBase(Double.parseDouble(fields[4]));
                        tilha.setMarca(fields[5]);
                        tilha.setDescricao(fields[6]);
                        tilha.setEstado(fields[7]);
                        tilha.setNumdonos(Integer.parseInt(fields[8]));
                        tilha.setAvalestado(Integer.parseInt(fields[9]));
                        tilha.setNome(fields[10]);
                        tilha.setTamanho(Integer.parseInt(fields[11]));
                        tilha.setTemAtacadores(Boolean.parseBoolean(fields[12]));
                        tilha.setCor(fields[13]);

                        listaArtigos.put(tilha.getCodBarras(), tilha);
                    }

                    if (fields.length >= 11 && fields[10].equals("mala")) {
                        Mala mala = new Mala();

                        mala.setCodBarras(fields[0]);
                        mala.setStock(Integer.parseInt(fields[1]));
                        mala.setDataLancamento(LocalDate.parse(fields[2]));
                        mala.setTransportadora(fields[3]);
                        mala.setPrecoBase(Double.parseDouble(fields[4]));
                        mala.setMarca(fields[5]);
                        mala.setDescricao(fields[6]);
                        mala.setEstado(fields[7]);
                        mala.setNumdonos(Integer.parseInt(fields[8]));
                        mala.setAvalestado(Integer.parseInt(fields[9]));
                        mala.setNome(fields[10]);
                        mala.setTamanho(fields[11]);
                        mala.setMaterial(fields[12]);
                        mala.setAnoColecao(Integer.parseInt(fields[13]));

                        listaArtigos.put(mala.getCodBarras(), mala);
                    }
                    if (fields.length >= 11 && fields[10].equals("tshirt")) {
                        TShirt tshirt = new TShirt();

                        tshirt.setCodBarras(fields[0]);
                        tshirt.setStock(Integer.parseInt(fields[1]));
                        tshirt.setDataLancamento(LocalDate.parse(fields[2]));
                        tshirt.setTransportadora(fields[3]);
                        tshirt.setPrecoBase(Double.parseDouble(fields[4]));
                        tshirt.setMarca(fields[5]);
                        tshirt.setDescricao(fields[6]);
                        tshirt.setEstado(fields[7]);
                        tshirt.setNumdonos(Integer.parseInt(fields[8]));
                        tshirt.setAvalestado(Integer.parseInt(fields[9]));
                        tshirt.setNome(fields[10]);
                        tshirt.setTam(TShirt.Tamanho.valueOf(fields[11]));
                        tshirt.setPadrao(TShirt.Padrao.valueOf(fields[12]));

                        listaArtigos.put(tshirt.getCodBarras(), tshirt);
                    }

                    stringBuilder.append("\n");
                }

                return stringBuilder.toString();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } System.out.println("File is empty or doesn't exist");

        return "";
    }

    public Sapatilha registarSapatilhaNova(Sapatilha umaSap) {
        this.listaArtigos.put(umaSap.getCodBarras(), umaSap.clone());
        return umaSap.clone();
    }

    public double getPrecoArtigos(List<Artigo> artigos)
    {
        double precototal = 0;

        for(Artigo artigo : artigos)
            precototal += artigo.getPrecoBase();

        return precototal;
    }

    public double getProfitVi(List<Artigo> artigos)
    {
        double profitTotalVi = 0;

        for(Artigo artigo : artigos)
        {
            // COMPOR ISTO <-------------------------------------
            profitTotalVi += 0.5;
        }

        return profitTotalVi;
    }

    public String getArtigoCodBarras(Artigo artigo)
    {
        return artigo.getCodBarras();
    }

    public void tiraArtigoMap(Artigo artigo)
    {
        this.listaArtigos.remove(artigo.getCodBarras());
    }

    public Sapatilha registarSapatilhaNova(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = new Sapatilha(nome,codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, 0, tamanhoSapatilha, temAtacadores, cor);
        this.listaArtigos.put(sapatilha.getCodBarras(), sapatilha.clone());
        this.listaArtigos.put(codBarras,sapatilha);
        return sapatilha.clone();
    }

    public Sapatilha registarSapatilhaUsada(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        Sapatilha sapatilhaUsa = new Sapatilha(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanhoSapatilha,
                temAtacadores, cor, numDonos, avalEstado);
        this.listaArtigos.put(codBarras,sapatilhaUsa);
        return sapatilhaUsa.clone();
    }

    public Mala registarMalaNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                 String material, int anoColecao) {

        Mala mala = new Mala(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, material, anoColecao);
        this.listaArtigos.put(codBarras,mala);
        return mala.clone();
    }

    public Mala registarMalaUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        Mala malausa = new Mala(nome, codBarras,1, dataop,  nomeTrans, precoBase, marca, descricao, desconto, tamanho,
                material, anoColecao, numDonos, avalEstado);
        this.listaArtigos.put(codBarras,malausa);
        return malausa.clone();
    }

    public TShirt registarTShirtNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                   int padrao) {

        TShirt tshirt = new TShirt(nome,codBarras, 1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, padrao);
        this.listaArtigos.put(codBarras,tshirt);

        return tshirt.clone();
    }

    public TShirt registarTShirtUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        TShirt tshirtUsa = new TShirt(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, padrao, numDonos, avalEstado);
        this.listaArtigos.put(codBarras,tshirtUsa);

        return tshirtUsa.clone();
    }

    public List<Artigo> getArtigosParaVenda(List<Artigo> list)
    {
        for (Artigo artigo : this.listaArtigos.values()) {
            list.add(artigo);
        }

        return  list;
    }
}
