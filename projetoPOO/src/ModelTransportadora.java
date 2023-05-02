import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelTransportadora {
    private Map<String, Transportadora> listaTransportadoras;

    public ModelTransportadora() {
        this.listaTransportadoras = new HashMap<String, Transportadora>();
    }

    public void criaTransportadora(String nome, double valBase, double margemLucro) {
        Transportadora trans = new Transportadora(nome, valBase, margemLucro);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
    }

    public boolean loginTransportadora(String nomeTrans) {
        if(getTransportadoraByName(nomeTrans) == null) return false;
        else return true;
    }

    public Transportadora getTransportadoraByName(String name) {
        return this.listaTransportadoras.get(name);
    }
}
