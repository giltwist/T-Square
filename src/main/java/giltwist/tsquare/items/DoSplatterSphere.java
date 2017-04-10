package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoSplatterSphere {

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
					blocksToChange.add(center);
					Random rnd = new Random();

					
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							for (int k = -1 * (size - 1); k <= (size - 1); k++) {
								if ((i * i + j * j + k * k) - 1 <= (size - 1) * (size - 1)) {

									if (rnd.nextFloat()<=growthPercent){
									blocksToChange.add(new BlockPos(center.getX() + i, center.getY() + j, center.getZ() + k));
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
