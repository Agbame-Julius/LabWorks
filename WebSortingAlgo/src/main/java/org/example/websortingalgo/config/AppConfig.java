package org.example.websortingalgo.config;

import org.example.websortingalgo.controller.Controller;
import org.example.websortingalgo.service.BucketSortService;
import org.example.websortingalgo.service.Greeting;
import org.example.websortingalgo.service.MergeSortService;
import org.example.websortingalgo.service.QuickSort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "org.example.websortingalgo.controller, org.example.websortingalgo.service")
public class AppConfig {
//    @Bean
//    public Controller controller(BucketSortService bucketSortService, Greeting greeting, MergeSortService mergeSortService, QuickSort quickSort) {
//        return new Controller(bucketSortService,greeting, mergeSortService, quickSort);
//    }

    @Bean
    public QuickSort quickSort() {
        return new QuickSort();
    }

    @Bean
    public MergeSortService mergeSortService() {
        return new MergeSortService();
    }

    @Bean
    public BucketSortService bucketSortService() {
        return new BucketSortService();
    }

    @Bean
    public Greeting greeting() {
        return new Greeting();
    }


}
