package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private Monkey author;
    private String content;
}
