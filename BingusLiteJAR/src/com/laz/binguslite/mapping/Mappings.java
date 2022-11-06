package com.laz.binguslite.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Mappings {
    public static Class<?> teamClass;

    public static Class<?> chatComponentTextClass;

    public static Class<?> scoreObjectiveClass;
    public static Method getDisplayNameMethod;

    public static Class<?> iChatComponentClass;
    public static Method getUnformattedTextMethod;
    public static Method getFormattedTextMethod;

    public static Class<?> scoreboardClass;
    public static Method getObjectiveInDisplaySlotMethod;
    public static Method getSortedScoresMethod;
    public static Method getPlayersTeamMethod;

    public static Class<?> networkPlayerInfoClass;
    public static Method getGameProfileMethod;

    public static Class<?> minecraftClass;
    public static Field theWorldField;
    public static Field inGameHasFocusField;
    public static Field gameSettingsField;
    public static Field fontRendererObjField;
    public static Field thePlayerField;
    public static Method getMinecraftMethod;
    public static Method getCurrentServerDataMethod;
    public static Method runGameLoopMethod;
    public static Method runTickMethod;

    public static Class<?> worldClass;
    public static Method getScoreboardMethod;

    public static Class<?> scoreClass;
    public static Method sGetPlayerNameMethod;

    public static Class<?> scorePlayerTeamClass;
    public static Method formatPlayerNameMethod;

    public static Class<?> serverDataClass;
    public static Field serverIPField;

    public static Class<?> stringUtilsClass;
    public static Method stripControlCodesMethod;

    public static Class<?> gameSettingsClass;
    public static Field showDebugInfoField;

    public static Class<?> glStateManagerClass;
    public static Method pushMatrixMethod;
    public static Method scaleMethod;
    public static Method popMatrixMethod;

    public static Class<?> fontRendererClass;
    public static Method drawStringMethod;

    public static Class<?> entityPlayerClass;
    public static Method addChatComponentMessageMethod;
    public static Method jumpMethod;

    public static Class<?> entityClass;
    public static Field motionXField;
    public static Field motionYField;
    public static Field motionZField;
    public static Field rotationYawField;
    public static Field rotationPitchField;
    public static Field onGroundField;
    public static Method moveEntityMethod;

    public static Class<?> guiNewChatClass;
    public static Method printChatMessageMethod;

    public static Class<?> entityPlayerSPClass;
    public static Field movementInputField;
    public static Method sendChatMessageMethod;

    public static Class<?> movementInputClass;
    public static Field moveStrafeField;
    public static Field moveForwardField;
    public static Field jumpField;
    public static Field sneakField;

    public static Class<?> entityRendererClass;
    public static Method updateCameraAndRenderMethod;

    public static Class<?> guiPlayerTabOverlayClass;
    public static Method renderPlayerlistMethod;
    public static Method gptoGetPlayerNameMethod;
}
