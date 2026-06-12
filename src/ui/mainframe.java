package ui;

import javax.swing.*;
import java.awt.*;

public class mainframe extends JFrame {

    JLabel appname = new JLabel("Code Snippet Manager");

    JButton newsnippet = new JButton("New Snippet");
    JButton allsnip = new JButton("All Snippets");
    JButton recentsnip = new JButton("Recent");
    JButton favoritesnip = new JButton("Favorites");

    JTextField searchBr = new JTextField("Search snippets...");
    JButton filterbut = new JButton("Filter");

    JPanel snippetlist = new JPanel();
    JScrollPane listscroll = new JScrollPane(snippetlist);
    detailpanel detailpanel= new detailpanel();
    codepanel codepanel= new codepanel();

    JPanel sidebar;
    JPanel centerPanel;
    JPanel rightPanel;

    public mainframe() {

        sidebar = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();

        setTitle("Code Snippet Manager");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //---------------- SIDEBAR ----------------

        sidebar.setBackground(new Color(20, 25, 40));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        appname.setForeground(Color.WHITE);
        appname.setFont(new Font("Segoe UI", Font.BOLD, 22));

        sidebar.add(appname);
        sidebar.add(Box.createVerticalStrut(15));

        stylePrimaryButton(newsnippet);

        sidebar.add(newsnippet);

        sidebar.add(Box.createVerticalStrut(30));

        styleButton(allsnip);
        styleButton(recentsnip);
        styleButton(favoritesnip);

        sidebar.add(allsnip);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(recentsnip);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(favoritesnip);

        //---------------- CENTER PANEL ----------------

        centerPanel.setBackground(new Color(15, 18, 30));
        centerPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(15, 18, 30));

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        searchBr.setPreferredSize(new Dimension(300, 40));
        searchBr.setBackground(new Color(24, 28, 45));
        searchBr.setForeground(Color.WHITE);
        searchBr.setCaretColor(Color.WHITE);

        styleButton(filterbut);

        topPanel.add(searchBr, BorderLayout.CENTER);
        topPanel.add(filterbut, BorderLayout.EAST);

        centerPanel.add(topPanel, BorderLayout.NORTH);

        snippetlist.setBackground(new Color(15, 18, 30));

        snippetlist.setLayout(
                new BoxLayout(snippetlist, BoxLayout.Y_AXIS)
        );

        snippetlist.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        listscroll.setBorder(null);

        centerPanel.add(listscroll, BorderLayout.CENTER);

        //---------------- SAMPLE CARDS ----------------

        snippetlist.add(
                new snippetcard(
                        "Singleton Class",
                        "Thread safe singleton implementation",
                        "Java"
                )
        );

        snippetlist.add(Box.createVerticalStrut(10));

        snippetlist.add(
                new snippetcard(
                        "Quick Sort",
                        "Quick sort implementation",
                        "Java"
                )
        );

        //this is the right panal code

        rightPanel.setBackground(new Color(18, 22, 38));
        rightPanel.setPreferredSize(new Dimension(400, 0));


        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(detailpanel, BorderLayout.NORTH);

        rightPanel.add(codepanel, BorderLayout.CENTER);


        //---------------- FRAME ----------------

        add(sidebar, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void stylePrimaryButton(JButton button) {

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBackground(
                new Color(92, 112, 231)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    private void styleButton(JButton button) {

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBackground(
                new Color(24, 28, 45)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}