import java.util.*;

public class Numbers {

    private double[] coeffs;

    // CONSTRUCTORS:

    public Numbers(double[] c){
        coeffs = c;
    }

    public Numbers(Numbers p){
        coeffs = new double[p.length()];
        for(int i = 0; i < p.length(); i++)
            coeffs[i] = p.term(i);
    }

    public Numbers(int l){
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

    // order = length -1
    public int length(){
        return coeffs.length;
    }

    // BINARY OPERATIONS

    //sum
    public static Numbers sum(Numbers a, Numbers b){
        double[] out = new double[Math.max(a.length(),b.length())];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) + b.term(i);
        return new Numbers(out);
    }

    //difference
    public static Numbers diff(Numbers a, Numbers b){
        double[] out = new double[Math.max(a.length(),b.length())];
        for(int i = 0; i < out.length; i++)
            out[i] = a.term(i) - b.term(i);
        return new Numbers(out);
    }

    //product
    public static Numbers prod(Numbers a, Numbers b){
        double[] out = new double[a.length() + b.length() - 1];
        for(int i = 0; i < a.length(); i++)
            for(int j = 0; j < b.length(); j++)
                // intuitive: just multiplying terms together and ln(ab) = a + b, so we add indices
                out[j+i] += ( a.term(i) * b.term(j) );
        return new Numbers(out);
    }

    // PC x C

    public Numbers prod( double c ){
        double[] out = new double[length()];
        for(int i = 0; i < length();i++)
            out[i] = coeffs[i] * c;
        return new Numbers(out);
    }

    public Numbers quot( double c ){
        double[] out = new double[length()];
        for(int i = 0; i < length();i++)
            out[i] = coeffs[i] / c;
        return new Numbers(out);
    }

    // is equal?
    public boolean equals(Numbers a){
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
    public Numbers shrink(int j){
        double[] u = new double[length()-j];
        for(int i = 0; i < u.length; i++)
            u[i] = coeffs[i+j];
        return new Numbers(u);
    }


    // random real
    public static Numbers rrand(int u, double r){
        double[] out = new double[u];
        for(int i = 0; i < out.length; i++)
            out[i] = Math.random()*r*2-r;
        return new Numbers(out);
    }

    public String toString(){
        String s = "";
        for(int i = length()-1; i >1 ; i--){
            s += "( " + coeffs[i] + " )x^" + i + " + ";
        }
        if(length() > 0)
            s += "( " + coeffs[1] + " )x ";
        s += coeffs[0];
        return s;
    }
}