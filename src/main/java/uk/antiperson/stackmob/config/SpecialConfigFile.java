package uk.antiperson.stackmob.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import uk.antiperson.stackmob.StackMob;

public abstract class SpecialConfigFile extends ConfigFile {

    public SpecialConfigFile(StackMob sm, String filePath) {
        super(sm, filePath);
    }

    public ConfigList getList(EntityType type, String path) {
        return getList(getPath(type, path));
    }

    public boolean getBoolean(EntityType type, String path) {
        return getBoolean(getPath(type, path));
    }

    public double getDouble(EntityType type, String path) {
        return getDouble(getPath(type, path));
    }

    public int getInt(EntityType type, String path) {
        return getInt(getPath(type, path));
    }

    public String getString(EntityType type, String path) {
        return getString(getPath(type, path));
    }

    public ConfigurationSection getConfigurationSection(EntityType type, String path) {
        return getConfigurationSection(getPath(type, path));
    }

    /**
     * Gets the custom config path for this entity if it has been specified. If not this will just be the normal value.
     * @param type the entity type
     * @param path the config path.
     * @return the path for this config path and entity.
     */
    private String getPath(EntityType type, String path) {
        if (!isFileLoaded()) {
            throw new UnsupportedOperationException("Configuration file has not been loaded!");
        }
        // Check if the specified general config path is overridden by an entity specific equivalent.
        String customPath = "custom." + type + "." + path;
        if (isSet(customPath)) {
            return customPath;
        }
        // Check if this entity specific path is specified to clone another path.
        String clone = "custom." + type + ".clone";
        String clonePath = "custom." + getString(clone) + "." + path;
        if (isString(clonePath)) {
            return clonePath;
        }
        return path;
    }
}
