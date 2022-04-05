package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWallDecoration_E extends DCTileModelBase {
	// fields
	private final ModelRenderer bb_main;

	public ModelWallDecoration_E() {
		textureWidth = 32;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -14.0F, 7.0F, 8, 10, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 19, 0, -3.5F, -13.0F, 6.0F, 2, 8, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, -0.5F, -10.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 1.0F, -10.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 2.5F, -10.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, -0.5F, -9.0F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 1.0F, -9.0F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 2.5F, -9.0F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, -0.5F, -7.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 1.0F, -7.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 2.5F, -7.5F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, -0.5F, -6.0F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 1.0F, -6.0F, 6.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, 2.5F, -6.0F, 6.5F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}