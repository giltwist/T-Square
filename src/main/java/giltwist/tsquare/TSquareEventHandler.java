package giltwist.tsquare;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TSquareEventHandler {

	@SubscribeEvent
	public void leftBlockClick(PlayerInteractEvent.LeftClickBlock event) {
		Boolean shouldCancel = false;
		if (event.getItemStack() != null) { // Prevent NPE in switch if empty

			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare")) {
				shouldCancel = true;
			}
		}

		if (shouldCancel && event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {

			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())) {
				TSquareItemSwitch.whichAction(event.getEntityPlayer(), true, false);
			} else {
				if (!event.getEntityPlayer().isSwingInProgress) {
					event.getEntityPlayer().addChatMessage(new TextComponentString("You do not have permission to use T-Square."));
				}
			}
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightBlockClick(PlayerInteractEvent.RightClickBlock event) {
		// only items which MUST click a block
		Boolean shouldCancel = false;

		if (event.getEntityPlayer().getHeldItemMainhand() != null) {

			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare")) {
				shouldCancel = true;
			}

		}

		if (shouldCancel && event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())) {
				TSquareItemSwitch.whichAction(event.getEntityPlayer(), true, true);
			} else {
				if (!event.getEntityPlayer().isSwingInProgress) {
					event.getEntityPlayer().addChatMessage(new TextComponentString("You do not have permission to use T-Square."));
				}
			}
		}
		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void rightClickItem(PlayerInteractEvent.RightClickItem event) {
		Boolean shouldCancel = false;
		if (event.getEntityPlayer().getHeldItemMainhand() != null) {

			if (event.getEntityPlayer().getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare")) {
				shouldCancel = true;
			}
		}
		if (shouldCancel && event.getSide().isServer() && event.getHand().toString() == "MAIN_HAND") {
			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())) {
				TSquareItemSwitch.whichAction(event.getEntityPlayer(), false, true);
			} else {
				if (!event.getEntityPlayer().isSwingInProgress) {
					event.getEntityPlayer().addChatMessage(new TextComponentString("You do not have permission to use T-Square."));
				}
			}
		}

		event.setCanceled(shouldCancel.booleanValue());

	}

	@SubscribeEvent
	public void leftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
		if (event.getHand().toString() == "MAIN_HAND") {
			if (event.getEntityPlayer().getHeldItemMainhand() != null) {

				if (event.getEntityPlayer().getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare")) {
					// For the love of Notch, why is this the only client-side
					// only
					// click event?
					// Packet triggers LeftEmptyPacketHandler server-side
					if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())) {
						TSquarePacketHandler.INSTANCE.sendToServer(new LeftEmptyPacket(0));
					} else {
						if (!event.getEntityPlayer().isSwingInProgress) {
							event.getEntityPlayer().addChatMessage(new TextComponentString("You do not have permission to use T-Square."));
						}
					}
				}
			}

		}
	}
}