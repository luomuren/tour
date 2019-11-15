package top.tmstarry.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top.tmstarry.tour.**.dao",
        "top.tmstarry.tour.config",
        "top.tmstarry.tour.**.service",
        "top.tmstarry.tour.**.view",
        "top.tmstarry.tour.shiro"
})
public class TourApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourApplication.class, args);
    }

}
