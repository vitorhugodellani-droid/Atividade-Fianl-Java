import java.util.Random;
import java.util.Scanner;

public class F1 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            System.out.print("Digite o nome do piloto: ");
            String nomePiloto = scanner.nextLine();

            // Dados iniciais do piloto
            int habilidade = 50;
            int pontos = 0;
            int saldo = 500;
            int corridas = 0;

            int opcao;
            do {
                opcao = exibirMenu(scanner, nomePiloto, habilidade, pontos, saldo);

                switch (opcao) {
                    case 1:
                        mostrarPiloto(nomePiloto, habilidade, pontos, saldo, corridas);
                        break;

                    case 2:
                        int custoTreino = 300;
                        if (saldo < custoTreino) {
                            System.out.println("Saldo insuficiente para treinar! Custo: R$ " + custoTreino);
                        } else if (habilidade >= 100) {
                            System.out.println("O piloto ja atingiu a habilidade maxima (100)!");
                        } else {
                            saldo -= custoTreino;
                            int sorteio = random.nextInt(10) + 1; // 1 a 10
                            System.out.println("Treinar custa R$ 300. Sorteio: " + sorteio);

                            int novaHabilidade = treinar(habilidade, sorteio);
                            if (novaHabilidade > habilidade) {
                                habilidade = novaHabilidade;
                                System.out.println("Treinamento concluido! Habilidade: " + habilidade);
                            } else {
                                System.out.println("Treinamento falhou! Habilidade: " + habilidade);
                            }
                            System.out.println("Saldo atual: R$ " + saldo);
                        }
                        break;

                    case 3:
                        System.out.println("Escolha a estrategia:");
                        System.out.println("1 - Conservadora (+0)");
                        System.out.println("2 - Equilibrada (+5)");
                        System.out.println("3 - Agressiva (+10)");
                        System.out.print("Estrategia: ");
                        int estrategia = scanner.nextInt();

                        int bonus;
                        if (estrategia == 1) {
                            bonus = 0;
                        } else if (estrategia == 2) {
                            bonus = 5;
                        } else if (estrategia == 3) {
                            bonus = 10;
                        } else {
                            System.out.println("Estrategia invalida! A corrida nao foi iniciada.");
                            break;
                        }

                        int sorte = random.nextInt(11); // 0 a 10
                        int desempenho = calcularDesempenho(habilidade, bonus, sorte);
                        String posicao = determinarPosicao(desempenho);
                        int pontosGanhos = calcularPontos(desempenho);
                        int premioGanho = calcularPremio(desempenho);

                        pontos += pontosGanhos;
                        saldo += premioGanho;
                        corridas++;

                        System.out.println("Sorte: " + sorte + " | Desempenho: " + desempenho);
                        System.out.println("Resultado: " + posicao + " | +" + pontosGanhos + " pontos | +R$ " + premioGanho);
                        System.out.println("Saldo atual: R$ " + saldo);
                        break;

                    case 4:
                        consultarResultados(corridas, pontos, saldo);
                        break;

                    case 0:
                        System.out.println("Encerrando o simulador. Ate a proxima!");
                        break;

                    default:
                        System.out.println("Opcao invalida! Tente novamente.");
                        break;
                }
            } while (opcao != 0);

            scanner.close();
        }

        // Exibe o cabecalho, o menu e retorna a opcao escolhida
        public static int exibirMenu(Scanner scanner, String nome, int habilidade, int pontos, int saldo) {
            System.out.println("========== JAVA F1 SIMULATOR ==========");
            System.out.println("Piloto: " + nome + " | Habilidade: " + habilidade);
            System.out.println("Pontos: " + pontos + " | Saldo: R$ " + saldo);
            System.out.println("1 - Visualizar piloto");
            System.out.println("2 - Treinar piloto");
            System.out.println("3 - Participar de corrida");
            System.out.println("4 - Consultar resultados");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            return scanner.nextInt();
        }

        // Exibe todos os dados atuais do piloto
        public static void mostrarPiloto(String nome, int habilidade, int pontos, int saldo, int corridas) {
            System.out.println("--- Dados do Piloto ---");
            System.out.println("Nome: " + nome);
            System.out.println("Habilidade: " + habilidade);
            System.out.println("Pontuacao: " + pontos);
            System.out.println("Dinheiro disponivel: R$ " + saldo);
            System.out.println("Corridas realizadas: " + corridas);
        }

        // Avalia o sorteio (1 a 10) e retorna a habilidade atualizada (limitada a 100)
        public static int treinar(int habilidade, int sorteio) {
            if (sorteio >= 6) {
                habilidade += 5;
                if (habilidade > 100) {
                    habilidade = 100;
                }
            }
            return habilidade;
        }

        // Calcula o desempenho total da corrida
        public static int calcularDesempenho(int habilidade, int bonus, int sorte) {
            return habilidade + bonus + sorte;
        }

        // Retorna a colocacao com base no desempenho
        public static String determinarPosicao(int desempenho) {
            if (desempenho >= 90) {
                return "1o lugar";
            } else if (desempenho >= 75) {
                return "2o lugar";
            } else if (desempenho >= 60) {
                return "3o lugar";
            } else {
                return "5o lugar";
            }
        }

        // Retorna os pontos conquistados na corrida
        public static int calcularPontos(int desempenho) {
            if (desempenho >= 90) {
                return 25;
            } else if (desempenho >= 75) {
                return 18;
            } else if (desempenho >= 60) {
                return 15;
            } else {
                return 10;
            }
        }

        // Retorna o premio em dinheiro conquistado na corrida
        public static int calcularPremio(int desempenho) {
            if (desempenho >= 90) {
                return 1000;
            } else if (desempenho >= 75) {
                return 700;
            } else if (desempenho >= 60) {
                return 500;
            } else {
                return 300;
            }
        }

        // Exibe o resumo de corridas, pontos e saldo
        public static void consultarResultados(int corridas, int pontos, int saldo) {
            System.out.println("\n--- Resultados Gerais ---");
            System.out.println("Corridas realizadas: " + corridas);
            System.out.println("Pontos acumulados: " + pontos);
            System.out.println("Saldo atual: R$ " + saldo);
        }

}
