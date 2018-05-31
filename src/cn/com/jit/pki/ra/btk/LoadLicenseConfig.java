//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.com.jit.pki.ra.btk;

import cn.com.jit.license.IDAFormula;
import cn.com.jit.license.LicenseException;
import cn.com.jit.pki.ra.btk.LicenseVerifiyException;

public class LoadLicenseConfig {
    private static LoadLicenseConfig instance;
    private String licsoftname;
    private String licDate;
    private String licCode;
    private String licDeployment;
    private String licHaveChild;

    public String getLicsoftname() {
        return this.licsoftname;
    }

    public void setLicsoftname(String licsoftname) {
        this.licsoftname = licsoftname;
    }

    public String getLicDate() {
        return this.licDate;
    }

    public void setLicDate(String licDate) {
        this.licDate = licDate;
    }

    public String getLicCode() {
        return this.licCode;
    }

    public void setLicCode(String licCode) {
        this.licCode = licCode;
    }

    public String getLicDeployment() {
        return this.licDeployment;
    }

    public void setLicDeployment(String licDeployment) {
        this.licDeployment = licDeployment;
    }

    public String getLicHaveChild() {
        return this.licHaveChild;
    }

    public void setLicHaveChild(String licHaveChild) {
        this.licHaveChild = licHaveChild;
    }

    private LoadLicenseConfig(String fileroad, String pcCode) throws LicenseVerifiyException {
        if(fileroad == null || "".equals(fileroad)) {
            fileroad = "./license.lce";
        }

        IDAFormula licenseAPI = null;

        try {
            licenseAPI = IDAFormula.getInstance(fileroad);
        } catch (Exception var10) {
            throw new LicenseVerifiyException("85010007", " License LOADING err: Loading the license file is failed !", var10);
        }

        try {
            this.licsoftname = licenseAPI.getSystemValue1();
        } catch (LicenseException var9) {
            throw new LicenseVerifiyException("85010001", " License LOADING err: Reading the softname of the license is failed !", var9);
        }

        try {
            this.licDate = licenseAPI.getSystemValue3();
        } catch (LicenseException var8) {
            //throw new LicenseVerifiyException("85010002", " License LOADING err: Reading the date of the license is failed !", var8);
        }

        try {
            this.licCode = licenseAPI.getCustomValue("限制码");
        } catch (LicenseException var7) {
            throw new LicenseVerifiyException("85010003", " License LOADING err: Reading the limitative code of the license is failed !", var7);
        }

        if(this.licCode != null && !"".equals(this.licCode)) {
            if(!pcCode.equals(this.licCode) && !"7vGcVDBtqmntpJwCcmI7214rNB8=".equals(this.licCode)) {
                throw new LicenseVerifiyException("85020003", " License Verifying err: License file error,the limitative code is wrong, please use the correct license file !  ");
            } else {
                try {
                    this.licDeployment = licenseAPI.getCustomValue("部署地");
                } catch (LicenseException var6) {
                    throw new LicenseVerifiyException("85010004", " License LOADING err: Reading the deployment of the license is failed !", var6);
                }

                if(this.licDeployment != null && !"".equals(this.licDeployment)) {
                    try {
                        this.licHaveChild = licenseAPI.getCustomValue("包含下级");
                    } catch (LicenseException var5) {
                        throw new LicenseVerifiyException("85010005", " License LOADING err: Reading the subordinate unit of the license is failed !", var5);
                    }

                    if(this.licHaveChild == null || "".equals(this.licHaveChild)) {
                        throw new LicenseVerifiyException("85010005", " License LOADING err: Reading the subordinate unit of the license is failed !");
                    }
                } else {
                    throw new LicenseVerifiyException("85010004", " License LOADING err: Reading the deployment of the license is failed !");
                }
            }
        } else {
            throw new LicenseVerifiyException("85010003", " License LOADING err: Reading the limitative code of the license is failed !");
        }
    }

    public void verify(String machineCode) throws LicenseVerifiyException {
        try {
            boolean ex = true;

            try {
                if(this.licDate == null || this.licDate.trim().equals("") || this.licDate.equalsIgnoreCase("NULL")) {
                    ex = false;
                }
            } catch (Exception var4) {
                throw new LicenseVerifiyException("85020002", " License Verifying err: License file has expired, please use the correct license file !", var4);
            }

            if(ex && Long.decode(this.licDate).longValue() < System.currentTimeMillis() / 1000L) {
                //throw new LicenseVerifiyException("85020002", " License Verifying err: License file has expired, please use the correct license file !");
            }
        } catch (LicenseVerifiyException var5) {
            instance = null;
            throw var5;
        }
    }

    public static LoadLicenseConfig getInstance(String fileroad, String pcCode) throws LicenseVerifiyException {
        if(instance == null) {
            Class var2 = LoadLicenseConfig.class;
            synchronized(LoadLicenseConfig.class) {
                if(instance == null) {
                    try {
                        instance = new LoadLicenseConfig(fileroad, pcCode);
                    } catch (LicenseVerifiyException var4) {
                        instance = null;
                        throw var4;
                    }
                }
            }
        }

        return instance;
    }
}
