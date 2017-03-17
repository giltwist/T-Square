package giltwist.tsquare;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

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
	    
	    RayTraceResult rtr = player.rayTrace(200, 1.0F);
	    searchBlock=rtr.getBlockPos();
	    if (player.worldObj.getBlockState(searchBlock).getMaterial()!=Material.AIR){
	    	result=searchBlock;
	    	
	    }
	    

	      
	    
	    
	    return result;
		
	}
	
	public static EnumFacing getBlockFace(EntityPlayer player){
		
		EnumFacing result=null;
		RayTraceResult rtr = player.rayTrace(200, 1.0F);
		result=rtr.sideHit;
		
		
		return result;
		
	}

}
