package top.tmstarry.tour.line.service.bean;

import top.tmstarry.tour.base.service.bean.Base;
import top.tmstarry.tour.scenicSpot.service.bean.ScenicSpot;

import java.util.Date;
import java.util.List;

public class Line extends Base {
    private int lineId;//线路ID

    private String lineName;//旅游团ID

    private List<ScenicSpot> scenicSpots;

    @Override
    public String toString() {
        return "Line{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", scenicSpots=" + scenicSpots +
                '}';
    }

    public List<ScenicSpot> getScenicSpots() {
        return scenicSpots;
    }

    public void setScenicSpots(List<ScenicSpot> scenicSpots) {
        this.scenicSpots = scenicSpots;
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

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {

        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
}
