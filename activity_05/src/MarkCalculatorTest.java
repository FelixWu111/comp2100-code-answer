

//Dongwoo Kim, 2019
//Sample code with JUnit 5 

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameters;

class MarkCalculatorTest {
	
	/* getImplementations will return a list of calculator implementations, 
	   which will be fed into other parameterized tests.
	*/
	@Parameters
	public static Iterable<? extends Object>  getImplementations() {
		return Arrays.asList(
				new MarkCalculator1(),
				new MarkCalculator2(),
				new MarkCalculator3(),
				new MarkCalculator4(),
				new MarkCalculator5(),
				new MarkCalculator6(),
				new MarkCalculator7()	
		   );
	}
	
	/* @ParameterizedTest code will run with a list of arguments returned by @MethodSource.
	   In other words, testException method will be tested 7 times with different implementation of calculator.
	*/
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testException(MarkCalculator calculator) {
		//TODO: write more test cases if you need
		//The following statement will pass all the test
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(-1, 0, 0, 0, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(5, 5, 5, 200, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(10, -10, 10, 100, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(10, 10, -10, 100, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(100, 5, 5, 90, true));
		//The following statement will fail the test
//		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(5, 5, 5, 5, true));
//		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(10, 10, 10, 100, true));
//		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 0, 0, 0, true));
	}
	
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testAllValue(MarkCalculator calculator) throws ComponentOutOfRangeException {
		//TEST ALL INPUT
		for (int lab = 0; lab < 11; lab++) {
			for (int ass1 = 0; ass1 < 11; ass1++) {
				for (int ass2 = 0; ass2 < 11; ass2++) {
					for (int exam = 0; exam < 101; exam++) {
						float temp_mark = lab * 1.0f + ass1 * 1.5f + ass2 * 1.5f + exam * 0.6f;
						int mark = Math.round(temp_mark);
						if (mark < 45)
							assertEquals(new MarkGrade(mark, Grade.N), calculator.calculateMark(lab, ass1, ass2, exam, true));
						else if (mark < 50)
							assertEquals(new MarkGrade(mark, Grade.PX), calculator.calculateMark(lab, ass1, ass2, exam, true));
						else if (mark < 60)
							assertEquals(new MarkGrade(mark, Grade.P), calculator.calculateMark(lab, ass1, ass2, exam, true));
						else if (mark < 70)
							assertEquals(new MarkGrade(mark, Grade.C), calculator.calculateMark(lab, ass1, ass2, exam, true));
						else if (mark < 80)
							assertEquals(new MarkGrade(mark, Grade.D), calculator.calculateMark(lab, ass1, ass2, exam, true));
						else
							assertEquals(new MarkGrade(mark, Grade.HD), calculator.calculateMark(lab, ass1, ass2, exam, true));
					}
				}
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testGradeN(MarkCalculator calculator) throws ComponentOutOfRangeException {
		//TODO: write more test cases if you need
		//The following statement will pass the test
		assertEquals(new MarkGrade(0, Grade.N), calculator.calculateMark(0, 0, 0, 0, true));
		assertEquals(new MarkGrade(8, Grade.N), calculator.calculateMark(2, 2, 2, 0, true));
		assertEquals(new MarkGrade(44, Grade.N), calculator.calculateMark(0, 0, 0, 73, true));
		//The following statement will fail the test
		//assertEquals(new MarkGrade(0, Grade.NCN), calculator.calculateMark(0, 0, 0, 0, true));
		//assertEquals(new MarkGrade(0, Grade.N), calculator.calculateMark(10, 10, 10, 100, true));
		//assertEquals(new MarkGrade(45, Grade.N), calculator.calculateMark(0, 0, 0, 75, true));
		
	}
	
	//TODO: write more test methods to test MarkCalculators
	/*
	 * MarkCalculator2 failed
	 */
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testGardeNCN(MarkCalculator markCalculator) throws ComponentOutOfRangeException{
		assertEquals(new MarkGrade(null, Grade.NCN), markCalculator.calculateMark(0, 0, 0, 0, false));
	}
	
	
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testGardeHD(MarkCalculator markCalculator) throws ComponentOutOfRangeException{
		//The logic is wrong but only MarkCalculator4 passes, so MarkCalculator4 has a bug.
		assertEquals(new MarkGrade(101, Grade.HD), markCalculator.calculateMark(2, 7, 8, 43, true));
		//MarkCalculator6 has a bug about HD.
		assertEquals(new MarkGrade(100, Grade.HD), markCalculator.calculateMark(10, 10, 10, 100, true));
		
	}
	
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testGardeHD1(MarkCalculator markCalculator) throws ComponentOutOfRangeException{
		//MarkCalculator6 has a bug about HD.
		assertEquals(new MarkGrade(100, Grade.HD), markCalculator.calculateMark(10, 10, 10, 100, true));
		
	}
	
	
	/*
	 * MarkCalculator does not throw the exception
	 */
	@ParameterizedTest
	@MethodSource("getImplementations")
	void testPartException(MarkCalculator calculator){
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 0, 0, -1, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 0, 0, 101, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 15, 15, 0, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, -1, 0, 0, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 11, 0, 0, true));
		assertThrows(ComponentOutOfRangeException.class, ()->calculator.calculateMark(0, 0, -1, 0, true));
	}
	

	
}

