import java.io.File;
import java.io.FileNotFoundException;
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
        return this.listaArtigos.get(codBarras);
    }

    public void loadArtigo() {
        String filePath = System.getProperty("src/artigos.txt"); // VER ISTO DEPOIS
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            // Loop para ler todas as linhas do arquivo
            while (scanner.hasNextLine()) {

                //Map<String, Artigo> produtosVendidos = new HashMap<>();
                String line = scanner.nextLine();

                // Divide a linha em campos usando o separador ":"
                String[] fields = line.split(":");

                if (fields[10].equals("sapatilha")) {
                    Sapatilha tilha = new Sapatilha();

                    tilha.setCodBarras(fields[0]);
                    tilha.setStock(Integer.parseInt(fields[1]));
                    tilha.setDataLancamento(LocalDate.parse(fields[2]));
                    tilha.setTransportadora(fields[3]);
                    tilha.setPrecoBase(Double.parseDouble(fields[4]));
                    tilha.setMarca(fields[5]);
                    tilha.setDescricao(fields[6]);
                    tilha.setEstado(fields[7]);
                    tilha.setDesconto(Integer.parseInt(fields[8]));
                    tilha.setNome(fields[9]);
                    tilha.setTamanho(Integer.parseInt(fields[10]));
                    tilha.setTemAtacadores(Boolean.parseBoolean(fields[11]));
                    tilha.setCor(fields[12]);

                    if (fields.length > 13) {
                        //tilha.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        //tilha.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                    }

                    listaArtigos.put(tilha.getCodBarras(), tilha);
                }

                if (fields[10].equals("mala")) {
                    Mala mala = new Mala();

                    mala.setCodBarras(fields[0]);
                    mala.setStock(Integer.parseInt(fields[1]));
                    mala.setDataLancamento(LocalDate.parse(fields[2]));
                    mala.setTransportadora(fields[3]);
                    mala.setPrecoBase(Double.parseDouble(fields[4]));
                    mala.setMarca(fields[5]);
                    mala.setDescricao(fields[6]);
                    mala.setEstado(fields[7]);
                    mala.setDesconto(Integer.parseInt(fields[8]));
                    mala.setNome(fields[9]);
                    mala.setTamanho(fields[10]);
                    mala.setMaterial(fields[11]);
                    mala.setAnoColecao(Integer.parseInt(fields[12]));

                    if (fields.length > 13) {
                        //mala.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        //mala.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                    }

                    listaArtigos.put(mala.getCodBarras(), mala);
                }
                if (fields[10].equals("tshirt")) {
                    TShirt tshirt = new TShirt();

                    tshirt.setCodBarras(fields[0]);
                    tshirt.setStock(Integer.parseInt(fields[1]));
                    tshirt.setDataLancamento(LocalDate.parse(fields[2]));
                    tshirt.setTransportadora(fields[3]);
                    tshirt.setPrecoBase(Double.parseDouble(fields[4]));
                    tshirt.setMarca(fields[5]);
                    tshirt.setDescricao(fields[6]);
                    tshirt.setEstado(fields[7]);
                    tshirt.setDesconto(Integer.parseInt(fields[8]));
                    tshirt.setNome(fields[9]);
                    tshirt.setTam(TShirt.Tamanho.valueOf(fields[10]));
                    tshirt.setPadrao(TShirt.Padrao.valueOf(fields[11]));

                    if (fields.length > 12) {
                        //tshirt.setNumDonos;        COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                        //tshirt.setAvalEstado       COMO FAZER ESTE CARALHO ? GT3 GT3 QUERO UM BRANCO
                    }

                    listaArtigos.put(tshirt.getCodBarras(), tshirt);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Sapatilha registarSapatilhaNova(Sapatilha umaSap) {
        this.listaArtigos.put(umaSap.getCodBarras(), umaSap.clone());
        return umaSap.clone();
    }

    public void setDiscountArtigo(String codBarras, int desconto) {
        Artigo art = getArtigoByCod(codBarras);
        double precoBaseArt = art.getPrecoAtual();
        art.setDesconto(desconto);
        art.setPrecoAtual(precoBaseArt * (1-(desconto/100)));
        this.listaArtigos.put(art.getCodBarras(), art.clone());
    }

    public Sapatilha registarSapatilhaNova(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = new Sapatilha(nome,codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, 0, tamanhoSapatilha, temAtacadores, cor);
        this.listaArtigos.put(sapatilha.getCodBarras(), sapatilha.clone());

        // STOCK A 1
        return sapatilha;
    }

    public Sapatilha registarSapatilhaUsada(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        Sapatilha sapatilhaUsa = new Sapatilha(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanhoSapatilha,
                temAtacadores, cor, numDonos, avalEstado);
        // falta stock
        return sapatilhaUsa;
    }

    public String getTipoArtigo(String codBarras) {
        StringBuilder sb = new StringBuilder();
        Artigo art = getArtigoByCod(codBarras);
        String type = art.getEstado().getClass().getSimpleName();
        sb.append(art.getClass().getSimpleName()).append(type);
        return sb.toString();
    }

    public Mala registarMalaNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                 String material, int anoColecao) {

        Mala mala = new Mala(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, material, anoColecao);
        // falta stock
        return mala;
    }

    public Mala registarMalaUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        Mala malausa = new Mala(nome, codBarras,1, dataop,  nomeTrans, precoBase, marca, descricao, desconto, tamanho,
                material, anoColecao, numDonos, avalEstado);
        // falta stock
        return malausa;
    }

    public TShirt registarTShirtNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                   int padrao) {

        TShirt tshirt = new TShirt(nome,codBarras, 1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, padrao);

        return tshirt;
    }

    public TShirt registarTShirtUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        TShirt tshirtUsa = new TShirt(nome, codBarras,1, dataop, nomeTrans, precoBase, marca, descricao, desconto, tamanho, padrao, numDonos, avalEstado);

        return tshirtUsa;
    }

    public List<Artigo> getArtigosParaVenda(List<Artigo> list)
    {
        for (Artigo artigo : this.listaArtigos.values()) {
            list.add(artigo);
        }

        return  list;
    }
}
