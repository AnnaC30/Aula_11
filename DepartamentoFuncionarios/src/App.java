import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int opcao = -1;
        List<Departamento> departamentos = new ArrayList<>();

        while (opcao != 0) {
            System.out.println("\n***** MENU PRINCIPAL *****");
            System.out.println("1 - Cadastrar departamento");
            System.out.println("2 - Listar departamentos");
            System.out.println("3 - Editar departamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarDepartamento(input, departamentos);
                    break;
                case 2:
                    listarDepartamentos(departamentos);
                    break;
                case 3:
                    editarDepartamento(input, departamentos);
                    break;
                case 0:
                    System.out.println("Encerrando aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        input.close();
    }

    private static void editarDepartamento(Scanner input, List<Departamento> departamentos) {
        System.out.print("\nInforme o nome do departamento: ");
        String nome = input.nextLine();

        Departamento dep = null;

        for (Departamento departamento : departamentos) {
            if (departamento.getNome().equalsIgnoreCase(nome)) {
                dep = departamento;
                break;
            }
        }

        if (dep == null) {
            System.out.println("Departamento não encontrado!");
        } else {
            int opcao = -1;
            while (opcao != 0) {
                System.out.println("\n***** MENU DO DEPARTAMENTO: " + dep.getNome() + " *****");
                System.out.println("1 - Cadastrar funcionário");
                System.out.println("2 - Remover funcionário");
                System.out.println("3 - Exibir total salarial");
                System.out.println("4 - Dar aumento a funcionário");
                System.out.println("5 - Listar funcionários (ordenar)");
                System.out.println("0 - Voltar");
                System.out.print("Opção: ");

                opcao = input.nextInt();
                input.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarFuncionario(input, dep);
                        break;
                    case 2:
                        removerFuncionario(input, dep);
                        break;
                    case 3:
                        exibirTotalSalarial(dep);
                        break;
                    case 4:
                        promoverFuncionario(input, dep);
                        break;
                    case 5:
                        System.out.println("\nOrdenar por:");
                        System.out.println("1 - Nome");
                        System.out.println("2 - CPF");
                        System.out.println("3 - Salário");
                        System.out.print("Opção: ");
                        int ordenacao = input.nextInt();
                        input.nextLine();
                        
                        switch (ordenacao) {
                            case 1:
                                dep.ordenarPorNome();
                                break;
                            case 2:
                                dep.ordenarPorCPF();
                                break;
                            case 3:
                                dep.ordenarPorSalario();
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                        dep.listarFuncionarios();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        }
    }

    private static void promoverFuncionario(Scanner input, Departamento dep) {
        System.out.print("\nInforme o nome do funcionário: ");
        String nome = input.nextLine();

        System.out.print("Informe o valor do aumento: ");
        double aumento = input.nextDouble();
        input.nextLine();

        Funcionario func = null;
        for (Funcionario funcionario : dep.getFuncionarios()) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                func = funcionario;
                break;
            }
        }

        if (func == null) {
            System.out.println("Funcionário não encontrado!");
        } else {
            double novoSalario = func.getSalario() + aumento;
            func.setSalario(novoSalario);
            System.out.printf("Aumento concedido! Novo salário de %s: R$%.2f\n", func.getNome(), novoSalario);
        }
    }

    private static void exibirTotalSalarial(Departamento dep) {
        double total = dep.obterTotalSalarios();
        System.out.printf("\nTotal salarial do departamento %s: R$%.2f\n", dep.getNome(), total);
    }

    private static void cadastrarFuncionario(Scanner input, Departamento departamento) {
        System.out.print("\nNome do funcionário: ");
        String nome = input.nextLine();
        
        System.out.print("CPF do funcionário: ");
        String cpf = input.nextLine();
        
        System.out.print("Salário do funcionário: ");
        double salario = input.nextDouble();
        input.nextLine();

        Funcionario func = new Funcionario(nome, cpf, salario);
        departamento.adicionarFuncionario(func);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void removerFuncionario(Scanner input, Departamento departamento) {
        System.out.print("\nNome do funcionário a remover: ");
        String nome = input.nextLine();
        
        departamento.removerFuncionario(nome);
        System.out.println("Funcionário removido com sucesso!");
    }

    private static void listarDepartamentos(List<Departamento> departamentos) {
        System.out.println("\nDepartamentos cadastrados:");
        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado.");
        } else {
            for (Departamento departamento : departamentos) {
                System.out.println(departamento);
            }
        }
    }

    private static void cadastrarDepartamento(Scanner input, List<Departamento> departamentos) {
        System.out.print("\nNome do departamento: ");
        String nome = input.nextLine();

        Departamento dep = new Departamento(nome);
        departamentos.add(dep);
        System.out.println("Departamento cadastrado com sucesso!");
    }
}