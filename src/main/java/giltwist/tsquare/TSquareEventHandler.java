package giltwist.tsquare;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())||!ModConfig.useWhitelist||(ModConfig.autoWhitelistOps&&event.getEntityPlayer().getServer().getPlayerList().canSendCommands(event.getEntityPlayer().getGameProfile()))) {
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
			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())||!ModConfig.useWhitelist||(ModConfig.autoWhitelistOps&&event.getEntityPlayer().getServer().getPlayerList().canSendCommands(event.getEntityPlayer().getGameProfile()))) {
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
			if (TSquare.USERWHITELIST.contains(event.getEntityPlayer().getName())||!ModConfig.useWhitelist||(ModConfig.autoWhitelistOps&&event.getEntityPlayer().getServer().getPlayerList().canSendCommands(event.getEntityPlayer().getGameProfile()))) {
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
					
						TSquarePacketHandler.INSTANCE.sendToServer(new LeftEmptyPacket(0));
					
				}
			}

		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void mouseEvent(MouseEvent event) {

		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;

		int scroll = event.getDwheel();

		if (scroll != 0) {
			if (player.getHeldItemMainhand() != null) {
				if (player.getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare") && player.isSneaking()) {
					if (scroll > 0 && player.getHeldItemMainhand().stackSize < player.getHeldItemMainhand().getMaxStackSize()) {
						TSquarePacketHandler.INSTANCE.sendToServer(new ScrollWheelPacket(1));
					}
					if (scroll < 0 && player.getHeldItemMainhand().stackSize > 1) {
						TSquarePacketHandler.INSTANCE.sendToServer(new ScrollWheelPacket(-1));
					}
					event.setCanceled(true);
				}
			}
		}

	}
}