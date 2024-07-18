package warehouse_management;

public class Main {
    public static void main(String[] args) {
        try {
            WarehouseManager warehouseManager = new WarehouseManagerImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1098);
            java.rmi.Naming.rebind("//localhost:1098/WarehouseManager", warehouseManager);
            System.out.println("Warehouse Management Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
