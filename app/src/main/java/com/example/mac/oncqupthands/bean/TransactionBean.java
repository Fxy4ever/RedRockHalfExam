package com.example.mac.oncqupthands.bean;

import java.util.List;

/**
 * Created by mac on 2018/5/25.
 */

public class TransactionBean {
        /**
         * id : 15238659215111108
         * time : null
         * title : 学习
         * content :
         * updated_time : 2018-04-16 16:05:22
         * date : [{"class":2,"day":2,"week":[7]}]
         */

        private long id;
        private Object time;
        private String title;
        private String content;
        private String updated_time;
        private List<DateBean> date;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdated_time() {
            return updated_time;
        }

        public void setUpdated_time(String updated_time) {
            this.updated_time = updated_time;
        }

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public static class DateBean {
            /**
             * class : 2
             * day : 2
             * week : [7]
             */

            private int classX;
            private int day;
            private List<Integer> week;

            public int getClassX() {
                return classX;
            }

            public void setClassX(int classX) {
                this.classX = classX;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public List<Integer> getWeek() {
                return week;
            }

            public void setWeek(List<Integer> week) {
                this.week = week;
            }
        }
}
