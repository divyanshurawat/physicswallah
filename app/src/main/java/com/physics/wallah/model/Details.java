package com.physics.wallah.model;

import java.util.ArrayList;
import java.util.List;


    public class Details {
        private Integer id;
        private String name;
        private List<String> subjects = new ArrayList<String>();
        private List<String> qualification = new ArrayList<String>();
        private String profileImage;


        public Details (Integer id, String name , List<String> subjects,List<String> qualification, String profileImage){

            this.id = id;
            this.name =name;
            this.profileImage =profileImage;
            this.qualification = qualification;
            this.subjects = subjects;


        }        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<String> getSubjects() {
            return subjects;
        }
        public void setSubjects(List<String> subjects) {
            this.subjects = subjects;
        }
        public List<String> getQualification() {
            return qualification;
        }
        public void setQualification(List<String> qualification) {
            this.qualification = qualification;
        }
        public String getProfileImage() {
            return profileImage;
        }
        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }
    }