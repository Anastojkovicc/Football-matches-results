/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANA
 */
public class OsvezavanjeNit extends Thread{
    ServerskaForma sf;

    public OsvezavanjeNit(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
      while(true){
      sf.srediTabelu();
          System.out.println("Osvezio");
          try {
              sleep(10000);
          } catch (InterruptedException ex) {
              Logger.getLogger(OsvezavanjeNit.class.getName()).log(Level.SEVERE, null, ex);
          }
      
      }
    }
    
    
}
