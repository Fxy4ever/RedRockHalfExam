package com.example.mac.oncqupthands.bean;

import java.util.List;

/**
 * Created by mac on 2018/5/25.
 */

public class AnswerBean {
        /**
         * id : 3
         * user_id : 2628
         * content : 测试
         * created_at : 2018-02-08 20:45:13
         * praise_num : 0
         * comment_num : 0
         * is_adopted : 0
         * photo_url : ["https://wx.idsbllp.cn/springtest/cyxbsMobile/Public/QA/Answer/5ab8bdb8c1470.jpg"]
         * photo_thumbnail_src : http://wx.idsbllp.cn/cyxbsMobile/Public/photo/thumbnail/1503374918_2132490885.png
         * nickname : 溟\\n濛
         * gender : 男
         */

        private String id;
        private String user_id;
        private String content;
        private String created_at;
        private String praise_num;
        private String comment_num;
        private String is_adopted;
        private String photo_thumbnail_src;
        private String nickname;
        private String gender;
        private List<String> photo_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(String praise_num) {
            this.praise_num = praise_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getIs_adopted() {
            return is_adopted;
        }

        public void setIs_adopted(String is_adopted) {
            this.is_adopted = is_adopted;
        }

        public String getPhoto_thumbnail_src() {
            return photo_thumbnail_src;
        }

        public void setPhoto_thumbnail_src(String photo_thumbnail_src) {
            this.photo_thumbnail_src = photo_thumbnail_src;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public List<String> getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(List<String> photo_url) {
            this.photo_url = photo_url;
        }
}
