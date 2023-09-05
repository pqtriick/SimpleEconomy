package de.pqtriick.economy.util.Skull;

/**
 * @author pqtriick_
 * @created 00:33, 05.09.2023
 */

public enum Skulls {

    ATM_INPUT("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmY2Yjg1ZjYyNjQ0NGRiZDViZGRmN2E1MjFmZTUyNzQ4ZmU0MzU2NGUwM2ZiZDM1YjZiNWU3OTdkZTk0MmQifX19"),
    ATM_OUTPUT("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk0MzI2YjUzOGVkOTg5ODcxMGQ1OGU1NTI0NzI2ZjMxMzAzNzM0NDgzZDFlNTIzN2VlMzI1YThiYmU1MjE3In19fQ=="),
    ATM_TRANSFER("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQxOTQxZTdlN2U5MTRhMTE1YzM0MmQ2ZDM4YTIyOTMxZTEzOGIzZGExZWViNGU5OTg1NzFlOTBmODcxNTE3In19fQ=="),
    ATM_INFO("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFmMDdkMWI5Mjk3MTQxYjMwODNmYzhkMTU2OTgxYzc4ZGQ2ZGFlY2VjNTI3NGViMGQ4N2UzYThiZGU5YmY4In19fQ==");

    private final String idTag;
    private Skulls(String texture) {
        this.idTag = texture;
    }

    public String getTexture() {
        return idTag;

    }
}
