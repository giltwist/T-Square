package giltwist.tsquare;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class LeftEmptyPacket implements IMessage {
	  // A default constructor is always required
	  public LeftEmptyPacket(){}

	  //private int toSend;
	  public LeftEmptyPacket(int toSendIn) {
	    //this.toSend = toSendIn;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
	    //buf.writeInt(toSend);
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	  //  toSend = buf.readInt();
	    
	  }
	}