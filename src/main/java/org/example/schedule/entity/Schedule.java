package org.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String todo;
    private String username;
    private String password;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;



    public Schedule(String todo, String username, String password) {
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.createtime = LocalDateTime.now();
        this.updatetime = this.createtime;  // 초기 생성 시 수정일 = 생성일
    }

    public Schedule(long id, String todo, String username, LocalDateTime createtime, LocalDateTime updatetime) {
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public void updateTodo(String todo, String username, String password) {
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.updatetime = LocalDateTime.now(); // updatetime만 갱신
    }
}
