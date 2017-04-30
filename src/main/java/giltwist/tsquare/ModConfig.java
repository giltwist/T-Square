package giltwist.tsquare;

import giltwist.tsquare.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

public class ModConfig {
    private static final String CATEGORY_GENERAL = "general";

    // This values below you can access elsewhere in your mod:
    public static boolean useWhitelist = false;
    public static boolean autoWhitelistOps = true;
    
    public static int maxBrushSize = 64;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);

        } catch (Exception e1) {
            TSquare.logger.error("Problem loading config file!" +  e1.toString());
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        useWhitelist = cfg.getBoolean("useWhitelist", CATEGORY_GENERAL, useWhitelist, "False means anyone can use T-Square, true means only whitelisted.");
        autoWhitelistOps = cfg.getBoolean("autoWhitelistOps", CATEGORY_GENERAL, autoWhitelistOps, "If true, ops can use T-Square even if not on whitelist.");
        maxBrushSize = cfg.getInt("maxBrushSize", CATEGORY_GENERAL, maxBrushSize, 1, 100, "Largest brush size allowed to users.  Bigger is laggier. Does not apply to advanced shapes.");
    }
 
}
