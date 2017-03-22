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

	public static void changeGrowth(EntityPlayer player, int amount) {
		ItemStack mainItem = player.getHeldItemMainhand();
				mainItem.getItem().showDurabilityBar(mainItem);
			int newDamage = Math.min(Math.max(mainItem.getItemDamage() + amount, 0), 19);
		mainItem.setItemDamage(newDamage);
	}

	public static void material(EntityPlayer player) {
		ItemStack mainItem = player.getHeldItemMainhand();
		if (!player.isSwingInProgress) {
			if (mainItem.getMaxDamage() == 0) {
				mainItem.getItem().setMaxDamage(20);
				mainItem.getItem().showDurabilityBar(mainItem);
			}

			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					int size = mainItem.stackSize;
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
					BlockControl.changeBlocks(player, toReplace, false);

				} else {
					player.addChatMessage(new TextComponentString("No material saved"));
				}

			}

		}
	}

	public static void blockstate(EntityPlayer player) {

		ItemStack mainItem = player.getHeldItemMainhand();

		
			mainItem.getItem().showDurabilityBar(mainItem);
		
		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				int size = mainItem.stackSize;
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
				BlockControl.changeBlocks(player, toReplace, true);

			} else {
				player.addChatMessage(new TextComponentString("No blockstate saved"));
			}

		}

	}
}
