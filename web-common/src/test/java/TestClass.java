import com.midea.common.config.JwtUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : 陆瑞
 * @version : 1.0.0
 * @date : 2020-06-05 02:26
 **/
@SpringBootTest
public class TestClass {
    public static void main(String[] args) {
        System.out.println(JwtUtil.getToken());
    }
}
