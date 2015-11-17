/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dao.DataSource;
import dao.inventory.GlobalInventoryDAO;
import dao.inventory.GlobalInventoryItemDAO;
import dao.inventory.InventoryDAO;
import dao.inventory.SectorInventoryDAO;
import dao.inventory.agents.*;
import dao.inventory.movements.FromSupplierToSectorDAO;
import dao.inventory.movements.MovementDAO;
import dao.inventory.mrp.tree.MRPTreeNodeDAO;
import dao.inventory.mrp.tree.ProductDAO;
import dao.inventory.mrp.tree.ProductModuleDAO;
import dao.inventory.orders.SaleOrderDAO;
import dao.inventory.util.UnitDAO;
import inventory.*;
import inventory.agents.*;
import inventory.movements.FromSupplierToSector;
import inventory.movements.Movement;
import inventory.mrp.tree.Product;
import inventory.mrp.tree.ProductItem;
import inventory.mrp.tree.ProductModule;
import inventory.orders.OrderItem;
import inventory.orders.SaleOrder;
import inventory.util.Unit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class Registration {
    
    private static boolean hasAlreadyRegisteredBefore = false;
    
    public static void main(String[] args) {
        EntityManager em = DataSource.createEntityManager();
        Registration.hasAlreadyRegisteredBefore = Registration.registerUnits(em);
        Registration.hasAlreadyRegisteredBefore = Registration.registerAgents(em);
        if (!Registration.hasAlreadyRegisteredBefore) {
            Registration.registerInventories(em);
        }
        Registration.hasAlreadyRegisteredBefore = Registration.registerInventoryItems(em);
        if (!Registration.hasAlreadyRegisteredBefore) {
            Registration.registerInventoryInitialState(em);
        }
        Registration.registerOrders(em);
        GlobalInventoryDAO globalDAO = new GlobalInventoryDAO(em);
        System.out.println(globalDAO.find("Fábrica em Jacutinga-MG"));
        em.close();
        DataSource.closeEntityManagerFactory();
    }
    
    public static boolean registerUnits(EntityManager em) {
        boolean hasRegistered = false;
        UnitDAO unitDAO = new UnitDAO(em);
        HashMap<String, Unit> units = Unit.getUnits();
        for (Unit value : units.values()) {
            if (!unitDAO.isRegistered(value)) {
                unitDAO.create(value);
            } else {
                 hasRegistered = true;
            }
        }
        return hasRegistered;
    }
    
    public static boolean registerAgents(EntityManager em) {
        boolean hasRegistered = false;
        AgentDAO agentDAO = new SupplierDAO(em);
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier("Fios Amparo LTDA"));
        suppliers.add(new Supplier("Fides Fios LTDA"));
        suppliers.add(new Supplier("Fios Sartori LTDA"));
        suppliers.add(new Supplier("Fiosul LTDA"));
        suppliers.add(new Supplier("Armarinhos LTDA"));
        for (Supplier supplier : suppliers) {
            if (!agentDAO.isRegistered(supplier)) {
                agentDAO.create(supplier);
            } else {
                 hasRegistered = true;
            }
        }
        agentDAO = new ResponsibleDAO(em);
        List<Responsible> responsibles = new ArrayList<>();
        responsibles.add(new Responsible("Adriano Henrique Rossette Leite"));
        responsibles.add(new Responsible("Fulano de Tal"));
        for (Responsible responsible : responsibles) {
            if (!agentDAO.isRegistered(responsible)) {
                agentDAO.create(responsible);
            } else {
                 hasRegistered = true;
            }
        }
        agentDAO = new ClientDAO(em);
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Renner"));
        clients.add(new Client("C&A"));
        clients.add(new Client("Marisa"));
        for (Client client : clients) {
            if (!agentDAO.isRegistered(client)) {
                agentDAO.create(client);
            } else {
                 hasRegistered = true;
            }
        }
        agentDAO = new SectorDAO(em);
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("Estoque de Matéria-Prima"));
        sectors.add(new Sector("Estoque de Tecidos"));
        sectors.add(new Sector("Estoque de Produtos Acabados"));
        sectors.add(new Sector("Corte"));
        sectors.add(new Sector("Costura"));
        sectors.add(new Sector("Passadoria"));
        sectors.add(new Sector("Tecimento"));
        sectors.add(new Sector("Desenvolvimento"));
        sectors.add(new Sector("Arremate"));
        for (Sector sector : sectors) {
            if (!agentDAO.isRegistered(sector)) {
                agentDAO.create(sector);
            } else {
                 hasRegistered = true;
            }
        }
        agentDAO = new FilialDAO(em);
        List<Filial> filials = new ArrayList<>();
        filials.add(new Filial("Fábrica em Jacutinga-MG"));
        filials.add(new Filial("Fábrica em Itapira-SP"));
        filials.add(new Filial("Loja no JK"));
        filials.add(new Filial("Loja no Higienópolis"));
        filials.add(new Filial("Loja em Jacutinga"));
        for (Filial filial : filials) {
            if (!agentDAO.isRegistered(filial)) {
                agentDAO.create(filial);
            } else {
                 hasRegistered = true;
            }
        }
        return hasRegistered;
    }
    
    public static void registerInventories(EntityManager em) {
        GlobalInventoryDAO globalDAO = new GlobalInventoryDAO(em);
        GlobalInventory globalInventory = new GlobalInventory(new Filial("Fábrica em Jacutinga-MG"));
        globalDAO.create(globalInventory);
        InventoryDAO inventoryDAO = new SectorInventoryDAO(em);
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("Estoque de Matéria-Prima"));
        sectors.add(new Sector("Estoque de Tecidos"));
        sectors.add(new Sector("Estoque de Produtos Acabados"));
        for (Sector sector : sectors) {
            SectorInventory inventory = new SectorInventory(globalInventory, sector);
            globalInventory.add(inventory);
            inventoryDAO.create(inventory);
        }
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Renner"));
        clients.add(new Client("C&A"));
        clients.add(new Client("Marisa"));
        for (Client client : clients) {
            ClientInventory inventory = new ClientInventory(globalInventory, client);
            globalInventory.add(inventory);
            inventoryDAO.create(inventory);
        } 
    }
    
    public static boolean registerInventoryItems(EntityManager em) {
        boolean hasRegistered = false;
        Unit g = new Unit("g", "Gramas", true);
        Unit semanas = new Unit("sem", "Semanas", true);
        Unit unidades = new Unit("un", "Unidades", false);
        
        List<GlobalInventoryItem> fios = new ArrayList<>();
        fios.add(new GlobalInventoryItem("Linha 2/28 Branca", 0, g, 4, semanas));
        fios.add(new GlobalInventoryItem("Linha 2/28 Azul", 0, g, 3, semanas));
        fios.add(new GlobalInventoryItem("Linha 2/28 Azul Escura", 0, g, 2.5, semanas));
        GlobalInventoryItem ziper25 = new GlobalInventoryItem("Zíper 25cm Azul", 0, unidades, 1.5, semanas);
        GlobalInventoryItem micanga = new GlobalInventoryItem("Miçanga", 0, unidades, 2.5, semanas);
        GlobalInventoryItem ziper35 = new GlobalInventoryItem("Zíper Azul (35 cm)", 0, unidades, 2, semanas);
        GlobalInventoryItem ziper50 = new GlobalInventoryItem("Zíper Azul (50 cm)", 0, unidades, 2, semanas);
        GlobalInventoryItem frente = new GlobalInventoryItem("Frente", 0, unidades, 4, semanas);  
        GlobalInventoryItem costas = new GlobalInventoryItem("Costas", 0, unidades, 2.5, semanas); 
        GlobalInventoryItem manga = new GlobalInventoryItem("Manga", 0, unidades, 2, semanas);
        GlobalInventoryItem blusaAzulP = new GlobalInventoryItem("Blusa Azul P", 0, unidades, 2, semanas);
        
        GlobalInventoryDAO globalDAO = new GlobalInventoryDAO(em);
        GlobalInventory globalInventory = globalDAO.find("Fábrica em Jacutinga-MG");
        GlobalInventoryItemDAO globalItemDAO = new GlobalInventoryItemDAO(em);
        List<GlobalInventoryItem> items = new ArrayList<>();
        items.addAll(fios); items.add(ziper25); items.add(ziper35); items.add(ziper50); items.add(micanga);
        items.add(frente); items.add(manga); items.add(costas);
        items.add(blusaAzulP);
        for (GlobalInventoryItem item : items) {
            if (!globalItemDAO.isRegistered(item)) {
                globalInventory.add(item);
                globalItemDAO.create(item);
            } else {
                hasRegistered = true;
            }
        }
        
        MRPTreeNodeDAO nodeDAO = new ProductModuleDAO(em); 
        ProductModule frenteTree = new ProductModule(frente, 1, unidades);
        frenteTree.addChild(new ProductItem(fios.get(0), 250, g));
        frenteTree.addChild(new ProductItem(fios.get(1), 250, g));
        if (!hasRegistered) {
            nodeDAO.create(frenteTree);
        }
        ProductModule costasTree = new ProductModule(costas, 1, unidades);
        costasTree.addChild(new ProductItem(fios.get(0), 250, g));
        costasTree.addChild(new ProductItem(fios.get(2), 250, g));
        if (!hasRegistered) {
            nodeDAO.create(costasTree); 
        }
        ProductModule mangaTree = new ProductModule(manga, 2, unidades);
        mangaTree.addChild(new ProductItem(fios.get(0), 100, g));       
        if (!hasRegistered) {
            nodeDAO.create(mangaTree);
        }
        
        /** PFs */
        nodeDAO = new ProductDAO(em);
        Product blusaAzulPTree = new Product(blusaAzulP);
        blusaAzulPTree.addChild(frenteTree);
        blusaAzulPTree.addChild(costasTree);
        blusaAzulPTree.addChild(mangaTree);
        blusaAzulPTree.addChild(new ProductItem(micanga, 10, unidades));
        blusaAzulPTree.addChild(new ProductItem(ziper25, 1, unidades));
        if (!hasRegistered) {
            nodeDAO.create(blusaAzulPTree);
        }
        
        return hasRegistered;
    }
    
    public static void registerInventoryInitialState(EntityManager em) {
        GlobalInventoryDAO globalDAO = new GlobalInventoryDAO(em);
        GlobalInventory globalInventory = globalDAO.find("Fábrica em Jacutinga-MG");
        SectorInventoryDAO sectorInventoryDAO = new SectorInventoryDAO(em);
        SectorInventory estoqueMP = sectorInventoryDAO.find("Estoque de Matéria-Prima");
        ResponsibleDAO responsibleDAO = new ResponsibleDAO(em);
        Responsible adrianohrl = responsibleDAO.find("Adriano Henrique Rossette Leite");
        SupplierDAO supplierDAO = new SupplierDAO(em);
        Supplier fiosAmparo = supplierDAO.find("Fios Amparo LTDA");
        Unit g = new Unit("g", "Gramas", true);
        Unit unidades = new Unit("un", "Unidades", false);
        Movement movement = new FromSupplierToSector(adrianohrl, fiosAmparo, estoqueMP, new GregorianCalendar());
        GlobalInventoryItemDAO globalItemDAO = new GlobalInventoryItemDAO(em);
        movement.add(new InventoryItem(globalItemDAO.find("Blusa Azul P"), 10, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Frente"), 20, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Zíper 25cm Azul"), 30, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Manga"), 35, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Miçanga"), 75, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Costas"), 15, 0, unidades));
        movement.add(new InventoryItem(globalItemDAO.find("Linha 2/28 Branca"), 15e3, 0, g));
        movement.add(new InventoryItem(globalItemDAO.find("Linha 2/28 Azul"), 5e3, 0, g));
        movement.add(new InventoryItem(globalItemDAO.find("Linha 2/28 Azul Escura"), 2e3, 0, g));
        if (movement.move()) {
            globalInventory.add(movement);
            MovementDAO movementDAO = new FromSupplierToSectorDAO(em);
            movementDAO.create(movement);
        } else {
            System.out.println("Error while moving materials to global inventory at the first time!!!");
        }
    }
    
    public static void registerOrders(EntityManager em) {
        GlobalInventoryItemDAO globalItemDAO = new GlobalInventoryItemDAO(em);
        GlobalInventoryItem blusaAzulP = globalItemDAO.find("Blusa Azul P");
        SectorInventoryDAO sectorInventoryDAO = new SectorInventoryDAO(em);
        SectorInventory estoqueMP = sectorInventoryDAO.find("Estoque de Matéria-Prima");
        ClientDAO clientDAO = new ClientDAO(em);
        Client renner = clientDAO.find("Renner");
        Unit unidades = new Unit("un", "Unidades", false);
        SaleOrderDAO saleOrderDAO = new SaleOrderDAO(em);
        SaleOrder saleOrder = new SaleOrder("Fest Malhas 2015", estoqueMP, new GregorianCalendar(), new GregorianCalendar(2016, Calendar.MAY, 1), estoqueMP.getAgent(), renner);
        saleOrder.getItems().add(new OrderItem(blusaAzulP, 100, unidades));
        saleOrder.order();
        saleOrderDAO.create(saleOrder);
    }
    
}
