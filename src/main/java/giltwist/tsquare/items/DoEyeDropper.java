package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoEyeDropper {

	public static void activate(EntityPlayer player, boolean fullBlockState) {

		if (!player.isSwingInProgress) {

			String itemUnlocal;
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {
				if (player.getHeldItemOffhand() == null) {
					itemUnlocal = "EmptyHand";
				} else {
					itemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
				}

				switch (itemUnlocal) {

				case "item.bucketWater":
					if (player.isSneaking()) {
						player.sendMessage(new TextComponentString("Replace Material: Water"));
						player.getEntityData().setString("TSquareReplaceMaterial", "minecraft:water");						
					} else {
						player.sendMessage(new TextComponentString("Place Material: Water"));
						player.getEntityData().setString("TSquarePlaceMaterial", "minecraft:water");
					}
					break;
				case "item.bucketLava":
					if (player.isSneaking()) {
						player.sendMessage(new TextComponentString("Replace Material: Lava"));
						player.getEntityData().setString("TSquareReplaceMaterial", "minecraft:lava");						
					} else {
						player.sendMessage(new TextComponentString("Place Material: Lava"));
						player.getEntityData().setString("TSquarePlaceMaterial", "minecraft:lava");
					}
					break;
				default:
					if (fullBlockState) {
						if (player.isSneaking()) {

							player.sendMessage(new TextComponentString("Replace Blockstate: " + player.getEntityWorld().getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquareReplaceState", player.getEntityWorld().getBlockState(targetBlock).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(targetBlock)));
							player.getEntityData().setString("TSquareReplaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());

						} else {
							player.sendMessage(new TextComponentString("Place Blockstate: " + player.getEntityWorld().getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquarePlaceState", player.getEntityWorld().getBlockState(targetBlock).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(targetBlock)));
							player.getEntityData().setString("TSquarePlaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());

						}

					} else {
						if (player.isSneaking()) {

							player.sendMessage(new TextComponentString("Replace Material: " + player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquareReplaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());
						} else {
							player.sendMessage(new TextComponentString("Place Material: " + player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquarePlaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());
						}
					}
					break;

				}
			}
		}
	}
}
