package com.example.springboot;

import com.example.springboot.entities.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

/**
 * Tutorial: https://www.baeldung.com/spring-boot-angular-web
 *
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * > @Configuration: Tags the class as a source of bean definitions for the application context.
 * > @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath
 * settings, other beans, and various property settings. For example, if spring-webmvc is
 * on the classpath, this annotation flags the application as a web application and
 * activates key behaviors, such as setting up a DispatcherServlet.
 * @ComponentScan: Tells Spring to look for other components, configurations, and services
 * in the com/example package, letting it find the controllers. The main() method uses
 * Spring Boot’s SpringApplication.run() method to launch an application.
 * <p>
 * There is also a CommandLineRunner method marked as a @Bean, and this runs on start up.
 * It retrieves all the beans that were created by your application or that were
 * automatically added by Spring Boot. It sorts them and prints them out.
 * <p>
 * You can check the health of the application by running the following command:
 * $ curl localhost:8080/actuator/health
 * <p>
 * You can try also to invoke shutdown through curl, to see what happens when
 * you have not added the necessary line to application.properties:
 * $ curl -X POST localhost:8080/actuator/shutdown
 * <p>
 * The /actuator/shutdown endpoint by default is visible only through JMX.
 * To enable it as an HTTP endpoint, add this line:
 * management.endpoints.web.exposure.include=health,info,shutdown
 * to your application.properties file. However, you probably should not enable
 * the shutdown endpoint for a publicly available application.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("Rick", "Bill", "July", "Johnny").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@mail.io");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }
}
