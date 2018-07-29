package br.com.qualidade.steps;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import br.com.qualidade.entidade.Filme;
import br.com.qualidade.entidade.NotaAluguel;
import br.com.qualidade.servico.AluguelService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AlugarFilmesSteps {

	private Filme filme;
	private AluguelService aluguel;
	private NotaAluguel nota;

	public AlugarFilmesSteps() {
		this.filme = new Filme();
		this.aluguel = new AluguelService();
	}

	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {

		filme.setEstoque(arg1);
	}

	@Dado("^que o preço do aluguel seja de R\\$ (\\d+)$")
	public void queOPreçoDoAluguelSejaDeR$(int arg1) throws Throwable {

		filme.setAluguel(arg1);
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {

		nota = aluguel.alugar(filme);
	}

	@Então("^o preço do aluguel será (\\d+)$")
	public void oPreçoDoAluguelSerá(int arg1) throws Throwable {

		Assert.assertEquals(arg1, nota.getPreco());
	}

	@Então("^a data de entrega será no dia seguinte$")
	public void aDataDeEntregaSeráNoDiaSeguinte() throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);

		Date dataRetorno = nota.getDataEntrega();
		Calendar calRetorno = Calendar.getInstance();
		calRetorno.setTime(dataRetorno);

		Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), calRetorno.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(cal.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
		Assert.assertEquals(cal.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR));
	}

	@Então("^o estoque do filme será de (\\d+) unidade$")
	public void oEstoqueDoFilmeSeráDeUnidade(int arg1) throws Throwable {

	}

}
