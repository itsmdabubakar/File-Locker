package com.example.filelocker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {

    // Store the selected file here
    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {
        // --- UI COMPONENTS ---

        // 1. File Selection Section
        Label lblInstruction = new Label("1. Select a file to Lock/Unlock:");
        Button btnSelectFile = new Button("📁 Choose File");
        Label lblSelectedFile = new Label("No file selected");
        lblSelectedFile.setStyle("-fx-text-fill: blue;"); // Make text blue

        // 2. Password Section
        Label lblPassword = new Label("2. Enter Secret Password:");
        PasswordField txtPassword = new PasswordField(); // Hides the password dots
        txtPassword.setPromptText("Type secret key...");

        // 3. Action Buttons
        Button btnEncrypt = new Button("🔒 Encrypt (Lock)");
        Button btnDecrypt = new Button("🔓 Decrypt (Unlock)");

        // Make buttons look strong
        btnEncrypt.setMaxWidth(Double.MAX_VALUE);
        btnDecrypt.setMaxWidth(Double.MAX_VALUE);

        // 4. Status Label (To show success/error)
        Label lblStatus = new Label("Status: Ready");

        // --- BUTTON ACTIONS ---

        // Logic: Open File Explorer
        btnSelectFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Sensitive File");
            selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                lblSelectedFile.setText(selectedFile.getName());
            }
        });

        // Logic: Encrypt
        btnEncrypt.setOnAction(e -> {
            if (selectedFile == null || txtPassword.getText().isEmpty()) {
                lblStatus.setText("Error: Please select file and password!");
                return;
            }
            try {
                // Create a new file name (e.g., photo.jpg -> photo.jpg.enc)
                File outputFile = new File(selectedFile.getAbsolutePath() + ".enc");

                AESUtils.encryptFile(selectedFile, outputFile, txtPassword.getText());

                lblStatus.setText("Success! Created: " + outputFile.getName());
                lblStatus.setStyle("-fx-text-fill: green;");
            } catch (Exception ex) {
                lblStatus.setText("Error: " + ex.getMessage());
            }
        });

        // Logic: Decrypt
        btnDecrypt.setOnAction(e -> {
            if (selectedFile == null || txtPassword.getText().isEmpty()) {
                lblStatus.setText("Error: Please select file and password!");
                return;
            }
            try {
                // Remove the .enc extension (e.g., photo.jpg.enc -> photo_decrypted.jpg)
                String originalName = selectedFile.getAbsolutePath().replace(".enc", "");
                File outputFile = new File(originalName.replace(".", "_decrypted."));

                AESUtils.decryptFile(selectedFile, outputFile, txtPassword.getText());

                lblStatus.setText("Success! Restored: " + outputFile.getName());
                lblStatus.setStyle("-fx-text-fill: green;");
            } catch (Exception ex) {
                lblStatus.setText("Error: Wrong Password or Corrupt File");
                lblStatus.setStyle("-fx-text-fill: red;");
            }
        });

        // --- LAYOUT ---
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                lblInstruction, btnSelectFile, lblSelectedFile,
                new Separator(), // Adds a line
                lblPassword, txtPassword,
                new Separator(),
                btnEncrypt, btnDecrypt,
                new Separator(),
                lblStatus
        );

        Scene scene = new Scene(layout, 350, 400);
        primaryStage.setTitle("File Locker 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}