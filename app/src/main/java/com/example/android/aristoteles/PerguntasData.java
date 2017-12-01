package com.example.android.aristoteles;

/**
 * Created by jhoneshenrique on 10/27/2017.
 */

public class PerguntasData {
    public String Alternativas[] = {
            "Qual das alternativas é uma Proposição?",
            "Qual é o príncípio da não contradição?",
            "Qual alternativa corresponde a uma proposição composta?",
            "Qual das proposições abaixo é verdadeira?",
            "Qual das proposições abaixo não é verdadeira?"
    };

    public String alternativas [][] = {
            {"Que horas são?","Eletróns possuem carga elétrica negativa","Abra a porta!","Você deveria estudar"},
            {"Não pode ser verdadeiro e falso ao mesmo tempo","Pode ser verdadeiro e falso ao mesmo tempo","Só pode ser verdadeiro","Não pode ser nem verdadeiro nem falso"},
            {"João é bom aluno","Hoje tem prova!","5>2 e 5<10","Maria gosta de João"},
            {"Ancar é a capital da Turquia e Toronto é a capital do Canada","7x8=56 e Palmeiras é campeão mundial","O sol é um planeta e a terra uma estrela ","59 é primo e a raíz de 25 é 5"},
            {"1+1=2 ou Brasil fica na Europa","10 é a raíz quadrada de 100 ou cantar é advérbio","N.Y é a capital dos EUA ou Helsínquia é a capital da Noruega","269 é primo ou 128 é primo"}

    };

    public String dicas [][] = {
            {"Uma pergunta nunca é uma Proposição","Parabéns, você acertou!","Uma ordem nunca é Proposição","Um conselho nunca é uma Proposição"},
            {"Parabéns, você acertou","Lembre-se que como o nome indica, não pode haver contradição","Lembre-se que uma Proposição também pode assumir o valor Falso","Uma Proposição tem que possuir um valor Falso ou Verdadeiro"},
            {"Temos apenas um termo aqui!","Uma Proposição composta possui um conector","5>2 e 5<10","Temos apenas um termo aqui"},
            {"A capital do Canada é Otawa e não Tornoto","Lembre-se que com o conector E, as duas sentenças devem ser verdade","As duas Proposições são falsas","59 é primo e a raíz de 25 é 5"},
            {"Lembre-se que com o conector OU, basta que uma sentença seja verdadeira para termos verdadeiro","10 é a raíz quadrada de 100, logo a Proposição é verdadeira","N.Y é a capital dos EUA ou Helsínquia é a capital da Noruega","269 é primo, logo a Proposição é verdadeira"}
    };

    public String respostas [] = {
            "Eletróns possuem carga elétrica negativa","Não pode ser verdadeiro e falso ao mesmo tempo","5>2 e 5<10","59 é primo e a raíz de 25 é 5","N.Y é a capital dos EUA ou Helsínquia é a capital da Noruega"
    };

    public String dicaCorreta [] = {
            "Correto! Essa frase é verdadeira, logo é uma proposição!","Não pode ser verdadeiro e falso ao mesmo tempo","5>2 e 5<10","59 é primo e a raíz de 25 é 5","N.Y é a capital dos EUA ou Helsínquia é a capital da Noruega"
    };

    public String getQuestion(int a){
        String question = Alternativas[a];
        return question;
    }

    public String getChoice1(int a){
        String choice = alternativas[a][0];
        return choice;
    };

    public String getChoice2(int a){
        String choice = alternativas[a][1];
        return choice;
    };

    public String getChoice3(int a){
        String choice = alternativas[a][2];
        return choice;
    };

    public String getChoice4(int a){
        String choice = alternativas[a][3];
        return choice;
    };



    public String getHint1(int a){
        String choice = dicas[a][0];
        return choice;
    };

    public String getHint2(int a){
        String choice = dicas[a][1];
        return choice;
    };

    public String getHint3(int a){
        String choice = dicas[a][2];
        return choice;
    };

    public String getHint4(int a){
        String choice = dicas[a][3];
        return choice;
    };


    public String resposta(int a){
        String resposta = respostas[a];
        return resposta;
    };

    public String dicaRespCorreta(int a){
        String resposta = dicaCorreta[a];
        return resposta;
    };
}
