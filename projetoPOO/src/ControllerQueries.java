import java.time.LocalDate;
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

    public String topNCompradores(int num, Map<String, Utilizador> listaUtilizadores, LocalDate di, LocalDate df) {
        return modelQueries.topNCompradores(num, listaUtilizadores, di, df);
    }

    public String topNVendedores(int num, Map<String, Utilizador> listaUtilizadores, LocalDate di, LocalDate df) {
        return modelQueries.topNVendedores(num, listaUtilizadores, di, df);
    }

    public double vintageProfit(Map<String, Encomenda> listaEncomendas) {
        return this.modelQueries.vintageProfit(listaEncomendas);
    }

    public String utilizadorComMaiorDinheiroGanho(Map<String, Utilizador> utilizadores, LocalDate di, LocalDate df) {
        return modelQueries.utilizadorComMaiorDinheiroGanho(utilizadores, di, df).toString();
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

    public String transportadoraComMaisFaturação(Map<String, Transportadora> lstTrans, Map<String, Utilizador> lstUsers) {
        return this.modelQueries.transportadoraComMaisFaturacao(lstTrans, lstUsers);
    }

    public String getVendedorQueMaisFaturouSempre(Map<String, Utilizador> lstUsers) {
        return modelQueries.getVendedorMaisFaturouSempre(lstUsers).toString();
    }

    public String getVendedorQueMaisFaturouPeriodo(Map<String, Utilizador> lstUsers, LocalDate di, LocalDate df) {
        return modelQueries.getVendedorQueMaisFaturouPeriodo(lstUsers, di, df).toString();
    }
}
