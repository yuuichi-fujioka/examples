package jp.yf.util;

import static jp.yf.util.Util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

public class UtilTest {

	@Test
	public void test_close() {
		final boolean[] called = { false };

		Closeable c = new Closeable() {

			@Override
			public void close() throws IOException {
				called[0] = true;
			}
		};

		close(c);

		assertThat(called[0], is(true));
	}

	@Test
	public void test_load_text() throws IOException {
		String text = loadTextResource(UtilTest.class, "HelloWorld.txt",
				Charset.forName("UTF-8"));
		String actual = "hello world.\r\nthis is pen.\r\nこんにちは世界";

		assertThat(text, is(actual));
	}

}
