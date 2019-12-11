package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics {

    private JButton INDIETROButton;
    private JLabel GPMAX;
    private JLabel JLABELPLAYERPUNTEGGIOPIUALTO;
    private JLabel JLABELPLAYERPIUMANCHE;
    private JLabel JLABELAVGPUNTIPIUALTI;
    private JLabel JLABELPLAYERPIUERRORI;
    private JLabel JLABELPLAYERPIUPERDE;
    private JLabel JLABELCONSONANTEPIUPUNTI;
    private JLabel JLABELAVGPUNTEGGIOVITTORIE;
    private JLabel JLABELAVGPASSA;
    private JLabel JLABELFATTODA;
    private JLabel AVGMOSSEPERVINCERE;
    private JLabel AVGLOSE;
    private JLabel JLABELNELLAFRASE;
    private JLabel JLABELMANCHEOSSERVATE;
    private JLabel JLABELMATCHOSSERAVATI;
    private JLabel JLABELNUMMANCHEVINTE;
    private JLabel JLABELNUMMATCHVINTI;
    private JLabel JLABELNUMPARTITE;
    private JLabel JLABELNUMMANCHE;

    public Statistics(ProxyRdF prox) {
        Initialize();
        prox.Stats(this,"");
    }

    public void setlblPMax(String txt)
    {
        JLABELPLAYERPUNTEGGIOPIUALTO.setText(txt);
    }

    public void setLblUsernman(String txt)
    {
        JLABELNUMMANCHE.setText(txt);
    }

    public void setLblPManche(String txt)
    {
        JLABELPLAYERPIUMANCHE.setText(txt);
    }

    public void setLblUsernpart(String txt)
    {
        JLABELNUMPARTITE.setText(txt);
    }

    public void setLblMaxAvg(String txt)
    {
        JLABELAVGPUNTIPIUALTI.setText(txt);
    }

    public void setLblNwin(String txt)
    {
        JLABELNUMMATCHVINTI.setText(txt);
    }

    public void setLblNmanwin(String txt)
    {
        JLABELNUMMANCHEVINTE.setText(txt);
    }

    public void setLblPLose(String txt)
    {
        JLABELPLAYERPIUPERDE.setText(txt);
    }

    public void setLblMatchobs(String txt)
    {
        JLABELMATCHOSSERAVATI.setText(txt);
    }

    public void setLblManobs(String txt)
    {
        JLABELMANCHEOSSERVATE.setText(txt);
    }

    public void setLblAvgpunwin(String txt)
    {
        JLABELAVGPUNTEGGIOVITTORIE.setText(txt);
    }

    public void setLblAvgnext(String txt)
    {
        JLABELAVGPASSA.setText(txt);
    }

    public void setLblAvglose(String txt)
    {
        AVGLOSE.setText(txt);
    }

    public void setLblAvgMoves(String txt)
    {
        AVGMOSSEPERVINCERE.setText(txt);
    }


    private void Initialize() {
        JLabel GPMAX = new JLabel();
        JLabel JLABELPLAYERPUNTEGGIOPIUALTO = new JLabel();
        JLabel JLABELPLAYERPIUMANCHE = new JLabel();
        JLabel JLABELAVGPUNTIPIUALTI = new JLabel();
        JLabel JLABELPLAYERPIUERRORI = new JLabel();
        JLabel JLABELPLAYERPIUPERDE = new JLabel();
        JLabel JLABELCONSONANTEPIUPUNTI = new JLabel();
        JLabel JLABELAVGPUNTEGGIOVITTORIE = new JLabel();
        JLabel JLABELAVGPASSA = new JLabel();
        JLabel JLABELFATTODA = new JLabel();
        JLabel AVGMOSSEPERVINCERE = new JLabel();
        JLabel AVGLOSE = new JLabel();
        JLabel JLABELNELLAFRASE = new JLabel();
        JLabel JLABELMANCHEOSSERVATE = new JLabel();
        JLabel JLABELMATCHOSSERAVATI = new JLabel();
        JLabel JLABELNUMMANCHEVINTE = new JLabel();
        JLabel JLABELNUMMATCHVINTI = new JLabel();
        JLabel JLABELNUMPARTITE = new JLabel();
        JLabel JLABELNUMMANCHE = new JLabel();



        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
