/*
 *     Copyright (c) 2016-2017 SparklingComet @ http://shanerx.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * NOTICE: All modifications made by others to the source code belong
 * to the respective contributor. No contributor should be held liable for
 * any damages of any kind, whether be material or moral, which were
 * caused by their contribution(s) to the project. See the full License for more information
 */

package org.shanerx.tradeshop.objects;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.io.Serializable;

public class ShopChunk implements Serializable {

	private World world;
	private int x, z;
	private String div = "_";
	private Chunk chunk;

	public ShopChunk(World w, int x, int z) {
		this.world = w;
		this.x = x;
		this.z = z;
		chunk = world.getChunkAt(x, z);
	}

	public ShopChunk(Chunk c) {
		this.world = c.getWorld();
		this.x = c.getX();
		this.z = c.getZ();
		chunk = c;
	}

	public String serialize() {
		return "c" + div + world.getName() + div + x + div + z;
	}

	public Chunk deserialize(String loc) {
		if (loc.startsWith("c")) {
			String[] locA = loc.split(div);
			World world = Bukkit.getWorld(locA[1]);
			int x = Integer.parseInt(locA[2]), z = Integer.parseInt(locA[3]);

			return world.getChunkAt(x, z);
		}

		return null;
	}

	public World getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public Chunk getChunk() {
		return chunk;
	}
}