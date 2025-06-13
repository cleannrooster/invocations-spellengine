package com.invoke.mixin;

import com.invoke.InvokeClient;
import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import net.fabricmc.fabric.mixin.networking.client.accessor.MinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.client.util.SpellRender;
import net.spell_engine.internals.container.SpellContainerSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static com.invoke.InvokeClient.TOTAL_LIST;
import static com.invoke.InvokeMod.MODID;

@Mixin(SpellRender.class)
public class SpellRenderMixin {
    private static final String[] FIRE_INVOKER_LIST = {
            "risingflame",
            "flameray",
            "scorchingwind",
            "meteorrush",
            "greaterfireball",
            "supernova",
            "buckshot",
            "combustion",
            "armageddon"
    };

    private static final String[] ARCANE_INVOKER_LIST = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    private static final String[] FROST_INVOKER_LIST = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    private static final String[] PUREFROST = {
            "",
            "",
            "",
            "",
    };
    private static final String[] FROST3ARCANE1 = {

            "",
            "",
            "",
            "",
    };
    private static final String[] FROST3ARCANE2 = {
            "",
            "",
            "",
            ""

    };
    private static final String[] FROST3ARCANE3 = {
            "",
            "",
            "",
            ""
    };
    private static final String[] PUREFIRE = {

            "",
            "",
            "",
            "",
    };
    private static final String[] FIRE3ARCANE1 = {

            "",
            "",
            "",
            "",

    };
    private static final String[] FIRE3ARCANE2 = {

            "",
            "",
            "",
            ""
    };
    private static final String[] FIRE3ARCANE3 = {
            "",
            "",
            "",
            ""
    };
  /*  private  static  String[][][] TOTAL_LIST = {{PUREFROST,FROST3ARCANE2,FROST3ARCANE1 , PUREFROST},
            {PUREFROST,FROST3ARCANE1,FROST3ARCANE3,PUREFROST},
            {PUREFROST,FIRE3ARCANE3, FROST3ARCANE2, PUREFROST},
            {PUREFIRE, FIRE3ARCANE2, FIRE3ARCANE2, FIRE3ARCANE1}};
    static {
        TOTAL_LIST[0][0][0] = "runic_invocation";
        TOTAL_LIST[0][1][0] = "magic_missile";
        TOTAL_LIST[0][2][0] = "enders_gaze";
        TOTAL_LIST[0][3][0] = "agonizing_blast";
        TOTAL_LIST[0][1][1] = "sonicboom";
        TOTAL_LIST[0][1][2] = "amethystburst";
        TOTAL_LIST[1][1][1] = "bouncing";
        TOTAL_LIST[1][1][0] = "blink";
        TOTAL_LIST[2][1][0] = "supernova";
        TOTAL_LIST[0][2][1] = "buckshot";
        TOTAL_LIST[1][2][0] = "combustion";
        TOTAL_LIST[0][0][1] = "icebarrage";
        TOTAL_LIST[0][0][2] = "glacier";
        TOTAL_LIST[0][0][3] = "resonance";
        TOTAL_LIST[1][0][1] = "sharedsuffering";
        TOTAL_LIST[2][0][1] = "combustion";
        TOTAL_LIST[1][0][2] = "upheaval";
        TOTAL_LIST[3][0][0] = "armageddon";
        TOTAL_LIST[1][0][0] = "scorchingwind";
        TOTAL_LIST[2][0][0] = "greaterfireball";


    }*/

      @Inject(at = @At("HEAD"), method = "iconTexture", cancellable = true)
    private static void iconTextureReplaceInvoke(Identifier spellId, CallbackInfoReturnable<Identifier> identifier) {
        if(MinecraftClient.getInstance() != null) {


            if (spellId.getNamespace().equals("invoke") && !(spellId.getPath().equals("rah") || spellId.getPath().equals("gon") || spellId.getPath().equals("heo"))) {
                identifier.setReturnValue(Identifier.of(InvokeMod.MODID, "textures/spell/" + "runic_invocation" + ".png"));
            }
        }
    }
}
