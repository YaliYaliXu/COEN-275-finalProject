package menuPanels;

import javax.swing.*;

/**
 * Created by manhongren on 6/3/17.
 */
public class ActiveTextField {
    private static ActiveTextField activeTextField;
    private JTextField currentTextField;
    private ActiveTextField(){}
    public static ActiveTextField getActiveTextField(){
        if (activeTextField == null){
            activeTextField = new ActiveTextField();
        }
        return activeTextField;
    }

    public void setCurrentTextField(JTextField currentTextField){
         this.currentTextField = currentTextField;
    }
    public JTextField getCurrentTextField(){
        return this.currentTextField;
    }
}
