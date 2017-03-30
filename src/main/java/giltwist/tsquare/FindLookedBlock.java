package giltwist.tsquare;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class FindLookedBlock {
	
	public static BlockPos getBlockPos(EntityPlayer player){
		
		//float eyeHeight = player.getEyeHeight();
		//BlockPos playerPos = player.getPosition();
	    BlockPos result = null;
	    BlockPos searchBlock;
	   // Vec3d playerLookVec = player.getLookVec();
	   // int i = 1;
	  //  int maxDistance = 200; //in meters
	 //   int stepsPerBlock = 100; 
	    
	    Vec3d eyes=player.getPositionVector().addVector(0, player.eyeHeight, 0);
	    Vec3d look=player.getLookVec().scale(200);
	    
	    RayTraceResult rtr =  player.getEntityWorld().rayTraceBlocks(eyes, eyes.add(look), true, false, false);
		   
		   
		   if (rtr!=null){
			   searchBlock=rtr.getBlockPos();
		    if (player.worldObj.getBlockState(searchBlock).getMaterial()!=Material.AIR){
		    	result=searchBlock;
		    }
		    }
	   
	    return result;
		
	}
	
	public static EnumFacing getBlockFace(EntityPlayer player){
		
		EnumFacing result=null;
		Vec3d eyes=player.getPositionVector().addVector(0, player.eyeHeight, 0);
	    Vec3d look=player.getLookVec().scale(200);
	    
	    RayTraceResult rtr =  player.getEntityWorld().rayTraceBlocks(eyes, eyes.add(look), true, false, false);
		result=rtr.sideHit;
		
		
		return result;
		
	}

}
