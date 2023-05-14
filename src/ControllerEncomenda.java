import java.util.List;
import java.util.Map;

public class ControllerEncomenda {
    private ViewerEncomenda viewerEncomenda;
    private ModelEncomenda modelEncomenda;

    public ControllerEncomenda(ViewerEncomenda viewerEncomenda, ModelEncomenda modelEncomenda)
    {
        this.setModelEncomenda(modelEncomenda);
        this.setViewerEncomenda(viewerEncomenda);
    }

    //methods

    public Encomenda criaEncomenda(String codSistemaUtlizador, List<Artigo> lstArt,double preco,double profitVi) {
        return this.modelEncomenda.criaEncomenda(codSistemaUtlizador, lstArt,preco,profitVi);
    }

    public String loadEncomendas() {
        return this.modelEncomenda.loadEncomendas();
    }

    public void addArtigoEncomenda (Encomenda enc, Artigo artigo) {
        this.modelEncomenda.addArtigoEncomenda(enc, artigo);
    }

    public String infoTodasEncomendas() {
        return modelEncomenda.infoTodasAsEncomendas();
    }

    public Map<String, Encomenda> getListaTodasEncomendas() {
        return modelEncomenda.getListaTodasEncomendas();
    }

    public String addEncomenda(List<Artigo> lstArt, Utilizador user) {
        return modelEncomenda.addEncomenda(lstArt, user);
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
