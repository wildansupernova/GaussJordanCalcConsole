import java.io.*;
import java.util.Scanner;
public class matriks {
        public static double[][] readMatriksbyFile(String fileName ){
            // The name of the file to open.
                // This will reference one line at a time
                String line = null;
                char tabChar[] ;
                int iMatriks=0;
                double[][] arr = new double[30][30];
                int jMatriks=0;
                String temp;
                int i , j ;
                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);
                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    iMatriks = 0;
                    jMatriks = 0;
                    while((line = bufferedReader.readLine()) != null) {
                        iMatriks ++;
                        jMatriks = 0;;
                        i = 0;
                        temp = "";
                        tabChar = line.toCharArray();
                        while (i < line.length()){
                           if (tabChar[i]!=' '){
                               temp += Character.toString(tabChar[i]);
                           }
                           else{
                               jMatriks ++;
                               arr[iMatriks][jMatriks] = Double.parseDouble(temp);
                               temp = "";
                           }
                           i++;
                        }
                        jMatriks++;
                        arr[iMatriks][jMatriks] = Double.parseDouble(temp);
                    }
                    // Always close files.
                    bufferedReader.close();
                }
                catch(FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                }
                catch(IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                    // Or we could just do this:
                    // ex.printStackTrace();
                }
                double[][] ars = new double [iMatriks+1][jMatriks+1];
                for(i = 1 ; i <= iMatriks ; i++){
                  for (j =1 ;j<= jMatriks ; j++ ) {
                      ars[i][j] = arr[i][j];

                  }
                }
                return ars;
        }
        public static void prinint(int a){
            System.out.print(a);
        }
        public static void prinintn(int a){
            System.out.println(a);
        }
        public static void prinstr(String a){
            System.out.print(a);
        }
        public static void prinstrn(String a){
            System.out.println(a);
        }
        public static String dobeltostr(double a){
            return String.format("%.1f",a);
        }
        public static double absolut(double x){
            if (x<0){
                return x*-1;
            } else{
                return x;
            }
        }

        public static int searchIdKolomMaks( int mulaibaris , int kolomke, double[][] arr){

            //Mencari angka terbesar suatu kolom tertentu , pencarian dimulai dari  mulaibaris ke akhir baris
            int idmaks;
            //Algoritma pencariann
            idmaks = mulaibaris;
            int jumlahbaris = arr.length -1;
            for (int i=mulaibaris;i<=jumlahbaris;i++){
                if (absolut(arr[i][kolomke])>absolut(arr[idmaks][kolomke])){
                    idmaks = i;
                }
            }

            return idmaks;

        }


        public static void tukarbaris(double arr1[], double arr2[]){
            //Melakukan penggantian baris
            for(int i=1;i<arr1.length;i++){
                double temp = arr1[i];
                arr1[i]= arr2[i];
                arr2[i] = temp;
            }
        }
        public static void kaliBarisDenganK(double arr[],double k){
            //Mengkali suatu row dengan konstanta bilangan k double
                int baris =arr.length-1;
                for(int i = 1;i<=baris;i++){
                    arr[i]*=k;
                }
        }
        public static void kurangibaris (double arr1[],double arr2[], double k ){
            //f.s : arr1= arr1-k*arr2
            int baris =arr1.length-1;
            for(int i = 1;i<=baris;i++){
                //prinstr(String.format("%.6f",arr1[i]) + " = "+ String.format("%.6f",arr1[i]) + " - "+String.format("%.6f",arr2[i])+  " \n");
                arr1[i]=arr1[i] - (k*arr2[i]);
            }

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
        /////////////////////Algoritma Gauss elimination//////////////////////
        public static boolean eliminasigauss (double arr[][]){
            //Initial State, harus berisi array nXn+1 (Augmented Matrix)
            //Final State isinya sudah tereliminasi echelon form but not reduced atau mengembalikan nilai true jika matrix adalah singular or false klo bukan
            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;
            boolean singularflag=false;
            for (int i=1 ;i<=jkolom-1;i++){
                //Melakukan operasi baris elementer serta pivoting
               for(int pass=i;pass<=jkolom-1;pass++){
                        tukarbaris(arr[i],arr[searchIdKolomMaks(i,pass,arr)]); //pivoting

                        //Algoritma Proses Baris Elementer
                            if (arr[i][pass]!=0){
                                kaliBarisDenganK(arr[i],1/arr[i][pass]); //membuat angka 1 diagonal

                                for(int j =i+1;j<=jbaris;j++){
                                    //tukarbaris(arr[j],arr[searchIdKolomMaks(j,i,arr)]); //Pivoting
                                        //Algoritma Proses baris ELEMENTER
                                            //Algoritma buat angka 0

                                            kurangibaris(arr[j],arr[i],arr[j][pass]);
                                        //
                                    }
                                break;
                            } else{
                                singularflag=true;
                            }
                }
            }
           return singularflag;
        }



        //////////////////jordan
        public static void jordan(double arr[][]){


            //Tanpa Elimiasi Gauss karena fungsinya mengeliminasi segitiga atas
            //////////Algoritma mengeliminasi segitiga atas
            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;

            for(int i=jkolom-1;i>=1;i--){ //perulangan dari kolom terakhir keawal
                int letak1=0; //letak angka1 dibaris
                for(int j =i;j>=1;j--){
                    if(arr[j][i]!=0){
                        letak1=j;
                        break;
                    }
                }

                if(letak1!=0){
                    for(int j =letak1-1;j>=1;j--){ //Perulangan untuk baris
                        //tulismatriks(arr);
                        //Algoritma Proses baris ELEMENTER
                            //Algoritma buat angka 0
                            kurangibaris(arr[j],arr[letak1],arr[j][i]);
                    }
                }


            }
            makeLeadingOne(arr);

        }
        public static void makeLeadingOne(double arr[][]){
            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;

            for(int i = 1;i<=jbaris;i++){
                boolean Nol = true;
                int j;
                for( j =1;j<=jkolom-1;j++){
                    if(arr[i][j]!=0){
                        //prinstrn(i+","+j);
                        Nol=false;
                        break;
                    }
                }
                if (!Nol){
                    if(arr[i][j]!=1){
                        kaliBarisDenganK(arr[i], 1/arr[i][j]);
                    }
                }


            }
        }

        public static boolean isInfiniteSolution(double arr[][]){
            //Initial State = bahwa matrix augmented masukann adalah matrix singular
            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;
            boolean Infinite = true;
            for(int i=1;i<=jbaris;i++){
                boolean issatubarisnol = arr[i][1]==0;
                for(int j =2 ;j<=jkolom-1;j++){
                    issatubarisnol = issatubarisnol && (arr[i][j]==0);
                }
                if (issatubarisnol){
                    if(arr[i][jkolom]!=0){
                        Infinite=false;
                        break;
                    }
                }
            }
            return Infinite;
        }
        public static void prinparameter(double arr[][]){

            /// Initiatal state  = procedure ini Dipanggil jika matriks singular,bersolusi banyak, dan juga matriksnya sudah diterapkan fungsi makeLeadingOnr

            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;//termasuk  Right Handednya
            char[]  penggantian = new char [jkolom+1];
            boolean[][]  isLeadingOne = new boolean [arr.length][arr[1].length];
            boolean[]  kepakegak = new boolean [jkolom+1];

            /////////Mencari Letak Leading One untuk algoritma selanjutnya

            for(int i = 1;i<=jbaris;i++){

                int j;
                for( j =1;j<=jkolom-1;j++){
                    if(arr[i][j]==1){
                        isLeadingOne[i][j]=true;
                        break;
                    }
                }
            }


            ///Scan Substitusi ada 1 tidak;
            int countersimbol=0;
                for(int j = 1;j<=jkolom-1;j++){
                    boolean isnoany1= true;
                    for(int i = 1 ; i<=jbaris;i++){
                        if(isLeadingOne[i][j]==true){
                            isnoany1 =false;
                            break;
                        }
                    }

                    if(isnoany1){
                        countersimbol++;
                        int temp = 111+countersimbol;
                        penggantian[j]=(char) temp;
                        kepakegak [j]= true;
                    }
                }
            ///

            //Print Permisalan Atau Substituen
            prinstrn("+++++Permisalan Huruf++++++++");
            for(int i=1;i<=jkolom-1;i++){
                if (kepakegak[i]){
                    prinstrn("x" + i+" = "+ penggantian[i]);
                }
            }
            prinstrn("++++++++Persamaan Parameternya+++++++");
            //Sekarang prin parameternya

            for(int i =1 ;i<=jbaris;i++){
                //Cari dulu angka 1 nya dikolom mana baris ke-i kalo gak ada gak jadi prin
                int idx1 = 0;
                for(int j = i;j<=jkolom-1;j++){
                    if (arr[i][j]==1){
                        idx1=j;
                        break;
                    }
                }
                if (idx1!=0){ //Kalo Ketemu angka satunya
                    prinstr("x"+idx1+" = ");
                    boolean isSemuaNol = true;
                    for(int j = idx1+1;j<=jkolom-1;j++){
                      if(arr[i][j]!=0){
                            if(arr[i][j]==1){
                                prinstr(" - "+"("+ penggantian[j]+")");
                            } else{
                                prinstr(" - "+"("+dobeltostr(arr[i][j])+ penggantian[j]+")");
                            }
                            isSemuaNol=false;
                      }
                    }

                    //Prin konstantan

                        if (isSemuaNol){

                            prinstr(dobeltostr (arr[i][jkolom])+"\n");
                        }else{
                            if(arr[i][jkolom]!=0){
                                prinstr(" + " +dobeltostr (arr[i][jkolom])+"\n");
                            } else{
                                prinstr("\n");
                            }
                        }



                }
            }
        }
        public static String parameterToString(double arr[][]){

                        /// Initiatal state  = procedure ini Dipanggil jika matriks singular,bersolusi banyak, dan juga matriksnya sudah diterapkan fungsi makeLeadingOnr

                        int jbaris = arr.length-1;
                        int jkolom = arr[1].length-1;//termasuk  Right Handednya
                        char[]  penggantian = new char [jkolom+1];
                        boolean[][]  isLeadingOne = new boolean [arr.length][arr[1].length];
                        boolean[]  kepakegak = new boolean [jkolom+1];
                        String hasil="";
                        /////////Mencari Letak Leading One untuk algoritma selanjutnya

                        for(int i = 1;i<=jbaris;i++){

                            int j;
                            for( j =1;j<=jkolom-1;j++){
                                if(arr[i][j]==1){
                                    isLeadingOne[i][j]=true;
                                    break;
                                }
                            }
                        }


                        ///Scan Substitusi ada 1 tidak;
                        int countersimbol=0;
                            for(int j = 1;j<=jkolom-1;j++){
                                boolean isnoany1= true;
                                for(int i = 1 ; i<=jbaris;i++){
                                    if(isLeadingOne[i][j]==true){
                                        isnoany1 =false;
                                        break;
                                    }
                                }

                                if(isnoany1){
                                    countersimbol++;
                                    int temp = 111+countersimbol;
                                    penggantian[j]=(char) temp;
                                    kepakegak [j]= true;
                                }
                            }
                        ///

                        //Print Permisalan Atau Substituen
                        //prinstrn("+++++Permisalan Huruf++++++++");
                        hasil = hasil +"+++++Permisalan Huruf++++++++\n" ;
                        for(int i=1;i<=jkolom-1;i++){
                            if (kepakegak[i]){
                                //prinstrn("x" + i+" = "+ penggantian[i]);
                                hasil = hasil+ "x" + i+" = "+ penggantian[i] + "\n" ;
                            }
                        }
                        //prinstrn("++++++++Persamaan Parameternya+++++++");
                        hasil = hasil + "++++++++Persamaan Parameternya+++++++\n";
                        //Sekarang prin parameternya

                        for(int i =1 ;i<=jbaris;i++){
                            //Cari dulu angka 1 nya dikolom mana baris ke-i kalo gak ada gak jadi prin
                            int idx1 = 0;
                            for(int j = i;j<=jkolom-1;j++){
                                if (arr[i][j]==1){
                                    idx1=j;
                                    break;
                                }
                            }
                            if (idx1!=0){ //Kalo Ketemu angka satunya
                                //prinstr("x"+idx1+" = ");
                                hasil = hasil + "x"+idx1+" = ";
                                boolean isSemuaNol = true;
                                for(int j = idx1+1;j<=jkolom-1;j++){
                                  if(arr[i][j]!=0){
                                        if(arr[i][j]==1){
                                            //prinstr(" - "+"("+ penggantian[j]+")");
                                            hasil = hasil + " - "+"("+ penggantian[j]+")";
                                        } else{
                                            //prinstr(" - "+"("+dobeltostr(arr[i][j])+ penggantian[j]+")");
                                            hasil = hasil + " - "+"("+dobeltostr(arr[i][j])+ penggantian[j]+")";
                                        }
                                        isSemuaNol=false;
                                  }
                                }

                                //Prin konstantan

                                    if (isSemuaNol){

                                        //prinstr(dobeltostr (arr[i][jkolom])+"\n");
                                        hasil = hasil + dobeltostr (arr[i][jkolom])+"\n";
                                    }else{
                                        if(arr[i][jkolom]!=0){
                                            //prinstr(" + " +dobeltostr (arr[i][jkolom])+"\n");
                                            hasil = hasil +" + " +dobeltostr (arr[i][jkolom])+"\n" ;
                                        } else{
                                            //prinstr("\n");
                                            hasil = hasil + "\n";
                                        }
                                    }



                            }
                        }
                        return hasil;
                    }
        public static void writeArsip(double arr[][] , String filename){
            int i , j;
            int jbaris = arr.length-1;
            int jkolom = arr[1].length-1;
            BufferedWriter bufferedWriter = null;
            try{
            //create FileOutputStream object

            Writer writer = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(writer);
            //System.out.println(Double.toString(d));
            bufferedWriter.write("++++++++++ Matriks Awal ++++++++++\n");
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",arr[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",arr[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Matriks Eliminasi Gauss ++++++++++\n");
            boolean flag_singular = eliminasigauss(arr);
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",arr[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",arr[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Matriks Eliminasi Gauss Jordan ++++++++++\n");
            jordan(arr);
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",arr[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",arr[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Hasil ++++++++++\n");
            if (flag_singular){
                if (isInfiniteSolution(arr) ){
                    bufferedWriter.write("Solusi Banyak\n");
                    bufferedWriter.write(parameterToString(arr));
                } else  {
                    bufferedWriter.write("Gak ada solusi\n");
                }
            } else{
                for (i=1 ; i<=jbaris;i++){
                    bufferedWriter.write("solusi X"+i+" = " + String.format("%.3f",arr[i][jkolom])+"\n");
                }
            }
            bufferedWriter.close();
            }
            catch (IOException e){
            System.out.println("IOException : " + e);
            }
        }

        public static double[][] buatMatriksHilbert(int n){
      		//Fungsi ini akan menerima masukan sebuah dan akan mengeluarkan matriks hilber nXn+1
      		int i, j;
      		double M[][] = new double[n+1][n+2];
      		for(i = 1;i <= n;i++){
      			for(j = 1;j <= n;j++){
      				M[i][j] = 1.00 /(i+j-1);
      			}
      			M[i][n+1] = 1;
      		}
          return M;
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
        public static void writeArsipinter(double arr[][] , String filename){
            int i , j;
            int jb = arr.length-1;
            int jk = arr[1].length-1;
            BufferedWriter bufferedWriter = null;
            try{
            //create FileOutputStream object

            Writer writer = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(writer);
            //System.out.println(Double.toString(d));
            bufferedWriter.write("++++++++++ Titik ++++++++++\n");
            for (i = 1 ; i<= jb ; i++)
            {
                for (j = 1 ; j<= jk ; j++)
                {
                    if (j != jk){
                        bufferedWriter.write(String.format("%.3f",arr[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",arr[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Matriks Interpolasi ++++++++++\n");
            double[][]  matriksInterpolasi = buatMatriksInterpolasi(arr);
            int jbaris = matriksInterpolasi.length-1;
            int jkolom = matriksInterpolasi[1].length-1;
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Matriks Eliminasi Gauss ++++++++++\n");
            boolean flag_singular = eliminasigauss(matriksInterpolasi);
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Matriks Eliminasi Gauss Jordan ++++++++++\n");
            jordan(matriksInterpolasi);
            for (i = 1 ; i<= jbaris; i++)
            {
                for (j = 1 ; j<= jkolom ; j++)
                {
                    if (j != jkolom){
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j])+" ");
                    }
                    else{
                        bufferedWriter.write(String.format("%.3f",matriksInterpolasi[i][j]));
                    }

                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.write("++++++++++ Hasil ++++++++++\n");
            bufferedWriter.write(solusiInterpolasiToStringParamChange(matriksInterpolasi));
            bufferedWriter.close();
            }
            catch (IOException e){
            System.out.println("IOException : " + e);
            }
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
         static double[][] keluaranbaru(double arr [][]){
            double[][] b = new double[arr.length][arr[1].length];
            b =arr;
            return b;
        }
        public static void main(String []args) {
          //Scanner scan = new Scanner(System.in);
          int m,n;
          /*prinstr("Berapa baris : ");
          m = scan.nextInt();
          prinstr("Berapa kolom : ");
          n = scan.nextInt();
          prinstr("Masukkan Matriks augmentednya \n");
          if (m<n-1){
            m=n-1;
          }*/
          double[][] augtab = bacaInputXY();
          //System.out.println(augtab.length);
          //////////////////Nerima Input dari keyboard
          /*for(int i=1;i<=m;i++){
                for(int j =1;j<=n;j++){
                  augtab[i][j] = scan.nextDouble();
                }
          }*/
          writeArsipinter(augtab,"interpolasi.txt");
          /////////////////////////

          /*boolean flag_singular = eliminasigauss(augtab); //Kalo Matriksnya singular nanti hasilnya true
          prinstr("hasil scannya eliminasi :  \n");
          tulismatriks(augtab);
          jordan(augtab);
          prinstr("hasil scannya jordan :  \n");
          tulismatriks(augtab);
          if (flag_singular){
                if (isInfiniteSolution(augtab) ){
                        prinstr("Solusi Banyak\n");
                        prinparameter(augtab);
                } else  {
                    prinstr("Gak ada solusi\n");
                }
        } else{


            for (int i=1 ; i<=m;i++){
              prinstr("solusi X"+i+" = " + String.format("%.3f",augtab[i][n])+"\n");
            }
        }*/
       }
    }
    