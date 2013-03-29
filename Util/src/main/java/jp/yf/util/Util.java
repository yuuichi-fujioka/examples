package jp.yf.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	private static Logger logger = LoggerFactory.getLogger(Util.class);

	private Util() {
	}

	public static void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				logger.warn("close threw exception", e);
			}
		}
	}

	public static String readText(InputStream in, Charset charset)
			throws IOException {

		ByteArrayOutputStream byteArray = null;
		try {
			byteArray = new ByteArrayOutputStream();

			int data;
			while ((data = in.read()) >= 0) {
				byteArray.write(data);
			}

			return new String(byteArray.toByteArray(), charset);
		} finally {
			close(byteArray);
		}
	}

	public static String loadTextResource(Class<?> clazz, String resourceName,
			Charset charset) throws IOException {
		InputStream in = null;
		try {
			in = new BufferedInputStream(
					clazz.getResourceAsStream(resourceName));
			return readText(in, charset);
		} finally {
			close(in);
		}
	}

	public static BufferedImage loadImageResource(Class<?> clazz,
			String resourceName) throws IOException {
		InputStream in = null;
		try {
			in = new BufferedInputStream(
					clazz.getResourceAsStream(resourceName));
			return ImageIO.read(in);
		} finally {
			close(in);
		}
	}
}
