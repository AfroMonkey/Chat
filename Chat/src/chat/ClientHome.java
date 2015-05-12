package chat;

import java.net.Socket;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientHome extends JFrame{
    private JTable usersTable;
    private final JScrollPane usersScroll;
    private static DefaultTableModel usersNames;
    
    private final String user;
    private final Socket socket;
    
    public ClientHome(String user, Socket socket) {
        super(user);
        this.setSize(150, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.user = user;
        this.socket = socket;
        
        usersNames = new DefaultTableModel(new String[] {"Users"}, 0);
        usersTable = new JTable(usersNames);
        usersTable.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        int row = usersTable.rowAtPoint(e.getPoint());
                        int col = usersTable.columnAtPoint(e.getPoint());
                        ClientConversation conversation = new ClientConversation(usersTable.getValueAt(row,col).toString(), socket);
                        conversation.setVisible(true);
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
        
        Thread contactListener = new Thread(new ContactListener(socket, user));
        contactListener.start();
    }
    
    public static void setUsersConnected(String[] users) {
        for(String user : users) {
            usersNames.addRow(new Object[] {user});
        }
    }
}
