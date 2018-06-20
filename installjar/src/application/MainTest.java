package application;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class MainTest {
	private static Main main =new Main();
	@Test
	public void testCopy() throws Exception {
		File file = new File("D:/data/testfile/mainTest/old/test.txt");
		File toFile = new File("D:/data/testfile/mainTest/new");
		main.copy(file, toFile);
		File test = new File("D:/data/testfile/mainTest/new/test.txt");
		assertEquals(true, test.isFile());
		main.deleteFile(test);
	}

	@Test
	public void testDeleteFile() throws Exception {
		File file = new File("D:/data/testfile/mainTest/old/test.txt");
		File toFile = new File("D:/data/testfile/mainTest/new");
		main.copy(file, toFile);
		File test = new File("D:/data/testfile/mainTest/new/test.txt");
		main.deleteFile(test);
		assertEquals(false, test.isFile());
	}

	@Test
	public void testReadFileByLines() {
		main.readFileByLines("D:/data/testfile/mainTest/old/test.txt");
		assertEquals("123", main.oldJar);
		assertEquals("456", main.newJar);
	}

	@Test
	public void testgetPath() {
		assertEquals("D:/data/testfile/mainTest/old", main.getPath("/D:/data/testfile/mainTest/old/test.txt"));
	}
}
