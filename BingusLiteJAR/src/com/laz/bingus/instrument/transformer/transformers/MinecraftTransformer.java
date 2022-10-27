package com.laz.bingus.instrument.transformer.transformers;

import com.laz.bingus.Client;
import com.laz.bingus.instrument.transformer.CustomClassWriter;
import com.laz.bingus.instrument.transformer.Transformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.security.ProtectionDomain;
import java.util.List;

import static com.laz.bingus.mapping.Mappings.*;
import static org.objectweb.asm.Opcodes.*;

public class MinecraftTransformer implements Transformer {
    private final Client client;

    public MinecraftTransformer(Client client) {
        this.client = client;
    }

    @Override
    public byte[] transform(ClassLoader loader, String name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] bytes) {
        if (classBeingRedefined == minecraftClass) {
            ClassReader classReader = new ClassReader(bytes);
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);
            for (MethodNode method : (List<MethodNode>) classNode.methods) {
                if (method.name.equals(runGameLoopMethod.getName()) && method.desc.equals("()V")) {
                    for (AbstractInsnNode abstractInsnNode : method.instructions.toArray()) {
                        if (abstractInsnNode instanceof MethodInsnNode) {
                            MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;

                            boolean opcodeCheck;
                            boolean ownerCheck;
                            boolean nameCheck;
                            boolean descCheck;

                            switch (client) {
                                case LUNAR:
                                    opcodeCheck = methodInsnNode.getOpcode() == INVOKEVIRTUAL || methodInsnNode.getOpcode() == INVOKESPECIAL;
                                    ownerCheck = methodInsnNode.owner.equals(entityRendererClass.getName().replace('.', '/'))
                                            || methodInsnNode.owner.equals(minecraftClass.getName().replace('.', '/'));
                                    nameCheck = methodInsnNode.name.equals(updateCameraAndRenderMethod.getName())
                                            || (methodInsnNode.name.startsWith("redirect$") && methodInsnNode.name.contains("impl$on" + updateCameraAndRenderMethod.getName().substring(0, 1).toUpperCase() + updateCameraAndRenderMethod.getName().substring(1)));
                                    descCheck = methodInsnNode.desc.equals("(FJ)V")
                                            || methodInsnNode.desc.equals("(L" + entityRendererClass.getName().replace('.', '/') + ";FJ)V");
                                    break;

                                default:
                                    opcodeCheck = methodInsnNode.getOpcode() == INVOKEVIRTUAL;
                                    ownerCheck = methodInsnNode.owner.equals(entityRendererClass.getName().replace('.', '/'));
                                    nameCheck = methodInsnNode.name.equals(updateCameraAndRenderMethod.getName());
                                    descCheck = methodInsnNode.desc.equals("(FJ)V");
                            }

                            if (opcodeCheck
                                    && ownerCheck
                                    && nameCheck
                                    && descCheck) {
                                method.instructions.insert(methodInsnNode, onRender());

                                break;
                            }
                        }
                    }

                    break;
                }
            }
            ClassWriter classWriter = new CustomClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        return bytes;
    }

    private InsnList onRender() {
        InsnList insnList = new InsnList();

        insnList.add(new FieldInsnNode(GETSTATIC, "com/laz/binguslite/BingusLite", "instance", "Lcom/laz/binguslite/BingusLite;"));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL, "com/laz/binguslite/BingusLite", "onRender", "()V", false));

        return insnList;
    }
}
