package dev.prithvis.shell.utils;

import dev.prithvis.shell.dtos.response.CentresResponse;
import dev.prithvis.shell.dtos.response.CustomerSatellitesResponse;
import dev.prithvis.shell.dtos.response.LaunchersResponse;
import dev.prithvis.shell.dtos.response.SpaceCraftsResponse;
import dev.prithvis.shell.models.Centre;
import dev.prithvis.shell.models.CustomerSatellite;
import dev.prithvis.shell.models.Launcher;
import dev.prithvis.shell.models.SpaceCraft;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class IsroUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsroUtil.class);
    private static final String SPACECRAFT_URI = "https://isro.vercel.app/api/spacecrafts";
    private static final String LAUNCHER_URI = "https://isro.vercel.app/api/launchers";
    private static final String CUSTOMER_SATELLITE_URI="https://isro.vercel.app/api/customer_satellites";
    private static final String CENTRES_URI="https://isro.vercel.app/api/centres";


    /**
     * Uses ISRO apis to get details about isro spacecraft missions.
     *
     * @return spacecrafts found
     */
    public List<SpaceCraft> getSpaceCrafts() {
        LOGGER.debug("Requesting {} to provide spacecrafts list", SPACECRAFT_URI);
        Flux<SpaceCraftsResponse> spaceCraftsResponseFlux = WebClient.create().
                get().
                uri(SPACECRAFT_URI).
                retrieve().
                bodyToFlux(SpaceCraftsResponse.class);
        List<SpaceCraftsResponse> spaceCraftsResponses = spaceCraftsResponseFlux.collect(Collectors.toList())
                .share().
                block();
        List<SpaceCraft> spaceCrafts = null;
        if (spaceCraftsResponses != null && !spaceCraftsResponses.isEmpty()) {
            spaceCrafts = spaceCraftsResponses.get(0).getSpacecrafts();
        }
        LOGGER.debug("Found spacecrafts are {}", spaceCrafts);
        return spaceCrafts;
    }

    /**
     * Tries to find spacecraft by id
     *
     * @param id Id for spacecraft
     * @return SpaceCraft if found else null
     */
    public SpaceCraft getSpaceCraftById(Long id) {
        SpaceCraft spaceCraft = null;
        List<SpaceCraft> spaceCrafts = getSpaceCrafts();
        for (SpaceCraft craft : spaceCrafts) {
            if (craft.getId().equals(id)) {
                spaceCraft = craft;
                break;
            }
        }
        return spaceCraft;
    }

    /**
     * calculates how close search spacecraft name is to the list of spacecraft by
     * using LevenshteinDistance algorithm.
     *
     * @param spacecraft name for spacecraft
     * @return SpaceCrafts that are similar in name to the provided name
     */
    public List<SpaceCraft> getSimilarSpaceCrafts(String spacecraft) {

        int allowedLevenshteinDistance = Integer.parseInt(ResourceBundle.getBundle("application").
                getString("isroutil.allowedLevenshteinDistance"));

        LOGGER.debug("Currently allowed LevenshteinDistance is {}", allowedLevenshteinDistance);

        List<SpaceCraft> similarSpacecrafts = new LinkedList<>();
        LevenshteinDistance ld = new LevenshteinDistance();
        for (SpaceCraft craft : getSpaceCrafts()) {
            Integer distance = ld.apply(craft.getName(), spacecraft);
            LOGGER.debug("LevenshteinDistance for {} is {}", craft.getName(), distance);
            if (distance <= allowedLevenshteinDistance) {
                similarSpacecrafts.add(craft);
            }
        }
        LOGGER.debug("Found similar spacecrafts are {}", similarSpacecrafts);
        return similarSpacecrafts;
    }

    /**
     * Provides different launchers by ISRO
     * @return Launchers used by ISRO
     */
    public List<Launcher> getLaunchers(){
        LOGGER.debug("Requesting {} for launcher details",LAUNCHER_URI);
        Flux<LaunchersResponse> launcherFlux = WebClient.create().
                get().
                uri(LAUNCHER_URI).
                retrieve().
                bodyToFlux(LaunchersResponse.class);
        List<LaunchersResponse> launchersResponses=launcherFlux.collect(Collectors.toList()).
                share().
                block();
        List<Launcher> launchers=null;
        if(launchersResponses!=null && !launchersResponses.isEmpty()){
            launchers=launchersResponses.get(0).getLaunchers();
        }
        LOGGER.debug("Found launchers are {}",launchers);
        return launchers;
    }

    /**
     * Provides data for customer satellites by ISRO
     * @return customer satellites missions by ISRO
     */
    public List<CustomerSatellite> getCustomerSatellites(){
        LOGGER.debug("Requesting {} for customer satellite details",CUSTOMER_SATELLITE_URI);
        Flux<CustomerSatellitesResponse> customerSatellitesResponseFlux = WebClient.create().
                get().
                uri(CUSTOMER_SATELLITE_URI).
                retrieve().
                bodyToFlux(CustomerSatellitesResponse.class);
        List<CustomerSatellitesResponse> customerSatellitesResponses=customerSatellitesResponseFlux.collect(Collectors.toList()).
                share().
                block();
        List<CustomerSatellite> customerSatellites=null;
        if(customerSatellitesResponses!=null && !customerSatellitesResponses.isEmpty()){
            customerSatellites=customerSatellitesResponses.get(0).getCustomerSatellites();
        }
        LOGGER.debug("Found customer satellites are {}",customerSatellites);
        return customerSatellites;
    }

    public List<Centre> getCentres(){
        LOGGER.debug("Requesting {} for centres details",CENTRES_URI);
        Flux<CentresResponse> centresResponseFlux = WebClient.create().
                get().
                uri(CENTRES_URI).
                retrieve().
                bodyToFlux(CentresResponse.class);
        List<CentresResponse> centresResponses=centresResponseFlux.collect(Collectors.toList()).
                share().
                block();
        List<Centre> centres=null;
        if(centresResponses!=null && !centresResponses.isEmpty()){
            centres=centresResponses.get(0).getCentres();
        }
        LOGGER.debug("Found centres are {}",centres);
        return centres;
    }

}
