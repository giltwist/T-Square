package giltwist.tsquare.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class DoResetAll {
	
	public static void warn(EntityPlayer player) {
		if (!player.isSwingInProgress){
		player.addChatMessage(new TextComponentString("Sneak-Right click to reset your T-Square variables"));
	}
	}

	public static void reset(EntityPlayer player){

		for (int i = 0; i < player.getEntityData().getKeySet().size(); i++) {

			String key = player.getEntityData().getKeySet().toArray()[i].toString();
			if (key.startsWith("TSquare")) {

				player.addChatMessage(new TextComponentString("Removed: " + key.toString()));
				player.getEntityData().getKeySet().remove(key);
			}

		}
		
	}
	
}
