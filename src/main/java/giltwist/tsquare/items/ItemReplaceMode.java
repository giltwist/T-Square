package giltwist.tsquare.items;

import giltwist.tsquare.TSquare;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemReplaceMode extends Item {

	public ItemReplaceMode() {
		setRegistryName("tsquareReplaceMode");
		setUnlocalizedName("tsquareReplaceMode");
		setCreativeTab(TSquare.creativeTab);
		GameRegistry.register(this);
		
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelResourceLocation allModel = new ModelResourceLocation(getRegistryName() + "_all", "inventory");
		ModelResourceLocation mModel = new ModelResourceLocation(getRegistryName() + "_m", "inventory");
		ModelResourceLocation mxModel = new ModelResourceLocation(getRegistryName() + "_mx", "inventory");
		ModelResourceLocation sModel = new ModelResourceLocation(getRegistryName() + "_s", "inventory");
		ModelResourceLocation sxModel = new ModelResourceLocation(getRegistryName() + "_sx", "inventory");
		

		ModelBakery.registerItemVariants(this, allModel, mModel, mxModel, sModel, sxModel);

		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				ModelResourceLocation whichModel;
				if (getTagCompoundSafe(stack).hasKey("mode")) {
					switch (getTagCompoundSafe(stack).getString("mode")) {
					case "m":
						whichModel = mModel;
						break;
					case "mx":
						whichModel = mxModel;
						break;
					case "s":
						whichModel = sModel;
						break;
					case "sx":
						whichModel = sxModel;
						break;
					default:
						whichModel = allModel;
						break;
					}
				} else {
					whichModel = allModel;
				}
				return whichModel;
			}
		});
	}


	//@Override
	//public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer playerIn, EnumHand hand) {
	//	return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	//}

	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			stack.setTagCompound(tagCompound);
		}
		return tagCompound;
	}
}
