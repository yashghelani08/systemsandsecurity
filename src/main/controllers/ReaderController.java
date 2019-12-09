package main.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.tables.ArticleTable;
import main.tables.EditionTable;
import main.tables.JournalTable;
import main.tables.VolumeTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderController {

    @FXML
    public TextArea abstractArea;
    public Label editorLab;
    public Label authorLab;
    public Text titleLab;
    public TextField pdfLink;

    @FXML
    TreeView selectionTreeView;

    public void initialize() throws SQLException {
        createTree2();
//        createTree();
    }

    private void createTree2(String... rootItems) throws SQLException {
        TreeItem<String> root = new TreeItem<>("Articles");
        root.setExpanded(true);
        ArrayList<String> artList = ArticleTable.SelectAllArticleTitles();

        for (int i = 0; i < artList.size(); i++) {
            TreeItem<String> journals = new TreeItem<>(artList.get(i));
            journals.setExpanded(true);
            root.getChildren().add(journals);
        }
        selectionTreeView.setRoot(root);

    }

    public void createTree(String... rootItems) throws SQLException {
        //create root
        TreeItem<String> root = new TreeItem<>("Journals");
        root.setExpanded(true);
        //create child
        //get list of journals
        ArrayList<String> journalList = JournalTable.SelectJournals();

        // add journals to tree
        for (int i = 0; i < journalList.size(); i++){
            TreeItem<String> journals = new TreeItem<>(journalList.get(i));
            journals.setExpanded(true);
            root.getChildren().add(journals);
            //get volumes (years) of that journal using journal name to get issn, then using issn to get publication year
            ArrayList<String> volumeList = VolumeTable.SelectVolumes(JournalTable.SelectISSN((journalList.get(i))));

            //add volumes to tree
            for (int j = 0; j < volumeList.size(); j++) {
                TreeItem<String> volumes = new TreeItem<>(volumeList.get(j));
                volumes.setExpanded(true);
                journals.getChildren().add(volumes);
                //**************************************need to update column in editions table to store year instead of vol id**************************
                //get editions of that volume using volume year
                int volID = VolumeTable.SelectVolID(Integer.valueOf(volumeList.get(j)));
                ArrayList<String> editionsList = EditionTable.selectEditions(volID);

                System.out.println("Vol list "+volumeList);
                System.out.println("ed list "+editionsList);

                //add volumes to tree
                for (int k = 0; k < editionsList.size(); k++) {
                    TreeItem<String> editions = new TreeItem<>(editionsList.get(k));
                    editions.setExpanded(true);
                    volumes.getChildren().add(editions);
                    //get articles of that edition using month
                    int editionid = EditionTable.SelectID(volID, editionsList.get(k));
                    ArrayList<String> articleList = null;
                    if (editionid != -1){
                        articleList = (ArrayList<String>) ArticleTable.SelectTitles(editionid);
                    }
                    System.out.println(articleList);

                    if (articleList != null){
                        for (int l = 0; l < articleList.size(); l++){
                            TreeItem<String> articles = new TreeItem<>(articleList.get(l));
                            editions.getChildren().add(articles);
                        }
                    }

                    System.out.println("edlist 2 "+editionsList.get(k)+" volid "+volID);

                }

            }

        }

        selectionTreeView.setRoot(root);
    }


    public void handleLogOut(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void loadArticle(MouseEvent mouseEvent) throws SQLException {
        String articleTitle = mouseEvent.getPickResult().toString();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(articleTitle);
        String title = "";
        while (m.find()) {
            System.out.println(m.group(1));
            title = m.group(1);

        }
        titleLab.setText(title);
        abstractArea.setText(ArticleTable.SelectAbstract(title));
        pdfLink.setText(ArticleTable.SelectPDF(title));
//        System.out.println(articleTitle.substring(articleTitle.indexOf('\'', articleTitle.lastIndexOf('\''))));
//
    }
}
