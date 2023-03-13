package dev.prithvis.shell.components;

import dev.prithvis.shell.models.Centre;
import dev.prithvis.shell.models.CustomerSatellite;
import dev.prithvis.shell.models.Launcher;
import dev.prithvis.shell.models.SpaceCraft;
import dev.prithvis.shell.utils.IsroUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class IsroCommands {
    @Autowired
    IsroUtil isroUtil;
    @ShellMethod(key = "isro",
            value = "displays a hello message",
            group = "greetings")
    public String helloMessage(
    ) {
        return "Hello to isro api utility";
    }

    @ShellMethod(key = "spacecraft-list",
            value = "displays list of space crafts launched by isro",
            group = "spacecraft")
    public List<SpaceCraft> getSpaceCrafts(){
        return isroUtil.getSpaceCrafts();
    }

    @ShellMethod(key = "spacecraft-by-id",
            value = "Gets detail for a spacecraft by id",
            group = "spacecraft")
    public SpaceCraft getSpaceCraftById(@ShellOption(help = "id as number") Long id){
        if(id < 1){
            String message=String.format("Invalid id for a space-craft %d",id);
            throw new IllegalArgumentException(message);
        }
        SpaceCraft spaceCraft=isroUtil.getSpaceCraftById(id);
        if(spaceCraft==null){
            String message=String.format("No spacecraft was found by id %d",id);
            throw new EntityNotFoundException(message);
        }
        return spaceCraft;
    }

    @ShellMethod(key = "search-spacecraft",
            value = "search for a spacecraft by name",
            group = "spacecraft")
    public List<SpaceCraft> searchSpaceCraft(@ShellOption(help = "name for spacecraft") String spacecraft){
        if(spacecraft.isBlank()){
            throw new IllegalArgumentException("Please provide a name to search");
        }
        return isroUtil.getSimilarSpaceCrafts(spacecraft);
    }

    @ShellMethod(key = "launchers",
            value = "displays different launchers used by ISRO",
            group = "launchers")
    public List<Launcher> getLaunchers(){
        return isroUtil.getLaunchers();
    }

    @ShellMethod(key = "customer-satellites",
            value = "displays customer satellites launched by isro",
            group = "customer-satellites")
    public List<CustomerSatellite> getCustomerSatellites(){
        return isroUtil.getCustomerSatellites();
    }

    @ShellMethod(key = "launch-centres",
            value = "displays different launch centres of ISRO",
            group = "centres")
    public List<Centre> getLaunchCentres(){
        return isroUtil.getCentres();
    }
}
