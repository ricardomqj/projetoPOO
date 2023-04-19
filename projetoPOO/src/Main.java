import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Map<String, Utilizador> utilizadores = new HashMap<>();
    Map<String, Artigo> artigos = new HashMap<>();
    Map<String, Transportadora> transportadoras = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Criar um utilizador");
        System.out.println("2 - Criar um artigo");
        System.out.println("3 - Criar uma transportadora");
        System.out.println("4 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

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
}
