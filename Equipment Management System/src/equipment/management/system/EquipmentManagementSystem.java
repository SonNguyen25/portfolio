/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipment.management.system;



import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Bao Son
 */
public class EquipmentManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Login a = new Login();
       JavaConnectionDataBase jcD = new JavaConnectionDataBase();
       jcD.connectToDatabase();
        if (jcD.connectToDatabase() == null) {
            JOptionPane.showMessageDialog(null, "Cannot connect to database");
        }
        else {
            a.setVisible(true);
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                UIManager.setLookAndFeel("com.jtattoo.plaf.acrypl.AcryplLookAndFeel");
            }
        } 
        catch (Exception e) {
        }
        
        
        
        
        }
        }
        


  
