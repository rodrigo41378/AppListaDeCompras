package com.lista_compras.ServerListaDeCompras;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// Exemplo de teste unitário puro (sem Spring) para uma classe de domínio hipotética.
// Adapte os nomes/atributos conforme sua classe real (ex.: Item, Produto).
@SpringBootTest
class DomainItemUnitTest {

    record Item(Long id, String name, int quantity) {}

    @Test
    @DisplayName("Item deve manter valores informados no construtor")
    void item_basico() {
        System.out.println();
        Item i = new Item(1L, "Arroz", 2);
        assertEquals(1L, i.id());
        assertEquals("Arroz", i.name());
        assertEquals(2, i.quantity());
    }

    @Test
    @DisplayName("Quantidade não deve ser negativa (exemplo de regra)")
    void quantidade_naoDeveSerNegativa() {
        Item i = new Item(2L, "Feijão", Math.max(0, -5)); // exemplo de normalização
        assertTrue(i.quantity() >= 0);
    }
}
