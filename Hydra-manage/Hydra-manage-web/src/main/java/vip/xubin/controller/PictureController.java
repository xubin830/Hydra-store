package vip.xubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import vip.xubin.common.utils.JsonUtils;
import vip.xubin.service.PictureService;

import java.util.Map;

/**
 * 图片上传Controller
 *
 * @author 許彬.
 * @creater 2016-08-18 15:13
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;


    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadPicture(MultipartFile uploadFile){

        Map map = pictureService.uploadPicture(uploadFile);

        return JsonUtils.objectToJson(map);
    }



}
