package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(Integer item : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter() {
			@Override
			public boolean include(Object item) {
				int current = (int)item;
				return current > 2;
			}
		});

		for(Integer item : result){
			assertTrue(item > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(i -> i % 2 == 0);
		for(Integer i : result){
			assertEquals(0, i % 2);
		}
	}

	@Test
	void test() {
		SimpleList<Integer> result = testList.filter(item -> item == 1);
		assertEquals(1, result.size());
	}

	@Test
	void testMap() {
		SimpleList<String> result = testList.map((i) -> "Hallo");
		for(String item: result) {
			assertEquals("Hallo", item);
		}
	}

	@Test
	void testDeleteExistingElement() {
		testList.delete(3);
		SimpleList<Integer> result = testList.filter(i -> i == 3);
		assertEquals(0, result.size());
	}
}