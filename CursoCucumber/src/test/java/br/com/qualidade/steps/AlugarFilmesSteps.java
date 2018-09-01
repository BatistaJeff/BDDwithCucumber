package br.com.qualidade.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import br.com.qualidade.entidade.Filme;
import br.com.qualidade.entidade.NotaAluguel;
import br.com.qualidade.entidade.TipoAluguel;
import br.com.qualidade.servico.AluguelService;
import br.com.qualidade.servico.util.DateUtils;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AlugarFilmesSteps {

	private Filme filme;
	private AluguelService aluguel;
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;
	
	public AlugarFilmesSteps() {
		this.filme = new Filme();
		this.aluguel = new AluguelService();
		this.nota = new NotaAluguel();
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
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (Exception e) {
			erro = e.getMessage();
		}
	}

	@Então("^o preço do aluguel será (\\d+)$")
	public void oPreçoDoAluguelSerá(int arg1) throws Throwable {

		Assert.assertEquals(arg1, nota.getPreco());
	}

	@Então("^o estoque do filme será de (\\d+) unidade$")
	public void oEstoqueDoFilmeSeráDeUnidade(int arg1) throws Throwable {
		Assert.assertEquals(arg1, filme.getEstoque());
	}
	
	@Então("^não será possivel por falta de estoque$")
	public void não_será_possivel_por_falta_de_estoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	
	}

	@Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
	public void que_o_preço_do_aluguel_seja_R$(int arg1) throws Throwable {
		System.out.println(arg1);
	}

	@Dado("^que o tipo de aluguel seja (.*)$")
	public void que_o_tipo_de_aluguel_seja_extendido(String arg1) throws Throwable {
		tipoAluguel = arg1.equals("semanal") ? TipoAluguel.SEMANAL :
				arg1.equals("comum") ? TipoAluguel.COMUM : TipoAluguel.EXTENDIDO;
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void o_preço_do_aluguel_será_R$(int arg1) throws Throwable {
		System.out.println(arg1);
	}

	@Então("^a data de entrega será em (\\d+) dias?$")
	public void a_data_de_entrega_será_em_dias(int arg1) throws Throwable {
		Date dataEsperada = DateUtils.obterDataDiferencaDias(arg1);
		Date dataReal = nota.getDataEntrega();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("^a pontuação recebida será de (\\d+) pontos$")
	public void a_pontuação_recebida_será_de_pontos(int arg1) throws Throwable {
		Assert.assertEquals(arg1, nota.getPontuacao());
	}
	
}
