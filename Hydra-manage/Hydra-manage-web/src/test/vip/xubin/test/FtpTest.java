package vip.xubin.test;

import org.junit.Test;
import vip.xubin.common.utils.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * FTP测试程序
 *
 * @author 許彬.
 * @creater 2016-08-18 14:21
 */

public class FtpTest {

    @Test
    public void FtpTest() throws FileNotFoundException {

        FtpUtil.uploadFile("192.168.202.120",21,"ftpuser","ftpuser","/home/ftpuser/www/images","/2016/08/18","test1.jpg",new FileInputStream(new File("C:\\Users\\cynic\\Desktop\\IDEA快捷键.jpg")));
    }
}
