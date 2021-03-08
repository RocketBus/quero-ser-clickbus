	package ClickCash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	int Total;
	int bill100,bill50,bill20,bill10;
	protected static final Integer Interger = null;
	protected static final int NullPointerException = 0;
	private JTextField valor;
	private JTextField txt100;
	private JTextField txt50;
	private JLabel n20;
	private JTextField txt20;
	private JLabel n10;
	private JTextField txt10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Withdraw() {
		
		setLocationRelativeTo(this);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 742);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(138, 43, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digite o valor do saque:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(120, 201, 260, 56);
		contentPane.add(lblNewLabel);
		
		valor = new JTextField();
		valor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		valor.setBounds(408, 209, 135, 45);
		contentPane.add(valor);
		valor.setColumns(10);
		
		JLabel n100 = new JLabel("");
		
		Image img100 = new ImageIcon(this.getClass().getResource("/nota 100.jpg")).getImage();
		n100.setIcon(new ImageIcon(img100));
		
		n100.setBounds(65, 307, 294, 130);
		contentPane.add(n100);
		
		txt100 = new JTextField();
		txt100.setHorizontalAlignment(SwingConstants.CENTER);
		txt100.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		txt100.setBounds(369, 307, 81, 37);
		contentPane.add(txt100);
		txt100.setColumns(10);
		
		
		
		JLabel n50 = new JLabel("");
		Image img50 = new ImageIcon(this.getClass().getResource("/Nota 50.jpg")).getImage();
		n50.setIcon(new ImageIcon(img50));
		n50.setBounds(65, 465, 294, 130);
		contentPane.add(n50);
		
		txt50 = new JTextField();
		txt50.setHorizontalAlignment(SwingConstants.CENTER);
		txt50.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		txt50.setColumns(10);
		txt50.setBounds(369, 465, 81, 37);
		contentPane.add(txt50);
		
		n20 = new JLabel("");
		Image img20 = new ImageIcon(this.getClass().getResource("/Nota 20.jpg")).getImage();
		n20.setIcon(new ImageIcon(img20));
		n20.setBounds(486, 307, 294, 130);
		contentPane.add(n20);
		
		txt20 = new JTextField();
		txt20.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		txt20.setColumns(10);
		txt20.setBounds(790, 307, 78, 37);
		contentPane.add(txt20);
		
		n10 = new JLabel("");
		Image img10 = new ImageIcon(this.getClass().getResource("/Nota 10.jpg")).getImage();
		n10.setIcon(new ImageIcon(img10));
		n10.setBounds(486, 466, 294, 130);
		contentPane.add(n10);
		
		txt10 = new JTextField();
		txt10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		txt10.setColumns(10);
		txt10.setBounds(787, 465, 81, 37);
		contentPane.add(txt10);
		
		JButton btnNewButton = new JButton("SACAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Total = Integer.valueOf(valor.getText());
				
				
								
				if ((Total < 10) || (Total %10 > 0) || (valor == null))  {
					JOptionPane.showMessageDialog(null, "Valor inválido.","Digite novamente", JOptionPane.WARNING_MESSAGE, null);
					txt100.setVisible(false);
					txt50.setVisible(false);
					txt20.setVisible(false);
					txt10.setVisible(false);									
				}
				else {
					int bill100 = Total/100;
					int rt100 = Total%100;
					
					int bill50 = rt100/50;
					int rt50 = rt100%50;
					
					int bill20 = rt50/20;
					int rt20 = rt50%20;
					
					int bill10 = rt20/10;
					int rt10= rt20%10;
					
		       		                
		        
		        txt100.setText("" + bill100);
		        txt100.setVisible(true);
																
		        txt50.setText("" + bill50);
		        txt50.setVisible(true);
		        		        
		        txt20.setText("" + bill20);
		        txt20.setVisible(true);
		        
		        txt10.setText("" + bill10);
		        txt10.setVisible(true);
		        
		        JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso!","Obrigado(a)", JOptionPane.WARNING_MESSAGE, null);
		        }
			
			} 
			
	       
			
		});
		
		btnNewButton.setBackground(new Color(240, 230, 140));
		btnNewButton.setForeground(new Color(148, 0, 211));
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBounds(625, 209, 121, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SAIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBackground(new Color(240, 230, 140));
		btnNewButton_1.setForeground(new Color(138, 43, 226));
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_1.setBounds(779, 655, 89, 37);
		contentPane.add(btnNewButton_1);
		Image img = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(240, 230, 140), 0));
		panel.setForeground(new Color(240, 230, 140));
		panel.setBackground(new Color(138, 43, 226));
		panel.setBounds(221, 32, 449, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Caixa Eletr\u00F4nico");
		lblNewLabel_2.setBounds(70, 27, 174, 32);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		
		JLabel lblNewLabel_1_1 = new JLabel("ClickCa$h");
		lblNewLabel_1_1.setBounds(100, 70, 106, 32);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		
		JLabel logo = new JLabel("");
		logo.setBounds(285, 29, 120, 90);
		panel.add(logo);
		logo.setIcon(new ImageIcon(img));
	}
}
