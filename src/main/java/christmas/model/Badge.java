package christmas.model;

import static christmas.constant.Constant.GIVE_SANTA_BADGE;
import static christmas.constant.Constant.GIVE_STAR_BADGE;
import static christmas.constant.Constant.GIVE_TREE_BADGE;
import static christmas.constant.Constant.NONE;
import static christmas.constant.Constant.SANTA;
import static christmas.constant.Constant.STAR;
import static christmas.constant.Constant.TREE;

public class Badge {
    private String badgeType;
    public Badge() {
        this.badgeType = NONE;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeByCharge(int benefitCharge) {
        if(benefitCharge >= GIVE_SANTA_BADGE) {
            badgeType = SANTA;
            return;
        }
        if(benefitCharge >= GIVE_TREE_BADGE) {
            badgeType = TREE;
            return;
        }
        if(benefitCharge >= GIVE_STAR_BADGE) {
            badgeType = STAR;
        }
    }
}
