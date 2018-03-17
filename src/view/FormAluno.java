package view;
import control.*;
import model.*;

import javax.swing.*;

public class FormAluno extends JFrame {

	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextRa = null;
	private JTextField jTextNome = null;
	private JButton jButtonInserir = null;
	private JButton jButtonPesquisar = null;
	private JButton jButtonRemover = null;

	//private ControladorAlunos controlador = null;
	private IControlador controlador = null;

	/**
	 * This method initializes jTextRa
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextRa() {
		if (jTextRa == null) {
			jTextRa = new JTextField();
			jTextRa.setBounds(new java.awt.Rectangle(75, 31, 93, 20));
		}
		return jTextRa;
	}

	/**
	 * This method initializes jTextNome
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextNome() {
		if (jTextNome == null) {
			jTextNome = new JTextField();
			jTextNome.setBounds(new java.awt.Rectangle(76, 60, 195, 20));
		}
		return jTextNome;
	}

	/**
	 * This method initializes jButtonInserir
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonInserir() {
		if (jButtonInserir == null) {
			jButtonInserir = new JButton();
			jButtonInserir.setBounds(new java.awt.Rectangle(10, 130, 100, 22));
			jButtonInserir.setText("Inserir");
			jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					//JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");

					// solicita ao controlador que efetue a inclusão do aluno
					controlador.inserirAluno(jTextRa.getText(), jTextNome.getText());

					jTextRa.setText("");
					jTextNome.setText("");
				}
			});
		}
		return jButtonInserir;
	}

	/**
	 * This method initializes jButtonPesquisar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPesquisar() {
		if (jButtonPesquisar == null) {
			jButtonPesquisar = new JButton();
			jButtonPesquisar.setBounds(new java.awt.Rectangle(120, 130, 100, 22));
			jButtonPesquisar.setText("Pesquisar");
			jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					// solicita ao controlador que efetue a busca pelo aluno
					// pelo RA
					Aluno aluno = controlador.pesquisarAluno(jTextRa.getText());

					// se encontrou um aluno com o RA
					if (aluno != null) {

						// exibe as informações na tela
						jTextRa.setText(aluno.getRa());
						jTextNome.setText(aluno.getNome());
					}
				}
			});
		}
		return jButtonPesquisar;
	}

	/**
	 * This method initializes jButtonLimpar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonRemover() {
		if (jButtonRemover == null) {
			jButtonRemover = new JButton();
			jButtonRemover.setBounds(new java.awt.Rectangle(230, 130, 100, 22));
			jButtonRemover.setText("Remover");
			jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					// solicita ao controlador que efetue a exclusão do aluno pelo RA
					controlador.removerAluno(jTextRa.getText());

					// limpa a tela
					jTextRa.setText("");
					jTextNome.setText("");

				}
			});
		}
		return jButtonRemover;
	}

	/**
	 * This is the default constructor
	 */
	public FormAluno() {
		super();
		initialize();

		//controlador = new ControladorAlunos();
		controlador = new ControladorDBAlunos();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(350, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(15, 62, 38, 16));
			jLabel1.setText("Nome:");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(14, 33, 38, 16));
			jLabel.setText("Ra:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJTextRa(), null);
			jContentPane.add(getJTextNome(), null);
			jContentPane.add(getJButtonInserir(), null);
			jContentPane.add(getJButtonPesquisar(), null);
			jContentPane.add(getJButtonRemover(), null);
		}
		return jContentPane;
	}

	public static void main(String[] args) {

		FormAluno form = new FormAluno();
		form.setVisible(true);

	}

}