
import inventory.ClientInventory;
import inventory.GlobalInventory;
import inventory.GlobalInventoryItem;
import inventory.InventoryItem;
import inventory.SectorInventory;
import inventory.agents.Client;
import inventory.agents.Filial;
import inventory.agents.Responsible;
import inventory.agents.Sector;
import inventory.agents.Supplier;
import inventory.movements.FromSupplierToSector;
import inventory.movements.Movement;
import inventory.mrp.tree.Product;
import inventory.mrp.tree.ProductItem;
import inventory.mrp.tree.ProductModule;
import inventory.orders.ManufacturingOrder;
import inventory.orders.OrderItem;
import inventory.orders.PurchaseOrder;
import inventory.orders.SaleOrder;
import inventory.util.Unit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author adrianohrl
 */
public class MRPTreeTest {
    
    public static void main(String[] args) {
        
        /** Unidades utilizadas */
        Unit g = new Unit("g", "Gramas", true);
        Unit h = new Unit("h", "Horas", true);
        Unit uni = new Unit("uni", "Unidades", false);
        Unit p = new Unit("%", "Porcentagem", true);
        
        /** MPs */
        List<GlobalInventoryItem> fios = new ArrayList<>();
        fios.add(new GlobalInventoryItem("F1 (Azul)", 0, g, 12, h));
        fios.add(new GlobalInventoryItem("F2 (Azul Marinho)", 0, g, 12, h));
        fios.add(new GlobalInventoryItem("F3 (Azul Claro)", 0, g, 12, h));
        fios.add(new GlobalInventoryItem("F4 (Ciano)", 0, g, 24, h));
        GlobalInventoryItem ziper25 = new GlobalInventoryItem("Zíper Azul (25 cm)", 0, uni, 48, h);
        //ziper25.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.NOVEMBER, 20), 7, uni));
        //ziper25.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 2), 6, uni));
        GlobalInventoryItem ziper35 = new GlobalInventoryItem("Zíper Azul (35 cm)", 0, uni, 0, h);
        //ziper35.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 5), 10, uni));
        GlobalInventoryItem ziper50 = new GlobalInventoryItem("Zíper Azul (50 cm)", 0, uni, 0, h);
        //ziper50.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.OCTOBER, 15), 4, uni));
        GlobalInventoryItem micanga = new GlobalInventoryItem("Micanga Perola", 0, uni, 72, h);
        //micanga.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.SEPTEMBER, 10), 500, uni));
        //micanga.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 1), 500, uni));
        
        /** WIP */
        GlobalInventoryItem blusa1Frente = new GlobalInventoryItem("Blusa 1 Frente", 0, p, 0, h);   
        ProductModule frente1 = new ProductModule(blusa1Frente, 1.00, p, 0, h);
        frente1.addChild(new ProductItem(fios.get(0), 100, g, 0, h));
        frente1.addChild(new ProductItem(fios.get(1), 200, g, 0, h));
        GlobalInventoryItem blusa1Costas = new GlobalInventoryItem("Blusa 1 Costas", 0, p, 0, h); 
        ProductModule costas1 = new ProductModule(blusa1Costas, 1.00, p, 0, h);
        costas1.addChild(new ProductItem(fios.get(0), 50, g, 0, h));
        costas1.addChild(new ProductItem(fios.get(2), 250, g, 0, h));
        GlobalInventoryItem blusa1Manga = new GlobalInventoryItem("Blusa 1 Manga", 0, p, 0, h); 
        ProductModule manga1 = new ProductModule(blusa1Manga, 2.00, p, 0, h);
        manga1.addChild(new ProductItem(fios.get(3), 150, g, 0, h));          
        
        /** PFs */
        GlobalInventoryItem blusa1p = new GlobalInventoryItem("Blusa 1 (P)", 0, uni, 0, h);
        Product blusa1pTree = new Product(blusa1p, 0, h);
        blusa1pTree.addChild(frente1);
        blusa1pTree.addChild(costas1);
        blusa1pTree.addChild(manga1);
        blusa1pTree.addChild(new ProductItem(micanga, 20, uni, 0, h));
        blusa1pTree.addChild(new ProductItem(ziper25, 1, uni, 0, h));
        
        /*GlobalInventoryItem blusa1m = new GlobalInventoryItem("Blusa 1 (M)", 0, uni, 0, h);
        Product blusa1mTree = new Product(blusa1m, 0, h);
        blusa1mTree.addChild(new ProductModule(frente1, 1.10, p, 0, h));
        blusa1mTree.addChild(new ProductModule(costas1, 1.10, p, 0, h));
        blusa1mTree.addChild(new ProductModule(manga1, 1.10, p, 0, h));
        blusa1mTree.addChild(new ProductItem(micanga, 20, uni, 0, h));
        blusa1mTree.addChild(new ProductItem(ziper35, 1, uni, 0, h));
        
        GlobalInventoryItem blusa1g = new GlobalInventoryItem("Blusa 1 (G)", 0, uni, 0, h);
        Product blusa1gTree = new Product(blusa1g, 0, h);
        blusa1gTree.addChild(new ProductModule(frente1, 1.25, p, 0, h));
        blusa1gTree.addChild(new ProductModule(costas1, 1.25, p, 0, h));
        blusa1gTree.addChild(new ProductModule(manga1, 1.25, p, 0, h));
        blusa1gTree.addChild(new ProductItem(micanga, 20, uni, 0, h));
        blusa1gTree.addChild(new ProductItem(ziper50, 1, uni, 0, h));*/
        
        System.out.println("\n--------- MRP Trees ---------");
        System.out.println("-----------------------------");
        System.out.println(blusa1pTree);
        /*System.out.println("-----------------------------");
        System.out.println(blusa1mTree);
        System.out.println("-----------------------------");
        System.out.println(blusa1gTree);*/
        
        
        Filial filial = new Filial("Cecília Prado - Fábrica de Jacutinga");
        GlobalInventory globalInventory = new GlobalInventory(filial);
        globalInventory.addAllItems(fios); globalInventory.add(ziper25); globalInventory.add(ziper35); globalInventory.add(ziper50); globalInventory.add(micanga);
        globalInventory.add(blusa1Frente); globalInventory.add(blusa1Costas); globalInventory.add(blusa1Manga);
        globalInventory.add(blusa1p); //globalInventory.add(blusa1m); globalInventory.add(blusa1g);
        
        Supplier fiosAmparo = new Supplier("Fios Amparo");
        SectorInventory estoqueMP = new SectorInventory(globalInventory, new Sector("Estoque de Matéria Prima"));
        //SectorInventory estoqueWIP = new SectorInventory(globalInventory, new Sector("Estoque de Produto Semi-acabado"));
        //SectorInventory estoquePA = new SectorInventory(globalInventory, new Sector("Estoque de Produto Acabado"));
        ClientInventory renner = new ClientInventory(globalInventory, new Client("Renner"));
        globalInventory.add(estoqueMP);
        //globalInventory.add(estoqueWIP);
        //globalInventory.add(estoquePA);
        globalInventory.add(renner);
        
        Responsible adrianohrl = new Responsible("Adriano Henrique Rossette Leite");
        Movement movement1 = new FromSupplierToSector(adrianohrl, fiosAmparo, estoqueMP, new GregorianCalendar());
        movement1.add(new InventoryItem(fios.get(0), 50, 0, g));
        movement1.add(new InventoryItem(fios.get(1), 20000, 0, g));
        movement1.add(new InventoryItem(fios.get(2), 2000, 0, g));
        movement1.add(new InventoryItem(fios.get(3), 350, 0, g));
        movement1.add(new InventoryItem(micanga, 50, 0, uni));
        movement1.add(new InventoryItem(ziper25, 4, 0, uni));
        movement1.add(new InventoryItem(blusa1Frente, 3, 0, p));
        movement1.add(new InventoryItem(blusa1Costas, 2, 0, p));
        movement1.add(new InventoryItem(blusa1Manga, 4, 0, p));
        movement1.add(new InventoryItem(blusa1p, 5, 0, p));
        if (movement1.move()) {
            globalInventory.add(movement1);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (1)!!!");
        }
        
        System.out.println("\n----------- Starting Filial Inventory ----------");
        System.out.println(globalInventory);
        
        SaleOrder saleOrder = new SaleOrder("Fest Malhas 2015", estoqueMP, new GregorianCalendar(), new GregorianCalendar(2016, Calendar.JANUARY, 1), estoqueMP.getAgent(), renner.getAgent());
        saleOrder.getItems().add(new OrderItem(blusa1p, 30, uni));
        saleOrder.order();
        System.out.println("---------------- Purchase Orders ------------------");
        List<PurchaseOrder> purchaseOrders = saleOrder.getPurchaseOrders();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            System.out.println(purchaseOrder);
            globalInventory.add(purchaseOrder);
        }
        System.out.println("---------------- Manufacturing Orders ------------------");
        List<ManufacturingOrder> manufacturingOrders =  saleOrder.getManufacturingOrders();
        for (ManufacturingOrder manufacturingOrder : manufacturingOrders) {
            System.out.println(manufacturingOrder);
            globalInventory.add(manufacturingOrder);
        }
        
        
        
        /*
        Movement movement2 = new FromSectorToSector(adrianohrl, estoqueMP, estoqueWIP, new GregorianCalendar());
        movement2.add(new InventoryItem(fios.get(0), 50, 0, g));
        movement2.add(new InventoryItem(fios.get(1), 0, 75, g));
        movement2.add(new InventoryItem(fios.get(3), 100, 150, g));
        if (movement2.move()) {
            globalInventory.add(movement2);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (2)!!!");
        }
        
        Movement movement3 = new FromSectorToSector(adrianohrl, estoqueWIP, estoquePA, new GregorianCalendar());
        movement3.add(new InventoryItem(fios.get(0), 50, 0, g));
        movement3.add(new InventoryItem(fios.get(1), 0, 70, g));
        if (movement3.move()) {
            globalInventory.add(movement3);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (3)!!!");
        }
        
        Movement movement4 = new FromSectorToSector(adrianohrl, estoqueMP, estoqueMP, new GregorianCalendar());
        movement4.add(new InventoryItem(fios.get(2), 200, 0, g));
        if (movement4.move()) {
            globalInventory.add(movement4);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (4)!!!");
        }
        
        InsideTheSector movement5 = new InsideTheSector(adrianohrl, new SaleOrder(), estoqueMP);
        movement5.add(new InventoryItem(fios.get(2), 150, 0, g));
        if (movement5.bound()) {
            globalInventory.add(movement5);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (5)!!!");
        }
        
        InsideTheSector movement6 = new InsideTheSector(adrianohrl, new SaleOrder(), estoqueMP);
        movement6.add(new InventoryItem(fios.get(2), 0, 100, g));
        if (movement6.unbound()) {
            globalInventory.add(movement6);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (6)!!!");
        }
        
        Movement movement7 = new FromSupplierToSector(adrianohrl, fiosAmparo, estoqueMP, new GregorianCalendar());
        movement7.add(new InventoryItem(fios.get(0), 100 * 100, 0, g));
        movement7.add(new InventoryItem(fios.get(1), 100 * 200, 0, g));
        if (movement7.move()) {
            globalInventory.add(movement7);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (7)!!!");
        }
        
        InsideTheSector movement8 = new InsideTheSector(adrianohrl, new ManufacturingOrder(), estoqueMP);
        movement8.add(new InventoryItem(blusa1Frente, 100, 0, p));
        if (movement8.transform()) {
            globalInventory.add(movement8);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (8)!!!");
        }*/
        
        System.out.println("\n----------- Filial Inventory ----------");
        System.out.println(globalInventory);        
        
        
        /*
        AbstractOrder order = new SaleOrder("Fest Malhas 2015", new GregorianCalendar(), new GregorianCalendar(2016, Calendar.JANUARY, 1), estoquePA, renner);
        */
        /*
        AbstractOrder purchase1 = new PurchaseOrder("Fest Malhas 2015", new GregorianCalendar(), new GregorianCalendar(2016, Calendar.JANUARY, 1), fiosAmparo, estoqueMP);
        purchase1.insert(fios.get(0), 200, g);
        purchase1.insert(fios.get(1), 250, g);
        purchase1.insert(fios.get(2), 100, g);
        purchase1.insert(fios.get(3), 300, g);
        
        Delivery purchase1delivery1 = new Delivery();
        purchase1delivery1.insert(fios.get(0), 100, g);
        purchase1delivery1.insert(fios.get(1), 100, g);
        purchase1.deliver(purchase1delivery1);
        
        Delivery purchase1delivery2 = new Delivery();
        purchase1delivery2.insert(fios.get(0), 50, g);
        purchase1delivery2.insert(fios.get(1), 150, g);
        purchase1delivery2.insert(fios.get(3), 300, g);
        purchase1.deliver(purchase1delivery2);
        
        Devolution purchase1devolution1 = new Devolution();
        purchase1devolution1.insert(fios.get(0), 75, g);
        purchase1.devolve(purchase1devolution1);
        
        Cancellation purchase1cancellation1 = new Cancellation();
        purchase1cancellation1.insert(fios.get(0), 125, g);
        purchase1cancellation1.insert(fios.get(2), 100, g);
        purchase1.cancel(purchase1cancellation1);
        
        System.out.println("\n--------- Orders ---------");
        System.out.println(purchase1);
        */
    }
    
}
