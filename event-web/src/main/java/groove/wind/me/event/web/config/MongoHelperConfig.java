package groove.wind.me.event.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"cn.craccd"})
public class MongoHelperConfig {

    // 开启事务(如使用单机mongodb,可不配置此@Bean)
//    @Bean
//    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }

}
