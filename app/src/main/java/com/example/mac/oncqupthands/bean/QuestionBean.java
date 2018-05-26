package com.example.mac.oncqupthands.bean;

/**
 * Created by mac on 2018/5/25.
 */

public  class QuestionBean {
        /**
         * title : 又在写bug\\ue056
         * description : bug是真的多
         * kind : 其他
         * tags : PHP
         * reward : 2
         * answer_num : 0
         * disappear_at : 2019-02-27 01:11:20
         * created_at : 2018-05-19 17:35:14
         * is_anonymous : 0
         * id : 52
         * photo_thumbnail_src :
         * nickname : 。
         * gender : 女
         */

        private String title;
        private String description;
        private String kind;
        private String tags;
        private int reward;
        private int answer_num;
        private String disappear_at;
        private String created_at;
        private int is_anonymous;
        private int id;
        private String photo_thumbnail_src;
        private String nickname;
        private String gender;

    private QuestionBean(Builder builder) {
        setTitle(builder.title);
        setDescription(builder.description);
        setKind(builder.kind);
        setTags(builder.tags);
        setReward(builder.reward);
        setAnswer_num(builder.answer_num);
        setDisappear_at(builder.disappear_at);
        setCreated_at(builder.created_at);
        setIs_anonymous(builder.is_anonymous);
        setId(builder.id);
        setPhoto_thumbnail_src(builder.photo_thumbnail_src);
        setNickname(builder.nickname);
        setGender(builder.gender);
    }

    public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public int getAnswer_num() {
            return answer_num;
        }

        public void setAnswer_num(int answer_num) {
            this.answer_num = answer_num;
        }

        public String getDisappear_at() {
            return disappear_at;
        }

        public void setDisappear_at(String disappear_at) {
            this.disappear_at = disappear_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(int is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    public static final class Builder {
        private String title;
        private String description;
        private String kind;
        private String tags;
        private int reward;
        private int answer_num;
        private String disappear_at;
        private String created_at;
        private int is_anonymous;
        private int id;
        private String photo_thumbnail_src;
        private String nickname;
        private String gender;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder kind(String val) {
            kind = val;
            return this;
        }

        public Builder tags(String val) {
            tags = val;
            return this;
        }

        public Builder reward(int val) {
            reward = val;
            return this;
        }

        public Builder answer_num(int val) {
            answer_num = val;
            return this;
        }

        public Builder disappear_at(String val) {
            disappear_at = val;
            return this;
        }

        public Builder created_at(String val) {
            created_at = val;
            return this;
        }

        public Builder is_anonymous(int val) {
            is_anonymous = val;
            return this;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder photo_thumbnail_src(String val) {
            photo_thumbnail_src = val;
            return this;
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public QuestionBean build() {
            return new QuestionBean(this);
        }
    }
}
