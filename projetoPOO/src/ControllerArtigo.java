import java.time.LocalDate;

public class ControllerArtigo {
    private ViewerArtigo viewerArtigo;
    private ModelArtigo modelArtigo;

    public ControllerArtigo(ViewerArtigo viewerArtigo,ModelArtigo modelArtigo)
    {
        this.setModelArtigo(modelArtigo);
        this.setViewerArtigo(viewerArtigo);
    }

    public Artigo getArtigoByCod(String codBarras) {

        return this.modelArtigo.getArtigoByCod(codBarras);
    }

    public Sapatilha registarSapatilhaNova(Sapatilha umaSap) {
        return this.modelArtigo.registarSapatilhaNova(umaSap);
    }

    public void setDiscountArtigo(String codBarras, int desconto) {
        this.modelArtigo.setDiscountArtigo(codBarras, desconto);
    }

    public Sapatilha registarSapatilhaNova(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto,int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = this.modelArtigo.registarSapatilhaNova(nome, codBarras, dataop, precoBase, nomeTrans, marca, descricao, tamanhoSapatilha, temAtacadores, cor);

        return sapatilha;
    }

    public Sapatilha registarSapatilhaUsada(String nome, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        Sapatilha sapatilhausa = this.modelArtigo.registarSapatilhaUsada(nome, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);

        return sapatilhausa;
    }

    public Mala registarMalaNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                 String material, int anoColecao) {

        Mala mala = this.modelArtigo.registarMalaNova(nome, utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao);

        return mala;
    }

    public Mala registarMalaUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        Mala malausa = this.modelArtigo.registarMalaUsada(nome, utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao, numDonos, avalEstado);

        return malausa;
    }

    public TShirt registarTShirtNova(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                   int padrao) {

        TShirt tshirt = this.modelArtigo.registarTShirtNova(nome, utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, padrao);
        return tshirt;
    }

    public TShirt registarTShirtUsada(String nome, Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String marca, String descricao, int desconto, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        TShirt tshirtusa = this.modelArtigo.registarTShirtUsada(nome, utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, padrao, numDonos, avalEstado);
        return tshirtusa;
    }

    public void setViewerArtigo(ViewerArtigo viewerArtigo) {
        this.viewerArtigo = viewerArtigo;
    }

    public void setModelArtigo(ModelArtigo modelArtigo) {
        this.modelArtigo = modelArtigo;
    }
}
