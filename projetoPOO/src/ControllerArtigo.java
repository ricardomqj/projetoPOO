import java.time.LocalDate;

public class ControllerArtigo {
    private ViewerArtigo viewerArtigo;
    private ModelArtigo modelArtigo;

    public ControllerArtigo(ViewerArtigo viewerArtigo,ModelArtigo modelArtigo)
    {
        this.setModelArtigo(modelArtigo);
        this.setViewerArtigo(viewerArtigo);
    }
    public void registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        this.modelArtigo.registarSapatilhaNova(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor);
    }

    public void registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        this.modelArtigo.registarSapatilhaUsada(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);
    }

    public void registarMalaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao) {

        this.modelArtigo.registarMalaNova(codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao);
    }

    public void registarMalaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        this.modelArtigo.registarMalaUsada(codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao, numDonos, avalEstado);
    }

    public void registarTShirtNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                   int padrao) {

        this.modelArtigo.registarTShirtNova(codBarras, dataop, precoBase, nomeTrans, tamanho, padrao);
    }

    public void registarTShirtUsada(String codBarras, LocalDate dataop, String nomeTrans, double precoBase, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        this.modelArtigo.registarTShirtUsada(codBarras, dataop, nomeTrans, precoBase, tamanho, padrao, numDonos, avalEstado);
    }

    public void setViewerArtigo(ViewerArtigo viewerArtigo) {
        this.viewerArtigo = viewerArtigo;
    }

    public void setModelArtigo(ModelArtigo modelArtigo) {
        this.modelArtigo = modelArtigo;
    }
}
