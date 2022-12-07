package myplugin1;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

import javax.annotation.CheckForNull;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PreviousPageAction  extends MDAction {

    public PreviousPageAction( String id,  String name) {
        super(id, name,  KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK), null);
    };

    @Override
    public void actionPerformed(@CheckForNull ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        Application.getInstance().getGUILog().showMessage("Feature coming soon...");
        System.out.println("Previous Page. To be implemented.");
    }
}
