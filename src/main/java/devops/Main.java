package devops;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Gson gson = new Gson();

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
        List<Map<String, String>> lista = ler(path);
        Map<String, String> item = new HashMap<>();

        try {
            if (tipo.equals("Estudantes") || tipo.equals("Professores") || tipo.equals("Disciplinas")) {
                System.out.print("Código: ");
                String codigo = scanner.nextLine();

                if (existeCodigo(codigo, path)) throw new Exception("Código já existe");

                item.put("codigo", codigo);

                if (!tipo.equals("Disciplinas")) {
                    System.out.print("CPF: ");
                    item.put("cpf", scanner.nextLine());
                }

                System.out.print("Nome: ");
                item.put("nome", scanner.nextLine());
            }

            lista.add(item);
            salvar(path, lista);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        menuOperacoes(tipo, path);
    }

    static void listar(String tipo, String path) {
        List<Map<String, String>> lista = ler(path);

        if (lista.isEmpty()) {
            System.out.println("Nenhum registro.");
        }

        for (Map<String, String> item : lista) {
            System.out.println(item);
        }

        menuOperacoes(tipo, path);
    }

    static void atualizar(String tipo, String path) {
        List<Map<String, String>> lista = ler(path);

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        for (Map<String, String> item : lista) {
            if (item.get("codigo").equals(codigo)) {
                for (String key : item.keySet()) {
                    System.out.print(key + ": ");
                    String valor = scanner.nextLine();
                    if (!valor.isEmpty()) {
                        item.put(key, valor);
                    }
                }
                salvar(path, lista);
                break;
            }
        }

        menuOperacoes(tipo, path);
    }

    static void excluir(String tipo, String path) {
        List<Map<String, String>> lista = ler(path);

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        lista.removeIf(item -> item.get("codigo").equals(codigo));

        salvar(path, lista);

        menuOperacoes(tipo, path);
    }

    static boolean existeCodigo(String codigo, String path) {
        List<Map<String, String>> lista = ler(path);
        return lista.stream().anyMatch(i -> i.get("codigo").equals(codigo));
    }

    static List<Map<String, String>> ler(String path) {
        try (Reader reader = new FileReader(path)) {
            Type type = new TypeToken<List<Map<String, String>>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static void salvar(String path, List<Map<String, String>> lista) {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}