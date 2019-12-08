package GUI;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import RdF.DatabaseConnector;
import RdF.ServerRdF;
import java.awt.Insets;
import java.awt.Color;

public class InsertDb {

    private JFrame frmServer;
    private JTextField db_user;
    private JTextField db_pass;
    private JTextField db_url;
    private ServerRdF server;
    private boolean ok = true;
    private DatabaseConnector dbconn;
    private String msg = "";

    public InsertDb(ServerRdF serverRdF) {
        server = serverRdF;
        frmServer = new JFrame();
        frmServer.getContentPane().setBackground(Color.WHITE);
        frmServer.setTitle("RdF: Inserisci database");
        frmServer.setBounds(100, 100, 494, 300);
        frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmServer.getContentPane().setLayout(gridBagLayout);

//----------------------------------------------------------
        JLabel lblUserDb = new JLabel("Inserisci user (host) database");
        GridBagConstraints gbc_lblUserDb = new GridBagConstraints();
        gbc_lblUserDb.anchor = GridBagConstraints.WEST;
        gbc_lblUserDb.gridwidth = 3;
        gbc_lblUserDb.insets = new Insets(0, 0, 5, 5);
        gbc_lblUserDb.gridx = 1;
        gbc_lblUserDb.gridy = 1;
        frmServer.getContentPane().add(lblUserDb, gbc_lblUserDb);

//----------------------------------------------------------
        db_user = new JTextField("");
        GridBagConstraints gbc_db_user = new GridBagConstraints();
        gbc_db_user.gridwidth = 8;
        gbc_db_user.insets = new Insets(0, 0, 5, 5);
        gbc_db_user.fill = GridBagConstraints.HORIZONTAL;
        gbc_db_user.gridx = 1;
        gbc_db_user.gridy = 2;
        frmServer.getContentPane().add(db_user, gbc_db_user);
        db_user.setColumns(10);

//----------------------------------------------------------
        JLabel lblPassDb = new JLabel("Inserisci password database");
        GridBagConstraints gbc_lblPassDb = new GridBagConstraints();
        gbc_lblPassDb.anchor = GridBagConstraints.WEST;
        gbc_lblPassDb.gridwidth = 3;
        gbc_lblPassDb.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassDb.gridx = 1;
        gbc_lblPassDb.gridy = 3;
        frmServer.getContentPane().add(lblPassDb, gbc_lblPassDb);

//----------------------------------------------------------
        db_pass = new JPasswordField("");
        GridBagConstraints gbc_db_pass = new GridBagConstraints();
        gbc_db_pass.gridwidth = 8;
        gbc_db_pass.insets = new Insets(0, 0, 5, 5);
        gbc_db_pass.fill = GridBagConstraints.HORIZONTAL;
        gbc_db_pass.gridx = 1;
        gbc_db_pass.gridy = 4;
        frmServer.getContentPane().add(db_pass, gbc_db_pass);
        db_pass.setColumns(10);

//----------------------------------------------------------	
        JLabel lblUrlDb = new JLabel("Inserisci url (username) database");
        GridBagConstraints gbc_lblUrlDb = new GridBagConstraints();
        gbc_lblUrlDb.anchor = GridBagConstraints.WEST;
        gbc_lblUrlDb.gridwidth = 3;
        gbc_lblUrlDb.insets = new Insets(0, 0, 5, 5);
        gbc_lblUrlDb.gridx = 1;
        gbc_lblUrlDb.gridy = 5;
        frmServer.getContentPane().add(lblUrlDb, gbc_lblUrlDb);

//----------------------------------------------------------
        db_url = new JTextField("");
        GridBagConstraints gbc_db_url = new GridBagConstraints();
        gbc_db_url.gridwidth = 8;
        gbc_db_url.insets = new Insets(0, 0, 5, 5);
        gbc_db_url.fill = GridBagConstraints.HORIZONTAL;
        gbc_db_url.gridx = 1;
        gbc_db_url.gridy = 6;
        frmServer.getContentPane().add(db_url, gbc_db_url);
        db_pass.setColumns(10);
//----------------------------------------------------------		
        JButton btnExit = new JButton("ESCI");
        btnExit.addActionListener(e -> System.exit(0));
        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.insets = new Insets(0, 0, 0, 5);
        gbc_btnExit.gridx = 5;
        gbc_btnExit.gridy = 7;
        frmServer.getContentPane().add(btnExit, gbc_btnExit);

//----------------------------------------------------------
        JButton btnStartServer = new JButton("CONFERMA");
        btnStartServer.addActionListener(e -> {
			String user = db_user.getText();
			String pswd = db_pass.getText();
			String url = db_url.getText();

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
                    frmServer.setVisible(false);
					SignUpserAdmin sign = new SignUpserAdmin(server);
				}
				else {
				    if(temp==1)
				    {
                        frmServer.setVisible(false);
                        InsertServer is = new InsertServer(server);
                    }
                }
			}
		});
        GridBagConstraints gbc_btnStartServer = new GridBagConstraints();
        gbc_btnStartServer.insets = new Insets(0, 0, 0, 5);
        gbc_btnStartServer.gridx = 8;
        gbc_btnStartServer.gridy = 7;
        frmServer.getContentPane().add(btnStartServer, gbc_btnStartServer);

        frmServer.show();
    }


}