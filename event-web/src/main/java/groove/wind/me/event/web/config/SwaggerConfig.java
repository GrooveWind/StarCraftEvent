package groove.wind.me.event.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {


    @Bean(value = "eventApi")
    public Docket eventApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("赛事/活动接口api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("groove.wind.me.event.web.controller.event"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    @Bean(value = "payApi")
    public Docket payApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("支付接口api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("groove.wind.me.event.web.controller.pay"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    @Bean(value = "bizApi")
    public Docket bizApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("业务接口api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("groove.wind.me.event.web.controller.biz"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Let's FUNK")
                .description("I feel good")
                .version("1.0")
                .build();
    }


}
