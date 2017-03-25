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

	public static void changeGrowth(EntityPlayer player, int amount) {
		ItemStack mainItem = player.getHeldItemMainhand();
		mainItem.getItem().showDurabilityBar(mainItem);
		int newDamage = Math.min(Math.max(mainItem.getItemDamage() + amount, 0), 19);
		mainItem.setItemDamage(newDamage);
	}

	public static void material(EntityPlayer player) {
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
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					int size = mainItem.stackSize;
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
										if (yCheck < 1 && player.worldObj.getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
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
						BlockControl.changeBlocks(player, toReplace, false);
					}

				} else {
					player.addChatMessage(new TextComponentString("No material saved"));
				}

			}

		}
	}

	public static void blockstate(EntityPlayer player) {

		ItemStack mainItem = player.getHeldItemMainhand();
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
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

				int size = mainItem.stackSize;
				float growth = mainItem.getMaxDamage() - mainItem.getItemDamage();
				float growthPercent = growth / 20;

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
				blocksToChange.add(center);
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
									if (yCheck < 1 && player.worldObj.getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
										yCheck++;
										blocksToChange.add(tempPos.add(sapFix));
									}

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
