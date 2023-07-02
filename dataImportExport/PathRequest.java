package dataImportExport;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class PathRequest extends Application {

    @Override
    public void start(Stage openDialog) throws Exception {

        FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open Resource File");
                    fileChooser.getExtensionFilters()
                               .addAll(new FileChooser.ExtensionFilter("txt File", "*.txt"));

        Optional<File> optionalSelectedFile = Optional.ofNullable(fileChooser.showOpenDialog(openDialog));

        String path = optionalSelectedFile.get().getAbsolutePath();
        System.out.println("Path: " + path);

        /*Image image = new Image("file:" + path);
        if (optionalSelectedFile.isPresent()) {
            ImageView iv1 = new ImageView();
                      iv1.setImage(image);
            HBox box = new HBox();
                 box.getChildren().add(iv1);
            Stage viewer = new Stage();
                  viewer.setTitle(path);
                  viewer.setScene(new Scene(box));
                  viewer.setWidth(800);
                  viewer.setHeight(600);
                  viewer.show();
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}