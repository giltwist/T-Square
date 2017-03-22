package giltwist.tsquare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
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
		Block replacemat = null;
		// IBlockState replacematState = null;

		ItemStack offhandItem = player.getHeldItemOffhand();
		String offhandItemUnlocal;
		String replaceMode = "all";
		boolean hasPlaceStateIfNeeded = true;
		boolean hasReplaceStateIfNeeded = true;

		if (offhandItem == null) {
			offhandItemUnlocal = "EmptyOffhand";
		} else {
			offhandItemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
			// player.addChatMessage(new TextComponentString("Offhand: " +
			// offhandItemUnlocal));

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

					if (!player.getEntityData().hasKey("TSquareReplaceState") && (replaceMode.equalsIgnoreCase("s") || replaceMode.equalsIgnoreCase("sx"))) {
						hasReplaceStateIfNeeded = false;
					}

				}
			}

			if (offhandItemUnlocal.equalsIgnoreCase("item.bucket")) {
				replaceMode = "air";
			}
			if (offhandItemUnlocal.equalsIgnoreCase("item.bucketWater")) {

				replaceMode = "water";
			}
			if (offhandItemUnlocal.equalsIgnoreCase("item.bucketLava")) {
				replaceMode = "lava";
			}

			if (completeState) {
				if (player.getEntityData().hasKey("TSquarePlaceState")) {
					placematState = placemat.getStateFromMeta(player.getEntityData().getInteger("TSquarePlaceState"));
				} else {
					hasPlaceStateIfNeeded = false;
				}
			} else {
				placematState = placemat.getDefaultState();
			}

			if (hasPlaceStateIfNeeded) {
				if (hasReplaceStateIfNeeded) {
					if (replaceMode.equalsIgnoreCase("all") || replaceMode.equalsIgnoreCase("air") || replaceMode.equalsIgnoreCase("water") || replaceMode.equalsIgnoreCase("lava") || player.getEntityData().hasKey("TSquareReplaceMaterial")) {

						String tempmat;
						int tempmeta;
						for (int i = 0; i < toReplace.length; i++) {

							switch (replaceMode) {
							case "m":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "mx":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
								if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "s":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
								tempmeta = player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i]));
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat) && tempmeta == player.getEntityData().getInteger("TSquareReplaceState")) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "sx":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
								tempmeta = player.worldObj.getBlockState(toReplace[i]).getBlock().getMetaFromState(player.worldObj.getBlockState(toReplace[i]));
								if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat) || tempmeta != player.getEntityData().getInteger("TSquareReplaceState")) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "air":
								replacemat = net.minecraft.block.Block.getBlockFromName("minecraft:air");
								tempmat = player.worldObj.getBlockState(toReplace[i]).getBlock().getRegistryName().toString();
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "water":
								if (player.worldObj.getBlockState(toReplace[i]).getMaterial() == Material.WATER) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							case "lava":
								if (player.worldObj.getBlockState(toReplace[i]).getMaterial() == Material.LAVA) {
									player.worldObj.setBlockState(toReplace[i], placematState);
								}
								break;
							default: // Replace all blocks
								player.worldObj.setBlockState(toReplace[i], placematState);
								break;

							}

						}
					} else {
						player.addChatMessage(new TextComponentString("Error: No replace material saved"));
					}

				} else {
					player.addChatMessage(new TextComponentString("Error: No replace blockstate saved"));
				}
			} else {
				player.addChatMessage(new TextComponentString("Error: No place blockstate saved"));
			}
		} else {
			player.addChatMessage(new TextComponentString("Error: No place material saved"));
		}

	}

	public static void blendBlocks(EntityPlayer player, BlockPos[] toReplace, boolean includeAir) {

		ItemStack offhandItem = player.getHeldItemOffhand();
		String offhandItemUnlocal;
		IBlockState[] bestNeighbor = new IBlockState[toReplace.length];
		boolean includeWater = false;
		boolean includeLava = false;

		if (offhandItem == null) {
			offhandItemUnlocal = "EmptyOffhand";
		} else {
			offhandItemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
			if (offhandItemUnlocal.equalsIgnoreCase("item.bucketLava")) {
				includeLava = true;
			}
		}

		if (player.isSneaking()) {
			includeWater = true;
		}

		Map<IBlockState, Integer> neighbors = new HashMap<IBlockState, Integer>();
		int tempCount;
		IBlockState tempState = null;
		int countMax;
		Integer[] neighborCounts;

		int tieCheck;

		// Do search
		for (int i = 0; i < toReplace.length; i++) {

			neighbors = new HashMap<IBlockState, Integer>();
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					for (int c = -1; c <= 1; c++) {

						tempState = player.worldObj.getBlockState(new BlockPos(toReplace[i].getX() + a, toReplace[i].getY() + b, toReplace[i].getZ() + c));

						if (neighbors.containsKey(tempState)) {
							tempCount = neighbors.get(tempState) + 1;
							neighbors.replace(tempState, tempCount);
						} else {
							neighbors.put(tempState, 1);
						}

						if (tempState.getBlock() == net.minecraft.block.Block.getBlockFromName("minecraft:air") && !includeAir) {
							neighbors.put(tempState, 0);
						}
						if (tempState.getMaterial() == Material.WATER && !includeWater) {
							neighbors.put(tempState, 0);
						}
						if (tempState.getMaterial() == Material.LAVA && !includeLava) {
							neighbors.put(tempState, 0);
						}

					}
				}
			}

			countMax = 0;
			neighborCounts = neighbors.values().toArray(new Integer[neighbors.values().size()]);
			for (Integer j : neighborCounts) {
				if (j > countMax) {
					countMax = j;
				}
			}

			tieCheck = 0;
			for (IBlockState n : neighbors.keySet()) {
				if (neighbors.get(n) == countMax) {
					tieCheck += 1;
					tempState = n;
				}
			}

			if (countMax == 0 || tieCheck != 1) {
				bestNeighbor[i] = player.worldObj.getBlockState(toReplace[i]);

			} else {
				bestNeighbor[i] = tempState;

			}

		}

		// Do the replacements
		for (

				int i = 0; i < toReplace.length; i++) {
			player.worldObj.setBlockState(toReplace[i], bestNeighbor[i]);
		}

	}

	public static void terraformBlocks(EntityPlayer player, BlockPos[] toReplace, int minAirFacesToMelt, int minNonAirNeighborsToGrow) {

		
		IBlockState[] bestNeighbor = new IBlockState[toReplace.length];
		
		Map<IBlockState, Integer> neighbors = new HashMap<IBlockState, Integer>();
		int tempCount;
		IBlockState tempState = null;
		int countMax;
		Integer[] neighborCounts;

		// Do search
		int airCheat;
		for (int i = 0; i < toReplace.length; i++) {

			neighbors = new HashMap<IBlockState, Integer>();
			neighbors.put(net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState(), 0);
			for (EnumFacing f : EnumFacing.values()) {

				tempState = player.worldObj.getBlockState(toReplace[i].offset(f));
				
				if (tempState.getBlock()==net.minecraft.block.Block.getBlockFromName("minecraft:air")){
					airCheat=-1;
				}else{
					airCheat=1;
				}

				if (neighbors.containsKey(tempState)) {
					tempCount = neighbors.get(tempState) + airCheat;
					neighbors.replace(tempState, tempCount);
				} else {
					neighbors.put(tempState, airCheat);
				}

			}

			countMax = 0;
			neighborCounts = neighbors.values().toArray(new Integer[neighbors.values().size()]);
			for (Integer j : neighborCounts) {
				if (j > countMax) {
					countMax = j;
				}
			}

			
			for (IBlockState n : neighbors.keySet()) {
				if (neighbors.get(n) == countMax) {
					tempState = n;
				}
			}

			if (player.worldObj.getBlockState(toReplace[i]) == net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState()) {
				
				if (countMax < minNonAirNeighborsToGrow) {
					bestNeighbor[i] = player.worldObj.getBlockState(toReplace[i]);
					

				} else {
					
					bestNeighbor[i] = tempState;

				}
			}else{
				
				
				if ( Math.abs(neighbors.get(net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState())) >=minAirFacesToMelt){
					
					bestNeighbor[i]=net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState();
				}else{
					
					bestNeighbor[i] = player.worldObj.getBlockState(toReplace[i]);
				}
				
			}

		}

		// Do the replacements
		for (int i = 0; i < toReplace.length; i++) {
			player.worldObj.setBlockState(toReplace[i], bestNeighbor[i]);
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
			// player.addChatMessage(new TextComponentString("Timestamp check: "
			// + bits[1]));
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
