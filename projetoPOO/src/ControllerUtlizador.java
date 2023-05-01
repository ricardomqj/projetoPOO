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

    public boolean loginUtlizador(String email)
    {
        return modelUtlizador.loginUtlizador(email); //retorna se existe ou nao
    }

    public void setViewerUtlizador(ViewerUtlizador viewerUtlizador) {
        this.viewerUtlizador = viewerUtlizador;
    }

    public void setModelUtlizador(ModelUtlizador modelUtlizador) {
        this.modelUtlizador = modelUtlizador;
    }
}
