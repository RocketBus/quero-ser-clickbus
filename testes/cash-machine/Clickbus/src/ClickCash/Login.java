package ClickCash;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcpf;
	private JTextField txtconta;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */public Login() {
			setBounds(100, 100, 474, 440);
			getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(138, 43, 226));
			panel.setBounds(10, 11, 433, 373);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Caixa Eletr\u00F4nico");
			lblNewLabel.setBounds(58, 25, 143, 27);
			lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			lblNewLabel.setForeground(new Color(255, 255, 255));
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("ClickCa$h");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
			lblNewLabel_1.setBounds(78, 59, 87, 27);
			panel.add(lblNewLabel_1);
			
					
			JLabel lblNewLabel_3 = new JLabel("CONTA:");
			lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setBounds(58, 155, 58, 22);
			panel.add(lblNewLabel_3);
			
			txtconta = new JTextField();
			txtconta.setBounds(151, 153, 140, 33);
			panel.add(txtconta);
			txtconta.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("SENHA:");
			lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
			lblNewLabel_4.setForeground(new Color(255, 255, 255));
			lblNewLabel_4.setBounds(54, 214, 56, 22);
			panel.add(lblNewLabel_4);
			
			txtpassword = new JPasswordField();
			txtpassword.setBounds(151, 212, 140, 33);
			panel.add(txtpassword);
			
			JLabel logo = new JLabel("");
			Image img = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
			logo.setIcon(new ImageIcon(img));
			logo.setBounds(230, 11, 120, 90);
			panel.add(logo);
			{
				JPanel btnok = new JPanel();
				btnok.setBounds(-10, 308, 527, 65);
				panel.add(btnok);
				btnok.setBackground(new Color(138, 43, 226));
				btnok.setLayout(null);
				{
					JButton okButton = new JButton("ENTER");
					okButton.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							
						
							if (txtconta.getText().equals("123456") && (txtpassword.getText().equals("123456"))){
					                Withdraw withdraw = new Withdraw();
					                withdraw.setExtendedState(JFrame.NORMAL);
					                withdraw.setVisible(true);
				                    setVisible(false);
					                Login.setExtendedState(JFrame.DISPOSE_ON_CLOSE);
					                JOptionPane.showMessageDialog(null, "Acesso liberado ao saque para conta:" +
					                		txtconta.getText(),"Bem Vindo", JOptionPane.PLAIN_MESSAGE);
					            }
							else {
									JOptionPane.showMessageDialog(null,"Dados incorretos. Tente novamente" );
						}
					}
						
					});
					
					okButton.setBounds(97, 5, 96, 31);
					okButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
					okButton.setBackground(new Color(240, 230, 140));
					okButton.setActionCommand("OK");
					btnok.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton btncancel = new JButton("CANCELAR");
					btncancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);	}
					});
					btncancel.setBounds(221, 5, 117, 31);
					btncancel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
					btncancel.setBackground(new Color(240, 230, 140));
					btncancel.setActionCommand("Cancel");
					btnok.add(btncancel);
				}
			}
		}	
	 protected static void setExtendedState(int disposeOnClose) {
			// TODO Auto-generated method stub
			
		}

		protected static void DISPOSE_ON_CLOSE(int i) {
			// TODO Auto-generated method stub
			
		}
	}
