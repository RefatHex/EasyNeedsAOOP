//// ShoppingCart.java
//
//import com.example.easyneedsaoop.ShoppingCartItem;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//
//public class ShoppingCart {
//
//    @FXML
//    private TableView<ShoppingCartItem> cartTable;
//
//    @FXML
//    private TableColumn<ShoppingCartItem, Double> col_price;
//
//    @FXML
//    private TableColumn<ShoppingCartItem, String> col_productName;
//
//    @FXML
//    private TableColumn<ShoppingCartItem, Integer> col_quantity;
//
//    @FXML
//    private TableColumn<ShoppingCartItem, String> col_size;
//
//    @FXML
//    private Button order;
//
//    // ObservableList to store the items in the shopping cart
//    private ObservableList<ShoppingCartItem> cartItems = FXCollections.observableArrayList();
//
//    public void initialize() {
//        // Set up the columns with cell value factories
//        col_productName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
//        col_price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
//        col_quantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
//        col_size.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
//
//        // Set the items in the table
//        cartTable.setItems(cartItems);
//    }
//
//    public void addItem(String productName, double price, int quantity, String size) {
////        ShoppingCartItem newItem = new ShoppingCartItem(productName, price, quantity, size);
//        cartItems.add(newItem);
//    }
//}
