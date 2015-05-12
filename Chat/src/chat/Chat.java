package chat;

/**
 * @author  MJDPCode
 * 
 *          Moises Navarro
 *          Juan Jimenez
 *          Diego Celada
 *          Jose Gomez
 * 
 * @version 1.0
 * @since   05/10/2015
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LoginForm login = new LoginForm();
        login.setVisible(true);
        login.setServerIP();
        //Client client = new Client("user");
        //client.connect("IP", 13);
    }
}
