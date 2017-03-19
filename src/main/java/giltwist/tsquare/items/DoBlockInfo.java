package giltwist.tsquare.items;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DoBlockInfo {

	public static void material(PlayerInteractEvent.LeftClickBlock event) {

		if (!event.getEntityPlayer().isSwingInProgress) {
				event.getEntityPlayer().addChatMessage(new TextComponentString("Block's material: " + event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString()));
				//event.getEntityPlayer().addChatMessage(new TextComponentString("Sighted face: " + event.getFace()));
				


		}
	}

	public static void blockstate(PlayerInteractEvent.RightClickBlock event) {


			event.getEntityPlayer().addChatMessage(new TextComponentString("Block's state: " + event.getWorld().getBlockState(event.getPos()).toString()));
		
	}

}
