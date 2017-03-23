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

public class DoSplatterCircle {

	public static void changeGrowth(EntityPlayer player, int amount) {
		ItemStack mainItem = player.getHeldItemMainhand();
		mainItem.getItem().showDurabilityBar(mainItem);
		int newDamage = Math.min(Math.max(mainItem.getItemDamage() + amount, 0), 19);
		mainItem.setItemDamage(newDamage);
	}

	public static void material(EntityPlayer player) {
		ItemStack mainItem = player.getHeldItemMainhand();
		if (!player.isSwingInProgress) {

			EnumFacing face;

			if (mainItem.getMaxDamage() == 0) {
				mainItem.getItem().setMaxDamage(20);
				mainItem.getItem().showDurabilityBar(mainItem);
			}

			face = FindLookedBlock.getBlockFace(player);

			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (face != null) {
					if (player.isSneaking()) {
						center = center.offset(face);
					}
				}
				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					int size = mainItem.stackSize;
					float growth = mainItem.getMaxDamage() - mainItem.getItemDamage();
					float growthPercent = growth / 20;

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
					blocksToChange.add(center);
					Random rnd = new Random();

					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							// the -1 <= below prevents "nipples"
							if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {
								if (rnd.nextFloat() <= growthPercent) {
									if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
										blocksToChange.add(new BlockPos(center.getX() + i, center.getY(), center.getZ() + j));
									}
									if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
										blocksToChange.add(new BlockPos(center.getX() + i, center.getY() + j, center.getZ()));
									}
									if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
										blocksToChange.add(new BlockPos(center.getX(), center.getY() + i, center.getZ() + j));
									}
								}
							}
						}
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

		EnumFacing face;

		if (mainItem.getMaxDamage() == 0) {
			mainItem.getItem().setMaxDamage(20);
			mainItem.getItem().showDurabilityBar(mainItem);
		}

		face = FindLookedBlock.getBlockFace(player);

		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (face != null) {
				if (player.isSneaking()) {
					center = center.offset(face);
				}
			}
			if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

				int size = mainItem.stackSize;
				float growth = mainItem.getMaxDamage() - mainItem.getItemDamage();
				float growthPercent = growth / 20;

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
				blocksToChange.add(center);
				Random rnd = new Random();

				for (int i = -1 * (size - 1); i <= (size - 1); i++) {
					for (int j = -1 * (size - 1); j <= (size - 1); j++) {
						// the -1 <= below prevents "nipples"
						if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {
							if (rnd.nextFloat() <= growthPercent) {
								if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
									blocksToChange.add(new BlockPos(center.getX() + i, center.getY(), center.getZ() + j));
								}
								if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
									blocksToChange.add(new BlockPos(center.getX() + i, center.getY() + j, center.getZ()));
								}
								if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
									blocksToChange.add(new BlockPos(center.getX(), center.getY() + i, center.getZ() + j));
								}
							}
						}
					}
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
