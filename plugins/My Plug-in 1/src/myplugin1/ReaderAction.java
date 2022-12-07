package myplugin1;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

import javax.annotation.CheckForNull;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReaderAction extends MDAction {

    FileLoader fileLoader;

    public ReaderAction(String id, String name) {
        super(id, name, null, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose TXT File ");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            this.fileLoader = new FileLoader(fc.getSelectedFile());

        }else{
            JOptionPane.showMessageDialog(null,
                    "Directory choosing command cancelled by user.");
        }


    }

    public FileLoader getFileLoader() {
        return fileLoader;
    }

    public void setFileLoader(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }
}
