/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421901065 */

package main;

import java.util.ArrayList;
import javax.swing.*;
import model.*;
import service.RelatorioService;

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

        try {
            Turma turma1A = new Turma("primeiro Ano A", 1);

            Aluno mateus = new Aluno(
                "Mateus Fernandes da Silva",
                "111.111.111-11",
                "Rua A",
                "1111-1111",
                "mateus@email.com",
                "MAT01",
                turma1A,
                1
            );

            Aluno adeiodo = new Aluno(
                "Adeiodo Lopez da Silva",
                "222.222.222-22",
                "Rua B",
                "2222-2222",
                "adeiodo@email.com",
                "MAT02",
                turma1A,
                1
            );

            turma1A.adicionarAluno(mateus);
            turma1A.adicionarAluno(adeiodo);

            Disciplina mat = new Disciplina("Matemática", "MAT101", 60);
            Disciplina ing = new Disciplina("Inglês", "ING101", 40);
            Disciplina port = new Disciplina("Português", "PORT101", 60);

            turma1A.adicionarDisciplina(mat);
            turma1A.adicionarDisciplina(ing);
            turma1A.adicionarDisciplina(port);

            // Teste de matrícula duplicada (lançará exceção se descomentado)
            // turma1A.adicionarAluno(mateus);

            Professor profJose = new Professor(
                "José",
                "333.333.333-33",
                "Rua C",
                "3333-3333",
                "jose@escola.com",
                "PROF01",
                mat
            );

            Professor profMaria = new Professor(
                "Maria",
                "444.444.444-44",
                "Rua D",
                "4444-4444",
                "maria@escola.com",
                "PROF02",
                ing
            );

            Professor profAna = new Professor(
                "Ana",
                "555.555.555-55",
                "Rua E",
                "5555-5555",
                "ana@escola.com",
                "PROF03",
                port
            );

            profJose.adicionarTurma(turma1A);
            profMaria.adicionarTurma(turma1A);
            profAna.adicionarTurma(turma1A);

            ArrayList<Aluno> alunos = new ArrayList<>();
            alunos.add(mateus);
            alunos.add(adeiodo);

            ArrayList<Professor> professores = new ArrayList<>();
            professores.add(profJose);
            professores.add(profMaria);
            professores.add(profAna);

            RelatorioService.exibirRelatorioEscolar(turma1A, alunos, professores);

            ArrayList<Pessoa> pessoas = new ArrayList<>();
            pessoas.addAll(alunos);
            pessoas.addAll(professores);

            RelatorioService.exibirListaPessoas(pessoas);

        } catch (exceptions.DadoInvalidoException e) {
            System.err.println("Erro de Validação de Dados: " + e.getMessage());
        } catch (exceptions.MatriculaInvalidaException e) {
            System.err.println("Erro na Matrícula: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }



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
                "4 - Buscar Aluno por CPF\n" +
                "5 - Ver Relatórios / Cadastros\n" +
                "6 - Sair"
            );

            if (opcao == null || opcao.equals("6")) {
                rodando = false;
            } else {
                try {
                    // CADASTRAR TURMA
                    if (opcao.equals("1")) {
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
                        String cargaHorariaTexto = JOptionPane.showInputDialog("Carga horária da disciplina:");
                        int cargaHoraria = 60;
                        try {
                            cargaHoraria = Integer.parseInt(cargaHorariaTexto);
                        } catch (NumberFormatException e) {
                            // use default 60
                        }

                        // Cria o objeto Disciplina antes de criar o Professor
                        Disciplina disciplina = new Disciplina(nomeDisciplina, codDisciplina, cargaHoraria);

                        Professor professor = new Professor(nome, cpf, endereco, telefone, email, matricula, disciplina);
                        diretor.cadastrarProfessor(professor);

                        JOptionPane.showMessageDialog(null, "Professor cadastrado: " + nome);

                    // CADASTRAR ALUNO
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

                    // BUSCAR ALUNO POR CPF
                    } else if (opcao.equals("4")) {
                        String cpfBusca = JOptionPane.showInputDialog("Digite o CPF do aluno:");
                        if (cpfBusca == null || cpfBusca.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "CPF inválido!");
                        } else {
                            String cpfBuscaLimpo = cpfBusca.replaceAll("[^0-9]", "");
                            Aluno alunoEncontrado = null;
                            for (Aluno a : diretor.getAlunos()) {
                                String cpfAlunoLimpo = a.getCpf().replaceAll("[^0-9]", "");
                                if (cpfAlunoLimpo.equals(cpfBuscaLimpo)) {
                                    alunoEncontrado = a;
                                    break;
                                }
                            }

                            if (alunoEncontrado != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("=== ALUNO ENCONTRADO ===\n");
                                sb.append("Nome: ").append(alunoEncontrado.getNome()).append("\n");
                                sb.append("CPF: ").append(alunoEncontrado.getCpf()).append("\n");
                                sb.append("RA: ").append(alunoEncontrado.getRa()).append("\n");
                                sb.append("Turma: ").append(alunoEncontrado.getTurma().getCodigo()).append("\n");
                                sb.append("Período: ").append(alunoEncontrado.getPeriodo()).append("\n");
                                sb.append("E-mail: ").append(alunoEncontrado.getEmail()).append("\n");
                                sb.append("Telefone: ").append(alunoEncontrado.getTelefone()).append("\n");
                                sb.append("Endereço: ").append(alunoEncontrado.getEndereco()).append("\n");

                                sb.append("\nResponsáveis:\n");
                                if (alunoEncontrado.getResponsaveis().isEmpty()) {
                                    sb.append("- Nenhum responsável cadastrado\n");
                                } else {
                                    for (Responsavel r : alunoEncontrado.getResponsaveis()) {
                                        sb.append("- ").append(r.getNome())
                                          .append(" (").append(r.getParentesco()).append(")\n")
                                          .append("  CPF: ").append(r.getCpf())
                                          .append(" | Tel: ").append(r.getTelefone()).append("\n");
                                    }
                                }
                                JOptionPane.showMessageDialog(null, sb.toString(), "Detalhes do Aluno", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Aluno com CPF \"" + cpfBusca + "\" não encontrado.", "Não Encontrado", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                    // VER RELATÓRIOS / CADASTROS
                    } else if (opcao.equals("5")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("=== TURMAS CADASTRADAS ===\n");
                        if (diretor.getTurmas().isEmpty()) {
                            sb.append("(Nenhuma turma cadastrada)\n");
                        } else {
                            for (Turma t : diretor.getTurmas()) {
                                sb.append("- ").append(t.toString()).append("\n");
                            }
                        }

                        sb.append("\n=== PROFESSORES CADASTRADOS ===\n");
                        if (diretor.getProfessores().isEmpty()) {
                            sb.append("(Nenhum professor cadastrado)\n");
                        } else {
                            for (Professor p : diretor.getProfessores()) {
                                sb.append("- ").append(p.apresentar()).append("\n");
                            }
                        }

                        sb.append("\n=== ALUNOS CADASTRADOS ===\n");
                        if (diretor.getAlunos().isEmpty()) {
                            sb.append("(Nenhum aluno cadastrado)\n");
                        } else {
                            for (Aluno a : diretor.getAlunos()) {
                                sb.append("- ").append(a.apresentar()).append("\n");
                            }
                        }

                        JTextArea textArea = new JTextArea(sb.toString());
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));
                        JOptionPane.showMessageDialog(null, scrollPane, "Relatório Geral de Cadastros", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                } catch (exceptions.DadoInvalidoException e) {
                    JOptionPane.showMessageDialog(null, "Erro de Validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro de Formato: Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
    }
}
