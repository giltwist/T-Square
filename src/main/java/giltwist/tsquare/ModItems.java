package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ModifierBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	// Utilities
	@GameRegistry.ObjectHolder("tsquareMoveBlock")
	public static ItemBase tsquareMoveBlock;
	@GameRegistry.ObjectHolder("tsquareEyeDropper")
	public static ItemBase tsquareEyeDropper;
	@GameRegistry.ObjectHolder("tsquarePaintbrush")
	public static ItemBase tsquarePaintbrush;
	@GameRegistry.ObjectHolder("tsquareResetAll")
	public static ItemBase tsquareResetAll;
	@GameRegistry.ObjectHolder("tsquareRotateBlock")
	public static ItemBase tsquareRotateBlock;
	@GameRegistry.ObjectHolder("tsquareBlockInfo")
	public static ItemBase tsquareBlockInfo;
	@GameRegistry.ObjectHolder("tsquareUndo")
	public static ItemBase tsquareUndo;
	@GameRegistry.ObjectHolder("tsquareUp")
	public static ItemBase tsquareUp;
	@GameRegistry.ObjectHolder("tsquareLoadArea")
	public static ItemBase tsquareLoadArea;
	@GameRegistry.ObjectHolder("tsquareColorWheel")
	public static ItemBase tsquareColorWheel;
	@GameRegistry.ObjectHolder("tsquareGreenThumb")
	public static ItemBase tsquareGreenThumb;
	@GameRegistry.ObjectHolder("tsquareDelete")
	public static ItemBase tsquareDelete;
	@GameRegistry.ObjectHolder("tsquareRuler")
	public static ItemBase tsquareRuler;
	@GameRegistry.ObjectHolder("tsquareGetBlock")
	public static ItemBase tsquareGetBlock;

	@GameRegistry.ObjectHolder("tsquareReplaceMode")
	public static ModifierBase tsquareReplaceMode;
	@GameRegistry.ObjectHolder("tsquareEraser")
	public static ModifierBase tsquareEraser;

	// Basic Shapes
	@GameRegistry.ObjectHolder("tsquareSquareCenter")
	public static ItemBase tsquareSquareCenter;
	@GameRegistry.ObjectHolder("tsquareCircleCenter")
	public static ItemBase tsquareCircleCenter;
	@GameRegistry.ObjectHolder("tsquareCubeCenter")
	public static ItemBase tsquareCubeCenter;
	@GameRegistry.ObjectHolder("tsquareSphereCenter")
	public static ItemBase tsquareSphereCenter;
	@GameRegistry.ObjectHolder("tsquareOverlay")
	public static ItemBase tsquareOverlay;
	@GameRegistry.ObjectHolder("tsquareFillDown")
	public static ItemBase tsquareFillDown;
	@GameRegistry.ObjectHolder("tsquareHollow")
	public static ItemBase tsquareHollow;

	// Advanced Shapes
	@GameRegistry.ObjectHolder("tsquareCuboid2Corners")
	public static ItemBase tsquareCuboid2Corners;
	@GameRegistry.ObjectHolder("tsquareLine")
	public static ItemBase tsquareLine;
	@GameRegistry.ObjectHolder("tsquareTriangle3Corners")
	public static ItemBase tsquareTriangle3Corners;
	@GameRegistry.ObjectHolder("tsquareCircle3Points")
	public static ItemBase tsquareCircle3Points;

	// Randomized Shapes
	@GameRegistry.ObjectHolder("tsquareBlob")
	public static ItemBase tsquareBlob;
	@GameRegistry.ObjectHolder("tsquareSplatterSphere")
	public static ItemBase tsquareSplatterSphere;
	@GameRegistry.ObjectHolder("tsquareSplatterCircle")
	public static ItemBase tsquareSplatterCircle;
	@GameRegistry.ObjectHolder("tsquareSplatterOverlay")
	public static ItemBase tsquareSplatterOverlay;

	// Terraformers
	@GameRegistry.ObjectHolder("tsquareBlendCircle")
	public static ItemBase tsquareBlendCircle;
	@GameRegistry.ObjectHolder("tsquareBlendSphere")
	public static ItemBase tsquareBlendSphere;
	@GameRegistry.ObjectHolder("tsquareSmooth")
	public static ItemBase tsquareSmooth;
	@GameRegistry.ObjectHolder("tsquareMelt")
	public static ItemBase tsquareMelt;
	@GameRegistry.ObjectHolder("tsquareFill")
	public static ItemBase tsquareFill;
	@GameRegistry.ObjectHolder("tsquareGrow")
	public static ItemBase tsquareGrow;
	@GameRegistry.ObjectHolder("tsquareRandomTerraform")
	public static ItemBase tsquareRandomTerraform;

	
	@SideOnly(Side.CLIENT)
	public static void initModels() {

		tsquareBlendCircle.initModel();
		tsquareBlendSphere.initModel();
		tsquareBlob.initModel();
		tsquareBlockInfo.initModel();
		tsquareCircle3Points.initModel();
		tsquareCircleCenter.initModel();
		tsquareColorWheel.initModel();
		tsquareCubeCenter.initModel();
		tsquareCuboid2Corners.initModel();
		tsquareDelete.initModel();
		tsquareEyeDropper.initModel();
		tsquareFill.initModel();
		tsquareFillDown.initModel();
		tsquareGetBlock.initModel();
		tsquareGreenThumb.initModel();
		tsquareGrow.initModel();
		tsquareHollow.initModel();
		tsquareLine.initModel();
		tsquareLoadArea.initModel();
		tsquareMelt.initModel();
		tsquareMoveBlock.initModel();
		tsquareOverlay.initModel();
		tsquarePaintbrush.initModel();
		tsquareRandomTerraform.initModel();
		tsquareResetAll.initModel();
		tsquareRotateBlock.initModel();
		tsquareRuler.initModel();
		tsquareSmooth.initModel();
		tsquareSphereCenter.initModel();
		tsquareSplatterCircle.initModel();
		tsquareSplatterOverlay.initModel();
		tsquareSplatterSphere.initModel();
		tsquareSquareCenter.initModel();
		tsquareTriangle3Corners.initModel();
		tsquareUndo.initModel();
		tsquareUp.initModel();

		tsquareEraser.initModel();
		tsquareReplaceMode.initModel();

	}

}