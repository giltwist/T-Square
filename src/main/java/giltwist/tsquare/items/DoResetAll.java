package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class DoResetAll {
	
	public static void warn(EntityPlayer player) {
		if (!player.isSwingInProgress){
		player.sendMessage(new TextComponentString("Sneak-Left click to reset your T-Square variables"));
	}
	}

	public static void reset(EntityPlayer player){
		if (!player.isSwingInProgress){
			Set <String> toRemove=new HashSet<String>();
			for (String key:player.getEntityData().getKeySet()) {

				if (key.startsWith("TSquare")) {

					player.sendMessage(new TextComponentString("Removed: " + key.toString()));
					toRemove.add(key);
					}

			}
			player.getEntityData().getKeySet().removeAll(toRemove);

			player.sendMessage(new TextComponentString("Done."));
		
		
		
		}
		
	}
	
}
