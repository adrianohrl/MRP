
import inventory.*;
import inventory.agents.*;
import inventory.movements.*;
import inventory.mrp.tree.*;
import inventory.orders.*;
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
        Unit kg = new Unit("kg", "Kilogramas", true);
        Unit h = new Unit("h", "Horas", true);
        Unit semanas = new Unit("sem", "Semanas", true);
        Unit unidades = new Unit("un", "Unidades", false);
        Unit p = new Unit("%", "Porcentagem", true);
        
        /** MPs */
        List<GlobalInventoryItem> fios = new ArrayList<>();
        fios.add(new GlobalInventoryItem("Linha 2/28 Branca", 0, g, 4, semanas));
        fios.add(new GlobalInventoryItem("Linha 2/28 Azul", 0, g, 3, semanas));
        fios.add(new GlobalInventoryItem("Linha 2/28 Azul Escura", 0, g, 2.5, semanas));
        GlobalInventoryItem ziper25 = new GlobalInventoryItem("Zíper 25cm Azul", 0, unidades, 1.5, semanas);
        GlobalInventoryItem micanga = new GlobalInventoryItem("Miçanga", 0, unidades, 2.5, semanas);
        //GlobalInventoryItem ziper35 = new GlobalInventoryItem("Zíper Azul (35 cm)", 0, unidades, 2, semanas);
        //GlobalInventoryItem ziper50 = new GlobalInventoryItem("Zíper Azul (50 cm)", 0, unidades, 2, semanas);
        
        //ziper25.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.NOVEMBER, 20), 7, uni));
        //ziper25.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 2), 6, uni));
        //ziper35.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 5), 10, uni));
        //ziper50.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.OCTOBER, 15), 4, uni));
        //micanga.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.SEPTEMBER, 10), 500, uni));
        //micanga.addScheduledReceipt(new ScheduledReceipt(new GregorianCalendar(2015, Calendar.DECEMBER, 1), 500, uni));
        
        /** WIP */
        GlobalInventoryItem frente = new GlobalInventoryItem("Frente", 0, unidades, 4, semanas);   
        ProductModule frenteTree = new ProductModule(frente, 1, unidades);
        frenteTree.addChild(new ProductItem(fios.get(0), 250, g));
        frenteTree.addChild(new ProductItem(fios.get(1), 250, g));
        GlobalInventoryItem costas = new GlobalInventoryItem("Costas", 0, unidades, 2.5, semanas); 
        ProductModule costasTree = new ProductModule(costas, 1, unidades);
        costasTree.addChild(new ProductItem(fios.get(0), 250, g));
        costasTree.addChild(new ProductItem(fios.get(2), 250, g));
        GlobalInventoryItem manga = new GlobalInventoryItem("Manga", 0, unidades, 2, semanas); 
        ProductModule mangaTree = new ProductModule(manga, 2, unidades);
        mangaTree.addChild(new ProductItem(fios.get(0), 100, g));          
        
        /** PFs */
        GlobalInventoryItem blusaAzulP = new GlobalInventoryItem("Blusa Azul P", 0, unidades, 2, semanas);
        Product blusa1pTree = new Product(blusaAzulP);
        blusa1pTree.addChild(frenteTree);
        blusa1pTree.addChild(costasTree);
        blusa1pTree.addChild(mangaTree);
        blusa1pTree.addChild(new ProductItem(micanga, 10, unidades));
        blusa1pTree.addChild(new ProductItem(ziper25, 1, unidades));
        
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
        globalInventory.addAllItems(fios); globalInventory.add(micanga); globalInventory.add(ziper25); //globalInventory.add(ziper35); globalInventory.add(ziper50); globalInventory.add(micanga);
        globalInventory.add(frente); globalInventory.add(costas); globalInventory.add(manga);
        globalInventory.add(blusaAzulP); //globalInventory.add(blusa1m); globalInventory.add(blusa1g);
        
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
        movement1.add(new InventoryItem(blusaAzulP, 10, 0, unidades));
        movement1.add(new InventoryItem(frente, 20, 0, unidades));
        movement1.add(new InventoryItem(ziper25, 30, 0, unidades));
        movement1.add(new InventoryItem(manga, 35, 0, unidades));
        movement1.add(new InventoryItem(micanga, 75, 0, unidades));
        movement1.add(new InventoryItem(costas, 15, 0, unidades));
        movement1.add(new InventoryItem(fios.get(0), 15e3, 0, g));
        movement1.add(new InventoryItem(fios.get(1), 5e3, 0, g));
        movement1.add(new InventoryItem(fios.get(2), 2e3, 0, g));
        if (movement1.move()) {
            globalInventory.add(movement1);
        } else {
            System.out.println("ERROR AO REALIZAR MOVIMENTO NO ESTOQUE (1)!!!");
        }
        
        System.out.println("\n----------- Starting Filial Inventory ----------");
        System.out.println(globalInventory);
        
        SaleOrder saleOrder = new SaleOrder("Fest Malhas 2015", estoqueMP, new GregorianCalendar(), new GregorianCalendar(2016, Calendar.MAY, 1), estoqueMP.getAgent(), renner.getAgent());
        saleOrder.getItems().add(new OrderItem(blusaAzulP, 100, unidades));
        saleOrder.order();
        System.out.println("---------------- Purchase Orders ------------------");
        List<PurchaseOrder> purchaseOrders = saleOrder.getPurchaseOrders();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            System.out.println(purchaseOrder);
           // globalInventory.add(purchaseOrder);
        }
        System.out.println("---------------- Manufacturing Orders ------------------");
        List<ManufacturingOrder> manufacturingOrders =  saleOrder.getManufacturingOrders();
        for (ManufacturingOrder manufacturingOrder : manufacturingOrders) {
            System.out.println(manufacturingOrder);
         //   globalInventory.add(manufacturingOrder);
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
