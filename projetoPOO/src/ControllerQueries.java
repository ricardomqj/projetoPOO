import java.util.List;
import java.util.Map;

public class ControllerQueries {

    private ViewerQueries viewerQueries;
    private ModelQueries modelQueries;

    public ControllerQueries(ViewerQueries viewerQueries,ModelQueries modelQueries)
    {
        this.setModelQueries(modelQueries);
        this.setViewerQueries(viewerQueries);
    }

    public double vintageProfit(Map<String, Encomenda> listaEncomendas) {
        return this.modelQueries.vintageProfit(listaEncomendas);
    }

    public String encsVendedorToString(Map<String, Encomenda> encsVendedor) {
        return this.viewerQueries.encsVendedorToString(encsVendedor);
    }

    public Map<String, Encomenda> encsVendedor(Map<String, Encomenda> listaEncomendas, Utilizador user) {
        return this.modelQueries.encsVendedor(listaEncomendas, user);
    }

    public ModelQueries getModelQueries() {
        return modelQueries;
    }

    public void setModelQueries(ModelQueries modelQueries) {
        this.modelQueries = modelQueries;
    }

    public ViewerQueries getViewerQueries() {
        return viewerQueries;
    }

    public void setViewerQueries(ViewerQueries viewerQueries) {
        this.viewerQueries = viewerQueries;
    }
}
