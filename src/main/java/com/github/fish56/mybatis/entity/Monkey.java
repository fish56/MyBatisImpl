package com.github.fish56.mybatis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class Monkey {
    private Integer id;
    private String name;
    private Integer phoneNumber;
    private Date birthday;
}
