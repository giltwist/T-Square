package giltwist.tsquare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class BlockControl {

	public static void logUndo(EntityPlayer player, BlockPos[] toReplace) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp time = new Timestamp(now.getTime());
		File rootDirectory = player.getServer().getDataDirectory();
		String configPath = rootDirectory.getAbsolutePath() + File.separator + "config" + File.separator;
		String tsquarePath = configPath + "T-Square" + File.separator;
		String undoPath = tsquarePath + "undo" + File.separator;

		File configFile = new File(configPath);
		if (!configFile.exists() || !configFile.isDirectory()) {
			configFile.mkdir();
		}
		File tsquareFile = new File(tsquarePath);
		if (!tsquareFile.exists() || !tsquareFile.isDirectory()) {
			tsquareFile.mkdir();
		}
		File undoFile = new File(undoPath);
		if (!undoFile.exists() || !undoFile.isDirectory()) {
			undoFile.mkdir();
		}

		File undoLog = new File(undoPath + player.getName() + "@" + time.getTime());
		// player.addChatMessage(new
		// TextComponentString(undoLog.getAbsolutePath()));

		try {
			undoLog.createNewFile();
			String toLog = null;
			for (int i = 0; i < toReplace.length; i++) {
				toLog = toReplace[i].getX() + "," + toReplace[i].getY() + "," + toReplace[i].getZ() + "," + player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString() + ","
						+ player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i])) + ";\n";
				Files.write(Paths.get(undoLog.getAbsolutePath()), toLog.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
		} catch (IOException ioe) {
			player.addChatMessage(new TextComponentString(ioe.getMessage()));
		}

	}

	@SuppressWarnings("deprecation")
	public static void changeBlocks(EntityPlayer player, BlockPos[] toReplace, boolean completeState) {
		Block placemat;
		IBlockState placematState;
		placemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquarePlaceMaterial"));
		if (completeState) {
			placematState = placemat.getStateFromMeta(player.getEntityData().getInteger("TSquarePlaceState"));
		} else {
			placematState = placemat.getDefaultState();
		}
		
		for (int i = 0; i < toReplace.length; i++) {
			player.worldObj.setBlockState(toReplace[i], placematState);
			
		}

	}

}
