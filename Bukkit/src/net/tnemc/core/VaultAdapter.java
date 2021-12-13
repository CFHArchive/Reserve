package net.tnemc.core;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.tnemc.core.economy.strict.EconomyAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VaultAdapter implements Economy {

  private final EconomyAdapter api;
  private final String world;

  public VaultAdapter(EconomyAdapter api) {
    this.api = api;
    this.world = Reserve.instance().defaultWorld;
  }

  @Override
  public boolean isEnabled() {
    return api.vault();
  }

  @Override
  public String getName() {
    return api.name();
  }

  @Override
  public boolean hasBankSupport() {
    return true;
  }

  @Override
  public int fractionalDigits() {
    return 2;
  }

  @Override
  public String format(double amount) {
    return api.getDefaultCurrency().format(new BigDecimal(amount));
  }

  @Override
  public String currencyNamePlural() {
    return api.getDefaultCurrency().displayPlural();
  }

  @Override
  public String currencyNameSingular() {
    return api.getDefaultCurrency().display();
  }

  @Override
  public boolean hasAccount(String username) {
    return api.hasAccount(username);
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer) {
    return api.hasAccount(offlinePlayer.getUniqueId());
  }

  @Override
  public boolean hasAccount(String username, String world) {
    return api.hasAccount(username);
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer, String world) {
    return api.hasAccount(offlinePlayer.getUniqueId());
  }

  @Override
  public double getBalance(String username) {
    return api.getOrCreateAccount(username).holdings(world).doubleValue();
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer) {
    return api.getOrCreateAccount(offlinePlayer.getUniqueId()).holdings(world).doubleValue();
  }

  @Override
  public double getBalance(String username, String world) {
    return api.getOrCreateAccount(username).holdings(world).doubleValue();
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer, String world) {
    return api.getOrCreateAccount(offlinePlayer.getUniqueId()).holdings(world).doubleValue();
  }

  @Override
  public boolean has(String username, double amount) {
    return api.getOrCreateAccount(username).has(world, new BigDecimal(amount));
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, double amount) {
    return api.getOrCreateAccount(offlinePlayer.getUniqueId()).has(world, new BigDecimal(amount));
  }

  @Override
  public boolean has(String username, String world, double amount) {
    return api.getOrCreateAccount(username).has(world, new BigDecimal(amount));
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, String world, double amount) {
    return api.getOrCreateAccount(offlinePlayer.getUniqueId()).has(world, new BigDecimal(amount));
  }

  @Override
  public EconomyResponse withdrawPlayer(String username, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse withdrawPlayer(String username, String world, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse depositPlayer(String username, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse depositPlayer(String username, String world, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  public EconomyResponse depositPlayer(final UUID id, String world, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse createBank(String name, String world) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse createBank(String name, OfflinePlayer offlinePlayer) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse deleteBank(String name) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse bankBalance(String name) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse bankHas(String name, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse bankWithdraw(String name, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse bankDeposit(String name, double amount) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse isBankOwner(String name, String username) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse isBankOwner(String name, OfflinePlayer offlinePlayer) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse isBankMember(String name, String username) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public EconomyResponse isBankMember(String name, OfflinePlayer offlinePlayer) {
    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks not supported yet!");
  }

  @Override
  public List<String> getBanks() {
    return new ArrayList<>();
  }

  @Override
  public boolean createPlayerAccount(String username) {
    return api.createAccount(username);
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
    return api.createAccount(offlinePlayer.getUniqueId(), offlinePlayer.getName());
  }

  @Override
  public boolean createPlayerAccount(String username, String world) {
    return api.createAccount(username);
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String world) {
    return api.createAccount(offlinePlayer.getUniqueId(), offlinePlayer.getName());
  }
}