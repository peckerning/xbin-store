package cn.binux;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableWebMvc
public class XbinStoreWebAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XbinStoreWebAdminApplication.class, args);
	}
	@Value("${beetl.templates-path}") String templatesPath;//模板跟目录
	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		try {
			ClasspathResourceLoader cploder = new ClasspathResourceLoader(XbinStoreWebAdminApplication.class.getClassLoader(),templatesPath);
			beetlGroupUtilConfiguration.setResourceLoader(cploder);
			return beetlGroupUtilConfiguration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
}
