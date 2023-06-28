package ra.model;

import ra.config.InputMethods;
import ra.service.IService;

import java.util.ArrayList;
import java.util.List;

public class Product implements IService<Product,String>,Comparable<Product> {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;
    private List<Product> products = new ArrayList<>();
    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "ID: " + productId +
                " | Name: '" + productName +
                " | Price: " + productPrice +
                " | Des: " + description +
                " | Stock: " + stock +
                " | Catalog: " + catalog.getCatalogName() +
                " | Status: " + (status?"Bán":"Không bán")
                ;
    }
    public void inputData(List<Catalog>catalogs){
        System.out.println("Nhập ID: ");
        this.productId = InputMethods.getProductId();
        System.out.println("Nhập name: ");
        this.productName = InputMethods.getString();
        System.out.println("Nhập price: ");
        this.productPrice = InputMethods.getPositiveDouble();
        System.out.println("Nhập des: ");
        this.description = InputMethods.getString();
        System.out.println("Nhập stock: ");
        this.stock = InputMethods.getStock();
        System.out.println("Lựa chọn catalog: ");
        for (Catalog catalog : catalogs) {
            System.out.println(catalog.toString());
        }
        System.out.println("Nhập ID catalog muốn chọn: ");
        while(true){
            int catalogId = InputMethods.getInteger();
            boolean flag = true;
            for (Catalog catalog : catalogs) {
                if(catalogId==catalog.getCatalogId()){
                    flag = false;
                    this.catalog = catalog;
                    break;
                }
            }
            if(flag){
                System.out.println("Mã tác giả không tồn tại");
            } else {
                break;
            }
        }
        System.out.println("Nhập status: ");
        this.status = InputMethods.getBoolean();
    }
    public void editData(List<Catalog>catalogs){
        System.out.println("Nhập name: ");
        this.productName = InputMethods.getString();
        System.out.println("Nhập price: ");
        this.productPrice = InputMethods.getPositiveDouble();
        System.out.println("Nhập des: ");
        this.description = InputMethods.getString();
        System.out.println("Nhập stock: ");
        this.stock = InputMethods.getStock();
        System.out.println("Lựa chọn catalog: ");
        for (Catalog catalog : catalogs) {
            System.out.println(catalog.toString());
        }
        System.out.println("Nhập ID catalog muốn chọn: ");
        while(true){
            int catalogId = InputMethods.getInteger();
            boolean flag = true;
            for (Catalog catalog : catalogs) {
                if(catalogId==catalog.getCatalogId()){
                    flag = false;
                    this.catalog = catalog;
                    break;
                }
            }
            if(flag){
                System.out.println("Mã tác giả không tồn tại");
            } else {
                break;
            }
        }
        System.out.println("Nhập status: ");
        this.status = InputMethods.getBoolean();
    }
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product findById(String id) {
        for (Product product : products
        ) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        if(findById(product.getProductId())==null){
            products.add(product);

        }else {
            products.set(products.indexOf(product),product);
        }
    }

    @Override
    public void delete(String deleteId) {
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;
        }
        products.remove(findById(deleteId));
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getProductPrice(),this.getProductPrice());
    }
}
