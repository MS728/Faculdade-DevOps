package devops;

import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static String pathEstudantes = "estudantes.json";
    static String pathProfessores = "professores.json";
    static String pathDisciplinas = "disciplinas.json";
    static String pathTurmas = "turmas.json";
    static String pathMatriculas = "matriculas.json";

    public static void main(String[] args) {
        System.out.println("Bem vindo ao sistema acadêmico!");
        menuPrincipal();
    }

    static void menuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Estudantes");
        System.out.println("2 - Disciplinas");
        System.out.println("3 - Professores");
        System.out.println("4 - Turmas");
        System.out.println("5 - Matrículas");
        System.out.println("9 - Sair");

        String op = scanner.nextLine();

        switch (op) {
            case "1": menuOperacoes("Estudantes", pathEstudantes); break;
            case "2": menuOperacoes("Disciplinas", pathDisciplinas); break;
            case "3": menuOperacoes("Professores", pathProfessores); break;
            case "4": menuOperacoes("Turmas", pathTurmas); break;
            case "5": menuOperacoes("Matrículas", pathMatriculas); break;
            case "9": return;
            default:
                System.out.println("Opção inválida");
                menuPrincipal();
        }
    }

    static void menuOperacoes(String tipo, String path) {
        System.out.println("\n=== " + tipo + " ===");
        System.out.println("1 - Incluir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Excluir");
        System.out.println("9 - Voltar");

        String op = scanner.nextLine();

        switch (op) {
            case "1": incluir(tipo, path); break;
            case "2": listar(tipo, path); break;
            case "3": atualizar(tipo, path); break;
            case "4": excluir(tipo, path); break;
            case "9": menuPrincipal(); break;
            default:
                System.out.println("Opção inválida");
                menuOperacoes(tipo, path);
        }
    }

    static void incluir(String tipo, String path) {
	
    }

    static void listar(String tipo, String path) {
	
    }

    static void atualizar(String tipo, String path) {
	
    }

    static void excluir(String tipo, String path) {
	
    }

    
}