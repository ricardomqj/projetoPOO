import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ModelVersao {
    private Map<LocalTime, Versao> listaVersoes;
    public ModelVersao() {
        this.listaVersoes = new HashMap<LocalTime, Versao>();
    }

    // METHODS

    public void addUserToTxt(Utilizador user, String versaoUserTxt) {
        String userTxt = user.toStringTxt();

        //Fazer toStringTxtUser e toStringTxtEncomenda
    }

    public void addTransportadoraTxt(Transportadora trans, String versaoTransportadorasTxt) {
        String transTxt = trans.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoTransportadorasTxt.isEmpty()) {
            sb.append(transTxt);
        } else {
            sb.append(versaoTransportadorasTxt).append("\n").append(transTxt);
        }

    }

    public void addSapatilhaTxt(Sapatilha tilha, String versaoArtigoTxt) {
        String sapatilhaTxt = tilha.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoArtigoTxt.isEmpty()) {
            sb.append(sapatilhaTxt);
        } else {
            sb.append(versaoArtigoTxt).append("\n").append(sapatilhaTxt);
        }

    }
    public void addSapatilhaUsadaTxt(Sapatilha tilha, String versaoArtigoTxt) {
        String sapatilhaUsadaTxt = tilha.toStringTxt();

        //Fazer acabar funçao toStringTxt
    }

    public void addMalaTxt(Mala mala, String versaoArtigoTxt) {
        String malaTxt = mala.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoArtigoTxt.isEmpty()) {
            sb.append(malaTxt);
        } else {
            sb.append(versaoArtigoTxt).append("\n").append(malaTxt);
        }

    }
    public void addMalaUsadaTxt(Mala mala, String versaoArtigoTxt) {
        String malaUsadaTxt = mala.toStringTxt();

        //Fazer acabar funçao toStringTxt
    }

    public void addTShirtTxt(TShirt tshirt, String versaoArtigoTxt) {
        String tshirtTxt = tshirt.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoArtigoTxt.isEmpty()) {
            sb.append(tshirtTxt);
        } else {
            sb.append(versaoArtigoTxt).append("\n").append(tshirtTxt);
        }

    }
    public void addTShirtUsadaTxt(TShirt tshirt, String versaoArtigoTxt) {
        String tshirtUsadaTxt = tshirt.toStringTxt();

        //Fazer acabar funçao toStringTxt
    }

    //getters e setters

    public Map<LocalTime, Versao> getListaVersoes() {
        return this.listaVersoes;
    }
    public void setListaVersoes(Map<LocalTime, Versao> listaVersoes) {
        this.listaVersoes = listaVersoes;
    }
}
