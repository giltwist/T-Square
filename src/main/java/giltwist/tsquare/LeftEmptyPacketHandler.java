package giltwist.tsquare;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
        return null;
		
		// No response packet
			}
	
	public void handle(LeftEmptyPacket message, MessageContext ctx){
				
		EntityPlayer player = (EntityPlayer) ctx.getServerHandler().playerEntity;
		
		// The value that was sent
		if (TSquare.USERWHITELIST.contains(player.getName())) {
		TSquareItemSwitch.whichAction(player, false, false);
	} else {
		if (!player.isSwingInProgress) {
			player.sendMessage(new TextComponentString("You do not have permission to use T-Square."));
		}
	}

		
	}

}