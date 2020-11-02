import com.wang.SpringSwaggerApplication;
import com.wang.bean.Employee;
import com.wang.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSwaggerApplication.class)
public class SeriveTest {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 测试service
     */
    @Test
    public void testEmployService(){
        Employee employee = employeeService.getEmployeeByName("张三");
        System.err.println("*********"+employee.getUsername());
    }


}
