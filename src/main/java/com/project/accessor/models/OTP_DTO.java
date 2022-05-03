package com.project.accessor.models;

import lombok.Data;

import java.util.Date;

@Data
public class OTP_DTO {
    private String userID;
    private String otp;
    private OTP_Type otpType;
    private String sentTo;
    private Date createdAt;
    private Date expiryAr;
    private OTPstatus otpStatus;
}
