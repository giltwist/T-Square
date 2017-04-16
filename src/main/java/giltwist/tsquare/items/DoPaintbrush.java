package giltwist.tsquare.items;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoPaintbrush {

	@SuppressWarnings("deprecation")
	public static void activate(EntityPlayer player, boolean fullBlockState) {

		String offHand=null;
		Block placemat;
		IBlockState placematState;
		Boolean isBlock = false;

		if (player.getHeldItemOffhand()!=null){
			offHand=player.getHeldItemOffhand().getItem().getRegistryName().toString();
		if (player.getHeldItemOffhand().getItem() instanceof net.minecraft.item.ItemBlock) {
			isBlock = true;
		}
		}

		BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

		if (targetBlock == null) {
			player.sendMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (isBlock && !player.isSneaking()) {
				
				//offhand override
				placemat = net.minecraft.block.Block.getBlockFromName(offHand);

				if (fullBlockState) {
					placematState = placemat.getStateFromMeta(player.getHeldItemOffhand().getMetadata());
				} else {
					placematState = placemat.getDefaultState();
				}
				if (!TSquare.BLOCKBLACKLIST.contains(player.getEntityWorld().getBlockState(targetBlock).getBlock())&&!TSquare.BLOCKBLACKLIST.contains(placematState.getBlock())) {
				player.getEntityWorld().setBlockState(targetBlock, placematState);
				}else {
					player.sendMessage(new TextComponentString("Error: Either target block or offhand material is on global blacklist"));
				}
			} else {
				//use normal means
				
				BlockPos[] toReplace = new BlockPos[1];
				toReplace[0]=targetBlock;
				BlockControl.changeBlocks(player, toReplace, fullBlockState);
			}

		}
	}

	

}
