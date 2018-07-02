package com.reuxertz.fauna.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * CrabAdult - Cucho, CarrotJet
 * Created using Tabula 7.0.0
 */
public class ModelCrab extends ModelBase {
    public ModelRenderer bodyCore;
    public ModelRenderer thigh4Left;
    public ModelRenderer thigh4Right;
    public ModelRenderer bodyMiddle;
    public ModelRenderer bodyUnder;
    public ModelRenderer bodyRear;
    public ModelRenderer jawRight;
    public ModelRenderer jawLeft;
    public ModelRenderer bicepLeft;
    public ModelRenderer bicepRight;
    public ModelRenderer thigh1Right;
    public ModelRenderer thigh2Right;
    public ModelRenderer thigh3Right;
    public ModelRenderer thigh1Left;
    public ModelRenderer thigh2Left;
    public ModelRenderer thigh3Left;
    public ModelRenderer bodyTop;
    public ModelRenderer eyeRight;
    public ModelRenderer eyeLeft;
    public ModelRenderer forearmLeft;
    public ModelRenderer handLeft;
    public ModelRenderer fingerLeft;
    public ModelRenderer forearmRight;
    public ModelRenderer handRight;
    public ModelRenderer fingerRight;
    public ModelRenderer calf1Right;
    public ModelRenderer calf2Right;
    public ModelRenderer calf3Right;
    public ModelRenderer calf1Left;
    public ModelRenderer calf2Left;
    public ModelRenderer calf3Left;
    public ModelRenderer calf4Left;
    public ModelRenderer calf4Right;

    public ModelCrab() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.bodyRear = new ModelRenderer(this, 0, 13);
        this.bodyRear.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.bodyRear.addBox(-3.5F, -2.53F, 4.0F, 7, 2, 2, 0.0F);
        this.thigh1Left = new ModelRenderer(this, 50, 53);
        this.thigh1Left.setRotationPoint(4.0F, 1.5F, 0.0F);
        this.thigh1Left.addBox(0.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh1Left, 0.0F, 0.0F, -0.296705972839036F);
        this.calf1Left = new ModelRenderer(this, 27, 53);
        this.calf1Left.setRotationPoint(5.0F, -0.29F, 0.0F);
        this.calf1Left.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.handRight = new ModelRenderer(this, 82, 53);
        this.handRight.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.handRight.addBox(-1.0F, -0.5F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(handRight, 0.0F, -0.22689280275926282F, 0.0F);
        this.fingerRight = new ModelRenderer(this, 98, 53);
        this.fingerRight.setRotationPoint(0.9743700647852362F, 0.0F, -0.2249510543438653F);
        this.fingerRight.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(fingerRight, 0.0F, -0.24434609527920606F, 0.0F);
        this.calf4Right = new ModelRenderer(this, 27, 53);
        this.calf4Right.setRotationPoint(-4.707628295277264F, 0.9563047559630355F, 0.03F);
        this.calf4Right.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.thigh4Right = new ModelRenderer(this, 50, 53);
        this.thigh4Right.setRotationPoint(-3.0F, 22.5F, 4.5F);
        this.thigh4Right.addBox(-4.707628295277264F, -0.043695244036964453F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh4Right, 0.0F, 0.0F, 0.296705972839036F);
        this.thigh3Left = new ModelRenderer(this, 50, 53);
        this.thigh3Left.setRotationPoint(4.0F, 1.5F, 3.0F);
        this.thigh3Left.addBox(0.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh3Left, 0.0F, 0.0F, -0.296705972839036F);
        this.thigh1Right = new ModelRenderer(this, 50, 53);
        this.thigh1Right.setRotationPoint(-4.0F, 1.5F, 0.0F);
        this.thigh1Right.addBox(-5.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh1Right, 0.0F, 0.0F, 0.296705972839036F);
        this.eyeRight = new ModelRenderer(this, 0, 0);
        this.eyeRight.setRotationPoint(-2.0F, -1.0F, -2.33F);
        this.eyeRight.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(eyeRight, 0.0F, 0.0F, -0.41887902047863906F);
        this.calf3Right = new ModelRenderer(this, 27, 53);
        this.calf3Right.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.calf3Right.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.bicepLeft = new ModelRenderer(this, 110, 60);
        this.bicepLeft.mirror = true;
        this.bicepLeft.setRotationPoint(3.0F, 0.5F, -2.0F);
        this.bicepLeft.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.thigh3Right = new ModelRenderer(this, 50, 53);
        this.thigh3Right.setRotationPoint(-4.0F, 1.5F, 3.0F);
        this.thigh3Right.addBox(-5.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh3Right, 0.0F, 0.0F, 0.296705972839036F);
        this.calf2Right = new ModelRenderer(this, 27, 53);
        this.calf2Right.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.calf2Right.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.eyeLeft = new ModelRenderer(this, 0, 0);
        this.eyeLeft.setRotationPoint(2.0F, -1.0F, -2.33F);
        this.eyeLeft.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(eyeLeft, 0.0F, 0.0F, 0.41887902047863906F);
        this.calf2Left = new ModelRenderer(this, 27, 53);
        this.calf2Left.setRotationPoint(5.0F, -0.29F, 0.0F);
        this.calf2Left.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.bodyMiddle = new ModelRenderer(this, 0, 33);
        this.bodyMiddle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyMiddle.addBox(-4.97F, -1.47F, -3.17F, 10, 2, 7, 0.0F);
        this.thigh4Left = new ModelRenderer(this, 50, 53);
        this.thigh4Left.setRotationPoint(3.0F, 22.5F, 4.5F);
        this.thigh4Left.addBox(-0.2923717047227367F, -0.043695244036964453F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh4Left, 0.0F, -0.0F, -0.296705972839036F);
        this.thigh2Left = new ModelRenderer(this, 50, 53);
        this.thigh2Left.setRotationPoint(4.0F, 1.5F, 1.5F);
        this.thigh2Left.addBox(0.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh2Left, 0.0F, 0.0F, -0.296705972839036F);
        this.bodyCore = new ModelRenderer(this, 0, 20);
        this.bodyCore.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.bodyCore.addBox(-5.5F, -1.0F, -3.5F, 11, 2, 8, 0.0F);
        this.jawRight = new ModelRenderer(this, 0, 53);
        this.jawRight.setRotationPoint(-0.5F, 0.3299999999999983F, -3.0F);
        this.jawRight.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(jawRight, 0.2617993877991494F, 0.0F, -0.24434609527920617F);
        this.handLeft = new ModelRenderer(this, 82, 53);
        this.handLeft.mirror = true;
        this.handLeft.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.handLeft.addBox(-2.0F, -0.5F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(handLeft, 0.0F, 0.22689280275926282F, 0.0F);
        this.calf4Left = new ModelRenderer(this, 27, 53);
        this.calf4Left.setRotationPoint(4.707628295277264F, 0.6663047559630355F, 0.0F);
        this.calf4Left.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.forearmLeft = new ModelRenderer(this, 108, 51);
        this.forearmLeft.mirror = true;
        this.forearmLeft.setRotationPoint(4.5F, 0.0F, 0.5F);
        this.forearmLeft.addBox(-2.0F, -0.5F, -5.0F, 3, 2, 5, 0.0F);
        this.fingerLeft = new ModelRenderer(this, 98, 53);
        this.fingerLeft.mirror = true;
        this.fingerLeft.setRotationPoint(-0.9743700647852362F, 0.0F, -0.2249510543438653F);
        this.fingerLeft.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(fingerLeft, 0.0F, 0.24434609527920606F, 0.0F);
        this.thigh2Right = new ModelRenderer(this, 50, 53);
        this.thigh2Right.setRotationPoint(-4.0F, 1.5F, 1.5F);
        this.thigh2Right.addBox(-5.0F, -1.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(thigh2Right, 0.0F, 0.0F, 0.296705972839036F);
        this.calf1Right = new ModelRenderer(this, 27, 53);
        this.calf1Right.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.calf1Right.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.bodyUnder = new ModelRenderer(this, 0, 43);
        this.bodyUnder.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.bodyUnder.addBox(-3.5F, -0.4F, -3.0F, 7, 1, 4, 0.0F);
        this.bicepRight = new ModelRenderer(this, 110, 60);
        this.bicepRight.setRotationPoint(-3.0F, 0.5F, -2.0F);
        this.bicepRight.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.jawLeft = new ModelRenderer(this, 0, 53);
        this.jawLeft.setRotationPoint(0.47F, 0.3299999999999983F, -3.0F);
        this.jawLeft.addBox(0.0F, -1.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(jawLeft, 0.2617993877991494F, 0.0F, 0.24434609527920617F);
        this.forearmRight = new ModelRenderer(this, 108, 51);
        this.forearmRight.setRotationPoint(-4.5F, 0.0F, 0.5F);
        this.forearmRight.addBox(-1.0F, -0.5F, -5.0F, 3, 2, 5, 0.0F);
        this.bodyTop = new ModelRenderer(this, 0, 0);
        this.bodyTop.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.bodyTop.addBox(-3.5F, -3.0F, -3.0F, 7, 2, 7, 0.0F);
        this.calf3Left = new ModelRenderer(this, 27, 53);
        this.calf3Left.setRotationPoint(5.0F, -0.29F, 0.0F);
        this.calf3Left.addBox(-0.5F, -0.47F, -0.5F, 1, 3, 1, 0.0F);
        this.bodyCore.addChild(this.bodyRear);
        this.bodyCore.addChild(this.thigh1Left);
        this.thigh1Left.addChild(this.calf1Left);
        this.forearmRight.addChild(this.handRight);
        this.handRight.addChild(this.fingerRight);
        this.thigh4Right.addChild(this.calf4Right);
        this.bodyCore.addChild(this.thigh3Left);
        this.bodyCore.addChild(this.thigh1Right);
        this.bodyMiddle.addChild(this.eyeRight);
        this.thigh3Right.addChild(this.calf3Right);
        this.bodyCore.addChild(this.bicepLeft);
        this.bodyCore.addChild(this.thigh3Right);
        this.thigh2Right.addChild(this.calf2Right);
        this.bodyMiddle.addChild(this.eyeLeft);
        this.thigh2Left.addChild(this.calf2Left);
        this.bodyCore.addChild(this.bodyMiddle);
        this.bodyCore.addChild(this.thigh2Left);
        this.bodyCore.addChild(this.jawRight);
        this.forearmLeft.addChild(this.handLeft);
        this.thigh4Left.addChild(this.calf4Left);
        this.bicepLeft.addChild(this.forearmLeft);
        this.handLeft.addChild(this.fingerLeft);
        this.bodyCore.addChild(this.thigh2Right);
        this.thigh1Right.addChild(this.calf1Right);
        this.bodyCore.addChild(this.bodyUnder);
        this.bodyCore.addChild(this.bicepRight);
        this.bodyCore.addChild(this.jawLeft);
        this.bicepRight.addChild(this.forearmRight);
        this.bodyMiddle.addChild(this.bodyTop);
        this.thigh3Left.addChild(this.calf3Left);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.thigh4Right.render(f5);
        this.thigh4Left.render(f5);
        this.bodyCore.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

