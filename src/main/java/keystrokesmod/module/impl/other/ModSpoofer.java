package keystrokesmod.module.impl.other;

import keystrokesmod.module.Module;
import keystrokesmod.module.setting.impl.ButtonSetting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.HashSet;
import java.util.Set;

public class ModSpoofer extends Module {
    public static final ButtonSetting cancel = new ButtonSetting("Cancel", true);
    private final Set<ButtonSetting> mods = new HashSet<>();
    public static final Set<String> filteredMod = new HashSet<>();

    public ModSpoofer() {
        super("ModSpoofer", category.other);
        this.registerSetting(cancel);
        for (ModContainer modContainer : Loader.instance().getActiveModList()) {
            ButtonSetting setting = new ButtonSetting(modContainer.getModId(), true, () -> !cancel.isToggled());
            this.registerSetting(setting);
            mods.add(setting);
        }
    }

    @Override
    public void guiUpdate() {
        for (ButtonSetting mod : mods) {
            if (!mod.isToggled()) {
                filteredMod.add(mod.getName());
            }
        }
    }

    @Override
    public void onDisable() {
        filteredMod.clear();
    }
}
