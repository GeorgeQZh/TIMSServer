package ustc.sse.tims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TimsApplication {

    public static ConfigurableApplicationContext mainExec(String[] args) {

        return SpringApplication.run(TimsApplication.class, args);
    }

    public static void main(String[] args) {
        mainExec(args);
    }

}
