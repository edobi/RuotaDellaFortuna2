package GUI;

import RdF.DatabaseConnector;
import RdF.ServerRdF;

import javax.swing.*;

public class AddDatabase {
    private JTextField dbuser;
    private JButton ESCIButton;
    private JButton CONFERMAButton;
    private JPasswordField passwordField1;
    private JTextField urldatabase;
    private ServerRdF server;
    private boolean ok = true;
    private DatabaseConnector dbconn;
    private String msg = "";

    public AddDatabase(ServerRdF serverRdF) {
        server = serverRdF;


    CONFERMAButton.addActionListener(e -> {
        String user = dbuser.getText();
        String pswd = passwordField1.getText();
        String url = urldatabase.getText();

        if (user.equals("")) {
            msg = msg + "- Nome database non valido \n";
            ok = false;
        }

        if (pswd.equals("")) {
            msg = msg + "- Password non valida \n";
            ok = false;
        }

        if (url.equals("")) {
            msg = msg + "- Indirizzo database non valido \n";
            ok = false;
        }
        if (!ok) {
            JOptionPane.showMessageDialog(null, msg, "Attenzione", JOptionPane.ERROR_MESSAGE);
        } else {
            //frmServer.setVisible(false);
            //InsertServer is = new InsertServer(server);

            dbconn = new DatabaseConnector(user, url, pswd);
            server.setDbcon(dbconn);
            //System.out.println(new DatabaseConnector(user, url, pswd));
            int temp=server.Adminex();
            if (temp==0) {
                SignUpserAdmin sign = new SignUpserAdmin(server);
            }
            else {
                if(temp==1)
                {
                    InsertServer is = new InsertServer(server);
                }
            }
        }

});
        ESCIButton.addActionListener(e -> System.exit(0));
}
}