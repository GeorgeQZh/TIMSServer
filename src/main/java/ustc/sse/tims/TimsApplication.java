package ustc.sse.tims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("ustc.sse.tims.mapper")
@SpringBootApplication
@EnableCaching
public class TimsApplication {

    public static ConfigurableApplicationContext mainExec(String[] args) {

        return SpringApplication.run(TimsApplication.class, args);
    }

    public static void main(String[] args) {
        mainExec(args);
    }

}
