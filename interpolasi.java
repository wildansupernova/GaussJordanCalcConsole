import java.io.*;
import java.util.Scanner;


public class interpolasi{
    //////Declare Pasangan xy
    public static void prinstrn(String a){
        System.out.println(a);
    }
    public static void prinstr(String a){
        System.out.print(a);
    }
    public static double[][] bacaInputXY (){
        //Final state dari fungsi ini adalah mengembalikan nilai pasangan array dai hasil bacaan
        Scanner scan = new Scanner(System.in);
        
        prinstr("Berapa banyak titik : ");
        int n;
        n = scan.nextInt();
        double[][]  xy = new double [n+1][3]; //Didefinisikan sebagai pasangan x,y dalam array

        //Input Masukannnya
        for(int i =1;i<=n;i++){
            for(int j=1;j<=2;j++){
                xy[i][j] = scan.nextDouble();

            }
        }

        return xy;


    }

    public static double[][] buatMatriksInterpolasi(double arr[][]){
        int jbarispoint = arr.length-1;
        int jkolompoint = arr[1].length-1;

        double[][]  hasil = new double [arr.length][arr.length+1];
        int jbarishasil = hasil.length-1;
        int jkolomhasil = hasil[1].length-1;

        for(int ip = 1;ip<=jbarispoint;ip++){
            for(int i=1;i<=jkolomhasil-1;i++){
                hasil[ip][i] = Math.pow(arr[ip][1],i-1);
            }
            hasil[ip][jkolomhasil] = arr[ip][2];
        }
        return hasil;
    }

    public static void tulismatriks(double arr[][]){
        
                    ///Menulis Matriks di layar
                    int jbaris = arr.length-1;
                    int jkolom = arr[1].length-1;
                    prinstrn("================ Phase "+ 33 +"================");
                    for(int m=1;m<=jbaris;m++){
                        for(int c =1;c<=jkolom;c++){
                           
                          prinstr(String.format("%.6f",arr[m][c])+ "    "); 
                        }
                        prinstr("\n");
                      }
                }
    public static String solusiInterpolasiToStringParamChange(double arr[][]){
        matriks m = new matriks();
        int jbaris = arr.length-1;
        int jkolom = arr[1].length-1;

        String hasil= "";
        hasil = hasil + "p(x) = ";
        boolean lewatangkapertama=false;;
        for(int i = 1;i<=jbaris;i++){
            
                if (arr[i][jkolom]!=0){
                    if(arr[i][jkolom]>0){
                        if (!lewatangkapertama){
                            if(i==1){
                                hasil = hasil+String.format("%.2f",arr[i][jkolom]);
                            } else{
                                int temp = i-1;
                                hasil = hasil+String.format("%.2f",arr[i][jkolom])+"x^"+temp;
                            }
                            
                        } else{
                            int temp = i-1;
                            hasil = hasil +" + "+ String.format("%.2f",arr[i][jkolom])+"x^"+temp;
                        }
                    } else{
                        if (!lewatangkapertama){
                            if(i==1){
                                hasil = hasil+String.format("%.2f",arr[i][jkolom]);
                            } else{
                                int temp = i-1;
                                hasil = hasil+" "+String.format("%.2f",arr[i][jkolom])+"x^"+temp;
                            }
                            
                        } else{
                            int temp = i-1;
                            hasil = hasil + String.format("%.2f",arr[i][jkolom])+"x^"+temp;
                        }
                    }
                    lewatangkapertama = true;
                }
            


            
            
        }
        return hasil;
    }

    public static double hasilFungsiE(double x){
        return Math.exp(-x)/(1+Math.pow(x,0.5)+Math.pow(x,2));
    }

    public static double[][] hasiltitik2(double a, double b,double n){
        double h = (b-a)/n;
        double temp = a;
        int counter=0;

        while(temp<b){
            temp+=h;
            counter++;
            
        }
        counter+=1;
        double[][]  xy = new double [counter+1][3];
        temp = a ;
        for(int i =1 ; i<=counter ;i++){
            if (temp>b){
                xy[i][1]=b;
                xy[i][2]=hasilFungsiE(b);
                temp+=h;
            }  else{
                xy[i][1]=temp;
                xy[i][2]=hasilFungsiE(temp);
                temp+=h;
            }
        }

        return xy;
    }

    public static double hasilfungsiinterpolasi(double arr[][], double x){
        double sum=0;
        int jbaris = arr.length-1;
        int jkolom = arr[1].length-1;
        for(int i =1 ; i<=jbaris;i++){
            sum+=arr[i][jkolom]*Math.pow(x,i-1);
        }
        return sum;
    }
    public static void main(String []args) {
        matriks m = new matriks();
        double[][] contoh = hasiltitik2(0, 5, 12);
        m.tulismatriks(contoh);
        //double[][]  hasil = bacaInputXY();
        //double[][]  matriksInterpolasi = buatMatriksInterpolasi(hasil);
        double[][]  matriksfungsi = buatMatriksInterpolasi(contoh);

        m.eliminasigauss(matriksfungsi);
        m.jordan(matriksfungsi);
        tulismatriks(matriksfungsi);
        prinstrn(solusiInterpolasiToStringParamChange(matriksfungsi));
    }
}