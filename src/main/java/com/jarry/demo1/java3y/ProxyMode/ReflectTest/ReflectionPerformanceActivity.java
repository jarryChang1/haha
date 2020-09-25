package com.jarry.demo1.java3y.ProxyMode.ReflectTest;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.ReflectTest
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-14 10:23
 *
 * 结论（1w次以上）：
 * 1、直接调用方法     1.4
 * 2、反射调用方法     6.2
 * 3、直接访问实例     1
 * 4、反射访问实例     3.75
 *
 * 反射慢的根本原因：getMethod和getDeclaredField方法会比invoke和set方法耗时
 * （可能在代码中需要很多逻辑判断：如安全检查、参数检查、找到这个class）
 */
//public class ReflectionPerformanceActivity extends Activity{
//    private TextView mExecuteResultTxtView = null;
//    private EditText mExecuteCountEditTxt = null;
//    private Executor mPerformanceExecutor = Executors.newSingleThreadExecutor();
//    private static final int AVERAGE_COUNT = 10;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reflection_performance_layout);
//        mExecuteResultTxtView = (TextView)findViewById(R.id.executeResultTxtId);
//        mExecuteCountEditTxt = (EditText)findViewById(R.id.executeCountEditTxtId);
//    }
//
//    public void onClick(View v){
//        switch(v.getId()){
//            case R.id.executeBtnId:{
//                execute();
//            }
//            break;
//            default:{
//
//            }
//            break;
//        }
//    }
//
//    private void execute(){
//        mExecuteResultTxtView.setText("");
//        mPerformanceExecutor.execute(new Runnable(){
//            @Override
//            public void run(){
//                long costTime = 0;
//                int executeCount = Integer.parseInt(mExecuteCountEditTxt.getText().toString());
//                long reflectMethodCostTime=0,normalMethodCostTime=0,reflectFieldCostTime=0,normalFieldCostTime=0;
//                updateResultTextView(executeCount + "毫秒耗时情况测试");
//                for(int index = 0; index < AVERAGE_COUNT; index++){
//                    updateResultTextView("第 " + (index+1) + " 次");
//                    costTime = getNormalCallCostTime(executeCount);
//                    reflectMethodCostTime += costTime;
//                    updateResultTextView("执行直接调用方法耗时：" + costTime + " 毫秒");
//                    costTime = getReflectCallMethodCostTime(executeCount);
//                    normalMethodCostTime += costTime;
//                    updateResultTextView("执行反射调用方法耗时：" + costTime + " 毫秒");
//                    costTime = getNormalFieldCostTime(executeCount);
//                    reflectFieldCostTime += costTime;
//                    updateResultTextView("执行普通调用实例耗时：" + costTime + " 毫秒");
//                    costTime = getReflectCallFieldCostTime(executeCount);
//                    normalFieldCostTime += costTime;
//                    updateResultTextView("执行反射调用实例耗时：" + costTime + " 毫秒");
//                }
//
//                updateResultTextView("执行直接调用方法平均耗时：" + reflectMethodCostTime/AVERAGE_COUNT + " 毫秒");
//                updateResultTextView("执行反射调用方法平均耗时：" + normalMethodCostTime/AVERAGE_COUNT + " 毫秒");
//                updateResultTextView("执行普通调用实例平均耗时：" + reflectFieldCostTime/AVERAGE_COUNT + " 毫秒");
//                updateResultTextView("执行反射调用实例平均耗时：" + normalFieldCostTime/AVERAGE_COUNT + " 毫秒");
//            }
//        });
//    }
//
//    private long getReflectCallMethodCostTime(int count){
//        long startTime = System.currentTimeMillis();
//        for(int index = 0 ; index < count; index++){
//            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
//            try{
//                Method setmLanguageMethod = programMonkey.getClass().getMethod("setmLanguage", String.class);
//                setmLanguageMethod.setAccessible(true);
//                setmLanguageMethod.invoke(programMonkey, "Java");
//            }catch(IllegalAccessException e){
//                e.printStackTrace();
//            }catch(InvocationTargetException e){
//                e.printStackTrace();
//            }catch(NoSuchMethodException e){
//                e.printStackTrace();
//            }
//        }
//
//        return System.currentTimeMillis()-startTime;
//    }
//
//    private long getReflectCallFieldCostTime(int count){
//        long startTime = System.currentTimeMillis();
//        for(int index = 0 ; index < count; index++){
//            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
//            try{
//                Field ageField = programMonkey.getClass().getDeclaredField("mLanguage");
//                ageField.set(programMonkey, "Java");
//            }catch(NoSuchFieldException e){
//                e.printStackTrace();
//            }catch(IllegalAccessException e){
//                e.printStackTrace();
//            }
//        }
//
//        return System.currentTimeMillis()-startTime;
//    }
//
//    private long getNormalCallCostTime(int count){
//        long startTime = System.currentTimeMillis();
//        for(int index = 0 ; index < count; index++){
//            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
//            programMonkey.setmLanguage("Java");
//        }
//
//        return System.currentTimeMillis()-startTime;
//    }
//
//    private long getNormalFieldCostTime(int count){
//        long startTime = System.currentTimeMillis();
//        for(int index = 0 ; index < count; index++){
//            ProgramMonkey programMonkey = new ProgramMonkey("小明", "男", 12);
//            programMonkey.mLanguage = "Java";
//        }
//
//        return System.currentTimeMillis()-startTime;
//    }
//
//    private void updateResultTextView(final String content){
//        ReflectionPerformanceActivity.this.runOnUiThread(new Runnable(){
//            @Override
//            public void run(){
//                mExecuteResultTxtView.append(content);
//                mExecuteResultTxtView.append("\n");
//            }
//        });
//    }
//}
