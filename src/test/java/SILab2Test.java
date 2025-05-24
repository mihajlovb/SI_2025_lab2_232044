import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2Test {

    @Test
    void testEveryStatement() {
        // Тест 1: allItems == null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "9876543210123456"));
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // Тест 2: item со празно име
        Item item2 = new Item("", 3, 80, 0);
        RuntimeException ex2 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item2), "9876543210123456"));
        assertEquals("Invalid item!", ex2.getMessage());

        // Тест 3: невалидна должина на картичка
        Item item3 = new Item("Tablet", 2, 450, 0.15);
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item3), "98765432101234"));
        assertEquals("Invalid card number!", ex3.getMessage());

        // Тест 4: невалиден карактер во картичка
        Item item4 = new Item("Notebook", 1, 90, 0);
        RuntimeException ex4 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item4), "12a4b6cd89efghij"));
        assertEquals("Invalid character in card number!", ex4.getMessage());

        // Тест 5: валиден случај
        Item item5 = new Item("Marker", 4, 30, 0);
        double result = SILab2.checkCart(List.of(item5), "9876543210123456");
        assertEquals(120.0, result);
    }

    @Test
    void testMultipleCondition() {
        // MC1: price > 300 (T), discount = 0 (F), quantity = 1 (F)
        Item mc1 = new Item("Camera", 1, 400, 0);
        assertEquals(370.0, SILab2.checkCart(List.of(mc1), "1234432112344321"));

        // MC2: price = 100 (F), discount > 0 (T), quantity = 1 (F)
        Item mc2 = new Item("Speaker", 1, 100, 0.2);
        assertEquals(50.0, SILab2.checkCart(List.of(mc2), "3210321032103210"));

        // MC3: price = 100 (F), discount = 0 (F), quantity > 10 (T)
        Item mc3 = new Item("Backpack", 11, 100, 0);
        assertEquals(1070.0, SILab2.checkCart(List.of(mc3), "0000111122223333"));

        // MC4: price = 100 (F), discount = 0 (F), quantity = 2 (F)
        Item mc4 = new Item("PenCase", 2, 100, 0);
        assertEquals(200.0, SILab2.checkCart(List.of(mc4), "9999888877776666"));
    }
}
