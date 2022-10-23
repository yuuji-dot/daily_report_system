package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Employee;
import models.Follow;
import models.validators.FollowValidator;

public class FollowService extends ServiceBase{

    public List<FollowView> getMinePerPage(EmployeeView employee, int page){

        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_SPECIAL_EMPLOYEE, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return FollowConverter.toViewList(follows);
    }
    public List<EmployeeView> getAllPerPage(int page) {
        List<Employee> employees = em.createNamedQuery(JpaConst.Q_EMP_GET_ALL, Employee.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return EmployeeConverter.toViewList(employees);
    }

    /**
     * 指定した従業員が作成した日報データの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_FOL_SPECIAL_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    public List<String> create(FollowView fv, String pepper){
        LocalDateTime now = LocalDateTime.now();
        fv.setCreatedAt(now);

        List<String> errors = FollowValidator.validate(null, fv);

        if(errors.size() == 0){
            create(fv);
        }
        return errors;

    }

    /**
     * 指定した検索条件の氏名を取得し返却する
     *

    public String getName(String name){
        String namesearch = em.createNamedQury(JpaConst.Q_FOL_GET_NAME,);

        return namesearch;
    }

    public long getNumber(int number){
        long numbersearch = em.createNamedQuery(JpaConst.Q_FOL_GET_NUMBER,);

        return numbersearch;
    }*/

    /**
     * 指定されたページ数の一覧画面に表示する日報データを取得し、FollowViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<FollowView> getPerPage(int page) {

        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL, Follow.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return FollowConverter.toViewList(follows);
    }



    /**
     * フォローテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long follows_count = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT, Long.class)
                .getSingleResult();
        return follows_count;
    }

    public FollowView findOne(int id) {
        Follow f = findOneInternal(id);
        return FollowConverter.toView(f);
    }


    public void destroy(Integer id) {
        FollowView savedFol = findOne(id);
        savedFol.setDeleteFlag(JpaConst.EMP_DEL_TRUE);
    }


    /*public Boolean validatenumberSearch(String f_employee, String pepper) {
        boolean isValidEmployee = false;
        if(f_employee !=null && !f_employee.equals("")) {
            FollowView fv = findOne(f_employee,pepper);

            if(fv !=null && fv.getId() != null) {
                isValidEmployee = true;
            }
        }
        return isValidEmployee;
    }

    public Boolean validatenameSearch(String name, String pepper) {
        boolean isValidEmployee = false;
        if(name !=null && !name.equals(""));
            FollowView fv = findOne(name, pepper);
            if(fv !=null && fv.getId() !=null) {
                isValidEmployee = true;
            }
            return isValidEmployee;
}

    private FollowView findOne(String name, String pepper) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }*/
    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Follow findOneInternal(int id) {
        Follow f =em.find(Follow.class, id);

        return f;
    }


    private void create(FollowView fv) {

        em.getTransaction().begin();
        // nullチェック
        if(fv.getFollowed() == null || fv.getFollower() == null) {
          System.out.println("followかfollowedか、どっちも？nullやないかい！！ちゃんとインスタンス入れて呼んでよ！");
        }
        //toModeメソッド呼び出し。。。
        em.persist(FollowConverter.toModel(fv));
        em.getTransaction().commit();


    }

}



