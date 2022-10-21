package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォロー社員データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FOL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_FOL_GET_ALL,
            query = JpaConst.Q_FOL_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_COUNT,
            query = JpaConst.Q_FOL_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_SPECIAL_EMPLOYEE,
            query = JpaConst.Q_FOL_SPECIAL_EMPLOYEE_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_SPECIAL_COUNT,
            query = JpaConst.Q_FOL_SPECIAL_COUNT_DEF)

})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Follow {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローを行った従業員のid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_EMP, nullable = false)
    private Employee followed;

    /**
     * フォローされた従業員のID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_F_EMP, nullable = false)
    private Employee follower;


    /**
     * 登録日時
     */
    @Column(name = JpaConst.FOL_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.FOL_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;



    }


