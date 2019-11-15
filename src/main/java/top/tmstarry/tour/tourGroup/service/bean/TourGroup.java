package top.tmstarry.tour.tourGroup.service.bean;

import org.apache.ibatis.annotations.Mapper;
import top.tmstarry.tour.base.service.bean.Base;

import java.util.Date;

/**
 * @ClassName TourGroup
 * @Author 落幕人
 * <p>
 * 旅游团实体类
 * <p>
 * @Date 2019/11/6 10:18
 * @Version 1.0
 **/
@Mapper
public class TourGroup extends Base {
    private int tourGroupId;
    private String tourGroupName;
    private int lineId;
    private String lineName;
    private int guide;
    private String userName;
    private int tourGroupNum;
    private String tourGroupContent;
    private String tourGroupImg;
    private Date chufaTime;

    public Date getChufaTime() {
        return chufaTime;
    }

    public void setChufaTime(Date chufaTime) {
        this.chufaTime = chufaTime;
    }

    public String getTourGroupImg() {
        return tourGroupImg;
    }

    public void setTourGroupImg(String tourGroupImg) {
        this.tourGroupImg = tourGroupImg;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(int tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    public String getTourGroupName() {
        return tourGroupName;
    }

    public void setTourGroupName(String tourGroupName) {
        this.tourGroupName = tourGroupName;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getGuide() {
        return guide;
    }

    public void setGuide(int guide) {
        this.guide = guide;
    }

    public int getTourGroupNum() {
        return tourGroupNum;
    }

    public void setTourGroupNum(int tourGroupNum) {
        this.tourGroupNum = tourGroupNum;
    }

    public String getTourGroupContent() {
        return tourGroupContent;
    }

    public void setTourGroupContent(String tourGroupContent) {
        this.tourGroupContent = tourGroupContent;
    }
}
