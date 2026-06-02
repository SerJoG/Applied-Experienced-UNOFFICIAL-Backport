package com.serjog.appexp.api.integration.emi;

import appeng.api.integrations.emi.EmiStackConverter;
import appeng.api.stacks.GenericStack;
import dev.emi.emi.api.stack.EmiStack;

public class EmiExperienceStackConverter implements EmiStackConverter {
    @Override
    public Class<?> getKeyType() {
        return ExperienceKey.class;
    }

    @Override
    public @Nullable EmiStack toEmiStack(GenericStack stack) {
        if (stack.what() instanceof AEExperienceKey key) {
            return IExperienceLibAccess.INSTANCE.emiHelper().createEmiStack(key.getStack());
        }
        return null;
    }

    @Override
    public @Nullable GenericStack toGenericStack(EmiStack stack) {
        var key = stack.getKeyOfType(ExperienceKey.class);
        if (key != null) {
            return new GenericStack(KEY, stack.getAmount());
        }
        return null;
    }
}
