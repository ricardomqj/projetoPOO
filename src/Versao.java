import java.time.LocalTime;

public class Versao {
    private LocalTime time;
    private String versaoArtigosTxt;
    private String versaoUsersTxt;
    private String versaoTransportadorasTxt;
    private String versaoEncomendasTxt;

    public Versao() {
        this.time = null;
        this.versaoArtigosTxt = "";
        this.versaoUsersTxt = "";
        this.versaoTransportadorasTxt = "";
        this.versaoEncomendasTxt = "";
    }

    public Versao(String versaoArtigosTxt, String versaoUsersTxt, String versaoTransportadorasTxt, String versaoEncomendasTxt) {
        this.versaoArtigosTxt = versaoArtigosTxt;
        this.versaoUsersTxt = versaoUsersTxt;
        this.versaoTransportadorasTxt = versaoTransportadorasTxt;
        this.versaoEncomendasTxt = versaoEncomendasTxt;
    }

    public Versao(Versao umaVersao) {
        this.versaoArtigosTxt = umaVersao.getVersaoArtigosTxt();
        this.versaoUsersTxt = umaVersao.getVersaoUsersTxt();
        this.versaoTransportadorasTxt = umaVersao.getVersaoTransportadorasTxt();
        this.versaoEncomendasTxt = umaVersao.getVersaoEncomendasTxt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        //sb.append("Informações da Versão: \n");
        sb.append(super.toString());
        //sb.append("versaoTxt: ").append(this.versaoTxt).append("\n"); não acho necessario estar no toString
        sb.append("Hora de Criação: ").append(this.time.getHour()).append(":").append(this.time.getMinute()).append("\n");

        return sb.toString();
    }


    public LocalTime getTime() {
        return time;
    }
    public void setTime() {
        this.time = LocalTime.now();
    }

    public String getVersaoArtigosTxt() {
        return versaoArtigosTxt;
    }
    public void setVersaoArtigosTxt(String versaoArtigosTxt) {
        this.versaoArtigosTxt = versaoArtigosTxt;
    }

    public String getVersaoUsersTxt() {
        return versaoUsersTxt;
    }
    public void setVersaoUsersTxt(String versaoUsersTxt) {
        this.versaoUsersTxt = versaoUsersTxt;
    }

    public String getVersaoTransportadorasTxt() {
        return versaoTransportadorasTxt;
    }

    public void setVersaoTransportadorasTxt(String versaoTransportadorasTxt) {
        this.versaoTransportadorasTxt = versaoTransportadorasTxt;
    }

    public String getVersaoEncomendasTxt() {
        return versaoEncomendasTxt;
    }

    public void setVersaoEncomendasTxt(String versaoEncomendasTxt) {
        this.versaoEncomendasTxt = versaoEncomendasTxt;
    }
/*
    public String getVersaoEncomendasTxt() {
        return versaoEncomendasTxt;
    }

    public void setVersaoEncomendasTxt(String versaoEncomendasTxt) {
        this.versaoEncomendasTxt = versaoEncomendasTxt;
    }
 */
}
