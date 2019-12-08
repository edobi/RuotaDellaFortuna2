package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import RdF.ProxyRdF;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stats {//da terminare con vladi
  private JLabel lblPMax;
  private JLabel lblUsernman;
  private JLabel lblPManche;
  private JLabel lblUsernpart;
  private JLabel lblMaxAvg;
  private JLabel lblNwin;
  private JLabel lblNmanwin;
  private JLabel lblPLose;
  private JLabel lblMatchobs;
  private JLabel lblManobs;
  private JLabel lblAvgpunwin;
  private JLabel lblAvgnext;
  private JLabel lblAvglose;
  private JLabel lblAvgMoves;

    public void setlblPMax(String txt)
    {
        lblPMax.setText(txt);
    }

    public void setLblUsernman(String txt)
    {
        lblUsernman.setText(txt);
    }

    public void setLblPManche(String txt)
    {
        lblPManche.setText(txt);
    }

    public void setLblUsernpart(String txt)
    {
        lblUsernpart.setText(txt);
    }

    public void setLblMaxAvg(String txt)
    {
        lblMaxAvg.setText(txt);
    }

    public void setLblNwin(String txt)
    {
        lblNwin.setText(txt);
    }

    public void setLblNmanwin(String txt)
    {
        lblNmanwin.setText(txt);
    }

    public void setLblPLose(String txt)
    {
        lblPLose.setText(txt);
    }

    public void setLblMatchobs(String txt)
    {
        lblMatchobs.setText(txt);
    }

    public void setLblManobs(String txt)
    {
        lblManobs.setText(txt);
    }

    public void setLblAvgpunwin(String txt)
    {
        lblAvgpunwin.setText(txt);
    }

    public void setLblAvgnext(String txt)
    {
        lblAvgnext.setText(txt);
    }

    public void setLblAvglose(String txt)
    {
        lblAvglose.setText(txt);
    }

    public void setLblAvgMoves(String txt)
    {
        lblAvgMoves.setText(txt);
    }

    public Stats(ProxyRdF prox) {
        Initialize();
        prox.Stats(this,"");
    }

    private void Initialize()
    {
        JFrame frmStatistics = new JFrame();
        frmStatistics.getContentPane().setBackground(Color.WHITE);
        frmStatistics.setTitle("RdF: Statistiche");
        frmStatistics.setBackground(Color.BLUE);
        frmStatistics.setBounds(100, 100, 1211, 466);
        frmStatistics.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 0, 79, 52, 129, 0, 60, 0};
        gridBagLayout.rowHeights = new int[]{21, 29, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmStatistics.getContentPane().setLayout(gridBagLayout);
        frmStatistics.show();

        JLabel lblGlobalStats = new JLabel("STATISTICHE GLOBALI");
        GridBagConstraints gbc_lblGlobalStats = new GridBagConstraints();
        gbc_lblGlobalStats.gridwidth = 2;
        gbc_lblGlobalStats.insets = new Insets(0, 0, 5, 5);
        gbc_lblGlobalStats.gridx = 1;
        gbc_lblGlobalStats.gridy = 1;
        frmStatistics.getContentPane().add(lblGlobalStats, gbc_lblGlobalStats);

        JLabel lblNewLabel = new JLabel(" STATISTICHE  INDIVIDUALI");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.anchor = GridBagConstraints.ABOVE_BASELINE;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 4;
        gbc_lblNewLabel.gridy = 1;
        frmStatistics.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblMaxPoints = new JLabel(" Player con max punteggio");
        GridBagConstraints gbc_lblMaxPoints = new GridBagConstraints();
        gbc_lblMaxPoints.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaxPoints.gridx = 1;
        gbc_lblMaxPoints.gridy = 3;
        frmStatistics.getContentPane().add(lblMaxPoints, gbc_lblMaxPoints);

        lblPMax = new JLabel("");
        GridBagConstraints gbc_labelPMax = new GridBagConstraints();
        gbc_labelPMax.insets = new Insets(0, 0, 5, 5);
        gbc_labelPMax.gridx = 2;
        gbc_labelPMax.gridy = 3;
        frmStatistics.getContentPane().add(lblPMax, gbc_labelPMax);

        JLabel lblUserManche = new JLabel("Numero manche");
        GridBagConstraints gbc_lblUserManche = new GridBagConstraints();
        gbc_lblUserManche.insets = new Insets(0, 0, 5, 5);
        gbc_lblUserManche.gridx = 4;
        gbc_lblUserManche.gridy = 3;
        frmStatistics.getContentPane().add(lblUserManche, gbc_lblUserManche);

        lblUsernman = new JLabel("");
        GridBagConstraints gbc_lblUsernman = new GridBagConstraints();
        gbc_lblUsernman.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsernman.gridx = 5;
        gbc_lblUsernman.gridy = 3;
        frmStatistics.getContentPane().add(lblUsernman, gbc_lblUsernman);

        JLabel lblPlayerManche = new JLabel("Player con pi\u00F9 manche");
        GridBagConstraints gbc_lblPlayerManche = new GridBagConstraints();
        gbc_lblPlayerManche.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlayerManche.gridx = 1;
        gbc_lblPlayerManche.gridy = 4;
        frmStatistics.getContentPane().add(lblPlayerManche, gbc_lblPlayerManche);

        lblPManche = new JLabel("");
        GridBagConstraints gbc_lblPManche = new GridBagConstraints();
        gbc_lblPManche.insets = new Insets(0, 0, 5, 5);
        gbc_lblPManche.gridx = 2;
        gbc_lblPManche.gridy = 4;
        frmStatistics.getContentPane().add(lblPManche, gbc_lblPManche);

        JLabel lblNumMatch = new JLabel("Numero partite");
        GridBagConstraints gbc_lblNumMatch = new GridBagConstraints();
        gbc_lblNumMatch.insets = new Insets(0, 0, 5, 5);
        gbc_lblNumMatch.gridx = 4;
        gbc_lblNumMatch.gridy = 4;
        frmStatistics.getContentPane().add(lblNumMatch, gbc_lblNumMatch);

        lblUsernpart = new JLabel("");
        GridBagConstraints gbc_lblUsernpart = new GridBagConstraints();
        gbc_lblUsernpart.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsernpart.gridx = 5;
        gbc_lblUsernpart.gridy = 4;
        frmStatistics.getContentPane().add(lblUsernpart, gbc_lblUsernpart);

        JLabel lblPlayeerAvg = new JLabel(" Player con media punti pi\u00F9 alta");
        GridBagConstraints gbc_lblPlayeerAvg = new GridBagConstraints();
        gbc_lblPlayeerAvg.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlayeerAvg.gridx = 1;
        gbc_lblPlayeerAvg.gridy = 5;
        frmStatistics.getContentPane().add(lblPlayeerAvg, gbc_lblPlayeerAvg);

        lblMaxAvg = new JLabel("");
        GridBagConstraints gbc_lblMaxAvg = new GridBagConstraints();
        gbc_lblMaxAvg.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaxAvg.gridx = 2;
        gbc_lblMaxAvg.gridy = 5;
        frmStatistics.getContentPane().add(lblMaxAvg, gbc_lblMaxAvg);

        JLabel lblUserWin = new JLabel("Numero partite vinte");
        GridBagConstraints gbc_lblUserWin = new GridBagConstraints();
        gbc_lblUserWin.insets = new Insets(0, 0, 5, 5);
        gbc_lblUserWin.gridx = 4;
        gbc_lblUserWin.gridy = 5;
        frmStatistics.getContentPane().add(lblUserWin, gbc_lblUserWin);

        lblNwin = new JLabel("");
        GridBagConstraints gbc_lblNwin = new GridBagConstraints();
        gbc_lblNwin.insets = new Insets(0, 0, 5, 5);
        gbc_lblNwin.gridx = 5;
        gbc_lblNwin.gridy = 5;
        frmStatistics.getContentPane().add(lblNwin, gbc_lblNwin);

        JLabel lblPlayerError = new JLabel("Player con pi\u00F9 errori");
        GridBagConstraints gbc_lblPlayerError = new GridBagConstraints();
        gbc_lblPlayerError.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlayerError.gridx = 1;
        gbc_lblPlayerError.gridy = 6;
        frmStatistics.getContentPane().add(lblPlayerError, gbc_lblPlayerError);

        JLabel lblPErr = new JLabel("New label");
        GridBagConstraints gbc_lblPErr = new GridBagConstraints();
        gbc_lblPErr.anchor = GridBagConstraints.BASELINE;
        gbc_lblPErr.insets = new Insets(0, 0, 5, 5);
        gbc_lblPErr.gridx = 2;
        gbc_lblPErr.gridy = 6;
        frmStatistics.getContentPane().add(lblPErr, gbc_lblPErr);

        JLabel lblMancheWin = new JLabel("Numero manche vinte");
        GridBagConstraints gbc_lblMancheWin = new GridBagConstraints();
        gbc_lblMancheWin.insets = new Insets(0, 0, 5, 5);
        gbc_lblMancheWin.gridx = 4;
        gbc_lblMancheWin.gridy = 6;
        frmStatistics.getContentPane().add(lblMancheWin, gbc_lblMancheWin);

        lblNmanwin = new JLabel("");
        GridBagConstraints gbc_lblNmanwin = new GridBagConstraints();
        gbc_lblNmanwin.insets = new Insets(0, 0, 5, 5);
        gbc_lblNmanwin.gridx = 5;
        gbc_lblNmanwin.gridy = 6;
        frmStatistics.getContentPane().add(lblNmanwin, gbc_lblNmanwin);

        JLabel lblPlayerLose = new JLabel(" Player con pi\u00F9 \"perde\"");
        GridBagConstraints gbc_lblPlayerLose = new GridBagConstraints();
        gbc_lblPlayerLose.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlayerLose.gridx = 1;
        gbc_lblPlayerLose.gridy = 7;
        frmStatistics.getContentPane().add(lblPlayerLose, gbc_lblPlayerLose);

        lblPLose = new JLabel("");
        GridBagConstraints gbc_lblPLose = new GridBagConstraints();
        gbc_lblPLose.insets = new Insets(0, 0, 5, 5);
        gbc_lblPLose.gridx = 2;
        gbc_lblPLose.gridy = 7;
        frmStatistics.getContentPane().add(lblPLose, gbc_lblPLose);

        JLabel lblMatObs = new JLabel("Partite osservate");
        GridBagConstraints gbc_lblMatObs = new GridBagConstraints();
        gbc_lblMatObs.insets = new Insets(0, 0, 5, 5);
        gbc_lblMatObs.gridx = 4;
        gbc_lblMatObs.gridy = 7;
        frmStatistics.getContentPane().add(lblMatObs, gbc_lblMatObs);

        lblMatchobs = new JLabel("");
        GridBagConstraints gbc_lblMatchobs = new GridBagConstraints();
        gbc_lblMatchobs.insets = new Insets(0, 0, 5, 5);
        gbc_lblMatchobs.gridx = 5;
        gbc_lblMatchobs.gridy = 7;
        frmStatistics.getContentPane().add(lblMatchobs, gbc_lblMatchobs);

        JLabel lblMancObs = new JLabel("Manche osservate");
        GridBagConstraints gbc_lblMancObs = new GridBagConstraints();
        gbc_lblMancObs.insets = new Insets(0, 0, 5, 5);
        gbc_lblMancObs.gridx = 4;
        gbc_lblMancObs.gridy = 8;
        frmStatistics.getContentPane().add(lblMancObs, gbc_lblMancObs);

        lblManobs = new JLabel("");
        GridBagConstraints gbc_lblManobs = new GridBagConstraints();
        gbc_lblManobs.insets = new Insets(0, 0, 5, 5);
        gbc_lblManobs.gridx = 5;
        gbc_lblManobs.gridy = 8;
        frmStatistics.getContentPane().add(lblManobs, gbc_lblManobs);

        JLabel lblConsonant = new JLabel("  Consonante che ha generato pi\u00F9 punti");
        GridBagConstraints gbc_lblConsonant = new GridBagConstraints();
        gbc_lblConsonant.insets = new Insets(0, 0, 5, 5);
        gbc_lblConsonant.gridx = 1;
        gbc_lblConsonant.gridy = 9;
        frmStatistics.getContentPane().add(lblConsonant, gbc_lblConsonant);

        JLabel lblConsMaxP = new JLabel("New label");
        GridBagConstraints gbc_lblConsMaxP = new GridBagConstraints();
        gbc_lblConsMaxP.insets = new Insets(0, 0, 5, 5);
        gbc_lblConsMaxP.gridx = 2;
        gbc_lblConsMaxP.gridy = 9;
        frmStatistics.getContentPane().add(lblConsMaxP, gbc_lblConsMaxP);

        JLabel lblAvgScore = new JLabel("Punteggio medio vinto ");
        GridBagConstraints gbc_lblAvgScore = new GridBagConstraints();
        gbc_lblAvgScore.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvgScore.gridx = 4;
        gbc_lblAvgScore.gridy = 9;
        frmStatistics.getContentPane().add(lblAvgScore, gbc_lblAvgScore);

        lblAvgpunwin = new JLabel("");
        GridBagConstraints gbc_lblAvgpunwin = new GridBagConstraints();
        gbc_lblAvgpunwin.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvgpunwin.gridx = 5;
        gbc_lblAvgpunwin.gridy = 9;
        frmStatistics.getContentPane().add(lblAvgpunwin, gbc_lblAvgpunwin);

        JLabel lblInSentence = new JLabel("nella frase");
        GridBagConstraints gbc_lblInSentence = new GridBagConstraints();
        gbc_lblInSentence.insets = new Insets(0, 0, 5, 5);
        gbc_lblInSentence.gridx = 1;
        gbc_lblInSentence.gridy = 10;
        frmStatistics.getContentPane().add(lblInSentence, gbc_lblInSentence);

        JLabel lblSentence = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        GridBagConstraints gbc_lblSentence = new GridBagConstraints();
        gbc_lblSentence.insets = new Insets(0, 0, 5, 5);
        gbc_lblSentence.gridx = 2;
        gbc_lblSentence.gridy = 10;
        frmStatistics.getContentPane().add(lblSentence, gbc_lblSentence);

        JLabel lblAVGNext = new JLabel("Media passa ");
        GridBagConstraints gbc_lblAVGNext = new GridBagConstraints();
        gbc_lblAVGNext.insets = new Insets(0, 0, 5, 5);
        gbc_lblAVGNext.gridx = 4;
        gbc_lblAVGNext.gridy = 10;
        frmStatistics.getContentPane().add(lblAVGNext, gbc_lblAVGNext);

        lblAvgnext = new JLabel("");
        GridBagConstraints gbc_lblAvgnext = new GridBagConstraints();
        gbc_lblAvgnext.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvgnext.gridx = 5;
        gbc_lblAvgnext.gridy = 10;
        frmStatistics.getContentPane().add(lblAvgnext, gbc_lblAvgnext);

        JLabel lblBy = new JLabel("fatta da:");
        GridBagConstraints gbc_lblBy = new GridBagConstraints();
        gbc_lblBy.insets = new Insets(0, 0, 5, 5);
        gbc_lblBy.gridx = 1;
        gbc_lblBy.gridy = 11;
        frmStatistics.getContentPane().add(lblBy, gbc_lblBy);

        JLabel lblPlayer = new JLabel("New label");
        GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
        gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlayer.gridx = 2;
        gbc_lblPlayer.gridy = 11;
        frmStatistics.getContentPane().add(lblPlayer, gbc_lblPlayer);

        JLabel lblAVGLose = new JLabel("Media perdi");
        GridBagConstraints gbc_lblAVGLose = new GridBagConstraints();
        gbc_lblAVGLose.insets = new Insets(0, 0, 5, 5);
        gbc_lblAVGLose.gridx = 4;
        gbc_lblAVGLose.gridy = 11;
        frmStatistics.getContentPane().add(lblAVGLose, gbc_lblAVGLose);

        lblAvglose = new JLabel("");
        GridBagConstraints gbc_lblAvglose = new GridBagConstraints();
        gbc_lblAvglose.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvglose.gridx = 5;
        gbc_lblAvglose.gridy = 11;
        frmStatistics.getContentPane().add(lblAvglose, gbc_lblAvglose);

        JLabel lblAVGMoves = new JLabel("Media mosse per vincere");
        GridBagConstraints gbc_lblAVGMoves = new GridBagConstraints();
        gbc_lblAVGMoves.anchor = GridBagConstraints.SOUTH;
        gbc_lblAVGMoves.insets = new Insets(0, 0, 5, 5);
        gbc_lblAVGMoves.gridx = 1;
        gbc_lblAVGMoves.gridy = 13;
        frmStatistics.getContentPane().add(lblAVGMoves, gbc_lblAVGMoves);

         lblAvgMoves = new JLabel("");
        GridBagConstraints gbc_lblAvgMoves = new GridBagConstraints();
        gbc_lblAvgMoves.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvgMoves.gridx = 2;
        gbc_lblAvgMoves.gridy = 13;
        frmStatistics.getContentPane().add(lblAvgMoves, gbc_lblAvgMoves);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //bottone per stats utente

            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewButton.gridwidth = 2;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 16;
        frmStatistics.getContentPane().add(btnNewButton, gbc_btnNewButton);

    }

}