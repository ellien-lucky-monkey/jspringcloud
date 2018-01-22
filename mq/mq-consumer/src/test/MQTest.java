import com.jiangkui.cloud.mq.StartMq;
import com.jiangkui.cloud.mq.demo.MQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ellien
 * @date 2018/01/22 12:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartMq.class)
public class MQTest {

    @Autowired
    private MQSender sender;

    @Test
    public void hello() {
        sender.send();
    }
}
