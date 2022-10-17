package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Follow;

public class FollowConverter {
    /**
     * フォロー社員データのDTOモデル⇔Viewモデルの変換を行うクラス
     *
     */

    public static Follow toModel(FollowView fv) {
        return new Follow(
                fv.getId(),
                EmployeeConverter.toModel(fv.getFollowed()),
                EmployeeConverter.toModel(fv.getFollower()),
                fv.getCreatedAt());

    }

    public static FollowView toView(Follow f) {
        if(f == null) {
           return null;
        }
        return new FollowView(
                f.getId(),
                EmployeeConverter.toView(f.getFollowed()),
                EmployeeConverter.toView(f.getFollower()),
                f.getCreatedAt());
    }

    public static List<FollowView> toViewList(List<Follow> list) {
        List<FollowView> evs = new ArrayList<>();

        for(Follow f : list) {
            evs.add(toView(f));
        }

        return evs;
    }

    public static void copyViewModel(Follow f, FollowView fv) {
        f.setId(fv.getId());
        f.setFollowed(EmployeeConverter.toModel(fv.getFollowed()));
        f.setFollower(EmployeeConverter.toModel(fv.getFollower()));
        f.setCreatedAt(fv.getCreatedAt());

    }
}
