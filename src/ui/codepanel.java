package ui;

import javax.swing.*;
import java.awt.*;

public class codepanel extends JPanel {

    JTextArea codeArea;
    JScrollPane scrollPane;
    public void setCode(String code){
        codeArea.setText(code);
    }

    public codepanel() {

        setLayout(new BorderLayout());

        setBackground(new Color(24,28,45));

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        codeArea = new JTextArea();

        codeArea.setBackground(
                new Color(15,18,30)
        );

        codeArea.setForeground(Color.WHITE);

        codeArea.setCaretColor(Color.WHITE);

        codeArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        codeArea.setText(
                "public class Singleton {\n\n" +
                        "    private static volatile Singleton instance;\n\n" +
                        "    private Singleton() {\n" +
                        "    }\n\n" +
                        "    public static Singleton getInstance() {\n" +
                        "        if(instance == null) {\n" +
                        "            synchronized (Singleton.class) {\n" +
                        "                if(instance == null)\n" +
                        "                    instance = new Singleton();\n" +
                        "            }\n" +
                        "        }\n\n" +
                        "        return instance;\n" +
                        "    }\n" +
                        "}"
        );

        scrollPane = new JScrollPane(codeArea);

        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }
}