package giltwist.tsquare.items;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DoEyeDropper {

	public static void material(PlayerInteractEvent.LeftClickBlock event) {

		if (!event.getEntityPlayer().isSwingInProgress) {

			if (event.getEntityPlayer().isSneaking()) {

				event.getEntityPlayer().addChatMessage(new TextComponentString("Material Saved: " + event.getWorld().getBlockState(event.getPos()).getBlock().getLocalizedName()));
				event.getEntityPlayer().getEntityData().setString("TSquarePlaceMaterial", event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString());
			} else {
				event.getEntityPlayer().addChatMessage(new TextComponentString("Block's material: " + event.getWorld().getBlockState(event.getPos()).getBlock().getLocalizedName()));
			}

		}
	}

	public static void blockstate(PlayerInteractEvent.RightClickBlock event) {

		if (event.getEntityPlayer().isSneaking()) {

			event.getEntityPlayer().addChatMessage(new TextComponentString("Saved Blockstate: " + event.getWorld().getBlockState(event.getPos()).toString()));
			event.getEntityPlayer().getEntityData().setInteger("TSquarePlaceState", event.getWorld().getBlockState(event.getPos()).getBlock().getMetaFromState(event.getWorld().getBlockState(event.getPos())));
			event.getEntityPlayer().getEntityData().setString("TSquarePlaceMaterial", event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString());

		} else {
			event.getEntityPlayer().addChatMessage(new TextComponentString("Block's state: " + event.getWorld().getBlockState(event.getPos()).toString()));
		}
	}

}
