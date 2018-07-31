package com.nukeologist.circuitos.client.gui;

import com.nukeologist.circuitos.block.tileblock.BasicGenerator.TileEntityBasicGenerator;
import com.nukeologist.circuitos.reference.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;


public class GUIBasicGenerator extends GuiContainer {

    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/basicgenerator.png");
    private final TileEntityBasicGenerator tileentity;
    private final InventoryPlayer player;

    private int id = 0;

    GuiButton button1;

    public GUIBasicGenerator(InventoryPlayer player, TileEntityBasicGenerator tileEntity) {
        super(new ContainerBasicGenerator(player, tileEntity));
        this.player = player;
        this.tileentity = tileEntity;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        //if(this.tileentity.getDisplayName().getUnformattedText() != null) {
            String tileName = this.tileentity.getDisplayName().getUnformattedText();
       //}
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(button1 = new GuiButton(newId(), 75, this.ySize - 80, 90, 20, "Analyze Circuitry"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        button1.x = this.guiLeft + 30;
        button1.y = this.guiTop +60;

        //updateButtons();
    }

    public void updateButtons() {
        if(button1.displayString == "Analyzing...") {
            button1.enabled = false;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                button1.displayString = "Analyzing..."; //start the ANALYSIS!
                break;
            case 1:
                break;
        }
        updateButtons();
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private int newId() {
        return this.id ++;
    }
}
