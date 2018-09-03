package com.shaunlu.codeforfun.asmpoc.hacker;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.lang.reflect.Field;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static jdk.internal.org.objectweb.asm.Opcodes.V1_8;

public class ClassGenerateExample {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC, "com/shaunlu/codeforfun/DynamicUser", null, "java/lang/Object", null);
        cw.visitField(ACC_PUBLIC, "name", "Ljava/lang/String;", "shaun", null).visitEnd();
//        cw.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader(ClassGenerateExample.class.getClassLoader());
        Class c = myClassLoader.defineClass("com.shaunlu.codeforfun.DynamicUser", bytes);
        ClassReader cr = new ClassReader(bytes);
        ClassPrinter cp = new ClassPrinter();
        cr.accept(cp, 0);
        cp.printClassStructure();
    }

    public static class MyClassLoader extends ClassLoader {

        public MyClassLoader(ClassLoader parent){
            super(parent);
        }

        public Class defineClass(String name, byte[] bytes) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
