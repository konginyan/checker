package com.checker;

import com.checker.util.CheckUtil;
import com.checker.util.EncodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckerCoreApplicationTests {

	@Test
	public void contextLoads() {
		String x = "  :在我们日常的学习和生活之中,时时刻刻都存在矛盾,这些矛盾的存在是推动社会前进的动力,但得有一个前提条件：那就是,这些矛盾都得到很好地解决,只有这样,我们的社会才会进步,反之则会矛又加盾,到处是矛盾,矛矛盾盾.那么,任何正确处理同学们之间的矛盾呢?我给同学们提几点解决方法";
		String y = "在我们日常的学习和生活之中,时时刻刻都存在矛的存在是前进的动力,但得有一我们的社会才之则会矛又加盾,到处是矛盾.那么,任何正确处理同学们之间的矛盾呢?我给同学们提几点解决方法";
		System.out.println(CheckUtil.getSame(x,y));
		System.out.println(CheckUtil.getSameRate(x,y));
		System.out.println(CheckUtil.getSimilarRate(x,y));
	}
}
