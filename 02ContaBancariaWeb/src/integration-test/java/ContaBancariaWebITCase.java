
import org.junit.Test;
import org.openqa.selenium.By;

public class ContaBancariaWebITCase extends AbstractIntegrationTest {
    private static final String VALOR_DEPOSITO_TAG = "valorDeposito";
    private static final String VALOR_SAQUE_TAG = "valorSaque";
    private static final String VALOR_0 = "0";
    private static final String VALOR_10 = "10";
    private static final String VALOR_20 = "20";
    private static final String CNPJ_CLIENTE = "01.001...";
    private static final String CPF_CNPJ_TAG = "cpfCnpj";
    private static final String TIPO_CLIENTE_PESSOA_FISICA_TAG = "tipoClientePessoaFisica";
    private static final String TIPO_CLIENTE_PESSOA_JURIDICA_TAG = "tipoClientePessoaJuridica";
    private static final String NOME_CLIENTE_TAG = "nomeCliente";
    private static final String CPF_CLIENTE = "053.749...";
    private static final String NOME_CLIENTE_PESSOA_FISICA = "Carlos";
    private static final String NOME_CLIENTE_PESSOA_JURIDICA = "Atacadista S/A";

    @Test
    public void testRecuperarNomeCliente() {
        validarOperacaoPessoaFisica("operacaoRecuperarNomeCliente", NOME_CLIENTE_PESSOA_FISICA);
    }

    @Test
    public void testRecuperarCpfCliente() {
        validarOperacaoPessoaFisica("operacaoRecuperarCpfCliente", CPF_CLIENTE);
    }

    @Test
    public void testRecuperarCnpjCliente() {
        validarOperacaoPessoaJuridica("operacaoRecuperarCnpjCliente", CNPJ_CLIENTE);
    }

    @Test
    public void testOperacaoRecuperarSaldo() {
        validarOperacaoPessoaFisica("operacaoRecuperarSaldo", VALOR_0);
    }

    @Test
    public void testOperacaoDepositarValor() {
        validarOperacaoDepositoOuSaque("operacaoDepositarValor", VALOR_10, VALOR_0, VALOR_10);
    }

    @Test
    public void testOperacaoSacarValor() {
        validarOperacaoDepositoOuSaque("operacaoSacarValor", VALOR_0, VALOR_10, VALOR_0);
    }

    @Test
    public void testOperacaoDepositarSacarValor() {
        validarOperacaoDepositoOuSaque("operacaoDepositarSacarValor", VALOR_20, VALOR_10, VALOR_10);
    }

    private void validarOperacaoDepositoOuSaque(final String NOME_OPERACAO, final String VALOR_DEPOSITO,
        final String VALOR_SAQUE, final String RESPOSTA_ESPERADA) {
        preencherClientePessoaFisica(NOME_OPERACAO);
        driver.findElement(By.name(VALOR_DEPOSITO_TAG)).sendKeys(VALOR_DEPOSITO);
        driver.findElement(By.name(VALOR_SAQUE_TAG)).sendKeys(VALOR_SAQUE);
        submeterEValidar(RESPOSTA_ESPERADA);
    }

    private void preencherClientePessoaFisica(final String NOME_OPERACAO) {
        driver.get(APP_HOME_URL);
        driver.findElement(By.name(NOME_CLIENTE_TAG)).sendKeys(NOME_CLIENTE_PESSOA_FISICA);
        driver.findElement(By.id(TIPO_CLIENTE_PESSOA_FISICA_TAG)).click();
        driver.findElement(By.name(CPF_CNPJ_TAG)).sendKeys(CPF_CLIENTE);
        driver.findElement(By.id(NOME_OPERACAO)).click();
    }

    private void preencherClientePessoaJuridica(final String NOME_OPERACAO) {
        driver.get(APP_HOME_URL);
        driver.findElement(By.name(NOME_CLIENTE_TAG)).sendKeys(NOME_CLIENTE_PESSOA_JURIDICA);
        driver.findElement(By.id(TIPO_CLIENTE_PESSOA_JURIDICA_TAG)).click();
        driver.findElement(By.name(CPF_CNPJ_TAG)).sendKeys(CNPJ_CLIENTE);
        driver.findElement(By.id(NOME_OPERACAO)).click();
    }

    private void validarOperacaoPessoaFisica(final String NOME_OPERACAO, final String RESPOSTA_ESPERADA) {
        preencherClientePessoaFisica(NOME_OPERACAO);
        submeterEValidar(RESPOSTA_ESPERADA);
    }

    private void validarOperacaoPessoaJuridica(final String NOME_OPERACAO, final String RESPOSTA_ESPERADA) {
        preencherClientePessoaJuridica(NOME_OPERACAO);
        submeterEValidar(RESPOSTA_ESPERADA);
    }

}
