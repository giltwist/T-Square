package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;

public class DoOverlay {

	public static void activate(EntityPlayer player,boolean fullBlockState) {
		if (!player.isSwingInProgress) {
			
			ItemStack offhandItem = player.getHeldItemOffhand();
			String offhandItemUnlocal;

			if (offhandItem == null) {
				offhandItemUnlocal = "EmptyOffhand";
			} else {
				offhandItemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
			}
			
			Vec3i sapFix = new Vec3i(0,0,0);
			EnumFacing upDown=EnumFacing.DOWN;
			
			if (offhandItemUnlocal.contains("sapling")){
				upDown = EnumFacing.UP;
				sapFix = new Vec3i(0,1,0);
			}
			
			int depth;
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				
					int size = player.getHeldItemMainhand().getCount();

					if (player.isSneaking()) {
						depth = 3;
					} else {
						depth = 1;
					}

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
					int yCheck = 0;
					BlockPos tempPos = null;
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							// the -1 <= below prevents "nipples"
							if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {

								yCheck = 0;

								for (int n = 0; n <= 10; n++) {
									
									
									
									tempPos = new BlockPos(center.getX() + i, center.getY() - n, center.getZ() + j);
									if (yCheck==0 && player.getEntityWorld().getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")&& player.getEntityWorld().getBlockState(tempPos.offset(EnumFacing.UP)).getBlock() == net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
										yCheck++;
										blocksToChange.add(tempPos.add(sapFix));
										if (depth==3){
											blocksToChange.add(tempPos.add(sapFix).offset(upDown));
											blocksToChange.add(tempPos.add(sapFix).offset(upDown,2));
										}
										
									}

								
								}

							}
						}
					}
					BlockPos[] toReplace = blocksToChange.toArray(new BlockPos[blocksToChange.size()]);
					BlockControl.logUndo(player, toReplace);
					BlockControl.changeBlocks(player, toReplace, fullBlockState);



			}

		}
	}


}
