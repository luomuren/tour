package top.tmstarry.tour.scenicSpot.service.bean;

import top.tmstarry.tour.base.service.bean.Base;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ScenicSpot
 * @Author 落幕人
 * <p>
 * ScenicSpot实体类
 * <p>
 * @Date 2019/11/5 9:42
 * @Version 1.0
 **/
public class ScenicSpot extends Base {
    private Integer scenicSpotId;

    private String scenicSpotName;

    private String scenicSpotContent;

    private String scenicSpotImg;

    private String address;

    private Integer classId;

    private String className;

    @Override
    public String toString() {
        return "ScenicSpot{" +
                "scenicSpotId=" + scenicSpotId +
                ", scenicSpotName='" + scenicSpotName + '\'' +
                ", scenicSpotContent='" + scenicSpotContent + '\'' +
                ", scenicSpotImg='" + scenicSpotImg + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public Date getCreatTime() {
        return super.getCreatTime();
    }

    @Override
    public void setCreatTime(Date creatTime) {
        super.setCreatTime(creatTime);
    }

    @Override
    public Date getUpTime() {
        return super.getUpTime();
    }

    @Override
    public void setUpTime(Date upTime) {
        super.setUpTime(upTime);
    }

    @Override
    public String getCreatUser() {
        return super.getCreatUser();
    }

    @Override
    public void setCreatUser(String creatUser) {
        super.setCreatUser(creatUser);
    }

    @Override
    public String getUpUser() {
        return super.getUpUser();
    }

    @Override
    public void setUpUser(String upUser) {
        super.setUpUser(upUser);
    }

    public Integer getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Integer scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName;
    }

    public String getScenicSpotContent() {
        return scenicSpotContent;
    }

    public void setScenicSpotContent(String scenicSpotContent) {
        this.scenicSpotContent = scenicSpotContent;
    }

    public String getScenicSpotImg() {
        return scenicSpotImg;
    }

    public void setScenicSpotImg(String scenicSpotImg) {
        this.scenicSpotImg = scenicSpotImg;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
