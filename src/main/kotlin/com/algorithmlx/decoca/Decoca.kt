package com.algorithmlx.decoca

import com.algorithmlx.decoca.block.DecocaBlocks
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

const val ModId = "decoca"

@Mod(ModId)
object Decoca {
    init {
        DecocaBlocks.blocks.register(MOD_BUS)
        DecocaBlocks.items.register(MOD_BUS)
    }
}