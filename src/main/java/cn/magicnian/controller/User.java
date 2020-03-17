package cn.magicnian.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User extends BaseRequest{

    @NotNull
    private String userId;
    private String name;
    private String phone;
}
