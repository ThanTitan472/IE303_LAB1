import java.util.Scanner;

public class Bai3 {
    public static boolean flag_final = true;
    public static double calculate_cos(int x_prev, int y_prev, int x, int y) {
        double top = x - x_prev;
        double bot = Math.sqrt((x - x_prev) * (x - x_prev) + (y - y_prev) * (y - y_prev));
        return top / bot;
    }
    public static double calculate_sin(int x_prev, int y_prev, int x, int y) {
        double top = y - y_prev;
        double bot = Math.sqrt((x - x_prev) * (x - x_prev) + (y - y_prev) * (y - y_prev));
        return top / bot;
    }
    public static int[] nextselectpoint(int x_prev, int y_prev, int[][] points, int numpoint) {
        int[] select_point = new int[2];
        double[] cos = new double[numpoint];
        int[] sin_check = new int[numpoint];
        int top = 0;
        int bot = 0;
        for(int i=0; i<numpoint; i++) {
            cos[i] = calculate_cos(x_prev, y_prev, points[i][0], points[i][1]);
            if(calculate_sin(x_prev, y_prev, points[i][0], points[i][1]) >= 0) {
                sin_check[i] = 1;
                top++;
            }
            else {
                sin_check[i] = -1;
                bot++;
            }
        }
        if (top == 0)
            flag_final = false;
        if (bot == 1)
            flag_final = true;
        double example_cos = flag_final ? -1.0 : 1.0;
        for(int i=0; i<numpoint; i++) {
            if(flag_final && (sin_check[i] == 1)) {
                if (cos[i] > example_cos) {
                    example_cos = cos[i];
                    select_point[0] = points[i][0];
                    select_point[1] = points[i][1];
                } else if (cos[i] == example_cos) {
                    int dist1 = (points[i][0] - x_prev) * (points[i][0] - x_prev) + (points[i][1] - y_prev) * (points[i][1] - y_prev);
                    int dist2 = (select_point[0] - x_prev) * (select_point[0] - x_prev) + (select_point[1] - y_prev) * (select_point[1] - y_prev);
                    if (dist1 > dist2) {
                        select_point[0] = points[i][0];
                        select_point[1] = points[i][1];
                    }
                }
            } else if(!flag_final && (sin_check[i] == -1)) {
                if (cos[i] < example_cos) {
                    example_cos = cos[i];
                    select_point[0] = points[i][0];
                    select_point[1] = points[i][1];
                } else if (cos[i] == example_cos) {
                    int dist1 = (points[i][0] - x_prev) * (points[i][0] - x_prev) + (points[i][1] - y_prev) * (points[i][1] - y_prev);
                    int dist2 = (select_point[0] - x_prev) * (select_point[0] - x_prev) + (select_point[1] - y_prev) * (select_point[1] - y_prev);
                    if (dist1 > dist2) {
                        select_point[0] = points[i][0];
                        select_point[1] = points[i][1];
                    }
                }
            }
        }
        return select_point;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong diem: ");
        int numpoint = scanner.nextInt();
        int[][] points = new int[numpoint][2];

        System.out.println("Nhap toa do cac diem: ");
        int ymin = Integer.MAX_VALUE;
        int x_ymin = Integer.MIN_VALUE;

        for (int i = 0; i < numpoint; i++) {
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();

            if (points[i][1] < ymin || (points[i][1] == ymin && points[i][0] > x_ymin)) {
                ymin = points[i][1];
                x_ymin = points[i][0];
            }
        }

        scanner.close();
        if (numpoint == 0) {
            System.out.println("Khong co diem nao");
            return;
        }
        if (numpoint <= 3) {
            System.out.println("Ket qua: ");
            for (int i = 0; i < numpoint; i++) {
                System.out.println(points[i][0] + " " + points[i][1]);
            }
            return;
        }
        int[][] selectpoints = new int[numpoint][2];
        int k = 0;
        selectpoints[k][0] = x_ymin;
        selectpoints[k][1] = ymin;
        k++;

        while (true) {
            int[] select = nextselectpoint(selectpoints[k - 1][0], selectpoints[k - 1][1], points, numpoint);
            if (select[0] == x_ymin && select[1] == ymin)
                break;
            selectpoints[k][0] = select[0];
            selectpoints[k][1] = select[1];
            k++;
        }

        System.out.println("Ket qua: ");
        for (int i = 0; i < k; i++) {
            System.out.println(selectpoints[i][0] + " " + selectpoints[i][1]);
        }
    }
}
