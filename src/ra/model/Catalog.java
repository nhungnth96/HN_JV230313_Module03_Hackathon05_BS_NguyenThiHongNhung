package ra.model;

import ra.config.InputMethods;
import ra.service.IService;

import java.util.ArrayList;
import java.util.List;

public class Catalog implements IService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList<>();
    private int catalogId;
    private String catalogName;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return
                "ID: " + catalogId +
                        " | Name: " + catalogName
                ;
    }

    public void inputData() {
        System.out.println("Nhập ID: ");
        this.catalogId = InputMethods.getInteger();
        System.out.println("Nhập name: ");
        this.catalogName = InputMethods.getString();
    }


    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : catalogs
        ) {
            if (catalog.getCatalogId() == (id)) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void save(Catalog catalog) {
        if(findById(catalog.getCatalogId())==null){
            catalogs.add(catalog);

        }else {
            catalogs.set(catalogs.indexOf(catalog),catalog);
        }
    }

    @Override
    public void delete(Integer deleteId) {
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;
        }
        catalogs.remove(findById(deleteId));
    }
    public void delete(List<Product> products, Integer deleteId){
        boolean check = true;
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;

        }
        for (Product product : products) {
            if(product ==null){
                catalogs.remove(findById(deleteId));
            }  else {
                System.out.println("Không thể xóa catalog vì có product");
                break;
            }
        }

    }
}
