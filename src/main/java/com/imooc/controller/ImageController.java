package com.imooc.controller;

import com.UpYun;
import com.imooc.VO.ResultVO;
import com.imooc.config.UpYunConfig;
import com.imooc.utils.ResultVOUtil;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author CodingSir
 * @Title: project
 * @Package ========
 * @Description: ========
 * @date 2020/8/28  17:40
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private UpYunConfig upYunConfig;
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file_data") MultipartFile multipartFile) {
        UpYun upyun = new UpYun(upYunConfig.getBucketName(), upYunConfig.getUsername(), upYunConfig.getPassword());
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1));
        try {
            upyun.writeFile(fileName, multipartFile.getInputStream(), true, new HashMap<>());
            Map map = new HashMap<>();
            map.put("fileName", fileName);
            return ResultVOUtil.success(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }

        return null;
    }
}
