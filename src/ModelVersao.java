import java.io.BufferedWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class ModelVersao {
    private Map<LocalTime, Versao> listaVersoes;
    public ModelVersao() {
        this.listaVersoes = new HashMap<LocalTime, Versao>();
    }

    // METHODS

    public void carregaVersao(Versao versaoatual) {

    }

    public Versao getVersaoByTime(LocalTime time) {
        for (Versao versao : this.listaVersoes.values()) {
            if(versao.getTime().equals(time)) {
                return versao;
            }
        }
        System.out.println("Não foi efetuado o carregamento de qualquer versão, tente novamente.");
        return new Versao();
    }

    public void addUserToTxt(Utilizador user, Versao versaoatual) {
        String userTxt = user.toStringTxt();

        //Fazer toStringTxtUser e toStringTxtEncomenda
    }

    public void addTransportadoraTxt(Transportadora trans, Versao versaoatual) {
        String transTxt = trans.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoatual.getVersaoTransportadorasTxt().equals("")) {
            sb.append(transTxt).append("\n");
        } else {
            sb.append(versaoatual.getVersaoTransportadorasTxt()).append(transTxt);
        }

        versaoatual.setVersaoTransportadorasTxt(sb.toString());

    }
    public void addEncomendaTxt(Encomenda enc, Versao versaoatual) { // adicionei
        String transTxt = enc.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoatual.getVersaoEncomendasTxt().equals("")) {
            sb.append(transTxt).append("\n");
        } else {
            sb.append(versaoatual.getVersaoEncomendasTxt()).append(transTxt);
        }
        versaoatual.setVersaoEncomendasTxt(sb.toString());
    }

    public void addSapatilhaTxt(Sapatilha tilha, Versao versaoatual) {
        String sapatilhaTxt = tilha.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoatual.getVersaoArtigosTxt().equals("")) {
            sb.append(sapatilhaTxt);
        } else {
            sb.append(versaoatual.getVersaoArtigosTxt()).append(sapatilhaTxt);
        }

        versaoatual.setVersaoArtigosTxt(sb.toString());
    }

    public void addMalaTxt(Mala mala, Versao versaoatual) {
        String malaTxt = mala.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoatual.getVersaoArtigosTxt().equals("")) {
            sb.append(malaTxt);
        } else {
            sb.append(versaoatual.getVersaoArtigosTxt()).append(malaTxt);
        }

        versaoatual.setVersaoArtigosTxt(sb.toString());
    }

    public void addTShirtTxt(TShirt tshirt, Versao versaoatual) {
        String tshirtTxt = tshirt.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoatual.getVersaoArtigosTxt().equals("")) {
            sb.append(tshirtTxt);
        } else {
            sb.append(versaoatual.getVersaoArtigosTxt()).append(tshirtTxt);
        }

        versaoatual.setVersaoArtigosTxt(sb.toString());
    }

    // ATUALIZA TXTS

    public void atualizaTransportadoraTxt(Transportadora trans, String versaoTransportadorasTxt) {
        String transTxt = trans.toStringTxt();

        StringBuilder output = new StringBuilder();

        for (String line : versaoTransportadorasTxt.split("\n")) {
            String[] fields = line.split(":");
            if (fields[0].equals(trans.getNome())) {
                output.append(transTxt).append("\n");
            } else {
                output.append(line).append("\n");
            }
        }
    }

    public void atualizaArtigoTxt(Artigo artigo, String versaoArtigosTxt) {

        String artigoTxt = "";

        StringBuilder output = new StringBuilder();

        if(artigo instanceof Sapatilha) {
            Sapatilha sapatilha = (Sapatilha) artigo;
            artigoTxt = sapatilha.toStringTxt();
            System.out.println("ARTIGO TXT: ");
            System.out.println(artigoTxt);
        } else if (artigo instanceof TShirt) {
            TShirt tshirt = (TShirt) artigo;
            artigoTxt = tshirt.toStringTxt();
        } else if (artigo instanceof Mala) {
            Mala mala = (Mala) artigo;
            artigoTxt = mala.toStringTxt();
        }

        for (String line : versaoArtigosTxt.split("\n")) {
            String[] fields = line.split(":");
            if (fields[0].equals(artigo.getCodBarras())) {
                output.append(artigoTxt).append("\n");
            } else {
                output.append(line).append("\n");
            }
        }

    }

    public void atualizaUserTxt(Utilizador userComprador, String versaoUsersTxt) {
        String userTxt = userComprador.toStringTxt();

        StringBuilder output = new StringBuilder();

        for (String line : versaoUsersTxt.split("\n")) {
            String[] fields = line.split(":");
            if (fields[0].equals(userComprador.getCodigoSistema())) {
                output.append(userTxt).append("\n");
            } else {
                output.append(line).append("\n");
            }
        }
    }

    public void saveVersao(Versao versaoatual) {
        String pathArtigos = "src/artigos.txt";
        String pathUsers = "src/utilizadores.txt";
        String pathTransp = "src/transportadoras.txt";
        String pathEncomendas = "src/encomendas.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathArtigos));
            writer.write(versaoatual.getVersaoArtigosTxt());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathUsers));
            writer.write(versaoatual.getVersaoUsersTxt());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathTransp));
            writer.write(versaoatual.getVersaoTransportadorasTxt());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathEncomendas));
            writer.write(versaoatual.getVersaoEncomendasTxt());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listaVersoes.put(versaoatual.getTime(), versaoatual.clone());
    }

    //getters e setters

    public Map<LocalTime, Versao> getListaVersoes() {
        return this.listaVersoes;
    }
    public void setListaVersoes(Map<LocalTime, Versao> listaVersoes) {
        this.listaVersoes = listaVersoes;
    }
}
