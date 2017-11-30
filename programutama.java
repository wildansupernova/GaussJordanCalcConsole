import java.io.*;
import java.util.Scanner;
public class programutama {

    public static void prinMenuUtama(){
      matriks m = new matriks();
        m.prinstrn("Pilih pilihan anda : ");
        m.prinstrn("1. Sistem Persamaan Linear");
        m.prinstrn("2. Hilbert");
        m.prinstrn("3. Interpolasi Polinomial Titik");
        m.prinstrn("4. Interpolasi Pendekatan Fungsi");
        m.prinstrn("5. Exit");
    }
    public static void prinMenuSPL(){
      matriks m = new matriks();
      m.prinstrn("Input Matriks");
      m.prinstrn("1. Input Keyboard ");
      m.prinstrn("2. Input File");
    }
    public static double[][] keluaranbaru(double arr [][]){
        double[][] b = new double[arr.length][arr[1].length];
        b =arr;
        return b;
    }

    public static void copymatriks(double arr1[][],double arr2[][]){
        int jbaris = arr2.length-1;
        int jkolom = arr2[1].length-1;
        for(int i=1;i<=jbaris;i++){
            for(int j =1 ;j<=jkolom;j++){
                arr1[i][j]= arr2[i][j];
            }
        }
    }
    public static void main(String []args) {
        double[][] arsip;
        double[][] augtab;
        double[][] hasil;
        boolean flag_singular;
        int pilarsip;
        matriks m = new matriks();
        Scanner scan = new Scanner(System.in); // Sistem Input dengan Scanner
        //Initializing Judul
        String hasil1 = " _____                       _____                        _____  _   _       _                _____                        _         _ \n|_   _|_ _  ___  ___  ___   | __  | ___  ___  ___  ___   |  _  || | |_| ___ | |_  ___  ___   |   __| ___  ___  _____  ___ | |_  ___ |_|\n  | | | | || . || .'||_ -|  | __ -|| -_||_ -|| .'||  _|  |     || | | || .'|| . || .'||  _|  |  |  || -_|| . ||     || -_||  _||  _|| |\n  |_| |___||_  ||__,||___|  |_____||___||___||__,||_|    |__|__||_|_| ||__,||___||__,||_|    |_____||___||___||_|_|_||___||_|  |_|  |_|\n           |___|                                                  |___|                                                                \n\n";
        m.prinstrn(hasil1);
        //Initializing anggota kelompok
        hasil1="by\n-Wildan Dicky Alnatara - 13516012\n-Luthfi Ahmad Mujahid Hadiana -13516051\n-Raka Hadhyana - 13516099\n///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n";
        m.prinstrn(hasil1);

        int pilihan;
        do{
            prinMenuUtama();
            m.prinstr("Masukkan Input : ");
            pilihan = scan.nextInt();
        } while(pilihan>5 && pilihan <= 0);

        switch(pilihan){

            case 1 :
            int pilspl;
            do{
                prinMenuSPL();
                m.prinstr("Masukkan Input : ");
                pilspl = scan.nextInt();
            } while(pilspl>2 && pilspl <=0);
            if (pilspl == 1){
              int x,n;
              m.prinstr("Berapa baris : ");
              x = scan.nextInt();
              m.prinstr("Berapa kolom : ");
              n = scan.nextInt();
              m.prinstr("Masukkan Matriks augmentednya \n");
              int masukanbaris=x;
              if (x<n-1){
                x=n-1;
              }
              augtab = new double [x+1][n+1];
              arsip = new double [x+1][n+1];
              //////////////////Nerima Input dari keyboard
              for(int i=1;i<=masukanbaris;i++){
                    for(int j =1;j<=n;j++){
                      augtab[i][j] = scan.nextDouble();
                    }
              }
              copymatriks(arsip, augtab);
            }
            else {
              augtab = m.readMatriksbyFile("input.txt");
                arsip = m.readMatriksbyFile("input.txt");
            }
            

            flag_singular = m.eliminasigauss(augtab); //Kalo Matriksnya singular nanti hasilnya true
            m.prinstr(" eliminasi :  \n");
            m.tulismatriks(augtab);
            m.jordan(augtab);
            m.prinstr(" jordan :  \n");
            m.tulismatriks(augtab);
            if (flag_singular){
                if (m.isInfiniteSolution(augtab) ){
                      m.prinstr("Solusi Banyak\n");
                      m.prinparameter(augtab);
                }
                else  {
                      m.prinstr("Gak ada solusi\n");
                }
            }
            else{
                for (int i=1 ; i<= augtab.length-1 ;i++){
                  m.prinstr("solusi X"+i+" = " + String.format("%.3f",augtab[i][augtab[1].length-1])+"\n");
                }
            }
            do{
                m.prinstr("Apakah jawaban ingin di arsip ?\n");
                m.prinstr("1. Yes \n");
                m.prinstr("2. No \n");
                m.prinstr("Masukkan Input : ");
                pilarsip = scan.nextInt();
            } while(pilspl>2 && pilspl <= 0);
            if (pilarsip == 1){
              String filename;
              m.prinstr("Nama File : ");
              filename = scan.next();
              m.writeArsip(arsip,filename);
            }
                        break;
            case 2 :
            int nHilbert;
            do{
                m.prinstr("Masukkan Input n : \n");
                nHilbert = scan.nextInt();
            } while(nHilbert<0);
            augtab = m.buatMatriksHilbert(nHilbert);

            arsip = m.buatMatriksHilbert(nHilbert);
            m.prinstr(" Matriks Hilbert :  \n");
            m.tulismatriks(augtab);
            flag_singular = m.eliminasigauss(augtab); //Kalo Matriksnya singular nanti hasilnya true
            m.prinstr(" eliminasi :  \n");
            m.tulismatriks(augtab);
            m.jordan(augtab);
            m.prinstr(" jordan :  \n");
            m.tulismatriks(augtab);
            if (flag_singular){
                if (m.isInfiniteSolution(augtab) ){
                      m.prinstr("Solusi Banyak\n");
                      m.prinparameter(augtab);
                }
                else  {
                      m.prinstr("Gak ada solusi\n");
                }
            }
            else{
              for (int i=1 ; i<=augtab.length-1;i++){
                m.prinstr("solusi X"+i+" = " + String.format("%.3f",augtab[i][augtab[1].length-1])+"\n");
                }
            }
            do{
                m.prinstr("Apakah jawaban ingin di arsip ?\n");
                m.prinstr("1. Yes \n");
                m.prinstr("2. No \n");
                m.prinstr("Masukkan Input : ");
                pilarsip = scan.nextInt();
            } while(pilarsip>2 && pilarsip <= 0);
            if (pilarsip == 1){
              String filename;
              m.prinstr("Nama File : \n");
              filename = scan.next();
              m.writeArsip(arsip,filename);
            }
                        break;
            case 3 :
            hasil = m.bacaInputXY();
            
            augtab = m.buatMatriksInterpolasi(hasil);
            arsip = m.buatMatriksInterpolasi(hasil);
            m.prinstr(" Matriks Interpolasi :  \n");
            m.tulismatriks(augtab);           
            flag_singular = m.eliminasigauss(augtab); //Kalo Matriksnya singular nanti hasilnya true
            m.prinstr(" eliminasi :  \n");
            m.tulismatriks(augtab);
            m.jordan(augtab);
            m.prinstr(" jordan :  \n");
            m.tulismatriks(augtab);
            m.prinstrn(m.solusiInterpolasiToStringParamChange(augtab));
            m.prinstrn("Mau nyoba berapa kali masukin x ke hasil persamaannya? ");
            int coba;
            coba = scan.nextInt();
            for(int i=1;i<=coba;i++){
                double xx;
                xx = scan.nextDouble();
                m.prinstrn(String.format("%.3f",m.hasilfungsiinterpolasi(augtab, xx)) );
            }

                        break;
            case 4 :
                    int a,b,n;
                    m.prinstrn("Masukkan a b n nya");
                    a = scan.nextInt();
                    b = scan.nextInt();
                    n = scan.nextInt();
                    double[][] hasiltitik = m.hasiltitik2(a, b, n);
                    m.prinstrn("Hasil titik-titiknya");
                    m.tulismatriks(hasiltitik);
                    double[][]  matriksfungsi = m.buatMatriksInterpolasi(hasiltitik);
                    m.prinstrn("Hasil interpolasi titik-titiknya");
                    m.tulismatriks(matriksfungsi);
                    m.prinstrn("Setelah Gaus Jordan");
                    m.eliminasigauss(matriksfungsi);
                    m.jordan(matriksfungsi);
                    m.tulismatriks(matriksfungsi);
                    m.prinstrn("Fungsi Interpolasinya");
                    m.prinstrn(m.solusiInterpolasiToStringParamChange(matriksfungsi));
                    m.prinstrn("Berapa kali mau test Xnya");
                    n = scan.nextInt();
                    for(int i=1 ;i<=n;i++){
                        double masukan;
                        masukan = scan.nextDouble();
                        m.prinstrn("hasilnya : " + String.format("%.3f",m.hasilfungsiinterpolasi(matriksfungsi,masukan)));
                    }                    
                    
                    break;
            default:
                        break;
        }

    }
}
