/* Copyright 2020 lantisog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mxzx.model.nondb;

import com.google.common.collect.Maps;
import de.mxzx.model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Quest {

    protected final String name;
    protected final LinkedHashMap<String, String> rewards;
    protected LinkedHashMap<QuestItem, Map.Entry<Integer, Integer>> questItems;
    protected ClanQuest.QuestState state = QuestState.NONE;

    protected long time = 60 * 30;
    protected long timeRemaining;

    public Quest(String name, QuestItem questItem, int amount) {
        this.name = name;
        this.rewards = Maps.newLinkedHashMap();
        this.questItems = Maps.newLinkedHashMap();
        this.questItems.put(questItem, new AbstractMap.SimpleEntry<>(amount, 0));
    }

    public Quest(Quest quest) {
        this.name = quest.name;
        this.state = quest.state;
        this.rewards = quest.rewards;
        this.time = quest.time;
        this.timeRemaining = quest.timeRemaining;
        this.questItems = Maps.newLinkedHashMap();
        for (Map.Entry<QuestItem, Map.Entry<Integer, Integer>> e : quest.questItems.entrySet()) {
            Map.Entry<Integer, Integer> values = e.getValue();
            values.setValue(0);
            this.questItems.put(e.getKey(), new AbstractMap.SimpleEntry<>(values.getKey(), values.getValue()));
        }
    }
    public QuestState getState() {
        return state;
    }

    public enum QuestState {
        NONE,
        RUNNING,
        COMPLETED
    }

}
