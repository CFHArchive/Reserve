package net.tnemc.core.economy.response;


/**
 * Created by creatorfromhell on 8/9/2017.
 *
 * Reserve API
 *
 * Copyright (C) 2021 creatorfromhell
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA, or see
 * it at <https://www.gnu.org/licenses/lgpl-3.0.txt>.
 **/
public enum AccountResponse implements EconomyResponse {

  /**
   * The action was successfully completed, and during it an account was created.
   */
  CREATED {
    @Override
    public boolean success() {
      return true;
    }

    @Override
    public String response() {
      return "The specified account was created successfully.";
    }
  },

  /**
   * The action was successfully completed, and during it an account was created.
   */
  CREATION_FAILED {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account couldn't be created.";
    }
  },

  /**
   * The account that was attempted to be created already exists.
   */
  ALREADY_EXISTS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account already exists.";
    }
  },

  /**
   * The account specified during a call doesn't exist.
   */
  DOESNT_EXIST {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account doesn't exist.";
    }
  }

}