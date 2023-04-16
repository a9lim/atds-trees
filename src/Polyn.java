import java.util.*;

// polynomial class
public class Polyn {

    private double[] coeffs;

    // CONSTRUCTORS:

    public Polyn(double[] c){
        coeffs = c;
    }
    // copy another polynomial
    public Polyn(Polyn p){
        coeffs = new double[p.length()];
        for(int i = 0; i < p.length(); i++)
            coeffs[i] = p.term(i);
    }

    // 0 polynomial
    public Polyn(int l){
        coeffs = new double[l];;
    }

    // GETTERS

    // return coefficients
    public double[] coeffs(){
        return coeffs;
    }

    // return one term
    public double term(int i){
        return i < coeffs.length ? coeffs[i] : 0;
    }

    // length of polynomial
    // order = length -1
    public int length(){
        return coeffs.length;
    }

    // BINARY OPERATIONS: PR x PR

    //sum
    public static Polyn sum(Polyn a, Polyn b){
        double[] out = new double[Math.max(a.length(),b.length())];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) + b.term(i);
        return new Polyn(out);
    }

    //difference
    public static Polyn diff(Polyn a, Polyn b){
        double[] out = new double[Math.max(a.length(),b.length())];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) - b.term(i);
        return new Polyn(out);
    }

    //product
    public static Polyn prod(Polyn a, Polyn b){
        double[] out = new double[a.length() + b.length() - 1];
        for(int i = 0; i < a.length(); i++)
            for(int j = 0; j < b.length(); j++)
                // intuitive: just multiplying terms together and ln(ab) = a + b, so we add indices
                out[j+i] += ( a.term(i) * b.term(j) );
        return new Polyn(out);
    }

    // quotient
    // without remainder
    // synthetic division
    public static Polyn quot(Polyn a, Polyn b){
        if(a.length()-b.length()+1 < 0)
            return new Polyn(new double[0]);
        switch (b.length()) {
            // if possible, use horner's or scalar division
            case 1:
                return a.quot(b.term(0));
            case 2:
                return a.equot(b.term(0));
            default:
                double[] out = new double[a.length() - b.length() + 1];
                double c = b.term(b.length() - 1);

                // copy terms
                for (int i = 0; i < out.length; i++)
                    out[out.length - i - 1] = a.term(a.length() - i - 1);

                // divide and smear over smaller terms
                for (int i = out.length - 1; i >= 0; i--) {
                    out[i] /= (c);
                    for (int j = 1 ; j < b.length() && j <= i; j++)
                        out[i-j] -= (b.term(b.length()-j-1)* out[i]);
                }
                return new Polyn(out);
        }
    }
    // NON STATIC
    // same as before
    public Polyn quot(Polyn b){
        double[] out = new double[length()-b.length()+1];
        double c = b.term(b.length()-1);
        for(int i = 0; i < out.length; i++)
            out[out.length -i-1] = coeffs[length()-i-1];
        for(int i = 0; i < out.length; i++){
            out[out.length -i-1] /= c;
            for(int j = 1; j < out.length - i && j < b.length() ; j++)
                out[out.length -i-j-1] -= (b.term(b.length()-j-1) * out[out.length -i-1]);
        }
        return new Polyn(out);
    }

    //single term split
    // w/ horners
    // just explicit synthetic on a term of (x-c)
    public Polyn equot(double c){
        double[] out = new double[length()-1];
        out[out.length - 1] = coeffs[out.length];
        // taking advantage of the polynomial remainder theorem
        for(int i = out.length-1; i > 0; i--)
            out[i-1] = coeffs[i] + c * out[i];
        return new Polyn(out);
    }

    // PC x C

    // scalar polynomial product
    public Polyn prod( double c ){
        double[] out = new double[length()];
        for(int i = 0; i < length();i++)
            out[i] = coeffs[i] * c;
        return new Polyn(out);
    }

    // scalar polynomial quotient
    public Polyn quot( double c ){
        double[] out = new double[length()];
        for(int i = 0; i < length();i++)
            out[i] = coeffs[i] / c;
        return new Polyn(out);
    }

    // SPECIAL POLYNOMIAL OPERATIONS!!!!
    // mostly solving

    // derivative
    public Polyn dx(){
        double[] out = new double[length()-1];
        for(int i = 1; i < length(); i++)
            out[i-1] = coeffs[i]*i;
        return new Polyn(out);
    }

    // value at one doublelex number
    // evaluated implicitly
    public double eval( double c ){
        double out = coeffs[length()-1];
        // horner's method:
        // a x^n + b x^(n-1) + c x^(n-2)...
        // = a x * ( b x * ( c x * (...
        for(int i = length()-2; i >= 0; i--){
            out *= c;
            out += coeffs[i];
        }
        return out;
    }

    // is equal?
    public boolean equals(Polyn a){
        if(length() != a.length())
            return false;
        for(int i = 0; i < length(); i++)
            if(coeffs[i] != a.term(i))
                return false;
        return true;
    }


    // count leading zeros
    // not a boolean but same category
    public int zs(){
        for(int i = 0; i < length(); i++)
            if(coeffs[i] != 0)
                return i;
        return length() - 1;
    }

    // TOOLS


    // flatten: setting the largest coeff to 1
    // equiv to quot(term(length() -1 ))
    public void flat(){
        double e = coeffs[length()-1];
        for(int i = 0; i < length()-1;i++)
            coeffs[i] /= e;
        coeffs[length()-1]= 1;
    }

    // remove first j terms
    public Polyn shrink(int j){
        double[] u = new double[length()-j];
        for(int i = 0; i < u.length; i++)
            u[i] = coeffs[i+j];
        return new Polyn(u);
    }


    // random real polynomial
    public static Polyn rrand(int u, double r){
        double[] out = new double[u];
        for(int i = 0; i < out.length; i++)
            out[i] = Math.random()*r*2-r;
        return new Polyn(out);
    }

    public String toString(){
        String s = "⟨ ";
        for(int i = 0; i < length()-1; i++){
            s += coeffs[i] + "   ";
        }
        s += coeffs[length()-1] + " ⟩\n";
        return s;
    }
}