/*
 * UploadPictureUtil.java 1.0.0 2018/01/25  20:36
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  20:36 created by xulihua
 */

package com.xulihuazj.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/*
 *     将文件上传至阿里云服务器
 *      图片
*/
public class UploadPictureUtil {

    private final static Logger logger = LogManager.getLogger(UploadPictureUtil.class);

    //客户端对象
    private static volatile OSSClient ossClient = null;


    private static final Object lockHelper = new Object();


    private OSSClient getOssClient(String endpoint, String accessKeyId, String accessKeySecret) {
        if (ossClient == null) {
            synchronized (lockHelper) {
                if (ossClient == null) {
                    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public void initOSSClient(String endpoint, String accessKeyId, String accessKeySecret) {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /*
    *   accessKeyId 账户key
    *    accessKeySecret 加密秘钥
    *   bucketName  bucket名称
    *   source     图片数据源
    *  fileName   文件将名称
    *  endpoint   域名地址
    *  folder     存放在阿里云上面的文件夹名称
    */
    public String uploadSource(String accessKeyId, String accessKeySecret, String bucketName, InputStream source, String fileName, String endpoint, String folder) {
        //获得OSSClient对象
        getOssClient(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = source;
        //用于存放putObject接口返回过来的MD5加密值
        String eTag;
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            //图片类型
            objectMetadata.setContentType("image/jpeg");
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, objectMetadata);
            eTag = putResult.getETag();
        } catch (IOException e) {
            LogHelper.exception(e, logger, "上传图片异常");
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LogHelper.exception(e, logger, "上传图片异常");
                throw new RuntimeException(e);
            }
        }
        return eTag;
    }

    /*
  *   accessKeyId 账户key
  *    accessKeySecret 加密秘钥
  *   bucketName  bucket名称
  *   folder     存放文件夹
  *  fileName   文件将名称
  *  endpoint   域名地址
  */
    public void deletePic(String accessKeyId, String accessKeySecret, String endpoint, String bucketName, String fileName, String folder) {
        //获得OSSClient对象
        getOssClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, folder + fileName);
    }
}
