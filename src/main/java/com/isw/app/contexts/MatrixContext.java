package com.isw.app.contexts;

import com.isw.app.core.objects.Matrix;

public class MatrixContext {
  private static Matrix matrix;

  public static void clearMatrix() {
    matrix = null;
  }

  public static Matrix getMatrix() {
    return matrix;
  }

  public static void setMatrix(Matrix matrix) {
    MatrixContext.matrix = matrix;
  }
}
