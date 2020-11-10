package br.com.dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("Parâmetro da leitura de arquivo não foi encontrado");

		System.exit(SpringApplication.exit(SpringApplication.run(SincronizacaoReceitaApplication.class, args)));
	}

}
