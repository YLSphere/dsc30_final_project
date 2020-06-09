import org.junit.jupiter.api.Test;


public class HashTableTest {
    HashTable test = new HashTable(10);

    @Test
    public void insert() {
        test.insert(10);
        test.insert(1);
        test.insert(104);
        test.insert(15);
        test.insert(110);
        test.insert(108);
        test.insert(109);
        test.insert(100);

    }
}