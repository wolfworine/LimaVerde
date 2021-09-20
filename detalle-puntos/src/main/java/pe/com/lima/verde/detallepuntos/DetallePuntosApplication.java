package pe.com.lima.verde.detallepuntos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pe.com.lima.verde.detallepuntos"})
public class DetallePuntosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetallePuntosApplication.class, args);
	}

}
