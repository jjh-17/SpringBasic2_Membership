package spring_basic2.Membership2.configuration.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.filter.*;

@Configuration
@ComponentScan(
        basePackages = "spring_basic2.Membership2",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Configuration.class, FilterLombok.class, FilterFieldMatch.class, FilterMultiBeanUse.class,
                        FilterAutoDI.class, FilterPrimary.class, FilterQualifier.class}),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterQualifier2.class)
)
public class AppConfigQualifier2 {
}
