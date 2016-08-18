package vip.xubin.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.xubin.common.utils.FtpUtil;
import vip.xubin.common.utils.IDUtils;
import vip.xubin.service.PictureService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 許彬.
 * @creater 2016-08-18 14:44
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private int FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        String imagePath = new DateTime().toString("/yyyy/MM/dd");

        HashMap<String, Object> map = new HashMap<>();

        try {
            String oldname = uploadFile.getOriginalFilename();
            String imageName = IDUtils.genImageName() + oldname.substring(oldname.lastIndexOf("."));

            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,imagePath, imageName, uploadFile.getInputStream());
            if (!result) {
                map.put("error",1);
                map.put("massage","文件上传失败");
                return map;
            }
                map.put("error",0);
                map.put("url", IMAGE_BASE_URL + imagePath + "/" + imageName);
                //map.put("url", "http://picm.photophoto.cn/006/018/030/0180300367.jpg");
                return map;


        } catch (IOException e) {
            map.put("error",1);
            map.put("massage","文件上传失败");
            return map;
        }

    }
}
