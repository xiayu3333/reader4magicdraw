package myplugin1;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

import javax.annotation.CheckForNull;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;

public class NextPageAction extends MDAction {

    ReaderAction readerAction;


    public NextPageAction( String id,  String name) {
        super(id, name,  KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK), null);
    };

    @Override
    public void actionPerformed(@CheckForNull ActionEvent actionEvent) {
        try {
            super.actionPerformed(actionEvent);
            this.readNext();
        }catch (Exception e){
            Application.getInstance().getGUILog().showError("No file loaded.");
        }
    }

    private void readNext() {
        if (this.readerAction.getFileLoader().getReader() == null){
            Application.getInstance().getGUILog().showError("No file loaded.");
        }else {

            try {
                BufferedReader reader = this.readerAction.getFileLoader().getReader();
                String line = new String();
                int count = 0;
                while (line != null && count < this.readerAction.getFileLoader().getNumLinesOneTime()) {
                    line = reader.readLine();
                    Application.getInstance().getGUILog().log(line);
                    count++;
                    linePresentPlusPlus();
                }
                if (this.readerAction.getFileLoader().getLinePresent() >=
                        this.readerAction.getFileLoader().getLineTotal()) {
                    reader.close();
                }
            }catch (Exception e){

                e.printStackTrace();
            }

        }
    }

    public void linePresentPlusPlus(){
        int linePresent = this.readerAction.getFileLoader().getLinePresent();
        linePresent++;
        this.readerAction.getFileLoader().setLinePresent(linePresent);
    }

    public ReaderAction getReaderAction() {
        return readerAction;
    }

    public void setReaderAction(ReaderAction readerAction) {
        this.readerAction = readerAction;
    }



}
