package com.laz.binguslite.modules.ghost;

import com.laz.binguslite.events.Event;
import com.laz.binguslite.events.listeners.EventRenderTick;
import com.laz.binguslite.modules.Module;
import com.laz.binguslite.settings.BooleanSetting;
import com.laz.binguslite.settings.NumberSetting;

import java.io.IOException;


public class AutoClicker extends Module {
    private final NumberSetting minCPS = new NumberSetting("Min CPS", 9, 1, 20, 1);
    private final NumberSetting maxCPS = new NumberSetting("Max CPS", 13, 1, 20, 1);
    private final NumberSetting minBHPS = new NumberSetting("Min BHPS", 5, 0.1, 12, 0.1);
    private final NumberSetting maxBHPS = new NumberSetting("Max BHPS", 6, 0.1, 12, 0.1);
    private final NumberSetting jitterStrength = new NumberSetting("Jitter", 0, 0, 1.0, 0.01);
    private final BooleanSetting withKillAura = new BooleanSetting("With KillAura", false);
    private final BooleanSetting blockHit = new BooleanSetting("Block Hit", true);
    private final BooleanSetting breakBlocks = new BooleanSetting("Break Blocks", true);
    private final BooleanSetting inventoryFill = new BooleanSetting("Inventory", false);
    private final BooleanSetting removeDelay = new BooleanSetting("No 1.8 Delay", false);
    private final BooleanSetting handOnly = new BooleanSetting("Hand", false);
    private final BooleanSetting swordsOnly = new BooleanSetting("Swords", true);
    private final BooleanSetting axesOnly = new BooleanSetting("Axes", false);
    private final BooleanSetting pickaxesOnly = new BooleanSetting("Pickaxes", false);
    private final BooleanSetting shovelsOnly = new BooleanSetting("Shovels", false);
    private long nextLeftUp;
    private long nextLeftDown;
    private long nextRightUp;
    private long nextRightDown;
    private long nextDrop;
    private long nextExhaust;
    private double dropRate;
    private boolean dropping;

    public AutoClicker() {
        super("Auto Clicker", Category.GHOST);
        setToggled(true);
    }

    @Override
    public String getSuffix() {
        return Math.round(minCPS.getValue()) + "-" + Math.round(maxCPS.getValue());
    }

    @Override
    public void onEvent(Event event) {
//        if (event instanceof EventRenderTick) {
//            if (minCPS.getValue() > maxCPS.getValue())
//                maxCPS.setValue(minCPS.getValue());
//
//            if (minBHPS.getValue() > maxBHPS.getValue())
//                maxBHPS.setValue(minBHPS.getValue());
//
//            if (mc.currentScreen == null && canClick() && (!breakBlocks.isEnabled() || !mc.playerController.getIsHittingBlock())) {
//                Mouse.poll();
//                if (Mouse.isButtonDown(0)) {
//                    if (jitterStrength.getValue() > 0 && random.nextDouble() > 0.65) {
//                        float jitterStrength = (float) this.jitterStrength.getValue() * 0.5f;
//                        mc.thePlayer.rotationYaw = random.nextBoolean() ? (mc.thePlayer.rotationYaw += random.nextFloat() * jitterStrength) : (mc.thePlayer.rotationYaw -= random.nextFloat() * jitterStrength);
//                        mc.thePlayer.rotationPitch = random.nextBoolean() ? (float) ((double) mc.thePlayer.rotationPitch + (double) random.nextFloat() * ((double) jitterStrength * 0.75)) : (float) ((double) mc.thePlayer.rotationPitch - (double) random.nextFloat() * ((double) jitterStrength * 0.75));
//                    }
//                    if (nextLeftDown > 0L && nextLeftUp > 0L) {
//                        if (System.currentTimeMillis() > nextLeftDown) {
//                            if (removeDelay.isEnabled())
//                                mc.leftClickCounter = 0;
//                            int attackKeyBind = mc.gameSettings.keyBindAttack.getKeyCode();
//                            KeyBinding.setKeyBindState(attackKeyBind, true);
//                            KeyBinding.onTick(attackKeyBind);
//                            MouseUtil.sendClick(0, true);
//                            generateLeftDelay();
//                        } else if (System.currentTimeMillis() > nextLeftUp) {
//                            int attackKeyBind = mc.gameSettings.keyBindAttack.getKeyCode();
//                            KeyBinding.setKeyBindState(attackKeyBind, false);
//                            MouseUtil.sendClick(0, false);
//                        }
//                    } else {
//                        generateLeftDelay();
//                    }
//                    if (blockHit.isEnabled() && Mouse.isButtonDown(1)) {
//                        if (nextRightDown > 0L && nextRightUp > 0L) {
//                            if (System.currentTimeMillis() > nextRightDown) {
//                                int useItemKeyBind = mc.gameSettings.keyBindUseItem.getKeyCode();
//                                KeyBinding.setKeyBindState(useItemKeyBind, true);
//                                KeyBinding.onTick(useItemKeyBind);
//                                MouseUtil.sendClick(1, true);
//                                generateRightDelay();
//                            } else if (System.currentTimeMillis() > nextRightUp) {
//                                int useItemKeyBind = mc.gameSettings.keyBindUseItem.getKeyCode();
//                                KeyBinding.setKeyBindState(useItemKeyBind, false);
//                                MouseUtil.sendClick(1, false);
//                            }
//                        } else {
//                            generateRightDelay();
//                        }
//                    } else {
//                        nextRightUp = 0L;
//                        nextRightDown = 0L;
//                    }
//                } else {
//                    nextRightUp = 0L;
//                    nextRightDown = 0L;
//                    nextLeftUp = 0L;
//                    nextLeftDown = 0L;
//                }
//            } else if (mc.currentScreen instanceof GuiContainer) {
//                if (Mouse.isButtonDown(0) && (Keyboard.isKeyDown(54) || Keyboard.isKeyDown(42))) {
//                    if (!inventoryFill.isEnabled()) {
//                        return;
//                    }
//                    if (nextLeftUp == 0L || nextLeftDown == 0L) {
//                        generateLeftDelay();
//                        return;
//                    }
//                    if (System.currentTimeMillis() > nextLeftDown) {
//                        generateLeftDelay();
//
//                        GuiContainer guiContainer = (GuiContainer) mc.currentScreen;
//
//                        int x = Mouse.getX() * guiContainer.width / mc.displayWidth;
//                        int y = guiContainer.height - Mouse.getY() * guiContainer.height / mc.displayHeight - 1;
//
//                        boolean isDoubleClick = guiContainer.lastClickSlot == guiContainer.getSlotAtPosition(x, y) && Minecraft.getSystemTime() - guiContainer.lastClickTime < 250L && guiContainer.lastClickButton == 0;
//
//                        if (!isDoubleClick) {
//                            try {
//                                guiContainer.mouseClicked(x, y, 0);
//                            } catch (IOException exception) {
//                                exception.printStackTrace();
//                            }
//                        }
//                    }
//                } else {
//                    nextRightUp = 0L;
//                    nextRightDown = 0L;
//                    nextLeftUp = 0L;
//                    nextLeftDown = 0L;
//                }
//            }
//        }
    }

    private void generateLeftDelay() {
        double cps = minCPS.getValue() + (maxCPS.getValue() - minCPS.getValue()) * random.nextDouble();
        long delay = (int) Math.round(1000.0 / cps);
        if (System.currentTimeMillis() > nextDrop) {
            if (!dropping && random.nextInt(100) >= 85) {
                dropping = true;
                dropRate = 1.1 + random.nextDouble() * 0.15;
            } else {
                dropping = false;
            }
            nextDrop = System.currentTimeMillis() + 500L + (long) random.nextInt(1500);
        }
        if (dropping) {
            delay = (long) ((double) delay * dropRate);
        }
        if (System.currentTimeMillis() > nextExhaust) {
            if (random.nextInt(100) >= 80) {
                delay += 50L + (long) random.nextInt(150);
            }
            nextExhaust = System.currentTimeMillis() + 500L + (long) random.nextInt(1500);
        }
        nextLeftDown = System.currentTimeMillis() + delay;
        nextLeftUp = System.currentTimeMillis() + delay / 2L - (long) random.nextInt(10);
    }

    private void generateRightDelay() {
        double cps = minBHPS.getValue() + (maxBHPS.getValue() - minBHPS.getValue()) * random.nextDouble();
        long delay = (int) Math.round(1000.0 / cps);
        nextRightDown = System.currentTimeMillis() + delay;
        nextRightUp = System.currentTimeMillis() + 20L + (long) random.nextInt(30);
    }

    private boolean canClick() {
        return true;
    }
}
