import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/BancoServlet")
public class BancoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		PessoaFisica pf = null;
		PessoaJuridica pj = null;
		ContaBancaria cb = null;

		String nomeCliente = req.getParameter("nomeCliente");
		String tipoCliente = req.getParameter("tipoCliente");
		String cpfCnpj = req.getParameter("cpfCnpj");
		
		String operacao = req.getParameter("operacao");

		if (tipoCliente.equals("pessoaFisica")) {
			pf = new PessoaFisica(nomeCliente, cpfCnpj);
			cb = new ContaBancaria(pf);
		} else {
			pj = new PessoaJuridica(nomeCliente, cpfCnpj);
			cb = new ContaBancaria(pj);
		}
					
		if (operacao.equals("recuperarNomeCliente")) {
			req.setAttribute("resposta", nomeCliente);
		} else if (operacao.equals("recuperarCpfCliente")) {
			String cpf = tipoCliente.equals("pessoaFisica") ? pf.getCpf() : null;
			req.setAttribute("resposta", cpf);
		} else if (operacao.equals("recuperarCnpjCliente")) {
			String cnpj = tipoCliente.equals("pessoaJuridica") ? pj.getCnpj() : null;
			req.setAttribute("resposta", cnpj);
		} else if (operacao.equals("recuperarSaldo")) {
			int saldo = cb.getSaldo();
			req.setAttribute("resposta", saldo);
		} else if (operacao.equals("depositarValor")) {
			int valorDeposito = Integer.parseInt(req.getParameter("valorDeposito"));
			int deposito = cb.depositar(valorDeposito);
			req.setAttribute("resposta", deposito);
		} else if (operacao.equals("sacarValor")) {
			int valorSaque = Integer.parseInt(req.getParameter("valorSaque"));
			int saque = cb.sacar(valorSaque);
			req.setAttribute("resposta", saque);
		} else if (operacao.equals("depositarSacarValor")) {
			int valorDeposito = Integer.parseInt(req.getParameter("valorDeposito"));
			int valorSaque = Integer.parseInt(req.getParameter("valorSaque"));
			int depositoSaque = cb.depositar(valorDeposito);
			depositoSaque = cb.sacar(valorSaque);
			req.setAttribute("resposta", depositoSaque);
		}

		RequestDispatcher reqDispatcher = req.getRequestDispatcher("resposta.jsp");
		reqDispatcher.forward(req, resp);

	}}