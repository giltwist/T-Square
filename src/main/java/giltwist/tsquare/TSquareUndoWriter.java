package giltwist.tsquare;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class TSquareUndoWriter implements Runnable {
	
	private EntityPlayer player;
	private Map<BlockPos,IBlockState> undoInfo;
	
	  public TSquareUndoWriter(EntityPlayer playerIn, Map<BlockPos,IBlockState> undoInfoIn) {
		    
		  this.player=playerIn;
		  this.undoInfo=undoInfoIn;
		  	  
		  }
	
	 @Override
	    public void run () 
	    {
		 
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
				String toLog = "";
				BlockPos tempPos=null;
				IBlockState tempState=null;
				int i=0;
				FileOutputStream fop = new FileOutputStream(undoLog);
				for (Map.Entry<BlockPos,IBlockState> e:undoInfo.entrySet()) {
					tempPos= e.getKey();
					tempState= e.getValue();
					if (i != undoInfo.size() - 1) {
						
						toLog = tempPos.getX() + "," + tempPos.getY() + "," + tempPos.getZ() + "," + tempState.getBlock().getRegistryName().toString() + ","
								+ tempState.getBlock().getMetaFromState(tempState) + "\n";
					} else {
						toLog = tempPos.getX() + "," + tempPos.getY() + "," + tempPos.getZ() + "," + tempState.getBlock().getRegistryName().toString() + ","
								+ tempState.getBlock().getMetaFromState(tempState);
					}
					i++;
					fop.write(toLog.getBytes());
					
				}
				
				
				fop.flush();
				fop.close();
				
				
			} catch (IOException ioe) {
				player.addChatMessage(new TextComponentString(ioe.getMessage()));
			}
		 
	    }

}
