package com.groupmicrofin.moneycalc.moneycalculator.model;

import java.time.LocalDate;
import java.util.Date;


/**
 * Created by OMAR KOUL on 11/20/2017.
 */


public class SocietyAccount {
    private Long id;
    private Long societyMasterID;
    private String societyAccountId;
    private String memberName;
    private String emailId;
    private Number phoneNum;
    private String photoId;
    private Number pendingLoan;
    private String lastMeetingDate;
    private String alertDatetime;
    private String user;
    private boolean flgFirstMeeting;

    public boolean isFirstMeeting() {
        if (id != null) {
            if (lastMeetingDate == null) {
                flgFirstMeeting = true;
            }
        }
        return flgFirstMeeting;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Long getSocietyMasterID() {
        return societyMasterID;
    }

    public void setSocietyMasterID(Long societyMasterID) {
        this.societyMasterID = societyMasterID;
    }

    public String getSocietyAccountId() {
        return societyAccountId;
    }

    public void setSocietyAccountId(String societyAccountId) {
        this.societyAccountId = societyAccountId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;

    }
    public Number getPendingLoan() {
        return pendingLoan;
    }

    public void setPendingLoan(Number pendingLoan) {
        this.pendingLoan = pendingLoan;
    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Number getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Number phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj == null) {
            return false;
        }
        if (obj instanceof SocietyAccount) {
            SocietyAccount other = (SocietyAccount  ) obj;
            if (this.getId() == other.getId() && this.getSocietyAccountId().equals(other.getSocietyAccountId())) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "SocietyAccount{" +
                "id=" + id +
                ", societyMasterID=" + societyMasterID +
                ", societyAccountId='" + societyAccountId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", photoId='" + photoId + '\'' +
                ", pendingLoan=" + pendingLoan +
                ", lastMeetingDate='" + lastMeetingDate + '\'' +
                ", alertDatetime='" + alertDatetime + '\'' +
                ", user='" + user + '\'' +
                ", flgFirstMeeting=" + flgFirstMeeting +
                '}';
    }
}
