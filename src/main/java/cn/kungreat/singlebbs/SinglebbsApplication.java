package cn.kungreat.singlebbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.kungreat.singlebbs.mapper")
public class SinglebbsApplication {

	public static final ObjectMapper MAP_JSON = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(SinglebbsApplication.class, args);
	}

}
