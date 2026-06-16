package ui;

import javax.swing.*;
import java.awt.*;
import model.Snippet;
import databaseOperations.dataQR;

public class detailpanel extends JPanel {
    private dataQR data;
    private Snippet currentSnippet;
    JLabel titleLabel;
    JLabel languageLabel;
    JLabel descriptionLabel;
    JCheckBox favorite;
    JPanel tagsPanel;
    JLabel createdLabel;
    JLabel updatedLabel;
    JLabel collectionLabel;
    JLabel languageInfoLabel;
    public detailpanel(dataQR data) {
        this.data=data;
        initUI();
    }



    public void displaySnippet(Snippet snippet)
    {
        currentSnippet=snippet;
        titleLabel.setText(snippet.getTitle());
        languageLabel.setText(snippet.getLanguage());
        descriptionLabel.setText(snippet.getDescription());
        favorite.setSelected(snippet.isFavorite());

        // Display the dates - ADD THESE LINES
        createdLabel.setText("Created : " + snippet.getFormattedCreatedAt());
        updatedLabel.setText("Updated : " + snippet.getFormattedUpdatedAt());
        languageInfoLabel.setText("Language : " + snippet.getLanguage());
    }

    public void initUI()
    {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBackground(new Color(18,22,38));

        setBorder(
                BorderFactory.createEmptyBorder(20,20,20,20 )
        );

        //the title

        titleLabel = new JLabel("Code title");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(new Font("Segoe UI",Font.BOLD,24));

        //this section adds to database
        favorite = new JCheckBox("Favorite");
        favorite.setBackground(new Color(18,22,38));
        favorite.setForeground(Color.WHITE);
        favorite.setFocusable(false);
        //action listener
        favorite.addActionListener(e->{
            if(currentSnippet!=null){
                boolean isSelected=favorite.isSelected();
                currentSnippet.setFavorite(isSelected);
                data.updateFavorite(currentSnippet.getId(),isSelected);
                System.out.println("favorite updated to"+isSelected);
            }
        });


        JPanel titlePanel = new JPanel( new BorderLayout());

        titlePanel.setBackground(new Color(18,22,38));

        titlePanel.add(titleLabel, BorderLayout.WEST
        );

        titlePanel.add(favorite,  BorderLayout.EAST);
        add(titlePanel);

        languageLabel = new JLabel("programming language ");

        languageLabel.setForeground(
                new Color(120,140,255)
        );

        languageLabel.setFont(
                new Font("Segoe UI", Font.PLAIN,15)
        );

        add(languageLabel);

        add(Box.createVerticalStrut(15));

        descriptionLabel = new JLabel(
                "Code description"
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
                new JLabel("meta data about the code:")
        );

        add(tagsPanel);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(10));

        // information section

        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(
                new GridLayout( 2, 2, 20, 20)
        );

        infoPanel.setBackground(
                new Color(18,22,38)
        );

        createdLabel =
                new JLabel("added date :");

        updatedLabel =
                new JLabel("last updated date :");

        languageInfoLabel =
                new JLabel("Language :");

        collectionLabel =
                new JLabel(" ");

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
    public Snippet getCurrentSnippet() {
        return currentSnippet;
    }

}