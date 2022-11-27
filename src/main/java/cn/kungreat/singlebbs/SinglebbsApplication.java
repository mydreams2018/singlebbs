package cn.kungreat.singlebbs;

import cn.kungreat.singlebbs.mapper.ManagerMapper;
import cn.kungreat.singlebbs.util.InvalidUserCacle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "cn.kungreat.singlebbs.mapper")
public class SinglebbsApplication {

	public static final ObjectMapper MAP_JSON = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(SinglebbsApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(ApplicationContext applicationContext){
		return args -> {
			ManagerMapper bean = applicationContext.getBean(ManagerMapper.class);
			List<String> invalidUsers = bean.selectInvalidUsers();
			if(invalidUsers != null && invalidUsers.size() > 0){
				InvalidUserCacle.addUsersInvalid(invalidUsers);
			}
		};
	}

}
