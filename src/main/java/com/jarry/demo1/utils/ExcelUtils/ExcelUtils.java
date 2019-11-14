package com.jarry.demo1.utils.ExcelUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;


@Slf4j
public class ExcelUtils {

    /**
     * 生成excel
     * @param response
     * @param fileName
     * @param datas
     * @param clazz
     * @param hanlder
     */
    public static void excelExport(HttpServletResponse response, String fileName, List<? extends BaseRowModel> datas,
                                   Class<? extends BaseRowModel> clazz, WriteHandler hanlder){
        ServletOutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = response.getOutputStream();
            fileName = new String(fileName.getBytes(), "iso8859-1");
            response.setHeader("Content-disposition","attachment;filename="+ fileName +".xlsx");
            response.setCharacterEncoding("utf-8");
            writer = EasyExcelFactory.getWriterWithTempAndHandler(null,outputStream,ExcelTypeEnum.XLSX,true,hanlder);
//            writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0, clazz);
            writer.write(datas,sheet);
            writer.finish();
        } catch (Exception e){
        } finally {
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 动态表头导出
     * @param response
     * @param fileName 文件名
     * @param heads    表头
     * @param datas    数据
     * @param hanlder   表格样式
     */
    public static void excelExport(HttpServletResponse response, String fileName, LinkedHashMap<String,String> heads,
                                   List<? extends Object> datas,Class clazz, WriteHandler hanlder){
        ServletOutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            log.info("--------开始导入{}数据，共{}条",clazz.getName(),datas.size());
            outputStream = response.getOutputStream();
            fileName = new String(fileName.getBytes(), "iso8859-1");
            response.setHeader("Content-disposition","attachment;filename="+ fileName +".xlsx");
            response.setCharacterEncoding("utf-8");
            writer = EasyExcelFactory.getWriterWithTempAndHandler(null,outputStream,ExcelTypeEnum.XLSX,true,hanlder);
//            writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX,true);
            Sheet sheet = new Sheet(1,0);
            List<List<String>> head = new ArrayList<>();
            for(Map.Entry<String,String> entry:heads.entrySet()){
                head.add(Collections.singletonList(entry.getKey()));
            }
            sheet.setHead(head);
            writer.write1(getData(heads,datas,clazz),sheet);
            writer.finish();
            log.info("--------导入完成");
        } catch (Exception e){
        } finally {
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导入excel
     * @param request
     * @param file
     * @param clazz
     * @return
     */
    public static List<Object> excelImport(HttpServletRequest request, MultipartFile file,Class<? extends BaseRowModel> clazz){
        InputStream is = null;
        try {
            is = new BufferedInputStream(request.getInputStream());
            Sheet sheet = new Sheet(1,0, clazz);
            List<Object> datas = EasyExcelFactory.read(is, sheet);
            return datas;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 导入excel
     * @param file
     * @param clazz
     * @return
     */
    public static List<Object> importExcel(MultipartFile file,Class<? extends BaseRowModel> clazz){
        InputStream is = null;
        try {
            is = new BufferedInputStream(file.getInputStream());
            Sheet sheet = new Sheet(1,0, clazz);
            List<Object> datas = EasyExcelFactory.read(is, sheet);
            return datas;
        } catch (IOException e){
            e.printStackTrace();
        } finally {
                if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private static LinkedList<List<Object>> getData(LinkedHashMap<String, String> heads, List<?> datas, Class clazz) {
        LinkedList<List<Object>> result = new LinkedList<>();
        datas.forEach(o->{
            List<Object> list = new ArrayList<>();
            heads.forEach((k,v)->{
                try {
                    Field field = clazz.getDeclaredField(v);
                    field.setAccessible(true);
                    Object o1 =  field.get(o);
                    list.add(o1);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            result.add(list);
        });
        return result;
    }
}
