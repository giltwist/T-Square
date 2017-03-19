package giltwist.tsquare.items;

import giltwist.tsquare.BlockControl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class DoUndo {

	public static void warn(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			player.addChatMessage(new TextComponentString("Sneak-Right click to undo"));
		}
	}
	
	public static void undo(EntityPlayer player){
		
		BlockControl.rollbackMostRecent(player);
	}

}