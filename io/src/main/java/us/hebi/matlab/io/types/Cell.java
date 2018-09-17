package us.hebi.matlab.io.types;

/**
 * Represents MATLAB's Cell array, e.g., 'x = cell(2,2,2)'
 * Behavior:
 * - Contains other Arrays
 * - Can be N-dimensional
 *
 * @author Florian Enner < florian @ hebirobotics.com >
 * @since 06 Sep 2018
 */
public interface Cell extends Array {

    Matrix getMatrix(int index);

    Matrix getMatrix(int row, int col);

    Matrix getMatrix(int[] indices);

    Sparse getSparse(int index);

    Sparse getSparse(int row, int col);

    Sparse getSparse(int[] indices);

    Char getChar(int index);

    Char getChar(int row, int col);

    Char getChar(int[] indices);

    Struct getStruct(int index);

    Struct getStruct(int row, int col);

    Struct getStruct(int[] indices);

    Cell getCell(int index);

    Cell getCell(int row, int col);

    Cell getCell(int[] indices);

    <T extends Array> T get(int index);

    <T extends Array> T get(int row, int col);

    <T extends Array> T get(int[] indices);

    Cell set(int index, Array value);

    Cell set(int row, int col, Array value);

    Cell set(int[] indices, Array value);

}