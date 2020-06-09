import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class DAFTreeTest {
    DAFTree test = new DAFTree();

    @Test
    public void insert() {

        test.insert(1,3);
        test.insert(2,3);
        test.insert(2,4);
        test.insert(2,5);
        test.insert(2,6);
        test.iterator();



        //System.out.println(test.iterator().hasNext());




    }
}