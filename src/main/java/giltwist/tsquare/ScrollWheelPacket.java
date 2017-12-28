package giltwist.tsquare;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ScrollWheelPacket implements IMessage {
	  // A default constructor is always required
	  public ScrollWheelPacket(){}

	  private int toSend;
	  public ScrollWheelPacket(int toSendIn) {
	    this.toSend = toSendIn;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    //Writes the int into the buf
	    buf.writeInt(toSend);
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	    toSend = buf.readInt();
	    
	  }
	  
	  
	  public static class ScrollWheelPacketHandler implements IMessageHandler<ScrollWheelPacket, IMessage> {
			// Do note that the default constructor is required, but implicitly defined
			// in this case

			@Override
			public IMessage onMessage(ScrollWheelPacket message, MessageContext ctx) {
				// This is the player the packet was sent to the server from
				FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
		        return null;
				
				// No response packet
					}
			
			public void handle(ScrollWheelPacket message, MessageContext ctx){
				
				EntityPlayer player = (EntityPlayer) ctx.getServerHandler().player;
				// The value that was sent
				
				if (player.getHeldItemMainhand().getItem().getUnlocalizedName().contains("tsquare")&&player.getHeldItemMainhand().getCount()+message.toSend<=player.getHeldItemMainhand().getMaxStackSize()&&player.getHeldItemMainhand().getCount()+message.toSend>0){
				player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount()+message.toSend);
				}

				
			}

		}
	  
	}