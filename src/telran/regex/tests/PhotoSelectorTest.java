package telran.regex.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.regex.tools.PhotoSelector;

class PhotoSelectorTest {
	private static final String[] pictures = {
			"Paris\\20140101_090000.jpg",
			"Paris\\20140201_121005.jpg",
			"Paris\\20150301_211035.jpg",	//2015 night spring
			"Paris\\20150401_110023.gif",	//2015 spring
			"Paris\\20150401_181705.jpg",	//2015 night spring
			"Paris\\20150501_231035.gif",	//2015 night spring
			"London\\20140205_090000.jpg",	
			"London\\20140205_121005.jpg",	
			"London\\20140601_211035.gif",  //night
			"London\\20151001_110023.jpg",//autumn
			"London\\20151001_121705.jpg",//autumn
			"London\\20151001_231035.jpg",//autumn night 
			"Chicago\\20150301_120001.png",//2015 spring
			"Chicago\\20151111_232000.png",//autumn night
	};
	@Test
	void testAllEuropePictures() {
	String regex = "^(Paris|London)";
	String[] actual  = PhotoSelector.selectPicture(pictures, regex);
	String[] expected = {
			"Paris\\20140101_090000.jpg",
			"Paris\\20140201_121005.jpg",
			"Paris\\20150301_211035.jpg",	
			"Paris\\20150401_110023.gif",	
			"Paris\\20150401_181705.jpg",	
			"Paris\\20150501_231035.gif",	
			"London\\20140205_090000.jpg",	
			"London\\20140205_121005.jpg",	
			"London\\20140601_211035.gif",
			"London\\20151001_110023.jpg",	
			"London\\20151001_121705.jpg",	
			"London\\20151001_231035.jpg",	
	};
	assertArrayEquals(expected, actual);
	}
	
	@Test 
	void testAllAutumnPictures () {
		  String regex = "^(London|Chicago)\\\\201509|201510|201511";
		String[] actual = PhotoSelector.selectPicture(pictures, regex);
		String[] expected = {
				"London\\20151001_110023.jpg",
				"London\\20151001_121705.jpg",
				"London\\20151001_231035.jpg",
				"Chicago\\20151111_232000.png"
		};
		assertArrayEquals(expected, actual);
	}
	
	@Test 
	void testAll2015SpringPhotos () {
		String regex = "^(Paris|Chicago)\\\\(20[1][5])(0[3-5])";
		String[] actual = PhotoSelector.selectPicture(pictures, regex);
		String[] expected = {
				"Paris\\20150301_211035.jpg",	
				"Paris\\20150401_110023.gif",	
				"Paris\\20150401_181705.jpg",
				"Paris\\20150501_231035.gif",
				"Chicago\\20150301_120001.png"
		};
		assertArrayEquals(expected, actual);
	}
	
	@Test 
	void testAllNightPictures () { //18:00-23:59
		String regex = "^(London|Paris|Chicago)\\\\(2014|2015)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\_(1[8-9]|2[0-3])[0-5][0-9][0-5][0-9]\\.\\w{3}$";
		String[] actual = PhotoSelector.selectPicture(pictures, regex);
		String[] expected = {
				"Paris\\20150301_211035.jpg",
				"Paris\\20150401_181705.jpg",	
				"Paris\\20150501_231035.gif",	
				"London\\20140601_211035.gif",
				"London\\20151001_231035.jpg",
				"Chicago\\20151111_232000.png"
		};
		assertArrayEquals(expected, actual);
	}
	@Test 
	void testAllNightPicturesFromChichago () { //18:00-23:59
		String regex = "^Chicago\\\\2015(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\_(1[8-9]|2[0-3])[0-5][0-9][0-5][0-9]\\.\\w{3}$";
		String[] actual = PhotoSelector.selectPicture(pictures, regex);
		String[] expected = {
				"Chicago\\20151111_232000.png"
		};
		assertArrayEquals(expected, actual);
	}
	@Test 
	void testAllPicturesJpgAndPng () { 
		String regex = "^(London|Paris|Chicago)\\\\(2014|2015)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])_(0[0-9]|1[0-9]|2[0-3])[0-5][0-9][0-5][0-9]\\.(jpg|png)$";


		String[] actual = PhotoSelector.selectPicture(pictures, regex);
		String[] expected = {
				"Paris\\20140101_090000.jpg",
				"Paris\\20140201_121005.jpg",
				"Paris\\20150301_211035.jpg",
				"Paris\\20150401_181705.jpg",
				"London\\20140205_090000.jpg",	
				"London\\20140205_121005.jpg",	
				"London\\20151001_110023.jpg",
				"London\\20151001_121705.jpg",
				"London\\20151001_231035.jpg",
				"Chicago\\20150301_120001.png",
				"Chicago\\20151111_232000.png",
		};
		assertArrayEquals(expected, actual);
	}

}
