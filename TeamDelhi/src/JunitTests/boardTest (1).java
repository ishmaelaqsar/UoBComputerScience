package Model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;


class boardTest {
	@Rule
	public ExpectedException expected = ExpectedException.none();

	board testBoard = new board();
	Player testPlayer = new Player("TestMan");
	
	@Before
	public void setUp() {
		
		testBoard.board = new int[][] {{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}};

		testPlayer.playerBoard.board = new int[][] {{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1}};
	}


	//isBoardEmpty method
	@Test
	public void test1() {
		boolean actual = testBoard.isBoardEmpty();
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		this.testBoard.board = new int[][] {{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,1}};

			boolean actual = testBoard.isBoardEmpty();
			boolean expected = false;
			assertEquals(expected, actual);
	}

	@Test
	public void test3() {
		this.testBoard.board = new int[][] {{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,-1,0,0,0,0},
			{0,0,0,44,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,10000,0,0},
			{0,0,0,24,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,16}};

			boolean actual = testBoard.isBoardEmpty();
			boolean expected = false;
			assertEquals(expected, actual);
	}

	@Test
	public void test4() {
		this.testBoard.board = new int[][] {{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,Integer.MAX_VALUE,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,Integer.MIN_VALUE,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}};

			boolean actual = testBoard.isBoardEmpty();
			boolean expected = false;
			assertEquals(expected, actual);
	}


	//isValidLocation(int row, int column, int size, boolean vertical)
	@Test
	public void test5() {
		boolean actual = testBoard.isValidLocation(1, 2, 3, true);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void test6() {
		boolean actual = testBoard.isValidLocation(1, 2, 3, false);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void test7() {
		boolean actual = testBoard.isValidLocation(1, 1, 300, true);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void test8() {
		boolean actual = testBoard.isValidLocation(1, 1, 0, true);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void test9() {
		boolean actual = testBoard.isValidLocation(99, 99, 4, true);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void test10() {
		boolean actual = testBoard.isValidLocation(9, 9, 2, false);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	//coordToInt(String coordinates)
	@Test
	public void test11() {
		int actual = testBoard.coordToInt("a1");
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void test12() {
		int actual = testBoard.coordToInt("a10");
		int expected = 9;
		assertEquals(expected, actual);
	}

	@Test
	public void test13() {
		int actual = testBoard.coordToInt("j10");
		int expected = 99;
		assertEquals(expected, actual);
	}

	//	@Test (expected = IllegalArgumentException.class)
	//	public void test14() {
	//		int actual = testBoard.coordToInt("a1000");
	//		assertEquals(expected, actual);
	//	}
	//	
	//	@Test (expected = IllegalArgumentException.class)
	//	public void test15() {
	//		int actual = testBoard.coordToInt("hello");
	//		assertEquals(expected, actual);
	//	}


	//placeShip(ship ship, String coordinates)
	@Test
	public void test16() {
		ship testShip = new ship("patrol boat");

		boolean actual = testBoard.placeShip(testShip, "a1");
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void test17() {
		ship testShip = new ship("patrol boat");

		boolean actual = testBoard.placeShip(testShip, "j1");
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void test18() {
		ship testShip = new ship("patrol boat");
		testShip.vertical = true;

		boolean actual = testBoard.placeShip(testShip, "j1");
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void test19() {
		ship testShip = new ship("patrol boat");

		boolean actual = testBoard.placeShip(testShip, "j10");
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	public void test20() {
		ship testShip = new ship("patrol boat");

		boolean actual = testBoard.placeShip(testShip, "j10");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test21() {
		ship testShip = new ship("patrol boat");

		boolean actual = testBoard.placeShip(testShip, "j1000");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	//shot(Player player, String shotCoords, int arrayIndex)
	@Test
	public void test22() {

		testPlayer.playerBoard.board = new int[][] {{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1}};

			int[][] actual = testPlayer.playerBoard.board;


			testPlayer.playerBoard.shot(testPlayer, "a1", 0);

			int[][] expected = {{0,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1}};

			assertEquals(expected, actual);
	}


}
