package de.pqtriick.economy.api;

import java.util.UUID;

public interface IEconomyAPI {

    Integer getBankmoney(UUID uuid);

    Integer getLocalmoney(UUID uuid);

    void addBankmoney(UUID uuid, int amount);

    void addLocalmoney(UUID uuid, int amount);

    void removeBankmoney(UUID uuid, int amount);

    void removeLocalmoney(UUID uuid, int amount);

}
