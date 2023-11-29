import Model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;

public class GestorAcademico {
    static Scanner sc = new Scanner(System.in);

    public static ArrayList<Aluno> alunos = new ArrayList<>();
    public static ArrayList<Professor> professores = new ArrayList<>();
    public static ArrayList<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            menuInicial();
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> menuAluno(op);
                case 2 -> menuProfessor(op);
                case 3 -> menuTurma(op);
                case 4 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opcao invalida!");
            }
        }
    }

    public static void menuInicial() {
        System.out.print("\033[H\033[2J");
        System.out.println("\n\033[1m===============================");
        System.out.println("        Gestor Academico");
        System.out.println("===============================\033[0m");
        System.out.println("\033[1;32m1 - Alunos\033[0m");
        System.out.println("\033[1;32m2 - Professores\033[0m");
        System.out.println("\033[1;32m3 - Turmas\033[0m");
        System.out.println("\033[1;31m4 - Sair\033[0m");
        System.out.println("\033[1m===============================\033[0m");
        System.out.print("Escolha uma opcao para gerenciar: ");
    }

    public static void menuTurma(int op) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m===============================");
            System.out.println("        Gestor Academico - Turma");
            System.out.println("===============================\033[0m");
            System.out.println("\033[1;32m1 - Cadastrar Turma\033[0m");
            System.out.println("\033[1;32m2 - Listar Turmas\033[0m");
            System.out.println("\033[1;32m3 - Alterar dados da turma\033[0m");
            System.out.println("\033[1;32m4 - Excluir Turma\033[0m");
            System.out.println("\033[1;32m5 - Adicionar Aluno\033[0m");
            System.out.println("\033[1;32m6 - Remover Aluno\033[0m");
            System.out.println("\033[1;32m7 - Listar Alunos na turma\033[0m");
            System.out.println("\033[1;32m8 - Definir professor\033[0m");
            System.out.println("\033[1;31m9 - Voltar\033[0m");
            System.out.println("\033[1m===============================\033[0m");

            System.out.print("Escolha uma opcao: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt){
                case 1 -> {
                    int semestre; int capacidadeMax;

                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Cadastrar Turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    System.out.println("\nInserira o periodo da turma (Ex: manha, tarde, noite):");
                    System.out.print("-> ");
                    String periodo = sc.next();
                    sc.nextLine();

                    do{
                        System.out.println("\nInserir a capacidade maxima de alunos na turma:");
                        System.out.print("-> ");
                        capacidadeMax = sc.nextInt();
                        sc.nextLine();

                        if(capacidadeMax > 0){
                            break;
                        }else{
                            System.out.println("capacidade invalida!");
                        }
                    } while(true);

                    do{
                        System.out.println("\nInserir o semestre atual da turma:");
                        System.out.print("-> ");
                        semestre = sc.nextInt();
                        sc.nextLine();

                        if(semestre > 0){
                            break;
                        }else{
                            System.out.println("semestre invalido!");
                        }
                    } while(true);

                    Turma turma = new Turma(nome, periodo, capacidadeMax, LocalDate.now().getYear(), semestre, null, null);
                    turmas.add(turma);
                    System.out.println("\n Turma cadastrada com sucesso!");
                }
                case 2 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Listar Turmas");
                    System.out.println("=======================================\033[0m");
                    if (!turmas.isEmpty()){
                        for (Turma turma : turmas) {
                            System.out.println("\n"+turma);
                            System.out.println("=======================================\033[0m");
                        }
                    }else{
                        System.out.println("\nNenhuma turma cadastrada");
                    }
                }
                case 3 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Alterar dados da turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma que deseja alterar:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    alterarDadosTurma(turma);
                }
                case 4 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Excluir dados da turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma que deseja excluir:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    deletarDados(op, turma);
                }
                case 5 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Adicionar aluno a turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma que o aluno sera incluido:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    if (turma != null) {
                        int optAluno = 0;
                        System.out.println("\nTurma encontrada");

                        while(optAluno != 3){
                            System.out.print("\033[H\033[2J");
                            System.out.println("\n\033[1m=======================================");
                            System.out.println("Gestor Academico -  Adicionar aluno a turma");
                            System.out.println("=======================================\033[0m");
                            menuLocalizarAluno();
                            System.out.print("Escolha uma opcao: ");
                            optAluno= sc.nextInt();
                            sc.nextLine();

                            switch (optAluno){
                                case 1 -> {
                                    System.out.println("Inserir o nome do aluno:");
                                    System.out.print("-> ");
                                    String nomeAluno = sc.next();
                                    sc.nextLine();

                                    Object aluno = localizarNome(1, nomeAluno);
                                    inserirAlunosTurma(turma, aluno);
                                }
                                case 2-> {
                                    System.out.println("Inserir o numero de matricula do aluno:");
                                    System.out.print("-> ");
                                    long matricula = sc.nextLong();
                                    sc.nextLine();

                                    Object aluno = localizarRegistro(1, matricula);
                                    inserirAlunosTurma(turma, aluno);
                                }
                                case 3 -> System.out.println("\nVoltando ao menu anterior...");
                                default -> System.out.println("\nOpcao invalida!");
                            }
                        }
                    } else {
                        System.out.println("\nTurma nao encontrada");
                    }
                }
                case 6 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Remover aluno da turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma que o aluno sera removido:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    if (turma != null) {
                        int optAluno = 0;
                        System.out.println("\nTurma encontrada");

                        while (optAluno != 3) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("\n\033[1m=======================================");
                            System.out.println("Gestor Academico -  Remover aluno da turma");
                            System.out.println("=======================================\033[0m");
                            menuLocalizarAluno();
                            System.out.print("Escolha uma opcao: ");
                            optAluno = sc.nextInt();
                            sc.nextLine();

                            switch (optAluno) {
                                case 1 -> {
                                    System.out.println("Inserir o nome do aluno:");
                                    System.out.print("-> ");
                                    String nomeAluno = sc.next();
                                    sc.nextLine();

                                    Object aluno = localizarNome(1, nomeAluno);
                                    removerAlunosTurma(turma, aluno);
                                }
                                case 2 -> {
                                    System.out.println("Inserir o numero de matricula do aluno:");
                                    System.out.print("-> ");
                                    long matricula = sc.nextLong();
                                    sc.nextLine();

                                    Object aluno = localizarRegistro(1, matricula);
                                    removerAlunosTurma(turma, aluno);
                                }
                                case 3 -> System.out.println("\nVoltando ao menu anterior...");
                                default -> System.out.println("\nOpcao invalida!");
                            }
                        }
                    } else {
                        System.out.println("\nTurma nao encontrada");
                    }
                }
                case 7 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Listar alunos da turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    if (turma != null) {
                        System.out.println("\nTurma encontrada");
                        int i = turmas.indexOf(turma);
                        System.out.println("Alunos: \n" + turmas.get(i).listarAlunos());
                    } else {
                        System.out.println("\nTurma nao encontrada");
                    }
                }
                case 8 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Atribuir professor a turma");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome da turma:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    Object turma = localizarNome(op, nome);
                    if (turma != null) {
                        int optProf = 0;
                        System.out.println("\nTurma encontrada");

                        while (optProf != 3) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("\n\033[1m=======================================");
                            System.out.println("Gestor Academico -  Remover aluno da turma");
                            System.out.println("=======================================\033[0m");
                            menuLocalizarProfessor();
                            System.out.print("Escolha uma opcao: ");
                            optProf = sc.nextInt();
                            sc.nextLine();

                            switch (optProf) {
                                case 1 -> {
                                    System.out.println("Inserir o nome do professor:");
                                    System.out.print("-> ");
                                    String nomeProf = sc.next();
                                    sc.nextLine();

                                    Object professor = localizarNome(2, nomeProf);
                                    atribuirProfTurma(turma, professor);
                                }
                                case 2 -> {
                                    System.out.println("Inserir o numero de registro do professor:");
                                    System.out.print("-> ");
                                    long registro = sc.nextLong();
                                    sc.nextLine();

                                    Object professor = localizarRegistro(2, registro);
                                    atribuirProfTurma(turma, professor);
                                }
                                case 3 -> System.out.println("\nVoltando ao menu anterior...");
                                default -> System.out.println("\nOpcao invalida!");
                            }
                        }
                    } else {
                        System.out.println("\nTurma nao encontrada");
                    }
                }
                case 9 -> {
                    System.out.println("\nVoltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("\nOpcao invalida!");
            }
        }
    }

    public static void menuProfessor(int op) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m===============================");
            System.out.println("        Gestor Academico - Professor");
            System.out.println("===============================\033[0m");
            System.out.println("\033[1;32m1 - Cadastrar professor\033[0m");
            System.out.println("\033[1;32m2 - Listar professor\033[0m");
            System.out.println("\033[1;32m3 - Alterar dados do professor\033[0m");
            System.out.println("\033[1;32m4 - Excluir professor\033[0m");
            System.out.println("\033[1;32m5 - Inserir Disciplinas\033[0m");
            System.out.println("\033[1;31m6 - Voltar\033[0m");
            System.out.println("\033[1m===============================\033[0m");

            System.out.print("Escolha uma opcao: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt){
                case 1 -> {
                    int dia; int mes; int ano;
                    Random random = new Random();

                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Cadastrar Professor");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome do professor:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    System.out.println("Inserira a especialidade do professor do professor:");
                    System.out.print("-> ");
                    String especialidade = sc.next();
                    sc.nextLine();

                    do{
                        System.out.println("\nInserir o ano do nascimento do professor:");
                        System.out.print("-> ");
                        ano = sc.nextInt();
                        sc.nextLine();

                        if(ano <= LocalDate.now().getYear() - 18 && ano >= LocalDate.now().getYear() - 100){
                            break;
                        }else{
                            System.out.println("Ano de Nascimento Invalido!");
                        }
                    } while(true);

                    do{
                        System.out.println("\nInserir o mes do nascimento do prossor:");
                        System.out.print("-> ");
                        mes = sc.nextInt();
                        sc.nextLine();

                        if(mes >= 1 && mes <= 12){
                            break;
                        }else{
                            System.out.println("Mes de Nascimento Invalido!");
                        }
                    } while(true);

                    do{
                        System.out.println("\nInserir o dia do nascimento do professor:");
                        System.out.print("-> ");
                        dia = sc.nextInt();
                        sc.nextLine();

                        if(dia >= 1 && dia <= YearMonth.of(ano, mes).lengthOfMonth()){
                            break;
                        }else{
                            System.out.println("Dia de Nascimento Invalido!");
                        }
                    } while(true);

                    long registro = Long.parseLong(String.valueOf(dia + mes + ano + (random.nextInt(900000) + 100000)));

                    Professor professor = new Professor(nome, dia+"/"+mes+"/"+ano, registro*7, especialidade, null);
                    professores.add(professor);
                    System.out.println("\n Professor cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Listar Professores");
                    System.out.println("=======================================\033[0m");
                    if (!professores.isEmpty()){
                        for (Professor professor : professores) {
                            System.out.println("\n"+professor);
                            System.out.println("=======================================\033[0m");
                        }
                    }else{
                        System.out.println("\nNenhum professor cadastrado");
                    }
                }
                case 3 -> {
                    int optAlt = 0;
                    while(optAlt != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Alterar dados do Professor");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarProfessor();

                        System.out.print("Escolha uma opcao: ");
                        optAlt = sc.nextInt();
                        sc.nextLine();

                        switch (optAlt) {
                            case 1 -> {
                                System.out.println("Inserir o nome do professor:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object professor = localizarNome(op, nome);
                                alterarDadosProfessor(professor);
                            }
                            case 2-> {
                                System.out.println("Inserir o numero de registro do professor:");
                                System.out.print("-> ");
                                long registro = sc.nextLong();
                                sc.nextLine();

                                Object professor = localizarRegistro(op, registro);
                                alterarDadosProfessor(professor);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 4 -> {
                    int optRm = 0;
                    while(optRm != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Excluir dados do Professor");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarProfessor();

                        System.out.print("Escolha uma opcao: ");
                        optRm = sc.nextInt();
                        sc.nextLine();

                        switch (optRm) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object professor = localizarNome(op, nome);
                                deletarDados(op, professor);

                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object professor = localizarRegistro(op, matricula);
                                deletarDados(op, professor);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 5 -> {
                    int optD = 0;
                    while(optD != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Inserir Disciplinas do professor");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarProfessor();

                        System.out.print("Escolha uma opcao: ");
                        optD = sc.nextInt();
                        sc.nextLine();

                        switch (optD) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object professor = localizarNome(op, nome);
                                cadastrarDisciplinas(professor);
                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object professor = localizarRegistro(op, matricula);
                                cadastrarDisciplinas(professor);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 6 -> {
                    System.out.println("\nVoltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("\nOpcao invalida!");
            }
        }
    }

    public static void menuAluno(int op) {
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m===============================");
            System.out.println("      Gestor Academico - Aluno");
            System.out.println("===============================\033[0m");
            System.out.println("\033[1;32m1 - Cadastrar Aluno\033[0m");
            System.out.println("\033[1;32m2 - Consultar Alunos cadastrados\033[0m");
            System.out.println("\033[1;32m3 - Alterar Dados do Aluno\033[0m");
            System.out.println("\033[1;32m4 - Excluir Alunos\033[0m");
            System.out.println("\033[1;32m5 - Inserir Notas\033[0m");
            System.out.println("\033[1;32m6 - Consultar Media\033[0m");
            System.out.println("\033[1;31m7 - Voltar\033[0m");
            System.out.println("\033[1m===============================\033[0m");

            System.out.print("Escolha uma opcao: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt){
                case 1 -> {
                    int dia; int mes; int ano;
                    Random random = new Random();

                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Cadastrar Aluno");
                    System.out.println("=======================================\033[0m");
                    System.out.println("Inserir o nome do aluno:");
                    System.out.print("-> ");
                    String nome = sc.next();
                    sc.nextLine();

                    do{
                        System.out.println("\nInserir o ano do nascimento do aluno:");
                        System.out.print("-> ");
                        ano = sc.nextInt();
                        sc.nextLine();

                        if(ano <= LocalDate.now().getYear() - 5 && ano >= LocalDate.now().getYear() - 100){
                            break;
                        }else{
                            System.out.println("Ano de Nascimento Invalido!");
                        }
                    } while(true);

                    do{
                        System.out.println("\nInserir o mes do nascimento do aluno:");
                        System.out.print("-> ");
                        mes = sc.nextInt();
                        sc.nextLine();

                        if(mes >= 1 && mes <= 12){
                            break;
                        }else{
                            System.out.println("Mes de Nascimento Invalido!");
                        }
                    } while(true);

                    do{
                        System.out.println("\nInserir o dia do nascimento do aluno:");
                        System.out.print("-> ");
                        dia = sc.nextInt();
                        sc.nextLine();

                        if(dia >= 1 && dia <= YearMonth.of(ano, mes).lengthOfMonth()){
                            break;
                        }else{
                            System.out.println("Dia de Nascimento Invalido!");
                        }
                    } while(true);

                    long matricula = Long.parseLong(String.valueOf(dia + mes + ano + (random.nextInt(900000) + 100000)));

                    Aluno aluno = new Aluno(nome, dia+"/"+mes+"/"+ano, matricula*7, null);
                    alunos.add(aluno);
                    System.out.println("\n Aluno cadastrado com sucesso!");
                }
                case 2 ->{
                    System.out.print("\033[H\033[2J");
                    System.out.println("\n\033[1m=======================================");
                    System.out.println("Gestor Academico - Listar Alunos");
                    System.out.println("=======================================\033[0m");
                    if (!alunos.isEmpty()){
                        for (Aluno aluno : alunos) {
                            System.out.println("\n"+aluno);
                            System.out.println("=======================================\033[0m");
                        }
                    }else{
                        System.out.println("\nNenhum aluno cadastrado");
                    }

                }
                case 3 -> {
                    int optAlt = 0;
                    while(optAlt != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Alterar dados do Aluno");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarAluno();

                        System.out.print("Escolha uma opcao: ");
                        optAlt = sc.nextInt();
                        sc.nextLine();

                        switch (optAlt) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object aluno = localizarNome(op, nome);
                                alterarDadosAluno(aluno);
                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object aluno = localizarRegistro(op, matricula);
                                alterarDadosAluno(aluno);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 4 -> {
                    int optRm = 0;
                    while(optRm != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Excluir dados do Alunos");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarAluno();

                        System.out.print("Escolha uma opcao: ");
                        optRm = sc.nextInt();
                        sc.nextLine();

                        switch (optRm) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object aluno = localizarNome(op, nome);
                                deletarDados(op, aluno);
                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object aluno = localizarRegistro(op, matricula);
                                deletarDados(op, aluno);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 5 -> {
                    int optN = 0;
                    while(optN != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Inserir notas do Alunos");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarAluno();

                        System.out.print("Escolha uma opcao: ");
                        optN = sc.nextInt();
                        sc.nextLine();

                        switch (optN) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object aluno = localizarNome(op, nome);
                                cadastrarNotas(aluno);
                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object aluno = localizarRegistro(op, matricula);
                                cadastrarNotas(aluno);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 6 -> {
                    int optM = 0;
                    while(optM != 3) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("\n\033[1m=======================================");
                        System.out.println("Gestor Academico - Calcular media das notas do Alunos");
                        System.out.println("=======================================\033[0m");
                        menuLocalizarAluno();

                        System.out.print("Escolha uma opcao: ");
                        optM = sc.nextInt();
                        sc.nextLine();

                        switch (optM) {
                            case 1 -> {
                                System.out.println("Inserir o nome do aluno:");
                                System.out.print("-> ");
                                String nome = sc.next();
                                sc.nextLine();

                                Object aluno = localizarNome(op, nome);
                                buscarMedia(aluno);

                            }
                            case 2-> {
                                System.out.println("Inserir o numero de matricula do aluno:");
                                System.out.print("-> ");
                                long matricula = sc.nextLong();
                                sc.nextLine();

                                Object aluno = localizarRegistro(op, matricula);
                                buscarMedia(aluno);
                            }
                            case 3 -> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("\nOpcao invalida!");
                        }
                    }
                }
                case 7 -> {
                    System.out.println("\nVoltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("\nOpcao invalida!");
            }
        }
    }

    private static void buscarMedia(Object aluno) {
        if (aluno != null) {
            System.out.println("\nAluno encontrado");
            int i = alunos.indexOf(aluno);

            double m = alunos.get(i).calcularMedia();

            if (m == -1.0){
                System.out.println("\nNao ha notas cadastradas");
            }else{
                System.out.println("\nA media das notas do aluno " + alunos.get(i).getNome() + "e: " + m);
            }
        }else{
            System.out.println("\nAluno nao encontrado");
        }
    }

    private static void cadastrarDisciplinas(Object professor) {
        ArrayList<String> disciplinas = new ArrayList<>();
        int disc = 0;

        if (professor != null) {
            while(disc < 1){
                System.out.println("\nProfessor encontrado");
                System.out.print("\033[H\033[2J");
                System.out.println("\n\033[1m=======================================");
                System.out.println("Gestor Academico - Inserir Disciplinas do professor");
                System.out.println("=======================================\033[0m");
                System.out.println("Quantas quantas disciplinas o professor possuira:");
                System.out.print("-> ");
                disc = sc.nextInt();
                sc.nextLine();
            }

            for (int i = 0; i < disc; i++){
                System.out.println("Insira o nome da disciplina - " + (i+1) + ":");
                System.out.print("-> ");
                String d = sc.next();
                sc.nextLine();
                if (!d.isEmpty()){
                    disciplinas.add(d);
                } else{
                    System.out.println("\nValor invalido");
                    i--;
                }
            }

            int i = professores.indexOf(professor);
            professores.set(i, new Professor(professores.get(i).getNome(), professores.get(i).getData_nasc(), professores.get(i).getRegistro(), professores.get(i).getEspecialidade(), disciplinas));
        }else{
            System.out.println("\nAluno nao encontrado");
        }
    }

    private static void cadastrarNotas(Object aluno) {
        ArrayList<Double> notas = new ArrayList<>();
        int mencoes = 0;

        if (aluno != null) {
            while(mencoes < 1){
                System.out.println("\nAluno encontrado");
                System.out.print("\033[H\033[2J");
                System.out.println("\n\033[1m=======================================");
                System.out.println("Gestor Academico - Inserir notas do Alunos");
                System.out.println("=======================================\033[0m");
                System.out.println("Quantas mencoes o aluno possuira:");
                System.out.print("-> ");
                mencoes = sc.nextInt();
                sc.nextLine();
            }

            for (int i = 0; i < mencoes; i++){
                System.out.println("Insira o valor da Nota - " + (i+1) + ":");
                System.out.print("-> ");
                double nota = sc.nextDouble();
                sc.nextLine();
                if (nota <= 10 && nota >= 0){
                    notas.add(nota);
                } else{
                    System.out.println("\nValor invalido");
                    i--;
                }
            }

            int i = alunos.indexOf(aluno);
            alunos.set(i, new Aluno(alunos.get(i).getNome(), alunos.get(i).getData_nasc(), alunos.get(i).getMatricula(), notas));
        }else{
            System.out.println("\nAluno nao encontrado");
        }
    }

    private static void inserirAlunosTurma(Object turma, Object aluno) {
        if (aluno != null) {
            System.out.println("Aluno encontrado");
            int i = turmas.indexOf(turma);

            if (turmas.get(i).getAlunos() == null) turmas.get(i).setAlunos(new ArrayList<>()) ;

            if (turmas.get(i).getCapacidadeMax() > turmas.get(i).getAlunos().size()){
                turmas.get(i).adicionarAluno((Aluno) aluno);
                System.out.println("Aluno adicionado a turma com sucesso!");
            }else {
                System.out.println("Capacidade maxima da turma atingida");
            }

        }else{
            System.out.println("\nAluno nao encontrado");
        }
    }

    private static void atribuirProfTurma(Object turma, Object prof) {
        if (prof != null) {
            System.out.println("Professor encontrado");
            int i = turmas.indexOf(turma);
            boolean profAtrib = false;

            for (Turma value : turmas) {
                if (value.getProfessor() != null && value.getProfessor().equals(prof)) {
                    profAtrib = true;
                    break;
                }
            }

            if (profAtrib){
                System.out.println("Professor ja atribuido a uma turma!");
            }else{
                turmas.get(i).setProfessor((Professor) prof);
                System.out.println("Professor atribuido com sucesso!");
            }
        }else{
            System.out.println("\nProfessor nao encontrado");
        }
    }

    private static void removerAlunosTurma(Object turma, Object aluno) {
        if (aluno != null) {
            System.out.println("Aluno encontrado");
            int i = turmas.indexOf(turma);
            int op = -1;
            while(op != 0){
                System.out.println("Tem certeza que deseja remover esse aluno? 1 - SIM | 0 - NAO");
                System.out.print("-> ");
                op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1 -> {
                        turmas.get(i).removerAluno((Aluno) aluno);
                        System.out.println("Aluno removido da turma com sucesso!");
                        op = 0;
                    }
                    case 0-> System.out.println("\nVoltando ao menu anterior...");
                    default -> System.out.println("Opcao invalida!");
                }
            }
        }else{
            System.out.println("\nAluno nao encontrado");
        }
    }

    public static void menuLocalizarAluno(){
        System.out.println("\033[1mVoce deseja buscar o aluno por:\033[0m");
        System.out.println("\033[1;32m1 - Nome\033[0m");
        System.out.println("\033[1;32m2 - Matricula\033[0m");
        System.out.println("\033[1;31m3 - Voltar\033[0m");
    }

    public static void menuLocalizarProfessor(){
        System.out.println("\033[1mVoce deseja buscar o professor por:\033[0m");
        System.out.println("\033[1;32m1 - Nome\033[0m");
        System.out.println("\033[1;32m2 - Registro\033[0m");
        System.out.println("\033[1;31m3 - Voltar\033[0m");
    }

    public static void alterarDadosAluno(Object aluno){
        if (aluno != null) {
            System.out.println("\nAluno encontrado");
            int i = alunos.indexOf(aluno); int dia; int mes; int ano;

            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m=======================================");
            System.out.println("Gestor Academico - Alterar dados do Aluno");
            System.out.println("=======================================\033[0m");
            System.out.println("Inserir o novo nome do aluno:");
            System.out.print("-> ");
            String nomeAlt = sc.next();
            sc.nextLine();

            do{
                System.out.println("\nInserir o ano do nascimento do aluno:");
                System.out.print("-> ");
                ano = sc.nextInt();
                sc.nextLine();

                if(ano <= LocalDate.now().getYear() - 5 && ano >= LocalDate.now().getYear() - 100){
                    break;
                }else{
                    System.out.println("Ano de Nascimento Invalido!");
                }
            } while(true);

            do{
                System.out.println("\nInserir o mes do nascimento do aluno:");
                System.out.print("-> ");
                mes = sc.nextInt();
                sc.nextLine();

                if(mes >= 1 && mes <= 12){
                    break;
                }else{
                    System.out.println("Mes de Nascimento Invalido!");
                }
            } while(true);

            do{
                System.out.println("\nInserir o dia do nascimento do aluno:");
                System.out.print("-> ");
                dia = sc.nextInt();
                sc.nextLine();

                if(dia >= 1 && dia <= YearMonth.of(ano, mes).lengthOfMonth()){
                    break;
                }else{
                    System.out.println("Dia de Nascimento Invalido!");
                }
            } while(true);

            alunos.set(i, new Aluno(nomeAlt, dia+"/"+mes+"/"+ano, alunos.get(i).getMatricula(), null));
            System.out.println("\n Dados do aluno alterados com sucesso!");
        } else {
            System.out.println("\nAluno nao encontrado");
        }
    }

    public static void alterarDadosTurma(Object turma){
        if (turma != null) {
            System.out.println("\nTurma encontrada");
            int i = turmas.indexOf(turma); int semestre; int capacidadeMax;

            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m=======================================");
            System.out.println("Gestor Academico - Alterar Turma");
            System.out.println("=======================================\033[0m");
            System.out.println("Inserir o novo nome da turma:");
            System.out.print("-> ");
            String nome = sc.next();
            sc.nextLine();

            System.out.println("Inserira o periodo da turma (Ex: manha, tarde, noite):");
            System.out.print("-> ");
            String periodo = sc.next();
            sc.nextLine();

            do{
                System.out.println("\nInserir a capacidade maxima de alunos na turma:");
                System.out.print("-> ");
                capacidadeMax = sc.nextInt();
                sc.nextLine();

                if(capacidadeMax > 0){
                    break;
                }else{
                    System.out.println("capacidade invalida!");
                }
            } while(true);

            do{
                System.out.println("\nInserir o semestre atual da turma:");
                System.out.print("-> ");
                semestre = sc.nextInt();
                sc.nextLine();

                if(semestre > 0){
                    break;
                }else{
                    System.out.println("semestre invalido!");
                }
            } while(true);

            turmas.set(i, new Turma(nome, periodo, capacidadeMax, LocalDate.now().getYear(), semestre, turmas.get(i).getAlunos(), turmas.get(i).getProfessor()));
            System.out.println("\n Turma alterada com sucesso!");
        } else {
            System.out.println("\nTurma nao encontrada");
        }
    }

    public static void alterarDadosProfessor(Object professor){
        if (professor != null) {
            System.out.println("\nProfessor encontrado");
            int i = professores.indexOf(professor); int dia; int mes; int ano;

            System.out.print("\033[H\033[2J");
            System.out.println("\n\033[1m=======================================");
            System.out.println("Gestor Academico - Alterar dados do Professor");
            System.out.println("=======================================\033[0m");
            System.out.println("Inserir o novo nome do professor:");
            System.out.print("-> ");
            String nomeAlt = sc.next();
            sc.nextLine();

            System.out.println("Inserira a especialidade do professor do professor:");
            System.out.print("-> ");
            String especialidade = sc.next();
            sc.nextLine();

            do{
                System.out.println("\nInserir o ano do nascimento do professor:");
                System.out.print("-> ");
                ano = sc.nextInt();
                sc.nextLine();

                if(ano <= LocalDate.now().getYear() - 18 && ano >= LocalDate.now().getYear() - 100){
                    break;
                }else{
                    System.out.println("Ano de Nascimento Invalido!");
                }
            } while(true);

            do{
                System.out.println("\nInserir o mes do nascimento do prossor:");
                System.out.print("-> ");
                mes = sc.nextInt();
                sc.nextLine();

                if(mes >= 1 && mes <= 12){
                    break;
                }else{
                    System.out.println("Mes de Nascimento Invalido!");
                }
            } while(true);

            do{
                System.out.println("\nInserir o dia do nascimento do professor:");
                System.out.print("-> ");
                dia = sc.nextInt();
                sc.nextLine();

                if(dia >= 1 && dia <= YearMonth.of(ano, mes).lengthOfMonth()){
                    break;
                }else{
                    System.out.println("Dia de Nascimento Invalido!");
                }
            } while(true);

            professores.set(i, new Professor(nomeAlt, dia+"/"+mes+"/"+ano, professores.get(i).getRegistro(), especialidade, null));
            System.out.println("\n Dados do professor alterados com sucesso!");
        } else {
            System.out.println("\nProfessor nao encontrado");
        }
    }

    private static void deletarDados(int opt, Object obj) {
        switch (opt){
            case 1 -> {
                if (obj != null) {
                    int op = -1;
                    while(op != 0){
                        System.out.println("\nAluno encontrado");
                        System.out.println("Tem certeza que deseja deleter esse aluno? 1 - SIM | 0 - NAO");
                        System.out.print("-> ");
                        op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1 -> {
                                alunos.remove(obj);
                                System.out.println("Aluno deletado com sucesso!");
                                op = 0;
                            }
                            case 0-> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("Opcao invalida!");
                        }
                    }
                } else {
                    System.out.println("\nAluno nao encontrado");
                }
            }
            case 2 -> {
                if (obj != null) {
                    int op = -1;
                    while(op != 0){
                        System.out.println("\nProfessor encontrado");
                        System.out.println("Tem certeza que deseja deleter esse professor? 1 - SIM | 0 - NAO");
                        System.out.print("-> ");
                        op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1 -> {
                                professores.remove(obj);
                                System.out.println("Professor deletado com sucesso!");
                                op = 0;
                            }
                            case 0-> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("Opcao invalida!");
                        }
                    }
                } else {
                    System.out.println("\nProfessor nao encontrado");
                }
            }
            case 3 -> {
                if (obj != null) {
                    int op = -1;
                    while(op != 0){
                        System.out.println("\nTurma encontrada");
                        System.out.println("Tem certeza que deseja deleter essa turma? 1 - SIM | 0 - NAO");
                        System.out.print("-> ");
                        op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1 -> {
                                turmas.remove(obj);
                                System.out.println("Turma deletada com sucesso!");
                                op = 0;
                            }
                            case 0-> System.out.println("\nVoltando ao menu anterior...");
                            default -> System.out.println("Opcao invalida!");
                        }
                    }
                } else {
                    System.out.println("\nTurma nao encontrada");
                }
            }
            default -> System.out.println("Opcao Invalida");
        }
    }

    public static Object localizarNome(int op, String nome){
        switch (op){
            case 1 -> {
                for (Aluno aluno : alunos) {
                    if (aluno.getNome().equals(nome.toLowerCase().trim())) {
                        return aluno;
                    }
                }
            }
            case 2 ->{
                for (Professor professor : professores) {
                    if (professor.getNome().equals(nome.toLowerCase().trim())) {
                        return professor;
                    }
                }
            }
            case 3 ->{
                for (Turma turma : turmas) {
                    if (turma.getNome().equals(nome.toLowerCase().trim())) {
                        return turma;
                    }
                }
            }
            default -> System.out.println("Opcao Invalida");
        }
        return null;
    }

    public static Object localizarRegistro(int op, long registro){
        switch (op){
            case 1 -> {
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula() == registro) {
                        return aluno;
                    }
                }
            }
            case 2 ->{
                for (Professor professor : professores) {
                    if (professor.getRegistro() == registro) {
                        return professor;
                    }
                }
            }
            default -> System.out.println("Opcao Invalida");
        }

        return null;
    }
}