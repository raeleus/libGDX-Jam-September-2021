package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static TextureAtlas textures_textures;

    public static Skin skin_skin;

    public static Sound sfx_assaultDie;

    public static Sound sfx_assaultEasterEgg;

    public static Sound sfx_assaultSelected1;

    public static Sound sfx_assaultSelected2;

    public static Sound sfx_assaultSelected3;

    public static Sound sfx_assault;

    public static Sound sfx_bing;

    public static Sound sfx_buildingDestroyed;

    public static Sound sfx_click;

    public static Sound sfx_coin;

    public static Sound sfx_commanderYourBaseIsUnderAttack;

    public static Sound sfx_explosion2;

    public static Sound sfx_explosion3;

    public static Sound sfx_explosion;

    public static Sound sfx_failure;

    public static Sound sfx_femaleDeath;

    public static Sound sfx_heavyDie;

    public static Sound sfx_heavyEasterEgg;

    public static Sound sfx_heavySelected1;

    public static Sound sfx_heavySelected2;

    public static Sound sfx_heavySelected3;

    public static Sound sfx_libgdxBeep;

    public static Sound sfx_libgdxBurstFire;

    public static Sound sfx_libgdxExplosion;

    public static Sound sfx_libgdxMadeWithUnity;

    public static Sound sfx_libgdxScream;

    public static Sound sfx_militiaDie;

    public static Sound sfx_militiaEasterEgg;

    public static Sound sfx_militiaSelected1;

    public static Sound sfx_militiaSelected2;

    public static Sound sfx_militiaSelected3;

    public static Sound sfx_militia;

    public static Sound sfx_missile;

    public static Sound sfx_missionFailure;

    public static Sound sfx_mortar;

    public static Sound sfx_pouncer;

    public static Sound sfx_ray3kItsInTheGame;

    public static Sound sfx_ray3kRay3k;

    public static Sound sfx_reinforcementsHaveArrived;

    public static Sound sfx_slash;

    public static Sound sfx_sniperDie;

    public static Sound sfx_sniperEasterEgg;

    public static Sound sfx_sniperSelected1;

    public static Sound sfx_sniperSelected2;

    public static Sound sfx_sniperSelected3;

    public static Sound sfx_sniper;

    public static Sound sfx_spit;

    public static Sound sfx_tank;

    public static Sound sfx_troopDeploy;

    public static Sound sfx_victory;

    public static Sound sfx_witch;

    public static Sound sfx_zombieDeath;

    public static Sound sfx_zombieGargle;

    public static Sound sfx_zombieGrowl;

    public static Sound sfx_zombieRally;

    public static Music bgm_audioSample;

    public static Music bgm_game;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        textures_textures = assetManager.get("textures/textures.atlas");
        SpineActor.skeletonData = assetManager.get("spine/actor.json");
        SpineActor.animationData = assetManager.get("spine/actor.json-animation");
        SpineActor.animationFlagBlue = SpineActor.skeletonData.findAnimation("flag-blue");
        SpineActor.animationFlagGreen = SpineActor.skeletonData.findAnimation("flag-green");
        SpineActor.animationFlagNone = SpineActor.skeletonData.findAnimation("flag-none");
        SpineActor.animationFlagOrange = SpineActor.skeletonData.findAnimation("flag-orange");
        SpineActor.animationFlagPurple = SpineActor.skeletonData.findAnimation("flag-purple");
        SpineActor.animationHurtLeft = SpineActor.skeletonData.findAnimation("hurt-left");
        SpineActor.animationHurtRight = SpineActor.skeletonData.findAnimation("hurt-right");
        SpineActor.animationSelectedBlue = SpineActor.skeletonData.findAnimation("selected-blue");
        SpineActor.animationSelectedGreen = SpineActor.skeletonData.findAnimation("selected-green");
        SpineActor.animationSelectedOrange = SpineActor.skeletonData.findAnimation("selected-orange");
        SpineActor.animationSelectedPurple = SpineActor.skeletonData.findAnimation("selected-purple");
        SpineActor.animationShoot = SpineActor.skeletonData.findAnimation("shoot");
        SpineActor.animationSpit = SpineActor.skeletonData.findAnimation("spit");
        SpineActor.animationStand = SpineActor.skeletonData.findAnimation("stand");
        SpineActor.animationWalk = SpineActor.skeletonData.findAnimation("walk");
        SpineActor.skinDefault = SpineActor.skeletonData.findSkin("default");
        SpineActor.skinAssault = SpineActor.skeletonData.findSkin("assault");
        SpineActor.skinExploder = SpineActor.skeletonData.findSkin("exploder");
        SpineActor.skinHeavy = SpineActor.skeletonData.findSkin("heavy");
        SpineActor.skinMilitia = SpineActor.skeletonData.findSkin("militia");
        SpineActor.skinPouncer = SpineActor.skeletonData.findSkin("pouncer");
        SpineActor.skinSniper = SpineActor.skeletonData.findSkin("sniper");
        SpineActor.skinSpitter = SpineActor.skeletonData.findSkin("spitter");
        SpineActor.skinWitch = SpineActor.skeletonData.findSkin("witch");
        SpineActor.skinZombie = SpineActor.skeletonData.findSkin("zombie");
        SpineBloodCloud.skeletonData = assetManager.get("spine/blood-cloud.json");
        SpineBloodCloud.animationData = assetManager.get("spine/blood-cloud.json-animation");
        SpineBloodCloud.animationAnimation = SpineBloodCloud.skeletonData.findAnimation("animation");
        SpineBloodCloud.skinDefault = SpineBloodCloud.skeletonData.findSkin("default");
        SpineBloodSplatter.skeletonData = assetManager.get("spine/blood-splatter.json");
        SpineBloodSplatter.animationData = assetManager.get("spine/blood-splatter.json-animation");
        SpineBloodSplatter.animationAnimation = SpineBloodSplatter.skeletonData.findAnimation("animation");
        SpineBloodSplatter.skinDefault = SpineBloodSplatter.skeletonData.findSkin("default");
        SpineBomb.skeletonData = assetManager.get("spine/bomb.json");
        SpineBomb.animationData = assetManager.get("spine/bomb.json-animation");
        SpineBomb.animationAnimation = SpineBomb.skeletonData.findAnimation("animation");
        SpineBomb.skinDefault = SpineBomb.skeletonData.findSkin("default");
        SpineCoin.skeletonData = assetManager.get("spine/coin.json");
        SpineCoin.animationData = assetManager.get("spine/coin.json-animation");
        SpineCoin.animationAnimation = SpineCoin.skeletonData.findAnimation("animation");
        SpineCoin.skinDefault = SpineCoin.skeletonData.findSkin("default");
        SpineExplosion.skeletonData = assetManager.get("spine/explosion.json");
        SpineExplosion.animationData = assetManager.get("spine/explosion.json-animation");
        SpineExplosion.animationAnimation = SpineExplosion.skeletonData.findAnimation("animation");
        SpineExplosion.skinDefault = SpineExplosion.skeletonData.findSkin("default");
        SpineHouse.skeletonData = assetManager.get("spine/house.json");
        SpineHouse.animationData = assetManager.get("spine/house.json-animation");
        SpineHouse.animationAlive = SpineHouse.skeletonData.findAnimation("alive");
        SpineHouse.animationDestroyed = SpineHouse.skeletonData.findAnimation("destroyed");
        SpineHouse.skinDefault = SpineHouse.skeletonData.findSkin("default");
        SpineHouse.skinHouse1 = SpineHouse.skeletonData.findSkin("house-1");
        SpineHouse.skinHouse2 = SpineHouse.skeletonData.findSkin("house-2");
        SpineLibgdx.skeletonData = assetManager.get("spine/libgdx.json");
        SpineLibgdx.animationData = assetManager.get("spine/libgdx.json-animation");
        SpineLibgdx.animationAnimation = SpineLibgdx.skeletonData.findAnimation("animation");
        SpineLibgdx.animationStand = SpineLibgdx.skeletonData.findAnimation("stand");
        SpineLibgdx.skinDefault = SpineLibgdx.skeletonData.findSkin("default");
        SpineMissile.skeletonData = assetManager.get("spine/missile.json");
        SpineMissile.animationData = assetManager.get("spine/missile.json-animation");
        SpineMissile.animationAnimation = SpineMissile.skeletonData.findAnimation("animation");
        SpineMissile.skinDefault = SpineMissile.skeletonData.findSkin("default");
        SpineMove.skeletonData = assetManager.get("spine/move.json");
        SpineMove.animationData = assetManager.get("spine/move.json-animation");
        SpineMove.animationError = SpineMove.skeletonData.findAnimation("error");
        SpineMove.animationMove = SpineMove.skeletonData.findAnimation("move");
        SpineMove.skinDefault = SpineMove.skeletonData.findSkin("default");
        SpinePoisonCloud.skeletonData = assetManager.get("spine/poison-cloud.json");
        SpinePoisonCloud.animationData = assetManager.get("spine/poison-cloud.json-animation");
        SpinePoisonCloud.animationAnimation = SpinePoisonCloud.skeletonData.findAnimation("animation");
        SpinePoisonCloud.skinDefault = SpinePoisonCloud.skeletonData.findSkin("default");
        SpineRay3k.skeletonData = assetManager.get("spine/ray3k.json");
        SpineRay3k.animationData = assetManager.get("spine/ray3k.json-animation");
        SpineRay3k.animationAnimation = SpineRay3k.skeletonData.findAnimation("animation");
        SpineRay3k.animationStand = SpineRay3k.skeletonData.findAnimation("stand");
        SpineRay3k.skinDefault = SpineRay3k.skeletonData.findSkin("default");
        SpineShip.skeletonData = assetManager.get("spine/ship.json");
        SpineShip.animationData = assetManager.get("spine/ship.json-animation");
        SpineShip.animationAnimation = SpineShip.skeletonData.findAnimation("animation");
        SpineShip.animationNoThruster = SpineShip.skeletonData.findAnimation("no-thruster");
        SpineShip.animationThruster = SpineShip.skeletonData.findAnimation("thruster");
        SpineShip.skinDefault = SpineShip.skeletonData.findSkin("default");
        SpineSmoke.skeletonData = assetManager.get("spine/smoke.json");
        SpineSmoke.animationData = assetManager.get("spine/smoke.json-animation");
        SpineSmoke.animationAnimation = SpineSmoke.skeletonData.findAnimation("animation");
        SpineSmoke.skinDefault = SpineSmoke.skeletonData.findSkin("default");
        SpineSpit.skeletonData = assetManager.get("spine/spit.json");
        SpineSpit.animationData = assetManager.get("spine/spit.json-animation");
        SpineSpit.animationAnimation = SpineSpit.skeletonData.findAnimation("animation");
        SpineSpit.skinDefault = SpineSpit.skeletonData.findSkin("default");
        SpineThrusterSmall.skeletonData = assetManager.get("spine/thruster-small.json");
        SpineThrusterSmall.animationData = assetManager.get("spine/thruster-small.json-animation");
        SpineThrusterSmall.animationAnimation = SpineThrusterSmall.skeletonData.findAnimation("animation");
        SpineThrusterSmall.skinDefault = SpineThrusterSmall.skeletonData.findSkin("default");
        SpineThruster.skeletonData = assetManager.get("spine/thruster.json");
        SpineThruster.animationData = assetManager.get("spine/thruster.json-animation");
        SpineThruster.animationAnimation = SpineThruster.skeletonData.findAnimation("animation");
        SpineThruster.skinDefault = SpineThruster.skeletonData.findSkin("default");
        skin_skin = assetManager.get("skin/skin.json");
        SkinSkinStyles.ibLevel8 = skin_skin.get("level-8", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibOptions = skin_skin.get("options", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel13 = skin_skin.get("level-13", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibDefaults = skin_skin.get("defaults", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel5 = skin_skin.get("level-5", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibDefault = skin_skin.get("default", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibMortar = skin_skin.get("mortar", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel10 = skin_skin.get("level-10", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel2 = skin_skin.get("level-2", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibMine = skin_skin.get("mine", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel15 = skin_skin.get("level-15", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel7 = skin_skin.get("level-7", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibAssault = skin_skin.get("assault", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibSniper = skin_skin.get("sniper", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel12 = skin_skin.get("level-12", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibBomb = skin_skin.get("bomb", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel4 = skin_skin.get("level-4", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibMilitia = skin_skin.get("militia", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel9 = skin_skin.get("level-9", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel1 = skin_skin.get("level-1", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel14 = skin_skin.get("level-14", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibOk = skin_skin.get("ok", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel6 = skin_skin.get("level-6", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel11 = skin_skin.get("level-11", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibHeavy = skin_skin.get("heavy", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibCredits = skin_skin.get("credits", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibLevel3 = skin_skin.get("level-3", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibPlay = skin_skin.get("play", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.ibBindings = skin_skin.get("bindings", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.lDefault = skin_skin.get("default", Label.LabelStyle.class);
        SkinSkinStyles.spDefault = skin_skin.get("default", ScrollPane.ScrollPaneStyle.class);
        SkinSkinStyles.sDefaultHorizontal = skin_skin.get("default-horizontal", Slider.SliderStyle.class);
        SkinSkinStyles.tbToggle = skin_skin.get("toggle", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tbDefault = skin_skin.get("default", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tfDefault = skin_skin.get("default", TextField.TextFieldStyle.class);
        SkinSkinStyles.ttDefault = skin_skin.get("default", TextTooltip.TextTooltipStyle.class);
        SkinSkinStyles.wDefault = skin_skin.get("default", Window.WindowStyle.class);
        sfx_assaultDie = assetManager.get("sfx/assault-die.mp3");
        sfx_assaultEasterEgg = assetManager.get("sfx/assault-easter-egg.mp3");
        sfx_assaultSelected1 = assetManager.get("sfx/assault-selected-1.mp3");
        sfx_assaultSelected2 = assetManager.get("sfx/assault-selected-2.mp3");
        sfx_assaultSelected3 = assetManager.get("sfx/assault-selected-3.mp3");
        sfx_assault = assetManager.get("sfx/assault.mp3");
        sfx_bing = assetManager.get("sfx/bing.mp3");
        sfx_buildingDestroyed = assetManager.get("sfx/building-destroyed.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_coin = assetManager.get("sfx/coin.mp3");
        sfx_commanderYourBaseIsUnderAttack = assetManager.get("sfx/commander-your-base-is-under-attack.mp3");
        sfx_explosion2 = assetManager.get("sfx/explosion-2.mp3");
        sfx_explosion3 = assetManager.get("sfx/explosion-3.mp3");
        sfx_explosion = assetManager.get("sfx/explosion.mp3");
        sfx_failure = assetManager.get("sfx/failure.mp3");
        sfx_femaleDeath = assetManager.get("sfx/female-death.mp3");
        sfx_heavyDie = assetManager.get("sfx/heavy-die.mp3");
        sfx_heavyEasterEgg = assetManager.get("sfx/heavy-easter-egg.mp3");
        sfx_heavySelected1 = assetManager.get("sfx/heavy-selected-1.mp3");
        sfx_heavySelected2 = assetManager.get("sfx/heavy-selected-2.mp3");
        sfx_heavySelected3 = assetManager.get("sfx/heavy-selected-3.mp3");
        sfx_libgdxBeep = assetManager.get("sfx/libgdx-beep.mp3");
        sfx_libgdxBurstFire = assetManager.get("sfx/libgdx-burst-fire.mp3");
        sfx_libgdxExplosion = assetManager.get("sfx/libgdx-explosion.mp3");
        sfx_libgdxMadeWithUnity = assetManager.get("sfx/libgdx-made-with-unity.mp3");
        sfx_libgdxScream = assetManager.get("sfx/libgdx-scream.mp3");
        sfx_militiaDie = assetManager.get("sfx/militia-die.mp3");
        sfx_militiaEasterEgg = assetManager.get("sfx/militia-easter-egg.mp3");
        sfx_militiaSelected1 = assetManager.get("sfx/militia-selected-1.mp3");
        sfx_militiaSelected2 = assetManager.get("sfx/militia-selected-2.mp3");
        sfx_militiaSelected3 = assetManager.get("sfx/militia-selected-3.mp3");
        sfx_militia = assetManager.get("sfx/militia.mp3");
        sfx_missile = assetManager.get("sfx/missile.mp3");
        sfx_missionFailure = assetManager.get("sfx/mission-failure.mp3");
        sfx_mortar = assetManager.get("sfx/mortar.mp3");
        sfx_pouncer = assetManager.get("sfx/pouncer.mp3");
        sfx_ray3kItsInTheGame = assetManager.get("sfx/ray3k-it's-in-the-game.mp3");
        sfx_ray3kRay3k = assetManager.get("sfx/ray3k-ray3k.mp3");
        sfx_reinforcementsHaveArrived = assetManager.get("sfx/reinforcements-have-arrived.mp3");
        sfx_slash = assetManager.get("sfx/slash.mp3");
        sfx_sniperDie = assetManager.get("sfx/sniper-die.mp3");
        sfx_sniperEasterEgg = assetManager.get("sfx/sniper-easter-egg.mp3");
        sfx_sniperSelected1 = assetManager.get("sfx/sniper-selected-1.mp3");
        sfx_sniperSelected2 = assetManager.get("sfx/sniper-selected-2.mp3");
        sfx_sniperSelected3 = assetManager.get("sfx/sniper-selected-3.mp3");
        sfx_sniper = assetManager.get("sfx/sniper.mp3");
        sfx_spit = assetManager.get("sfx/spit.mp3");
        sfx_tank = assetManager.get("sfx/tank.mp3");
        sfx_troopDeploy = assetManager.get("sfx/troop-deploy.mp3");
        sfx_victory = assetManager.get("sfx/victory.mp3");
        sfx_witch = assetManager.get("sfx/witch.mp3");
        sfx_zombieDeath = assetManager.get("sfx/zombie death.mp3");
        sfx_zombieGargle = assetManager.get("sfx/zombie gargle.mp3");
        sfx_zombieGrowl = assetManager.get("sfx/zombie-growl.mp3");
        sfx_zombieRally = assetManager.get("sfx/zombie-rally.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.ogg");
        bgm_game = assetManager.get("bgm/game.ogg");
        bgm_menu = assetManager.get("bgm/menu.ogg");
    }

    public static class SpineActor {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationFlagBlue;

        public static Animation animationFlagGreen;

        public static Animation animationFlagNone;

        public static Animation animationFlagOrange;

        public static Animation animationFlagPurple;

        public static Animation animationHurtLeft;

        public static Animation animationHurtRight;

        public static Animation animationSelectedBlue;

        public static Animation animationSelectedGreen;

        public static Animation animationSelectedOrange;

        public static Animation animationSelectedPurple;

        public static Animation animationShoot;

        public static Animation animationSpit;

        public static Animation animationStand;

        public static Animation animationWalk;

        public static com.esotericsoftware.spine.Skin skinDefault;

        public static com.esotericsoftware.spine.Skin skinAssault;

        public static com.esotericsoftware.spine.Skin skinExploder;

        public static com.esotericsoftware.spine.Skin skinHeavy;

        public static com.esotericsoftware.spine.Skin skinMilitia;

        public static com.esotericsoftware.spine.Skin skinPouncer;

        public static com.esotericsoftware.spine.Skin skinSniper;

        public static com.esotericsoftware.spine.Skin skinSpitter;

        public static com.esotericsoftware.spine.Skin skinWitch;

        public static com.esotericsoftware.spine.Skin skinZombie;
    }

    public static class SpineBloodCloud {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineBloodSplatter {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineBomb {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineCoin {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineExplosion {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineHouse {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAlive;

        public static Animation animationDestroyed;

        public static com.esotericsoftware.spine.Skin skinDefault;

        public static com.esotericsoftware.spine.Skin skinHouse1;

        public static com.esotericsoftware.spine.Skin skinHouse2;
    }

    public static class SpineLibgdx {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineMissile {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineMove {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationError;

        public static Animation animationMove;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpinePoisonCloud {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRay3k {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineShip {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationNoThruster;

        public static Animation animationThruster;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSmoke {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSpit {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineThrusterSmall {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineThruster {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SkinSkinStyles {
        public static ImageButton.ImageButtonStyle ibLevel8;

        public static ImageButton.ImageButtonStyle ibOptions;

        public static ImageButton.ImageButtonStyle ibLevel13;

        public static ImageButton.ImageButtonStyle ibDefaults;

        public static ImageButton.ImageButtonStyle ibLevel5;

        public static ImageButton.ImageButtonStyle ibDefault;

        public static ImageButton.ImageButtonStyle ibMortar;

        public static ImageButton.ImageButtonStyle ibLevel10;

        public static ImageButton.ImageButtonStyle ibLevel2;

        public static ImageButton.ImageButtonStyle ibMine;

        public static ImageButton.ImageButtonStyle ibLevel15;

        public static ImageButton.ImageButtonStyle ibLevel7;

        public static ImageButton.ImageButtonStyle ibAssault;

        public static ImageButton.ImageButtonStyle ibSniper;

        public static ImageButton.ImageButtonStyle ibLevel12;

        public static ImageButton.ImageButtonStyle ibBomb;

        public static ImageButton.ImageButtonStyle ibLevel4;

        public static ImageButton.ImageButtonStyle ibMilitia;

        public static ImageButton.ImageButtonStyle ibLevel9;

        public static ImageButton.ImageButtonStyle ibLevel1;

        public static ImageButton.ImageButtonStyle ibLevel14;

        public static ImageButton.ImageButtonStyle ibOk;

        public static ImageButton.ImageButtonStyle ibLevel6;

        public static ImageButton.ImageButtonStyle ibLevel11;

        public static ImageButton.ImageButtonStyle ibHeavy;

        public static ImageButton.ImageButtonStyle ibCredits;

        public static ImageButton.ImageButtonStyle ibLevel3;

        public static ImageButton.ImageButtonStyle ibPlay;

        public static ImageButton.ImageButtonStyle ibBindings;

        public static Label.LabelStyle lDefault;

        public static ScrollPane.ScrollPaneStyle spDefault;

        public static Slider.SliderStyle sDefaultHorizontal;

        public static TextButton.TextButtonStyle tbToggle;

        public static TextButton.TextButtonStyle tbDefault;

        public static TextField.TextFieldStyle tfDefault;

        public static TextTooltip.TextTooltipStyle ttDefault;

        public static Window.WindowStyle wDefault;
    }

    public static class Values {
        public static boolean debugWalkable = false;

        public static boolean debugJbump = false;

        public static Range militiaMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float militiaMoveSpeed = 100.0f;

        public static int militiaDamage = 2;

        public static float militiaShotDelayBottom = 0.8f;

        public static float militiaShotDelayTop = 0.9f;

        public static Range militiaShotRangeRange = new Range(0.0f, 1000.0f);

        public static float militiaShotRange = 100.0f;

        public static int militiaSquadSize = 6;

        public static Range assaultMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float assaultMoveSpeed = 120.0f;

        public static int assaultDamage = 3;

        public static float assaultShotDelayBottom = 0.8f;

        public static float assaultShotDelayTop = 0.8f;

        public static Range assaultShotRangeRange = new Range(0.0f, 1000.0f);

        public static float assaultShotRange = 150.0f;

        public static int assaultSquadSize = 6;

        public static Range sniperMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float sniperMoveSpeed = 100.0f;

        public static int sniperDamage = 20;

        public static float sniperShotDelayBottom = 1.2f;

        public static float sniperShotDelayTop = 1.2f;

        public static Range sniperShotRangeRange = new Range(0.0f, 1000.0f);

        public static float sniperShotRange = 450.0f;

        public static int sniperSquadSize = 2;

        public static Range heavyMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float heavyMoveSpeed = 40.0f;

        public static int heavyDamage = 10;

        public static float heavyShotDelayBottom = 1.3f;

        public static float heavyShotDelayTop = 1.5f;

        public static Range heavyShotRangeRange = new Range(0.0f, 1000.0f);

        public static float heavyShotRange = 220.0f;

        public static Range heavySplashRangeRange = new Range(0.0f, 1000.0f);

        public static float heavySplashRange = 50.0f;

        public static Range heavyMissileDelayRange = new Range(0.0f, 1000.0f);

        public static float heavyMissileDelay = 0.5f;

        public static int heavySquadSize = 3;

        public static Range soldierHurtDelayRange = new Range(0.0f, 5.0f);

        public static float soldierHurtDelay = 0.5f;

        public static Range soldierHurtSpeedRange = new Range(0.0f, 1000.0f);

        public static float soldierHurtSpeed = 400.0f;

        public static Range soldierHurtFrictionRange = new Range(0.0f, 1000.0f);

        public static float soldierHurtFriction = 1000.0f;

        public static int soldierHealth = 10;

        public static Range soldierImproveSpeedRange = new Range(0.0f, 400.0f);

        public static float soldierImproveSpeed = 25.0f;

        public static int soldierImproveDamage = 2;

        public static Range soldierImproveRangeRange = new Range(0.0f, 400.0f);

        public static float soldierImproveRange = 50.0f;

        public static int soldierImproveHealth = 1;

        public static Range soldierImproveSplashRange = new Range(0.0f, 400.0f);

        public static float soldierImproveSplash = 10.0f;

        public static int soldierImproveSquadSize = 1;

        public static Range zombieMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float zombieMoveSpeed = 75.0f;

        public static int zombieDamage = 2;

        public static int zombieHealth = 6;

        public static Range pouncerMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float pouncerMoveSpeed = 300.0f;

        public static int pouncerDamage = 2;

        public static int pouncerHealth = 25;

        public static Range tankMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float tankMoveSpeed = 75.0f;

        public static int tankDamage = 2;

        public static int tankHealth = 6;

        public static Range witchMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float witchMoveSpeed = 75.0f;

        public static int witchDamage = 2;

        public static int witchHealth = 6;

        public static Range spitterMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float spitterMoveSpeed = 55.0f;

        public static int spitterDamage = 3;

        public static int spitterHealth = 20;

        public static Range spitterRangeRange = new Range(0.0f, 1000.0f);

        public static float spitterRange = 200.0f;

        public static Range spitterDelayRange = new Range(0.0f, 5.0f);

        public static float spitterDelay = 1.9f;

        public static Range exploderMoveSpeedRange = new Range(0.0f, 1000.0f);

        public static float exploderMoveSpeed = 75.0f;

        public static int exploderDamage = 2;

        public static int exploderHealth = 1;

        public static Range exploderRangeRange = new Range(0.0f, 1000.0f);

        public static float exploderRange = 100.0f;

        public static Range enemyHurtDelayRange = new Range(0.0f, 5.0f);

        public static float enemyHurtDelay = 0.5f;

        public static Range enemyHurtSpeedRange = new Range(0.0f, 1000.0f);

        public static float enemyHurtSpeed = 400.0f;

        public static Range enemyHurtFrictionRange = new Range(0.0f, 1000.0f);

        public static float enemyHurtFriction = 1000.0f;

        public static Range enemyToPlayerMinDistanceRange = new Range(0.0f, 1000.0f);

        public static float enemyToPlayerMinDistance = 200.0f;

        public static Range shipSpeedRange = new Range(0.0f, 1000.0f);

        public static float shipSpeed = 50.0f;

        public static Range shipSpawnDelayRange = new Range(0.0f, 5.0f);

        public static float shipSpawnDelay = 0.4f;

        public static int houseHealth = 10;

        public static Range houseHurtDelayRange = new Range(0.0f, 5.0f);

        public static float houseHurtDelay = 0.5f;
    }

    public static class Range {
        public float min;

        public float max;

        Range(float min, float max) {
            this.min = min;
            this.max = max;
        }
    }
}
