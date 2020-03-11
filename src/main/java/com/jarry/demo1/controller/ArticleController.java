package com.jarry.demo1.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.Entry.Result;
import com.jarry.demo1.annotation.SysOperationLog;
import com.jarry.demo1.service.ArticleService;
import com.jarry.demo1.utils.ExcelUtils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-12 15:53
 */
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    //根据id获取文章内容
    @SysOperationLog(remark = "新建用户",operationType = 1)
    @GetMapping("getArticle/{id}")
    public Result getArticle(@PathVariable("id") int id){
        Result s = new Result();
        Article article = articleService.getOne(id);
        s.setData(article);
        return s;
    }

    //导入文章的excel，处理数据; 没有定制逻辑，只能自己用
    @PostMapping("importExcel")
    public Result importExcel(@RequestParam("file")MultipartFile file){
        List<Object> list = ExcelUtils.importExcel(file,Article.class);
        if(list != null) {
            System.out.println(list.toString());
            return new Result("0","成功",list);
        }
        return null;
    }

    @PostMapping("importExcel2")
    public Result importExcel2(@RequestParam("file")MultipartFile file) throws IOException {
        List<Object> list = new ArrayList<>();
        Gson gson = new Gson();
        Map<String,String> fieldExplain = new LinkedHashMap<>();
        fieldExplain.put("title","标题");
        fieldExplain.put("abstracts","摘要");
        fieldExplain.put("content","内容");
        Map<String,Object> field = new LinkedHashMap<>(16);
        Map<String,Integer> headIndex = new LinkedHashMap<>(16);
        AnalysisEventListener excelListener = new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                //当前读到的excel值
                List<String> excelList = gson.fromJson(gson.toJson(o), new TypeToken<List<String>>() {
                }.getType());
                System.out.println("当前行:" + analysisContext.getCurrentRowNum());
                System.out.println(excelList);
                if(analysisContext.getCurrentRowNum() == 0){
                    for (int i = 0;i< excelList.size();i++) {
                        for (Map.Entry<String, String> entry : fieldExplain.entrySet()
                                ) {
                              if (entry.getValue().equals(excelList.get(i))){
                                  field.put(entry.getKey(),entry.getValue());
                                  headIndex.put(entry.getKey(),i);
                              }
                        }
                    }
                }else {
                    for (Map.Entry<String,Object> entry: field.entrySet()
                         ) {
                        for (Map.Entry<String,Integer> headEntry:headIndex.entrySet()
                             ) {
                            if(entry.getKey().equals(headEntry.getKey())){
                                field.replace(entry.getKey(),excelList.get(headEntry.getValue()));
                            }
                        }
                    }
                Article article = gson.fromJson(gson.toJson(field),new TypeToken<Article>(){}.getType());

//                list.add(o);
                doSomething(article);
                }
            }
            public void doSomething(Article o){
                //入库等操作，方法可设计
                System.out.println(o.toString());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            list.clear();
            log.info("---------------------------------------------clear success");
            }
        };
        EasyExcelFactory.readBySax(file.getInputStream(),new Sheet(1,0),excelListener);

        if(list != null) {
            System.out.println(list.toString());
            return new Result("0","成功",list);
        }
        return null;
    }

    @GetMapping("downloadExcel")
    public Result downloadExcel(HttpServletResponse httpResponse)throws IOException {
        ServletOutputStream outputStream = httpResponse.getOutputStream();
        List<Article> list = new ArrayList<>();
        list.add(new Article(1L,"哈哈1","传参1","吃药1",new Date()));
        list.add(new Article(2L,"哈哈2","传参2","吃药2",new Date(2017,1,2)));
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null,outputStream,ExcelTypeEnum.XLSX,true,null);
        String filename = new String(("Article" + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"iso8859-1");
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setHeader("Content-disposition","attachment;filename=" + filename + ".xlsx");
        Sheet sheet = new Sheet(1,1,Article.class);
        sheet.setSheetName("第一个sheet");
        writer.write(list,sheet);
        writer.finish();
//        httpResponse.setContentType("multipart/form-data");

        outputStream.flush();
//        List<Article> list = new ArrayList<>();
//        list.add(new Article(1L,"哈哈1","传参1","吃药1",new Date()));
//        list.add(new Article(2L,"哈哈2","传参2","吃药2",new Date(17,1,2)));
//        ExcelUtils.excelExport(httpResponse,"文章实体",list,Article.class,null);
        return new Result("0","成功",list);
    }

    @GetMapping("downloadExcel2")
    public Result downloadExcel2(HttpServletResponse httpServletResponse){
                List<Article> list = new ArrayList<>();
        list.add(new Article(1L,"哈哈1","传参1","吃药1",new Date()));
        list.add(new Article(2L,"哈哈2","传参2","吃药2",new Date(17,1,2)));
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("标题","title");
        linkedHashMap.put("内容","content");
        linkedHashMap.put("摘要","abstracts");
        ExcelUtils.excelExport(httpServletResponse,"文章实体",linkedHashMap,list,Article.class,null);

        return new Result("0","成功",list);
    }
}
