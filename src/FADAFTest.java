import org.junit.jupiter.api.Test;

public class FADAFTest {
    FADAF test;

    @Test
    public void nUniqueKeys() {
        test = new FADAF(10);
        test.insert(1,2);
        test.insert(2,2);

        test.insert(3,2);
        test.insert(1,6);
        System.out.print(test.nUniqueKeys());

        System.out.print(test.removeAll(1));
        System.out.print(test.nUniqueKeys());




    }
}