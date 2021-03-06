package io.github.chingow.istock;

import io.github.chingow.istock.spider.timerjob.ITimerJob;
import io.github.chingow.istock.spider.timerjob.ITimerJobFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

/**
 * spring boot 启动类
 *
 * @author chingow
 */
@Controller
@EnableCaching
@ServletComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        try {
            // 开启核心调度任务线程
            ITimerJobFactory.getJob(ITimerJobFactory.TimerJob.CORE_SCHEDULE).execute(ITimerJob.COMMAND.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}