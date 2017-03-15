package giltwist.tsquare;

import giltwist.tsquare.items.DoBlockInfo;
import giltwist.tsquare.items.DoEyeDropper;
import giltwist.tsquare.items.DoMoveBlock;
import giltwist.tsquare.items.DoPaintbrush;
import giltwist.tsquare.items.DoReplaceMode;
import giltwist.tsquare.items.DoResetAll;
import giltwist.tsquare.items.DoRotateBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TSquareEventHandler {

	@SubscribeEvent
	public void leftBlockClick(PlayerInteractEvent.LeftClickBlock event) {
		Boolean shouldCancel = false;
		String itemUnlocal;
		if (event.getItemStack() == null) { // Prevent NPE in switch if empty
			itemUnlocal = "EmptyHand";
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab){
				shouldCancel=true;
			}
		}

		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			switch (itemUnlocal) {

			case "item.itemEyeDropper":
				DoEyeDropper.material(event);
				break;
			case "item.itemBlockInfo":
				DoBlockInfo.material(event);
				break;
			case "item.itemPaintbrush":
				DoPaintbrush.material(event);
				break;
			case "item.itemMoveBlock":
				DoMoveBlock.push(event);
				break;
			case "item.itemRotateBlock":

				DoRotateBlock.rotation(event.getEntityPlayer(), event.getPos(), event.getFace());
				break;
			case "item.itemResetAll":
				DoResetAll.warn(event.getEntityPlayer());
				break;
			default:
				shouldCancel = false;
				break;
			}
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightBlockClick(PlayerInteractEvent.RightClickBlock event) {

		Boolean shouldCancel = false;
		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) {
			itemUnlocal = "EmptyHand"; // prevent NPE
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab){
				shouldCancel=true;
			}
		
		}
		
		
		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			
			switch (itemUnlocal) {

			case "item.itemEyeDropper":
				DoEyeDropper.blockstate(event);
				break;
			case "item.itemBlockInfo":
				DoBlockInfo.blockstate(event);
				break;
			case "item.itemMoveBlock":

				DoMoveBlock.pull(event);
				break;
			case "item.itemPaintbrush":
				DoPaintbrush.blockstate(event);
				break;
			case "item.itemResetAll": // info on click, reset on sneak-click

				if (event.getEntityPlayer().isSneaking()) {
					DoResetAll.reset(event.getEntityPlayer());

				} else {

					DoResetAll.warn(event.getEntityPlayer());
				}

				break;
			case "item.itemRotateBlock":
				DoRotateBlock.rotation(event.getEntityPlayer(), event.getPos(), event.getFace().getOpposite());
				break;
			default:
				shouldCancel = false;
				break;
			}
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightClickItem(PlayerInteractEvent.RightClickItem event) {

		Boolean shouldCancel = false;

		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) {
			itemUnlocal = "EmptyHand"; // prevent NPE
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab){
				shouldCancel=true;
			}
		}
		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			switch (itemUnlocal) {

			case "item.itemResetAll":
				if (event.getEntityPlayer().isSneaking()) {
					DoResetAll.reset(event.getEntityPlayer());
				} else {
					DoResetAll.warn(event.getEntityPlayer());
				}
				break;
			case "item.itemReplaceMode":
			
				DoReplaceMode.blockstate(event.getEntityPlayer(),event.getItemStack());
				break;
			
			default:
				shouldCancel = false;
				break;
			}
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {

		String itemUnlocal;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) {
			itemUnlocal = "EmptyHand"; // prevent NPE
		} else {
			itemUnlocal = event.getEntityPlayer().getHeldItemMainhand().getUnlocalizedName();
		}
		if (event.getHand().toString() == "MAIN_HAND") {
			switch (itemUnlocal) {

			case "item.itemResetAll": // info on click, reset on sneak-click

				DoResetAll.warn(event.getEntityPlayer());
				break;
			case "item.itemReplaceMode":
				
				DoReplaceMode.material(event.getEntityPlayer(),event.getItemStack());
				break;
			default:
				break;
			}
		}
	}

}
