package com.shaunlu.codeforfun.asmpoc.hacker;

import jdk.internal.org.objectweb.asm.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

public class ClassPrinter extends ClassVisitor {

    private StringBuffer printStr = new StringBuffer();

    public ClassPrinter() {
        super(ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // class name
        printStr.append(name);
        // super class
        if (null != superName && !superName.isEmpty()) {
            printStr.append(" extends ").append(superName);
        }
        // interfaces
        if (null != interfaces && 0 != interfaces.length) {
            printStr.append(" implements ");
            for (int i = 0; i < interfaces.length; i++) {
                printStr.append(interfaces[i]);
                if ((interfaces.length - 1) != i) {
                    printStr.append(", ");
                }
            }
        }
        printStr.append(" {");
    }

    @Override
    public void visitSource(String s, String s1) {
        super.visitSource(s, s1);
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
        super.visitOuterClass(s, s1, s2);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
        return super.visitTypeAnnotation(i, typePath, s, b);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }

    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
        super.visitInnerClass(s, s1, s2, i);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        printStr.append("\n\t");
        printStr.append(getAccessModifier(access)).append(" ").append(name).append(" ").append(desc);
        if (null != value) {
            printStr.append(" ").append(value.toString());
        }
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        printStr.append("\n\t");
        printStr.append(getAccessModifier(access)).append(" ").append(name).append(" ").append(desc);
        if (null != exceptions && 0 != exceptions.length) {
            printStr.append(" throws ");
            for (int i = 0; i < exceptions.length; i++) {
                printStr.append(exceptions[i]);
                if ((exceptions.length - 1) != i) {
                    printStr.append(", ");
                }
            }
        }
        return null;
    }

    @Override
    public void visitEnd() {
        printStr.append("\n}");
    }

    private String getAccessModifier(int access) {
        String ret = null;
        switch (access) {
            case 1:
                ret = "public";
                break;
            case 2:
                ret = "private";
                break;
            case 3:
                ret = "protected";
                break;
            default:
                break;
        }
        return ret;
    }


    public void printClassStructure() {
        System.out.println(printStr.toString());
    }
}
