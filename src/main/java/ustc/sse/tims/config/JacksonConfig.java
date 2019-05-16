package ustc.sse.tims.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.config
 * @date 2019/5/15-22:57
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper getObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper om = builder.build();
        return om;
    }

}
