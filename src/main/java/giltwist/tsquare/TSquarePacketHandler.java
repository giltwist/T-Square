package giltwist.tsquare;

import giltwist.tsquare.ScrollWheelPacket.ScrollWheelPacketHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class TSquarePacketHandler {

    private static int packetId = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public TSquarePacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        // Register messages which are sent from the client to the server here:
        INSTANCE.registerMessage(LeftEmptyPacketHandler.class, LeftEmptyPacket.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(ScrollWheelPacketHandler.class, ScrollWheelPacket.class, nextID(), Side.SERVER);
    }
	
}
