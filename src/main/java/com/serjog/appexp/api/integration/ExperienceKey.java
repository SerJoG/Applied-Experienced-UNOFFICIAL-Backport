package com.serjog.appexp.api.integration;

import com.serjog.appexp.AppliedExperienced;
import net.minecraft.resources.ResourceLocation;

public class ExperienceKey {
    public final ResourceLocation getId() {
        return AppliedExperienced.id("experience");
    }
}
