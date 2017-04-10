package giltwist.tsquare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		
		Map<BlockPos,IBlockState> undoInfo=new HashMap<BlockPos,IBlockState>();
		
		for (BlockPos b:toReplace) {
			
			undoInfo.put(b, player.getEntityWorld().getBlockState(b));
		}
		
		Thread fileIOThread = new Thread(new TSquareUndoWriter(player, undoInfo));
		fileIOThread.start();
		

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
					// player.addChatMessage(new TextComponentString("Replace
					// Mode: " + replaceMode));
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
			if (offhandItemUnlocal.equalsIgnoreCase("item.milk")) {
				replaceMode = "milk";
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
					if (replaceMode.equalsIgnoreCase("all") || replaceMode.equalsIgnoreCase("air")|| replaceMode.equalsIgnoreCase("milk") || replaceMode.equalsIgnoreCase("water") || replaceMode.equalsIgnoreCase("lava") || player.getEntityData().hasKey("TSquareReplaceMaterial")) {

						String tempmat;
						int tempmeta;
				

						for (BlockPos b:toReplace) {

						

							switch (replaceMode) {
							case "m":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "mx":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "s":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								tempmeta = player.getEntityWorld().getBlockState(b).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(b));
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat) && tempmeta == player.getEntityData().getInteger("TSquareReplaceState")) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "sx":
								replacemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquareReplaceMaterial"));
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								tempmeta = player.getEntityWorld().getBlockState(b).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(b));
								if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat) || tempmeta != player.getEntityData().getInteger("TSquareReplaceState")) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "air":
								replacemat = net.minecraft.block.Block.getBlockFromName("minecraft:air");
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								if (replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "milk": // anything NOT air
								replacemat = net.minecraft.block.Block.getBlockFromName("minecraft:air");
								tempmat = player.getEntityWorld().getBlockState(b).getBlock().getRegistryName().toString();
								if (!replacemat.getRegistryName().toString().equalsIgnoreCase(tempmat)) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "water":
								if (player.getEntityWorld().getBlockState(b).getMaterial() == Material.WATER) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							case "lava":
								if (player.getEntityWorld().getBlockState(b).getMaterial() == Material.LAVA) {
									player.getEntityWorld().setBlockState(b, placematState);
								}
								break;
							default: // Replace all blocks
								player.getEntityWorld().setBlockState(b, placematState);
								break;

							}

						}
						
						
						//player.getEntityWorld().markBlockRangeForRenderUpdate(minPosX, minPosY, minPosZ, maxPosX, maxPosY, maxPosZ);
						//DoLoadArea.activate(player);
					} else {
						player.sendMessage(new TextComponentString("Error: No replace material saved"));
					}

				} else {
					player.sendMessage(new TextComponentString("Error: No replace blockstate saved"));
				}
			} else {
				player.sendMessage(new TextComponentString("Error: No place blockstate saved"));
			}
		} else {
			player.sendMessage(new TextComponentString("Error: No place material saved"));
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
		int minPosX = toReplace[0].getX();
		int minPosY = toReplace[0].getY();
		int minPosZ = toReplace[0].getZ();
		int maxPosX = toReplace[0].getX();
		int maxPosY = toReplace[0].getY();
		int maxPosZ = toReplace[0].getZ();

		for (int i = 0; i < toReplace.length; i++) {

			if (toReplace[i].getX() < minPosX) {
				minPosX = toReplace[i].getX();
			}
			if (toReplace[i].getY() < minPosY) {
				minPosY = toReplace[i].getY();
			}
			if (toReplace[i].getZ() < minPosZ) {
				minPosZ = toReplace[i].getZ();
			}

			if (toReplace[i].getX() > maxPosX) {
				maxPosX = toReplace[i].getX();
			}
			if (toReplace[i].getY() > maxPosY) {
				maxPosY = toReplace[i].getY();
			}
			if (toReplace[i].getZ() > maxPosZ) {
				maxPosZ = toReplace[i].getZ();
			}

			neighbors = new HashMap<IBlockState, Integer>();
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					for (int c = -1; c <= 1; c++) {

						tempState = player.getEntityWorld().getBlockState(new BlockPos(toReplace[i].getX() + a, toReplace[i].getY() + b, toReplace[i].getZ() + c));

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
				bestNeighbor[i] = player.getEntityWorld().getBlockState(toReplace[i]);

			} else {
				bestNeighbor[i] = tempState;

			}

		}

		// Do the replacements
		for (int i = 0; i < toReplace.length; i++) {
			player.getEntityWorld().setBlockState(toReplace[i], bestNeighbor[i]);
		}
		player.getEntityWorld().markBlockRangeForRenderUpdate(minPosX, minPosY, minPosZ, maxPosX, maxPosY, maxPosZ);

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
		int minPosX = toReplace[0].getX();
		int minPosY = toReplace[0].getY();
		int minPosZ = toReplace[0].getZ();
		int maxPosX = toReplace[0].getX();
		int maxPosY = toReplace[0].getY();
		int maxPosZ = toReplace[0].getZ();

		for (int i = 0; i < toReplace.length; i++) {

			if (toReplace[i].getX() < minPosX) {
				minPosX = toReplace[i].getX();
			}
			if (toReplace[i].getY() < minPosY) {
				minPosY = toReplace[i].getY();
			}
			if (toReplace[i].getZ() < minPosZ) {
				minPosZ = toReplace[i].getZ();
			}

			if (toReplace[i].getX() > maxPosX) {
				maxPosX = toReplace[i].getX();
			}
			if (toReplace[i].getY() > maxPosY) {
				maxPosY = toReplace[i].getY();
			}
			if (toReplace[i].getZ() > maxPosZ) {
				maxPosZ = toReplace[i].getZ();
			}

			neighbors = new HashMap<IBlockState, Integer>();
			neighbors.put(net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState(), 0);
			for (EnumFacing f : EnumFacing.values()) {

				tempState = player.getEntityWorld().getBlockState(toReplace[i].offset(f));

				if (tempState.getBlock() == net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
					airCheat = -1;
				} else {
					airCheat = 1;
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

			if (player.getEntityWorld().getBlockState(toReplace[i]) == net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState()) {

				if (countMax < minNonAirNeighborsToGrow) {
					bestNeighbor[i] = player.getEntityWorld().getBlockState(toReplace[i]);

				} else {

					bestNeighbor[i] = tempState;

				}
			} else {

				if (Math.abs(neighbors.get(net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState())) >= minAirFacesToMelt) {

					bestNeighbor[i] = net.minecraft.block.Block.getBlockFromName("minecraft:air").getDefaultState();
				} else {

					bestNeighbor[i] = player.getEntityWorld().getBlockState(toReplace[i]);
				}

			}

		}

		// Do the replacements
		for (int i = 0; i < toReplace.length; i++) {
			player.getEntityWorld().setBlockState(toReplace[i], bestNeighbor[i]);
		}
		player.getEntityWorld().markBlockRangeForRenderUpdate(minPosX, minPosY, minPosZ, maxPosX, maxPosY, maxPosZ);

	}

	@SuppressWarnings("deprecation")
	public static void rollbackMostRecent(EntityPlayer player) {

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp time = new Timestamp(now.getTime());
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
		File undoFileRollback = new File(undoPath);
		if (!undoFileRollback.exists() || !undoFileRollback.isDirectory()) {
			undoFileRollback.mkdir();
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
			if (time.getTime() - 3600000 > Long.parseLong(bits[1])) {
				//delete if older than an hour
				file.delete();

			} else {
				if (bits[0].equalsIgnoreCase(player.getName()) && Long.parseLong(bits[1]) > newestUndo) {
					newestUndo = Long.parseLong(bits[1]);
				}
			}
		}

		if (newestUndo == 0) {

			player.sendMessage(new TextComponentString("Nothing to undo."));
		} else {
			try {
				String rollbackPath = undoPath + player.getName() + "@" + newestUndo;
				List<String> undoInfo = Files.readAllLines(Paths.get(rollbackPath));
				String[] currentUndo;
				String[] firstUndo = undoInfo.get(0).split(",");
				
				
				int minPosX = Integer.parseInt(firstUndo[0]);
				int minPosY = Integer.parseInt(firstUndo[1]);
				int minPosZ = Integer.parseInt(firstUndo[2]);
				int maxPosX = Integer.parseInt(firstUndo[0]);
				int maxPosY = Integer.parseInt(firstUndo[1]);
				int maxPosZ = Integer.parseInt(firstUndo[2]);

				for (int i = 0; i < undoInfo.size(); i++) {

					currentUndo = undoInfo.get(i).split(",");
					undoPos = new BlockPos(Integer.parseInt(currentUndo[0]), Integer.parseInt(currentUndo[1]), Integer.parseInt(currentUndo[2]));
					undoMaterial = net.minecraft.block.Block.getBlockFromName(currentUndo[3]);
					undoState = undoMaterial.getStateFromMeta(Integer.parseInt(currentUndo[4]));
					player.getEntityWorld().setBlockState(undoPos, undoState);
					
					if (undoPos.getX() < minPosX) {
						minPosX = undoPos.getX();
					}
					if (undoPos.getY() < minPosY) {
						minPosY = undoPos.getY();
					}
					if (undoPos.getZ() < minPosZ) {
						minPosZ = undoPos.getZ();
					}

					if (undoPos.getX() > maxPosX) {
						maxPosX = undoPos.getX();
					}
					if (undoPos.getY() > maxPosY) {
						maxPosY = undoPos.getY();
					}
					if (undoPos.getZ() > maxPosZ) {
						maxPosZ = undoPos.getZ();
					}

				}
				Files.deleteIfExists(Paths.get(rollbackPath));
				player.getEntityWorld().markBlockRangeForRenderUpdate(minPosX, minPosY, minPosZ, maxPosX, maxPosY, maxPosZ);
			} catch (IOException ioe) {
				player.sendMessage(new TextComponentString(ioe.getMessage()));
			}
		}

	}

}
