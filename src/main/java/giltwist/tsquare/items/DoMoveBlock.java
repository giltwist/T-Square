package giltwist.tsquare.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DoMoveBlock {

	public static void push(PlayerInteractEvent.LeftClickBlock event) {

		BlockPos movestart = event.getPos();
		BlockPos moveend = event.getPos().subtract(event.getFace().getDirectionVec());
		IBlockState moveblock = event.getWorld().getBlockState(movestart);

		if (event.getWorld().getBlockState(moveend).getMaterial() == net.minecraft.block.material.Material.AIR || event.getEntityPlayer().isSneaking()) {
			event.getWorld().setBlockToAir(movestart);

			event.getWorld().setBlockState(moveend, moveblock);

		}

	}

	public static void pull(PlayerInteractEvent.RightClickBlock event) {

		BlockPos movestart = event.getPos();
		BlockPos moveend = event.getPos().add(event.getFace().getDirectionVec());
		IBlockState moveblock = event.getWorld().getBlockState(movestart);

		if (event.getWorld().getBlockState(moveend).getMaterial() == net.minecraft.block.material.Material.AIR || event.getEntityPlayer().isSneaking()) {
			event.getWorld().setBlockToAir(movestart);

			event.getWorld().setBlockState(moveend, moveblock);

		}

	}

}
