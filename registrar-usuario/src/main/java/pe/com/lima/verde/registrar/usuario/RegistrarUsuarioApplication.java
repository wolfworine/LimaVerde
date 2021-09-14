package pe.com.lima.verde.registrar.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(description = "Componente de entidad registrar de Usuario.", title = "API Registrar Usuario", version = "1.0.0"), servers = @Server(url = "http://localhost:8092/"))
@ComponentScan(basePackages = {"pe.com.lima.verde.registrar.usuario"})
public class RegistrarUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrarUsuarioApplication.class, args);
	}

}
