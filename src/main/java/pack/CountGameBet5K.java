package pack;

import utils.WrapUIdBetGold;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Fresher on 16/09/2017.
 */
public class CountGameBet5K {


    static String startGameUserAction = "StartGameUser";

    public static void main(String[] args) throws IOException {
        Stream<Path> allPathLogFiles = Files.list(Paths.get("./data_log"));
        List<WrapUIdBetGold> allCreateGame = allPathLogFiles.flatMap(path -> {
            //System.out.println(path.toString());
            try {
                Stream<String> allString = Files.lines(Paths.get(path.toString()))
                        .filter(line -> line.contains(startGameUserAction));
                System.out.println("a file had been processed");
                return allString.map(CountGameBet5K::parseBetGoldInAline);

            } catch (IOException ioe) {
                return null;
            }
        }).collect(Collectors.toList());


        System.out.println("All game created "+ allCreateGame.size());

        List<WrapUIdBetGold> allGame5K= allCreateGame.parallelStream().filter(CountGameBet5K::filterBetGold5K).collect(Collectors.toList());

        System.out.println("all Game 5k count "+ allGame5K.size());

        List<WrapUIdBetGold> allGameUserGoldLessThan30kIn= allGame5K.parallelStream().filter(CountGameBet5K::filterLessThan30k).collect(Collectors.toList());


        List<WrapUIdBetGold> allGameBetween30kAnd50K= allGame5K.parallelStream().filter(CountGameBet5K::filterBetWeen30kAnd50K).collect(Collectors.toList());


        List<WrapUIdBetGold> allGameBiggerThan50K= allGame5K.parallelStream().filter(CountGameBet5K::filterBiggerThan50K).collect(Collectors.toList());


        System.out.println("All game less than 30K " + allGameUserGoldLessThan30kIn.size());

        System.out.println("All game between than 30K " + allGameBetween30kAnd50K.size());


        System.out.println("all game bigger than 50k "+ allGameBiggerThan50K.size());
        //System.out.println();

    }

    public static boolean filterBetGold5K(WrapUIdBetGold wrapUIdBetGold) {
        return wrapUIdBetGold.getBetGold() == 5000L;
    }


    public static boolean filterLessThan30k(WrapUIdBetGold wrapUIdBetGold) {
        return wrapUIdBetGold.getGoldWhenCreateGame() <= 30000L;
    }

    public static boolean filterBetWeen30kAnd50K(WrapUIdBetGold wrapUIdBetGold) {
        return (wrapUIdBetGold.getGoldWhenCreateGame() > 30000L && wrapUIdBetGold.getGoldWhenCreateGame() <= 50000L);
    }

    public static boolean filterBiggerThan50K(WrapUIdBetGold wrapUIdBetGold) {
        return wrapUIdBetGold.getGoldWhenCreateGame() > 50000L;
    }

    //public static WrapUIdBetGold

    public static WrapUIdBetGold parseBetGoldInAline(String line) {
        if (!line.contains("StartGameUser")) throw new IllegalArgumentException("no action detected");
        String[] allParams = line.split("\\|");
        int uid = Integer.parseInt(allParams[1]);
        long gold = Long.parseLong(allParams[10]);
        long betGold = Long.parseLong(allParams[15]);
        WrapUIdBetGold res = new WrapUIdBetGold(uid, betGold, gold);
        return res;

    }


}
