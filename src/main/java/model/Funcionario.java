package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(LocalDate dataNascimento) {
        super(dataNascimento);
    }

    public Funcionario(String nome, LocalDate dataNascimento) {
        super(nome, dataNascimento);
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, FUNCAO funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        setFuncao(funcao);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(FUNCAO funcao) {
        /* Formata o Enum para String */
        this.funcao = funcao.name().charAt(0) + funcao.name().substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return getNome() + ": " + getDataNascimento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                + ", R$" + Tabela.DECIMAL_FORMAT_SALARIO.format(getSalario()) + ", " + getFuncao();
    }
}
