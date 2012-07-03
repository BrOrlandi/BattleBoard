package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JBoard extends JDialog{
	
	JScrollPane scrollPane;
	ImageJPanel contentPane;
	
	JButton button;
	ItemTable jtable;
	
	public JBoard()
	{	
		setSize(new Dimension(800, 500));
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		try {
			contentPane = new ImageJPanel(path+"grass-background.jpg");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Imagem nao encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		setContentPane(contentPane);
		
		button = new JButton("Botao Teste!");
		contentPane.add(button);
		
		DefaultTableModel tm =  new DefaultTableModel(20, 10); 
				  
		OpaqueJTable jtable1 = new OpaqueJTable();  
		jtable1.setCellSelectionEnabled(true);
		
		jtable1.setModel(tm);  
		jtable1.setAlpha(0.3f);
		
//		
//		TableColumnModel columnModel = jtable1.getComponentAt(0, 0);
//		JTableRenderer renderer = new JTableRenderer();
//		renderer.setValue(new ImageIcon(path+"panda.png"));
//		columnModel.get.setCellRenderer(renderer);

		  
		getContentPane().add(jtable1);
	}
}
