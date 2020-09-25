package com.jarry.demo1.java3y.ProxyMode;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-02 17:27
 */
public class ProgrammerBigV implements Programmer {

    private Java3y java3y;

    public ProgrammerBigV(Java3y java3y){
        this.java3y = java3y;
    }

    public void upvote(){
        System.out.println("程序员大V点赞评论收藏转发!");
    }

    @Override
    public void coding() {
        //让java3y来写文章、发文章
        java3y.coding();
        //程序员大V点赞转发评论收藏！
        upvote();
    }
}
