import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

        ViewerEncomenda viewerEncomenda = new ViewerEncomenda();
        ModelEncomenda modelEncomenda = new ModelEncomenda();
        ControllerEncomenda controllerEncomenda = new ControllerEncomenda(viewerEncomenda,modelEncomenda);

        ViewerVersao viewerVersao = new ViewerVersao();
        ModelVersao modelVersao = new ModelVersao();
        ControllerVersao controllerVersao = new ControllerVersao(viewerVersao,modelVersao);

        ViewerQueries viewerQueries = new ViewerQueries();
        ModelQueries modelQueries = new ModelQueries();
        ControllerQueries controllerQueries = new ControllerQueries(viewerQueries,modelQueries);

        public void menuInicial()
        {
            Scanner scanner = new Scanner(System.in);

            //ver como se vai fazer acerca de quando se dá estes loads

            String versaoUsersTxt = controllerUtlizador.loadUtilizadores();
            String versaoTransportadorasTxt = controllerTransportadora.loadTransportadoras();
            String versaoArtigosTxt = controllerArtigo.loadArtigos();
            //String versaoEncomendasTxt = controllerEncomenda.loadEncomendas();
            String versaoEncomendasTxt = null;

            Versao versaoAtual = new Versao(versaoArtigosTxt, versaoUsersTxt, versaoTransportadorasTxt, versaoEncomendasTxt); // a data de criação é definida aqui caralho gt3 gt3 quero...

            System.out.println("O que deseja fazer?");
            System.out.println(" 1 - Criar um utlizador");                         //feito
            System.out.println(" 2 - Efetuar login com utlizador");                //feito
            System.out.println(" 3 - Criar uma transportadora");
            System.out.println(" 4 - Efetuar login com transportadora");
            System.out.println(" 5 - Funções gerais");
            System.out.println(" 6 - Ver lista de transportadoras disponíveis");
            System.out.println(" 7 - Ver lista de utilizadores criados");
            System.out.println(" 8 - Ver lista de encomendas");
            System.out.println(" 9 - Load & Save");
            System.out.println("10 - ADMIN");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criaUtlizador(versaoAtual);
                    break;
                case 2:
                    loginUtlizador(versaoAtual);
                    break;
                case 3 :
                    criaTransportadora(versaoAtual);
                    break;
                case 4:
                    loginTransportadora(versaoAtual);
                    break;
                case 6:
                    infosTransportadoras();
                    break;
                case 7:
                    infosTodosUsers();
                    break;
                case 8:
                    infoTodasEncomendas();
                    break;
                case 9:
                    menuLoadSave(versaoAtual);
                    break;
                case 10:
                    menuQueries();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
        }


        // Funções do user

        private void menuQueries() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que query deseja executar?");
            System.out.println("1 - query 1");
            System.out.println("2 - query 2");
            System.out.println("3 - query 3");
            System.out.println("4 - query 4");
            System.out.println("5 - query 5");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    System.out.println("Email do vendedor que pretende analisar: ");
                    String email = scanner.next();

                    Map<String, Encomenda> encsVendedor = controllerQueries.encsVendedor(controllerEncomenda.getListaTodasEncomendas(), controllerUtlizador.getUserByEmail(email));
                    String encsVendedorString = controllerQueries.encsVendedorToString(encsVendedor);
                    System.out.println(encsVendedorString);
                    break;
                case 4:

                    break;
                case 5:
                    double lucro = controllerQueries.vintageProfit(controllerEncomenda.getListaTodasEncomendas());
                    System.out.println("A vintage ganhou " + Double.toString(lucro) + " durante o seu funcionamento.");
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
        }

        private void menuLoadVersoes() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que versão deseja carregar?");

            Map<LocalTime, Versao> listaVersoes = controllerVersao.getListaVersoes();               //vai buscar o map de todas as versoes;
            String listaVersoesString = controllerVersao.getListaVersoesToString(listaVersoes);    //vai ao viewer transformar tudo isto numa string só;
            System.out.println(listaVersoesString);                                               //apresenta aqui todas as versoes por ordem crescente exemplo:

            //Versão - 20:18
            //Versão - 20:23
            //Versão - 21:01
            //Versão - 22:03

            System.out.println("Coloque a hora da versão que pretende carregar (hh:mm): "); //Coloque a hora da versão que pretende carregar (hh:mm)

            int opcao = scanner.nextInt();
            scanner.nextLine();

            // VER COMO FAZER ESTA PARTE

            /*
            switch (opcao) {
                case 1:

                    break;
                case 2:
                    menuLoadVersoes();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
            */
        }

        private void menuLoadSave(Versao versaoAtual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1 - Dar save da versão atual");
            System.out.println("2 - Dar load de uma versão");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    controllerVersao.saveVersao(versaoAtual);
                    //saveVersao(versaoAtual);
                    break;
                case 2:
                    menuLoadVersoes();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
        }


        //apagar
        private void saveVersao(Versao versaoAtual) {

            //controllerVersao.saveEncomendas(versaoAtual.getVersaoEncomendasTxt());
            //controllerVersao.saveArtigos(versaoAtual.getVersaoArtigosTxt());
            //controllerVersao.saveTransportadoras(versaoAtual.getVersaoTransportadorasTxt());
            //controllerVersao.saveUtilizadores(versaoAtual.getVersaoUsersTxt());

            controllerVersao.saveVersao(versaoAtual);
        }

        private void criaUtlizador(Versao versaoAtual) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.nextLine();

            System.out.println("Digite o nome do utilizador:");
            String nomeUtilizador = scanner.nextLine();

            System.out.println("Digite a morada do utilizador:");
            String moradaUtilizador = scanner.nextLine();

            System.out.println("Digite o NIF do utilizador:");
            String nifUtilizador = scanner.nextLine();

            Utilizador user = controllerUtlizador.criaUtlizador(emailUtilizador,nomeUtilizador,moradaUtilizador,nifUtilizador);
            controllerVersao.addUserToTxt(user, versaoAtual.getVersaoUsersTxt());

            menuInicial();
        }

        private void loginUtlizador(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.next();

            Utilizador utilizador = controllerUtlizador.getUserByEmail(emailUtilizador);
            if(utilizador != null)
            {
                menuUtlizador(utilizador, versaoatual);
            }
            else
            {
                System.out.println("Esse email não está registado!");
                menuInicial();
            }
        }

        private void menuUtlizador(Utilizador utilizador, Versao versaoatual)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1 - Vender um artigo.");
            System.out.println("2 - Ver artigos à venda no momento.");
            System.out.println("3 - Efetuar uma encomenda.");
            System.out.println("4 - Devolver uma encomenda.");
            System.out.println("5 - Consultar informações do utilizador.");
            System.out.println("6 - Sair.");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarArtigoParaVenda(utilizador, versaoatual);
                    break;
                case 2:
                    menuArtigosAvenda(utilizador, versaoatual);
                    break;
                case 3:
                    efetuaEncomenda(utilizador, versaoatual);
                    break;
                case 4:

                    break;

                case 5:
                    infoUser(utilizador.getEmail(), versaoatual);
                    break;
                case 6:
                    menuInicial();
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
                    menuUtlizador(utilizador, versaoatual);
                    break;
            }
        }

        public void infoTodasEncomendas(){
            System.out.println(controllerEncomenda.infoTodasEncomendas());
        }

        private void infosTodosUsers() {
            System.out.println(controllerUtlizador.infoTodosUsers());
            menuInicial();
        }

        private void infoUser(String email, Versao versaoatual) {
            System.out.println(controllerUtlizador.infoUserByEmail(email));
            menuUtlizador(controllerUtlizador.getUserByEmail(email), versaoatual);
        }
        
        private void adicionarArtigoParaVenda(Utilizador utilizador, Versao versaoatual) {
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
                    registarSapatilha(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 2:
                    registarTShirt(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 3:
                    registarMala(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 4:
                    //fazer ainda
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível otário");
                    adicionarArtigoParaVenda(utilizador, versaoatual);
                    break;
            }
        }

            private void registarSapatilha(Utilizador utilizador, Versao versaoatual){
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

                System.out.println("Marca: ");
                String marca = scanner.next();

                System.out.println("Descrição: ");
                String descricao = scanner.next();

                System.out.println("Sapatilha Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta) {
                    Sapatilha sapatilha = controllerArtigo.registarSapatilhaNova("sapatilha", codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanhoSapatilha, temAtacadores, cor);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addSapatilhaTxt(sapatilha, versaoatual.getVersaoUsersTxt());
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Sapatilha (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Sapatilha sapatilhausa = controllerArtigo.registarSapatilhaUsada("sapatilha", codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addSapatilhaUsadaTxt(sapatilhausa, versaoatual.getVersaoArtigosTxt());

                }


                //System.out.println("Sapatilha premium(true ou false)? ");
                //boolean isPremium = scanner.nextBoolean();

                //Sapatilha sap = new Sapatilha(codBarras, transArticle, stock, numDonos, avalEstado, precoBase, dataop1, desconto, tamanhoSpatilha, temAtacadores, cor, isPremium);
                //artigos.put(sap.getCodBarras(), sap.clone());
                //artigosVenda.put(sap.getCodBarras(), sap.clone());
            }

            private void registarMala(Utilizador utilizador, Versao versaoatual) {
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

                while(!controllerTransportadora.loginTransportadora(nomeTrans)) {
                    System.out.println("Transportadora não encontrada");
                    System.out.println("Digite o nome de outra transportadora: ");
                    nomeTrans = scanner.next();
                }

                System.out.println("Tamanho da Mala (S/M/L): ");
                String tamanho = scanner.next();

                System.out.println("Material da Mala: ");
                String material = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Ano da coleção: ");
                int anoColecao = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Marca: ");
                String marca = scanner.nextLine();

                System.out.println("Descrição: ");
                String descricao = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Insira o desconto: "); // COMO É CALCULADO O DESCONTO???
                int desconto = scanner.nextInt();

                System.out.println("Mala Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta == true) {
                    Mala mala = controllerArtigo.registarMalaNova("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaTxt(mala, versaoatual.getVersaoUsersTxt());
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Mala (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Mala malausa = controllerArtigo.registarMalaUsada("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaUsadaTxt(malausa, versaoatual.getVersaoUsersTxt());
                }
            }

            // FEITO CARALHO
            private void registarTShirt(Utilizador utilizador, Versao versaoatual) {
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
                    if (tamanho < 1 || tamanho > 4) {
                        System.out.println("Essa opção não está diponível");
                    }
                } while (tamanho < 1 || tamanho > 4);

                int padrao;
                do {
                    System.out.println("Padrao da TShirt: \n(LISO, RISCAS ou PALMEIRAS)");
                    System.out.println("1 - LISO");
                    System.out.println("2 - RISCAS");
                    System.out.println("3 - PALMEIRAS");
                    padrao = scanner.nextInt();
                    if (padrao < 1 || padrao > 3) {
                        System.out.println("Essa opção não está diponível");
                    }
                } while (padrao < 1 || padrao > 3);

                System.out.println("Marca: ");
                String marca = scanner.next();

                System.out.println("Descrição: ");
                String descricao = scanner.next();

                System.out.println("Insira o desconto: "); // COMO É CALCULADO O DESCONTO???
                int desconto = scanner.nextInt();

                System.out.println("TShirt Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if (resposta) {
                    TShirt tshirt = controllerArtigo.registarTShirtNova("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, padrao);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addTShirtTxt(tshirt, versaoatual.getVersaoUsersTxt());

                } else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da TShirt (0-5): ");
                    int avalEstado = scanner.nextInt();

                    TShirt tshirtusa = controllerArtigo.registarTShirtUsada("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, padrao, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addTShirtUsadaTxt(tshirtusa, versaoatual.getVersaoUsersTxt());
                }

            }

            private void menuArtigosAvenda(Utilizador user, Versao versaoatual) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Que artigo deseja ver? ");
                System.out.println("1 - Todos os Artigos");
                System.out.println("2 - Todos os Artigos de um User específico");
                System.out.println("3 - Todas as Sapatilhas");
                System.out.println("4 - Todas as TShirts");
                System.out.println("5 - Todas as Malas");
                System.out.println("6 - Aplicar desconto num artigo");
                System.out.println("7 - Os meus artigos à venda");
                System.out.println("0 - Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        verTodosArtigosVenda();
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 2:
                        System.out.println("Digite o email do utilizador: ");
                        String email = scanner.next();
                        //verArtigosVendaUser(email);
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 3:
                        //artigosAVendaPorTipo("Sapatilha");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 4:
                        //artigosAVendaPorTipo("TShirt");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 5:
                        //artigosAVendaPorTipo("Mala");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 6:
                        aplicarDescontoArtigo(user, versaoatual);
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 7:
                        //verArtigosVendaUser(user.getEmail());
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 0:
                        menuInicial();
                    default:
                        System.out.println("Essa opção não está diponível");
                }
            }

            private void aplicarDescontoArtigo(Utilizador user, Versao versaoatual) {
                Scanner sc = new Scanner(System.in);

                System.out.println("Insira o código de barras do produto: ");
                String codBarras = sc.next();
                sc.nextLine();
                if(!controllerUtlizador.userTemArtigo(codBarras)) {
                    System.out.println("O utilizador não tem esse artigo");
                    System.out.println("Insira o código de barras novamente");
                    codBarras = sc.next();
                }

                System.out.println("Insira o valor do desconto(0 a 60%):");
                int desconto = sc.nextInt();

                controllerArtigo.setDiscountArtigo(codBarras, desconto);
                controllerVersao.atualizaUserTxt(user, versaoatual.getVersaoUsersTxt());
                controllerVersao.atualizaArtigoTxt(controllerArtigo.getArtigoByCod(codBarras), versaoatual.getVersaoArtigosTxt());
                System.out.println("Desconto inserido!");
            }

            public void verTodosArtigosVenda() {
            List<Artigo> listArtigos = new ArrayList<Artigo>();

            System.out.println(controllerArtigo.getArtigosParaVenda(listArtigos));
            }

            /*
            public void verArtigosVendaUser(String email) {
                System.out.println(controllerUtlizador.toStringArtigosVendaUser(email));
            }

            public void artigosAVendaPorTipo(String type) {
                System.out.println(controllerUtlizador.toStringArtigoAVendaByType(type));
            }

            public void verInfoTodasEncomendas(){
                System.out.println(controllerEncomenda.infoTodasEncomendas());
            }
             */

        private void efetuaEncomenda(Utilizador userComprador, Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que pretende fazer? ");
            System.out.println("1. Adicionar artigo ao carrinho.");
            System.out.println("2. Consultar carrinho.");
            System.out.println("3. Remover artigo do carrinho.");
            System.out.println("4. Checkout do carrinho.");
            System.out.println("0. Voltar");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    adicionaArtigoCarrinho(userComprador,versaoatual);
                    menuUtlizador(userComprador, versaoatual);
                    break;
                case 2:
                    controllerUtlizador.getInfoCarrinho(userComprador);
                    menuUtlizador(userComprador, versaoatual);
                    break;
                case 3:
                    System.out.println("Insira o código de barras do produto a remover: ");
                    String codBarras1 = scanner.next();
                    controllerUtlizador.removeArtigoCarrinho(userComprador, codBarras1);
                    controllerVersao.atualizaUserTxt(userComprador, versaoatual.getVersaoUsersTxt());
                case 4:
                    List<Artigo> artigosCarrinho = controllerUtlizador.getArtigosCarrinho(userComprador);
                    String codSistemaUtlizador = controllerUtlizador.getCodSistemaUtlizador(userComprador);
                    Double preco = controllerArtigo.getprecoArtigos(artigosCarrinho);
                    Double profitVi = controllerArtigo.getProfitVi(artigosCarrinho);

                    controllerEncomenda.criaEncomenda(codSistemaUtlizador,artigosCarrinho,preco,profitVi);

                    for(Artigo artigo : artigosCarrinho)
                    {
                        controllerArtigo.tiraArtigoMap(artigo);
                        controllerUtlizador.retiraArtigoDeVenda(controllerArtigo.getArtigoCodBarras(artigo),artigo,codSistemaUtlizador);
                    }

                    controllerVersao.atualizaUserTxt(userComprador, versaoatual.getVersaoUsersTxt());

                    //controllerVersao.atualizaEncomendaTxt(userComprador, userComprador.getArtigosCarrinho(), versaoatual.getVersaoUsersTxt());
                    System.out.println("Encomenda feita!");
                    menuUtlizador(userComprador, versaoatual);
                    break;
                case 0:
                    menuUtlizador(userComprador, versaoatual);
                    break;
                default:
                    System.out.println("Insira uma opção válida!");
                    efetuaEncomenda(userComprador, versaoatual);
            }

        }

        private void adicionaArtigoCarrinho(Utilizador userComprador, Versao versaoatual)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Insira o código de barras do produto: ");
            String codBarras = scanner.next();

            Artigo art = controllerArtigo.getArtigoByCod(codBarras);

            if(!controllerUtlizador.addArtigoCarrinho(userComprador, art)) {
                System.out.println("Artiogo já está no carrinho");
                efetuaEncomenda(userComprador,versaoatual);
            }

            controllerVersao.atualizaUserTxt(userComprador, versaoatual.getVersaoUsersTxt());

            System.out.println("Artigo adicionado ao carrinho!");
        }



            // Funções para criar transportadora

            private void infosTransportadoras () {
                System.out.println(controllerTransportadora.infosTodasAsTransportadoras());
                menuInicial();
            }

            private void criaTransportadora (Versao versaoatual) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Insira o nome da transportadora: ");
                String nomeTrans = scanner.next();

                System.out.println("Insira o valor base do preço de expedição: ");
                double valBaseTrans = scanner.nextDouble();

                System.out.println("Insira em %, a margem de lucro da transportadora: ");
                double margemLucroTrans = scanner.nextDouble();

                Transportadora trans = controllerTransportadora.criaTransportadora(nomeTrans, valBaseTrans, margemLucroTrans);
                controllerVersao.addTransportadoraTxt(trans, versaoatual.getVersaoUsersTxt());

                menuInicial();
            }

            private void loginTransportadora (Versao versaoatual) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o nome da transportadora: ");
                String nomeTrans = scanner.next();

                if (controllerTransportadora.loginTransportadora(nomeTrans)) {
                    menuTransportadora(nomeTrans, versaoatual);
                /*
                System.out.println("Transportadora encontrada!");
                System.out.println("O que pretende fazer? ");
                System.out.println("1. Informações da Transportadora.");
                System.out.println("2. Alterar valor base do preço de expedição.");
                System.out.println("3. Alterar % da margem de lucro da transportadora.");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 :

                }*/
                } else {
                    System.out.println("Transportadora não encontrada!");
                    menuInicial();
                }
            }

            private void menuTransportadora (String nomeTrans, Versao versaoatual){
                Scanner scanner = new Scanner(System.in);

                System.out.println("O que deseja fazer?");
                System.out.println("1. Consultar informações sobre a Transportadora.");
                System.out.println("2. Alterar valor base do preço de expedição.");
                System.out.println("3. Alterar % da margem de lucro da transportadora.");
                System.out.println("4. Voltar para o menu principal");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println(controllerTransportadora.getInfoTrans(nomeTrans));
                        menuTransportadora(nomeTrans, versaoatual);
                    case 2:
                        System.out.println("Insira o novo valor base de expedição: ");
                        double newValorExp = scanner.nextDouble();
                        controllerTransportadora.changeValBaseExpTransportadora(nomeTrans, newValorExp);
                        controllerVersao.atualizaTransportadoraTxt(controllerTransportadora.getTransportadoraByName(nomeTrans), versaoatual.getVersaoTransportadorasTxt());
                        menuTransportadora(nomeTrans, versaoatual);
                    case 3:
                        System.out.println("Insira o valor em %(0 a 100) da margem de lucro: ");
                        double newMargemLucro = scanner.nextDouble();
                        controllerTransportadora.changeMargemDeLucroTransportadora(nomeTrans, newMargemLucro);
                        controllerVersao.atualizaTransportadoraTxt(controllerTransportadora.getTransportadoraByName(nomeTrans), versaoatual.getVersaoTransportadorasTxt());
                        menuTransportadora(nomeTrans, versaoatual);
                    case 4:
                        menuInicial();
                    default:
                        menuTransportadora(nomeTrans, versaoatual);
                }
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



        Scanner scanner = new Scanner(System.in);
            Map<String, Artigo> lstArtigos = new HashMap<String, Artigo>();

            //System.out.println("Quantos artigos deseja encomendar?");


            /*
            int numArt = scanner.nextInt();
            for(int i = 0; i < numArt; i++) {
                System.out.println("Insira o código de barras do produto: ");
                String codBarras = scanner.next();
                Artigo art = controllerArtigo.getArtigoByCod(codBarras);
                lstArtigos.put(art.getCodBarras(), art.clone());
            }
            */
            /*
            String codBarras;
            System.out.println("Digite o codigo de barras do artigo: ");
            codBarras = scanner.next();

            Artigo art = controllerArtigo.getArtigoByCod(codBarras);
            /*
            while(art == null) {
                System.out.println("Código de barras errado.");
                System.out.println("Digite o código de barras novamente: ");
                codBarras = scanner.next();
            } */
            /*
            art = controllerArtigo.getArtigoByCod(codBarras);
            System.out.println("get artigo feito");
            lstArtigos.put(art.getCodBarras(), art.clone());
            */

            /*
            while(true) {
                System.out.println("Deseja adicionar mais algum produto? (S ou N)");
                String resposta = scanner.next();

                if(resposta.equals("N")) break;
                else{
                    System.out.println("Digite o código de barras do artigo que deseja adicionar à encomenda: ");
                    codBarras = scanner.next();
                    Artigo art2 = controllerArtigo.getArtigoByCod(codBarras);
                    lstArtigos.put(art2.getCodBarras(), art2.clone());
                }
            }
            controllerEncomenda.criaEncomenda(userComprador, lstArtigos);
            controllerUtlizador.addEncomendaUser(userComprador, lstArtigos);
            for(Artigo article : lstArtigos.values()) {
                Utilizador uti = controllerUtlizador.getUserByArtigo(article);
                uti.removeArtigoAVenda(article);
                controllerUtlizador.criaUtilizador2(uti);
            }

        }*/



        /*
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
        */




        }
