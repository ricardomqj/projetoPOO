import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
            for(Artigo art : enc.getArtigos()) {
                if (user.getProdutosVendidos().containsValue(art)) {
                    encsUser.put(enc.getCodSistema(), enc);
                }
            }
        }

        return encsUser;
    }

    public String topNCompradores(int num, Map<String, Utilizador> listaUtilizadores, LocalDate di, LocalDate df) {
        StringBuilder sb = new StringBuilder();
        List<Utilizador> utilizadoresOrdenadosPorDinheiroGasto = new ArrayList<>(listaUtilizadores.values());

        Collections.sort(utilizadoresOrdenadosPorDinheiroGasto, new Comparator<Utilizador>() {
            @Override
            public int compare(Utilizador u1, Utilizador u2) {
                double dinheiroGastoU1 = u1.dinheiroGasto(di, df);
                double dinheiroGastoU2 = u2.dinheiroGasto(di, df);
                if(dinheiroGastoU1 < dinheiroGastoU2) {
                    return 1;
                } else if(dinheiroGastoU1 > dinheiroGastoU2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        utilizadoresOrdenadosPorDinheiroGasto = utilizadoresOrdenadosPorDinheiroGasto.subList(0, Math.min(num, utilizadoresOrdenadosPorDinheiroGasto.size()));
        for(Utilizador uti : utilizadoresOrdenadosPorDinheiroGasto) {
            sb.append(uti.toString()).append("\n");
        }
        return sb.toString();
    }

    public Utilizador utilizadorComMaiorDinheiroGanho(Map<String, Utilizador> utilizadores, LocalDate di, LocalDate df) {
        Utilizador utilizadorComMaiorDinheiro = null;
        double maiorDinheiroGanho = Double.NEGATIVE_INFINITY;

        for(Utilizador utilizador : utilizadores.values()) {
            double dinheiroGanho = utilizador.dinheiroGanho(di, df);
            if(dinheiroGanho > maiorDinheiroGanho) {
                maiorDinheiroGanho = dinheiroGanho;
                utilizadorComMaiorDinheiro = utilizador;
            }
        }

        return utilizadorComMaiorDinheiro;
    }

    public String topNVendedores(int num, Map<String, Utilizador> listaUtilizadores, LocalDate di, LocalDate df) {
        StringBuilder sb = new StringBuilder();

        List<Utilizador> utilizadoresList = new ArrayList<>(listaUtilizadores.values());
        utilizadoresList.sort(Comparator.comparingDouble(u -> -u.dinheiroGanho(di, df)));
        utilizadoresList = utilizadoresList.subList(0, Math.min(num, utilizadoresList.size()));

        for(Utilizador uti : utilizadoresList) {
            sb.append(uti.toString()).append("\n___________________________\n");
        }

        return sb.toString();
    }

    public Utilizador getVendedorMaisFaturouSempre(Map<String, Utilizador> lstUsers) {
        Utilizador usrComMaisProfit = null;
        double maiorProfit = Double.NEGATIVE_INFINITY;

        for(Utilizador utilizador : lstUsers.values()) {
            if(utilizador.getProfit() > maiorProfit) {
                maiorProfit = utilizador.getProfit();
                usrComMaisProfit = utilizador;
            }
        }

        return usrComMaisProfit;
    }

    public Utilizador getVendedorQueMaisFaturouPeriodo(Map<String, Utilizador> lstUsers, LocalDate di, LocalDate df) {
        Utilizador ret = null;
        double maiorProfit = Double.NEGATIVE_INFINITY;

        for(Utilizador uti : lstUsers.values()) {
            double profitAtual = 0.0;
            for(Artigo art : uti.getProdutosVendidos().values()) {
                if(art.getDataComprado().isAfter(di) && art.getDataComprado().isBefore(df)) {
                    profitAtual += art.getPrecoBase();
                }
            }
            if(profitAtual>maiorProfit) {
                maiorProfit = profitAtual;
                ret = uti.clone();
            }
        }
        return ret;
    }

    public String transportadoraComMaisFaturacao(Map<String, Transportadora> lstTransportadoras, Map<String, Utilizador> listaUtilizadores) {
        String ret = null;
        double val = Double.NEGATIVE_INFINITY;

        for(Transportadora trans : lstTransportadoras.values()) {
            double currentVal = 0.0;
            for(Utilizador user : listaUtilizadores.values()) {
                for(Artigo art : user.getProdutosVendidos().values()) {
                    if(art.getNomeTransportadora().equals(trans.getNome())) {
                        currentVal = currentVal + (trans.valorExpedicao() * (1 - trans.getMargemLucro())/100);
                        break;
                    }
                }
            }
            if(currentVal > val) val = currentVal;
            ret = trans.getNome();
        }

        return ret;
    }
}
