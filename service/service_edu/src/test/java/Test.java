import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

@SpringBootTest
public class Test {



    private int i = 0;

    @org.junit.Test
    public void test(){
        boolean empty = StringUtils.isEmpty(i);
//        System.out.println(empty);
        Object i = this.i;
        if (i==null ||i.equals("")) {
            System.out.println(true);
        }
        else System.out.println(false);

    }
}
