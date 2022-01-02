import java.lang.Math;

public class Expr {
    double A;

    public double eval(double x, double y) {
        return 0;
    }

}

class X extends Expr {
    public String toString() {
        return ("X");
    }

    public double eval(double x, double y) {
        return x;
    }

}

class Y extends Expr {
    public String toString() {
        return ("Y");
    }

    public double eval(double x, double y) {
        return y;
    }
}

class Sin extends Expr {
    Expr op;

    Sin(Expr o) {
        op = o;
    }

    public String toString() {
        return ("sin(Pi*" + op + ")");
    }
    public double eval(double x, double y) {
        return Math.sin(op.eval(x, y) * Math.PI);
    }

}

class Cos extends Expr {
    Expr op;

    Cos(Expr o) {
        op = o;
    }

    public String toString() {
        return ("cos(Pi*" + op + ")");
    }

    public double eval(double x, double y) {
        return Math.cos(op.eval(x, y) * Math.PI);
    }
}

class Moyenne extends Expr {
    Expr op1;
    Expr op2;

    Moyenne(Expr o1, Expr o2) {
        op1 = o1;
        op2 = o2;
    }

    public String toString() {
        return ("(" + op1 + "+" + op2 + ")/2");
    }

    public double eval(double x, double y) {
        return (op1.eval(x, y) + op2.eval(x, y)) / 2;
    }
}

class Mult extends Expr {
    Expr op1;
    Expr op2;

    Mult(Expr o1, Expr o2) {
        op1 = o1;
        op2 = o2;
    }

    public String toString() {
        return (op1 + "*" + op2);
    }

    public double eval(double x, double y) {
        return (op1.eval(x, y) * op2.eval(x, y));
    }
}