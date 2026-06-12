package ui;

import javax.swing.*;
import java.awt.*;
import model.Snippet;

public class detailpanel extends JPanel {

    JLabel titleLabel;
    JLabel languageLabel;
    JLabel descriptionLabel;
    JCheckBox favorite;

    JPanel tagsPanel;

    JLabel createdLabel;
    JLabel updatedLabel;
    JLabel collectionLabel;
    JLabel languageInfoLabel;

    codepanel codePanel;
    public void displaySnippet(Snippet snippet) {
       titleLabel.setText(snippet.getTitle());
    }

    public detailpanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(18,22,38));

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // Title

        titleLabel = new JLabel("Singleton Class");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        favorite = new JCheckBox("Favorite");

        favorite.setBackground(
                new Color(18,22,38)
        );

        favorite.setForeground(Color.WHITE);

        favorite.setFocusable(false);

        favorite.setSelected(true);

        JPanel titlePanel = new JPanel(
                new BorderLayout()
        );

        titlePanel.setBackground(
                new Color(18,22,38)
        );

        titlePanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        titlePanel.add(
                favorite,
                BorderLayout.EAST
        );

        add(titlePanel);


        languageLabel = new JLabel("Java");

        languageLabel.setForeground(
                new Color(120,140,255)
        );

        languageLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        add(languageLabel);

        add(Box.createVerticalStrut(15));

        descriptionLabel = new JLabel(
                "Thread-safe singleton implementation using double-checked locking."
        );

        descriptionLabel.setForeground(Color.LIGHT_GRAY);

        add(descriptionLabel);

        add(Box.createVerticalStrut(20));

        tagsPanel = new JPanel();

        tagsPanel.setBackground(
                new Color(18,22,38)
        );

        tagsPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT
                )
        );

        tagsPanel.add(
                new JLabel("design-pattern")
        );

        tagsPanel.add(
                new JLabel("singleton")
        );

        tagsPanel.add(
                new JLabel("java")
        );

        add(tagsPanel);

        add(Box.createVerticalStrut(20));


        codePanel = new codepanel();

        codePanel.setPreferredSize(
                new Dimension(
                        500,
                        450

                )
        );

        add(codePanel);

        add(Box.createVerticalStrut(20));

        // Information section

        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(
                new GridLayout(
                        2,
                        2,
                        20,
                        20
                )
        );

        infoPanel.setBackground(
                new Color(18,22,38)
        );

        createdLabel =
                new JLabel("Created : May 12, 2024");

        updatedLabel =
                new JLabel("Updated : May 20, 2024");

        languageInfoLabel =
                new JLabel("Language : Java");

        collectionLabel =
                new JLabel("Collection : Design Patterns");

        createdLabel.setForeground(Color.WHITE);
        updatedLabel.setForeground(Color.WHITE);
        languageInfoLabel.setForeground(Color.WHITE);
        collectionLabel.setForeground(Color.WHITE);

        infoPanel.add(createdLabel);
        infoPanel.add(updatedLabel);
        infoPanel.add(languageInfoLabel);
        infoPanel.add(collectionLabel);

        add(Box.createVerticalStrut(20));

        add(infoPanel);
    }
}