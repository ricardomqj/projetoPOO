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

            System.out.println("O que deseja fazer?");
            System.out.println(" 1. Criar um utlizador");                         //feito
            System.out.println(" 2. Efetuar login com utlizador");                //feito
            System.out.println(" 3. Criar uma transportadora");
            System.out.println(" 4. Efetuar login com transportadora");
            System.out.println(" 5. Funções gerais");
            System.out.println(" 6. Ver lista de transportadoras disponíveis");
            System.out.println(" 7. Ver lista de utilizadores criados");
            System.out.println(" 8. Ver lista de encomendas");
            System.out.println(" 9. Load & Save");
            System.out.println("10. ADMIN");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    scanner.close();
                    criaUtlizador(versaoAtual);
                    break;
                case 2:
                    scanner.close();
                    loginUtlizador(versaoAtual);
                    break;
                case 3 :
                    scanner.close();
                    criaTransportadora(versaoAtual);
                    break;
                case 4:
                    scanner.close();
                    loginTransportadora(versaoAtual);
                    break;
                case 6:
                    scanner.close();
                    infosTransportadoras(versaoAtual);
                    break;
                case 7:
                    scanner.close();
                    infosTodosUsers(versaoAtual);
                    break;
                case 8:
                    scanner.close();
                    System.out.println("Essa opção não está diponível");
                    menuInicial(versaoAtual);
                case 9:
                    scanner.close();
                    menuLoadSave(versaoAtual);
                    break;
                case 10:
                    scanner.close();
                    menuQueries(versaoAtual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
                    scanner.close();
                    menuInicial(versaoAtual);
            }
            scanner.close();
        }

        private void menuQueries(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que query deseja executar?");
            System.out.println("&. Query 1");
            System.out.println("&. Query 2");
            System.out.println("1. Lista de encomendas emitidas por um vendedor"); // feito
            System.out.println("2. Transportadora com maior volume da faturação"); // feito(?)
            System.out.println("3. Dinheiro ganho pela Vintage no seu funcionamento"); // feito
            System.out.println("4. Vendedor que mais faturou desde sempre"); // feito (?)
            System.out.println("5. Vendedor que mais faturou num periodo de tempo");
            System.out.println("6. Top N maiores compradores num período de tempo"); // feito(?)
            System.out.println("7. Top N maiores vendedores num periodo de tempo");
            System.out.println("0. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Email do vendedor que pretende analisar: ");
                    String email = scanner.next();
                    Map<String, Encomenda> encsVendedor = controllerQueries.encsVendedor(controllerEncomenda.getListaTodasEncomendas(), controllerUtlizador.getUserByEmail(email));
                    String encsVendedorString = controllerQueries.encsVendedorToString(encsVendedor);
                    System.out.println(encsVendedorString);
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 2:
                    System.out.println(controllerQueries.transportadoraComMaisFaturação(controllerTransportadora.getListaTransportadoras(), controllerUtlizador.getListaUtilizadores()));
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 3:
                    double lucro = controllerQueries.vintageProfit(controllerEncomenda.getListaTodasEncomendas());
                    System.out.println("A vintage ganhou " + Double.toString(lucro) + " durante o seu funcionamento.");
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 4:
                    System.out.println("Utilizador que mais faturou: ");
                    System.out.println(controllerQueries.getVendedorQueMaisFaturouSempre(controllerUtlizador.getListaUtilizadores()));
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 5:
                    System.out.println("Insira a data inicial: ");
                    String dataString1 = scanner.next();
                    System.out.println("Digite o data final: ");
                    String dataString2 = scanner.next();

                    LocalDate data1 = LocalDate.parse(dataString1);
                    LocalDate data2 = LocalDate.parse(dataString2);
                    System.out.println(controllerQueries.utilizadorComMaiorDinheiroGanho(controllerUtlizador.getListaUtilizadores(), data1, data2));
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 6:
                    System.out.println("Quantos compradores pretende ver: ");
                    int num = scanner.nextInt();
                    System.out.println("Insira a data inicial: ");
                    String dataString3 = scanner.next();
                    System.out.println("Insira a data final: ");
                    String dataString4 = scanner.next();

                    LocalDate data3 = LocalDate.parse(dataString3);
                    LocalDate data4 = LocalDate.parse(dataString4);
                    System.out.println(controllerQueries.topNCompradores(num, controllerUtlizador.getListaUtilizadores(), data3, data4));
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 7:
                    System.out.println("Quantos vendedors pretende ver: ");
                    int num1 = scanner.nextInt();
                    System.out.println("Insira a data inicial: ");
                    String dataString5 = scanner.next();
                    System.out.println("Insira a data final: ");
                    String dataString6 = scanner.next();

                    LocalDate data5 = LocalDate.parse(dataString5);
                    LocalDate data6 = LocalDate.parse(dataString6);
                    System.out.println(controllerQueries.topNVendedores(num1, controllerUtlizador.getListaUtilizadores(), data5, data6));
                    scanner.close();
                    menuQueries(versaoatual);
                    break;
                case 0:
                    scanner.close();
                    menuInicial(versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }
            scanner.close();
        }

        private void menuLoadVersoes(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que versão deseja carregar?");

            Map<LocalTime, Versao> listaVersoes = controllerVersao.getListaVersoes();               //vai buscar o map de todas as versoes;
            String listaVersoesString = controllerVersao.getListaVersoesToString(listaVersoes);    //vai ao viewer transformar tudo isto numa string só;
            System.out.println(listaVersoesString);                                               //apresenta aqui todas as versoes por ordem crescente exemplo:

            System.out.println("Copie e cole a Hora de Criação da Versão que deseja carregar"); //Coloque a hora da versão que pretende carregar (hh:mm)

            String input = scanner.nextLine();

            try {
                LocalTime time = LocalTime.parse(input);

                controllerVersao.loadVersao(time);
                controllerArtigo.loadArtigos();
                controllerUtlizador.loadUtilizadores();
                controllerEncomenda.loadEncomendas();
                controllerTransportadora.loadTransportadoras();

                System.out.println("Load efetuado!");
                scanner.close();
                menuLoadSave(versaoatual);

            } catch (DateTimeParseException e) {
                System.out.println("Input inválido: Por favor copie e cole a Hora de criação da versão:");
                scanner.close();
                menuLoadSave(versaoatual);
            }

            scanner.close();
        }

        private void menuLoadSave(Versao versaoatual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1. Dar save da versão atual");
            System.out.println("2. Dar load de uma versão");
            System.out.println("0. Voltar atrás");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    controllerVersao.saveVersao(versaoatual);

                    Versao versaoNova = new Versao(versaoatual.getVersaoArtigosTxt(), versaoatual.getVersaoUsersTxt(), versaoatual.getVersaoTransportadorasTxt(), versaoatual.getVersaoEncomendasTxt());

                    scanner.close();
                    menuInicial(versaoNova);
                    break;
                case 2:
                    scanner.close();
                    menuLoadVersoes(versaoatual);
                    break;
                case 0:
                    scanner.close();
                    menuInicial(versaoatual);
                    break;
                default:
                    System.out.println("Essa opção não está diponível");
            }

            scanner.close();
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

            scanner.close();
            menuInicial(versaoAtual);
        }

        private void loginUtlizador(Versao versaoAtual) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o email do utilizador:");
            String emailUtilizador = scanner.next();

            Utilizador utilizador = controllerUtlizador.getUserByEmail(emailUtilizador);
            if(utilizador != null)
            {
                scanner.close();
                menuUtlizador(utilizador, versaoAtual);
            }
            else
            {
                System.out.println("Esse email não está registado!");
                scanner.close();
                menuInicial(versaoAtual);
            }
        }

        private void menuUtlizador(Utilizador utilizador, Versao versaoatual)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("O que deseja fazer?");
            System.out.println("1. Vender um artigo.");
            System.out.println("2. Ver artigos à venda no momento.");
            System.out.println("3. Efetuar uma encomenda.");
            System.out.println("4. Devolver uma encomenda.");
            System.out.println("5. Consultar informações do utilizador.");
            System.out.println("0. Voltar.");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    scanner.close();
                    adicionarArtigoParaVenda(utilizador, versaoatual);
                    menuInicial(versaoatual);
                    break;
                case 2:
                    scanner.close();
                    menuArtigosAvenda(utilizador, versaoatual);
                    break;
                case 3:
                    scanner.close();
                    efetuaEncomenda(utilizador, versaoatual);
                    menuInicial(versaoatual);
                    break;
                case 4:
                    scanner.close();
                    System.out.println("Essa opção não está diponível");
                    menuInicial(versaoatual);
                    break;
                case 5:
                    scanner.close();
                    infoUser(utilizador.getEmail(), versaoatual);
                    menuInicial(versaoatual);
                    break;
                case 0:
                    scanner.close();
                    menuInicial(versaoatual);
                    break;
                default:
                    scanner.close();
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
                    scanner.close();
                    registarSapatilha(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 2:
                    scanner.close();
                    registarTShirt(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 3:
                    scanner.close();
                    registarMala(utilizador, versaoatual);
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                case 4:
                    scanner.close();
                    //fazer ainda
                    System.out.println("O artigo foi colocado à venda!");
                    menuUtlizador(utilizador, versaoatual);
                    break;
                default:
                    scanner.close();
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
                scanner.close();
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

                System.out.println("Mala Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if(resposta == true) {
                    Mala mala = controllerArtigo.registarMalaNova("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanho, material, anoColecao);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaTxt(mala, versaoatual);
                }
                else {
                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da Mala (0-5): ");
                    int avalEstado = scanner.nextInt();

                    Mala malausa = controllerArtigo.registarMalaUsada("mala", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanho, material, anoColecao, numDonos, avalEstado);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addMalaTxt(malausa, versaoatual);
                }
                scanner.close();
            }

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

                System.out.println("TShirt Nova (true ou false)? ");
                boolean resposta = scanner.nextBoolean();

                if (resposta) {

                    TShirt tshirt = controllerArtigo.registarTShirtNova("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanho, padrao);
                    controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                    controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                    controllerVersao.addTShirtTxt(tshirt, versaoatual);

                } else {

                    System.out.println("Quantos donos já teve: ");
                    int numDonos = scanner.nextInt();

                    System.out.println("Estado da TShirt (0-5): ");
                    int avalEstado = scanner.nextInt();

                    if (padrao == 2 || padrao == 3) {
                        TShirt tshirtusa = controllerArtigo.registarTShirtUsada("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, 50, tamanho, padrao, numDonos, avalEstado);
                        controllerUtlizador.registarArtigoNoUtlizador(utilizador, codBarras);
                        controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                        controllerVersao.addTShirtTxt(tshirtusa, versaoatual);
                    } else {
                        TShirt tshirtusa = controllerArtigo.registarTShirtUsada("tshirt", utilizador, codBarras, dataop, precoBase, nomeTrans, marca, descricao, 0, tamanho, padrao, numDonos, avalEstado);
                        controllerUtlizador.registarArtigoNoUtlizador(utilizador,codBarras);
                        controllerVersao.atualizaUserTxt(utilizador, versaoatual.getVersaoUsersTxt());
                        controllerVersao.addTShirtTxt(tshirtusa, versaoatual);
                    }
                }
                scanner.close();
            }

            private void menuArtigosAvenda(Utilizador user, Versao versaoatual) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Que artigo deseja ver? ");
                System.out.println("1. Todos os Artigos");
                System.out.println("2. Todos os Artigos de um User específico");
                System.out.println("3. Todas as Sapatilhas");
                System.out.println("4. Todas as TShirts");
                System.out.println("5. Todas as Malas");
                System.out.println("6. Aplicar desconto num artigo");
                System.out.println("7. Os meus artigos à venda");
                System.out.println("0. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        scanner.close();
                        verTodosArtigosVenda();
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 2:
                        scanner.close();
                        System.out.println("Digite o email do utilizador: ");
                        String email = scanner.next();
                        //verArtigosVendaUser(email);
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 3:
                        scanner.close();
                        //artigosAVendaPorTipo("Sapatilha");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 4:
                        scanner.close();
                        //artigosAVendaPorTipo("TShirt");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 5:
                        scanner.close();
                        //artigosAVendaPorTipo("Mala");
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 6:
                        scanner.close();
                        aplicarDescontoArtigo(user, versaoatual);
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 7:
                        scanner.close();
                        //verArtigosVendaUser(user.getEmail());
                        menuArtigosAvenda(user, versaoatual);
                        break;
                    case 0:
                        scanner.close();
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
                controllerVersao.atualizaUserTxt(user, versaoatual.getVersaoUsersTxt());

                controllerVersao.atualizaArtigoTxt(controllerArtigo.getArtigoByCod(codBarras), versaoatual.getVersaoArtigosTxt());
                System.out.println("Desconto inserido!");

                sc.close();
            }

            public void verTodosArtigosVenda() {
                List<Artigo> listArtigos = new ArrayList<Artigo>();

                System.out.println(controllerArtigo.getArtigosParaVenda(listArtigos));
            }

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
                    scanner.close();
                    adicionaArtigoCarrinho(userComprador,versaoatual);
                    menuUtlizador(userComprador, versaoatual);
                    break;
                case 2:
                    scanner.close();
                    controllerUtlizador.getInfoCarrinho(userComprador);
                    menuUtlizador(userComprador, versaoatual);
                    break;
                case 3:
                    scanner.close();
                    System.out.println("Insira o código de barras do produto a remover: ");
                    String codBarras1 = scanner.next();
                    controllerUtlizador.removeArtigoCarrinho(userComprador, codBarras1);
                    controllerVersao.atualizaUserTxt(userComprador, versaoatual.getVersaoUsersTxt());
                case 4:
                    scanner.close();
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
                    scanner.close();
                    menuUtlizador(userComprador, versaoatual);
                    break;
                default:
                    scanner.close();
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

            scanner.close();
        }

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

                scanner.close();
                menuInicial(versaoatual);
            }

            private void loginTransportadora (Versao versaoatual) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o nome da transportadora: ");
                String nomeTrans = scanner.next();

                if (controllerTransportadora.loginTransportadora(nomeTrans)) {
                    scanner.close();
                    menuTransportadora(nomeTrans, versaoatual);

                } else {
                    System.out.println("Transportadora não encontrada!");
                    scanner.close();
                    menuInicial(versaoatual);
                }
            }

            private void menuTransportadora (String nomeTrans, Versao versaoatual){
                Scanner scanner = new Scanner(System.in);

                System.out.println("O que deseja fazer?");
                System.out.println("1. Consultar informações sobre a Transportadora.");
                System.out.println("2. Alterar valor base do preço de expedição.");
                System.out.println("3. Alterar % da margem de lucro da transportadora.");
                System.out.println("0. Voltar");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        scanner.close();
                        System.out.println(controllerTransportadora.getInfoTrans(nomeTrans));
                        menuTransportadora(nomeTrans, versaoatual);
                    case 2:
                        scanner.close();
                        System.out.println("Insira o novo valor base de expedição: ");
                        double newValorExp = scanner.nextDouble();
                        controllerTransportadora.changeValBaseExpTransportadora(nomeTrans, newValorExp);
                        controllerVersao.atualizaTransportadoraTxt(controllerTransportadora.getTransportadoraByName(nomeTrans), versaoatual.getVersaoTransportadorasTxt());
                        menuTransportadora(nomeTrans, versaoatual);
                    case 3:
                        scanner.close();
                        System.out.println("Insira o valor em %(0 a 100) da margem de lucro: ");
                        double newMargemLucro = scanner.nextDouble();
                        controllerTransportadora.changeMargemDeLucroTransportadora(nomeTrans, newMargemLucro);
                        controllerVersao.atualizaTransportadoraTxt(controllerTransportadora.getTransportadoraByName(nomeTrans), versaoatual.getVersaoTransportadorasTxt());
                        menuTransportadora(nomeTrans, versaoatual);
                    case 0:
                        scanner.close();
                        menuInicial(versaoatual);
                    default:
                        scanner.close();
                        menuTransportadora(nomeTrans, versaoatual);
                }
            }

}
