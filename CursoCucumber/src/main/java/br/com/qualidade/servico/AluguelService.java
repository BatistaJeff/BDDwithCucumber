package br.com.qualidade.servico;

import java.util.Calendar;

import br.com.qualidade.entidade.Filme;
import br.com.qualidade.entidade.NotaAluguel;
import br.com.qualidade.servico.util.DateUtils;
import br.com.qualidade.servico.util.Valores;
import br.com.qualidade.servico.util.ValoresUtils;

public class AluguelService {

	NotaAluguel nota;

	
	public NotaAluguel alugar(Filme filme) {
		
		if(filme.getEstoque() == 0) { 
			throw new RuntimeException("Filme sem estoque");
		}
		this.nota = new NotaAluguel();
		this.nota.setPreco(filme.getAluguel());
		
		nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
		filme.setEstoque(filme.getEstoque() - ValoresUtils.VALOR_A_SER_DADO_BAIXA);
		
		return this.nota;
	}

}
