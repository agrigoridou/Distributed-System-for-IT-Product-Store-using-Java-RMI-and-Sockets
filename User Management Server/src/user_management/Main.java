package user_management;

import warehouse_management.WarehouseManager;

public class Main {
    public static void main(String[] args) {
        try {
            WarehouseManager warehouseManager = (WarehouseManager) java.rmi.Naming.lookup("//localhost:1098/WarehouseManager");
            UserManager userManager = new UserManagerImpl(warehouseManager);
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("//localhost:1099/UserManager", userManager);
            System.out.println("User Management Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
