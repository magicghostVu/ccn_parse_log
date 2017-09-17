package utils;

/**
 * Created by Fresher on 17/09/2017.
 */
public class FirstGameOfNewUser {
    private Integer uid;
    private Long timeStampStartFirstGame;
    private Long timeStampEndFirstGame;


    public FirstGameOfNewUser(Integer uid, Long timeStampStartFirstGame, Long timeStampEndFirstGame) {
        this.uid = uid;
        this.timeStampStartFirstGame = timeStampStartFirstGame;
        this.timeStampEndFirstGame = timeStampEndFirstGame;
    }

    public Integer getUid() {
        return uid;
    }

    public Long getTimeStampStartFirstGame() {
        return timeStampStartFirstGame;
    }

    public Long getTimeStampEndFirstGame() {
        return timeStampEndFirstGame;
    }
}
