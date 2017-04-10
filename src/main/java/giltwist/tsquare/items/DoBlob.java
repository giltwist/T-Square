package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoBlob {

	public static void changeGrowth(EntityPlayer player, boolean isRight) {
		ItemStack mainItem = player.getHeldItemMainhand();
				mainItem.getItem().showDurabilityBar(mainItem);
				int amount=0;
				if (isRight){
					amount=-1;
				}else{
					amount=1;
				}
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

					Set<BlockPos> tempAdd = new HashSet<BlockPos>();

					for (int r = 1; r < size; r++) {
						for (BlockPos temp : blocksToChange) {

							for (EnumFacing f : EnumFacing.values()) {
								float tempChance = rnd.nextFloat();

								if (tempChance <= growthPercent) {

									tempAdd.add(temp.offset(f));

								}
							}

						}
						blocksToChange.addAll(tempAdd);
					}

					BlockPos[] toReplace = blocksToChange.toArray(new BlockPos[blocksToChange.size()]);
					BlockControl.logUndo(player, toReplace);
					BlockControl.changeBlocks(player, toReplace, fullBlockState);

				

			}

		}
	}

	
}
