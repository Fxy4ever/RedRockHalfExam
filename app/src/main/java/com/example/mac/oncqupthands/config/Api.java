package com.example.mac.oncqupthands.config;

/**
 * Created by mac on 2018/5/25.
 */

public class Api {

    // TODO: 2018/5/25 用户部分
    /**
     * 验证用户信息
     */
    public static String verify="https://wx.idsbllp.cn/api/verify";
    //1.stuNum 2.idNum身份证后六位

    /**
     * 获取用户信息
     */
    public static String search = "https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/search";
    //1.stuNum 2.idNum身份证后六位


    // TODO: 2018/5/25 问题部分

    /**
     * 添加新问题
     */
    public static String add ="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/add";
    //1.stuNum 2.idNum 3.title 4.description 5.is_anonymous 0不匿名1匿名
    //6.kind问题类型 填写情感等几个大标题7.tags标签 8.reward奖励分数 最低为1 9.disappear_time

    /**
     * 获取问题列表
     */
    public static String getQuestionList="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList";
    //1.page 2.size 3.kind

    /**
     * 取消提问
     */
    public static String cancelQuestion="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/cancelQuestion";
    //1.stuNum 2.idNum 3.question_id

    /**
     * 获得问题详情
     */
    public static String getDetailedInfo="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getDetailedInfo";
    //1.stuNum 2.idNum 3.question_id

    /**
     * 问题图片上传
     */

    public static String uploadPic="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/uploadPicture";
    //1.stuNum 2.idNum 3.question_id 4.photo1 5.photo2

    // TODO: 2018/5/25 答案部分

    /**
     * 回答问题
     */
    public static String answerQ="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/add";
    //1.stuNum 2.idNum 3.content 4.question_id

    /**
     * 获取回答列表补充
     */
    public static String getAnswerList = "https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/getAnswerlist";
    //1.stuNum 2.idNum 3.page 4.size 5.question_id

    /**
     * 点赞
     */
    public static String praise = "https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/praise";
    //1.stuNum 2.idNum 3.answer_id

    /**
     * 取消点赞
     */
    public static String cancelPraise ="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/cancelPraise";
    //1.stuNum 2.idNum 3.answer_id

    /**
     * 获取评论列表
     */
    public static String getRemarkList="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/getRemarkList";
    //1.stuNum 2.idNum 3.answer_id

    /**
     * 评论答案
     */
    public static String remark="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/remark";
    //1.stuNum 2.idNum 3.answer_id 4.content

    /**
     * 采纳问题
     */
    public static String adopt="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/adopt";
    //1.stuNum 2.idNum 3.answer_id 4.question_id

    /**
     * 答案图片上传
     */
    public static String uploadAnswerQ ="https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Answer/uploadPicture";
    //1.stuNum 2.idNum 3.answer_id 4.photo1 5.photo2


    // TODO: 2018/5/25 课表部分
    /**
     * 课表查询
     */
    public static String kebiao = "https://wx.idsbllp.cn/api/kebiao";
    //1.stu_num 2.forceFetch true从教务在线 false从服务器

    /**
     * 获取备忘
     */
    public static String getTransaction ="https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/getTransaction";
    //1.stuNum 2.idNum

    /**
     * 添加备忘
     */
    public static String addTransaction ="https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/addTransaction";
    //1.stuNum 2.idNum 3.time提前几分钟提醒 4.title 5.content 6.id 7.date
    //id:System.currentTimeMillis() + "" + (new Random().nextInt(9999 - 1000 + 1) + 1000)
    /**
     * 编辑备忘
     */
    public static String ChangeTransaction ="https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/addTransaction";

    /**
     * 删除备忘
     */
    public static String deleteTransaction = "https://wx.idsbllp.cn/cyxbsMobile/index.php/Home/Person/deleteTransaction";
    //1.stuNum 2.idNum 3.id

}
