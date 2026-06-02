package com.serjog.appexp;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod()
public class AppliedExperienced
{
    public static final String MODID = "appexp";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AppliedExperienced(FMLJavaModLoadingContext context)
    {
        IEventBus bus = context.getModEventBus();

        AExpItems.initialize(bus);
        AExpBlocks.DR.register(bus);
        AExpBlockEntities.DR.register(bus);
        AExpComponents.initialize(bus);
        AExpMenus.initialize(bus);

        bus.addListener(AppliedExperiencedDataGenerators::onGatherData);

        bus.addListener(ExperienceKeyType::register);

        StorageCells.addCellHandler(ExperienceCellHandler.INSTANCE);
        bus.addListener(AExpItems::initCellUpgrades);

        StackWorldBehaviors.registerImportStrategy(ExperienceKeyType.TYPE, ExperienceStackImportStrategy::new);
        StackWorldBehaviors.registerExportStrategy(ExperienceKeyType.TYPE, ExperienceStackExportStrategy::new);
        StackWorldBehaviors.registerExternalStorageStrategy(ExperienceKeyType.TYPE, ExperienceExternalStorageStrategy::new);

        ContainerItemStrategy.register(ExperienceKeyType.TYPE, AEExperienceKey.class, new ExperienceContainerItemStrategy());
        GenericSlotCapacities.register(ExperienceKeyType.TYPE, AEExperienceKey.MAX_EXPERIENCE);

        bus.addListener(GenericStackExperienceStorage::registerCapability);
        bus.addListener(ExperienceConverterEntity::registerCapability);
        bus.addListener(ExperienceAcceptorEntity::registerCapability);
        bus.addListener(ExperienceAcceptorPart::registerCapability);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
