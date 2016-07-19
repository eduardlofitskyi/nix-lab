package com.nixsolutions.lab1.task1;

/**
 * Working with big matrix by hand is difficult task.
 * So this class implement standard operation over matrix such as addition, multiplication and transpose
 *
 * @author Eduard Lofitskyi
 */
public class Matrix {

    private static final int DEFAULT_SIZE = 10;

    private double[][] field;

    /**
     * Create matrix with DEFAULT_SIZE
     */
    public Matrix (){
        field = new double[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    /**
     * Create matrix with given horizontal and vertical size
     *
     * @param vertical number of rows
     * @param horizontal number of columns
     */
    public Matrix(int vertical, int horizontal){
        field = new double[vertical][horizontal];
    }

    /**
     *  Return matrix sum
     *
     * @param m matrix that supposed to be added to given matrix
     * @return new Matrix instance that contains matrix sum
     */
    public Matrix plusMatrix(Matrix m){

        if (this.field.length != m.field.length || this.field[0].length != m.field[0].length){
            throw new IllegalArgumentException("Matrix has different dimension");
        }

        Matrix newMatrix = new Matrix(this.field.length, this.field[0].length);

        for (int i = 0; i < this.field.length; i++){
            for (int j = 0; j < this.field[0].length; j++){
                newMatrix.field[i][j] = this.field[i][j] + m.field[i][j];
            }
        }

        return newMatrix;
    }

    /**
     *  Return matrix product
     *
     * @param m matrix that supposed to be multiplied by given matrix
     * @return new Matrix instance that contains matrix product
     */
    public Matrix multiplyMatrix(Matrix m){

        if (this.field[0].length != m.field.length){
            throw new IllegalArgumentException("Horizontal size matrix A not correspond to vertical size matrix B");
        }

        Matrix newMatrix = new Matrix(this.field.length, m.field[0].length);

        for (int i = 0; i < this.field.length; i++){
            for (int j = 0; j < m.field[0].length; j++){
                for (int k = 0; k < this.field[0].length; k++)
                    newMatrix.field[i][j] += this.field[i][k] * m.field[k][j];
            }
        }

        return newMatrix;
    }

    /**
     * Reflect given matrix over its main diagonal
     */
    public void transpose(){
        double[][] transposedField = new double[this.field[0].length][this.field.length];
        for (int i = 0; i < this.field[0].length; i++){
            for (int j = 0; j < this.field.length; j++){
                transposedField[i][j] = this.field[j][i];
            }
        }
        this.field = transposedField;
    }

    /**
     * Forms Matrix instance's information in readable format
     *
     * @return readable presentation of matrix
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.field.length; i++){
            builder.append("[");
            for (int j = 0; j < this.field[0].length; j++){
                builder.append(this.field[i][j]).append(",\t");
            }
            builder.append("]\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix(2, 4);
        Matrix matrix3 = new Matrix();

        matrix1.field = new double[][] {{2, 8}, {-3, 7}, {-8, 5}};
        matrix2.field = new double[][] {{-3, -5, -6, -3},{-9, -2, 3, -3}};
        matrix3.field = new double[][] {{3, 0, 0, 3},{10, 10, 10, 10}};

        Matrix matrixProduct = matrix1.multiplyMatrix(matrix2);
        System.out.println(matrixProduct);

        Matrix matrixSum = matrix2.plusMatrix(matrix3);
        System.out.println(matrixSum);

        matrixSum.transpose();
        System.out.println(matrixSum);
    }
}
