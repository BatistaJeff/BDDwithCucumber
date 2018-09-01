package br.com.qualidade.servico;

import br.com.qualidade.entidade.Filme;
import br.com.qualidade.entidade.NotaAluguel;
import br.com.qualidade.entidade.TipoAluguel;
import br.com.qualidade.servico.util.DateUtils;

public class AluguelService {

	private NotaAluguel notaAluguel;

	public NotaAluguel alugar(Filme filme, TipoAluguel aluguel) {
		if (filme.getEstoque() == 0) {
			throw new RuntimeException("Filme sem estoque");
		} else if (aluguel != null){
			notaAluguel = new NotaAluguel();
			switch (aluguel) {
			case COMUM:
				notaAluguel.setPreco(filme.getAluguel());
				notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
				notaAluguel.setPontuacao(1);
				break;

			case EXTENDIDO:
				notaAluguel.setPreco(filme.getAluguel() * 2);
				notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(3));
				notaAluguel.setPontuacao(2);
				break;

			case SEMANAL:
				notaAluguel.setPreco(filme.getAluguel() * 3);
				notaAluguel.setDataEntrega(DateUtils.obterDataDiferencaDias(7));
				notaAluguel.setPontuacao(3);
				break;
			}
			filme.setEstoque(filme.getEstoque() - 1);
		}
		return notaAluguel;
	}


}
