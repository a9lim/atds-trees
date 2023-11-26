package Util;

public class Vec{

    private double[] elements;

    // CONSTRUCTORS:

    // array of doublelex numbers
    public Vec(double[] c){
        elements = new double[c.length];
        for(int i = 0; i < c.length; i++)
            elements[i] = c[i];
    }

    // new vector with l elements
    public Vec(int l){
        elements = new double[l];
    }


    // GETTERS/ATTRIBUTES

    // return elements
    public double[] elements(){
        return elements;
    }

    // return one term
    public double term(int i){
        return elements[i];
    }

    // length
    public int length(){
        return elements.length;
    }

    // MODIFIERS

    // set a vector
    public void set(Vec c){
        elements = new double[c.length()];
        for(int i = 0; i < length(); i++)
            elements[i] = c.term(i);
    }

    // BINARY OPERATIONS: C^n x C^n

    // sum
    public static Vec sum(Vec a, Vec b){
        double[] out = new double[a.length()];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) + b.term(i);
        return new Vec(out);
    }

    // difference
    public static Vec diff(Vec a, Vec b){
        double[] out = new double[a.length()];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) - b.term(i);
        return new Vec(out);
    }

    public static double iprod(Vec a, Vec b){
        double sum = 0;
        for(int i = 0; i < a.length(); i++)
            sum += a.term(i) * b.term(i);
        return sum;
    }

    // NON STATIC BINARY OPERATIONS

    // +=
    public void sum(Vec b){
        for(int i = 0; i < length(); i++)
            elements[i] += b.term(i);
    }

    // -=
    public void diff(Vec b){
        for(int i = 0; i < length(); i++)
            elements[i] -= b.term(i);
    }

    // R^n x R

    // vector-scalar product
    public static Vec prod(Vec a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = a.term(i) * c;
        return new Vec(out);
    }

    // scalar quotient
    public static Vec quot(Vec a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = a.term(i) / c;
        return new Vec(out);
    }

    // NON STATIC

    // C^n x C

    // *=
    public void prod( double c ){
        for(int i = 0; i < length();i++)
            elements[i] *= c;
    }

    // /=
    public void quot( double c ){
        for(int i = 0; i < length();i++)
            elements[i] /= c;
    }


    // BOOLEANS


    // is every element zero?
    public boolean isz(){
        for(double c: elements)
            if(c != 0)
                return false;
        return true;
    }


    // is equal?
    public boolean equals(Vec a){
        if(length() != a.length())
            return false;
        for(int i = 0; i < length(); i++)
            if(elements[i] != a.term(i))
                return false;
        return true;
    }

    // TOOLS

    // magnitude-squared
    // equiv to iprod(v,v)

    public double msq(){
        double out = 0;
        for(double i: elements)
            out += i*i;
        return out;
    }

    // magnitude
    // equiv to sqrt(msq)
    public double mag(){
        return elements.length > 2 ? Math.sqrt(msq()) : Math.hypot(elements[0],elements[1]);
    }

    //hat
    public Vec hat(){
        return quot(this,mag());
    }

    // random real vector
    public static Vec rrand(int u, double r){
        double[] out = new double[u];
        for(int i = 0; i < out.length; i++)
            out[i] = Math.random()*r*2-r;
        return new Vec(out);
    }

    public String toString(){
        String s = "⟨ ";
        for(int i = 0; i < length()-1; i++){
            s += elements[i] + "   ";
        }
        s += elements[length()-1] + " ⟩\n";
        return s;
    }

}
