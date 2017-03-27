package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;

public class DoLoadArea {
	
	public static void warn(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			player.addChatMessage(new TextComponentString("Sneak-Left click to force a chunk to render"));
		}
	}

	public static void activate(EntityPlayer player) {

		if (!player.isSwingInProgress) {
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {
		
				Chunk targetChunk = player.worldObj.getChunkFromBlockCoords(targetBlock);
				//player.worldObj.markBlockRangeForRenderUpdate(targetChunk.xPosition, 0, targetChunk.zPosition, targetChunk.xPosition+15, 255, targetChunk.zPosition+15);
				
				short[] wholeChunk= new short[655356];
				
				for (int i=0;i<wholeChunk.length;i++){
				wholeChunk[i]=(short) i;
				}
				
				
				if (player instanceof EntityPlayerMP){
					
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					playerMP.connection.sendPacket(new net.minecraft.network.play.server.SPacketMultiBlockChange(655356, wholeChunk, targetChunk));
					
				}
				
				
				

			}
		}
	}
}
