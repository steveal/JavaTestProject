package com.test.pattern;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参考链接
 * http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651477705&idx=1&sn=89a37b2724fec43b75baf5d1eb310e8b&chksm=bd253ab68a52b3a08db633703154db0cc7b3ed5f18ccc92151b8aefdc4a1f8c4ab1b4dd1ceeb&mpshare=1&scene=1&srcid=1109plSCIV7SwrMaqgvOlQsL#rd
 * @author Steve
 *
 */
public class TestPattern {

	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
//		test7();
//		test8();
		test9();
	}

	/**
	 * basic
	 */
	public static void test1() {
		String text = "This is the text to be searched " + "for occurrences of the http:// pattern.";
		String pattern = ".*http://.*";
		// String pattern = ".*http://.*";
		boolean m = Pattern.matches(pattern, text);
		System.out.println(pattern + "match : " + m);

		String pattern2 = "http://";
		boolean m2 = Pattern.matches(pattern2, text);
		System.out.println(pattern2 + "match : " + m2);
	}

	/**
	 * basic2
	 */
	public static void test2() {
		String text = "This is the text to be searched " + "for occurrences of the http:// pattern.";
		String patternString = ".*hTTp://.*";
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		boolean matches = matcher.matches();

		System.out.println("matches = " + matches);
	}

	/**
	 * split
	 */
	public static void test3() {
		String text = "A sep Text sep With sep Many sep Separators";
		String patterString = "sep";
		Pattern pattern = Pattern.compile(patterString);
		String[] splits = pattern.split(text);
		for (String s : splits) {
			System.out.println(s);
		}

	}

	/**
	 * lookingAt
	 */
	public static void test4() {
		String text = "This is the text to be searched " + "for occurrences of the http:// pattern.";
		String patternString = "This is the";
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		System.out.println("lookingAt = " + matcher.lookingAt());
		System.out.println("matches   = " + matcher.matches());
	}

	/**
	 * find
	 */
	public static void test5() {
		String text = "This is the text which is to be searched " + "for occurrences of the word 'is'.";
		String patternString = "is";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find()) {
			count++;
			System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
		}
		
//		while(matcher.find()) {
//			System.out.println(text.substring(matcher.start(),matcher.end()));
//		}
	}

	/**
	 * basic group
	 */
	public static void test6() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "(John)";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}
	
	/**
	 * tow group
	 */
	public static void test7() {
		String text    =
		          "John writes about this, and John Doe writes about that," +
		                  " and John Wayne writes about everything."
		        ;
		String patternString1 = "(John) (.+?) ";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
		    System.out.println("found: " + matcher.group(1) +
		                       " "       + matcher.group(2));
		}
	}
	
	/**
	 * replace
	 */
	public static void test8() {
		String text    =
		          "John writes about this, and John Doe writes about that," +
		                  " and John Wayne writes about everything."
		        ;
		String patternString1 = "((John) (.+?)) ";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		 
		String replaceAll = matcher.replaceAll("Joe Blocks ");
		System.out.println("replaceAll   = " + replaceAll);
		 
		String replaceFirst = matcher.replaceFirst("Joe Blocks ");
		System.out.println("replaceFirst = " + replaceFirst);
	}
	
	
	/**
	 * appendReplacement appendTail
	 */
	public static void test9() {
		String text    =
		          "John writes about this, and John Doe writes about that," +
		                  " and John Wayne writes about everything."
		        ;
		 
		String patternString1 = "((John) (.+?)) ";
//		String patternString1 = "John ";
		Pattern      pattern      = Pattern.compile(patternString1);
		Matcher      matcher      = pattern.matcher(text);
		StringBuffer stringBuffer = new StringBuffer();
		 
		while(matcher.find()){
		    matcher.appendReplacement(stringBuffer, "Joe Blocks ");
		    System.out.println(stringBuffer.toString());
		}
		matcher.appendTail(stringBuffer);
		System.out.println(stringBuffer.toString());
	}
}
