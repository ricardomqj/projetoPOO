import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.time.format.DateTimeParseException;

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

        public void loadInitialFiles() {

            String versaoUsersTxt = controllerUtlizador.loadUtilizadores();
            String versaoTransportadorasTxt = controllerTransportadora.loadTransportadoras();
            String versaoArtigosTxt = controllerArtigo.loadArtigos();
            String versaoEncomendasTxt = controllerEncomenda.loadEncomendas();

            Versao versaoAtual = new Versao(versaoArtigosTxt, versaoUsersTxt, versaoTransportadorasTxt, versaoEncomendasTxt);

            menuInicial(versaoAtual);
        }

        public void menuInicial(Versao versaoAtual)
        {
            Scanner scanner = new Scanner(System.in);

            //ver como se vai fazer acerca de quando se dá estes loads

            //String versaoUsersTxt = controllerUtlizador.loadUtilizadores();
            //String versaoTransportadorasTxt = controllerTransportadora.loadTransportadoras();
            //String versaoArtigosTxt = controllerArtigo.loadArtigos();
            //String versaoEncomendasTxt = controllerEncomenda.loadEncomendas();

            //Versao versaoAtual = new Versao(versaoArtigosTxt, versaoUsersTxt, versaoTransportadorasTxt, versaoEncomendasTxt); // a data de criação é definida aqui caralho gt3 gt3 quero...

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
                    infosTransportadoras(versaoAtual);
                    break;
                case 7:
                    infosTodosUsers(versaoAtual);
                    break;
                case 8:

                case 9:
                    menuLoadSave(versaoAtual);
                    break;
                case 10:
                    menuQueries(versaoAtual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
                    menuInicial(versaoAtual);
            }
        }


        // Funções do user

        private void menuQueries(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que query deseja executar?");
            System.out.println("1 - Query 1");
            System.out.println("2 - Query 2");
            System.out.println("3 - Lista de encomendas emitidas por um vendedor"); // feito
            System.out.println("4 - Transportadora com maior volume da faturação"); // feito(?)
            System.out.println("5 - Dinheiro ganho pela Vintage no seu funcionamento"); // feito
            System.out.println("6 - Vendedor que mais faturou desde sempre"); // feito (?)
            System.out.println("7 - Vendedor que mais faturou num periodo de tempo");
            System.out.println("8 - Top N maiores compradores num período de tempo"); // feito(?)
            System.out.println("9 - Top N maiores vendedores num periodo de tempo");
            System.out.println("0 - Sair");

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
                    menuQueries(versaoatual);
                    break;
                case 4:
                    System.out.println(controllerQueries.transportadoraComMaisFaturação(controllerTransportadora.getListaTransportadoras(), controllerUtlizador.getListaUtilizadores()));
                    menuQueries(versaoatual);
                    break;
                case 5:
                    double lucro = controllerQueries.vintageProfit(controllerEncomenda.getListaTodasEncomendas());
                    System.out.println("A vintage ganhou " + Double.toString(lucro) + " durante o seu funcionamento.");
                    menuQueries(versaoatual);
                    break;
                case 6:
                    System.out.println("Utilizador que mais faturou: ");
                    System.out.println(controllerQueries.getVendedorQueMaisFaturouSempre(controllerUtlizador.getListaUtilizadores()));
                    menuQueries(versaoatual);
                    break;
                case 7:
                    System.out.println("Insira a data inicial: ");
                    String dataString1 = scanner.next();
                    System.out.println("Digite o data final: ");
                    String dataString2 = scanner.next();

                    LocalDate data1 = LocalDate.parse(dataString1);
                    LocalDate data2 = LocalDate.parse(dataString2);
                    System.out.println(controllerQueries.utilizadorComMaiorDinheiroGanho(controllerUtlizador.getListaUtilizadores(), data1, data2));
                    menuQueries(versaoatual);
                    break;
                case 8:
                    System.out.println("Quantos compradores pretende ver: ");
                    int num = scanner.nextInt();
                    System.out.println("Insira a data inicial: ");
                    String dataString3 = scanner.next();
                    System.out.println("Insira a data final: ");
                    String dataString4 = scanner.next();

                    LocalDate data3 = LocalDate.parse(dataString3);
                    LocalDate data4 = LocalDate.parse(dataString4);
                    System.out.println(controllerQueries.topNCompradores(num, controllerUtlizador.getListaUtilizadores(), data3, data4));
                    menuQueries(versaoatual);
                    break;

                case 9:
                    System.out.println("Quantos vendedors pretende ver: ");
                    int num1 = scanner.nextInt();
                    System.out.println("Insira a data inicial: ");
                    String dataString5 = scanner.next();
                    System.out.println("Insira a data final: ");
                    String dataString6 = scanner.next();

                    LocalDate data5 = LocalDate.parse(dataString5);
                    LocalDate data6 = LocalDate.parse(dataString6);
                    System.out.println(controllerQueries.topNVendedores(num1, controllerUtlizador.getListaUtilizadores(), data5, data6));
                    menuQueries(versaoatual);
                    break;
                case 0:
                    menuInicial(versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
        }

        private void menuLoadVersoes(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que versão deseja carregar?");

            Map<LocalTime, Versao> listaVersoes = controllerVersao.getListaVersoes();               //vai buscar o map de todas as versoes;
            String listaVersoesString = controllerVersao.getListaVersoesToString(listaVersoes);    //vai ao viewer transformar tudo isto numa string só;
            System.out.println(listaVersoesString);                                               //apresenta aqui todas as versoes por ordem crescente exemplo:

            //Versão - 20:18
            //Versão - 20:23
            //Versão - 21:01
            //Versão - 22:03

            System.out.println("Copie e cole a Hora de Criação da Versão que deseja carregar"); //Coloque a hora da versão que pretende carregar (hh:mm)

            String input = scanner.nextLine();

            try {
                // parse the input string to create a LocalTime object
                LocalTime time = LocalTime.parse(input);

                controllerVersao.loadVersao(time);
                controllerArtigo.loadArtigos();
                controllerUtlizador.loadUtilizadores();
                controllerEncomenda.loadEncomendas();
                controllerTransportadora.loadTransportadoras();

                System.out.println("Load efetuado!");
                menuLoadSave(versaoatual);


                // print the LocalTime variable and its components
            } catch (DateTimeParseException e) {
                // handle the case where the input string is not a valid LocalTime value
                System.out.println("Input inválido: Por favor copie e cole a Hora de criação da versão:");
                menuLoadSave(versaoatual);
            }

        }

        private void menuLoadSave(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1 - Dar save da versão atual");
            System.out.println("2 - Dar load de uma versão");
            System.out.println("0 - Voltar atrás");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    controllerVersao.saveVersao(versaoatual);

                    Versao versaoNova = new Versao(versaoatual.getVersaoArtigosTxt(), versaoatual.getVersaoUsersTxt(), versaoatual.getVersaoTransportadorasTxt(), versaoatual.getVersaoEncomendasTxt());

                    menuInicial(versaoNova);
                    break;
                case 2:
                    menuLoadVersoes(versaoatual);
                    break;
                case 0:
                    menuInicial(versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
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
            controllerVersao.addUserToTxt(user, versaoAtual);

            menuInicial(versaoAtual);
        }

        private void loginUtlizador(Versao versaoAtual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.next();

            Utilizador utilizador = controllerUtlizador.getUserByEmail(emailUtilizador);
            if(utilizador != null)
            {
                menuUtlizador(utilizador, versaoAtual);
            }
            else
            {
                System.out.println("Esse email não está registado!");
                menuInicial(versaoAtual);
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
                    menuInicial(versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
                    menuUtlizador(utilizador, versaoatual);
                    break;
            }
        }

        private void infosTodosUsers(Versao versaoAtual) {
            System.out.println(controllerUtlizador.infoTodosUsers());
            menuInicial(versaoAtual);
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
                    controllerVersao.addSapatilhaTxt(sapatilha, versaoatual);
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Sapatilha (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Sapatilha sapatilhausa = controllerArtigo.registarSapatilhaUsada("sapatilha", codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addSapatilhaTxt(sapatilhausa, versaoatual);

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

                System.out.println("Insira o desconto: "); // COMO É CALCULADO O DESCONTO???
                int desconto = scanner.nextInt();

                System.out.println("Mala Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta == true) {
                    Mala mala = controllerArtigo.registarMalaNova("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaTxt(mala, versaoatual);
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Mala (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Mala malausa = controllerArtigo.registarMalaUsada("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, material, anoColecao, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaTxt(malausa, versaoatual);
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
                    controllerVersao.addTShirtTxt(tshirt, versaoatual);

                } else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da TShirt (0-5): ");
                    int avalEstado = scanner.nextInt();

                    TShirt tshirtusa = controllerArtigo.registarTShirtUsada("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, desconto, tamanho, padrao, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addTShirtTxt(tshirtusa, versaoatual);
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
                        menuInicial(versaoatual);
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
                System.out.println("qual é o desconto aqui?");
                System.out.println(desconto);
                controllerVersao.atualizaUserTxt(user, versaoatual.getVersaoUsersTxt());
                System.out.println("ARTIGO QUE FOI APLICADO O DESCONTO \n");
                System.out.println(controllerArtigo.getArtigoByCod(codBarras).toString());
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

            private void infosTransportadoras (Versao versaoAtual) {
                System.out.println(controllerTransportadora.infosTodasAsTransportadoras());
                menuInicial(versaoAtual);
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
                controllerVersao.addTransportadoraTxt(trans, versaoatual);

                System.out.println("versaoTransportadorasTxt (FORA):");
                System.out.println(versaoatual.getVersaoTransportadorasTxt());

                menuInicial(versaoatual);
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
                    menuInicial(versaoatual);
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
                        menuInicial(versaoatual);
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
