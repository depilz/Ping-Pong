package proyecto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Fondo extends JFrame 
{
	private JPanel contentPane;
	private ElegirCancha elegirCancha = new ElegirCancha();
	
	public static void main(String[] args) 
	{	
		Fondo fondo = new Fondo();
		fondo.setVisible(true);
	}

 	public Fondo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 70, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(elegirCancha);
	}

}