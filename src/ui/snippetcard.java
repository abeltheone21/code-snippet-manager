package ui;

import javax.swing.*;
import java.awt.*;

public class snippetcard extends JPanel {

  public snippetcard(String title, String description, String language) {

    setBackground(new Color(24, 28, 45));

    setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 110)
    );

    setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(40, 45, 65)
                    ),
                    BorderFactory.createEmptyBorder(
                            15,
                            15,
                            15,
                            15
                    )
            )
    );

    setLayout(new BorderLayout());





    // text section
    JPanel textPanel = new JPanel();

    textPanel.setOpaque(false);

    textPanel.setLayout(
            new BoxLayout(
                    textPanel,
                    BoxLayout.Y_AXIS
            )
    );

    JLabel titleLabel = new JLabel(title);

    titleLabel.setForeground(Color.WHITE);

    titleLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    16
            )
    );

    JLabel langLabel = new JLabel(language);

    langLabel.setForeground(
            new Color(120, 140, 255)
    );

    langLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    13
            )
    );

    JLabel descLabel = new JLabel(description);

    descLabel.setForeground(Color.LIGHT_GRAY);

    descLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    12
            )
    );

    textPanel.add(titleLabel);
    textPanel.add(Box.createVerticalStrut(5));
    textPanel.add(langLabel);
    textPanel.add(Box.createVerticalStrut(5));
    textPanel.add(descLabel);

    add(textPanel, BorderLayout.CENTER);

    // star section
    JLabel starLabel = new JLabel("#");

    starLabel.setForeground(
            new Color(49, 248, 22)
    );

    starLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    18
            )
    );

    add(starLabel, BorderLayout.EAST);
  }
}