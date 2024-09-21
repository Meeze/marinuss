package de.mxzx.converter;

import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.persistence.AttributeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ItemListConverter implements
        AttributeConverter<List<ItemStack>, String> {

    private static final String SEPARATOR = ", ";
    private static final String SEPARATOR2 = "| ";

    @Override
    public String convertToDatabaseColumn(List<ItemStack> location) {
        if (location == null) {
            return null;
        }
        return toBase64(location);
    }

    @SneakyThrows
    @Override
    public List<ItemStack> convertToEntityAttribute(String location) {
        if (location == null || location.isEmpty()) {
            return null;
        }

        return locFromBase64(location);
    }


    /**
     * A method to serialize an inventory to Base64 string.
     *
     * @param loc to serialize
     * @return Base64 string of the provided inventory
     * @throws IllegalStateException
     */
    public static String toBase64(List<ItemStack> loc) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(loc);

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static List<ItemStack> locFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            List<ItemStack> item = (List<ItemStack>) dataInput.readObject();
            dataInput.close();
            return item;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

}
