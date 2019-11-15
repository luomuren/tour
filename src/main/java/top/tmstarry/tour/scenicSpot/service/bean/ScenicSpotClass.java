package top.tmstarry.tour.scenicSpot.service.bean;

import top.tmstarry.tour.base.service.bean.Base;

import java.util.Date;

public class ScenicSpotClass extends Base {
    private Integer classId;

    private String className;

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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}