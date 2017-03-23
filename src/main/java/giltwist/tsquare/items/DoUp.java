package giltwist.tsquare.items;

import giltwist.tsquare.BlockControl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class DoUp {


	public static void goUp(EntityPlayer player) {

		ItemStack mainItem = player.getHeldItemMainhand();
		int distance=mainItem.stackSize;
		BlockPos[] toReplace=new BlockPos[1];
		
		toReplace[0]=player.getPosition().offset(EnumFacing.UP, distance);
		BlockControl.changeBlocks(player, toReplace, true);		
		//player.attemptTeleport(player.getPosition().getX(), player.getPosition().getY()+distance, player.getPosition().getZ());


	}

}
