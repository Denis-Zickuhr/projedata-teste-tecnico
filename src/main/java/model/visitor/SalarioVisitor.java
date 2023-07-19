package model.visitor;

import model.Funcionario;
import model.Tabela;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 3.11 –> Total dos salários dos funcionários.
 * 3.12 –> Quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
 * **/

public class SalarioVisitor implements TabelaVisitor {

    private BigDecimal sumOfSalaries = BigDecimal.ZERO;
    private Map<Funcionario, Double> funcionarioSalarioRelacao;
    private static final double SALARIO_MINIMO = 1212.00;


    public BigDecimal getSumOfSalaries(){
        return sumOfSalaries;
    }

    public Map<Funcionario, Double> getFuncionarioSalarioRelacao() {
        return funcionarioSalarioRelacao;
    }

    /**
     * Calcula o salário total dos funcionários, e retona
     * a quantidade de salários minimos de cada funcionário.
     * <p>
     * Valores podem ser obtidos com:
     *  getFuncionarioSalarioRelacao(): Map<Funcionario, Double>
     *  getSumOfSalaries(): BigDecimal
     * <p>
     * **/
    @Override
    public void visit(Tabela tabela) {
        funcionarioSalarioRelacao = new HashMap<>(tabela.getEntradas().size());
        for (Funcionario funcionario : tabela.getEntradas()) {
            funcionarioSalarioRelacao.put(funcionario, (funcionario.getSalario().doubleValue()/SALARIO_MINIMO));
            sumOfSalaries = sumOfSalaries.add(funcionario.getSalario());
        }
    }
}
