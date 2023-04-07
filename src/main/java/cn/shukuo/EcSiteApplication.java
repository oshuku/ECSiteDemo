package cn.shukuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class EcSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcSiteApplication.class, args);
	}

}
