/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421901065 */

package main;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import model.*;
import service.RelatorioService;

public class Main {

    private static JTextField campo() {
        JTextField f = new JTextField();
        f.setPreferredSize(new Dimension(220, 28));
        return f;
    }

    private static String ler(JTextField campo, String nomeCampo) {
        String val = campo.getText().trim();
        if (val.isEmpty()) throw new IllegalArgumentException(nomeCampo);
        return val;
    }

    public static void main(String[] args) {

        // LOGIN
        JTextField campoUsuario = campo();
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(220, 28));

        JPanel painelLogin = new JPanel(new GridLayout(2, 2, 6, 8));
        painelLogin.setBorder(BorderFactory.createTitledBorder("Acesso ao Sistema"));
        painelLogin.add(new JLabel("Usuário:"));  painelLogin.add(campoUsuario);
        painelLogin.add(new JLabel("Senha:"));    painelLogin.add(campoSenha);

        int opcaoLogin = JOptionPane.showConfirmDialog(
            null, painelLogin, "Login - Sistema Escolar", JOptionPane.OK_CANCEL_OPTION
        );

        if (opcaoLogin != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            return;
        }

        String usuario = campoUsuario.getText().trim();
        String senha   = new String(campoSenha.getPassword());

        if (!usuario.equals("direcao") || !senha.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!\nBem-vindo(a), " + usuario);

        // DADOS INICIAIS
        try {
            Turma turma1A = new Turma("primeiro Ano A", 1);

            Aluno mateus  = new Aluno("Mateus Fernandes da Silva","111.111.111-11","Rua A","1111-1111","mateus@email.com","MAT01",turma1A,1);
            Aluno adeiodo = new Aluno("Adeiodo Lopez da Silva","222.222.222-22","Rua B","2222-2222","adeiodo@email.com","MAT02",turma1A,1);
            turma1A.adicionarAluno(mateus);
            turma1A.adicionarAluno(adeiodo);

            Disciplina mat  = new Disciplina("Matemática","MAT101",60);
            Disciplina ing  = new Disciplina("Inglês","ING101",40);
            Disciplina port = new Disciplina("Português","PORT101",60);
            turma1A.adicionarDisciplina(mat);
            turma1A.adicionarDisciplina(ing);
            turma1A.adicionarDisciplina(port);

            Professor profJose  = new Professor("José","333.333.333-33","Rua C","3333-3333","jose@escola.com","PROF01",mat);
            Professor profMaria = new Professor("Maria","444.444.444-44","Rua D","4444-4444","maria@escola.com","PROF02",ing);
            Professor profAna   = new Professor("Ana","555.555.555-55","Rua E","5555-5555","ana@escola.com","PROF03",port);
            profJose.adicionarTurma(turma1A);
            profMaria.adicionarTurma(turma1A);
            profAna.adicionarTurma(turma1A);

            ArrayList<Aluno> alunosIni = new ArrayList<>();
            alunosIni.add(mateus); alunosIni.add(adeiodo);
            ArrayList<Professor> profsIni = new ArrayList<>();
            profsIni.add(profJose); profsIni.add(profMaria); profsIni.add(profAna);

            RelatorioService.exibirRelatorioEscolar(turma1A, alunosIni, profsIni);
            ArrayList<Pessoa> pessoasIni = new ArrayList<>();
            pessoasIni.addAll(alunosIni); pessoasIni.addAll(profsIni);
            RelatorioService.exibirListaPessoas(pessoasIni);

        } catch (exceptions.DadoInvalidoException e) {
            System.err.println("Erro de Validação: " + e.getMessage());
        } catch (exceptions.MatriculaInvalidaException e) {
            System.err.println("Erro na Matrícula: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

        // DIRETOR LOGADO
        Direcao diretor = new Direcao(
            "Carlos Silva","666.666.666-66","Rua F","6666-6666","carlos@escola.com","Diretor"
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
                continue;
            }

            try {
                switch (opcao) {

                    // 1 - CADASTRAR TURMA
                    case "1": {
                        JTextField fNome = campo();
                        JTextField fAno  = campo();

                        JPanel p = new JPanel(new GridLayout(2, 2, 6, 8));
                        p.setBorder(BorderFactory.createTitledBorder("Nova Turma"));
                        p.add(new JLabel("Nome da turma:"));  p.add(fNome);
                        p.add(new JLabel("Ano:"));            p.add(fAno);

                        int r = JOptionPane.showConfirmDialog(null, p, "Cadastrar Turma", JOptionPane.OK_CANCEL_OPTION);
                        if (r != JOptionPane.OK_OPTION) break;

                        String nomeTurma = ler(fNome, "Nome da turma");
                        int ano = Integer.parseInt(ler(fAno, "Ano"));

                        diretor.cadastrarTurma(new Turma(nomeTurma, ano));
                        JOptionPane.showMessageDialog(null, "Turma cadastrada: " + nomeTurma);
                        break;
                    }

                    // 2 - CADASTRAR PROFESSOR
                    case "2": {
                        // Janela 1: dados pessoais
                        JTextField fNome     = campo(); JTextField fCpf  = campo();
                        JTextField fEndereco = campo(); JTextField fTel  = campo();
                        JTextField fEmail    = campo(); JTextField fMatr = campo();

                        JPanel pPessoa = new JPanel(new GridLayout(6, 2, 6, 8));
                        pPessoa.setBorder(BorderFactory.createTitledBorder("Dados do Professor"));
                        pPessoa.add(new JLabel("Nome:"));       pPessoa.add(fNome);
                        pPessoa.add(new JLabel("CPF:"));        pPessoa.add(fCpf);
                        pPessoa.add(new JLabel("Endereço:"));   pPessoa.add(fEndereco);
                        pPessoa.add(new JLabel("Telefone:"));   pPessoa.add(fTel);
                        pPessoa.add(new JLabel("E-mail:"));     pPessoa.add(fEmail);
                        pPessoa.add(new JLabel("Matrícula:")); pPessoa.add(fMatr);

                        int r1 = JOptionPane.showConfirmDialog(null, pPessoa, "Cadastrar Professor (1/2)", JOptionPane.OK_CANCEL_OPTION);
                        if (r1 != JOptionPane.OK_OPTION) break;

                        // Janela 2: disciplina
                        JTextField fDisc  = campo();
                        JTextField fCodD  = campo();
                        JTextField fCarga = campo();

                        JPanel pDisc = new JPanel(new GridLayout(3, 2, 6, 8));
                        pDisc.setBorder(BorderFactory.createTitledBorder("Disciplina do Professor"));
                        pDisc.add(new JLabel("Nome da disciplina:")); pDisc.add(fDisc);
                        pDisc.add(new JLabel("Código:"));             pDisc.add(fCodD);
                        pDisc.add(new JLabel("Carga horária (h):"));  pDisc.add(fCarga);

                        int r2 = JOptionPane.showConfirmDialog(null, pDisc, "Cadastrar Professor (2/2)", JOptionPane.OK_CANCEL_OPTION);
                        if (r2 != JOptionPane.OK_OPTION) break;

                        Disciplina disciplina = new Disciplina(
                            ler(fDisc, "Disciplina"),
                            ler(fCodD, "Código da disciplina"),
                            Integer.parseInt(ler(fCarga, "Carga horária"))
                        );
                        Professor professor = new Professor(
                            ler(fNome, "Nome"), ler(fCpf, "CPF"), ler(fEndereco, "Endereço"),
                            ler(fTel, "Telefone"), ler(fEmail, "E-mail"), ler(fMatr, "Matrícula"),
                            disciplina
                        );
                        diretor.cadastrarProfessor(professor);
                        JOptionPane.showMessageDialog(null, "Professor cadastrado: " + professor.getNome());
                        break;
                    }

                    // 3 - CADASTRAR ALUNO
                    case "3": {
                        // Janela 1: dados do responsável
                        JTextField fNomeResp  = campo(); JTextField fCpfResp  = campo();
                        JTextField fEndResp   = campo(); JTextField fTelResp  = campo();
                        JTextField fEmailResp = campo(); JTextField fParent   = campo();

                        JPanel pResp = new JPanel(new GridLayout(6, 2, 6, 8));
                        pResp.setBorder(BorderFactory.createTitledBorder("Dados do Responsável"));
                        pResp.add(new JLabel("Nome:"));        pResp.add(fNomeResp);
                        pResp.add(new JLabel("CPF:"));         pResp.add(fCpfResp);
                        pResp.add(new JLabel("Endereço:"));    pResp.add(fEndResp);
                        pResp.add(new JLabel("Telefone:"));    pResp.add(fTelResp);
                        pResp.add(new JLabel("E-mail:"));      pResp.add(fEmailResp);
                        pResp.add(new JLabel("Parentesco:")); pResp.add(fParent);

                        int r1 = JOptionPane.showConfirmDialog(null, pResp, "Cadastrar Aluno (1/2) - Responsável", JOptionPane.OK_CANCEL_OPTION);
                        if (r1 != JOptionPane.OK_OPTION) break;

                        // Janela 2: dados do aluno
                        JTextField fNomeAluno  = campo(); JTextField fCpfAluno  = campo();
                        JTextField fEmailAluno = campo(); JTextField fRa        = campo();
                        JTextField fCodTurma   = campo(); JTextField fPeriodo   = campo();

                        JPanel pAluno = new JPanel(new GridLayout(6, 2, 6, 8));
                        pAluno.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
                        pAluno.add(new JLabel("Nome:"));            pAluno.add(fNomeAluno);
                        pAluno.add(new JLabel("CPF:"));             pAluno.add(fCpfAluno);
                        pAluno.add(new JLabel("E-mail:"));          pAluno.add(fEmailAluno);
                        pAluno.add(new JLabel("RA:"));              pAluno.add(fRa);
                        pAluno.add(new JLabel("Código da turma:")); pAluno.add(fCodTurma);
                        pAluno.add(new JLabel("Período:"));         pAluno.add(fPeriodo);

                        int r2 = JOptionPane.showConfirmDialog(null, pAluno, "Cadastrar Aluno (2/2) - Aluno", JOptionPane.OK_CANCEL_OPTION);
                        if (r2 != JOptionPane.OK_OPTION) break;

                        String endResp = ler(fEndResp, "Endereço do responsável");
                        String telResp = ler(fTelResp, "Telefone do responsável");

                        Responsavel responsavel = new Responsavel(
                            ler(fNomeResp, "Nome do responsável"), ler(fCpfResp, "CPF do responsável"),
                            endResp, telResp,
                            ler(fEmailResp, "E-mail do responsável"), ler(fParent, "Parentesco")
                        );

                        String codTurma = ler(fCodTurma, "Código da turma");
                        Turma turmaSelecionada = null;
                        for (Turma t : diretor.getTurmas()) {
                            if (t.getCodigo().equals(codTurma)) { turmaSelecionada = t; break; }
                        }

                        if (turmaSelecionada == null) {
                            JOptionPane.showMessageDialog(null,
                                "Turma \"" + codTurma + "\" não encontrada!\nCadastre a turma primeiro.",
                                "Turma não encontrada", JOptionPane.WARNING_MESSAGE);
                        } else {
                            Aluno aluno = new Aluno(
                                ler(fNomeAluno, "Nome do aluno"), ler(fCpfAluno, "CPF do aluno"),
                                endResp, telResp,
                                ler(fEmailAluno, "E-mail do aluno"), ler(fRa, "RA"),
                                turmaSelecionada, Integer.parseInt(ler(fPeriodo, "Período"))
                            );
                            diretor.cadastrarAluno(aluno, responsavel);
                            JOptionPane.showMessageDialog(null,
                                "Aluno cadastrado: " + aluno.getNome() + "\nResponsável: " + responsavel.getNome());
                        }
                        break;
                    }

                    // 4 - BUSCAR ALUNO POR CPF
                    case "4": {
                        JTextField fCpf = campo();
                        JPanel p = new JPanel(new GridLayout(1, 2, 6, 8));
                        p.setBorder(BorderFactory.createTitledBorder("Buscar Aluno"));
                        p.add(new JLabel("CPF do aluno:")); p.add(fCpf);

                        int r = JOptionPane.showConfirmDialog(null, p, "Busca por CPF", JOptionPane.OK_CANCEL_OPTION);
                        if (r != JOptionPane.OK_OPTION) break;

                        String cpfBusca = fCpf.getText().replaceAll("[^0-9]", "");
                        if (cpfBusca.isEmpty()) { JOptionPane.showMessageDialog(null, "CPF inválido!"); break; }

                        Aluno encontrado = null;
                        for (Aluno a : diretor.getAlunos()) {
                            if (a.getCpf().replaceAll("[^0-9]", "").equals(cpfBusca)) { encontrado = a; break; }
                        }

                        if (encontrado != null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("=== ALUNO ENCONTRADO ===\n");
                            sb.append("Nome: ").append(encontrado.getNome()).append("\n");
                            sb.append("CPF: ").append(encontrado.getCpf()).append("\n");
                            sb.append("RA: ").append(encontrado.getRa()).append("\n");
                            sb.append("Turma: ").append(encontrado.getTurma().getCodigo()).append("\n");
                            sb.append("Período: ").append(encontrado.getPeriodo()).append("\n");
                            sb.append("E-mail: ").append(encontrado.getEmail()).append("\n");
                            sb.append("Telefone: ").append(encontrado.getTelefone()).append("\n");
                            sb.append("Endereço: ").append(encontrado.getEndereco()).append("\n");
                            sb.append("\nResponsáveis:\n");
                            if (encontrado.getResponsaveis().isEmpty()) {
                                sb.append("- Nenhum responsável cadastrado\n");
                            } else {
                                for (Responsavel resp : encontrado.getResponsaveis()) {
                                    sb.append("- ").append(resp.getNome())
                                      .append(" (").append(resp.getParentesco()).append(")\n")
                                      .append("  CPF: ").append(resp.getCpf())
                                      .append(" | Tel: ").append(resp.getTelefone()).append("\n");
                                }
                            }
                            JTextArea ta = new JTextArea(sb.toString());
                            ta.setEditable(false);
                            JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Detalhes do Aluno", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                "Aluno com CPF \"" + fCpf.getText() + "\" não encontrado.",
                                "Não Encontrado", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    }

                    // 5 - VER RELATÓRIOS
                    case "5": {
                        StringBuilder sb = new StringBuilder();
                        sb.append("=== TURMAS CADASTRADAS ===\n");
                        if (diretor.getTurmas().isEmpty()) sb.append("(Nenhuma turma cadastrada)\n");
                        else for (Turma t : diretor.getTurmas()) sb.append("- ").append(t.toString()).append("\n");

                        sb.append("\n=== PROFESSORES CADASTRADOS ===\n");
                        if (diretor.getProfessores().isEmpty()) sb.append("(Nenhum professor cadastrado)\n");
                        else for (Professor p : diretor.getProfessores()) sb.append("- ").append(p.apresentar()).append("\n");

                        sb.append("\n=== ALUNOS CADASTRADOS ===\n");
                        if (diretor.getAlunos().isEmpty()) sb.append("(Nenhum aluno cadastrado)\n");
                        else for (Aluno a : diretor.getAlunos()) sb.append("- ").append(a.apresentar()).append("\n");

                        JTextArea ta = new JTextArea(sb.toString());
                        ta.setEditable(false);
                        JScrollPane scroll = new JScrollPane(ta);
                        scroll.setPreferredSize(new Dimension(500, 400));
                        JOptionPane.showMessageDialog(null, scroll, "Relatório Geral", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido nos campos numéricos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Campo obrigatório não preenchido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (exceptions.DadoInvalidoException e) {
                JOptionPane.showMessageDialog(null, "Erro de Validação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
    }
}