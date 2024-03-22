package club.p6e.coat.console;

import club.p6e.coat.common.global.GlobalUserInfo;
import club.p6e.coat.common.global.Globals;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class P6eCloudIndexApplication {

    public static void main(String[] args) {
        Globals.setDebug();
        GlobalUserInfo.setDebug(new GlobalUserInfo().setId(30040).setAccount("123456"));
        SpringApplication.run(P6eCloudIndexApplication.class, args);
    }

}
