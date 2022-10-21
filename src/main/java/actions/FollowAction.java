package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.FollowService;

/**
 * フォロー社員に関する処理を行うActionクラス
 *
 */
public class FollowAction extends ActionBase {

    private FollowService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * フォロー社員の一覧をトップ画面に表示する。
     */
    /**
     * 社員一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するフォロー社員データを取得
        int page = getPage();
        List<FollowView> follows = service.getPerPage(page);

        //全フォロー社員データの件数を取得
        long followsCount = service.countAll();

        putRequestScope(AttributeConst.FOLLOWS, follows); //取得したフォロー社員データ
        putRequestScope(AttributeConst.FOL_COUNT, followsCount); //全てのフォロー社員の件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_FOL_INDEX);
    }

    //全社員を一覧表示する
    public void search() throws ServletException, IOException{
        //指定されたページ数の一覧画面に表示するフォロー社員データを取得
        int page = getPage();
        List<FollowView> allemployee = service.getPerPage(page);

        long allCount =service.countAll();

        putRequestScope(AttributeConst.EMPLOYEES, allemployee);
        putRequestScope(AttributeConst.EMP_COUNT, allCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_FOL_SEARCH);
    }

    /**
     * 名前で検索する
     * @throws ServletException
     * @throws IOException
     */
    /*public void namesearch() throws ServletException, IOException{
        String namesearch =  service.getName(name);
        //指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<FollowView> follows = service.getPerPage(page);

        long followCount = service.countAll();

        putRequestScope(AttributeConst.FOL_NAME, namesearch);
        putRequestScope(AttributeConst.FOLLOWS, follows);
        putRequestScope(AttributeConst.FOL_COUNT, followCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
    forward(ForwardConst.FW_FOL_INDEX);

}*/
    /**
    public void namesearch() throws ServletException, IOException{
    String name = getRequestParam(AttributeConst.EMP_NAME);
    String pepper = getContextScope(PropertyConst.PEPPER);

    Boolean isValidEmployee = service.validatenameSearch(name, pepper);

    if(isValidEmployee) {
        if(checkToken()) {
            FollowView fv=service.findOne(name, pepper);
            putSessionScope(AttributeConst.EMP_NAME, fv);

        }else {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.FOL_FAILUE, true);

            forward(ForwardConst.FW_FOL_SEARCH);

        }
    }*/
    /*public void numbersearch() throws ServletException, IOException{
        String numbersearch = service.getNnmber(number);
        //指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<FollowView> follows = service.getPerPage(page);

        long followsCount = service.countAll();

        putRequestScope(AttributeConst.FOL_NUMBER, numbersearch);
        putRequestScope(AttributeConst.FOLLOWS, follows);
        putRequestScope(AttributeConst.FOL_COUNT, followsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
    forward(ForwardConst.FW_FOL_INDEX);
    }*/
    /**
    public void numbersearch() throws ServletException, IOException{
        String code = getRequestParam(AttributeConst.EMP_CODE);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効な従業員か確認する
        Boolean isValidEmployee = service.validatenumberSearch(code, pepper);

        if(isValidEmployee) {

            if(checkToken()) {
                FollowView fv=service.findOne(code,pepper);
                putSessionScope(AttributeConst.EMP_CODE, fv);

            }else {
                //検索失敗の場合
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.FOL_FAILUE, true);

                //検索結果画面を表示
                forward(ForwardConst.FW_FOL_SEARCH);
            }
        }
    }
    */
    /**
     *フォローを行う
     */

    public void create() throws ServletException, IOException{

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev =(EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        EmployeeView followedEmployee=(EmployeeView)getSessionScope(AttributeConst.FOL_FOLLOWED);
        //フォロー
        FollowView fv = new FollowView(
                null,
                ev,
                followedEmployee,
                null,
                null);
       //アプリケーションスコープからpepper文字列を取得
        String pepper = getContextScope(PropertyConst.PEPPER);

        List<String> errors = service.create(fv, pepper);


        if(errors.size() > 0) {
          //フォローが成功した場合
            putSessionScope(AttributeConst.FLUSH, MessageConst.F_SUCESS.getMessage());
        }else {
            //フォローが失敗した場合
            putSessionScope(AttributeConst.FLUSH, MessageConst.F_FAILUE.getMessage());

        }

        forward(ForwardConst.FW_FOL_TOP);
    }


    //フォローした社員の日報一覧を取得する
    public void f_index() throws ServletException, IOException{
        //フォローした社員の従業員情報を取得
        EmployeeView followerEmployee = (EmployeeView)getSessionScope(AttributeConst.FOL_FOLLOWED);

        int page =getPage();
        List<FollowView> fol_reports = service.getMinePerPage(followerEmployee,page);

        //フォローした従業員が作成した日報データの件数を取得
        long folReportsCount = service.countAllMine(followerEmployee);

        putRequestScope(AttributeConst.REPORTS, fol_reports); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, folReportsCount); //フォローした従業員が作成した日報の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        forward(ForwardConst.FW_FOL_REPORT);
    }

    //フォローを外す
    public void destroy() throws ServletException, IOException{
        if(checkToken()) {
            //idを条件にデータ論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.FOL_FOLLOWED)));

            //フォローを外したことに関するフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.F_OUT.getMessage());

            //トップ画面へ遷移
            forward(ForwardConst.FW_FOL_TOP);
        }
    }
}
