package user_management;

import warehouse_management.Product;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserManager extends Remote {
    boolean authenticate(String username, String password) throws RemoteException;
    List<Product> searchProducts(String criteria) throws RemoteException;
    boolean purchaseProduct(String username, String productCode, int quantity) throws RemoteException;
    boolean addProduct(String username, String productCode, String productName, int quantity) throws RemoteException; // Προσθήκη της μεθόδου addProduct
    boolean removeProduct(String username, String productCode) throws RemoteException; // Προσθήκη της μεθόδου removeProduct
}
