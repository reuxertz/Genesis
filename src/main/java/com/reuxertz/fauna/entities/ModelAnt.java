package com.reuxertz.fauna.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AntWorkerIdle - Cucho, CarrotJet
 * Created using Tabula 7.0.0
 */
public class ModelAnt extends ModelBase {
    public ModelRenderer bodyAbdomen1A;
    public ModelRenderer legRightRear;
    public ModelRenderer legLeftRear;
    public ModelRenderer bodyAbdomen1B;
    public ModelRenderer legRightMiddle;
    public ModelRenderer legLeftMiddle;
    public ModelRenderer bodyThorax;
    public ModelRenderer bodyAbdomen2A;
    public ModelRenderer head;
    public ModelRenderer legRightFront;
    public ModelRenderer legLeftFront;
    public ModelRenderer wingRight;
    public ModelRenderer wingLeft;
    public ModelRenderer mandibleRight;
    public ModelRenderer mandibleLeft;
    public ModelRenderer antennaRight;
    public ModelRenderer antennaLeft;
    public ModelRenderer bodyAbdomen2B;

    public ModelAnt() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.antennaRight = new ModelRenderer(this, 8, -1);
        this.antennaRight.setRotationPoint(-0.5F, -0.5F, -2.0F);
        this.antennaRight.addBox(0.0F, -5.0F, -1.0F, 0, 5, 1, 0.0F);
        this.setRotateAngle(antennaRight, 0.9105382707654417F, 0.31869712141416456F, 0.0F);
        this.bodyAbdomen1B = new ModelRenderer(this, 14, 17);
        this.bodyAbdomen1B.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyAbdomen1B.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 4, 0.0F);
        this.legLeftRear = new ModelRenderer(this, 10, 5);
        this.legLeftRear.setRotationPoint(1.0F, 21.5F, 1.9F);
        this.legLeftRear.addBox(0.0F, -2.5F, 0.0F, 5, 5, 0, 0.0F);
        this.setRotateAngle(legLeftRear, 0.0F, -0.5009094953223726F, 0.0F);
        this.legLeftMiddle = new ModelRenderer(this, 10, 0);
        this.legLeftMiddle.setRotationPoint(1.0F, -0.5F, 0.1F);
        this.legLeftMiddle.addBox(0.0F, -2.0F, 0.0F, 4, 5, 0, 0.0F);
        this.setRotateAngle(legLeftMiddle, 0.0F, 0.27314402793711257F, 0.0F);
        this.bodyAbdomen1A = new ModelRenderer(this, 16, 23);
        this.bodyAbdomen1A.setRotationPoint(0.0F, 21.5F, 1.0F);
        this.bodyAbdomen1A.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F);
        this.bodyThorax = new ModelRenderer(this, 15, 11);
        this.bodyThorax.setRotationPoint(0.0F, 0.0F, -0.3F);
        this.bodyThorax.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(bodyThorax, -0.136659280431156F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, -7, 10);
        this.wingRight.setRotationPoint(-0.4F, -0.8F, -2.0F);
        this.wingRight.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 7, 0.0F);
        this.setRotateAngle(wingRight, 0.5061454830783556F, -0.22549653935766736F, -0.03159045946109736F);
        this.legLeftFront = new ModelRenderer(this, 10, 0);
        this.legLeftFront.setRotationPoint(1.0F, -0.28F, -1.65F);
        this.legLeftFront.addBox(0.0F, -2.0F, 0.0F, 4, 5, 0, 0.0F);
        this.setRotateAngle(legLeftFront, 0.15952309363228173F, 0.5407030022678433F, 0.08272860654453122F);
        this.head = new ModelRenderer(this, 0, 27);
        this.head.setRotationPoint(0.0F, -0.5F, -3.0F);
        this.head.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(head, 0.31869712141416456F, 0.0F, 0.0F);
        this.legRightFront = new ModelRenderer(this, 0, 0);
        this.legRightFront.setRotationPoint(-1.0F, -0.28F, -1.65F);
        this.legRightFront.addBox(-4.0F, -2.0F, 0.0F, 4, 5, 0, 0.0F);
        this.setRotateAngle(legRightFront, 0.15952309363228173F, -0.5407030022678433F, -0.08272860654453122F);
        this.mandibleRight = new ModelRenderer(this, 0, 25);
        this.mandibleRight.setRotationPoint(-1.5F, 0.0F, -3.0F);
        this.mandibleRight.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(mandibleRight, 0.18203784098300857F, -0.136659280431156F, 0.0F);
        this.antennaLeft = new ModelRenderer(this, 8, -1);
        this.antennaLeft.setRotationPoint(0.5F, -0.5F, -2.0F);
        this.antennaLeft.addBox(0.0F, -5.0F, -1.0F, 0, 5, 1, 0.0F);
        this.setRotateAngle(antennaLeft, 0.9105382707654417F, -0.31869712141416456F, 0.0F);
        this.bodyAbdomen2A = new ModelRenderer(this, 14, 27);
        this.bodyAbdomen2A.setRotationPoint(0.0F, -0.2F, 1.3F);
        this.bodyAbdomen2A.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(bodyAbdomen2A, -0.136659280431156F, 0.0F, 0.0F);
        this.bodyAbdomen2B = new ModelRenderer(this, 22, 21);
        this.bodyAbdomen2B.setRotationPoint(0.0F, -1.2F, 0.8F);
        this.bodyAbdomen2B.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.mandibleLeft = new ModelRenderer(this, 6, 25);
        this.mandibleLeft.setRotationPoint(1.5F, 0.0F, -3.0F);
        this.mandibleLeft.addBox(-1.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(mandibleLeft, 0.18203784098300857F, 0.136659280431156F, 0.0F);
        this.legRightRear = new ModelRenderer(this, 0, 5);
        this.legRightRear.setRotationPoint(-1.0F, 21.5F, 1.9F);
        this.legRightRear.addBox(-5.0F, -2.5F, 0.0F, 5, 5, 0, 0.0F);
        this.setRotateAngle(legRightRear, 0.0F, 0.5009094953223726F, 0.0F);
        this.legRightMiddle = new ModelRenderer(this, 0, 0);
        this.legRightMiddle.setRotationPoint(-1.0F, -0.5F, 0.1F);
        this.legRightMiddle.addBox(-4.0F, -2.0F, 0.0F, 4, 5, 0, 0.0F);
        this.setRotateAngle(legRightMiddle, 0.0F, -0.27314402793711257F, 0.0F);
        this.wingLeft = new ModelRenderer(this, -3, 10);
        this.wingLeft.setRotationPoint(0.4F, -0.8F, -2.0F);
        this.wingLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 7, 0.0F);
        this.setRotateAngle(wingLeft, 0.5061454830783556F, 0.22549653935766736F, 0.03159045946109736F);
        this.head.addChild(this.antennaRight);
        this.bodyAbdomen1A.addChild(this.bodyAbdomen1B);
        this.bodyAbdomen1A.addChild(this.legLeftMiddle);
        this.bodyAbdomen1B.addChild(this.bodyThorax);
        this.bodyThorax.addChild(this.wingRight);
        this.bodyThorax.addChild(this.legLeftFront);
        this.bodyThorax.addChild(this.head);
        this.bodyThorax.addChild(this.legRightFront);
        this.head.addChild(this.mandibleRight);
        this.head.addChild(this.antennaLeft);
        this.bodyAbdomen1B.addChild(this.bodyAbdomen2A);
        this.bodyAbdomen2A.addChild(this.bodyAbdomen2B);
        this.head.addChild(this.mandibleLeft);
        this.bodyAbdomen1A.addChild(this.legRightMiddle);
        this.bodyThorax.addChild(this.wingLeft);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.legLeftRear.render(f5);
        this.bodyAbdomen1A.render(f5);
        this.legRightRear.render(f5);
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
