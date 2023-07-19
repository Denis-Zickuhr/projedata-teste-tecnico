package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class Pessoa {
    private final LocalDate dataNascimento;
    private String nome;

    public Pessoa(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "nome='" + nome + "', " + "dataNascimento='" + dataNascimento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + "'";
    }
}
