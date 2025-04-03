import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Departamento {
    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("Funcionário " + funcionario.getNome() + " adicionado ao departamento " + nome);
    }

    public void removerFuncionario(String nome) {
        boolean removido = funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
        if (removido) {
            System.out.println("Funcionário " + nome + " removido com sucesso!");
        } else {
            System.out.println("Funcionário " + nome + " não encontrado!");
        }
    }

    public double obterTotalSalarios() {
        return funcionarios.stream()
                         .mapToDouble(Funcionario::getSalario)
                         .sum();
    }

    public void promoverFuncionario(String nome, double aumento) {
        Funcionario funcionario = buscarFuncionarioPorNome(nome);
        
        if (funcionario != null) {
            funcionario.setSalario(funcionario.getSalario() + aumento);
            System.out.printf("Funcionário %s recebeu aumento de R$%.2f. Novo salário: R$%.2f\n",
                             nome, aumento, funcionario.getSalario());
        } else {
            System.out.println("Funcionário " + nome + " não encontrado!");
        }
    }

    // Métodos de ordenação
    public void ordenarPorNome() {
        Collections.sort(funcionarios);
        System.out.println("Funcionários ordenados por nome.");
    }

    public void ordenarPorCPF() {
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getCpf));
        System.out.println("Funcionários ordenados por CPF.");
    }

    public void ordenarPorSalario() {
        Collections.sort(funcionarios, Comparator.comparingDouble(Funcionario::getSalario));
        System.out.println("Funcionários ordenados por salário.");
    }

    private Funcionario buscarFuncionarioPorNome(String nome) {
        return funcionarios.stream()
                          .filter(f -> f.getNome().equalsIgnoreCase(nome))
                          .findFirst()
                          .orElse(null);
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado no departamento " + nome);
        } else {
            System.out.println("\nFuncionários do departamento " + nome + ":");
            System.out.println("----------------------------------");
            funcionarios.forEach(System.out::println);
            System.out.println("----------------------------------");
            System.out.println("Total de funcionários: " + funcionarios.size());
        }
    }

    @Override
    public String toString() {
        return "Departamento: " + nome + " | Funcionários: " + funcionarios.size();
    }
}