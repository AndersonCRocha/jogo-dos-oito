package io.github.andersoncrocha.models;

import io.github.andersoncrocha.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private final int boardSize;
    private List<Part> parts;
    private Part blankPart;

    public Board(int size) {
        this.boardSize = size;
        this.prepareBoard();
    }

    private void prepareBoard() {
        this.parts = IntStream.range(1, boardSize * boardSize).mapToObj(Part::new).collect(Collectors.toList());

        Part blankPart = new Part();
        this.parts.add(blankPart);

        this.blankPart = blankPart;

        this.updateNeighbors();
        this.shuffle();
    }

    private void updateNeighbors() {
        this.parts.forEach(part -> {
            int partIndex = this.parts.indexOf(part);
            part.setIndex(partIndex);

            boolean hasLeftNeighbor = partIndex % boardSize > 0;
            boolean hasRightNeighbor = partIndex % boardSize < boardSize - 1;
            boolean hasUpstairsNeighbor = partIndex - boardSize >= 0;
            boolean hasDownstairsNeighbor = partIndex + boardSize < boardSize * boardSize;

            Part leftNeighbor = hasLeftNeighbor ? this.parts.get(partIndex - 1) : null;
            Part rightNeighbor = hasRightNeighbor ? this.parts.get(partIndex + 1) : null;
            Part upstairsNeighbor = hasUpstairsNeighbor ? this.parts.get(partIndex - boardSize) : null;
            Part downstairsNeighbor = hasDownstairsNeighbor ? this.parts.get(partIndex + boardSize) : null;

            List<Part> neighbors = Stream.of(leftNeighbor, rightNeighbor, upstairsNeighbor, downstairsNeighbor)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            part.setNeighbors(neighbors);
        });
    }

    private void shuffle() {
        IntStream.range(0, 1000).forEach(x -> {
            List<Integer> allowedNumbersToMovements = this.getAllowedNumbersToMovements();
            Integer numberToMove = CollectionUtils.getRandomValue(allowedNumbersToMovements);
            this.move(numberToMove);
        });
    }

    public List<List<Part>> getPartsAsBoardRows() {
        return IntStream.iterate(0, index -> index < this.parts.size(), index -> index + boardSize)
                .mapToObj(index -> this.parts.subList(index, index + boardSize))
                .collect(Collectors.toList());
    }

    public List<Integer> getAllowedNumbersToMovements() {
        return this.blankPart.getNeighbors().stream()
                .map(Part::getValue)
                .collect(Collectors.toList());
    }

    public boolean move(int numberToMove) {
        return this.blankPart.getNeighbors().stream()
                .filter(part -> part.valuesMatch(numberToMove))
                .findFirst()
                .map(partToMove -> {
                    int indexOfPartToMove = this.parts.indexOf(partToMove);
                    Collections.swap(this.parts, indexOfPartToMove, this.blankPart.getIndex());
                    this.updateNeighbors();
                    return true;
                }).orElse(false);
    }

    public boolean checkBoard() {
        return this.parts.stream()
                .filter(Part::isNotBlank)
                .allMatch(part -> part.getIndex() + 1 == part.getValue());
    }

}
