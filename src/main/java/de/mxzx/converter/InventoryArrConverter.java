package de.mxzx.converter;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.persistence.AttributeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InventoryArrConverter implements
        AttributeConverter<Inventory[], String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(Inventory[] e) {
        if (e == null) {
            return null;
        }
        return toBase64(e);
    }

    @SneakyThrows
    @Override
    public Inventory[] convertToEntityAttribute(String e) {
        if (e == null || e.isEmpty()) {
            return null;
        }

        return fromBase64(e);
    }


    private String toBase64(Inventory[] inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(inventory.length);
            for (Inventory inv : inventory) {
                // Write the size of the inventory
                dataOutput.writeInt(inv.getSize());

                // Save every element in the list
                for (int i = 0; i < inv.getSize(); i++) {
                    dataOutput.writeObject(inv.getItem(i));
                }
            }
            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }


    private Inventory[] fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            int length = dataInput.readInt();
            Inventory[] inventories = new Inventory[length];
            for (int i = 0; i < length; i++) {
                Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
                // Read the serialized inventory
                for (int j = 0; j < inventory.getSize(); j++) {
                    inventory.setItem(j, (ItemStack) dataInput.readObject());
                }
                inventories[i] = inventory;
            }

            dataInput.close();
            return inventories;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

}
