package sungho1.springCorePrinciple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;
@Configuration
//이 부분에 뺄것들을 적는다. Configuration은 AppConfig 코드가 지금은 따로 있으니 추가한다.(코드 중복 방지)
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
