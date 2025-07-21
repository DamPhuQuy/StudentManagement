package com.mycompany.app.utilities.animation;

public class Effect {

    public static void dotEffect(String message) throws InterruptedException {
        System.out.print(message);

        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }

        System.out.println(" ✔");
    }

    public static void spinner(String message) {
        String[] spinner = { "|", "/", "-", "\\" };

        System.out.print(message + " ");
        try {
            for (int i = 0; i < 15; i++) {
                System.out.print("\b" + spinner[i % spinner.length]);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }

        System.out.println("\b✔");
    }

    public static void progressBar() {
        System.out.print("In progress: [");

        try {
            for (int i = 0; i <= 20; i++) {
                System.out.print("#");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }

        System.out.println("] Done!");
    }
}
