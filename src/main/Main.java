/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421901065 */

package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.*;
import service.RelatorioService;

public class Main {

    private static final Color ERRO_BG    = new Color(255, 220, 220);
    private static final Color ERRO_BORDA = new Color(200, 0, 0);
    private static final Color OK_BG      = new Color(220, 255, 220);
    private static final Color OK_BORDA   = new Color(0, 160, 0);

    private static JTextField campo() {
        JTextField f = new JTextField();
        f.setPreferredSize(new Dimension(220, 28));
        return f;
    }

    //destaca campo em vermelho se vazio
    private static String ler(JTextField campo, String nomeCampo) {
        String val = campo.getText().trim();
        if (val.isEmpty()) {
            campo.setBackground(ERRO_BG);
            campo.setBorder(new LineBorder(ERRO_BORDA, 2));
            throw new IllegalArgumentException(nomeCampo);
        }
        campo.setBackground(OK_BG);
        campo.setBorder(new LineBorder(OK_BORDA, 2));
        return val;
    }

    // === TESTE DE CONEXÃO COM O DOCKER ===
    private static void testarConexao() {
        java.sql.Connection testeConn = ConexaoBanco.getConexao();
        if (testeConn != null) {
            System.out.println("Sua aplicação Java conectou com sucesso ao MySQL no Docker!");
            try {
                testeConn.close();
            } catch (java.sql.SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        } else {
            System.err.println("Falha crítica: Não foi possível conectar ao banco de dados.");
        }
    }

    //máscara de CPF
    private static JTextField campoCpf() {
        JTextField f = campo();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String t = f.getText().replaceAll("[^0-9]", "");
                if (t.length() > 11) t = t.substring(0, 11);
                StringBuilder sb = new StringBuilder(t);
                if (sb.length() > 9) sb.insert(9, '-');
                if (sb.length() > 6) sb.insert(6, '.');
                if (sb.length() > 3) sb.insert(3, '.');
                f.setText(sb.toString());
                f.setCaretPosition(f.getText().length());
            }
        });
        return f;
    }

    //máscara de telefone
    private static JTextField campoTelefone() {
        JTextField f = campo();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String t = f.getText().replaceAll("[^0-9]", "");
                if (t.length() > 11) t = t.substring(0, 11);
                StringBuilder sb = new StringBuilder(t);
                if (sb.length() > 6) sb.insert(6, '-');
                if (sb.length() > 2) sb.insert(2, ')');
                if (sb.length() > 0) sb.insert(0, '(');
                f.setText(sb.toString());
                f.setCaretPosition(f.getText().length());
            }
        });
        return f;
    }

    //campo só aceita números
    private static JTextField campoNumerico() {
        JTextField f = campo();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String t = f.getText().replaceAll("[^0-9]", "");
                f.setText(t);
                f.setCaretPosition(f.getText().length());
            }
        });
        return f;
    }

    //menu com botões clicáveis
    private static String exibirMenu() {
        final String[] escolha = {null};

        JDialog dialog = new JDialog((Frame) null, "Menu - Direção", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(5, 1, 0, 6));
        painel.setBorder(BorderFactory.createTitledBorder("O que deseja fazer?"));

        String[][] opcoes = {
            {"1", "Cadastrar Turma"},
            {"2", "Cadastrar Professor"},
            {"3", "Cadastrar Aluno"},
            {"4", "Buscar Aluno por CPF"},
            {"5", "Ver Relatórios / Cadastros"}
        };

        for (String[] op : opcoes) {
            JButton btn = new JButton(op[0] + " - " + op[1]);
            btn.setPreferredSize(new Dimension(280, 36));
            btn.setFont(btn.getFont().deriveFont(13f));
            String num = op[0];
            btn.addActionListener(ev -> { escolha[0] = num; dialog.dispose(); });
            painel.add(btn);
        }

        JButton btnSair = new JButton("Sair do sistema");
        btnSair.setForeground(Color.RED);
        btnSair.addActionListener(ev -> { escolha[0] = "6"; dialog.dispose(); });

        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        wrapper.add(painel, BorderLayout.CENTER);
        wrapper.add(btnSair, BorderLayout.SOUTH);

        dialog.add(wrapper);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return escolha[0];
    }

    //mensagem de sucesso com ícone verde
    private static void sucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    //confirmação ao cancelar
    private static boolean confirmarCancelamento() {
        int r = JOptionPane.showConfirmDialog(null,
            "Deseja realmente cancelar?\nOs dados preenchidos serão perdidos.",
            "Cancelar cadastro", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return r == JOptionPane.YES_OPTION;
    }

    //dropdown de turmas cadastradas
    private static Turma escolherTurma(Direcao diretor) {
        ArrayList<Turma> turmas = diretor.getTurmas();
        if (turmas.isEmpty()) return null;
        String[] nomes = new String[turmas.size()];
        for (int i = 0; i < turmas.size(); i++)
            nomes[i] = turmas.get(i).getCodigo() + " (Ano " + turmas.get(i).getAno() + ")";
        String escolha = (String) JOptionPane.showInputDialog(
            null, "Selecione a turma:", "Escolher Turma",
            JOptionPane.QUESTION_MESSAGE, null, nomes, nomes[0]);
        if (escolha == null) return null;
        return turmas.get(java.util.Arrays.asList(nomes).indexOf(escolha));
    }

    //relatório com filtro
    private static void exibirRelatorio(Direcao diretor) {
        String[] filtros = {"Tudo", "Somente Turmas", "Somente Professores", "Somente Alunos"};
        String filtro = (String) JOptionPane.showInputDialog(
            null, "O que deseja visualizar?", "Relatório",
            JOptionPane.QUESTION_MESSAGE, null, filtros, filtros[0]);
        if (filtro == null) return;

        StringBuilder sb = new StringBuilder();
        if (filtro.equals("Tudo") || filtro.equals("Somente Turmas")) {
            sb.append("=== TURMAS CADASTRADAS ===\n");
            if (diretor.getTurmas().isEmpty()) sb.append("(Nenhuma turma cadastrada)\n");
            else for (Turma t : diretor.getTurmas()) sb.append("- ").append(t.toString()).append("\n");
        }
        if (filtro.equals("Tudo") || filtro.equals("Somente Professores")) {
            sb.append("\n=== PROFESSORES CADASTRADOS ===\n");
            if (diretor.getProfessores().isEmpty()) sb.append("(Nenhum professor cadastrado)\n");
            else for (Professor p : diretor.getProfessores()) sb.append("- ").append(p.apresentar()).append("\n");
        }
        if (filtro.equals("Tudo") || filtro.equals("Somente Alunos")) {
            sb.append("\n=== ALUNOS CADASTRADOS ===\n");
            if (diretor.getAlunos().isEmpty()) sb.append("(Nenhum aluno cadastrado)\n");
            else for (Aluno a : diretor.getAlunos()) sb.append("- ").append(a.apresentar()).append("\n");
        }

        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        JScrollPane scroll = new JScrollPane(ta);
        scroll.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(null, scroll, "Relatório - " + filtro, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        // LOGIN
        JTextField campoUsuario = campo();
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(220, 28));

        JPanel painelLogin = new JPanel(new GridLayout(2, 2, 6, 8));
        painelLogin.setBorder(BorderFactory.createTitledBorder("Acesso ao Sistema"));
        painelLogin.add(new JLabel("Usuário:")); painelLogin.add(campoUsuario);
        painelLogin.add(new JLabel("Senha:"));   painelLogin.add(campoSenha);

        int opcaoLogin = JOptionPane.showConfirmDialog(
            null, painelLogin, "Login - Sistema Escolar", JOptionPane.OK_CANCEL_OPTION);
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
        sucesso("Login realizado com sucesso!\nBem-vindo(a), " + usuario);
        testarConexao();

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
        } catch (Exception e) {
            System.err.println("Erro nos dados iniciais: " + e.getMessage());
        }

        // DIRETOR LOGADO
        Direcao diretor = new Direcao("Carlos Silva","666.666.666-66","Rua F","6666-6666","carlos@escola.com","Diretor");

        // MENU PRINCIPAL
        boolean rodando = true;
        while (rodando) {
            String opcao = exibirMenu();

            if (opcao == null || opcao.equals("6")) { rodando = false; continue; }

            try {
                switch (opcao) {

                    // 1 - CADASTRAR TURMA
                    case "1": {
                        JTextField fNome = campo();
                        JTextField fAno  = campoNumerico();

                        JPanel p = new JPanel(new GridLayout(2, 2, 6, 8));
                        p.setBorder(BorderFactory.createTitledBorder("Nova Turma"));
                        p.add(new JLabel("Nome da turma:")); p.add(fNome);
                        p.add(new JLabel("Ano:"));           p.add(fAno);

                        int r = JOptionPane.showConfirmDialog(null, p, "Cadastrar Turma", JOptionPane.OK_CANCEL_OPTION);
                        if (r != JOptionPane.OK_OPTION) { if (confirmarCancelamento()) break; else continue; }

                        diretor.cadastrarTurma(new Turma(ler(fNome, "Nome da turma"), Integer.parseInt(ler(fAno, "Ano"))));
                        sucesso("Turma cadastrada: " + fNome.getText());
                        break;
                    }

                    // 2 - CADASTRAR PROFESSOR
                    case "2": {
                        // ordem lógica: Nome → Matrícula → CPF → Endereço → Telefone → E-mail
                        JTextField fNome     = campo();
                        JTextField fMatr     = campo();
                        JTextField fCpf      = campoCpf();
                        JTextField fEndereco = campo();
                        JTextField fTel      = campoTelefone();
                        JTextField fEmail    = campo();

                        JPanel pPessoa = new JPanel(new GridLayout(6, 2, 6, 8));
                        pPessoa.setBorder(BorderFactory.createTitledBorder("Dados do Professor"));
                        pPessoa.add(new JLabel("Nome:"));      pPessoa.add(fNome);
                        pPessoa.add(new JLabel("Matrícula:")); pPessoa.add(fMatr);
                        pPessoa.add(new JLabel("CPF:"));       pPessoa.add(fCpf);
                        pPessoa.add(new JLabel("Endereço:"));  pPessoa.add(fEndereco);
                        pPessoa.add(new JLabel("Telefone:"));  pPessoa.add(fTel);
                        pPessoa.add(new JLabel("E-mail:"));    pPessoa.add(fEmail);

                        int r1 = JOptionPane.showConfirmDialog(null, pPessoa, "Cadastrar Professor (1/2)", JOptionPane.OK_CANCEL_OPTION);
                        if (r1 != JOptionPane.OK_OPTION) { if (confirmarCancelamento()) break; else continue; }

                        JTextField fDisc  = campo();
                        JTextField fCodD  = campo();
                        JTextField fCarga = campoNumerico();

                        JPanel pDisc = new JPanel(new GridLayout(3, 2, 6, 8));
                        pDisc.setBorder(BorderFactory.createTitledBorder("Disciplina do Professor"));
                        pDisc.add(new JLabel("Nome da disciplina:")); pDisc.add(fDisc);
                        pDisc.add(new JLabel("Código:"));             pDisc.add(fCodD);
                        pDisc.add(new JLabel("Carga horária (h):"));  pDisc.add(fCarga);

                        int r2 = JOptionPane.showConfirmDialog(null, pDisc, "Cadastrar Professor (2/2)", JOptionPane.OK_CANCEL_OPTION);
                        if (r2 != JOptionPane.OK_OPTION) { if (confirmarCancelamento()) break; else continue; }

                        Disciplina disciplina = new Disciplina(
                            ler(fDisc, "Disciplina"), ler(fCodD, "Código"),
                            Integer.parseInt(ler(fCarga, "Carga horária")));
                        Professor professor = new Professor(
                            ler(fNome, "Nome"), ler(fCpf, "CPF"), ler(fEndereco, "Endereço"),
                            ler(fTel, "Telefone"), ler(fEmail, "E-mail"), ler(fMatr, "Matrícula"),
                            disciplina);
                        diretor.cadastrarProfessor(professor);
                        sucesso("Professor cadastrado: " + professor.getNome());
                        break;
                    }

                    // 3 - CADASTRAR ALUNO
                    case "3": {
                        if (diretor.getTurmas().isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                "Nenhuma turma cadastrada!\nCadastre uma turma primeiro.",
                                "Sem turmas", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                        JTextField fNomeResp  = campo();
                        JTextField fCpfResp   = campoCpf();
                        JTextField fEndResp   = campo();
                        JTextField fTelResp   = campoTelefone();
                        JTextField fEmailResp = campo();
                        JTextField fParent    = campo();

                        JPanel pResp = new JPanel(new GridLayout(6, 2, 6, 8));
                        pResp.setBorder(BorderFactory.createTitledBorder("Dados do Responsável"));
                        pResp.add(new JLabel("Nome:"));       pResp.add(fNomeResp);
                        pResp.add(new JLabel("CPF:"));        pResp.add(fCpfResp);
                        pResp.add(new JLabel("Endereço:"));   pResp.add(fEndResp);
                        pResp.add(new JLabel("Telefone:"));   pResp.add(fTelResp);
                        pResp.add(new JLabel("E-mail:"));     pResp.add(fEmailResp);
                        pResp.add(new JLabel("Parentesco:")); pResp.add(fParent);

                        int r1 = JOptionPane.showConfirmDialog(null, pResp, "Cadastrar Aluno (1/2) - Responsável", JOptionPane.OK_CANCEL_OPTION);
                        if (r1 != JOptionPane.OK_OPTION) { if (confirmarCancelamento()) break; else continue; }

                        Turma turmaSelecionada = escolherTurma(diretor);
                        if (turmaSelecionada == null) break;

                        JTextField fNomeAluno  = campo();
                        JTextField fCpfAluno   = campoCpf();
                        JTextField fEmailAluno = campo();
                        JTextField fRa         = campo();
                        JTextField fPeriodo    = campoNumerico();

                        JPanel pAluno = new JPanel(new GridLayout(5, 2, 6, 8));
                        pAluno.setBorder(BorderFactory.createTitledBorder("Dados do Aluno  |  Turma: " + turmaSelecionada.getCodigo()));
                        pAluno.add(new JLabel("Nome:"));    pAluno.add(fNomeAluno);
                        pAluno.add(new JLabel("CPF:"));     pAluno.add(fCpfAluno);
                        pAluno.add(new JLabel("E-mail:"));  pAluno.add(fEmailAluno);
                        pAluno.add(new JLabel("RA:"));      pAluno.add(fRa);
                        pAluno.add(new JLabel("Período:")); pAluno.add(fPeriodo);

                        int r2 = JOptionPane.showConfirmDialog(null, pAluno, "Cadastrar Aluno (2/2) - Aluno", JOptionPane.OK_CANCEL_OPTION);
                        if (r2 != JOptionPane.OK_OPTION) { if (confirmarCancelamento()) break; else continue; }

                        String endResp = ler(fEndResp, "Endereço do responsável");
                        String telResp = ler(fTelResp, "Telefone do responsável");

                        Responsavel responsavel = new Responsavel(
                            ler(fNomeResp, "Nome do responsável"), ler(fCpfResp, "CPF do responsável"),
                            endResp, telResp, ler(fEmailResp, "E-mail do responsável"), ler(fParent, "Parentesco"));
                        Aluno aluno = new Aluno(
                            ler(fNomeAluno, "Nome do aluno"), ler(fCpfAluno, "CPF do aluno"),
                            endResp, telResp, ler(fEmailAluno, "E-mail do aluno"), ler(fRa, "RA"),
                            turmaSelecionada, Integer.parseInt(ler(fPeriodo, "Período")));
                        diretor.cadastrarAluno(aluno, responsavel);
                        sucesso("Aluno cadastrado: " + aluno.getNome() + "\nResponsável: " + responsavel.getNome());
                        break;
                    }

                    // 4 - BUSCAR ALUNO POR CPF
                    case "4": {
                        JTextField fCpf = campoCpf();
                        JPanel p = new JPanel(new GridLayout(1, 2, 6, 8));
                        p.setBorder(BorderFactory.createTitledBorder("Buscar Aluno"));
                        p.add(new JLabel("CPF do aluno:")); p.add(fCpf);

                        int r = JOptionPane.showConfirmDialog(null, p, "Busca por CPF", JOptionPane.OK_CANCEL_OPTION);
                        if (r != JOptionPane.OK_OPTION) break;

                        String cpfBusca = fCpf.getText().replaceAll("[^0-9]", "");
                        if (cpfBusca.isEmpty()) { JOptionPane.showMessageDialog(null, "CPF inválido!"); break; }

                        Aluno encontrado = null;
                        for (Aluno a : diretor.getAlunos())
                            if (a.getCpf().replaceAll("[^0-9]", "").equals(cpfBusca)) { encontrado = a; break; }

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
                                for (Responsavel resp : encontrado.getResponsaveis())
                                    sb.append("- ").append(resp.getNome()).append(" (").append(resp.getParentesco()).append(")\n")
                                      .append("  CPF: ").append(resp.getCpf()).append(" | Tel: ").append(resp.getTelefone()).append("\n");
                            }
                            JTextArea ta = new JTextArea(sb.toString());
                            ta.setEditable(false);
                            JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Detalhes do Aluno", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //oferecer nova tentativa
                            int retry = JOptionPane.showConfirmDialog(null,
                                "Aluno com CPF \"" + fCpf.getText() + "\" não encontrado.\nDeseja buscar novamente?",
                                "Não Encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    }

                    // 5 - VER RELATÓRIOS
                    case "5": {
                        exibirRelatorio(diretor);
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