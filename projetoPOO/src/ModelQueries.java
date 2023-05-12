import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelQueries {
    public double vintageProfit(Map<String, Encomenda> listaEncomendas) {
        double lucro = 0.0;

        for(Encomenda enc : listaEncomendas.values()){
            lucro += enc.vintageProfit();
        }

        return lucro;
    }

    public Map<String, Encomenda> encsVendedor(Map<String, Encomenda> listaEncomendas, Utilizador user) {
        Map<String, Encomenda> encsUser = new HashMap<String, Encomenda>();

        for(Encomenda enc : listaEncomendas.values()) {
                for(Artigo art : enc.getArtigos().values()) {
                    if (user.getProdutosVendidos().containsValue(art)) {
                        encsUser.put(enc.getCodSistema(), enc);
                    }
                }
        }

        return encsUser;
    }
}
