package ge.vakho.matrix_market.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ge.vakho.matrix_market.MatrixMarket;
import ge.vakho.matrix_market.part.data.Data.Entry;
import ge.vakho.matrix_market.part.header.enums.Symmetry;
import ge.vakho.matrix_market.part.size.Size;

public class MMUtils {

	public static boolean isPositiveDefinite(MatrixMarket mm) {
		Symmetry symmetry = mm.getHeader().getSymmetry();
		Size size = mm.getData().getSize();
		List<Entry> entries = mm.getData().getEntries();

		boolean isSymmetric = (symmetry == Symmetry.SYMMETRIC);
		double[][] matrix = new double[size.getNumberOfRows()][size.getNumberOfColumns()];
		for (Entry entry : entries) {
			int i = entry.getRowNumber() - 1;
			int j = entry.getColumnNumber() - 1;
			matrix[i][j] = entry.getValue();
			if (isSymmetric && i != j) {
				matrix[j][i] = entry.getValue();
			}
		}
		return isPositiveDefinite(matrix);
	}

	/**
	 * A matrix is positive definite if it’s symmetric and all its eigenvalues are
	 * positive. Alternatively, a matrix is positive definite if it’s symmetric and
	 * all its pivots are positive and all the pivots will be positive if and only
	 * if det(A_k) > 0 for all [1 <= k <= n].
	 * 
	 * @param matrix market object
	 * @return whether matrix is positive definite or not
	 * @see <a href=
	 *      "https://www.math.utah.edu/~zwick/Classes/Fall2012_2270/Lectures/Lecture33_with_Examples.pdf">How
	 *      to determine if matrix is positive definite.</a>
	 */
	public static boolean isPositiveDefinite(double[][] matrix) {
		List<Double> dets = new ArrayList<>(matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			double[][] copy = Arrays.copyOfRange(matrix, 0, i + 1);
			for (int j = 0; j < copy.length; j++) {
				copy[j] = Arrays.copyOfRange(copy[j], 0, i + 1);
			}
			dets.add(determinant(copy));
		}
		return dets.parallelStream().map(det -> det > 0).reduce(true, Boolean::logicalAnd);
	}

	public static double determinant(double[][] matrix) {
		double result = 0;
		if (matrix.length == 1) {
			result = matrix[0][0];
			return result;
		}
		if (matrix.length == 2) {
			result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
			return result;
		}
		for (int i = 0; i < matrix[0].length; i++) {
			double temp[][] = new double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {

					if (k < i) {
						temp[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temp[j - 1][k - 1] = matrix[j][k];
					}
				}
			}
			result += matrix[0][i] * Math.pow(-1, (int) i) * determinant(temp);
		}
		return result;
	}

	public static void main(String[] args) {
		double[][] matrix = { //
				{ 2, -1, 0 }, //
				{ -1, 2, -1 }, //
				{ 0, -1, 2 } //
		};

		for (int i = 0; i < matrix.length; i++) {
			double[][] copy = Arrays.copyOfRange(matrix, 0, i + 1);
			for (int j = 0; j < copy.length; j++) {
				copy[j] = Arrays.copyOfRange(copy[j], 0, i + 1);
			}
			for (int j = 0; j < copy.length; j++) {
				System.out.print("[ ");
				for (int k = 0; k < copy[j].length; k++) {
					System.out.print(copy[j][k]);
					if (k != copy[j].length - 1) {
						System.out.print(", ");
					}
				}
				System.out.print(" ]");
				System.out.println();
			}
			System.out.println();

			System.out.println(determinant(copy));
		}

		System.out.println(isPositiveDefinite(matrix));
	}
}