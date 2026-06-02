package com.serjog.appexp.block;

import appeng.block.AEBaseBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class ExperienceAcceptorBlock extends AEBaseEntityBlock<ExperienceAcceptorEntity> {
    public ExperienceAcceptorBlock() {
        super(AEBaseBlock.defaultProps(MapColor.METAL, SoundType.METAL));
    }
}
