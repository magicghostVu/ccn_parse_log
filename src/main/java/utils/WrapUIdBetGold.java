package utils;

/**
 * Created by Fresher on 16/09/2017.
 */
public class WrapUIdBetGold {
    private int uid;
    private long betGold;
    private long goldWhenCreateGame;

    public WrapUIdBetGold(int uid, long betGold, long goldWhenCreateGame) {
        this.uid = uid;
        this.betGold = betGold;
        this.goldWhenCreateGame = goldWhenCreateGame;
    }

    public int getUid() {
        return uid;
    }

    public long getBetGold() {
        return betGold;
    }

    public long getGoldWhenCreateGame() {
        return goldWhenCreateGame;
    }
}
