package model.visitor;

import model.Funcionario;
import model.Pessoa;
import model.Tabela;

import java.util.Comparator;
import java.util.stream.Stream;

public class IdadeVisitor implements TabelaVisitor {

    private final ORDEM ordem;
    private Funcionario selectedFuncionario;

    public IdadeVisitor(ORDEM ordem) {
        this.ordem = ordem;
    }

    public Funcionario getSelectedFuncionario() {
        return selectedFuncionario;
    }

    @Override
    public void visit(Tabela tabela) {
        Stream<Funcionario> stream = tabela.getEntradas().stream();
        if (ordem == ORDEM.MAIOR) {
            selectedFuncionario = stream
                    .min(Comparator.comparing(Pessoa::getDataNascimento))
                    .orElse(null);
        } else if (ordem == ORDEM.MENOR) {
            selectedFuncionario = stream
                    .max(Comparator.comparing(Pessoa::getDataNascimento))
                    .orElse(null);
        }
    }

    public enum ORDEM {
        MAIOR,
        MENOR
    }
}
