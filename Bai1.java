 import java.util.Scanner;
 import java.util.Random;
 import java.lang.Math;

 public class Bai1 {
     public static double[][] randompoint(double r, int numpoint) {
         double min = -r;
         double max = r;
         Random random = new Random();
         
         double[][] points = new double[numpoint][2];
         
         for(int i=0; i<numpoint; i++) {
             double x = min + (max - min)*random.nextDouble();
             double y = min + (max - min)*random.nextDouble();
             points[i][0] = x;
             points[i][1] = y;
         }
         return points;
     }
     public static boolean checking(double x, double y, double r) {
         if(Math.pow(x,2) + Math.pow(y, 2) <= Math.pow(r,2))
             return true;
         return false;
     }
     
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         System.out.println("Nhap ban kinh r: ");
         double r = scanner.nextDouble();
         int numpoint = 1000000;
         int inCircle = 0;
         double[][] points = randompoint(r,numpoint);
         for(int i=0; i<points.length; i++) {
             if(checking(points[i][0],points[i][1],r))
                 inCircle++;
         }
         double S = (1.0*inCircle/numpoint)*Math.pow(2*r,2);
         System.out.println("Dien tich hinh tron nay la: " + S);
     }
 }
 