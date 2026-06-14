package ui;

import databaseOperations.dataQR;

import javax.swing.*;
import java.awt.*;
import model.Snippet;
import java.util.List;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

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
    dataQR data = new dataQR();
    detailpanel detailpanel = new detailpanel(data);
    codepanel codepanel = new codepanel();

    JPanel sidebar;
    JPanel centerPanel;
    JPanel rightPanel;

    public mainframe() {

        sidebar = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();

        setTitle("Code Snippet Manager");
        // Replace your icon code with this:
        try {
            java.io.File iconFile = new java.io.File("icon.png");
            if (iconFile.exists()) {
                setIconImage(new javax.swing.ImageIcon(iconFile.getAbsolutePath()).getImage());
                System.out.println("Icon loaded from project root!");
            } else {
                System.out.println("Icon not found at: " + iconFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            java.net.URL iconURL = getClass().getResource("/app-icon.png");
            if (iconURL != null) {
                setIconImage(new javax.swing.ImageIcon(iconURL).getImage());
                System.out.println("Icon loaded!");
            } else {
                System.out.println("Icon not found - check src/resources/app-icon.png");
            }
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }

        setSize(1200, 700);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //this is the sidebar section

        sidebar.setBackground(new Color(20, 25, 40));
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

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

        //button action listeners
        newsnippet.addActionListener(e -> openNewSnippetDialog());
        allsnip.addActionListener(e -> loadsnippets());
        recentsnip.addActionListener(e -> loadRecentSnippets());
        favoritesnip.addActionListener(e -> loadFavoriteSnippets());
        filterbut.addActionListener(e -> searchSnippets());
        searchBr.addActionListener(e -> searchSnippets());

        // the center panel code ||center||
        centerPanel.setBackground(new Color(15, 18, 30));
        centerPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(15, 18, 30));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        searchBr.setPreferredSize(new Dimension(300, 40));
        searchBr.setBackground(new Color(24, 28, 45));
        searchBr.setForeground(Color.WHITE);
        searchBr.setCaretColor(Color.WHITE);

        styleButton(filterbut);

        topPanel.add(searchBr, BorderLayout.CENTER);
        topPanel.add(filterbut, BorderLayout.EAST);
        centerPanel.add(topPanel, BorderLayout.NORTH);

        snippetlist.setBackground(new Color(15, 18, 30));
        snippetlist.setLayout(new BoxLayout(snippetlist, BoxLayout.Y_AXIS));
        snippetlist.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        listscroll.setBorder(null);
        centerPanel.add(listscroll, BorderLayout.CENTER);

        //the right panal code |||right panel|
        rightPanel.setBackground(new Color(18, 22, 38));
        rightPanel.setPreferredSize(new Dimension(400, 0));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(detailpanel, BorderLayout.NORTH);
        rightPanel.add(codepanel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // edit button event listener
        codepanel.setEditButtonListener(e -> {
            Snippet currentSnippet = detailpanel.getCurrentSnippet();
            if (currentSnippet != null) {
                openEditSnippetDialog(currentSnippet);
            } else {
                JOptionPane.showMessageDialog(this, "No snippet selected to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        loadsnippets();
        setVisible(true);
    }


    private void stylePrimaryButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(new Color(92, 112, 231));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(new Color(24, 28, 45));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void loadsnippets() {
        snippetlist.removeAll();
        List<Snippet> snippets = data.getallsnippets();

        if (snippets == null || snippets.isEmpty()) {
            JLabel emptyLabel = new JLabel("No snippets found. Click 'New Snippet' to add one!");
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            snippetlist.add(emptyLabel);
        } else {
            for (Snippet s : snippets) {
                snippetcard card = new snippetcard(s);

                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        updateRightPanel(s);
                    }
                });

                card.setDeleteButtonListener(e -> {
                    deleteSnippet(s);
                });

                snippetlist.add(card);
                snippetlist.add(Box.createVerticalStrut(10));
            }
        }

        snippetlist.revalidate();
        snippetlist.repaint();
    }

    public void updateRightPanel(Snippet snippet) {
        detailpanel.displaySnippet(snippet);
        codepanel.setCode(snippet.getCode());
        codepanel.setLanguage(snippet.getLanguage());
    }

    private void deleteSnippet(Snippet snippet) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete \"" + snippet.getTitle() + "\"?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            data.deleteSnippet(snippet.getId());
            loadsnippets();
            JOptionPane.showMessageDialog(this, "Snippet deleted successfully!");
        }
    }

    private void loadFavoriteSnippets() {
        snippetlist.removeAll();
        List<Snippet> allSnippets = data.getallsnippets();
        List<Snippet> favoriteSnippets = new java.util.ArrayList<>();

        for (Snippet s : allSnippets) {
            if (s.isFavorite()) {
                favoriteSnippets.add(s);
            }
        }

        if (favoriteSnippets.isEmpty()) {
            JLabel emptyLabel = new JLabel("No favorite snippets yet!");
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            snippetlist.add(emptyLabel);
        } else {
            for (Snippet s : favoriteSnippets) {
                snippetcard card = new snippetcard(s);
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        updateRightPanel(s);
                    }
                });
                card.setDeleteButtonListener(e -> {
                    deleteSnippet(s);
                });
                snippetlist.add(card);
                snippetlist.add(Box.createVerticalStrut(10));
            }
        }

        snippetlist.revalidate();
        snippetlist.repaint();
    }

    private void loadRecentSnippets() {
        snippetlist.removeAll();
        List<Snippet> recentSnippets = data.getRecentSnippets();

        if (recentSnippets == null || recentSnippets.isEmpty()) {
            JLabel emptyLabel = new JLabel("No snippets found!");
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            snippetlist.add(emptyLabel);
        } else {
            for (Snippet s : recentSnippets) {
                snippetcard card = new snippetcard(s);
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        updateRightPanel(s);
                    }
                });
                card.setDeleteButtonListener(e -> {
                    deleteSnippet(s);
                });
                snippetlist.add(card);
                snippetlist.add(Box.createVerticalStrut(10));
            }
        }

        snippetlist.revalidate();
        snippetlist.repaint();
    }

    private void searchSnippets() {
        String searchTerm = searchBr.getText().trim().toLowerCase();
        if (searchTerm.isEmpty() || searchTerm.equals("search snippets...")) {
            loadsnippets();
            return;
        }

        snippetlist.removeAll();
        List<Snippet> allSnippets = data.getallsnippets();
        List<Snippet> searchResults = new java.util.ArrayList<>();

        for (Snippet s : allSnippets) {
            if (s.getTitle().toLowerCase().contains(searchTerm) ||
                    s.getDescription().toLowerCase().contains(searchTerm) ||
                    s.getLanguage().toLowerCase().contains(searchTerm)) {
                searchResults.add(s);
            }
        }

        if (searchResults.isEmpty()) {
            JLabel emptyLabel = new JLabel("No snippets found matching: " + searchTerm);
            emptyLabel.setForeground(Color.LIGHT_GRAY);
            snippetlist.add(emptyLabel);
        } else {
            for (Snippet s : searchResults) {
                snippetcard card = new snippetcard(s);
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        updateRightPanel(s);
                    }
                });
                card.setDeleteButtonListener(e -> {
                    deleteSnippet(s);
                });
                snippetlist.add(card);
                snippetlist.add(Box.createVerticalStrut(10));
            }
        }

        snippetlist.revalidate();
        snippetlist.repaint();
    }

    private void openEditSnippetDialog(Snippet snippet) {
        // ... (keep your existing openEditSnippetDialog method)
        JDialog dialog = new JDialog(this, "Edit Snippet", true);
        dialog.setSize(750, 650);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        formPanel.setBackground(new Color(18, 22, 38));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 10);

        // the title field
        JTextField titleField = new JTextField(snippet.getTitle());
        styleTextField(titleField);
        titleField.setPreferredSize(new Dimension(0, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        JLabel titleLabel = createLabel("Title:");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        formPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(titleField, gbc);

        //programming language ComboBox
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        JLabel langLabel = createLabel("Language:");
        langLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        formPanel.add(langLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        JPanel languagePanel = new JPanel(new BorderLayout(5, 0));
        languagePanel.setOpaque(false);

        JComboBox<String> languageCombo = new JComboBox<>();
        languageCombo.setEditable(true);
        languageCombo.setBackground(new Color(24, 28, 45));
        languageCombo.setForeground(Color.WHITE);
        languageCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        languageCombo.setPreferredSize(new Dimension(0, 35));
        languageCombo.setSelectedItem(snippet.getLanguage());

        languageCombo.addItem("Java");
        languageCombo.addItem("Python");
        languageCombo.addItem("JavaScript");
        languageCombo.addItem("C++");
        languageCombo.addItem("C#");
        languageCombo.addItem("HTML/CSS");
        languageCombo.addItem("SQL");

        languagePanel.add(languageCombo, BorderLayout.CENTER);
        formPanel.add(languagePanel, gbc);

        // Description field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel descLabel = createLabel("Description:");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        formPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        JTextArea descArea = new JTextArea(snippet.getDescription(), 3, 20);
        styleTextArea(descArea);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setPreferredSize(new Dimension(0, 70));
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));
        formPanel.add(descScroll, gbc);

        // Code field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel codeLabel = createLabel("Code:");
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        formPanel.add(codeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        org.fife.ui.rsyntaxtextarea.RSyntaxTextArea editCodeArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        editCodeArea.setText(snippet.getCode());
        editCodeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_JAVA);
        editCodeArea.setCodeFoldingEnabled(true);
        editCodeArea.setAntiAliasingEnabled(true);
        editCodeArea.setBackground(new Color(15, 18, 30));
        editCodeArea.setForeground(Color.WHITE);
        editCodeArea.setCaretColor(Color.WHITE);
        editCodeArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        editCodeArea.setEditable(true);
        editCodeArea.setLineWrap(false);

        org.fife.ui.rtextarea.RTextScrollPane codeScroll = new org.fife.ui.rtextarea.RTextScrollPane(editCodeArea);
        codeScroll.setLineNumbersEnabled(true);
        codeScroll.setFoldIndicatorEnabled(true);
        codeScroll.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));

        formPanel.add(codeScroll, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        JButton saveBtn = new JButton("Save Changes");
        JButton cancelBtn = new JButton("Cancel");

        styleDialogButton(saveBtn);
        styleDialogButton(cancelBtn);

        saveBtn.addActionListener(e -> {
            String newTitle = titleField.getText().trim();
            String newLanguage = (String) languageCombo.getSelectedItem();
            String newDescription = descArea.getText().trim();
            String newCode = editCodeArea.getText();

            if (newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Title is required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (newLanguage == null || newLanguage.trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Language is required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                data.updateSnippet(snippet.getId(), newTitle, newLanguage.trim(), newCode, newDescription);
                snippet.setTitle(newTitle);
                snippet.setLanguage(newLanguage.trim());
                snippet.setDescription(newDescription);
                snippet.setCode(newCode);
                loadsnippets();
                updateRightPanel(snippet);
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Snippet updated successfully!");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(18, 22, 38));
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void openNewSnippetDialog() {
        JDialog dialog = new JDialog(this, "New Snippet", true);
        dialog.setSize(600, 500);  // Made larger for better code view
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        formPanel.setBackground(new Color(18, 22, 38));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 10);

        // Title field
        JTextField titleField = new JTextField();
        styleTextField(titleField);
        titleField.setPreferredSize(new Dimension(0, 28));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel titleLabel = createLabel("Title:");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(titleField, gbc);

        // Language ComboBox
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel langLabel = createLabel("Language:");
        langLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(langLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JPanel languagePanel = new JPanel(new BorderLayout(5, 0));
        languagePanel.setOpaque(false);

        JComboBox<String> languageCombo = new JComboBox<>();
        languageCombo.setEditable(true);
        languageCombo.setBackground(new Color(24, 28, 45));
        languageCombo.setForeground(Color.WHITE);
        languageCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        languageCombo.setPreferredSize(new Dimension(0, 28));

        languageCombo.addItem("Java");
        languageCombo.addItem("Python");
        languageCombo.addItem("JavaScript");
        languageCombo.addItem("C++");
        languageCombo.addItem("C#");
        languageCombo.addItem("HTML/CSS");
        languageCombo.addItem("SQL");

        languagePanel.add(languageCombo, BorderLayout.CENTER);
        formPanel.add(languagePanel, gbc);

        // Description field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel descLabel = createLabel("Description:");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextArea descArea = new JTextArea(3, 20);
        styleTextArea(descArea);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setPreferredSize(new Dimension(0, 70));
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));
        formPanel.add(descScroll, gbc);

        // ===== CODE FIELD WITH SYNTAX HIGHLIGHTING =====
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel codeLabel = createLabel("Code:");
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(codeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        // Create RSyntaxTextArea (same as codepanel)
        org.fife.ui.rsyntaxtextarea.RSyntaxTextArea codeArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);
        codeArea.setBackground(new Color(15, 18, 30));
        codeArea.setForeground(Color.WHITE);
        codeArea.setCaretColor(Color.WHITE);
        codeArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        codeArea.setLineWrap(false);
        codeArea.setText("// Write or paste your code here...");

        // Create scroll pane with line numbers
        org.fife.ui.rtextarea.RTextScrollPane codeScroll = new org.fife.ui.rtextarea.RTextScrollPane(codeArea);
        codeScroll.setLineNumbersEnabled(true);
        codeScroll.setFoldIndicatorEnabled(true);
        codeScroll.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));
        codeScroll.getViewport().setBackground(new Color(15, 18, 30));

        formPanel.add(codeScroll, gbc);

        // Reset fill for button panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        // =============================================

        // Add language change listener to update syntax highlighting
        languageCombo.addActionListener(e -> {
            String selectedLang = (String) languageCombo.getSelectedItem();
            if (selectedLang != null) {
                String lang = selectedLang.toLowerCase();
                switch (lang) {
                    case "java":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_JAVA);
                        break;
                    case "python":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_PYTHON);
                        break;
                    case "javascript":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
                        break;
                    case "c++":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                        break;
                    case "c#":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_CSHARP);
                        break;
                    case "html":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_HTML);
                        break;
                    case "css":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_CSS);
                        break;
                    case "sql":
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_SQL);
                        break;
                    default:
                        codeArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_NONE);
                }
            }
        });

        // Buttons
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        styleDialogButton(saveBtn);
        styleDialogButton(cancelBtn);

        saveBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String language = (String) languageCombo.getSelectedItem();
            String description = descArea.getText().trim();
            String code = codeArea.getText();

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Title is required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (language == null || language.trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Language is required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                data.addSnippet(title, language.trim(), code, description);
                dialog.dispose();
                loadsnippets();
                JOptionPane.showMessageDialog(this, "Snippet added successfully!");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(18, 22, 38));
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private void styleTextField(JTextField field) {
        field.setBackground(new Color(24, 28, 45));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));
    }

    private void styleTextArea(JTextArea area) {
        area.setBackground(new Color(24, 28, 45));
        area.setForeground(Color.WHITE);
        area.setCaretColor(Color.WHITE);
        area.setBorder(BorderFactory.createLineBorder(new Color(40, 45, 65)));
    }

    private void styleDialogButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(92, 112, 231));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
}