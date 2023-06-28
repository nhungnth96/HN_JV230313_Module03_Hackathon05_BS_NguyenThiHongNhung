package ra.run;

import ra.config.InputMethods;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.IService;

import java.util.Collections;
import java.util.List;

public class BookManagement {
        private final static Catalog CATALOG = new Catalog();
        private final static Product PRODUCT = new Product();
        private static List<Catalog> catalogs = CATALOG.getAll();
        private static List<Product> products = PRODUCT.getAll();

    public static void main(String[] args) {

        boolean loop = true;
        while (loop){
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục [5 điểm]\n" +
                    "2. Quản lý sản phẩm [5 điểm]\n" +
                    "3. Thoát [5 điểm]");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    catalogMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    loop = false;
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);

            }
        }
    }
    // ****************************CATALOG*********************
    public static void catalogMenu(){
        while(true){
            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục [5 điểm]\n" +
                    "2. Hiển thị thông tin tất cả các danh mục [5 điểm]\n" +
                    "3. Sửa tên danh mục theo mã danh mục [5 điểm]\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) [5 điểm]\n" +
                    "5. Quay lại");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    addNewCatalog();
                    break;
                case 2:
                    showAllCatalog();
                    break;
                case 3:
                    editCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);
            } if(choice==5){
                break;
            }
        }
    }
    public static void addNewCatalog(){
        System.out.println("Nhập số lượng thêm mới: ");
        int sl = InputMethods.getInteger();
        for (int i = 1; i <= sl; i++) {
                System.out.println("Nhập thông tin catalog " + i);
                Catalog catalog = new Catalog();
                catalog.inputData();
                CATALOG.save(catalog);
        }
    }
    public static void showAllCatalog(){
        for (Catalog catalog : catalogs) {
            System.out.println( catalog.toString());
        }
    }
    public static void editCatalog(){
        System.out.println("Nhập ID muốn sửa");
        Integer editId = InputMethods.getInteger();
        Catalog editCatalog = CATALOG.findById(editId);
        if(editId ==null){
            System.err.println("Không tìm thấy catalog ");
            return;
        }
        System.out.println(editCatalog);
        editCatalog.inputData();
        CATALOG.save(editCatalog);
    }
    public static void deleteCatalog(){
        System.out.println("nhập ID muốn xóa ");
        int deleteId = InputMethods.getInteger();
        CATALOG.delete(deleteId);
    }
    // ****************************PRODUCT*********************
    public static void productMenu(){
        while(true){
            System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                    "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm [5 điểm]\n" +
                    "2. Hiển thị thông tin các sản phẩm [5 điểm]\n" +
                    "3. Sắp xếp sản phẩm theo giá giảm dần [5 điểm]\n" +
                    "4. Xóa sản phẩm theo mã [5 điểm]\n" +
                    "5. Tìm kiếm sách theo tên sách [10 điểm]\n" +
                    "6. Thay đổi thông tin của sách theo mã sách [10 điểm]\n" +
                    "7. Quay lại");
            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    showAllProduct();
                    break;
                case 3:
                    sortByDESPrice();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    editProduct();
                    break;
                case 7:
                    break;
                default:
                    System.err.println(InputMethods.ERROR_ALERT);
            } if(choice==7){
                break;
            }
        }
    }
    public static void addNewProduct(){
        if(catalogs.isEmpty()){
            System.err.println("Không có catalog, thêm mới trước");
            return;
        }
        System.out.println("Nhập số lượng thêm mới: ");
        int sl = InputMethods.getInteger();
        for (int i = 1; i <= sl; i++) {
            System.out.println("Nhập thông tin product " + i);
            Product product = new Product();
            product.inputData(catalogs);
            PRODUCT.save(product);
        }
    }
    public static void showAllProduct(){

        for (Product product : products) {
            System.out.println( product.toString());
        }
    }
    public static void editProduct(){
        System.out.println("Nhập ID muốn sửa");
        String editId = InputMethods.getString();
        Product editProduct = PRODUCT.findById(editId);
        if(editId ==null){
            System.err.println("Không tìm thấy product ");
            return;
        }
        System.out.println(editProduct);
        editProduct.editData(catalogs);
        PRODUCT.save(editProduct);
    }
    public static void deleteProduct(){
        System.out.println("nhập ID muốn xóa ");
        String deleteId = InputMethods.getString();
        PRODUCT.delete(deleteId);
    }
    public static void sortByDESPrice(){
        Collections.sort(products);
    }
    public static void searchByName(){
        System.out.println("Nhập vào từ khóa tên sách: ");
        boolean check =  true;
        String keyword = InputMethods.getString();
        for (Product product: products) {
            if(product.getProductName().toLowerCase().contains(keyword)){
                System.out.println(product.toString());
                check= false;
            }
        }
        if (check){
            System.err.println("Không tìm thấy");
        }
    }
    }

