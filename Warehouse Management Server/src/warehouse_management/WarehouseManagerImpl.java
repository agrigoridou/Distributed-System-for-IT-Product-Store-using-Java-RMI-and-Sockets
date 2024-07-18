package warehouse_management;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseManagerImpl extends UnicastRemoteObject implements WarehouseManager {
    private Map<String, Product> products;

    protected WarehouseManagerImpl() throws RemoteException {
        super();
        products = new HashMap<>();
        products.put("P001", new Product("P001", "Laptop", 10));
        products.put("P002", new Product("P002", "Keyboard", 50));
    }

    @Override
    public List<Product> getAvailableProducts() throws RemoteException {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean addProduct(String productCode, String productName, int quantity) throws RemoteException {
        if (products.containsKey(productCode)) {
            return false;
        }
        products.put(productCode, new Product(productCode, productName, quantity));
        return true;
    }

    @Override
    public boolean removeProduct(String productCode) throws RemoteException {
        if (products.containsKey(productCode)) {
            products.remove(productCode);
            return true;
        }
        return false;
    }

    @Override
    public boolean purchaseProduct(String productCode, int quantity) throws RemoteException {
        Product product = products.get(productCode);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }
}
