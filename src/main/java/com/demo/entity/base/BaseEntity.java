package com.demo.entity.base;

import com.demo.constants.BaseConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体父类
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1123888708106006594L;

    /**
     * 当前页
     */
    private Integer pageNum = BaseConstants.DEFAULT_PAGE_NUMBER;
    /**
     * 每页显示条数
     */
    private Integer pageSize = BaseConstants.PAGE_SIZE;

    /**
     * 创建时间
     * 表字段 : t_ad.create_date
     */
    private Date createDate;

    /**
     * 修改时间
     * 表字段 : t_ad.update_date
     */
    private Date updateDate;

    /**
     * 创建人
     * 表字段 : t_ad.create_user
     */
    private Integer createUser;

    /**
     * 修改人
     * 表字段 : t_ad.update_user
     */
    private Integer updateUser;

    /**
     * 创建人名称
     * 表字段 : t_ad.create_name
     */
    private String createName;

    /**
     * 修改人名称
     * 表字段 : t_ad.update_name
     */
    private String updateName;

    /**
     * 是否删除
     * 表字段 : t_ad.is_del
     */
    private String isDel = "0";

    /**
     * 创建时间开始日期[yyyy-MM-dd]
     * 此字段用于查询时使用，无须入库
     */
    private Date startDate;

    /**
     * 创建时间结束日期[yyyy-MM-dd]
     * 此字段用于查询时使用，无须入库
     */
    private Date endDate;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}