//package com.jarry.demo1.utils.ExcelUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @BelongsProject: demo1
// * @BelongsPackage: com.jarry.demo1.utils.ExcelUtils
// * @Author: Jarry.Chang
// * @CreateTime: 2019-11-14 15:36
// */
////上传utils
//@Slf4j
//public class ExcelUploadUtils {
//
//    /**
//     * 1.EXCEL 文件兼容2003版本 和 2007版本不区分版本上传  2.支持多个sheet一起上传  3.按照指定列名去取EXCEL中的数据列(Excel的列名必须和实体类列名相同)4.具备校验数据模板功能
//     * @param uploadDTO  已经上传(存放）至指定位置的EXCEL文件 --   路径 + 文件名
//     * @param describeProperty map集合   例如 ：User 对象 ，map 中 key 为 用户名  value 为字段名 userName 形如 map.put("用户名","userName")
//     * @param clazz    User.class
//     * @param propertyValue 这个map需要添加EXCEL中没有的字段的话     key 为userName vale 为admin  形如 map.put("password","123456")
//     * @param <T> List<User>
//     * @throws Exception
//     */
//    public static <T> List<ExcelDataDTO<T>> excelUpload(UploadDTO uploadDTO, Map<String, String> describeProperty, Class<T> clazz, HashMap<String, Object> propertyValue) throws IOException {
//        FileInputStream in=new FileInputStream(uploadDTO.getFile().getPath()+uploadDTO.getFile().getName());
//        // 添加判断是excel2003 excel 2007
//        Workbook workbook =null;
//        if(uploadDTO.getFile().getName().endsWith("xls")){
//            workbook	= new HSSFWorkbook(in);
//        }else{
//            workbook	= new XSSFWorkbook(in);
//        }
//        T tmp = null;
//        List<ExcelDataDTO<T>> list = new ArrayList<>();
//        HashMap<String,List<String>> lackMap=new HashMap<>();
//        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++){ //excel  处理页数 sheet
//            List<String> lackList=new ArrayList<>();//EXCEL中缺少的列
//            Sheet sheet = workbook.getSheetAt(numSheet);
//            if (sheet.getLastRowNum() == 0 &&  sheet.getPhysicalNumberOfRows() == 0) {
//                continue;
//            }
//            // 取标第一列 标题列  循环拿出需要的 列名+列序号
//            HashMap<Integer,String> needMap=new HashMap();
//            int order=-1;
//            Row firstRow = sheet.getRow(0);
//            if(null==firstRow){
//                log.debug("第"+(numSheet+1)+"个sheet表头没有位于第一行");
//                throw new ExcelException(CommonError.FIRST_CELL_NULL, "第"+(numSheet+1)+"个sheet表头没有位于第一行");
//            }
//
//            for (Cell titleCell : firstRow) {
//                order++;
//                for (String key : describeProperty.keySet()) {
//                    if(titleCell.getStringCellValue().equals(key)){
//                        needMap.put(order,describeProperty.get(key));
//                    }
//                }
//            }
//            //循环拿出缺项
//            for (String key : describeProperty.keySet()) {
//                boolean tag=false;
//                for (Cell titleCell : firstRow) {
//                    if(titleCell.getStringCellValue().equals(key)){
//                        tag=true;
//                    }
//                }
//                if (!tag){
//                    lackList.add(key);
//                }
//            }
//            if(lackList.size()>0){
//                lackMap.put("第"+(numSheet+1)+"个sheet缺少标题列",lackList);
//            }
//            if(needMap.size()!=describeProperty.size()){
//                continue;
//            }
//            // 循环行Row
//            Integer rowNumber = 0;
//            try {
//                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//                    rowNumber = rowNum + 1;
//                    ExcelDataDTO<T> data = new ExcelDataDTO();
//                    Row xssfRow = sheet.getRow(rowNum);
//                    data.setRowNum(rowNum);
//                    if (xssfRow != null) {
//                        tmp = clazz.newInstance();
//                        for (Integer a : needMap.keySet()) {
//                            Method m = clazz.getMethod(ReflectUtil.parSetName(needMap.get(a)), getClassType(needMap.get(a), clazz));
//                            if (null == xssfRow.getCell(a)) {
//                                m.invoke(tmp, "");//埋雷时刻：当反射的字段是日期类型时，你填个空字符串，几个意思，累了 不想改bug了 当你发现此处的问题时 你赢了
//                            }else{
//                                if (xssfRow.getCell(a).getCellType()== HSSFCell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(xssfRow.getCell(a))) {
//                                    Object val = DateUtil.getJavaDate(xssfRow.getCell(a).getNumericCellValue());
//                                    String classType = getClassType(needMap.get(a), clazz).getName();
//                                    if("java.lang.String".equals(classType))
//                                        val = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(val).toString();
//                                    m.invoke(tmp, val);
//                                }else{
//                                    //设置单元格格式为字符串格式
//                                    xssfRow.getCell(a).setCellType(Cell.CELL_TYPE_STRING);
//                                    //获取类型
//                                    String classType = getClassType(needMap.get(a), clazz).getName();
//                                    //获取对应格式的值
//                                    Object val = getRightVal(xssfRow.getCell(a).getStringCellValue(), classType);
//                                    //执行对应的方法将值注入
//                                    m.invoke(tmp, val);
//                                }
//                            }
//                            //设置Map中的值
//                            if(propertyValue!=null && propertyValue.size()>0){
//                                Iterator<String> iter = propertyValue.keySet().iterator();
//                                while (iter.hasNext()) {
//                                    String key = iter.next();
//                                    Method m1 = clazz.getMethod(ReflectUtil.parSetName(key), getClassType(key, clazz));
//                                    m1.invoke(tmp, propertyValue.get(key));
//                                }
//                            }
//
//                        }
//                        data.setObject(tmp);
//                        list.add(data);
//                    }
//                }
//            }catch (Exception e){
//                throw new BusinessException(BusinessError.DATA_UPLOAD_ERROR,"第"+rowNumber+"行数据有误");
//                //有啥问题就抛给上级
//            }
//        }
//
//        if(lackMap.size()>0){
//            log.debug("EXCEL上传异常"+lackMap.toString());
//            throw new ExcelException(CommonError.FILE_OUT_OF_SIZE, lackMap.toString());
//        }
//        return list;
//    }
//    //将文本值 转成对应类 需要的类型
//    private static Object getRightVal(String stringCellValue, String classType){
//
//        if("java.lang.Long".equals(classType)){
//            return Long.parseLong(stringCellValue);
//        }
//        if("java.lang.Integer".equals(classType)){
//            return Integer.valueOf(stringCellValue);
//        }
//        if("java.math.BigDecimal".equals(classType)){
//            if (stringCellValue.equals("")){
//                return new BigDecimal("0");
//            }
//            return new BigDecimal(stringCellValue);
//        }
//        if("java.util.Date".equals(classType)){
//            //指点江山：你把stringCellValue当做字符串取出来 如果
//            try {
//                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringCellValue);
//            } catch (ParseException e) {
//                try {
//                    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(stringCellValue);
//                } catch (ParseException e1) {
//                    try {
//                        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringCellValue);
//                    } catch (ParseException e2) {
//                        try {
//                            return new SimpleDateFormat("yyyyMMdd").parse(stringCellValue);
//                        } catch (ParseException e3) {
//                            throw new CommonException(CommonError.TIME_FORMAT_ERROR, "时间格式不对，请修改时间格式 标准参考格式：yyyy-MM-dd HH:mm:ss");
//                        }
//                    }
//                }
//            }
//        }
//        if("java.lang.Double".equals(classType)){
//            return Double.valueOf(stringCellValue);
//        }
//        return stringCellValue;
//    }
//
//    private static <T> Class<?> getClassType(String str, Class<T> clazz) {
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field f : fields) {
//            if(str.equals(f.getName())){
//                return f.getType();
//            }
//        }
//        return null;
//    }
//
//}
