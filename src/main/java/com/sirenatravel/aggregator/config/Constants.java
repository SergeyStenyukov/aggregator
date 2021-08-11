package com.sirenatravel.aggregator.config;

public class Constants {

    private Constants() {
    }

    public static final class Companies {

        public static final String UBER = "uber";

        public static final String YANDEX = "yandex";

        public static final String CITY_MOBIL = "citymobil";
    }

    public static final class TaxiOrders {

        public static final String UBER_ORDER = "http://localhost:8080/uber/taxi/{start_location}/{end_location}";

        public static final String YANDEX_ORDER = "http://localhost:8080/yandex/taxi/{start_location}/{end_location}";

        public static final String CITY_MOBIL_ORDER = "http://localhost:8080/citymobil/taxi/{start_location}/{end_location}";
    }

    public static final class TaxiApprove {

        public static final String UBER_APPROVE = "http://localhost:8080/uber/taxi/approve/{uuid}";

        public static final String YANDEX_APPROVE = "http://localhost:8080/yandex/taxi/approve/{uuid}";

        public static final String CITY_MOBIL_APPROVE = "http://localhost:8080/citymobil/taxi/approve/{uuid}";
    }
}
