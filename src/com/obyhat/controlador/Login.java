package com.obyhat.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.obyhat.vista.login.PanelLogin;

public class Login implements ActionListener {

  private PanelLogin PL;

  public Login(PanelLogin PL) {
    
    this.PL = PL;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == this.PL.getBtnIniciar()) {}
  }
	
}
