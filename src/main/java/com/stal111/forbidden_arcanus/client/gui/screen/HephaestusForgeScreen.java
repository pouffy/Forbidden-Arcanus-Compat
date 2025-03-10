package com.stal111.forbidden_arcanus.client.gui.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.forge.HephaestusForgeLevel;
import com.stal111.forbidden_arcanus.common.block.entity.forge.essence.EssenceType;
import com.stal111.forbidden_arcanus.common.inventory.EnhancerSlot;
import com.stal111.forbidden_arcanus.common.inventory.HephaestusForgeMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Hephaestus Forge Screen
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.gui.screen.HephaestusForgeScreen
 *
 * @author stal111
 * @since 2021-06-28
 */
public class HephaestusForgeScreen extends AbstractContainerScreen<HephaestusForgeMenu> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(ForbiddenArcanus.MOD_ID, "textures/gui/container/hephaestus_forge.png");

    public static final List<EssenceBarDefinition> ESSENCE_BAR_DEFINITIONS = ImmutableList.of(
            new EssenceBarDefinition(EssenceType.AUREAL, 0, 12, 177),
            new EssenceBarDefinition(EssenceType.SOULS, 1, 24, 183),
            new EssenceBarDefinition(EssenceType.BLOOD, 2, 148, 189),
            new EssenceBarDefinition(EssenceType.EXPERIENCE, 3, 160, 195)
    );

    public HephaestusForgeScreen(HephaestusForgeMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.titleLabelY -= 2;
        this.inventoryLabelY += 2;
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        for (int i = 0; i < this.menu.slots.size(); i++) {
            Slot slot = this.menu.slots.get(i);

            if (slot instanceof EnhancerSlot enhancerSlot) {
                enhancerSlot.updateLocked(this.menu.getLevel());
            }
        }
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        super.render(poseStack, mouseX, mouseY, partialTicks);

        for (int i = 0; i < this.menu.slots.size(); i++) {
            Slot slot = this.menu.slots.get(i);

            if (slot instanceof EnhancerSlot) {
                int posX = mouseX - this.leftPos;
                int posY = mouseY - this.topPos;

                if (posX >= (slot.x - 1) && posX < (slot.x + 16 + 1) && posY >= (slot.y - 1) && posY < (slot.y + 16 + 1)) {
                    this.hoveredSlot = slot;
                }
            }
        }

        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTicks, int x, int y) {
        this.renderBackground(poseStack);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURES);

        this.blit(poseStack, this.getGuiLeft(), this.getGuiTop(), 0, 0, this.getXSize(), this.getYSize());

        this.blit(poseStack, this.getGuiLeft() - 26, this.getGuiTop() + 16, 176, 61, 29, 51);
        this.blit(poseStack, this.getGuiLeft() + 172, this.getGuiTop() + 16, 206, 61, 29, 51);

        for (Slot slotItemHandler : this.menu.slots) {
            if (slotItemHandler instanceof EnhancerSlot enhancerSlot) {
                this.renderEnhancerSlot(enhancerSlot, poseStack, this.getGuiLeft(), this.getGuiTop());
            }
        }

        HephaestusForgeLevel level = this.menu.getLevel();

        for (EssenceBarDefinition definition : ESSENCE_BAR_DEFINITIONS) {
            this.renderBar(poseStack, definition, definition.getMaxAmount(level));
        }
    }

    @Override
    protected void renderTooltip(@Nonnull PoseStack poseStack, int x, int y) {
        super.renderTooltip(poseStack, x, y);
        int posX = x - this.getGuiLeft();
        int posY = y - this.getGuiTop();

        this.renderBarsTooltip(poseStack, posX, posY, x, y);

        Slot slot = this.getSlotUnderMouse();

        if (slot instanceof EnhancerSlot enhancerSlot && this.menu.isSlotLocked(enhancerSlot)) {
            this.renderTooltip(poseStack, Component.translatable("gui.forbidden_arcanus.hephaestus_forge.unlocked_at").append(": " + enhancerSlot.getAdditionalData()), x, y);
        }
    }

    private void renderBarsTooltip(PoseStack poseStack, int x, int y, int screenX, int screenY) {
        if (!(y >= 19 && y <= 68)) {
            return;
        }

        ContainerData data = this.menu.getHephaestusForgeData();
        HephaestusForgeLevel level = this.menu.getLevel();

        for (EssenceBarDefinition definition : ESSENCE_BAR_DEFINITIONS) {
            if (x >= definition.x() - 2 && x <= definition.x() + 5) {
                this.renderTooltip(poseStack, definition.buildComponent(data, level), screenX, screenY);
                break;
            }
        }
    }

    private void renderBar(PoseStack matrixStack, EssenceBarDefinition definition, int max) {
        int ySize = Math.toIntExact(Math.round(32.0F * this.menu.getHephaestusForgeData().get(definition.dataKey()) / max));
        this.blit(matrixStack, this.getGuiLeft() + definition.x(), this.getGuiTop() + 22 + 32 - ySize, definition.textureX(), 3 + 32 - ySize, 4, ySize);
    }

    public void renderEnhancerSlot(EnhancerSlot slot, PoseStack poseStack, int guiLeft, int guiTop) {
        if (this.menu.isSlotLocked(slot)) {
            this.blit(poseStack, guiLeft + slot.x - 2, guiTop + slot.y - 2, 176, 40, 20, 20);
        }
    }

    private record EssenceBarDefinition(EssenceType type, int dataKey, int x, int textureX) {

        public int getMaxAmount(HephaestusForgeLevel level) {
            return level.getMaxAmount(this.type);
        }

        public MutableComponent buildComponent(ContainerData data, HephaestusForgeLevel level) {
            return this.type.getComponent().copy().append(": " + data.get(this.dataKey) + "/" + this.getMaxAmount(level));
        }
    }
}
