package model.visitor;

import model.FUNCAO;
import model.Funcionario;
import model.Tabela;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgruparFuncionariosVisitor implements TabelaVisitor{

    private Map<String, List<Funcionario>> funcaoFuncionarioMap;
    private Map<Integer, List<Funcionario>> dataNascimentoFuncionarioMap;

    public Map<String, List<Funcionario>> getFuncaoFuncionarioMap() {
        return funcaoFuncionarioMap;
    }

    public Map<Integer, List<Funcionario>> getDataNascimentoFuncionarioMap() {
        return dataNascimentoFuncionarioMap;
    }

    @Override
    public void visit(Tabela tabela) {
        dataNascimentoFuncionarioMap = tabela.getEntradas().stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.getDataNascimento().getMonth().getValue()));

        funcaoFuncionarioMap = tabela.getEntradas().stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.getFuncao().toUpperCase()));
    }
}
