package de.pqtriick.economy.api;


/**
 * @author pqtriick_
 * @created 00:15, 05.09.2023
 */

public class EconomyAPI {

    private static IEconomyAPI api;

    public static IEconomyAPI getApi() {
        return api;
    }

    public static void setApi(IEconomyAPI api) {
        EconomyAPI.api = api;
    }
}
