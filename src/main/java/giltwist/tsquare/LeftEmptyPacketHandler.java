package giltwist.tsquare;

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
TSquareItemSwitch.whichAction(player, false, false);
		
		// No response packet
		return null;
	}

}