package com.checker.util;

import org.junit.Test;

public class NcrTests {

	@Test
	public void ncrTest(){
		String str = "&#20154;&#31867;&#65292;&#20351;&#25105;&#20204;&#30340;&#12290;&#12304;&#12305;&#65306;&#65307;";
		String str1 = "&#20154;&#31867;&#65292;,a.bc;120";
		System.out.println(EncodeUtil.ConvertDecimalNCRToString(str1));
	}
}
