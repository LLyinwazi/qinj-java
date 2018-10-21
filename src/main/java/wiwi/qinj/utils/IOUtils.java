package wiwi.qinj.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Wisya
 * @description IO 工具类
 * @buildTime 2018-08-31 11:48
 */
@Slf4j
public class IOUtils {


    /**
     * 保存文件并返回文件路径
     * @param bytes
     * @param path
     * @return 文件路径
     */
    public static String write2file(byte[] bytes, String path) {
        log.debug("------write2file----path=" + path);
        try {
            File file = new File(path);
            file.deleteOnExit();
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.write(bytes);
        } catch (Exception e) {
            log.warn("---write2file error---", e);
            return null;
        }
        return path;
    }


    /**
     * 保存到文件
     */
    public static boolean write2file(InputStream in, String path) {
        FileOutputStream out = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            byte[] buff = new byte[1024 * 8];
            int read = in.read(buff);
            while (read != -1) {
                out.write(buff, 0, read);
                read = in.read(buff);
            }
        } catch (Exception e) {
            return false;
        } finally {
            close(in, out);
        }
        return true;
    }

    /**
     * 关闭流, 多个关闭时注意顺序
     */
    public static void close(Closeable... stream) {
        for (Closeable closeable : stream) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 根据文件路径获取文件输入流
     */
    public static InputStream getFileInputStream(String path) {
        try {
            return Files.newInputStream(Paths.get(path));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 根据路径获取文件字节码
     */
    public static byte[] loadFile(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取BufferedReader
     */
    public static BufferedReader getBufferReader(File file) {
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * 获取BufferedReader
     */
    public static BufferedReader getBufferReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    /**
     * 获取BufferedReader
     */
    public static BufferedReader getBufferReader(byte[] bytes) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
    }

}
