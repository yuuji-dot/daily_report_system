package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
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
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するフォロー社員データを取得
        int page = getPage();
        List<FollowView> follows = service.getAllPerPage(page);

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

    /**
     *フォローを行う
     */

}
