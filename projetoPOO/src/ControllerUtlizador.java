import java.util.Scanner;

public class ControllerUtlizador {
    private ViewerUtlizador viewerUtlizador;
    private ModelUtlizador modelUtlizador;

    public ControllerUtlizador(ViewerUtlizador viewerUtlizador,ModelUtlizador modelUtlizador)
    {
        this.setModelUtlizador(modelUtlizador);
        this.setViewerUtlizador(viewerUtlizador);
    }

    public void criaUtlizador(String email,String nome,String morada,String nif)
    {
        this.modelUtlizador.criaUtlizador(email,nome,morada,nif);
    }

    public void loginUtlizador()
    public void setViewerUtlizador(ViewerUtlizador viewerUtlizador) {
        this.viewerUtlizador = viewerUtlizador;
    }

    public void setModelUtlizador(ModelUtlizador modelUtlizador) {
        this.modelUtlizador = modelUtlizador;
    }
}
