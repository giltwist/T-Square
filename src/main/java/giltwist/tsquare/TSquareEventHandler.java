package giltwist.tsquare;

import java.util.Random;

import giltwist.tsquare.items.DoBlend;
import giltwist.tsquare.items.DoBlob;
import giltwist.tsquare.items.DoBlockInfo;
import giltwist.tsquare.items.DoCircleCenter;
import giltwist.tsquare.items.DoCubeCenter;
import giltwist.tsquare.items.DoCuboid2Corners;
import giltwist.tsquare.items.DoEraser;
import giltwist.tsquare.items.DoEyeDropper;
import giltwist.tsquare.items.DoFillDown;
import giltwist.tsquare.items.DoLine;
import giltwist.tsquare.items.DoMoveBlock;
import giltwist.tsquare.items.DoOverlay;
import giltwist.tsquare.items.DoPaintbrush;
import giltwist.tsquare.items.DoReplaceMode;
import giltwist.tsquare.items.DoResetAll;
import giltwist.tsquare.items.DoRotateBlock;
import giltwist.tsquare.items.DoSphereCenter;
import giltwist.tsquare.items.DoSquareCenter;
import giltwist.tsquare.items.DoTerraform;
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
			Random rnd = new Random();
			switch (itemUnlocal) {

			case "item.tsquareEyeDropper":
				DoEyeDropper.material(event.getEntityPlayer());
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
			case "item.tsquareSquareCenter":
				DoSquareCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareCircleCenter":
				DoCircleCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareOverlay":
				DoOverlay.material(event.getEntityPlayer());
				break;
			case "item.tsquareFillDown":
				DoFillDown.material(event.getEntityPlayer());
				break;
			case "item.tsquareCubeCenter":
				DoCubeCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareSphereCenter":
				DoCubeCenter.material(event.getEntityPlayer());
				break;
			case "item.tsquareBlendSphere":
				DoBlend.sphere(event.getEntityPlayer(), true);
				break;
			case "item.tsquareCuboid2Corners":
				if (event.getEntityPlayer().isSneaking()) {
					DoCuboid2Corners.material(event.getEntityPlayer());
				} else {
					DoCuboid2Corners.setPoint(event.getEntityPlayer(), 1);
				}
				break;
			case "item.tsquareLine":
				if (event.getEntityPlayer().isSneaking()) {
					DoLine.material(event.getEntityPlayer());
				} else {
					DoLine.setPoint(event.getEntityPlayer(), 1);
				}
				break;
			case "item.tsquareBlob":
				if (!event.getEntityPlayer().isSneaking()) {
					DoBlob.material(event.getEntityPlayer());
				} else {
					DoBlob.changeGrowth(event.getEntityPlayer(), 1);
				}
				break;
			case "item.tsquareSmooth":
				DoTerraform.sphere(event.getEntityPlayer(), 4, 3); // strong
				break;
			case "item.tsquareMelt":
				DoTerraform.sphere(event.getEntityPlayer(), 1, 7); // strong

				break;
			case "item.tsquareFill":
				DoTerraform.sphere(event.getEntityPlayer(), 7, 3); // strong

				break;
			case "item.tsquareGrow":
				DoTerraform.sphere(event.getEntityPlayer(), 7, 1); // strong
				break;
			case "item.tsquareRandomTerraform":
				
				DoTerraform.sphere(event.getEntityPlayer(),rnd.nextInt(3)+1 , rnd.nextInt(6)+1); // strong
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
		// only items which MUST click a block
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

			case "item.tsquareBlockInfo":
				DoBlockInfo.blockstate(event);
				break;
			case "item.tsquareMoveBlock":
				DoMoveBlock.pull(event);
				break;
			case "item.tsquarePaintbrush":
				DoPaintbrush.blockstate(event);
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
		// all items that don't care if block.
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
Random rnd = new Random();
			switch (itemUnlocal) {

			case "item.tsquareResetAll":
				DoResetAll.warn(event.getEntityPlayer());
				break;
			case "item.tsquareUndo":

				DoUndo.warn(event.getEntityPlayer());

				break;
			case "item.tsquareEyeDropper":
				DoEyeDropper.blockstate(event.getEntityPlayer());
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
			case "item.tsquareOverlay":
				DoOverlay.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareFillDown":
				DoFillDown.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareCubeCenter":
				DoCubeCenter.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareSphereCenter":
				DoSphereCenter.blockstate(event.getEntityPlayer());
				break;
			case "item.tsquareBlendSphere":
				DoBlend.sphere(event.getEntityPlayer(), false);
				break;
			case "item.tsquareCuboid2Corners":
				if (event.getEntityPlayer().isSneaking()) {
					DoCuboid2Corners.blockstate(event.getEntityPlayer());
				} else {
					DoCuboid2Corners.setPoint(event.getEntityPlayer(), 2);
				}
				break;
			case "item.tsquareLine":
				if (event.getEntityPlayer().isSneaking()) {
					DoLine.blockstate(event.getEntityPlayer());
				} else {
					DoLine.setPoint(event.getEntityPlayer(), 2);
				}
				break;
			case "item.tsquareBlob":
				if (!event.getEntityPlayer().isSneaking()) {
					DoBlob.blockstate(event.getEntityPlayer());
				} else {
					DoBlob.changeGrowth(event.getEntityPlayer(), -1);
				}
				break;
			case "item.tsquareSmooth":
				DoTerraform.sphere(event.getEntityPlayer(), 5, 4); // weak
																	// smooth
				break;
			case "item.tsquareMelt":
				DoTerraform.sphere(event.getEntityPlayer(), 2, 7); // weak melt
				break;
			case "item.tsquareFill":
				DoTerraform.sphere(event.getEntityPlayer(), 7, 4); // weak fill
				break;
			case "item.tsquareGrow":
				DoTerraform.sphere(event.getEntityPlayer(), 7, 2); // weak grow
				break;
			case "item.tsquareRandomTerraform":
				DoTerraform.sphere(event.getEntityPlayer(),rnd.nextInt(6)+1 , rnd.nextInt(3)+1);
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
		if (event.getHand().toString() == "MAIN_HAND") {
			if (event.getEntityPlayer().getHeldItemMainhand() != null) {

				if (event.getEntityPlayer().getHeldItemMainhand().getItem().getCreativeTab() == TSquare.creativeTab && event.getHand().toString() == "MAIN_HAND") {
					// For the love of Notch, why is this the only client-side
					// only
					// click event?
					// Packet triggers LeftEmptyPacketHandler server-side

					TSquarePacketHandler.INSTANCE.sendToServer(new LeftEmptyPacket(7));

				}
			}

		}
	}
}