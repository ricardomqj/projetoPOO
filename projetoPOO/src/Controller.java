import java.time.LocalDate;
import java.util.Scanner;

public class Controller {
        ViewerUtlizador viewerUtlizador = new ViewerUtlizador();
        ModelUtlizador modelUtlizador = new ModelUtlizador();
        ControllerUtlizador controllerUtlizador = new ControllerUtlizador(viewerUtlizador,modelUtlizador);

        ViewerTransportadora viewerTransportadora = new ViewerTransportadora();
        ModelTransportadora modelTransportadora = new ModelTransportadora();
        ControllerTransportadora controllerTransportadora = new ControllerTransportadora(viewerTransportadora, modelTransportadora);

        ViewerArtigo viewerArtigo = new ViewerArtigo();
        ModelArtigo modelArtigo = new ModelArtigo();
        ControllerArtigo controllerArtigo = new ControllerArtigo(viewerArtigo,modelArtigo);
        public void menuInicial()
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1 - Criar um utlizador");                 //feito
            System.out.println("2 - Efetuar login com utlizador");        //feito
            System.out.println("3 - Criar uma transportadora");
            System.out.println("4 - Efetuar login com transportadora");
            System.out.println("5 - Funções gerais");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criaUtlizador();
                    break;
                case 2:
                    loginUtlizador();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
        }

        public void criaUtlizador() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.nextLine();

            System.out.println("Digite o nome do utilizador:");
            String nomeUtilizador = scanner.nextLine();

            System.out.println("Digite a morada do utilizador:");
            String moradaUtilizador = scanner.nextLine();

            System.out.println("Digite o NIF do utilizador:");
            String nifUtilizador = scanner.nextLine();

            controllerUtlizador.criaUtlizador(emailUtilizador,nomeUtilizador,moradaUtilizador,nifUtilizador);

            menuInicial();
        }

        public void loginUtlizador() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.next();

            Utilizador utilizador = controllerUtlizador.criaUtlizadorVazio();
            if(utilizador != null)
            {
                menuUtlizador(utilizador);
            }
            else
            {
                System.out.println("Esse email não está registado!");
                menuInicial();
            }
        }

        public void menuUtlizador(Utilizador utilizador)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1 - Vender um artigo");
            System.out.println("2 - Ver artigos à venda no momento");
            System.out.println("3 - Efetuar uma encomenda");
            System.out.println("4 - Devolver uma encomenda");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarArtigoParaVenda(utilizador);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    menuInicial();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
                    menuUtlizador(utilizador);
                    break;
            }
        }

        public void adicionarArtigoParaVenda(Utilizador utilizador) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que artigo deseja colocar à venda?");
            System.out.println("1 - Sapatilha");
            System.out.println("2 - T-Shirt");
            System.out.println("3 - Mala");
            System.out.println("4 - Outro");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registarSapatilha(utilizador);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador);
                    break;
                case 2:
                    registarTShirt(utilizador);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador);
                    break;
                case 3:
                    registarMala(utilizador);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador);
                    break;
                case 4:
                    //fazer ainda
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador);
                    break;
                default:
                    System.out.println("Essa opção não está diponível otário");
                    adicionarArtigoParaVenda(utilizador);
                    break;
            }
        }

            public void registarSapatilha(Utilizador utilizador){
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o código de barras do produto: ");
                String codBarras = scanner.next();

                System.out.println("Digite o preço de base do artigo: ");
                double precoBase = scanner.nextDouble();

                System.out.println("Digite a data de lançamento do artigo no formato yyyy-mm-dd: ");
                String dataString = scanner.next();

                LocalDate dataop = LocalDate.parse(dataString);

                System.out.println("Digite o nome da transportadora: ");
                String nomeTrans = scanner.next();

                while(!controllerTransportadora.loginTransportadora(nomeTrans)) {
                    System.out.println("Transportadora não encontrada");
                    System.out.println("Digite o nome de outra transportadora: ");
                    nomeTrans = scanner.next();
                }

                System.out.println("Insira o tamanho: ");
                int tamanhoSapatilha = scanner.nextInt();

                System.out.println("Sapatilha tem atacadores(true ou false)? ");
                boolean temAtacadores = scanner.nextBoolean();

                System.out.println("Cor da sapatilha? ");
                String cor = scanner.next();

                System.out.println("Sapatilha Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta == true) {
                    Sapatilha sapatilha = controllerArtigo.registarSapatilhaNova(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor);
                    controllerUtlizador.registarSapatilhaUser(utilizador, sapatilha);
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Sapatilha (0-5): ");
                    int avalEstado = scanner.nextInt();

                    System.out.println("Marca: ");
                    String marca = scanner.next();

                    System.out.println("Descrição: ");
                    String descricao = scanner.next();

                    System.out.println("Insira o desconto: "); // COMO É CALCULADO O DESCONTO???
                    int desconto = scanner.nextInt();

                    Sapatilha sapatilhausa = controllerArtigo.registarSapatilhaUsada(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);
                    controllerUtlizador.registarSapatilhaUsadaUser(utilizador, sapatilhausa);
                }


                //System.out.println("Sapatilha premium(true ou false)? ");
                //boolean isPremium = scanner.nextBoolean();

                //Sapatilha sap = new Sapatilha(codBarras, transArticle, stock, numDonos, avalEstado, precoBase, dataop1, desconto, tamanhoSpatilha, temAtacadores, cor, isPremium);
                //artigos.put(sap.getCodBarras(), sap.clone());
                //artigosVenda.put(sap.getCodBarras(), sap.clone());
            }

            public void registarMala(Utilizador utilizador) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o código de barras do produto: ");
                String codBarras = scanner.next();

                System.out.println("Digite o preço de base do artigo: ");
                double precoBase = scanner.nextDouble();

                System.out.println("Digite a data de lançamento do artigo no formato yyyy/mm/dd: ");
                String dataString = scanner.next();

                LocalDate dataop = LocalDate.parse(dataString);

                System.out.println("Digite o nome da transportadora: ");
                String nomeTrans = scanner.next();

                while(controllerTransportadora.loginTransportadora(nomeTrans) == false) {
                    System.out.println("Transportadora não encontrada");
                    System.out.println("Digite o nome de outra transportadora: ");
                    nomeTrans = scanner.next();
                }

                System.out.println("Tamanho da Mala (S/M/L): ");
                String tamanho = scanner.next();

                System.out.println("Material da Mala: ");
                String material = scanner.next();

                System.out.println("Ano da coleção: ");
                int anoColecao = scanner.nextInt();

                System.out.println("Mala Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta == true) {
                    Mala mala = controllerArtigo.registarMalaNova(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao);
                    controllerUtlizador.registarMalaUser(utilizador, mala);
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Mala (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Mala malausa = controllerArtigo.registarMalaUsada(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao, numDonos, avalEstado);
                    controllerUtlizador.registarMalaUsadaUser(utilizador, malausa);
                }
            }

            // FEITO CARALHO
            public void registarTShirt(Utilizador utilizador) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o código de barras do produto: ");
                String codBarras = scanner.next();

                System.out.println("Digite o preço de base do artigo: ");
                double precoBase = scanner.nextDouble();

                System.out.println("Digite a data de lançamento do artigo no formato yyyy/mm/dd: ");
                String dataString = scanner.next();

                LocalDate dataop = LocalDate.parse(dataString);

                System.out.println("Digite o nome da transportadora: ");
                String nomeTrans = scanner.next();

                while (!controllerTransportadora.loginTransportadora(nomeTrans)) {
                    System.out.println("Transportadora não encontrada");
                    System.out.println("Digite o nome de outra transportadora: ");
                    nomeTrans = scanner.next();
                }

                int tamanho;
                do {
                    System.out.println("Tamanho da TShirt (S/M/L/XL): ");
                    System.out.println("1 - S");
                    System.out.println("2 - M");
                    System.out.println("3 - L");
                    System.out.println("4 - XL");
                    tamanho = scanner.nextInt();
                    if(tamanho < 1 || tamanho > 4)
                    {
                        System.out.println("Essa opção não está diponível");
                    }
                } while(tamanho < 1 || tamanho > 4);

                int padrao;
                do {
                    System.out.println("Padrao da TShirt: \n(LISO, RISCAS ou PALMEIRAS)");
                    System.out.println("1 - LISO");
                    System.out.println("2 - RISCAS");
                    System.out.println("3 - PALMEIRAS");
                    padrao = scanner.nextInt();
                    if(padrao < 1 || padrao > 3){
                        System.out.println("Essa opção não está diponível");
                    }
                }while(padrao < 1 || padrao > 3);

                System.out.println("TShirt Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta) {
                    TShirt tshirt = controllerArtigo.registarTShirtNova(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, padrao);
                    controllerUtlizador.registarTShirtUser(utilizador, tshirt);

                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da TShirt (0-5): ");
                    int avalEstado = scanner.nextInt();

                    TShirt tshirtusa = controllerArtigo.registarTShirtUsada(utilizador, codBarras, dataop, nomeTrans, precoBase, tamanho, padrao, numDonos, avalEstado);
                    controllerUtlizador.registarTShirtUsadaUser(utilizador, tshirtusa);
                }
        }

        /*
        public void  registarSapatilha(){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o código de barras do produto: ");
            String codBarras = scanner.next();

            System.out.println("Digite o preço de base do artigo: ");
            double precoBase = scanner.nextDouble();

            System.out.println("Digite o data no formato yyyy/mm/dd: ");
            String dataString = scanner.next();

            LocalDate dataop1 = LocalDate.parse(dataString);
            System.out.println("Digite o nome da transportadora: ");
            String nomeTrans = scanner.next();

            Transportadora transArticle = transportadoras.get(nomeTrans);
            if(transArticle == null) {
                System.out.println("Transportadora não encontrada");
            }

            System.out.println("Insira o tamanho: ");
            int tamanhoSpatilha = scanner.nextInt();

            System.out.println("Sapatilha tem atacadores(true ou false)? ");
            boolean temAtacadores = scanner.nextBoolean();

            System.out.println("Cor da sapatilha? ");
            String cor = scanner.next();

            System.out.println("Sapatilha premium(true ou false)? ");
            boolean isPremium = scanner.nextBoolean();

            Sapatilha sap = new Sapatilha(codBarras, transArticle, stock, numDonos, avalEstado, precoBase, dataop1, desconto, tamanhoSpatilha, temAtacadores, cor, isPremium);
            artigos.put(sap.getCodBarras(), sap.clone());
            artigosVenda.put(sap.getCodBarras(), sap.clone());
        }

        /*

    public void criaEncomendaController() {

        Scanner scanner = new Scanner(System.in);

        Map<String, Artigo> artigos = new HashMap<>();

        System.out.println("Digite o email associado ao utilizador:");
        String emailUser = scanner.next();

        ModelEncomenda.criaEncomendaModel(emailUser);

        addArtigoEncomendaController(artigos);

        menu();
    }


    public void addArtigoEncomendaController(Map<String, Artigo> artigos) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código de barras do Artigo que deseja adicionar: ");
        String codBarras = scanner.next();

        //Artigo retrievedArtigo = Model.getListaArtigos.get(codBarras);

        ModelEncomenda.addArtigoEncomendaModel(artigos, codBarras);

        System.out.println("Deseja adicionar mais algum artigo? (S/N)");
        String simnao = scanner.next();

        if (simnao.equals("S")) addArtigoEncomendaController(artigos);

    }















/*
            switch (opcao) {
                case 1:
                    int numArtigosVenda, numArtigosAdquiridos;
                    System.out.println("Digite o código do utilizador:");
                    String codigoUtilizador = scanner.nextLine();

                    System.out.println("Digite o email do utilizador:");
                    String emailUtilizador = scanner.nextLine();

                    System.out.println("Digite o nome do utilizador:");
                    String nomeUtilizador = scanner.nextLine();

                    System.out.println("Digite a morada do utilizador:");
                    String moradaUtilizador = scanner.nextLine();

                    System.out.println("Digite o NIF do utilizador:");
                    String nifUtilizador = scanner.nextLine();

                    System.out.println("Digite o número de artigos à venda do utilizador: ");
                    numArtigosVenda = scanner.nextInt();

                    System.out.println("Digite o número de artigos adquiridos: ");
                    numArtigosAdquiridos = scanner.nextInt();;
                    int i = 0;
                    HashMap<String, Artigo> artigosVenda = new HashMap<>();
                    HashMap<String, Artigo> artigosAdquiridos = new HashMap<>();
                    while(i < numArtigosVenda) {
                        int opcao2;
                        System.out.println("Que produto deseja inserir na lista de artigos vendidos? ");
                        System.out.println("1. Sapatilha");
                        System.out.println("2. T-Shirt");
                        System.out.println("3. Mala");
                        opcao2 = scanner.nextInt();
                        switch (opcao2) {
                            case 1:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString = scanner.next();
                                LocalDate dataop1 = LocalDate.parse(dataString);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans = scanner.next();
                                Transportadora transArticle = transportadoras.get(nomeTrans);
                                if(transArticle == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Insira o tamanho: ");
                                int tamanhoSpatilha = scanner.nextInt();
                                System.out.println("Sapatilha tem atacadores(true ou false)? ");
                                boolean temAtacadores = scanner.nextBoolean();
                                System.out.println("Cor da sapatilha? ");
                                String cor = scanner.next();
                                System.out.println("Sapatilha premium(true ou false)? ");
                                boolean isPremium = scanner.nextBoolean();
                                Sapatilha sap = new Sapatilha(codBarras, transArticle, stock, numDonos, avalEstado, precoBase, dataop1, desconto, tamanhoSpatilha, temAtacadores, cor, isPremium);
                                artigos.put(sap.getCodBarras(), sap.clone());
                                artigosVenda.put(sap.getCodBarras(), sap.clone());
                            case 2:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras2 = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock2 = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos2 = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado2 = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase2 = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto2 = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString2 = scanner.next();
                                LocalDate dataop2 = LocalDate.parse(dataString2);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans2 = scanner.next();
                                Transportadora transArticle2 = transportadoras.get(nomeTrans2);
                                if(transArticle2 == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Padrão da T-Shirt(liso, riscas, palmeiras): ");
                                String pattern = scanner.next().toUpperCase();
                                TShirt.Padrao patt = TShirt.Padrao.valueOf(pattern);
                                System.out.println("Tamanho da T-SHIRT (xs, s, m, l, xl, xxl, xxxl):");
                                String tam = scanner.next().toUpperCase();
                                TShirt.Tamanho tamanho = TShirt.Tamanho.valueOf(tam);
                                TShirt tshirt = new TShirt(codBarras2, transArticle2, stock2, numDonos2, avalEstado2, precoBase2, desconto2, dataop2, tamanho, patt);
                                artigos.put(tshirt.getCodBarras(), tshirt.clone());
                                artigosVenda.put(tshirt.getCodBarras(), tshirt.clone());


                            case 3:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras3 = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock3 = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos3 = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado3 = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase3 = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto3 = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString3 = scanner.next();
                                LocalDate dataop3 = LocalDate.parse(dataString3);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans3 = scanner.next();
                                Transportadora transArticle3 = transportadoras.get(nomeTrans3);
                                if(transArticle3 == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Largura da mala: ");
                                double largura = scanner.nextDouble();
                                System.out.println("Altura da mala: ");
                                double altura = scanner.nextDouble();
                                System.out.println("Profundidade da Mala: ");
                                double profundidade = scanner.nextDouble();
                                System.out.println("Mala premium(true ou false): ");
                                boolean isPremium2 = scanner.nextBoolean();
                                Mala mala = new Mala(codBarras3, transArticle3, stock3, numDonos3, avalEstado3, precoBase3, desconto3, dataop3, largura, altura, profundidade, isPremium2);
                                artigos.put(mala.getCodBarras(), mala.clone());
                                artigosVenda.put(mala.getCodBarras(), mala.clone());
                            default:
                                System.out.println("Tipo de artigo inválido");
                                break;
                        }
                        Utilizador user = new Utilizador(codigoUtilizador, emailUtilizador, nomeUtilizador, moradaUtilizador, nifUtilizador, artigosVenda, artigosAdquiridos);
                    }

                    while(i < numArtigosAdquiridos) {
                        int opcao2;
                        System.out.println("Que produto deseja inserir na lista de artigos adquiridos? ");
                        System.out.println("1. Sapatilha");
                        System.out.println("2. T-Shirt");
                        System.out.println("3. Mala");
                        opcao2 = scanner.nextInt();
                        switch (opcao2) {
                            case 1:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString = scanner.next();
                                LocalDate dataop1 = LocalDate.parse(dataString);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans = scanner.next();
                                Transportadora transArticle = transportadoras.get(nomeTrans);
                                if(transArticle == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Insira o tamanho: ");
                                int tamanhoSpatilha = scanner.nextInt();
                                System.out.println("Sapatilha tem atacadores(true ou false)? ");
                                boolean temAtacadores = scanner.nextBoolean();
                                System.out.println("Cor da sapatilha? ");
                                String cor = scanner.next();
                                System.out.println("Sapatilha premium(true ou false)? ");
                                boolean isPremium = scanner.nextBoolean();
                                Sapatilha sap = new Sapatilha(codBarras, transArticle, stock, numDonos, avalEstado, precoBase, dataop1, desconto, tamanhoSpatilha, temAtacadores, cor, isPremium);
                                artigos.put(sap.getCodBarras(), sap.clone());
                                artigosAdquiridos.put(sap.getCodBarras(), sap.clone());
                                break;
                            case 2:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras2 = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock2 = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos2 = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado2 = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase2 = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto2 = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString2 = scanner.next();
                                LocalDate dataop2 = LocalDate.parse(dataString2);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans2 = scanner.next();
                                Transportadora transArticle2 = transportadoras.get(nomeTrans2);
                                if(transArticle2 == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Padrão da T-Shirt(liso, riscas, palmeiras): ");
                                String pattern = scanner.next().toUpperCase();
                                TShirt.Padrao patt = TShirt.Padrao.valueOf(pattern);
                                System.out.println("Tamanho da T-SHIRT (xs, s, m, l, xl, xxl, xxxl):");
                                String tam = scanner.next().toUpperCase();
                                TShirt.Tamanho tamanho = TShirt.Tamanho.valueOf(tam);
                                TShirt tshirt = new TShirt(codBarras2, transArticle2, stock2, numDonos2, avalEstado2, precoBase2, desconto2, dataop2, tamanho, patt);
                                artigos.put(tshirt.getCodBarras(), tshirt.clone());
                                artigosAdquiridos.put(tshirt.getCodBarras(), tshirt.clone());
                                break;

                            case 3:
                                System.out.println("Digite o código de barras do produto: ");
                                String codBarras3 = scanner.next();
                                System.out.println("Stock do produto: ");
                                int stock3 = scanner.nextInt();
                                System.out.println("Número de donos anteriores: ");
                                int numDonos3 = scanner.nextInt();
                                System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                int avalEstado3 = scanner.nextInt();
                                System.out.println("Digite o preço de base do artigo: ");
                                double precoBase3 = scanner.nextDouble();
                                System.out.println("Digite o valor do desconto de 0 a 100: ");
                                int desconto3 = scanner.nextInt();
                                System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                String dataString3 = scanner.next();
                                LocalDate dataop3 = LocalDate.parse(dataString3);
                                System.out.println("Digite o nome da transportadora: ");
                                String nomeTrans3 = scanner.next();
                                Transportadora transArticle3 = transportadoras.get(nomeTrans3);
                                if(transArticle3 == null) {
                                    System.out.println("Transportadora não encontrada");
                                }
                                System.out.println("Largura da mala: ");
                                double largura = scanner.nextDouble();
                                System.out.println("Altura da mala: ");
                                double altura = scanner.nextDouble();
                                System.out.println("Profundidade da Mala: ");
                                double profundidade = scanner.nextDouble();
                                System.out.println("Mala premium(true ou false): ");
                                boolean isPremium2 = scanner.nextBoolean();
                                Mala mala = new Mala(codBarras3, transArticle3, stock3, numDonos3, avalEstado3, precoBase3, desconto3, dataop3, largura, altura, profundidade, isPremium2);
                                artigos.put(mala.getCodBarras(), mala.clone());
                                artigosAdquiridos.put(mala.getCodBarras(), mala.clone());
                                break;
                            default:
                                System.out.println("Tipo de artigo inválido");
                                continue;
                        }
                        Utilizador user = new Utilizador(codigoUtilizador, emailUtilizador, nomeUtilizador, moradaUtilizador, nifUtilizador, artigosVenda, artigosAdquiridos);
                        utilizadores.put(user.getNif(), user.clone());
                    }

                case 2:
                    System.out.println("Que produto deseja inserir na lista de artigos adquiridos? ");
                    System.out.println("1. Sapatilha");
                    System.out.println("2. T-Shirt");
                    System.out.println("3. Mala");
                    int escolha = scanner.nextInt();
                    switch (escolha) {
                        case 1:

                            System.out.println("Qual é o estado da Sapatilha?");
                            System.out.println("1 -> Sapatilha nova");
                            System.out.println("2 -> Sapatilha usada");
                            int choseEstado = scanner.nextInt();
                            switch(choseEstado) {
                                case 1:
                                    System.out.println("Digite o código de barras da sapatilha: ");
                                    String codBarras = scanner.next();
                                    System.out.println("Stock da sapatilha: ");
                                    int stock = scanner.nextInt();
                                    System.out.println("Número de donos anteriores: ");
                                    int numDonos = scanner.nextInt();
                                    System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                                    int avalEstado = scanner.nextInt();
                                    System.out.println("Digite o preço de base da sapatilha: ");
                                    double precoBase = scanner.nextDouble();
                                    System.out.println("Digite o valor do desconto de 0 a 100: ");
                                    int desconto = scanner.nextInt();
                                    System.out.println("Digite o data no formato yyyy/mm/dd: ");
                                    String dataString = scanner.next();
                                    LocalDate dataop1 = LocalDate.parse(dataString);
                                    System.out.println("Digite o nome da transportadora: ");
                                    String nomeTrans = scanner.next();
                                    Transportadora transArticle1 = ger.getListaTransportadoras().get(nomeTrans);
                                    if(transArticle1 == null) {
                                        System.out.println("Transportadora não encontrada");
                                    }
                                    System.out.println("Insira o tamanho: ");
                                    int tamanhoSpatilha = scanner.nextInt();
                                    System.out.println("Sapatilha tem atacadores(true ou false)? ");
                                    boolean temAtacadores = scanner.nextBoolean();
                                    System.out.println("Cor da sapatilha? ");
                                    String cor = scanner.next();
                                    Sapatilha sap = new Sapatilha(codBarras, stock, dataop1, transArticle1, precoBase, tamanhoSpatilha, temAtacadores, cor);
                                    ger.addArtigo(sap);
                                    break;
                                case 2:
                                    System.out.println("Digite o código de barras da sapatilha usada: ");
                                    String codBarras1 = scanner.next();
                                    System.out.println("Digite o stock: ");
                                    int stock1 = scanner.nextInt();
                                    System.out.println("Insira a data de lançamento no formato yyyy/mm/dd: ");
                                    String dataString1 = scanner.next();
                                    LocalDate dataLanc = LocalDate.parse(dataString1);
                                    System.out.println("Nome da transportadora: ");
                                    String transName = scanner.next();
                                    Transportadora transUsedSap = ger.getListaTransportadoras().get(transName);
                                    if(transUsedSap == null)
                                        System.out.println("Transportadora não encontrada!");
                                    System.out.println("Insira o preço base da sapatilha usada: ");
                                    double precoBaseUsedSap = scanner.nextInt();
                                    System.out.println("Tamanho da sapatilha: ");
                                    int tamUsedSap = scanner.nextInt();
                                    System.out.println("Sapatilha tem atacadores? ");
                                    boolean atacUsedSap = scanner.nextBoolean();
                                    System.out.println("Cor da sapatilha: ");
                                    String corUsedSap = scanner.next();
                                    System.out.println("Número de donos anteriores: ");
                                    int numDonosUsedSap = scanner.nextInt();
                                    System.out.println("Avaliação do estado(1 a 5): ");
                                    int avlEstadoUsedSap = scanner.nextInt();
                                    System.out.println("Desconto da sapatilha(0 a 100): ");
                                    int descUsedSap = scanner.nextInt();
                                    SapatilhaUsada usedSap = new SapatilhaUsada(codBarras1, stock1, dataLanc, transUsedSap, precoBaseUsedSap, tamUsedSap, atacUsedSap, )
                                    ger.addArtigo(usedSap);
                            }

                        case 2:
                            System.out.println("Digite o código de barras do produto: ");
                            String codBarras2 = scanner.next();
                            System.out.println("Stock do produto: ");
                            int stock2 = scanner.nextInt();
                            System.out.println("Número de donos anteriores: ");
                            int numDonos2 = scanner.nextInt();
                            System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                            int avalEstado2 = scanner.nextInt();
                            System.out.println("Digite o preço de base do artigo: ");
                            double precoBase2 = scanner.nextDouble();
                            System.out.println("Digite o valor do desconto de 0 a 100: ");
                            int desconto2 = scanner.nextInt();
                            System.out.println("Digite o data no formato yyyy/mm/dd: ");
                            String dataString2 = scanner.next();
                            LocalDate dataop2 = LocalDate.parse(dataString2);
                            System.out.println("Digite o nome da transportadora: ");
                            String nomeTrans2 = scanner.next();
                            Transportadora transArticle2 = transportadoras.get(nomeTrans2);
                            if(transArticle2 == null) {
                                System.out.println("Transportadora não encontrada");
                            }
                            System.out.println("Padrão da T-Shirt(liso, riscas, palmeiras): ");
                            String pattern = scanner.next().toUpperCase();
                            TShirt.Padrao patt = TShirt.Padrao.valueOf(pattern);
                            System.out.println("Tamanho da T-SHIRT (xs, s, m, l, xl, xxl, xxxl):");
                            String tam = scanner.next().toUpperCase();
                            TShirt.Tamanho tamanho = TShirt.Tamanho.valueOf(tam);
                            TShirt tshirt = new TShirt(codBarras2, transArticle2, stock2, numDonos2, avalEstado2, precoBase2, desconto2, dataop2, tamanho, patt);
                            artigos.put(tshirt.getCodBarras(), tshirt.clone());
                            break;

                        case 3:
                            System.out.println("Digite o código de barras do produto: ");
                            String codBarras3 = scanner.next();
                            System.out.println("Stock do produto: ");
                            int stock3 = scanner.nextInt();
                            System.out.println("Número de donos anteriores: ");
                            int numDonos3 = scanner.nextInt();
                            System.out.println("Avaliação 1 a 5(sendo 1 novo e 5 não utilizáveis): ");
                            int avalEstado3 = scanner.nextInt();
                            System.out.println("Digite o preço de base do artigo: ");
                            double precoBase3 = scanner.nextDouble();
                            System.out.println("Digite o valor do desconto de 0 a 100: ");
                            int desconto3 = scanner.nextInt();
                            System.out.println("Digite o data no formato yyyy/mm/dd: ");
                            String dataString3 = scanner.next();
                            LocalDate dataop3 = LocalDate.parse(dataString3);
                            System.out.println("Digite o nome da transportadora: ");
                            String nomeTrans3 = scanner.next();
                            Transportadora transArticle3 = transportadoras.get(nomeTrans3);
                            if(transArticle3 == null) {
                                System.out.println("Transportadora não encontrada");
                            }
                            System.out.println("Largura da mala: ");
                            double largura = scanner.nextDouble();
                            System.out.println("Altura da mala: ");
                            double altura = scanner.nextDouble();
                            System.out.println("Profundidade da Mala: ");
                            double profundidade = scanner.nextDouble();
                            System.out.println("Mala premium(true ou false): ");
                            boolean isPremium2 = scanner.nextBoolean();
                            Mala mala = new Mala(codBarras3, transArticle3, stock3, numDonos3, avalEstado3, precoBase3, desconto3, dataop3, largura, altura, profundidade, isPremium2);
                            artigos.put(mala.getCodBarras(), mala.clone());
                            break;
                        default:
                            System.out.println("Tipo de artigo inválido");
                            continue;
                    }
                case 3:
                    System.out.println("Digite o nome da transportadora:");
                    String nomeTransportadoraCriar = scanner.nextLine();

                    System.out.println("Digite o valor base da transportadora:");
                    double valorBaseTransportadora = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite a margem de lucro da transportadora:");
                    double margemLucroTransportadora = scanner.nextDouble();
                    scanner.nextLine();

                    Transportadora transportadora = new Transportadora(nomeTransportadoraCriar, valorBaseTransportadora,
                            margemLucroTransportadora);
                    transportadoras.put(nomeTransportadoraCriar, transportadora);
                    System.out.println("Transportadora criada com sucesso!");
                    break;
                case 4:
                    System.out.println("Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida!");


            }
        }

}

 */
}
