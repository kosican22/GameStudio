package sk.ness.puzzle;

import java.util.Date;
import java.util.Scanner;

public class Game {

    private static Date startTime;

    public static void game() {
        startTime = new Date();
        Scanner scanner = new Scanner(System.in);
        FieldSerialization fieldSerialization = new FieldSerialization();
        Field field = fieldSerialization.newOrRestore();
        String movement = null;
        while (!field.isSolved() && !"exit".equals(movement)) {
            System.out.println(field.toString());
            System.out.println("Tvoj cas "+calcTime());
            System.out.println("Write new, exit or");
            System.out.println("Press w,a,s,d for movement");

            movement = scanner.next();

            if (movement.equals("w") || movement.equals("up")) {
                field.moveTile(MoveDirection.UP);
            }
            if (movement.equals("a") || movement.equals("down")) {
                field.moveTile(MoveDirection.LEFT);
            }
            if (movement.equals("s") || movement.equals("left")) {
                field.moveTile(MoveDirection.DOWN);
            }
            if (movement.equals("d") || movement.equals("right")) {
                field.moveTile(MoveDirection.RIGHT);
            }
            if (movement.equals("new")){
                field = new Field(4,4);
            }
            if (movement.equals("exit")){
                fieldSerialization.save(field);
            }
        }
        if (field.isSolved()) {
            long calculatedTime = calcTime();
            System.out.println("Uspesne si dokoncil za "+ calculatedTime +" sekund. Zadaj svoje meno do tabulky slavy");
            String meno = scanner.next();
            Winner winner = new Winner(meno, calculatedTime);
            WinnerDao winnerDao = null;
            try {
                winnerDao = new WinnerDao();
                winnerDao.insert(winner);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private static long calcTime() {
        Date currentTime = new Date();
        long timeInMillis = currentTime.getTime() - startTime.getTime();
        return timeInMillis / 1000;
    }
}
