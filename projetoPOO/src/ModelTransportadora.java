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

    public String getInfoTransportadoraByName(String nomeTrans) {
        return this.listaTransportadoras.get(nomeTrans).toString();
    }

    public void changeValBaseExpTransportadora(String nomeTrans, double newValBase) {
        Transportadora trans = this.listaTransportadoras.get(nomeTrans);
        trans.setValorBase(newValBase);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
    }

    public void changeMargemLucroTransportadora(String nomeTrans, double newMargemLucro) {
        Transportadora trans = this.listaTransportadoras.get(nomeTrans);
        trans.setMargemLucro(newMargemLucro);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
    }

    public String infoTodasAsTransportadoras() {
        StringBuilder sb = new StringBuilder();

        for(Transportadora trans : this.listaTransportadoras.values()) {
            sb.append(trans.toString()).append("\n__________________________\n");
        }

        return sb.toString();
    }

    public Transportadora getTransportadoraByName(String name) {
        return this.listaTransportadoras.get(name);
    }
}
