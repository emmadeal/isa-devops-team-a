package fr.univcotedazur.multifidelity.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE) //Protected access
public class RouteConstants {

    public static class ConsumerRoutes {
        public static final String BASE_CONSUMER_ROUTE = "/consumers";
    }


    public static class ShowCasesRoutes {
        public static final String BASE_SHOW_CASES_ROUTE = "/show-cases/societies";
    }

    public static class PartnerRoutes {
        public static final String BASE_PARTNER_ROUTE = "/partners";
    }

    public static class CardRoutes {
        public static final String BASE_CARD_ROUTE = "/purchases";
    }

    public static class MayorRoutes {
        public static final String BASE_MAYOR_ROUTE = "/mayors";
    }

    public static class CounterCollectRoutes {
        public static final String BASE_COLLECT_ROUTE = "/collect-gift";
    }

    public static class StatisticService {
        public static final String BASE_STATISTICS_ROUTE = "/statistics";
    }
}
