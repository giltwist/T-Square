package giltwist.tsquare;

import java.util.Random;

import giltwist.tsquare.items.DoBlend;
import giltwist.tsquare.items.DoBlob;
import giltwist.tsquare.items.DoCircleCenter;
import giltwist.tsquare.items.DoCubeCenter;
import giltwist.tsquare.items.DoCuboid2Corners;
import giltwist.tsquare.items.DoEraser;
import giltwist.tsquare.items.DoEyeDropper;
import giltwist.tsquare.items.DoLine;
import giltwist.tsquare.items.DoReplaceMode;
import giltwist.tsquare.items.DoResetAll;
import giltwist.tsquare.items.DoSphereCenter;
import giltwist.tsquare.items.DoSquareCenter;
import giltwist.tsquare.items.DoTerraform;
import giltwist.tsquare.items.DoUndo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

//The params of the IMessageHandler are <REQ, REPLY>
//This means that the first param is the packet you are receiving, and the second is the packet you are returning.
//The returned packet can be used as a "response" from a sent packet.
public class LeftEmptyPacketHandler implements IMessageHandler<LeftEmptyPacket, IMessage> {
	// Do note that the default constructor is required, but implicitly defined
	// in this case

	@Override
	public IMessage onMessage(LeftEmptyPacket message, MessageContext ctx) {
		// This is the player the packet was sent to the server from
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		// The value that was sent

		String itemUnlocal;
		if (player.getHeldItemMainhand() == null) {
			itemUnlocal = "EmptyHand"; // prevent NPE
		} else {
			itemUnlocal = player.getHeldItemMainhand().getUnlocalizedName();
		}
Random rnd = new Random();
		switch (itemUnlocal) {

		case "item.tsquareResetAll":
			if (player.isSneaking()) {
				DoResetAll.reset(player);
			} else {
				DoResetAll.warn(player);
			}

			break;
		case "item.tsquareUndo":
			if (player.isSneaking()) {
				DoUndo.undo(player);
			} else {
				DoUndo.warn(player);
			}
			break;
		case "item.tsquareReplaceMode":
			DoReplaceMode.material(player);
			break;
		case "item.tsquareEraser":
			DoEraser.material(player);
			break;
		case "item.tsquareEyeDropper":
			DoEyeDropper.material(player);
			break;
		case "item.tsquareSquareCenter":
			DoSquareCenter.material(player);
			break;
		case "item.tsquareCubeCenter":
			DoCubeCenter.material(player);
			break;
		case "item.tsquareCircleCenter":
			DoCircleCenter.material(player);
			break;
		case "item.tsquareSphereCenter":
			DoSphereCenter.material(player);
			break;
		case "item.tsquareBlendSphere":
			DoBlend.sphere(player, true);
			break;
		case "item.tsquareCuboid2Corners":
			if (player.isSneaking()) {
				DoCuboid2Corners.material(player);
			} else {
				DoCuboid2Corners.setPoint(player, 1);
			}
			break;
		case "item.tsquareLine":
			if (player.isSneaking()) {
				DoLine.material(player);
			} else {
				DoLine.setPoint(player, 1);
			}
			break;
		case "item.tsquareBlob":
			if (player.isSneaking()) {
				DoBlob.material(player);
			} else {
				DoBlob.changeGrowth(player, 1);
			}
			break;
		case "item.tsquareSmooth":
			DoTerraform.sphere(player, 4, 3); // strong Smooth
			break;
		case "item.tsquareMelt":
			DoTerraform.sphere(player, 1, 7); // strong melt
			break;
		case "item.tsquareFill":
			DoTerraform.sphere(player, 7, 3); // strong fill
			break;
		case "item.tsquareGrow":
			DoTerraform.sphere(player, 7, 1); // strong grow
			break;
		case "item.tsquareRandomTerraform":
			DoTerraform.sphere(player,rnd.nextInt(3)+1 , rnd.nextInt(6)+1);
			break;
		default:
			break;
		}

		// No response packet
		return null;
	}

}