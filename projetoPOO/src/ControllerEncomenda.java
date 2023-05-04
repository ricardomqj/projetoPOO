public class ControllerEncomenda {
    private ViewerEncomenda viewerEncomenda;
    private ModelEncomenda modelEncomenda;

    public ControllerEncomenda(ViewerEncomenda viewerEncomenda,ModelEncomenda modelEncomenda)
    {
        this.setModelEncomenda(modelEncomenda);
        this.setViewerEncomenda(viewerEncomenda);
    }

    //methods

    public Encomenda criaEncomenda(Utilizador user) {
        Encomenda enc = this.modelEncomenda.criaEncomenda(user);

        return enc;
    }

    public void addArtigoEncomenda (Encomenda enc, Artigo artigo) {
        this.modelEncomenda.addArtigoEncomenda(enc, artigo);
    }

    //getters e setters
    public ViewerEncomenda getViewerEncomenda() {
        return viewerEncomenda;
    }

    public void setViewerEncomenda(ViewerEncomenda viewerEncomenda) {
        this.viewerEncomenda = viewerEncomenda;
    }

    public ModelEncomenda getModelEncomenda() {
        return modelEncomenda;
    }

    public void setModelEncomenda(ModelEncomenda modelEncomenda) {
        this.modelEncomenda = modelEncomenda;
    }
}
