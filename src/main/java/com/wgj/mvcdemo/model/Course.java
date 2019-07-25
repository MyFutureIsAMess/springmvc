package com.wgj.mvcdemo.model;

import java.util.List;

/**
 * @Author Guojian Wang
 * @Date 2019/7/25 13:08
 */
public class Course {

    private Integer courseId;

    private String title;

    private String imgPath;

    private Integer learningNum;

    private Long duration;

    private Integer level;

    private String levelDesc;

    private String descr;

    private List<Chapter> chapterList;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getLearningNum() {
        return learningNum;
    }

    public void setLearningNum(Integer learningNum) {
        this.learningNum = learningNum;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
