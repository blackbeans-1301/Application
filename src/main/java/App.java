import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *  CHÚ ÝYYYYYYYYYYYYYY.
 *  1. Hoàn thành hàm getSearch ở cuối cùng, LINE 394.
 *  2. Hoàn thành hàm showAddWordDialog, LINE 230.
 *  3. Hoàn thành hàm showEditWordDialog, LINE 260.
 *  4. Hoàn thành hàm showEraseWordDialog, LINE 283.
 *  5. Hoàn thành hàm phát âm tiếng anh, LINE 363.
 */

public class App extends Application{

    public static Stage window = new Stage();
    public static Group root = new Group();
    public static GridPane grid = new GridPane();
    public static ListView<Word> wordList = new ListView<Word>();
    private final int WIDTH = 960;
    private final int HEIGHT = 740;

    /**
     * Phần này demo app thôi, lúc làm thì xóa đi.
     */
    Word word1 = new Word("hello", "xin chào", "Động từ");
    Word word2 = new Word("apple", "qua tao", "Danh từ");


    @Override
    public void start(Stage primaryStage) throws Exception {

        initLoadingWindow();
        isRunning();
//        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initLoadingWindow() throws FileNotFoundException, InterruptedException {

        window.setTitle("DTL Dictionary");
        Group root = new Group();

        FileInputStream icon = new FileInputStream("src/main/resources/DTL.png");
        Image ic = new Image(icon);

        FileInputStream image = new FileInputStream("src/main/resources/loadImage.png");
        Image loadBackground = new Image(image);
        ImageView imageView = new ImageView(loadBackground);

        root.getChildren().add(imageView);
        window.getIcons().add(ic);
        Scene loadScene = new Scene(root, WIDTH, HEIGHT);
        window.setScene(loadScene);

        window.show();

    }

    private void isRunning() throws FileNotFoundException {



//        GridPane grid = new GridPane();

        Scene scene;

        FileInputStream background = new FileInputStream("src/main/resources/background.png");
        Image bgr = new Image(background);
        ImageView viewBgr = new ImageView(bgr);

        viewBgr.setFitWidth(window.getWidth());
        viewBgr.setFitHeight(window.getHeight());

        //add search bar.
        Rectangle searchBar = new Rectangle();
        searchBar.setX(0);
        searchBar.setY(0);
        searchBar.setHeight(45);
        searchBar.setWidth(300);
        searchBar.setFill(Color.rgb(235, 212, 159));

        //Add Definition Bar
        Rectangle definitionBar = new Rectangle();
        definitionBar.setX(300);
        definitionBar.setY(0);
        definitionBar.setWidth(WIDTH - searchBar.getWidth());
        definitionBar.setHeight(45);
        definitionBar.setFill(Color.rgb(246, 176, 146));

        //Add search box.
        Rectangle searchBox = new Rectangle();
        searchBox.setX(0);
        searchBox.setY(0);
        searchBox.setHeight(2160);
        searchBox.setWidth(searchBar.getWidth());
        searchBox.setFill(Color.rgb(241, 229, 200));

        //Text search and definition
        Text search = new Text("Search");
        Font font = Font.loadFont("file:src/main/resources/Alegreya/Alegreya-Bold.ttf", 25);
        search.setX(110);
        search.setY(31);
        search.setFont(font);
        search.setFill(Color.rgb(110, 75, 32));

        //Text field input
        TextField input = new TextField();
        Font searchFont = Font.loadFont("file:src/main/resources/Alegreya/Alegreya.ttf", 18);
        input.setFont(searchFont);
        input.setLayoutX(38);
        input.setScaleX(1.2);
        input.setLayoutY(120);
//        ObservableList<Word> foundedWord = FXCollections.observableArrayList();
        input.setPromptText("Search");

        input.setOnAction(e -> {

            String inputRes = input.getText();

            this.wordSearcher(inputRes);

        });



        Text definition = new Text("Definition");
        definition.setX(550);
        definition.setY(31);
        definition.setFont(font);
        definition.setFill(Color.rgb(110, 75, 32));

        // Add button addWord.
        Button addWordButton = new Button();
        FileInputStream addButton = new FileInputStream("src/main/resources/add.png");
        Image addBt = new Image(addButton);
        addWordButton.setGraphic(new ImageView(addBt));
        addWordButton.setScaleX(0.035);
        addWordButton.setScaleY(0.035);
        addWordButton.setLayoutX(-915);
        addWordButton.setLayoutY(-463);
        addWordButton.setOnAction(e -> {
            this.showAddWordDialog();
        });


        // Add button editWord.
        Button editWordButton = new Button();
        FileInputStream editButton = new FileInputStream("src/main/resources/edit.png");
        Image editbt = new Image(editButton);
        editWordButton.setGraphic(new ImageView(editbt));
        editWordButton.setScaleX(0.035);
        editWordButton.setScaleY(0.035);
        editWordButton.setLayoutX(-820);
        editWordButton.setLayoutY(-463);
        editWordButton.setOnAction(e -> {
            this.showEditWordDialog();
        });

        //Add button eraseWord.
        Button eraseWordButton = new Button();
        FileInputStream eraseButton = new FileInputStream("src/main/resources/erase.png");
        Image erasebt = new Image(eraseButton);
        eraseWordButton.setGraphic(new ImageView(erasebt));
        eraseWordButton.setScaleX(0.035);
        eraseWordButton.setScaleY(0.035);
        eraseWordButton.setLayoutX(-725);
        eraseWordButton.setLayoutY(-463);
        eraseWordButton.setOnAction(e -> {
            this.showEraseWordDialog();
        });


        root.getChildren().add(viewBgr);
        root.getChildren().add(searchBox);
        root.getChildren().add(searchBar);
        root.getChildren().add(definitionBar);
        root.getChildren().add(search);
        root.getChildren().add(definition);
        root.getChildren().add(input);
        root.getChildren().add(addWordButton);
        root.getChildren().add(editWordButton);
        root.getChildren().add(eraseWordButton);
        root.getChildren().add(grid);

        scene = new Scene(root, WIDTH, HEIGHT);


        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    /**
     * Show input dialog of add word button.
     */
    private void showAddWordDialog() {
        TextInputDialog dialogTarget = new TextInputDialog();
        dialogTarget.setTitle("ADD WORD TARGET");
        dialogTarget.setHeaderText("Add a word-target!!");
        dialogTarget.setContentText("The word-target you want to add is:");

        Optional<String> inputTarget = dialogTarget.showAndWait();

        //Word target.
        String resultTarget = inputTarget.get();

        TextInputDialog dialogExplain = new TextInputDialog();
        dialogExplain.setTitle("ADD WORD EXPLAIN");
        dialogExplain.setHeaderText("Add a word-explain!!");
        dialogExplain.setContentText("The word-explain you want to add is:");
        Optional<String> inputExplain = dialogExplain.showAndWait();

        //Word explain.
        String resultExplain = inputExplain.get();

        // Biến String resultTarget là wordTarget user nhập vào.
        // Biến String resultExplain là wordExplain user nhập vào.
        // Đã có 2 biến người dùng nhập vào. Sau dòng code này là hàm để add new word vào dictionary.
        // Type you code hear...

    }

    /**
     *  Show input dialog off edit word button.
     */
    private void showEditWordDialog() {

        TextInputDialog dialogTarget = new TextInputDialog();
        dialogTarget.setTitle("EDIT WORD DIALOG");
        dialogTarget.setHeaderText("Type a word you want to edit here ");
        dialogTarget.setContentText("Word");
        Optional<String> resultTarget = dialogTarget.showAndWait();
        String wordTarget = resultTarget.get();

        TextInputDialog dialogExplain = new TextInputDialog();
        dialogExplain.setTitle("EDIT WORD DIALOG");
        dialogExplain.setHeaderText("Type word explain here");
        dialogExplain.setContentText("Word");
        Optional<String> resultExplain = dialogExplain.showAndWait();
        String wordExplain = resultExplain.get();

        // Code ở đây
        // String wordTarget và wordExplain
        //...


    }

    private void showEraseWordDialog() {

        TextInputDialog dialogErase = new TextInputDialog();
        dialogErase.setTitle("ERASE WORD DIALOG");
        dialogErase.setHeaderText("Type word you want to delete");
        dialogErase.setContentText("Word");
        Optional<String> result = dialogErase.showAndWait();

        String wordErase = result.get();
        //Biến String wordErase là từ mà user muốn xóa. Code phần xóa từ dưới đây...



    }

    private void wordSearcher(String input) {

        // Hoàn thành hàm getSearch ở dưới cùng nhé. Hàm này trả về 1 ArrayList là danh sách các từ tìm được

        ObservableList<Word> foundedWord = FXCollections.observableArrayList();
        ArrayList<Word> words = this.getSearch();

        foundedWord.removeAll(words);
        foundedWord.addAll(words);
        ListView<Word> wordList = new ListView<Word>(foundedWord);
        wordList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Từ vừa search
        Text target = new Text();
//
        // Tạo nút phát âm từ
        Button speaker = new Button();

        // Từ loại
        Text type = new Text();

        // Phần giải nghĩa
        Text explain = new Text();

        grid.add(target, 0, 0);
        grid.add(type, 0, 1);
        grid.add(explain, 0, 2);
        root.getChildren().add(speaker);

        wordList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {
            @Override
            public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {


                grid.setVgap(20);
                grid.setMinWidth(500);
                grid.setMinHeight(500);
                grid.setLayoutX(300);
                grid.setLayoutY(30);
                grid.setPadding(new Insets(20));
                grid.setHgap(10);
                grid.setVgap(10);


                // Từ vừa search
                target.setText(newValue.wordTarget);
                Font targetFont = Font.loadFont("file:src/main/resources/Alegreya/Alegreya.ttf", 25);
                target.setFont(targetFont);

                // Nút phát âm
                FileInputStream spk = null;
                try {
                    spk = new FileInputStream("src/main/resources/speak.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image spkButton = new Image(spk);
                speaker.setGraphic(new ImageView(spkButton));
                speaker.setScaleX(0.035);
                speaker.setScaleY(0.035);
                speaker.setLayoutX(100);
                speaker.setLayoutY(-475);
                speaker.setOnAction(e -> {
                    // Hàm phát âm từ ở đây.
                    // Type your code here...


                });

                // Từ loại.
                type.setText('(' + newValue.wordType + ')');
                Font wordTypeFont = Font.loadFont("file:src/main/resources/Alegreya/Alegreya-Italic.ttf", 22);
                type.setFont(wordTypeFont);
                type.setFill(Color.rgb(5 , 112, 180));

                // Giải thích
                explain.setText( "Định nghĩa: " + newValue.wordExplain);
                Font explainFont = Font.loadFont("file:src/main/resources/Alegreya/Alegreya.ttf", 25);
                explain.setFont(explainFont);
            }
        });

        wordList.setLayoutX(15);
        wordList.setLayoutY(180);
        wordList.setMinWidth(270);
        wordList.setMinHeight(HEIGHT - 200);
        wordList.setPadding(new Insets(10));

        root.getChildren().add(wordList);

    }

    /**
     * Hàm tìm kiếm từ.
     * @return output.
     */
    public ArrayList<Word> getSearch() {

        ArrayList<Word> output = new ArrayList<Word>();
        output.add(word1);
        output.add(word2);
        return output;
    }
}
