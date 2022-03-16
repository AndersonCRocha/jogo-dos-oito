package io.github.andersoncrocha.business;

import io.github.andersoncrocha.models.Board;

import java.util.Scanner;

public class EightPuzzle {

    private final Board board;

    public EightPuzzle() {
        this.board = new Board(3);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean playerWon = false;

            while (!playerWon) {
                this.printBoard();

                System.out.printf("Qual número deseja mover %s: ", board.getAllowedNumbersToMovements());
                int numberToMove = scanner.nextInt();

                boolean isMoved = board.move(numberToMove);

                if (isMoved) {
                    playerWon = board.checkBoard();
                } else {
                    System.err.println("Número não permite ser movido. Escolha outro: ");
                }
            }

            this.printBoard();
            System.out.println("Jogo finalizado. Você venceu!!!");
        }
    }

    private void printBoard() {
        this.board.getPartsAsBoardRows().forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });
    }

}
