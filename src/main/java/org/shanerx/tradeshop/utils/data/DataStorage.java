/*
 *
 *                         Copyright (c) 2016-2019
 *                SparklingComet @ http://shanerx.org
 *               KillerOfPie @ http://killerofpie.github.io
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *                http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  NOTICE: All modifications made by others to the source code belong
 *  to the respective contributor. No contributor should be held liable for
 *  any damages of any kind, whether be material or moral, which were
 *  caused by their contribution(s) to the project. See the full License for more information.
 *
 */

package org.shanerx.tradeshop.utils.data;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.shanerx.tradeshop.enumys.DebugLevels;
import org.shanerx.tradeshop.objects.PlayerSetting;
import org.shanerx.tradeshop.objects.Shop;
import org.shanerx.tradeshop.objects.ShopChunk;
import org.shanerx.tradeshop.objects.ShopLocation;
import org.shanerx.tradeshop.utils.Utils;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class DataStorage extends Utils {

    private DataType dataType;

    public DataStorage(DataType dataType) {
        this.dataType = dataType;
        debugger.log("Data storage set to: " + dataType.name(), DebugLevels.DISABLED);
    }

    public Shop loadShopFromSign(ShopLocation sign) {
        switch (dataType) {
            case FLATFILE:
                return new JsonConfiguration(sign.getChunk()).loadShop(sign);
            case SQLITE:
                return null; //TODO add SQLITE support
        }
        return null;
    }

    public Shop loadShopFromStorage(ShopLocation chest) {
        switch (dataType) {
            case FLATFILE:
                return null; //TODO decide if/how we want to handle this since flatfile won't support chest lookup without some changes
            case SQLITE:
                return null; //TODO add SQLITE support
        }
        return null;
    }

    public void saveShop(Shop shop) {
        switch (dataType) {
            case FLATFILE:
                new JsonConfiguration(shop.getShopLocation().getChunk()).saveShop(shop);
                break;
            case SQLITE:
                //TODO add SQLITE support
                break;
        }
    }

    public void removeShop(Shop shop) {
        switch (dataType) {
            case FLATFILE:
                new JsonConfiguration(shop.getShopLocation().getChunk()).removeShop(shop.getShopLocationAsSL());
                break;
            case SQLITE:
                //TODO add SQLITE support
                break;
        }
    }

    public int getShopCountInChunk(Chunk chunk) {
        switch (dataType) {
            case FLATFILE:
                return new JsonConfiguration(chunk).getShopCount();
            case SQLITE:
                return 0; //TODO add SQLITE support
        }
        return 0;
    }

    public int getShopCountInWorld(World world) {
        int count = 0;
        switch (dataType) {
            case FLATFILE:
                for (File file : Objects.requireNonNull(new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Data" + File.separator + world.getName()).listFiles())) {
                    count += new JsonConfiguration(ShopChunk.deserialize(file.getName().replace(".json", ""))).getShopCount();
                }
                break;
            case SQLITE:
                //TODO add SQLITE support
                break;
        }
        return count;
    }

    public PlayerSetting loadPlayer(UUID uuid) {
        switch (dataType) {
            case FLATFILE:
                return new PlayerSetting(uuid, new JsonConfiguration(uuid).loadPlayer());
            case SQLITE:
                return null; //TODO add SQLITE support
        }
        return null;
    }

    public void savePlayer(PlayerSetting playerSetting) {
        switch (dataType) {
            case FLATFILE:
                new JsonConfiguration(playerSetting.getUuid()).savePlayer(playerSetting.getData());
                break;
            case SQLITE:
                //TODO add SQLITE support
                break;
        }
    }

    public void removePlayer(PlayerSetting playerSetting) {
        switch (dataType) {
            case FLATFILE:
                new JsonConfiguration(playerSetting.getUuid()).removePlayer();
                break;
            case SQLITE:
                //TODO add SQLITE support
                break;
        }
    }


}