package com.zhskg.bag.rest.utils;

import com.zhskg.bag.common.util.ReturnMap;
import com.zhskg.bag.enums.FileSourceEnum;
import com.zhskg.bag.param.FileInfo;
import com.zhskg.bag.param.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther:jean
 * @Date:2018/10/15
 * @Descripsion
 */
@Slf4j
@Component
public class FileConsumer {

    private static final String BIN_WIN_ABSOLUTE_PATH = "d:/bag/hw/";
    private static final String BIN_LINUX_ABSOLUTE_PATH = "/dev/bag/hw/";
    private static final String BIN_RELATIVE_PATH = "hw/";
    private static final String WIN_ABSOLUTE_PATH = "d:/bag/image/";
    private static final String LINUX_ABSOLUTE_PATH = "d:/bag/image/";
    private static final String RELATIVE_PATH = "image/";


    public FileInfo uploadFileToDisk(MultipartFile file, String source) {
        try {
            String fileName = file.getOriginalFilename();
            String extName = getExtName(fileName);
            String absoluteDiskPath = null;
            String relativePath;
            String _fileName;
            File dest;
            String filePath;


            if (extName.equals("bin")) {
                source = FileSourceEnum.GENERA_BAG.getName();
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    absoluteDiskPath = BIN_WIN_ABSOLUTE_PATH + source;
                } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                    absoluteDiskPath = BIN_LINUX_ABSOLUTE_PATH + source;
                }
                relativePath = BIN_RELATIVE_PATH + source;
                // 解决中文问题，liunx下中文路径，图片显示问题
//                String _fileName = UUID.randomUUID() + "." + extName;
                _fileName = fileName;
                dest = new File(absoluteDiskPath + fileName);
                //判断父级文件是否存在
                if (!dest.getParentFile().exists()) {
                    //创建父级文件
                    dest.getParentFile().mkdirs();
                } else {
                    //文件夹中若存在文件，删除旧的
                    for (File f : dest.getParentFile().listFiles()) {
                        f.delete();
                    }
                }
            } else {
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    absoluteDiskPath = WIN_ABSOLUTE_PATH + source;
                } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                    absoluteDiskPath = LINUX_ABSOLUTE_PATH + source;
                }
                relativePath = RELATIVE_PATH + source;
                // 解决中文问题，liunx下中文路径，图片显示问题
                _fileName = UUID.randomUUID() + "." + extName;
                dest = new File(absoluteDiskPath + _fileName);
                //判断父级文件是否存在
                if (!dest.getParentFile().exists()) {
                    //创建父级文件
                    dest.getParentFile().mkdirs();
                }
            }
            file.transferTo(dest);
            filePath = relativePath + _fileName;

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileType(extName);
            fileInfo.setFilePath(filePath);
            fileInfo.setFileName(fileName);
            return fileInfo;
        } catch (Exception ex) {
            log.error("上传失败", ex);
        }
        return null;
    }

    public List batchUploadToDisk(MultipartFile[] files, String source) {
        try {
            List<FileInfo> list = new ArrayList<>();
            if (files != null && files.length > 0) {
                FileInfo fileInfo;
                String absoluteDiskPath = null;

                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    absoluteDiskPath = WIN_ABSOLUTE_PATH + source;
                } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                    absoluteDiskPath = LINUX_ABSOLUTE_PATH + source;
                }
                String relativePath = RELATIVE_PATH + source;
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    String extName = getExtName(fileName);
                    String _fileName = UUID.randomUUID() + "." + extName;
                    File dest = new File(absoluteDiskPath + _fileName);
                    //判断父级文件是否存在
                    if (!dest.getParentFile().exists()) {
                        //创建父级文件
                        dest.getParentFile().mkdirs();
                    }
                    file.transferTo(dest);
                    String filePath = relativePath + _fileName;

                    fileInfo = new FileInfo();
                    fileInfo.setFileType(extName);
                    fileInfo.setFilePath(filePath);
                    fileInfo.setFileName(fileName);
                    list.add(fileInfo);
                }

            }
            return list;
        } catch (Exception ex) {
            log.error("批量上传失败", ex);
        }
        return null;
    }

    private String getExtName(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.')).substring(1);
    }
}
