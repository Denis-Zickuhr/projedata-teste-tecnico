# Projeto ProjeData - Teste Técnico
Este é o projeto ProjeData - Teste Técnico, que consiste em uma tabela de funcionários com informações relevantes sobre cada funcionário. O projeto é desenvolvido em Java e utiliza o padrão de projeto Visitor para realizar algumas operações complexas.

## Funcionamento
A tabela de funcionários é representada pela classe Tabela, que possui uma lista de objetos Funcionario. Cada Funcionario tem informações como nome, data de nascimento, salário e função.

### Execução
Para executar o projeto, é possível utilizar o método main na classe Tabela, que possui diversos exemplos de operações que podem ser realizadas na tabela. O código é bem comentado e explicativo.

### Visitor - Design Pattern
O padrão de projeto Visitor é utilizado para realizar operações complexas nos elementos da tabela, como cálculos, agrupamentos e ordenações. Para isso, são implementadas classes que estendem a interface TabelaVisitor e implementam os métodos visit para cada operação desejada.

```

public interface TabelaVisitor {
    void visit(Tabela tabela);
}

public class Tabela {

    /** ...rest of code... **/

    public void accept(TabelaVisitor visitor) {
        visitor.visit(this);
    }
}

public class VisitorConcreto implements TabelaVisitor{
    @Override
    public void visit(Tabela tabela) {
        // FAZ ALGO
    }
}

```

### Observações Importantes!
- Os valores salariais são formatados com separador de milhar como ponto e separador decimal como vírgula.

- As informações de data de nascimento são exibidas no formato dia/mês/ano.

- O projeto utiliza a classe BigDecimal para representar os valores salariais, garantindo precisão e evitar problemas com arredondamento de valores.

- Para realizar as operações de agrupamento por função, ordenação alfabética e cálculo de salários, são utilizados visitors específicos para cada tarefa.

- Para realizar a remoção de um funcionário da lista, é possível utilizar o método removeFuncionarioByNome(String nome) na classe Tabela ou remover o objeto em si, através de indice ou referência.

- A tabela já é inicializada com alguns funcionários inseridos, mas novos funcionários podem ser adicionados através do método addEntrada(Funcionario f).

 - Algumas funções complexas, como cálculos de aumento de salário, agrupamento por função e cálculo de salários em relação ao salário mínimo, são realizadas por meio do padrão de projeto Visitor, tornando o código mais modular e extensível.

### Como executar
Para executar o projeto, basta utilizar o método main na classe Tabela. Ao rodar o programa, os resultados das operações serão exibidos no console, conforme explicado em cada seção do método main.

### Autor
Denis Zickuhr
