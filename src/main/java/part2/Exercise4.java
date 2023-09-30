package part2;


import java.util.Scanner;

public class Exercise4 {

    private char[][] image = {
            {'.', '#', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '#', '.'},
            {'.', '#', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '.', '.'}
    };
    
    private void printImage(char[][] image) {

        for (char[] row: image) {
            for (char pixel: row) {
                System.out.print(pixel);
            }
            System.out.println();
        }
    }

    private void paint(char[][] image, int x, int y, char newColor) {

        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length)
            throw new IllegalArgumentException("x must be between 0 and " + (image.length -1) +" and y must be between "+ (image[0].length -1));

        char currentColor = image[x][y];

        paintPixel(image, x, y, newColor, currentColor);
    }

    private void paintPixel (char[][] image, int x, int y, char newColor, char currentColor) {

        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length || image[x][y] != currentColor)
            return;


        image[x][y] = newColor;

        paintPixel(image, x + 1, y, newColor, currentColor);
        paintPixel(image, x - 1, y, newColor, currentColor);
        paintPixel(image, x, y + 1, newColor, currentColor);
        paintPixel(image, x, y - 1, newColor, currentColor);
    }

    

    public static void main(String[] args) {

        Exercise4 e = new Exercise4();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter with new color ");
        String color = scanner.nextLine();

        System.out.println("Enter with X (0 - " + (e.image.length -1) +")");
        int x = scanner.nextInt();
        System.out.println("Enter with Y (0 - " + (e.image[0].length -1) +")");
        int y = scanner.nextInt();

        System.out.println("Before");
        e.printImage(e.image);
        e.paint(e.image, x, y, color.charAt(0));
        System.out.println("\n\nAfter");
        e.printImage(e.image);

        scanner.close();
    }
}
