package giltwist.tsquare;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TSquareEventHandler {

	
	@SubscribeEvent
	public void leftBlockClick(PlayerInteractEvent.LeftClickBlock event) {
		Boolean shouldCancel = true;
		String itemUnlocal;
		if (event.getItemStack() == null) { // Prevent NPE in switch if empty
											// handed

			itemUnlocal = "EmptyHand";
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
		}
 
		switch (itemUnlocal) {

		case "item.itemEyeDropper": // Report material on click, save on
									// sneak-click
			if ( event.getSide().isServer()&&!event.getEntityPlayer().isSwingInProgress&&event.getHand().toString() == "MAIN_HAND" ) {

				if (event.getEntityPlayer().isSneaking()) {

					event.getEntityPlayer().addChatMessage(new TextComponentString("Material Saved: " + event.getWorld().getBlockState(event.getPos()).getBlock().getLocalizedName()));
					event.getEntityPlayer().getEntityData().setString("TSquarePlaceMaterial", event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString());
				} else {
					event.getEntityPlayer().addChatMessage(new TextComponentString(event.toString() +" | Block's material: " + event.getWorld().getBlockState(event.getPos()).getBlock().getLocalizedName()));
				}

			}
			break;
		case "item.itemPaintbrush": // material on click, lefthand overrides
			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
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

				if (isBlock) {
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
						event.getEntityPlayer().addChatMessage(new TextComponentString("No material saved or offhand"));
					}

				}

			}
			break;
		case "item.itemMoveBlock": // push only to air on click, anywhere on
									// sneak-click
			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

				BlockPos movestart = event.getPos();
				BlockPos moveend = event.getPos().subtract(event.getFace().getDirectionVec());
				IBlockState moveblock = event.getWorld().getBlockState(movestart);

				if (event.getWorld().getBlockState(moveend).getMaterial() == net.minecraft.block.material.Material.AIR || event.getEntityPlayer().isSneaking()) {
					event.getWorld().setBlockToAir(movestart);

					event.getWorld().setBlockState(moveend, moveblock);

				}

			}
			break;
		case "item.itemResetAll": // info only
			if (event.getSide().isServer()&&!event.getEntityPlayer().isSwingInProgress && event.getHand().toString() == "MAIN_HAND") {
				event.getEntityPlayer().addChatMessage(new TextComponentString("Sneak-Right click to reset your T-Square variables"));
			}
			break;
		default:
			shouldCancel = false;
			break;
		}
		event.setCanceled(shouldCancel.booleanValue());
	}

	@SubscribeEvent
	public void rightBlockClick(PlayerInteractEvent.RightClickBlock event) {

				
		Boolean shouldCancel = true;

		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) { // prevent
																		// NPE

			itemUnlocal = "EmptyHand";
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
		}

		switch (itemUnlocal) {

		case "item.itemEyeDropper": // report blockstate on click, save or
									// sneak-click

			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

				if (event.getEntityPlayer().isSneaking()) {

					event.getEntityPlayer().addChatMessage(new TextComponentString("Saved Blockstate: " + event.getWorld().getBlockState(event.getPos()).toString()));
					event.getEntityPlayer().getEntityData().setInteger("TSquarePlaceState", event.getWorld().getBlockState(event.getPos()).getBlock().getMetaFromState(event.getWorld().getBlockState(event.getPos())));
					event.getEntityPlayer().getEntityData().setString("TSquarePlaceMaterial", event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString());

				} else {
					event.getEntityPlayer().addChatMessage(new TextComponentString("Block's state: " + event.getWorld().getBlockState(event.getPos()).toString()));
				}
			}
			break;
		case "item.itemMoveBlock": // pull to air on click, anywhere on
									// sneak-click

			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
				BlockPos movestart = event.getPos();
				BlockPos moveend = event.getPos().add(event.getFace().getDirectionVec());
				IBlockState moveblock = event.getWorld().getBlockState(movestart);

				if (event.getWorld().getBlockState(moveend).getMaterial() == net.minecraft.block.material.Material.AIR || event.getEntityPlayer().isSneaking()) {
					event.getWorld().setBlockToAir(movestart);

					event.getWorld().setBlockState(moveend, moveblock);

				}

			}
			break;
		case "item.itemPaintbrush": // complete blockstate on click, offhand
									// overrides

			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
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

				if (isBlock) {
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
						event.getEntityPlayer().addChatMessage(new TextComponentString("No block state saved or offhand"));
					}

				}

			}
			break;
		case "item.itemResetAll": // info on click, reset on sneak-click
			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
				if (event.getEntityPlayer().isSneaking()) {
					event.getEntityPlayer().addChatMessage(new TextComponentString(event.getEntityPlayer().getEntityData().getKeySet().toString()));

				} else {
					event.getEntityPlayer().addChatMessage(new TextComponentString("Sneak-Right click to reset your T-Square variables"));
				}
			}
			break;
		default:
			shouldCancel = false;
			break;
		}

		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightClickItem(PlayerInteractEvent.RightClickItem event) {

		Boolean shouldCancel = true;

		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) { // prevent
																		// NPE

			itemUnlocal = "EmptyHand";
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
		}

		switch (itemUnlocal) {

		case "item.itemResetAll": // info on click, reset on sneak-click
			if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
				if (event.getEntityPlayer().isSneaking()) {
					
					for (int i=0; i<event.getEntityPlayer().getEntityData().getKeySet().size();i++){
						
						String key=event.getEntityPlayer().getEntityData().getKeySet().toArray()[i].toString();
						if (key.startsWith("TSquare")){
							
							event.getEntityPlayer().addChatMessage(new TextComponentString("Removed: "+key.toString()));
							event.getEntityPlayer().getEntityData().getKeySet().remove(key);
						}
						
					}
					

				} else {
					event.getEntityPlayer().addChatMessage(new TextComponentString("Sneak-Right click to reset your T-Square variables"));
				}
			}
			break;
		default:
			shouldCancel = false;
			break;
		}

		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {

		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) { // prevent
																		// NPE

			itemUnlocal = "EmptyHand";
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
		}

		switch (itemUnlocal) {

		case "item.itemResetAll": // info on click, reset on sneak-click
			if (event.getHand().toString() == "MAIN_HAND"&&!event.getEntityPlayer().isSwingInProgress) {
				event.getEntityPlayer().addChatMessage(new TextComponentString("Sneak-Right click to reset your T-Square variables"));
			}
			break;
		default:
			break;
		}

	}

}
