import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
}
