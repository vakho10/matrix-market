package ge.vakho.matrix_market;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ //
		HeaderTest.class, //
		MatrixMarketTest.class //
})
public class MatrixMarketTestSuite {
}