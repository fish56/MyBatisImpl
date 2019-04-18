package dao;

import entity.Monkey;

public interface MonkeyDao {
    public Monkey getMonkeyById(Integer id);

    // both of follow methods work
    // public Integer insertMonkey(Monkey monkey);
    public Boolean insertMonkey(Monkey monkey);
}
