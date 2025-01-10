class Power{
    public static void compute(int a, int b){
        long res = (long)Math.pow(a,b);
        System.out.println(a + " raised to the power of " + b + " is "+res);
    }

    public static void main(String[] args) {
        compute(2, 31);
    }
}

/*
1. Syntax: Java uses semicolons (;) to terminate statements, while Python does not require any punctuation.

2. Data Types: In Java, we need to specify data types, whereas Python does not require explicit type declarations.

3. Compilation: Java is a compiled language, meaning we need to compile the code with `javac` before running it with `java`.
                Python is an interpreted language, so we can run the script directly with the Python interpreter.

4. Class Requirement: In Java, all code must be contained within a class. However, Python does not have this requirement.
*/