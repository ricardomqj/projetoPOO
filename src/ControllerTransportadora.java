import java.util.Map;

public class ControllerTransportadora {
    private ViewerTransportadora viewerTransportadora;
    private ModelTransportadora modelTransportadora;

    public ControllerTransportadora(ViewerTransportadora viewerTransportadora, ModelTransportadora modelTransportadora) {
        this.setModelTransportadora(modelTransportadora);
        this.setViewerTransportadora(viewerTransportadora);
    }

    public Transportadora criaTransportadora(String nome, double valorBase, double margemLucro) {
        return this.modelTransportadora.criaTransportadora(nome, valorBase, margemLucro);
    }

    public String loadTransportadoras() {
        return this.modelTransportadora.loadTransportadoras();
    }

    public String getInfoTrans(String nomeTrans) {
        return modelTransportadora.getInfoTransportadoraByName(nomeTrans);
    }

    public String infosTodasAsTransportadoras() {
        return this.modelTransportadora.infoTodasAsTransportadoras();
    }

    public boolean loginTransportadora(String name) {
        return modelTransportadora.loginTransportadora(name);
    }

    public void changeValBaseExpTransportadora(String nomeTrans, double newValor) {
        this.modelTransportadora.changeValBaseExpTransportadora(nomeTrans, newValor);
    }

    public void changeMargemDeLucroTransportadora(String nomeTrans, double newMargemLucro) {
        this.modelTransportadora.changeMargemLucroTransportadora(nomeTrans, newMargemLucro);
    }

    public Transportadora getTransportadoraByName(String nomeTrans) {
        return this.modelTransportadora.getTransportadoraByName(nomeTrans);
    }

    public void setViewerTransportadora(ViewerTransportadora viewerTransportadora) {
        this.viewerTransportadora = viewerTransportadora;
    }

    public void setModelTransportadora(ModelTransportadora modelTransportadora) {
        this.modelTransportadora = modelTransportadora;
    }

    public Map<String, Transportadora> getListaTransportadoras() {
        return modelTransportadora.getListaTransportadoras();
    }
}
