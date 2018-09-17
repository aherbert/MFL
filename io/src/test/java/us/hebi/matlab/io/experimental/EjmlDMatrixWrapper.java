package us.hebi.matlab.io.experimental;

import org.ejml.data.DMatrix;
import us.hebi.matlab.io.mat.Mat5;
import us.hebi.matlab.io.mat.Mat5Serializable;
import us.hebi.matlab.io.mat.Mat5Writer;
import us.hebi.matlab.io.types.AbstractArray;
import us.hebi.matlab.io.types.MatlabType;
import us.hebi.matlab.io.types.Sink;

import java.io.IOException;

import static us.hebi.matlab.io.mat.Mat5Type.Double;

/**
 * Serializes an EJML double matrix into a MAT 5 file that can be read by MATLAB
 *
 * @author Florian Enner < florian @ hebirobotics.com >
 */
public class EjmlDMatrixWrapper extends AbstractArray implements Mat5Serializable {

    @Override
    public int getMat5Size(String name) {
        return Mat5.MATRIX_TAG_SIZE
                + Mat5Writer.computeArrayHeaderSize(name, this)
                + Double.computeSerializedSize(matrix.getNumElements());
    }

    @Override
    public void writeMat5(String name, Sink sink) throws IOException {
        Mat5Writer.writeMatrixTag(name, this, sink);
        Mat5Writer.writeArrayHeader(name, this, sink);

        // Data in column major format
        Double.writeTag(matrix.getNumElements(), sink);
        for (int col = 0; col < matrix.getNumCols(); col++) {
            for (int row = 0; row < matrix.getNumRows(); row++) {
                sink.writeDouble(matrix.unsafe_get(row, col));
            }
        }
        Double.writePadding(matrix.getNumElements(), sink);

    }

    @Override
    public MatlabType getType() {
        return MatlabType.Double;
    }

   public EjmlDMatrixWrapper(DMatrix matrix) {
        super(Mat5.dims(matrix.getNumRows(), matrix.getNumCols()), false);
        this.matrix = matrix;
    }

    final DMatrix matrix;

}