package fr.nocsy.almpet.data;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class AbstractConfig {
    public static String getPath() {
        /* 13 */ return path;
        /* 14 */ }

    private String folderName;
    private String fileName;
    private File folder;
    private static String path = "./plugins/AdvancedPet/";
    private File file;
    private FileConfiguration config;

    public String getFolderName() {
        /* 16 */ return this.folderName;
    }

    public void setFolderName(String folderName) {
        /* 17 */ this.folderName = folderName;
    }

    /* 20 */ public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 21 */ this.fileName = fileName;
    }

    public File getFolder() {
        /* 24 */ return this.folder;
    }

    public File getFile() {
        /* 27 */ return this.file;
    }

    public FileConfiguration getConfig() {
        /* 30 */ return this.config;
    }

    public void init(String folderName, String fileName) {
        /* 35 */ this.fileName = fileName;
        /* 36 */ this.folderName = folderName;

        /* 38 */ this.folder = new File(path + path);
        /* 39 */ if (!this.folder.exists()) {
            /* 40 */ this.folder.mkdirs();
        }
        /* 42 */ this.file = new File(path + path + "/" + folderName);

        /* 44 */ if (!this.file.exists()) {

            try {
                /* 47 */ this.file.createNewFile();
                /* 48 */ } catch (IOException e) {
                /* 49 */ e.printStackTrace();
            }
        }

        /* 53 */ loadConfig();
    }

    public void save() {
        try {
            /* 59 */ this.config.save(this.file);
            /* 60 */ } catch (IOException e) {
            /* 61 */ e.printStackTrace();
        }
    }

    public void loadConfig() {
        /* 67 */ this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
    }

    public abstract void reload();
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\AbstractConfig.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
