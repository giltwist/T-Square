package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ModifierBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	// Utilities
	@GameRegistry.ObjectHolder("tsquare:tsquareMoveBlock")
	public static ItemBase tsquareMoveBlock;
	@GameRegistry.ObjectHolder("tsquare:tsquareEyeDropper")
	public static ItemBase tsquareEyeDropper;
	@GameRegistry.ObjectHolder("tsquare:tsquarePaintbrush")
	public static ItemBase tsquarePaintbrush;
	@GameRegistry.ObjectHolder("tsquare:tsquareResetAll")
	public static ItemBase tsquareResetAll;
	@GameRegistry.ObjectHolder("tsquare:tsquareRotateBlock")
	public static ItemBase tsquareRotateBlock;
	@GameRegistry.ObjectHolder("tsquare:tsquareBlockInfo")
	public static ItemBase tsquareBlockInfo;
	@GameRegistry.ObjectHolder("tsquare:tsquareUndo")
	public static ItemBase tsquareUndo;
	@GameRegistry.ObjectHolder("tsquare:tsquareUp")
	public static ItemBase tsquareUp;
	@GameRegistry.ObjectHolder("tsquare:tsquareLoadArea")
	public static ItemBase tsquareLoadArea;
	@GameRegistry.ObjectHolder("tsquare:tsquareColorWheel")
	public static ItemBase tsquareColorWheel;
	@GameRegistry.ObjectHolder("tsquare:tsquareGreenThumb")
	public static ItemBase tsquareGreenThumb;
	@GameRegistry.ObjectHolder("tsquare:tsquareDelete")
	public static ItemBase tsquareDelete;
	@GameRegistry.ObjectHolder("tsquare:tsquareRuler")
	public static ItemBase tsquareRuler;
	@GameRegistry.ObjectHolder("tsquare:tsquareGetBlock")
	public static ItemBase tsquareGetBlock;

	@GameRegistry.ObjectHolder("tsquare:tsquareReplaceMode")
	public static ModifierBase tsquareReplaceMode;
	@GameRegistry.ObjectHolder("tsquare:tsquareEraser")
	public static ModifierBase tsquareEraser;

	// Basic Shapes
	@GameRegistry.ObjectHolder("tsquare:tsquareSquareCenter")
	public static ItemBase tsquareSquareCenter;
	@GameRegistry.ObjectHolder("tsquare:tsquareCircleCenter")
	public static ItemBase tsquareCircleCenter;
	@GameRegistry.ObjectHolder("tsquare:tsquareCubeCenter")
	public static ItemBase tsquareCubeCenter;
	@GameRegistry.ObjectHolder("tsquare:tsquareSphereCenter")
	public static ItemBase tsquareSphereCenter;
	@GameRegistry.ObjectHolder("tsquare:tsquareOverlay")
	public static ItemBase tsquareOverlay;
	@GameRegistry.ObjectHolder("tsquare:tsquareFillDown")
	public static ItemBase tsquareFillDown;
	@GameRegistry.ObjectHolder("tsquare:tsquareHollow")
	public static ItemBase tsquareHollow;

	// Advanced Shapes
	@GameRegistry.ObjectHolder("tsquare:tsquareCuboid2Corners")
	public static ItemBase tsquareCuboid2Corners;
	@GameRegistry.ObjectHolder("tsquare:tsquareLine")
	public static ItemBase tsquareLine;
	@GameRegistry.ObjectHolder("tsquare:tsquareTriangle3Corners")
	public static ItemBase tsquareTriangle3Corners;
	@GameRegistry.ObjectHolder("tsquare:tsquareCircle3Points")
	public static ItemBase tsquareCircle3Points;

	// Randomized Shapes
	@GameRegistry.ObjectHolder("tsquare:tsquareBlob")
	public static ItemBase tsquareBlob;
	@GameRegistry.ObjectHolder("tsquare:tsquareSplatterSphere")
	public static ItemBase tsquareSplatterSphere;
	@GameRegistry.ObjectHolder("tsquare:tsquareSplatterCircle")
	public static ItemBase tsquareSplatterCircle;
	@GameRegistry.ObjectHolder("tsquare:tsquareSplatterOverlay")
	public static ItemBase tsquareSplatterOverlay;

	// Terraformers
	@GameRegistry.ObjectHolder("tsquare:tsquareBlendCircle")
	public static ItemBase tsquareBlendCircle;
	@GameRegistry.ObjectHolder("tsquare:tsquareBlendSphere")
	public static ItemBase tsquareBlendSphere;
	@GameRegistry.ObjectHolder("tsquare:tsquareSmooth")
	public static ItemBase tsquareSmooth;
	@GameRegistry.ObjectHolder("tsquare:tsquareMelt")
	public static ItemBase tsquareMelt;
	@GameRegistry.ObjectHolder("tsquare:tsquareFill")
	public static ItemBase tsquareFill;
	@GameRegistry.ObjectHolder("tsquare:tsquareGrow")
	public static ItemBase tsquareGrow;
	@GameRegistry.ObjectHolder("tsquare:tsquareRandomTerraform")
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