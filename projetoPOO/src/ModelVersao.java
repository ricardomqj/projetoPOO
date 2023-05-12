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

    public void addUserToTxt(Utilizador user, String versaoUserTxt) {
        String userTxt = user.toStringTxt();

        //Fazer toStringTxtUser e toStringTxtEncomenda
    }

    public void addTransportadoraTxt(Transportadora trans, String versaoTransportadorasTxt) {
        String transTxt = trans.toStringTxt();

        StringBuilder sb = new StringBuilder();
        if (versaoTransportadorasTxt.isEmpty()) {
            sb.append(transTxt).append("\n");
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

    /* VER se vale mais a pena fazer desta forma em vez de ter varios para cada tipo

    public void addArtigoTxt(Artigo artigo, String versaoArtigosTxt) {

        String artigoTxt = "";

        StringBuilder output = new StringBuilder();

        if (artigo instanceof Sapatilha) {
            Sapatilha sapatilha = (Sapatilha) artigo;
            artigoTxt = sapatilha.toStringTxt();
        } else if (artigo instanceof TShirt) {
            TShirt tshirt = (TShirt) artigo;
            artigoTxt = tshirt.toStringTxt();
        } else if (artigo instanceof Mala) {
            Mala mala = (Mala) artigo;
            artigoTxt = mala.toStringTxt();
        }
    }
    */

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
        //String pathEncomendas = "src/data.txt";

        try (FileWriter writer = new FileWriter(pathArtigos)) {
            writer.write(versaoatual.getVersaoArtigosTxt());
            //System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(pathUsers)) {
            writer.write(versaoatual.getVersaoUsersTxt());
            //System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(pathTransp)) {
            writer.write(versaoatual.getVersaoTransportadorasTxt());
            //System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try (FileWriter writer = new FileWriter(pathEncomendas)) {
            writer.write(versaoatual.getVersaoEncomendasTxt());
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        this.listaVersoes.put(versaoatual.getTime(), versaoatual);
    }

    /*
    public void saveVersao(String versaoUsersTxt) {
        String filepath = "src/data.txt"
    }

    */

    //getters e setters

    public Map<LocalTime, Versao> getListaVersoes() {
        return this.listaVersoes;
    }
    public void setListaVersoes(Map<LocalTime, Versao> listaVersoes) {
        this.listaVersoes = listaVersoes;
    }
}
