package br.edu.cs.poo.ac.seguro.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ExcecaoValidacaoDados extends Exception {

    private static final long serialVersionUID = 1L; 
    private List<String> mensagens;

    public ExcecaoValidacaoDados() {
        super("Erros de validação ocorreram."); 
        this.mensagens = new ArrayList<>();
    }

    public ExcecaoValidacaoDados(String mensagemInicial) {
        super(mensagemInicial);
        this.mensagens = new ArrayList<>();
        if (mensagemInicial != null && !mensagemInicial.trim().isEmpty()) {
            this.mensagens.add(mensagemInicial);
        }
    }

    public void adicionarMensagem(String mensagem) {
        if (mensagem != null && !mensagem.trim().isEmpty()) {
            this.mensagens.add(mensagem);
        }
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        if (super.getMessage() != null && !super.getMessage().equals("Erros de validação ocorreram.")) {
            sb.append(super.getMessage());
            if (!mensagens.isEmpty()) {
                sb.append("\nDetalhes:\n");
            }
        } else if (mensagens.isEmpty()){
            return super.getMessage(); 
        }


        for (int i = 0; i < mensagens.size(); i++) {
            sb.append(mensagens.get(i));
            if (i < mensagens.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}