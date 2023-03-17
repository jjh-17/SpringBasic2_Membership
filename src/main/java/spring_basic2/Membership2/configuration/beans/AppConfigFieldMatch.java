package spring_basic2.Membership2.configuration.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.filter.*;

@Configuration
@ComponentScan(
        basePackages = "spring_basic2.Membership2",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Configuration.class, FilterLombok.class, FilterAutoDI.class, FilterMultiBeanUse.class,
                        FilterQualifier.class, FilterQualifier2.class, FilterPrimary.class}),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterFieldMatch.class)
)
public class AppConfigFieldMatch {
}
