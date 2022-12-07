package myplugin1;

import com.nomagic.magicdraw.core.Application;
import org.mozilla.universalchardet.UniversalDetector;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;



public class FileLoader  {

    File file;
    BufferedReader reader;
    Charset inputCharset;
    int numLinesOneTime = 5;
    int linePresent = 1;
    long lineTotal;
    long lineStart;


    public FileLoader(File file) {
        this.file = file;
        try {
            lineTotal = Files.lines(this.file.toPath()).count();
            String charset = detectCharset();
            this.inputCharset = Charset.forName(charset);
        }catch (Exception e1){
            e1.printStackTrace();
            lineTotal = 0x7fff_ffff_ffff_ffffL;   // Max value for long type
        }

        boolean ans = Application.getInstance().getGUILog().
                showCustomizedQuestion("How many lines do you want to read a time?", "5 lines", "10 lines", "Q", "Q");
        if (ans){
            this.setNumLinesOneTime(5);
        }else {
            this.setNumLinesOneTime(10);
        }



        try {

            this.lineStart = getNumFromUser(this.lineTotal);


            JOptionPane.showMessageDialog(null,
                    "File loaded.\nGo to your notification window and enjoy!\nCtrl + Plus to read next lines." );
            //reader = new BufferedReader(new FileReader(file));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file),this.inputCharset));
            ignoreLines(lineStart, reader);

            String line = new String();
            int count = 0;
            while (line != null && count < numLinesOneTime) {
                line = reader.readLine();
                Application.getInstance().getGUILog().log(line);
                count ++;
                linePresent ++;
            }

            if (linePresent >= lineTotal) {
                reader.close();

            }
        } catch (IOException e) {
            Application.getInstance().getGUILog().showError("File content loading error!");
        }
    }


    public long getNumFromUser(long numMax){
        boolean check;
        long num = 0;

        do
        {
            check = true;
            try
            {
                String input = JOptionPane.showInputDialog("Which line do you want to start with? (1 - " + numMax + ")." +
                        "\n 'Cancel' to start from the beginning.");
                System.out.println(input);

                if(input == null){
                   break;

                }

                num = Long.parseLong(input);
                if (num > numMax || num < 1){
                    throw new NumberFormatException();
                }


            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Please enter a number between 1 and " + numMax + " !");
                check = false;
            }
        }while(!check);

        return num;
    }

    public void ignoreLines(long num, BufferedReader reader) throws IOException {
        int count = 1;
        String line = new String();
        while (line != null && count < num) {
            line = reader.readLine();
            count ++;
            linePresent ++;
        }
    }

    public String detectCharset() throws IOException {
        byte[] buf = new byte[4096];
        java.io.InputStream fis = java.nio.file.Files.newInputStream(java.nio.file.Paths.get(this.file.toURI()));

        // (1)
        UniversalDetector detector = new UniversalDetector();

        // (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
        }

        // (5)
        detector.reset();
        return encoding;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public int getNumLinesOneTime() {
        return numLinesOneTime;
    }

    public void setNumLinesOneTime(int numLinesOneTime) {
        this.numLinesOneTime = numLinesOneTime;
    }

    public int getLinePresent() {
        return linePresent;
    }

    public void setLinePresent(int linePresent) {
        this.linePresent = linePresent;
    }

    public long getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(long lineTotal) {
        this.lineTotal = lineTotal;
    }

}

