package interfazcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientHome extends JFrame{
    private JTable usersTable;
    private JScrollPane usersScroll;
    private DefaultTableModel usersNames;
    
    public ClientHome() {
        super("Chat");
        this.setSize(150, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        usersNames = new DefaultTableModel(new String[] {"Users"}, 0);
        usersTable = new JTable(usersNames);
        usersTable.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        int row=usersTable.rowAtPoint(e.getPoint());
                        int col= usersTable.columnAtPoint(e.getPoint());
                        ClientConversation conversation = new ClientConversation(usersTable.getValueAt(row,col).toString());
                        conversation.show();
                    }
                }
            );
        usersScroll = new JScrollPane(usersTable);
        
        GroupLayout layout = new GroupLayout(this.getContentPane());
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addComponent(usersScroll)
        );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(usersScroll)
        );
        this.setLayout(layout);
        
        setNames();
    }

    private void setNames() {
        String[] names = {"Moy", "Juan", "Diego", "Pepe"};
        for(String name : names) {
            usersNames.addRow(new Object[] {name});
        }
    }
}
