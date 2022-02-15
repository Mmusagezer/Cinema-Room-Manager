package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        int[][] boughtSeats;
        int numberOfSoldSeats = 0;
        boughtSeats = new int[rows][cols];
        int userInput = -1;


        while(userInput != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    showTheSeats(boughtSeats);
                    break;
                case 2:
                    buyATicket(boughtSeats);
                    numberOfSoldSeats += 1;
                    break;
                case 3:
                    stats(numberOfSoldSeats, boughtSeats);
                case 0:
                    break;
            }
        }
    }

    public static int[] revenueCalculator(int [][] seats) {
        int rowNumber = seats.length;
        int colNumber = seats[0].length;
        int currentRevenue = 0;
        int totalRevenue = (rowNumber * colNumber < 60 ? rowNumber * colNumber * 10 : (rowNumber / 2) * colNumber * 10 + colNumber * (rowNumber / 2 + 1) * 8);

        if (rowNumber * colNumber <=60) {
            for (int i = 0; i < seats.length; i++){
                for (int j = 0; j < seats[i].length; j++){
                    if (seats[i][j] == 1) {
                        currentRevenue += 10;
                    }
                }
            }
        }
        else if (rowNumber * colNumber > 60) {
            for(int i = 0; i < seats.length; i++){
                for(int j = 0; j < seats[i].length; j++){
                    if (seats[i][j] == 1 && i <= rowNumber/2 - 1) {
                        currentRevenue += 10;
                    } else if (seats[i][j] == 1 && i > rowNumber/2 - 1) {
                        currentRevenue += 8;
                    }
                }
            }
        }
        return new int[] {totalRevenue, currentRevenue};
    }


    public static void buyATicket(int [][] boughtSeats){
        Scanner scanner = new Scanner(System.in);
        int rown;
        int coln;
        int rows = boughtSeats.length;
        int cols = boughtSeats[0].length;
        while(true) {
            System.out.println("Enter a row number: ");
            rown = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            coln = scanner.nextInt();

            System.out.println();
            if (rown > rows || coln > cols) {
                System.out.println("Wrong input!");
                continue;
            }
            else if (boughtSeats[rown-1][coln-1] == 1) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            break;
        }
        if(rows*cols <= 60) {
            System.out.println("Ticket price: $10");

        } else {
            if (rown <= (rows/2)) {
                System.out.println("Ticket price: $10");
            } else System.out.println("Ticket price: $8");
        }
        System.out.println();
        boughtSeats[rown-1][coln-1] = 1;
    }
    public static void showTheSeats(int [][] seatArray) {
        int cols = seatArray[0].length;
        int rows = seatArray.length;
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i =1; i <= cols; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= cols; j++) {
                if (seatArray[i-1][j-1] == 1){
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
    public static void stats(int numberOfSoldSeats, int [][] soldSeats) {
        int rows = soldSeats.length;
        int cols = soldSeats[0].length;

        System.out.printf("Number of purchased tickets: %d%n", numberOfSoldSeats);
        System.out.printf("Percentage: %.2f%%%n", ((double)numberOfSoldSeats / (double)(rows*cols))* 100.0);
        System.out.printf("Current income: $%d%n", revenueCalculator(soldSeats)[1]);
        System.out.print("Total income: $");
        System.out.println(revenueCalculator(soldSeats)[0]);

    }
}
