package com.jarry.demo1.java3y.ProxyMode.DesignMode.BuilderPa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.BuilderPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-27 09:51
 */
public class MessageTask {
    private String taskId;
    private String content;
    private String messageId;
    private String taskName;

    //增加private的构造函数
    private MessageTask(Builder builder){
        this.taskId = builder.taskId;
        this.content = builder.content;
        this.messageId = builder.messageId;
        this.taskName = builder.taskName;
    }
    //创建内部类(静态的)
    public static class Builder{
        private String taskId;
        private String content;
        private String messageId;
        private String taskName;

        public Builder setTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setMessageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setTaskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public MessageTask build(){
            return new MessageTask(this);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageTask{");
        sb.append("taskId='").append(taskId).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", messageId='").append(messageId).append('\'');
        sb.append(", taskName='").append(taskName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
