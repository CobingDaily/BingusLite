package com.laz.binguslite.instrument.transformer.transformers;

import com.laz.binguslite.instrument.transformer.CustomClassWriter;
import com.laz.binguslite.instrument.transformer.Transformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.security.ProtectionDomain;
import java.util.List;

import static com.laz.binguslite.mapping.Mappings.*;
import static org.objectweb.asm.Opcodes.*;

public class EntityPlayerTransformer implements Transformer {
    @Override
    public byte[] transform(ClassLoader loader, String name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] bytes) {
        if (classBeingRedefined == entityPlayerClass) {
            ClassReader classReader = new ClassReader(bytes);
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);
            for (MethodNode method : (List<MethodNode>) classNode.methods) {
                if (method.name.equals(jumpMethod.getName()) && method.desc.equals("()V")) {
                    method.instructions.insert(onJump());

                    break;
                }
            }
            ClassWriter classWriter = new CustomClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        return bytes;
    }

    private InsnList onJump() {
        InsnList insnList = new InsnList();

        insnList.add(new TypeInsnNode(NEW, "com/laz/binguslite/events/listeners/EventJump"));
        insnList.add(new InsnNode(DUP));
        insnList.add(new MethodInsnNode(INVOKESPECIAL, "com/laz/binguslite/events/listeners/EventJump", "<init>", "()V", false));
        insnList.add(new VarInsnNode(ASTORE, 1));

        insnList.add(new VarInsnNode(ALOAD, 0));
        insnList.add(new MethodInsnNode(INVOKESTATIC, "com/laz/binguslite/utilities/impl/PlayerUtil", "thePlayer", "()Ljava/lang/Object;", false));
        LabelNode ifacmpne = new LabelNode();
        insnList.add(new JumpInsnNode(IF_ACMPNE, ifacmpne));

        insnList.add(new FieldInsnNode(GETSTATIC, "com/laz/binguslite/BingusLite", "instance", "Lcom/laz/binguslite/BingusLite;"));
        insnList.add(new VarInsnNode(ALOAD, 1));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL, "com/laz/binguslite/BingusLite", "onEvent", "(Lcom/laz/binguslite/events/Event;)V", false));
        insnList.add(ifacmpne);

        insnList.add(new VarInsnNode(ALOAD, 1));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL, "com/laz/binguslite/events/listeners/EventJump", "isCancelled", "()Z", false));
        LabelNode ifeq = new LabelNode();
        insnList.add(new JumpInsnNode(IFEQ, ifeq));

        insnList.add(new InsnNode(RETURN));
        insnList.add(ifeq);

        return insnList;
    }
}
