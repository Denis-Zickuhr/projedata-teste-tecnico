package model.visitor;

import model.Tabela;

public interface TabelaVisitor {
    void visit(Tabela tabela);
}