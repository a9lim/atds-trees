import Util.Polyn;

public class Main {
    public static void main(String[] args){
        double[] fleemius = {-6,14,-32,28,-24};
        double[] u = {3,-4,6};

        Polyn fleebius = new Polyn(fleemius);
        Polyn uu = new Polyn(u);
        System.out.println(Polyn.quot(fleebius,uu));
    }
}
