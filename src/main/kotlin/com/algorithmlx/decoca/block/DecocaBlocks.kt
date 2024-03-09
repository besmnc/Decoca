package com.algorithmlx.decoca.block

import com.algorithmlx.decoca.ModId
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate
import thedarkcolour.kotlinforforge.forge.registerObject

object DecocaBlocks {
    private val blockTab = object : CreativeModeTab("$ModId.blocks") { override fun makeIcon(): ItemStack = ItemStack(carfstone) }
    private val wallTab = object : CreativeModeTab("$ModId.walls") { override fun makeIcon(): ItemStack = ItemStack(carfstoneWall) }
    private val stairsTab = object : CreativeModeTab("$ModId.stairs") { override fun makeIcon(): ItemStack = ItemStack(carfstoneStairs) }
    private val slabTab = object : CreativeModeTab("$ModId.slabs") { override fun makeIcon(): ItemStack = ItemStack(carfstoneSlab) }

    val blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, ModId)
    val items = DeferredRegister.create(ForgeRegistries.ITEMS, ModId)

    val carfstone by block("carfstone") { Block(Properties.of(Material.STONE)) }
    val carfstoneWall by wall("carfstone_wall") { WallBlock(Properties.copy(carfstone)) }
    val carfstoneStairs by stair("carfstone_stairs") { StairBlock(carfstone::defaultBlockState, Properties.copy(carfstone)) }
    val carfstoneSlab by slab("carfstone_slab") { SlabBlock(Properties.copy(carfstone)) }

    val smoothCarfstone by block("smooth_carfstone") { Block(Properties.of(Material.STONE)) }
    val smoothCarfstoneWall by wall("smooth_carfstone_wall") { WallBlock(Properties.copy(smoothCarfstone)) }
    val smoothCarfstoneStairs by stair("smooth_carfstone_stairs") { StairBlock(smoothCarfstone::defaultBlockState, Properties.copy(smoothCarfstone)) }
    val smoothCarfstoneSlab by slab("smooth_carfstone_slab") { SlabBlock(Properties.copy(smoothCarfstone)) }

    val carfstoneBrick by block("carfstone_bricks") { Block(Properties.of(Material.STONE)) }
    val carfstoneBrickWall by wall("carfstone_brick_wall") { WallBlock(Properties.copy(carfstoneBrick)) }
    val carfstoneBrickStairs by stair("carfstone_brick_stairs") { StairBlock(carfstoneBrick::defaultBlockState, Properties.copy(carfstoneBrick)) }
    val carfstoneBrickSlab by slab("carfstone_brick_slab") { SlabBlock(Properties.copy(carfstoneBrick)) }

    private fun <T: Block> block(id: String, block: ()-> T): ObjectHolderDelegate<T> =
        block(id, block, Item.Properties().tab(blockTab))

    private fun <T: WallBlock> wall(id: String, block: ()-> T): ObjectHolderDelegate<T> =
        block(id, block, Item.Properties().tab(wallTab))

    private fun <T: StairBlock> stair(id: String, block: ()-> T): ObjectHolderDelegate<T> =
        block(id, block, Item.Properties().tab(stairsTab))

    private fun <T: SlabBlock> slab(id: String, block: ()-> T): ObjectHolderDelegate<T> =
        block(id, block, Item.Properties().tab(slabTab))

    private fun wall(id: String, copy: Block): ObjectHolderDelegate<WallBlock> =
        wall(id) { WallBlock(Properties.copy(copy)) }

    private fun stair(id: String, copy: Block): ObjectHolderDelegate<StairBlock> =
        stair(id) { StairBlock(copy::defaultBlockState, Properties.copy(copy)) }

    private fun slab(id: String, copy: Block): ObjectHolderDelegate<SlabBlock> =
        slab(id) { SlabBlock(Properties.copy(copy)) }

    private fun <T: Block> block(id: String, block: () -> T, props: Item.Properties): ObjectHolderDelegate<T> {
        val regBlock = blocks.registerObject(id, block)
        items.registerObject(id) { BlockItem(regBlock.invoke(), props) }
        return regBlock
    }
}
