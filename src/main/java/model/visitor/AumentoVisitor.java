package model.visitor;

import model.Funcionario;
import model.Tabela;

import java.math.BigDecimal;

/** 3.4 -> Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor **/
public class AumentoVisitor implements TabelaVisitor{

    private final double porcetagemDeAumento;

    public AumentoVisitor(double porcentagemDeAumento){
        this.porcetagemDeAumento = porcentagemDeAumento / 100;
    }

    @Override
    public void visit(Tabela tabela) {
        for (Funcionario funcionario: tabela.getEntradas()
             ) {
            funcionario.setSalario(funcionario.getSalario().add(BigDecimal.valueOf(funcionario.getSalario().doubleValue() * porcetagemDeAumento)));
        }
    }
}
