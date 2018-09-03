package com.shaunlu.codeforfun.asmpoc.hacker;

import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.IOException;

public class ClassParseExample {

    public static void main(String[] args) throws IOException {

        ClassReader cr1 = new ClassReader("com.shaunlu.codeforfun.asmpoc.model.User");
        ClassPrinter cp1 = new ClassPrinter();
        cr1.accept(cp1, 0);
        cp1.printClassStructure();

        ClassReader cr2 = new ClassReader("com.shaunlu.codeforfun.asmpoc.model.Employee");
        ClassPrinter cp2 = new ClassPrinter();
        cr2.accept(cp2, 0);
        cp2.printClassStructure();
    }

}
