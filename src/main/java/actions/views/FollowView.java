package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FollowView {
    //id
    private Integer id;
    //フォローを行う従業員
    private EmployeeView followed;
    //フォローされた従業員
    private EmployeeView follower;
    //フォロー日時
    private LocalDateTime createdAt;
    //フォローを外されたかどうか（フォロー中：0、削除済み：1）
    private Integer deleteFlag;
}
