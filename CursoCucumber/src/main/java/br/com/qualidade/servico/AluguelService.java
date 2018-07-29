package br.com.qualidade.servico;

import java.util.Calendar;

import br.com.qualidade.entidade.Filme;
import br.com.qualidade.entidade.NotaAluguel;

public class AluguelService {

	NotaAluguel nota;
	
	public NotaAluguel alugar(Filme filme) {
		
		this.nota = new NotaAluguel();
		this.nota.setPreco(filme.getAluguel());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		nota.setDataEntrega(cal.getTime());
		
		return this.nota;
	}

}
