package com.mercadona.product.service;

import com.mercadona.product.dao.ProductRepository;
import com.mercadona.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final String DESTINATION_FORMAT = "%01d";
    private static final String ERROR_IN_THE_VALIDATION_OF_THE_FIELDS_ENTERED = "Error in the validation of the fields entered.";
    private static final String PRODUCT_FORMAT = "%05d";
    private static final String REGEX_DESTINATION = "[0-9]{1}";
    private static final String REGEX_PRODUCT = "[0-9]{1,5}";
    private static final String REGEX_SUPPLIER = "[0-9]{1,7}";
    private static final String SUPPLIER_FORMAT = "%07d";
    @Autowired
    ProductRepository dao;

    public Product saveProduct(Product product) {
        if (formatProduct(product)) {
            return dao.save(product);
        }
        else {
            throw new IllegalArgumentException(ERROR_IN_THE_VALIDATION_OF_THE_FIELDS_ENTERED);
        }
    }

    public List<Product> getProducts() {
        return dao.findAll();
    }

    public Optional<Product> getProductByEan(String ean) {
        return dao.findByEan(ean);
    }

    public void deleteProduct(Product product) {
        dao.delete(product);
    }

    public void deleteAllProducts(){
        dao.deleteAll();
    }

    private static boolean isNumeric(String code, String regex) {
        return  (code != null && code.matches(regex));
    }

    private static String formatter(String code, String format) {
        Formatter fmt = new Formatter();
        fmt.format(format, Integer.parseInt(code));
        return String.valueOf(fmt);
    }

    private static boolean formatProduct(Product product) {
        if (isNumeric(product.getSupplierCode(), REGEX_SUPPLIER)
                && isNumeric(product.getProductCode(), REGEX_PRODUCT)
                && isNumeric(product.getDestinationCode(), REGEX_DESTINATION)
                && !product.getDestinationCode().equals("7")) {
            product.setSupplierCode(formatter(product.getSupplierCode(), SUPPLIER_FORMAT));
            product.setProductCode(formatter(product.getProductCode(), PRODUCT_FORMAT));
            product.setDestinationCode(formatter(product.getDestinationCode(), DESTINATION_FORMAT));
            String eanCode = product.getSupplierCode().concat(product.getProductCode()).concat(product.getDestinationCode());
            product.setEan(eanCode);
            return true;
        }
        else {
            return false;
        }
    }
}
