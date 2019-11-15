package top.tmstarry.tour.base.service.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName Base
 * @Author 落幕人
 * <p>
 * 基础实体类
 * <p>
 * @Date 2019/10/26 11:19
 * @Version 1.0
 **/
abstract public class Base {
    @ApiModelProperty(hidden = true)
    private Date creatTime = null;

    @ApiModelProperty(hidden = true)
    private Date upTime = null;

    @ApiModelProperty(hidden = true)
    private String creatUser = null;

    @ApiModelProperty(hidden = true)
    private String upUser = null;

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser;
    }

    public String getUpUser() {
        return upUser;
    }

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }
}
