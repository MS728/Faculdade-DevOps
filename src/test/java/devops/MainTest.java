package devops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @TempDir
    Path tempDir;

    @Test
    void deveRetornarListaVaziaQuandoArquivoNaoExiste() {
        String path = tempDir.resolve("inexistente.json").toString();

        List<Map<String, String>> lista = Main.ler(path);

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void deveSalvarELerRegistrosCorretamente() {
        String path = tempDir.resolve("estudantes.json").toString();

        List<Map<String, String>> lista = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("codigo", "1");
        item.put("nome", "Matheus");
        item.put("cpf", "12345678900");
        lista.add(item);

        Main.salvar(path, lista);
        List<Map<String, String>> resultado = Main.ler(path);

        assertEquals(1, resultado.size());
        assertEquals("1", resultado.get(0).get("codigo"));
        assertEquals("Matheus", resultado.get(0).get("nome"));
        assertEquals("12345678900", resultado.get(0).get("cpf"));
    }

    @Test
    void deveRetornarTrueQuandoCodigoExiste() {
        String path = tempDir.resolve("professores.json").toString();

        List<Map<String, String>> lista = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("codigo", "10");
        item.put("nome", "Carlos");
        item.put("cpf", "11111111111");
        lista.add(item);

        Main.salvar(path, lista);

        assertTrue(Main.existeCodigo("10", path));
    }

    @Test
    void deveRetornarFalseQuandoCodigoNaoExiste() {
        String path = tempDir.resolve("disciplinas.json").toString();

        List<Map<String, String>> lista = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("codigo", "20");
        item.put("nome", "DevOps");
        lista.add(item);

        Main.salvar(path, lista);

        assertFalse(Main.existeCodigo("99", path));
    }

    @Test
    void deveSobrescreverConteudoAoSalvarNovamente() {
        String path = tempDir.resolve("matriculas.json").toString();

        List<Map<String, String>> lista1 = new ArrayList<>();
        Map<String, String> item1 = new HashMap<>();
        item1.put("codigo", "1");
        item1.put("nome", "Primeiro");
        lista1.add(item1);

        Main.salvar(path, lista1);

        List<Map<String, String>> lista2 = new ArrayList<>();
        Map<String, String> item2 = new HashMap<>();
        item2.put("codigo", "2");
        item2.put("nome", "Segundo");
        lista2.add(item2);

        Main.salvar(path, lista2);
        List<Map<String, String>> resultado = Main.ler(path);

        assertEquals(1, resultado.size());
        assertEquals("2", resultado.get(0).get("codigo"));
        assertEquals("Segundo", resultado.get(0).get("nome"));
    }
}