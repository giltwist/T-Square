package giltwist.tsquare.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DoPaintbrush {
	
	public static void material(PlayerInteractEvent.LeftClickBlock event) {
		
			String offHand;
			Block placemat;
			Boolean isBlock = false;
			if (event.getEntityPlayer().getHeldItemOffhand() == null) { // prevent
																		// NPE

				offHand = "EmptyHand";
			} else {
				offHand = event.getEntityPlayer().getHeldItemOffhand().getItem().getRegistryName().toString();
				if (event.getEntityPlayer().getHeldItemOffhand().getItem() instanceof net.minecraft.item.ItemBlock) {
					isBlock = true;
				}
			}

			if (isBlock && !event.getEntityPlayer().isSneaking()) {
				placemat = net.minecraft.block.Block.getBlockFromName(offHand);
				// event.getEntityPlayer().addChatMessage(new
				// TextComponentString("Placing material: " +
				// placemat.toString()));
				IBlockState placematState = placemat.getDefaultState();
				event.getWorld().setBlockState(event.getPos(), placematState);
			} else {

				if (event.getEntityPlayer().getEntityData().hasKey("TSquarePlaceMaterial")) {
					placemat = net.minecraft.block.Block.getBlockFromName(event.getEntityPlayer().getEntityData().getString("TSquarePlaceMaterial"));
					// event.getEntityPlayer().addChatMessage(new
					// TextComponentString("Placing material: " +
					// placemat.toString()));
					IBlockState placematState = placemat.getDefaultState();
					event.getWorld().setBlockState(event.getPos(), placematState);
				} else {
					if (event.getEntityPlayer().isSneaking()) {
						event.getEntityPlayer().addChatMessage(new TextComponentString("No material saved (offhand ignored)"));

					} else {
						event.getEntityPlayer().addChatMessage(new TextComponentString("No material saved or offhand"));
					}
				}

			}

		
		
	}
	
	public static void blockstate(PlayerInteractEvent.RightClickBlock event) {
		
		
			String offHand;
			IBlockState placematState;
			Block placemat;
			Boolean isBlock = false;
			if (event.getEntityPlayer().getHeldItemOffhand() == null) { // prevent
																		// NPE

				offHand = "EmptyHand";
			} else {
				offHand = event.getEntityPlayer().getHeldItemOffhand().getItem().getRegistryName().toString();
				if (event.getEntityPlayer().getHeldItemOffhand().getItem() instanceof net.minecraft.item.ItemBlock) {
					isBlock = true;
				}
			}

			if (isBlock && !event.getEntity().isSneaking()) {
				placemat = net.minecraft.block.Block.getBlockFromName(offHand);

				placematState = placemat.getStateFromMeta(event.getEntityPlayer().getHeldItemOffhand().getMetadata());
				event.getEntityPlayer().addChatMessage(new TextComponentString("Placing state: " + placematState.toString()));
				event.getWorld().setBlockState(event.getPos(), placematState);

			} else {

				if (event.getEntityPlayer().getEntityData().hasKey("TSquarePlaceState")) {
					placemat = net.minecraft.block.Block.getBlockFromName(event.getEntityPlayer().getEntityData().getString("TSquarePlaceMaterial"));
					// event.getEntityPlayer().addChatMessage(new
					// TextComponentString("Placing material: " +
					// placemat.toString()));
					placematState = placemat.getStateFromMeta(event.getEntityPlayer().getEntityData().getInteger("TSquarePlaceState"));
					event.getWorld().setBlockState(event.getPos(), placematState);
				} else {
					if (event.getEntityPlayer().isSneaking()) {

						event.getEntityPlayer().addChatMessage(new TextComponentString("No block state saved (offhand ignored)"));
					} else {
						event.getEntityPlayer().addChatMessage(new TextComponentString("No block state saved or offhand"));
					}
				}

			}

		
	}
	
	
}
