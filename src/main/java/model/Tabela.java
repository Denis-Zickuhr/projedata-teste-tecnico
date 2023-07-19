package model;

import model.visitor.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class Tabela {

    public static final DecimalFormat DECIMAL_FORMAT_SALARIO = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
    private final List<Funcionario> entradas = new ArrayList<>();
    private final List<FUNCAO> funcaoList = new ArrayList<>(List.of(FUNCAO.GERENTE, FUNCAO.CONTADOR, FUNCAO.DIRETOR, FUNCAO.COORDENADOR, FUNCAO.ELETRICISTA, FUNCAO.OPERADOR, FUNCAO.RECEPCIONISTA));

    public List<Funcionario> getEntradas() {
        return entradas;
    }

    public void addEntrada(Funcionario f){
        entradas.add(f);
    }

    public void removeFuncionarioByNome(String nome) {
        Iterator<Funcionario> iterator = entradas.iterator();
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNome().equals(nome)) {
                iterator.remove();
                break;
            }
        }
    }

    public void accept(TabelaVisitor visitor) {
        visitor.visit(this);
    }

    public static void main(String[] args) {
        Tabela tabela = new Tabela();

        // 3.1 –> Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        Funcionario f1 = new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), FUNCAO.OPERADOR);
        Funcionario f2 = new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), FUNCAO.OPERADOR);
        Funcionario f3 = new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), FUNCAO.COORDENADOR);
        Funcionario f4 = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), FUNCAO.DIRETOR);
        Funcionario f5 = new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), FUNCAO.RECEPCIONISTA);
        Funcionario f6 = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), FUNCAO.OPERADOR);
        Funcionario f7 = new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), FUNCAO.CONTADOR);
        Funcionario f8 = new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), FUNCAO.GERENTE);
        Funcionario f9 = new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), FUNCAO.ELETRICISTA);
        Funcionario f10 = new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), FUNCAO.GERENTE);

        tabela.addEntrada(f1);
        tabela.addEntrada(f2);
        tabela.addEntrada(f3);
        tabela.addEntrada(f4);
        tabela.addEntrada(f5);
        tabela.addEntrada(f6);
        tabela.addEntrada(f7);
        tabela.addEntrada(f8);
        tabela.addEntrada(f9);
        tabela.addEntrada(f10);

        // 3.2 –> Remover o funcionário “João” da lista.
        tabela.removeFuncionarioByNome("João");

        // OU //

        // tabela.getEntradas().remove(f2);

//        3.3 –> Imprimir todos os funcionários com todas informações, sendo que:
//           • informação de data deve ser exibido no formato dia/mês/ano;
//           • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

        System.out.println("Todos os funcionarios: ");
        for (Funcionario f: tabela.getEntradas()
             ) {
            System.out.println(f);
        }
        System.out.println("\n");

//        3.4 –> Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        AumentoVisitor aumentoVisitor = new AumentoVisitor(10.00);
        tabela.accept(aumentoVisitor);

//        3.5 –> Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        AgruparFuncionariosVisitor agruparFuncionariosVisitor = new AgruparFuncionariosVisitor();
        tabela.accept(agruparFuncionariosVisitor);

//        3.6 –> Imprimir os funcionários, agrupados por função.

        System.out.println("Funcionarios agrupodos por função: ");
        for (FUNCAO funcao: tabela.funcaoList
             ) {
            System.out.println(funcao.name() + ": ");
            for (Funcionario f: agruparFuncionariosVisitor.getFuncaoFuncionarioMap().get(funcao.name())
                 ) {
                System.out.println(f);
            }
        }
        System.out.println("\n");

//        3.8 –> Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        Map<Integer, List<Funcionario>> funcionarioMesMap =  agruparFuncionariosVisitor.getDataNascimentoFuncionarioMap();

        System.out.println("Funcionários que nasceram em outubro!");
        if (funcionarioMesMap.containsKey(10)) {
            for (Funcionario f : funcionarioMesMap.get(10)
            ) {
                System.out.println(f);
            }
        }else{
            System.out.println("Nenhum!");
        }
        System.out.println("Funcionários que nasceram em dezembro!");
        if (funcionarioMesMap.containsKey(12)) {
            for (Funcionario f : funcionarioMesMap.get(12)
            ) {
                System.out.println(f);
            }
        }else{
            System.out.println("Nenhum!");
        }
        System.out.println("\n");

//        3.9 –> Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        IdadeVisitor idadeVisitor = new IdadeVisitor(IdadeVisitor.ORDEM.MAIOR);
        tabela.accept(idadeVisitor);
        Funcionario funcionarioMaisVelho = idadeVisitor.getSelectedFuncionario();
        System.out.println("O funcionário mais velho é: " + funcionarioMaisVelho.getNome() + " que tem " + Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears());

//        3.10 –> Imprimir a lista de funcionários por ordem alfabética.

        OrdemAlfabeticaVisitor ordemAlfabeticaVisitor = new OrdemAlfabeticaVisitor();
        tabela.accept(ordemAlfabeticaVisitor);

        System.out.println("Todos os funcionarios, que agora estão ordenados em ordem alfabética: ");
        for (Funcionario f: tabela.getEntradas()
        ) {
            System.out.println(f);
        }
        System.out.println("\n");

//        3.11 –> Imprimir o total dos salários dos funcionários.

        SalarioVisitor salarioVisitor = new SalarioVisitor();
        tabela.accept(salarioVisitor);
        System.out.println("O total dos salários é: R$" + DECIMAL_FORMAT_SALARIO.format(salarioVisitor.getSumOfSalaries()));
        System.out.println("\n");

//        3.12 –> Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        System.out.println("Quantidades de salário que cada funcionário ganha: ");
        for (Funcionario f: tabela.getEntradas()
        ) {
            BigDecimal salario = BigDecimal.valueOf(salarioVisitor.getFuncionarioSalarioRelacao().get(f));
            BigDecimal salarioRounded = salario.setScale(2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha: " + salarioRounded + "x salários minimos!");
        }
    }
}
