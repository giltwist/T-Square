package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;

public class DoSplatterOverlay {

	public static void changeGrowth(EntityPlayer player, boolean isRight) {
		int amount=0;
		if (isRight){
			amount=-1;
		}else{
			amount=1;
		}
		ItemStack mainItem = player.getHeldItemMainhand();
		mainItem.getItem().showDurabilityBar(mainItem);
		int newDamage = Math.min(Math.max(mainItem.getItemDamage() + amount, 0), 19);
		mainItem.setItemDamage(newDamage);
	}

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		ItemStack mainItem = player.getHeldItemMainhand();
		if (!player.isSwingInProgress) {

			ItemStack offhandItem = player.getHeldItemOffhand();
			String offhandItemUnlocal;

			if (offhandItem == null) {
				offhandItemUnlocal = "EmptyOffhand";
			} else {
				offhandItemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
			}

			Vec3i sapFix = new Vec3i(0, 0, 0);

			if (offhandItemUnlocal.contains("sapling")) {
				sapFix = new Vec3i(0, 1, 0);
			}

			if (mainItem.getMaxDamage() == 0) {
				mainItem.getItem().setMaxDamage(20);
				mainItem.getItem().showDurabilityBar(mainItem);
			}

			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				

					int size = mainItem.getCount();
					float growth = mainItem.getMaxDamage() - mainItem.getItemDamage();
					float growthPercent = growth / 20;

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
					// blocksToChange.add(center);
					Random rnd = new Random();
					int yCheck = 0;
					BlockPos tempPos = null;
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							// the -1 <= below prevents "nipples"
							if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {

								yCheck = 0;
								if (rnd.nextFloat() <= growthPercent) {
									for (int n = 0; n <= 10; n++) {
										tempPos = new BlockPos(center.getX() + i, center.getY() - n, center.getZ() + j);
										if (yCheck < 1 && player.getEntityWorld().getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
											yCheck++;
											blocksToChange.add(tempPos.add(sapFix));
										}

									}
								}

							}
						}
					}
					if (blocksToChange.size() > 0) {
						BlockPos[] toReplace = blocksToChange.toArray(new BlockPos[blocksToChange.size()]);
						BlockControl.logUndo(player, toReplace);
						BlockControl.changeBlocks(player, toReplace, fullBlockState);
					}

				} 

			}

		}
	

}
