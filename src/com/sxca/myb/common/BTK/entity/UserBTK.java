package com.sxca.myb.common.BTK.entity;

/**
 * BTK5066个人用户信息封装类
 * Created by admin on 2017/3/23.
 */
public class UserBTK extends CertBaseBTK{
    private String userInfoId;  //用户ID
    private String certType;    //证书用户类型
    private String organId;     //机构ID
    private String username;    //用户名字
    private String idtype;      //证件类型
    private String useridcardnum;   //证件号码
    private String phonenum;        //电话
    private String usercontactaddr;//地址
    private String org;             //单位名称
    private String fax;             //传真
    private String userzipcode;    //邮编
    private String usercountry;    //国家
    private String column_WD9Z;    //序列号
    private String email;           //邮件

    public UserBTK(){

    }

    public UserBTK(String userInfoId, String certType, String organId, String username, String idtype, String useridcardnum, String phonenum,
                        String usercontactaddr, String org, String fax, String userzipcode, String usercountry, String column_WD9Z, String email) {
        this.userInfoId = userInfoId;
        this.certType = certType;
        this.organId = organId;
        this.username = username;
        this.idtype = idtype;
        this.useridcardnum = useridcardnum;
        this.phonenum = phonenum;
        this.usercontactaddr = usercontactaddr;
        this.org = org;
        this.fax = fax;
        this.userzipcode = userzipcode;
        this.usercountry = usercountry;
        this.column_WD9Z = column_WD9Z;
        this.email = email;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getUseridcardnum() {
        return useridcardnum;
    }

    public void setUseridcardnum(String useridcardnum) {
        this.useridcardnum = useridcardnum;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getUsercontactaddr() {
        return usercontactaddr;
    }

    public void setUsercontactaddr(String usercontactaddr) {
        this.usercontactaddr = usercontactaddr;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUserzipcode() {
        return userzipcode;
    }

    public void setUserzipcode(String userzipcode) {
        this.userzipcode = userzipcode;
    }

    public String getUsercountry() {
        return usercountry;
    }

    public void setUsercountry(String usercountry) {
        this.usercountry = usercountry;
    }

    public String getColumn_WD9Z() {
        return column_WD9Z;
    }

    public void setColumn_WD9Z(String column_WD9Z) {
        this.column_WD9Z = column_WD9Z;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
