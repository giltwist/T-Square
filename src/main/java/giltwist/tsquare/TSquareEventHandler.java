package giltwist.tsquare;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TSquareEventHandler {

	@SubscribeEvent
	public void leftBlockClick(PlayerInteractEvent.LeftClickBlock event) {
		Boolean shouldCancel = false;
		if (event.getItemStack() == null) { // Prevent NPE in switch if empty
		
		} else {
		
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}
		}
		
		if (shouldCancel&&event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

		TSquareItemSwitch.whichAction(event.getEntityPlayer(), true, false);
		}
		event.setCanceled(shouldCancel.booleanValue());
		

	}

	@SubscribeEvent
	public void rightBlockClick(PlayerInteractEvent.RightClickBlock event) {
		// only items which MUST click a block
		Boolean shouldCancel = false;
	
		if (event.getEntityPlayer().getHeldItemMainhand() == null) {
			
		} else {
			
			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}

		}

		if (shouldCancel&&event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

			TSquareItemSwitch.whichAction(event.getEntityPlayer(), true, true);
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightClickItem(PlayerInteractEvent.RightClickItem event) {
		Boolean shouldCancel = false;
		if (event.getEntityPlayer().getHeldItemMainhand() == null) {

		} else {

			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
				shouldCancel = true;
			}
		}
		if (shouldCancel&&event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
		TSquareItemSwitch.whichAction(event.getEntityPlayer(), false, true);
			
		}

		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
		if (event.getHand().toString() == "MAIN_HAND") {
			if (event.getEntityPlayer().getHeldItemMainhand() != null) {

				if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab) {
					// For the love of Notch, why is this the only client-side
					// only
					// click event?
					// Packet triggers LeftEmptyPacketHandler server-side

					TSquarePacketHandler.INSTANCE.sendToServer(new LeftEmptyPacket(0));

				}
			}

		}
	}
}