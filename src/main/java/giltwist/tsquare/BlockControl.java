package giltwist.tsquare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
				if (i != toReplace.length - 1) {
					toLog = toReplace[i].getX() + "," + toReplace[i].getY() + "," + toReplace[i].getZ() + "," + player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString() + ","
							+ player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i])) + "\n";
				} else {
					toLog = toReplace[i].getX() + "," + toReplace[i].getY() + "," + toReplace[i].getZ() + "," + player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString() + ","
							+ player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i]));
				}
				Files.write(Paths.get(undoLog.getAbsolutePath()), toLog.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
		} catch (IOException ioe) {
			player.addChatMessage(new TextComponentString(ioe.getMessage()));
		}

	}

	@SuppressWarnings("deprecation")
	public static void changeBlocks(EntityPlayer player, BlockPos[] toReplace, boolean completeState) {
		Block placemat = null;
		IBlockState placematState = null;
		Block replacemat =null;
		//IBlockState replacematState = null;

		ItemStack offhandItem = player.getHeldItemOffhand();
		String offhandItemUnlocal;
		String replaceMode = "all";
		boolean hasPlaceStateIfNeeded=true;
		boolean hasReplaceStateIfNeeded=true;
		

		if (offhandItem == null) {
			offhandItemUnlocal = "EmptyOffhand";
		} else {
			offhandItemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();

		}
		
		if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {
			
			if (offhandItemUnlocal.equalsIgnoreCase("item.tsquareEraser")) {
				placemat = net.minecraft.block.Block.getBlockFromName("minecraft:air");
				if (offhandItem.hasTagCompound() && offhandItem.getTagCompound().hasKey("mode")) {
					replaceMode = offhandItem.getTagCompound().getString("mode");
					player.addChatMessage(new TextComponentString("Replace Mode: " + replaceMode));
				}

			} else {
				placemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquarePlaceMaterial"));
			}

			if (offhandItemUnlocal.equalsIgnoreCase("item.tsquareReplaceMode")) {
				if (offhandItem.hasTagCompound() && offhandItem.getTagCompound().hasKey("mode")) {
					replaceMode = offhandItem.getTagCompound().getString("mode");
					//player.addChatMessage(new TextComponentString("Replace Mode: " + replaceMode));
					
						if (!player.getEntityData().hasKey("TSquareReplaceState") && (replaceMode.equalsIgnoreCase("s")||replaceMode.equalsIgnoreCase("sx"))){
							hasReplaceStateIfNeeded=false;
						}
					
				}
			}

			if (completeState) {
				if (player.getEntityData().hasKey("TSquarePlaceState")){
				placematState = placemat.getStateFromMeta(player.getEntityData().getInteger("TSquarePlaceState"));
				}else{
				hasPlaceStateIfNeeded=false;
				}
			} else {
				placematState = placemat.getDefaultState();
			}

			if (hasPlaceStateIfNeeded){
				if (hasReplaceStateIfNeeded){
					if(replaceMode.equalsIgnoreCase("all")||player.getEntityData().hasKey("TSquareReplaceMaterial")){
										
				String tempmat;
				int tempmeta;
			for (int i = 0; i < toReplace.length; i++) {

				switch (replaceMode) {
				case "m":
					replacemat=net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
					tempmat=player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
					if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)){
						player.worldObj.setBlockState(toReplace[i], placematState);
					}
					break;
				case "mx":
					replacemat=net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
					tempmat=player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
					if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)){
						player.worldObj.setBlockState(toReplace[i], placematState);
					}
					break;
				case "s":
					replacemat=net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
					tempmat=player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
					tempmeta=player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i]));
					if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)&&tempmeta==player.getEntityData().getInteger("TSquareReplaceState")){
						player.worldObj.setBlockState(toReplace[i], placematState);
					}
					break;
				case "sx":
					replacemat=net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
					tempmat=player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
					tempmeta=player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i]));
					if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)||tempmeta!=player.getEntityData().getInteger("TSquareReplaceState")){
						player.worldObj.setBlockState(toReplace[i], placematState);
					}
					break;
				default: // Replace all blocks
					player.worldObj.setBlockState(toReplace[i], placematState);
					break;

				}

			}
					}else{
						player.addChatMessage(new TextComponentString("Error: No replace material saved"));
					}
			
				}else{
					player.addChatMessage(new TextComponentString("Error: No replace blockstate saved"));
				}
			} else{
				player.addChatMessage(new TextComponentString("Error: No place blockstate saved"));
			}
		} else{
			player.addChatMessage(new TextComponentString("Error: No place material saved"));
		}

	}

	@SuppressWarnings("deprecation")
	public static void rollbackMostRecent(EntityPlayer player) {

		File rootDirectory = player.getServer().getDataDirectory();
		String configPath = rootDirectory.getAbsolutePath() + File.separator + "config" + File.separator;
		String tsquarePath = configPath + "T-Square" + File.separator;
		String undoPath = tsquarePath + "undo" + File.separator;
		Block undoMaterial;
		IBlockState undoState;
		BlockPos undoPos;

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

		long newestUndo = 0;
		File[] files = new File(undoPath).listFiles();
		String[] bits;
		for (File file : files) {
			if (!file.isFile())
				continue;

			bits = file.getName().split("@");
			//player.addChatMessage(new TextComponentString("Timestamp check: " + bits[1]));
			if (bits[0].equalsIgnoreCase(player.getName()) && Long.parseLong(bits[1]) > newestUndo) {
				newestUndo = Long.parseLong(bits[1]);
			}
		}

		if (newestUndo == 0) {

			player.addChatMessage(new TextComponentString("Nothing to undo."));
		} else {
			try {
				String rollbackPath = undoPath + player.getName() + "@" + newestUndo;
				List<String> undoInfo = Files.readAllLines(Paths.get(rollbackPath));
				String[] currentUndo;

				for (int i = 0; i < undoInfo.size(); i++) {

					currentUndo = undoInfo.get(i).split(",");
					undoPos = new BlockPos(Integer.parseInt(currentUndo[0]), Integer.parseInt(currentUndo[1]), Integer.parseInt(currentUndo[2]));
					undoMaterial = net.minecraft.block.Block.getBlockFromName(currentUndo[3]);
					undoState = undoMaterial.getStateFromMeta(Integer.parseInt(currentUndo[4]));
					player.worldObj.setBlockState(undoPos, undoState);

				}
				Files.deleteIfExists(Paths.get(rollbackPath));
			} catch (IOException ioe) {
				player.addChatMessage(new TextComponentString(ioe.getMessage()));
			}
		}

	}

}
