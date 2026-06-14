package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

public class codepanel extends JPanel {

    RSyntaxTextArea codeArea;
    RTextScrollPane scrollPane;
    JButton editButton;

    public void setCode(String code) {
        codeArea.setText(code);
    }

    public String getCode() {
        return codeArea.getText();
    }

    // set the  language for the syntax highlighting
    public void setLanguage(String language) {
        if (language == null) return;

        switch (language.toLowerCase()) {
            case "java":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                break;
            case "python":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
                break;
            case "javascript":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
                break;
            case "c++":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                break;
            case "c#":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
                break;
            case "html":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
                break;
            case "css":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
                break;
            case "sql":
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
                break;
            default:
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        }
    }

    public void setEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    // color for the code section
    private void applyDarkTheme() {
        // Set background and basic colors
        // dark background
        codeArea.setBackground(new Color(15, 18, 30));
        // light gray text
        codeArea.setForeground(new Color(96, 185, 226));
        // White cursor
        codeArea.setCaretColor(new Color(108, 255, 27));

        codeArea.setCurrentLineHighlightColor(new Color(0, 0, 0));  // highlight current line
        codeArea.setSelectionColor(new Color(92, 112, 231, 80));       // selection color
        codeArea.setUseSelectedTextColor(false);
    }

    public codepanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 28, 45));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);
        codeArea.setLineWrap(false);
        applyDarkTheme();
        codeArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        codeArea.setEditable(false);
        codeArea.setText("code display section for review and copying ");
        scrollPane = new RTextScrollPane(codeArea);
        scrollPane.setLineNumbersEnabled(true);
        scrollPane.setFoldIndicatorEnabled(true);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(15, 18, 30));
        editButton = new JButton("Edit Snippet");
        editButton.setFocusPainted(false);
        editButton.setBackground(new Color(92, 112, 231));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(24, 28, 45));
        buttonPanel.add(editButton);

        // add the components
        JPanel codePanelWithButton = new JPanel(new BorderLayout());
        codePanelWithButton.setBackground(new Color(24, 28, 45));
        codePanelWithButton.add(scrollPane, BorderLayout.CENTER);
        codePanelWithButton.add(buttonPanel, BorderLayout.SOUTH);

        add(codePanelWithButton, BorderLayout.CENTER);
    }
}