package model.visitor;

import model.Pessoa;
import model.Tabela;

import java.util.Comparator;

public class OrdemAlfabeticaVisitor implements TabelaVisitor{

    @Override
    public void visit(Tabela tabela) {
        tabela.getEntradas().sort(Comparator.comparing(Pessoa::getNome));
    }
}
