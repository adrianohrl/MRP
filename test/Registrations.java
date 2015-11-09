
import inventory.agents.Machine;
import inventory.agents.Sector;
import inventory.util.Unit;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrianohrl
 */
public class Registrations {
    
    public static List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit("l", "Volume em Litros", true));
        units.add(new Unit("m2", "Área em metros quadrados", true));
        units.add(new Unit("m", "Comprimento metros", true));
        units.add(new Unit("cm", "Centímetros", true));
        units.add(new Unit("uni.", "Unidades", false));
        units.add(new Unit("g", "Gramas", true));
        units.add(new Unit("kg", "Kilogramas", true));
        units.add(new Unit("dz.", "Dúzias", false));
        units.add(new Unit("dez.", "Dezenas", false));
        units.add(new Unit("cen.", "Centenas", false));
        units.add(new Unit("s", "Segundos", true));
        units.add(new Unit("min", "Minutos", true));
        units.add(new Unit("h", "Horas", true));
        return units;
    }
    
    public static List<Sector> getSectors() {
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("Estoque de Matéria Prima"));
        sectors.add(new Sector("Estoque de Produto Semi-acabado"));
        sectors.add(new Sector("Estoque de Produto Acabado"));
        sectors.add(new Sector("Corte"));
        sectors.add(new Sector("Arremate"));
        Sector costura = new Sector("Costura");
        costura.getMachines().add(new Machine("Overloque 1"));
        costura.getMachines().add(new Machine("Overloque 2"));
        costura.getMachines().add(new Machine("Overloque 3"));
        costura.getMachines().add(new Machine("Reta 1"));
        costura.getMachines().add(new Machine("Reta 2"));
        costura.getMachines().add(new Machine("Galoneira"));
        costura.getMachines().add(new Machine("Travete"));
        costura.getMachines().add(new Machine("Remalhadeira"));
        sectors.add(costura);
        Sector tecimento = new Sector("Tecimento");
        tecimento.getMachines().add(new Machine("Stoll 1"));
        tecimento.getMachines().add(new Machine("Stoll 2"));
        tecimento.getMachines().add(new Machine("Stoll 3"));
        tecimento.getMachines().add(new Machine("Shima 1"));
        tecimento.getMachines().add(new Machine("Shima 2"));
        tecimento.getMachines().add(new Machine("Coppo"));
        tecimento.getMachines().add(new Machine("PS"));
        sectors.add(tecimento);
        Sector passadoria = new Sector("Tecimento");
        tecimento.getMachines().add(new Machine("Mesa 1"));
        tecimento.getMachines().add(new Machine("Mesa 2"));
        sectors.add(passadoria);
        return sectors;
    }
    
}
