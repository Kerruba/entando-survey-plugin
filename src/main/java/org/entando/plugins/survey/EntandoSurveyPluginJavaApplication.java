package org.entando.plugins.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.entando")
public class EntandoSurveyPluginJavaApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EntandoSurveyPluginJavaApplication.class, args);
    }

}
