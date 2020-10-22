package cn.magicnian.深源批跑;

import cn.magicnian.util.HttpClientPoolingCrawler;
import cn.magicnian.util.HttpCustomResponse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class SYBatchDemo {

    static final Logger logger = LoggerFactory.getLogger(SYBatchDemo.class);


    public static void main(String[] args) {
        String url = "https://cloudapi.accuauth.com/image/quality";
        String apiId = "123";
        String apiSecret = "123";

        Map<String, String> headers = new HashMap<>();
        headers.put("X-DF-API-ID", apiId);
        headers.put("X-DF-API-SECRET", apiSecret);

        List<WriteData> saveDatas = new ArrayList<>();
        List<String> paths = getFullPath("D:\\batch\\good");
        for (int i = 0; i < paths.size(); i++) {
            WriteData writeData = new WriteData();
            writeData.setFileName(paths.get(i));
            try {
                logger.info("===={},start===", paths.get(i));
                Map<String, String> datas = new HashMap<>();
                datas.put("image_base64", getBase64(paths.get(i)));
                HttpClientPoolingCrawler clientPoolingCrawler = HttpClientPoolingCrawler.custom();
                HttpCustomResponse response = clientPoolingCrawler.url(url).headers(headers).data(datas).post();
                logger.info("{},result:{}", paths.get(i), response.getResponseBody());
                writeData.setResult(response.getResponseBody());
                SYResponse syResponse = JSONObject.parseObject(response.getResponseBody(), SYResponse.class);
                if (!syResponse.getStatus().equalsIgnoreCase("OK")) {
                    writeData.setQuality("0");
                    saveDatas.add(writeData);
                    throw new RuntimeException("error");
                } else {
                    writeData.setQuality(syResponse.getQuality());
                    saveDatas.add(writeData);
                }
            } catch (Exception e) {
                logger.error("{},get error", paths.get(i));
            }

        }

        //保存至excel
        String excel = "D:\\batch\\result\\good.xls";
        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(excel));
            WritableSheet sheet = writableWorkbook.createSheet("sheet1", 0);
            String[] titles = {"照片", "返回结果", "质量"};
            Label label = null;
            //设置第一行，表头
            for (int i = 0; i < titles.length; i++) {
                label = new Label(i, 0, titles[i]);
                sheet.addCell(label);
            }

            for (int i = 0; i < saveDatas.size(); i++) {
                WriteData writeData = saveDatas.get(i);
                label = new Label(0, i + 1, writeData.getFileName());
                sheet.addCell(label);

                label = new Label(1, i + 1, writeData.getResult());
                sheet.addCell(label);

                label = new Label(2, i + 1, writeData.getQuality());
                sheet.addCell(label);
            }

            writableWorkbook.write();
            writableWorkbook.close();

            logger.info("finish");

        } catch (Exception e) {
            e.printStackTrace();
        }
//        getFullPath("D:\\batch").forEach(System.out::println);
    }


    /**
     * 批量获取某个目录下所有图像转换成的base64字符串
     *
     * @param path 文件夹目录
     * @return
     */
    public static List<String> getBatchBase64(String path) {
        List<String> base64s = new ArrayList<>();
        List<String> filePaths = getFullPath(path);
        filePaths.forEach(p -> {
            String s = getBase64(p);
            if (s != null) {
                base64s.add(s);
            }
        });

        return base64s;
    }

    /**
     * 获取单张图片的base64编码
     *
     * @param filePath 图片路径
     * @return
     */
    public static String getBase64(String filePath) {
        File f = new File(filePath);
        BufferedImage image = null;
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            image = ImageIO.read(f);
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageString;
    }

    /**
     * 获取某个路径下的所有文件的全路径
     *
     * @param path
     * @return
     */
    public static List<String> getFullPath(String path) {
        List<String> paths = new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if(!files[i].isDirectory()){
                    paths.add(files[i].toString());
                }
            }
        }
        return paths;
    }

    @Data
    static class SYResponse {
        private String request_id;
        private String status;
        private String quality;
        private String reason;
    }

    @Data
    static class WriteData {
        private String fileName;
        private String result;
        private String quality;
    }
}
