package main.controllers;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import main.tables.EditionTable;
import main.tables.JournalTable;
import main.tables.VolumeTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderController {

    @FXML
    TreeView selectionTreeView;
    @FXML
    public void handleTree(javafx.event.ActionEvent event) throws SQLException {
        createTree();
    }

    public void createTree(String... rootItems) throws SQLException {
        //create root
        TreeItem<String> root = new TreeItem<>("Journals");
        root.setExpanded(true);
        //create child
        //get list of journals
        ArrayList<String> journalList = JournalTable.selectJournals();

        // add journals to tree
        for (int i = 0; i < journalList.size(); i++){
            TreeItem<String> journals = new TreeItem<>(journalList.get(i));
            journals.setExpanded(false);
            root.getChildren().add(journals);
            //get volumes of that journal
            ArrayList<String> volumeList = VolumeTable.selectVolumes(journalList.get(i));

            //add volumes to tree
            for (int j = 0; j < volumeList.size(); j++) {
                TreeItem<String> volumes = new TreeItem<>(volumeList.get(j));
                volumes.setExpanded(false);
                journals.getChildren().add(volumes);
                //get volumes of that journal
                ArrayList<String> editionsList = EditionTable.selectEditions(VolumeTable.SelectVolID(Integer.valueOf(volumeList.get(j))));

                //add volumes to tree
                for (int k = 0; k < editionsList.size(); k++) {
                    TreeItem<String> editions = new TreeItem<>(editionsList.get(k));
                    editions.setExpanded(false);
                    volumes.getChildren().add(editions);

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



}
