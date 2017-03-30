package giltwist.tsquare.proxy;

import giltwist.tsquare.TSquare;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(TSquare.MODID + ":" + id, "inventory"));
	}

	public void registerModifierRenderer(Item item, String registryName) {

		ModelResourceLocation allModel = new ModelResourceLocation(registryName + "_all", "inventory");
		ModelResourceLocation mModel = new ModelResourceLocation(registryName + "_m", "inventory");
		ModelResourceLocation mxModel = new ModelResourceLocation(registryName + "_mx", "inventory");
		ModelResourceLocation sModel = new ModelResourceLocation(registryName + "_s", "inventory");
		ModelResourceLocation sxModel = new ModelResourceLocation(registryName + "_sx", "inventory");

		ModelBakery.registerItemVariants(item, allModel, mModel, mxModel, sModel, sxModel);

		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
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

	// @Override
	// public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World
	// world, EntityPlayer playerIn, EnumHand hand) {
	// return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	// }

	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			stack.setTagCompound(tagCompound);
		}
		return tagCompound;
	}

}