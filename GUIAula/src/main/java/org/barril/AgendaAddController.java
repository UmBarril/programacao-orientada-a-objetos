package org.barril;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AgendaAddController implements ActionListener {
    private Agenda agenda;
    private JFrame janelaPrincipal;

    public AgendaAddController(Agenda agenda, JFrame janela) {
        this.agenda = agenda;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
