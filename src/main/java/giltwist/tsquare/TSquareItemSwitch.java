package giltwist.tsquare;

import java.util.Random;

import giltwist.tsquare.items.DoBlend;
import giltwist.tsquare.items.DoBlob;
import giltwist.tsquare.items.DoBlockInfo;
import giltwist.tsquare.items.DoCircle3Points;
import giltwist.tsquare.items.DoCircleCenter;
import giltwist.tsquare.items.DoColorWheel;
import giltwist.tsquare.items.DoCubeCenter;
import giltwist.tsquare.items.DoCuboid2Corners;
import giltwist.tsquare.items.DoDelete;
import giltwist.tsquare.items.DoEraser;
import giltwist.tsquare.items.DoEyeDropper;
import giltwist.tsquare.items.DoFillDown;
import giltwist.tsquare.items.DoGreenThumb;
import giltwist.tsquare.items.DoLine;
import giltwist.tsquare.items.DoLoadArea;
import giltwist.tsquare.items.DoMoveBlock;
import giltwist.tsquare.items.DoOverlay;
import giltwist.tsquare.items.DoPaintbrush;
import giltwist.tsquare.items.DoReplaceMode;
import giltwist.tsquare.items.DoResetAll;
import giltwist.tsquare.items.DoRotateBlock;
import giltwist.tsquare.items.DoSphereCenter;
import giltwist.tsquare.items.DoSplatterCircle;
import giltwist.tsquare.items.DoSplatterOverlay;
import giltwist.tsquare.items.DoSplatterSphere;
import giltwist.tsquare.items.DoSquareCenter;
import giltwist.tsquare.items.DoTerraform;
import giltwist.tsquare.items.DoTriangle3Corners;
import giltwist.tsquare.items.DoUndo;
import giltwist.tsquare.items.DoUp;
import net.minecraft.entity.player.EntityPlayer;

public class TSquareItemSwitch {

	public static void whichAction(EntityPlayer player, boolean isTouchingBlock, boolean isRightClick) {

		if (player.getHeldItemMainhand() != null) {
			String itemUnlocal = player.getHeldItemMainhand().getUnlocalizedName();

			Random rnd = new Random();

			if (isTouchingBlock && isRightClick) {
				// only those that MUST touch block

				switch (itemUnlocal) {
				case "item.tsquareMoveBlock":
					DoMoveBlock.activate(player,isRightClick);
					break;
				case "item.tsquarePaintbrush":
					DoPaintbrush.activate(player,isRightClick);
					break;
				case "item.tsquareRotateBlock":
					DoRotateBlock.activate(player, isRightClick);
					break;
				case "item.tsquareColorWheel":
					if (player.isSneaking()) {
						DoColorWheel.setMeta(player, 15);
					} else {
						DoColorWheel.adjustMeta(player,1);
					}
					break;
				default:
					break;

				}

			} else {
				switch (itemUnlocal) {
				//all items

				case "item.tsquareBlendSphere":
					DoBlend.sphere(player, isRightClick);
					break;
				case "item.tsquareBlob":
					if (!player.isSneaking()) {
						DoBlob.activate(player, isRightClick);
					} else {
						DoBlob.changeGrowth(player, isRightClick);
					}
					break;
				case "item.tsquareBlockInfo":
					DoBlockInfo.activate(player, isRightClick);
					break;
				case "item.tsquareCircle3Points":
					if (player.isSneaking()) {
						DoCircle3Points.activate(player,isRightClick);
					} else {
						if (isRightClick){
							DoCircle3Points.clearPoints(player);	
						}else{
							DoCircle3Points.setPoint(player);
						}
						
					}
					break;
				case "item.tsquareCircleCenter":
					DoCircleCenter.activate(player, isRightClick);
					break;
				case "item.tsquareColorWheel":
					//MUST touch block
					if (isTouchingBlock&&!isRightClick){ 
							if (player.isSneaking()) {
								DoColorWheel.setMeta(player, 0);
								;
							} else {
								DoColorWheel.adjustMeta(player, -1);
							}
					}
					break;
				case "item.tsquareCubeCenter":
					DoCubeCenter.activate(player, isRightClick);
					break;
				case "item.tsquareCuboid2Corners":
					if (player.isSneaking()) {
						DoCuboid2Corners.activate(player, isRightClick);
					} else {
						DoCuboid2Corners.setPoint(player, isRightClick);
					}
					break;
				case "item.tsquareDelete":
					DoDelete.activate(player);
					break;
				case "item.tsquareEraser":
					DoEraser.activate(player, isRightClick);
					break;
				case "item.tsquareEyeDropper":
					DoEyeDropper.activate(player, isRightClick);
					break;
				case "item.tsquareFillDown":
					DoFillDown.activate(player, isRightClick);
					break;
				case "item.tsquareGreenThumb":
					DoGreenThumb.activate(player);
					break;
				case "item.tsquareLine":
					if (player.isSneaking()) {
						DoLine.activate(player,isRightClick);
					} else {
						DoLine.setPoint(player,isRightClick);
					}
					break;
				case "item.tsquareLoadArea":
					if (player.isSneaking()&&!isRightClick) {
						DoLoadArea.activate(player);
					} else {
						DoLoadArea.warn(player);
					}
					break;
				case "item.tsquareMoveBlock":
					//MUST touch block
					if (isTouchingBlock&&!isRightClick){ 
					DoMoveBlock.activate(player,isRightClick);
					}
					break;
				case "item.tsquareOverlay":
					DoOverlay.activate(player,isRightClick);
					break;
				case "item.tsquarePaintbrush":
					//MUST touch block
					if (isTouchingBlock&&!isRightClick){ 
					DoPaintbrush.activate(player, isRightClick);
					}
					break;
				case "item.tsquareReplaceMode":
					DoReplaceMode.activate(player,isRightClick);
					break;
				case "item.tsquareResetAll":
					if (player.isSneaking()&&!isRightClick) {
						DoResetAll.reset(player);
					} else {
						DoResetAll.warn(player);
					}
					break;
				case "item.tsquareRotateBlock":
					//MUST touch block
					if (isTouchingBlock&&!isRightClick){ 
					DoRotateBlock.activate(player, isRightClick);
					}
					break;
				case "item.tsquareSphereCenter":
					DoSphereCenter.activate(player,isRightClick);
					break;
				case "item.tsquareSplatterCircle":
					if (!player.isSneaking()) {
						DoSplatterCircle.activate(player,isRightClick);
					} else {
						DoSplatterCircle.changeGrowth(player, isRightClick);
					}
					break;
				case "item.tsquareSplatterOverlay":
					if (!player.isSneaking()) {
						DoSplatterOverlay.activate(player,isRightClick);
					} else {
						DoSplatterOverlay.changeGrowth(player, isRightClick);
					}
					break;
				case "item.tsquareSplatterSphere":
					if (!player.isSneaking()) {
						DoSplatterSphere.activate(player,isRightClick);
					} else {
						DoSplatterSphere.changeGrowth(player, isRightClick);
					}
					break;
				case "item.tsquareSquareCenter":
					DoSquareCenter.activate(player,isRightClick);
					break;
					
					// Start Terraform
					
				case "item.tsquareFill":
					
					if (isRightClick){
						DoTerraform.sphere(player, 7, 4); // weak fill
					}else{
					DoTerraform.sphere(player, 7, 3); // strong
					}
					break;
				case "item.tsquareGrow":
					if (isRightClick){
						DoTerraform.sphere(player, 7, 2); // weak grow
					}else{
					DoTerraform.sphere(player, 7, 1); // strong
					}
					break;
				case "item.tsquareMelt":
					if (isRightClick){	
						DoTerraform.sphere(player, 2, 7); // weak melt
					}else{
					DoTerraform.sphere(player, 1, 7); // strong
					}
					break;
				case "item.tsquareRandomTerraform":
					if (isRightClick){
						DoTerraform.sphere(player,rnd.nextInt(6)+1 , rnd.nextInt(3)+1);
					}else{
					DoTerraform.sphere(player, rnd.nextInt(3) + 1, rnd.nextInt(6) + 1); // strong
					}
					break;
				case "item.tsquareSmooth":
					if (isRightClick){
						DoTerraform.sphere(player, 5, 4); // weak
					}else{
					DoTerraform.sphere(player, 4, 3); // strong
					}
					break;
				
				
					//End Terraform
				case "item.tsquareTriangle3Corners":
					if (player.isSneaking()) {
						DoTriangle3Corners.activate(player,isRightClick);
					} else {
						if (isRightClick){
							DoTriangle3Corners.clearPoints(player);	
						}else{
							DoTriangle3Corners.setPoint(player);
						}
						
					}
					break;
				case "item.tsquareUp":
					DoUp.activate(player,isRightClick);
					break;
				case "item.tsquareUndo":
					if (player.isSneaking()&&!isRightClick) {
						DoUndo.activate(player);
					} else {
						DoUndo.warn(player);
					}
					break;

				default:

					break;
				}
			}
		}

	}

}
