package warehouse_management;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface WarehouseManager extends Remote {
    List<Product> getAvailableProducts() throws RemoteException;
    boolean addProduct(String productCode, String productName, int quantity) throws RemoteException;
    boolean removeProduct(String productCode) throws RemoteException;
    boolean purchaseProduct(String productCode, int quantity) throws RemoteException;
}
