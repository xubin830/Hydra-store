package vip.xubin.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by cynic on 2016/8/18.
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);

}
