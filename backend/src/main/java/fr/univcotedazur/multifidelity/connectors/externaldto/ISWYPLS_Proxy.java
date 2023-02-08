package fr.univcotedazur.multifidelity.connectors.externaldto;

import fr.univcotedazur.multifidelity.interfaces.IsawWhereYouParkedLastSummer;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ISWYPLS_Proxy implements IsawWhereYouParkedLastSummer {
    @Override
    public void startCounter(String licencePlaten, LocalTime start, LocalTime end) {
        // commence un compteur entre lesd deux heure pour le stationnement
    }


    // penser au notif
}
