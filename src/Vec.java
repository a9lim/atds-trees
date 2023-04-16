

public class Vec{

    private double[] elements;

    // CONSTRUCTORS:

    // array of doublelex numbers
    public Vec(double[] c){
        elements = new double[c.length];
        for(int i = 0; i < c.length; i++)
            elements[i] = new double(c[i]);
    }

    // array of doubles
    public CVector(double[] c){
        elements = new double[c.length];
        for(int i = 0; i < c.length; i++)
            elements[i] = new double(c[i]);
    }

    // copy another vector
    public CVector(CVector p){
        elements = new double[p.length()];
        for(int i = 0; i < p.length(); i++)
            elements[i] = new double(p.term(i));
    }

    // new vector with l elements
    public CVector(int l){
        elements = double.z(l);
    }

    // use an array of doubles
    public CVector(double[] out, boolean b) {
        elements = out;
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

    // get u terms after index n
    public CVector subvec(int n, int u){
        double[] out = new double[u];
        System.arraycopy(elements, n, out, 0, u);
        return new CVector(out,true);
    }
    // subvec to the end
    public CVector subvec(int n ){
        return subvec(n,length()-n);
    }

    // MODIFIERS

    // set a vector
    public void set(CVector c){
        elements = double.z(c.length());
        for(int i = 0; i < length(); i++)
            elements[i].set( c.term(i) );
    }
    // set with offset
    public void set(CVector v, int n ){
        for(int i = 0; i < v.length(); i++)
            elements[i+n].set(v.term(i));
    }

    // BINARY OPERATIONS: C^n x C^n

    // sum
    public static CVector sum(CVector a, CVector b){
        double[] out = new double[a.length()];
        for(int i = 0; i < out.length; i++)
            out[i] = double.sum(a.term(i), b.term(i));
        return new CVector(out,true);
    }

    // difference
    public static CVector diff(CVector a, CVector b){
        double[] out = new double[a.length()];
        for(int i = 0; i < out.length; i++)
            out[i] = double.diff(a.term(i), b.term(i));
        return new CVector(out,true);
    }

    // offset inner product
    // when b.length < a.length
    // inner product is sum( a_i * b_i.conj )
    // maps vectors to a scalar

    public static double iprod(CVector a, CVector b, int n, int u){
        double sum = new double();
        for(int i = 0; i < u; i++)
            sum.sum( double.iprod(a.term(i+n), b.term(i)));
        return sum;
    }

    public static double iprod(CVector a, CVector b, int n){
        return iprod(a,b,n,b.length());
    }

    public static double iprod(CVector a, CVector b){
        return iprod(a,b,0);
    }

    //outer product
    public static Matrix oprod(CVector a, CVector b){
        CVector[] out = new CVector[b.length()];
        for(int i = 0; i < b.length(); i++)
            out[i] = prod(a,b.term(i).conj());
        return new Matrix(out,true);
    }

    // a projected in b
    // equal to b * (a*b)/|b|^2
    public static CVector proj(CVector a, CVector b){
        double o = double.quot(iprod(a,b),b.msq());
        return prod(b,o);
    }

    // NON STATIC BINARY OPERATIONS

    // +=
    public void sum(CVector b){
        for(int i = 0; i < length(); i++)
            elements[i].sum(b.term(i));
    }

    // -=
    public void diff(CVector b){
        for(int i = 0; i < length(); i++)
            elements[i].diff(b.term(i));
    }

    // C^n x C

    // vector-scalar product
    public static CVector prod(CVector a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = double.prod(a.term(i),c);
        return new CVector(out,true);
    }

    // scalar quotient
    public static CVector quot(CVector a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = double.quot(a.term(i),c);
        return new CVector(out,true);
    }

    // C^n x R

    // same as previous 2 but with doubles

    public static CVector prod(CVector a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = double.prod(a.term(i),c);
        return new CVector(out,true);
    }

    public static CVector quot(CVector a, double c ){
        double[] out = new double[a.length()];
        for(int i = 0; i < a.length();i++)
            out[i] = double.quot(a.term(i),c);
        return new CVector(out,true);
    }

    // tensor product:
    // a   c   ac
    // b x d = ad
    //         bc
    //         bd
    public static CVector tenprod(CVector a, CVector b){
        int al = a.length();
        int bl = b.length();
        double[] out = new double[al*bl];

        // using modulo and division to achieve the effect of the tensor product
        for(int i = 0; i < out.length; i++)
            out[i] = double.prod(a.term(i / bl ), b.term(i % bl ));
        return new CVector(out,true);
    }

    // NON STATIC

    // C^n x C

    // *=
    public void prod( double c ){
        for(int i = 0; i < length();i++)
            elements[i].prod(c);
    }

    // /=
    public void quot( double c ){
        for(int i = 0; i < length();i++)
            elements[i].quot(c);
    }
    // C^n x R

    // same as previous 2 but with doubles
    public void prod( double c ){
        for(int i = 0; i < length();i++)
            elements[i].prod(c);
    }

    public void quot( double c ){
        for(int i = 0; i < length();i++)
            elements[i].quot(c);
    }

    // UNARY OPERATIONS

    // conjugate of every element
    public CVector conj(){
        double[] out = new double[length()];
        for(int i = 0; i < length(); i++)
            out[i] = elements[i].conj();
        return new CVector(out,true);
    }

    // BOOLEANS

    // is every element real?
    public boolean isr(){
        for(double c: elements)
            if(!c.isr())
                return false;
        return true;
    }

    // is every element zero?
    public boolean isz(){
        for(double c: elements)
            if(!c.isz())
                return false;
        return true;
    }

    // is any element na?
    public boolean isna(){
        for(double c: elements)
            if(c.isna())
                return true;
        return false;
    }

    // is equal?
    public boolean equals(CVector a){
        if(length() != a.length())
            return false;
        for(int i = 0; i < length(); i++)
            if(!elements[i].equals(a.term(i)))
                return false;
        return true;
    }

    // TOOLS

    //testing timetaken
    public static double timetest(int its, int l, double d){
        CVector a;
        CVector b;
        long st;
        long ed;
        long avg = 0;
        for(int i = 0; i < its; i++){
            a = CVector.crand(l,d);
            b = CVector.crand(l,d);
            st = System.currentTimeMillis();
            sum(a,b);
            ed = System.currentTimeMillis();
            avg += ed - st;
        }
        return (double)avg/its;
    }


    // outer matrix of a vector with itself
    public Matrix omat(){
        double[][] out = new double[length()][length()];
        for(int i = 0; i < length(); i++)
            for(int j = 0; j < i+1; j++){
                out[i][j] = double.iprod(elements[i],elements[j]);
                if(i != j)
                    out[j][i] = new double(out[i][j]).conj();
            }
        return new Matrix(out,true);
    }

    // magnitude-squared
    // equiv to iprod(v,v)

    public double msq(int n, int u){
        double out = 0;
        for(int i = n; i < u+n;i++)
            out += elements[i].msq();
        return out;
    }
    public double msq(){
        return msq(0);
    }

    public double msq(int n){
        return msq(n,length());
    }

    // magnitude
    // equiv to sqrt(msq)
    public double mag(){
        return Math.sqrt(msq());
    }
    public double mag(int n){
        return Math.sqrt(msq(n));
    }
    public double mag(int n, int u ){
        return Math.sqrt(msq(n,u));
    }

    //hat
    public CVector hat(){
        return quot(this,mag());
    }

    // the proj of some subvector with some vector
    // condensed this.diff(prod(proj(this,h),2))
    // direct
    public void opset(CVector h, int n) {
        double c = double.dquot(iprod(this, h,n), h.msq());
        for (int i = 0; i < h.length();i++)
            // manually changing each element
            elements[i + n].diff(double.prod(h.term(i), c));
    }
    // same but conjugate-transposed
    // for use with row vectors
    public void copset(CVector h, int n) {
        double c = double.dquot(iprod(this,h,n), h.msq());
        for (int i = 0; i < h.length(); i++){
            elements[i + n].cdiff(double.prod(h.term(i), c));
        }
    }

    //2 * proj
    public static CVector dproj(CVector a, CVector b){
        double d = b.msq();
        if(d == 0)
            return new CVector(b.length());
        return prod(b,double.dquot(iprod(a,b),d));
    }

    //householder vector: the vector v - e_0 * |a| * arg(a_0)
    // by projecting into it you can unitarily arbitrarily modify matrices
    public CVector housev(){
        return housev(0);
    }
    //householder vector w/implicit shift n
    public CVector housev(int n ){
        return housev(n,length()-n);
    }
    //householder vector w/implicit shift n and length u
    public CVector housev(int n, int u ){
        double[] out = new double[u];
        double a;
        if(elements[n].isz())
            a = new double(mag(n,u));
        else
            a = double.prod(elements[n].hat(),mag(n,u));
        if(elements[n].equals(a))
            return new CVector(u);
        for(int i = 0; i < u; i++)
            out[i] = new double(elements[i+n]);
//        System.out.println(new Vector(out));
        out[0].diff(a);
        return new CVector(out,true);
    }

    // return a new vector with a_i - c
    //useful for shifting
    public CVector tdiff(int i, double c){
        CVector v = new CVector(this);
        v.term(i).diff(c);
        return v;
    }
    //tdiff with 1
    public CVector tdiff(double c){
        CVector v = new CVector(this);
        v.term(0).diff(c);
        return v;
    }

    // i-long vector with 1 at j
    public static CVector ii(int i, int j){
        CVector v = new CVector(i);
        v.term(j).set(new double(1));
        return v;
    }

    // random doublelex vector with length u and size r
    public static CVector crand(int u, double r){
        double[] out = new double[u];
        for(int i = 0; i < out.length; i++)
            out[i] = double.rand(Math.random()*r*2-r);
        return new CVector(out);
    }
    // random real vector
    public static CVector rrand(int u, double r){
        double[] out = new double[u];
        for(int i = 0; i < out.length; i++)
            out[i] = Math.random()*r*2-r;
        return new CVector(out);
    }

    public String tldr(){
        String s = "⟨ ";
        for(int i = 0; i < length()-1; i++){
            s += elements[i].tldr() + " ";
        }
        s += elements[length()-1].tldr() + " ⟩\n";
        return s;
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
