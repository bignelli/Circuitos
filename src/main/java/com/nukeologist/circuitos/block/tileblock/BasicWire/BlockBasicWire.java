package com.nukeologist.circuitos.block.tileblock.BasicWire;

import com.nukeologist.circuitos.block.tileblock.CircuitosBaseTile;
import com.nukeologist.circuitos.init.ModBlocks;
import com.nukeologist.circuitos.reference.Reference;
import com.nukeologist.circuitos.utility.UnlistedPropertyBoolean;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockBasicWire extends CircuitosBaseTile<TileEntityBasicWire> {

    public static final UnlistedPropertyBoolean NORTH = new UnlistedPropertyBoolean("north");
    public static final UnlistedPropertyBoolean SOUTH = new UnlistedPropertyBoolean("south");
    public static final UnlistedPropertyBoolean WEST = new UnlistedPropertyBoolean("west");
    public static final UnlistedPropertyBoolean EAST = new UnlistedPropertyBoolean("east");
    public static final UnlistedPropertyBoolean UP = new UnlistedPropertyBoolean("up");
    public static final UnlistedPropertyBoolean DOWN = new UnlistedPropertyBoolean("down");


    public BlockBasicWire(String name) {
        super(Material.CIRCUITS, name);
    }

    @Nullable
    @Override
    public TileEntityBasicWire createTileEntity(World world, IBlockState state) {
        return new TileEntityBasicWire();
    }

    @Override
    public Class getTileEntityClass() {
        return TileEntityBasicWire.class;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        StateMapperBase ignoreState = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return null;
            }
        };
        ModelLoader.setCustomStateMapper(this, ignoreState);

    }

    @SideOnly(Side.CLIENT)
    public void initItemModel() {
        Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(Reference.MOD_ID, "basicwire"));
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        //return super.createBlockState();
        IProperty[] listedProperties = new IProperty[0]; // no listed properties
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] { NORTH, SOUTH, WEST, EAST, UP, DOWN };
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        //return super.getExtendedState(state, world, pos);
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;

        boolean north = isSameBlock(world, pos.north());
        boolean south = isSameBlock(world, pos.south());
        boolean west = isSameBlock(world, pos.west());
        boolean east = isSameBlock(world, pos.east());
        boolean up = isSameBlock(world, pos.up());
        boolean down = isSameBlock(world, pos.down());

        return extendedBlockState
                .withProperty(NORTH, north)
                .withProperty(SOUTH, south)
                .withProperty(WEST, west)
                .withProperty(EAST, east)
                .withProperty(UP, up)
                .withProperty(DOWN, down);
    }

    //TODO: Check if its a "side" part of a generator and connect
    private boolean isSameBlock(IBlockAccess world, BlockPos pos) {
        if(world.getBlockState(pos).getBlock() == ModBlocks.basicGenerator) {
            //check if the side is "on/off"
            return true;
        }
        return world.getBlockState(pos).getBlock() == ModBlocks.basicWire;
    }


}
