package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Snippet;

public class snippetcard extends JPanel {
  private Snippet snippet;
  private JButton deleteButton;

  public snippetcard(Snippet snippet) {
    this.snippet = snippet;

    setBackground(new Color(24, 28, 45));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
    setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(40, 45, 65)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
    ));
    setLayout(new BorderLayout());

    //  a text section
    JPanel textPanel = new JPanel();
    textPanel.setOpaque(false);
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel(snippet.getTitle());
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

    JLabel langLabel = new JLabel(snippet.getLanguage());
    langLabel.setForeground(new Color(120, 140, 255));
    langLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

    JLabel descLabel = new JLabel(snippet.getDescription());
    descLabel.setForeground(Color.LIGHT_GRAY);
    descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

    textPanel.add(titleLabel);
    textPanel.add(Box.createVerticalStrut(5));
    textPanel.add(langLabel);
    textPanel.add(Box.createVerticalStrut(5));
    textPanel.add(descLabel);

    add(textPanel, BorderLayout.CENTER);

    // the right panel with star and delete button
    JPanel rightPanel = new JPanel();
    rightPanel.setOpaque(false);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    // delete button
    deleteButton = new JButton("x");
    deleteButton.setFocusPainted(false);
    deleteButton.setBorderPainted(false);
    deleteButton.setBackground(new Color(24, 28, 45));
    deleteButton.setForeground(new Color(255, 80, 80));
    deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
    deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    deleteButton.setToolTipText("Delete snippet");
    deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);


    rightPanel.add(Box.createVerticalStrut(10));
    rightPanel.add(deleteButton);

    add(rightPanel, BorderLayout.EAST);
  }

  public Snippet getSnippet() {
    return snippet;
  }

  public void setDeleteButtonListener(ActionListener listener) {
    deleteButton.addActionListener(listener);
  }
}