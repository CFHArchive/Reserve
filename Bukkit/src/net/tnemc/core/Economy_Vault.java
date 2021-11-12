package net.tnemc.core;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.tnemc.core.Reserve;
import net.tnemc.core.old.EconomyAPI;
import org.bukkit.OfflinePlayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by creatorfromhell on 8/9/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2021 creatorfromhell
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA, or see
 * it at <https://www.gnu.org/licenses/lgpl-3.0.txt>.
 **/
public class Economy_Vault implements Economy {

  private final Reserve plugin;
  private final EconomyAPI economyAPI;

  public Economy_Vault(Reserve plugin) {
    this.plugin = plugin;
    this.economyAPI = plugin.economy();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getName() {
    return "Reserve";
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
    return economyAPI.format(new BigDecimal(amount + ""), plugin.defaultWorld);
  }

  @Override
  public String currencyNamePlural() {
    return economyAPI.currencyDefaultPlural();
  }

  @Override
  public String currencyNameSingular() {
    return economyAPI.currencyDefaultSingular();
  }

  @Override
  public boolean hasAccount(String username) {
    return economyAPI.hasAccount(username);
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer) {
    return economyAPI.hasAccount(offlinePlayer.getUniqueId());
  }

  @Override
  public boolean hasAccount(String username, String world) {
    return economyAPI.hasAccount(username);
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer, String world) {
    return economyAPI.hasAccount(offlinePlayer.getUniqueId());
  }

  @Override
  public double getBalance(String username) {
    return getBalance(username, plugin.defaultWorld);
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer) {
    return getBalance(offlinePlayer.getName(), plugin.defaultWorld);
  }

  @Override
  public double getBalance(String username, String world) {
    return economyAPI.getHoldings(username, world).doubleValue();
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer, String world) {
    return getBalance(offlinePlayer.getName(), world);
  }

  @Override
  public boolean has(String username, double amount) {
    return has(username, plugin.defaultWorld, amount);
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, double amount) {
    return has(offlinePlayer.getName(), plugin.defaultWorld, amount);
  }

  @Override
  public boolean has(String username, String world, double amount) {
    return economyAPI.hasHoldings(username, new BigDecimal(amount + ""), world);
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, String world, double amount) {
    return has(offlinePlayer.getName(), world, amount);
  }

  @Override
  public EconomyResponse withdrawPlayer(String username, double amount) {
    return withdrawPlayer(username, plugin.defaultWorld, amount);
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
    return withdrawPlayer(offlinePlayer.getName(), plugin.defaultWorld, amount);
  }

  @Override
  public EconomyResponse withdrawPlayer(String username, String world, double amount) {
    if(!hasAccount(username)) {
      return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "That account does not exist!");
    }

    if(amount < 0) {
      return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative amounts.");
    }

    if(!has(username, world, amount)) {
      return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Insufficient funds!");
    }
    if(economyAPI.removeHoldings(username, new BigDecimal(amount + ""), world)) {
      return new EconomyResponse(amount, getBalance(username, world), EconomyResponse.ResponseType.SUCCESS, "");
    }
    return new EconomyResponse(amount, getBalance(username, world), EconomyResponse.ResponseType.FAILURE, "Unable to complete transaction!");
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
    return withdrawPlayer(offlinePlayer.getName(), world, amount);
  }

  @Override
  public EconomyResponse depositPlayer(String username, double amount) {
    return depositPlayer(username, plugin.defaultWorld, amount);
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
    return depositPlayer(offlinePlayer.getName(), plugin.defaultWorld, amount);
  }

  @Override
  public EconomyResponse depositPlayer(String username, String world, double amount) {
    if(!hasAccount(username)) {
      return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "That account does not exist!");
    }

    if(amount < 0) {
      return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative amounts.");
    }
    if(economyAPI.addHoldings(username, new BigDecimal(amount + ""), world)) {
      return new EconomyResponse(amount, getBalance(username, world), EconomyResponse.ResponseType.SUCCESS, "");
    }
    return new EconomyResponse(amount, getBalance(username, world), EconomyResponse.ResponseType.FAILURE, "Unable to complete transaction!");
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
    return depositPlayer(offlinePlayer.getName(), world, amount);
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
    return economyAPI.createAccount(username);
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
    return economyAPI.createAccount(offlinePlayer.getUniqueId());
  }

  @Override
  public boolean createPlayerAccount(String username, String world) {
    return economyAPI.createAccount(username);
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String world) {
    return economyAPI.createAccount(offlinePlayer.getUniqueId());
  }
}