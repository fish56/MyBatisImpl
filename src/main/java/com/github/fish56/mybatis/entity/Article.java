package com.github.fish56.mybatis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String content;

    // Monkey每个字段都是普通类型
    // 而Article的author属性则是Monkey类型的
    private Monkey author;
}
