package com.algorithmlx.decoca.block

import com.algorithmlx.decoca.ModId
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.ObjectHolderDelegate
import thedarkcolour.kotlinforforge.forge.registerObject

object DecocaBlocks {
    val blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, ModId)
    val items = DeferredRegister.create(ForgeRegistries.ITEMS, ModId)

    val carfstone by block("carfstone") { Block(BlockBehaviour.Properties.of(Material.STONE)) }
    val carfstoneWall by block("carfstone_wall") { WallBlock(BlockBehaviour.Properties.copy(carfstone)) }
    val carfstoneStairs by block("carfstone_stairs") { StairBlock(carfstone::defaultBlockState, BlockBehaviour.Properties.copy(carfstone)) }
    val carfstoneSlab by block("carfstone_slab") { SlabBlock(BlockBehaviour.Properties.copy(carfstone)) }

    val carfstoneBrick by block("carfstone_bricks") { Block(BlockBehaviour.Properties.of(Material.STONE)) }
    val carfstoneBrickWall by block("carfstone_brick_wall") { WallBlock(BlockBehaviour.Properties.copy(carfstoneBrick)) }
    val carfstoneBrickStairs by block("carfstone_brick_stairs") { StairBlock(carfstoneBrick::defaultBlockState, BlockBehaviour.Properties.copy(carfstoneBrick)) }
    val carfstoneBrickSlab by block("carfstone_brick_slab") { SlabBlock(BlockBehaviour.Properties.copy(carfstoneBrick)) }

    fun <T: Block> block(id: String, block: ()-> T): ObjectHolderDelegate<T> =
        block(id, block, Item.Properties().tab(CreativeModeTab.TAB_MATERIALS))

    fun <T: Block> block(id: String, block: () -> T, props: Item.Properties): ObjectHolderDelegate<T> {
        val regBlock =  blocks.registerObject(id, block)
        items.registerObject(id) { BlockItem(regBlock.invoke(), props) }
        return regBlock
    }
}
