package user_management;

import warehouse_management.Product;
import warehouse_management.WarehouseManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserManagerImpl extends UnicastRemoteObject implements UserManager {
    private Map<String, String> users;
    private WarehouseManager warehouseManager;

    protected UserManagerImpl(WarehouseManager warehouseManager) throws RemoteException {
        super();
        this.warehouseManager = warehouseManager;
        users = new HashMap<>();
        users.put("admin", "adminpass");
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    @Override
    public boolean authenticate(String username, String password) throws RemoteException {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    @Override
    public List<Product> searchProducts(String criteria) throws RemoteException {
        List<Product> availableProducts = warehouseManager.getAvailableProducts();
        return availableProducts.stream()
                .filter(product -> product.getName().toLowerCase().contains(criteria.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean purchaseProduct(String username, String productCode, int quantity) throws RemoteException {
        try {
            boolean purchaseSuccessful = warehouseManager.purchaseProduct(productCode, quantity);
            if (purchaseSuccessful) {
                System.out.println("User " + username + " purchased product with code: " + productCode + " and quantity: " + quantity);
            } else {
                System.out.println("User " + username + " failed to purchase product with code: " + productCode + " and quantity: " + quantity);
            }
            return purchaseSuccessful;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addProduct(String username, String productCode, String productName, int quantity) throws RemoteException {
        if (isAdmin(username)) {
            return warehouseManager.addProduct(productCode, productName, quantity);
        } else {
            System.out.println("User " + username + " does not have permission to add products.");
            return false;
        }
    }

    @Override
    public boolean removeProduct(String username, String productCode) throws RemoteException {
        if (isAdmin(username)) {
            return warehouseManager.removeProduct(productCode);
        } else {
            System.out.println("User " + username + " does not have permission to remove products.");
            return false;
        }
    }

    private boolean isAdmin(String username) {
        return username.equals("admin");
    }
}
