/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421901065 */


package main;

import javax.swing.*;
import model.*;

public class Main {
    public static void main(String[] args) {

        // LOGIN
        JTextField campoUsuario = new JTextField();
        JPasswordField campoSenha = new JPasswordField();

        Object[] camposLogin = {
            "Usuário:", campoUsuario,
            "Senha:", campoSenha
        };

        int opcaoLogin = JOptionPane.showConfirmDialog(
            null,
            camposLogin,
            "Login - Sistema Escolar",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (opcaoLogin != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            return;
        }

        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (!usuario.equals("direcao") || !senha.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!\nBem-vindo(a), " + usuario);

        // DIRETOR LOGADO
        Direcao diretor = new Direcao(
            "Carlos Silva", "666.666.666-66",
            "Rua F", "6666-6666",
            "carlos@escola.com", "Diretor"
        );

        // MENU PRINCIPAL
        boolean rodando = true;

        while (rodando) {
            String opcao = JOptionPane.showInputDialog(
                "=== MENU - DIREÇÃO ===\n\n" +
                "1 - Cadastrar Turma\n" +
                "2 - Cadastrar Professor\n" +
                "3 - Cadastrar Aluno\n" +
                "4 - Sair"
            );

            if (opcao == null || opcao.equals("4")) {
                rodando = false;


            // CADASTRAR TURMA

            } else if (opcao.equals("1")) {
                String nomeTurma = JOptionPane.showInputDialog("Nome da turma:");
                String anoTexto = JOptionPane.showInputDialog("Ano da turma:");
                int ano = Integer.parseInt(anoTexto);

                Turma turma = new Turma(nomeTurma, ano);
                diretor.cadastrarTurma(turma);

                JOptionPane.showMessageDialog(null, "Turma cadastrada: " + nomeTurma);


            // CADASTRAR PROFESSOR

            } else if (opcao.equals("2")) {
            String nome       = JOptionPane.showInputDialog("Nome do professor:");
            String cpf        = JOptionPane.showInputDialog("CPF:");
            String endereco   = JOptionPane.showInputDialog("Endereço:");
            String telefone   = JOptionPane.showInputDialog("Telefone:");
            String email      = JOptionPane.showInputDialog("E-mail:");
            String matricula  = JOptionPane.showInputDialog("Matrícula:");
            String nomeDisciplina = JOptionPane.showInputDialog("Disciplina:");
            String codDisciplina  = JOptionPane.showInputDialog("Código da disciplina:");

            // Cria o objeto Disciplina antes de criar o Professor
            Disciplina disciplina = new Disciplina(nomeDisciplina, codDisciplina, 0);

            Professor professor = new Professor(nome, cpf, endereco, telefone, email, matricula, disciplina);
            diretor.cadastrarProfessor(professor);

            JOptionPane.showMessageDialog(null, "Professor cadastrado: " + nome);
            } else if (opcao.equals("3")) {
                // Dados do responsável
                String nomeResp   = JOptionPane.showInputDialog("Nome do responsável:");
                String cpfResp    = JOptionPane.showInputDialog("CPF do responsável:");
                String endResp    = JOptionPane.showInputDialog("Endereço do responsável:");
                String telResp    = JOptionPane.showInputDialog("Telefone do responsável:");
                String emailResp  = JOptionPane.showInputDialog("E-mail do responsável:");
                String parentesco = JOptionPane.showInputDialog("Parentesco:");

                Responsavel responsavel = new Responsavel(nomeResp, cpfResp, endResp, telResp, emailResp, parentesco);

                // Dados exclusivos do aluno (endereço e telefone vêm do responsável)
                String nomeAluno  = JOptionPane.showInputDialog("Nome do aluno:");
                String cpfAluno   = JOptionPane.showInputDialog("CPF do aluno:");
                String emailAluno = JOptionPane.showInputDialog("E-mail do aluno:");
                String ra         = JOptionPane.showInputDialog("RA:");
                String codTurma   = JOptionPane.showInputDialog("Código da turma:");
                int periodo       = Integer.parseInt(JOptionPane.showInputDialog("Período:"));

                // Busca turma já cadastrada
                Turma turmaSelecionada = null;
                for (Turma t : diretor.getTurmas()) {
                    if (t.getCodigo().equals(codTurma)) {
                        turmaSelecionada = t;
                        break;
                    }
                }

                if (turmaSelecionada == null) {
                    JOptionPane.showMessageDialog(null, "Turma não encontrada! Cadastre a turma primeiro.");
                } else {
                    // Reusa endereço e telefone do responsável
                    Aluno aluno = new Aluno(nomeAluno, cpfAluno, endResp, telResp, emailAluno, ra, turmaSelecionada, periodo);
                    diretor.cadastrarAluno(aluno, responsavel);
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado: " + nomeAluno + "\nResponsável: " + nomeResp);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
    }
}
