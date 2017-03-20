package giltwist.tsquare;

import giltwist.tsquare.items.DoBlockInfo;
import giltwist.tsquare.items.DoCircleCenter;
import giltwist.tsquare.items.DoCubeCenter;
import giltwist.tsquare.items.DoEraser;
import giltwist.tsquare.items.DoEyeDropper;
import giltwist.tsquare.items.DoMoveBlock;
import giltwist.tsquare.items.DoPaintbrush;
import giltwist.tsquare.items.DoReplaceMode;
import giltwist.tsquare.items.DoResetAll;
import giltwist.tsquare.items.DoRotateBlock;
import giltwist.tsquare.items.DoSquareCenter;
import giltwist.tsquare.items.DoUndo;
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
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}
		}

		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			switch (itemUnlocal) {

			case "item.tsquareEyeDropper":
				DoEyeDropper.material(event);
				break;
			case "item.tsquareBlockInfo":
				DoBlockInfo.material(event);
				break;
			case "item.tsquarePaintbrush":
				DoPaintbrush.material(event);
				break;
			case "item.tsquareMoveBlock":
				DoMoveBlock.push(event);
				break;
			case "item.tsquareRotateBlock":
				DoRotateBlock.rotation(event.getEntityPlayer(), event.getPos(), event.getFace());
				break;
			case "item.tsquareResetAll":
				DoResetAll.warn(event.getEntityPlayer());
				break;
			case "item.tsquareUndo":
				DoUndo.warn(event.getEntityPlayer());
				break;
			case "item.tsquareSquareCenter":
				DoSquareCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareCircleCenter":
				DoCircleCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareCubeCenter":
				DoCubeCenter.material(event.getEntityPlayer());
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
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}

		}

		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

			switch (itemUnlocal) {

			case "item.tsquareEyeDropper":
				DoEyeDropper.blockstate(event);
				break;
			case "item.tsquareBlockInfo":
				DoBlockInfo.blockstate(event);
				break;
			case "item.tsquareMoveBlock":

				DoMoveBlock.pull(event);
				break;
			case "item.tsquarePaintbrush":
				DoPaintbrush.blockstate(event);
				break;
			case "item.tsquareResetAll": // info on click, reset on sneak-click
				if (event.getEntityPlayer().isSneaking()) {
					DoResetAll.reset(event.getEntityPlayer());

				} else {
					DoResetAll.warn(event.getEntityPlayer());
				}
				break;
			case "item.tsquareUndo": // info on click, reset on sneak-click
				if (event.getEntityPlayer().isSneaking()) {
					DoUndo.undo(event.getEntityPlayer());

				} else {
					DoUndo.warn(event.getEntityPlayer());
				}
				break;
			case "item.tsquareRotateBlock":
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
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}
		}
		if (event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			switch (itemUnlocal) {

			case "item.tsquareResetAll":
				if (event.getEntityPlayer().isSneaking()) {
					DoResetAll.reset(event.getEntityPlayer());
				} else {
					DoResetAll.warn(event.getEntityPlayer());
				}
				break;
			case "item.tsquareUndo":
				if (event.getEntityPlayer().isSneaking()) {
					DoUndo.undo(event.getEntityPlayer());
				} else {
					DoUndo.warn(event.getEntityPlayer());
				}
				break;
			case "item.tsquareReplaceMode":
				DoReplaceMode.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareEraser":
				DoEraser.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareSquareCenter":
				DoSquareCenter.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareCircleCenter":
				DoCircleCenter.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareCubeCenter":
				DoCubeCenter.blockstate(event.getEntityPlayer());
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
		if (event.getEntityPlayer().getHeldItemMainhand() != null) {

			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab && event.getHand().toString() == "MAIN_HAND") {
				// For the love of Notch, why is this the only client-side only click event?
				// Packet triggers LeftEmptyPacketHandler server-side
				TSquarePacketHandler.INSTANCE.sendToServer(new LeftEmptyPacket(7));

			}
		}

	}
	
	
}