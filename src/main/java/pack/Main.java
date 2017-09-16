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
public class Main {


    static String startGameUserAction = "StartGameUser";

    public static void main(String[] args) throws IOException {
        Stream<Path> allPathLogFiles = Files.list(Paths.get("./data_log"));
        List<WrapUIdBetGold> allCreateGame= allPathLogFiles.flatMap(path -> {
            //System.out.println(path.toString());
            try {
                 Stream<String> allString= Files.lines(Paths.get(path.toString()))
                         .filter(line-> line.contains(startGameUserAction));

                System.out.println("a file had been processed");

                 return allString.map(Main::parseBetGoldInAline);

            } catch (IOException ioe) {
                return null;
            }
        }).collect(Collectors.toList());


        System.out.println();

    }


    //public static WrapUIdBetGold

    public static WrapUIdBetGold parseBetGoldInAline(String line) {
        if (!line.contains("StartGameUser")) throw new IllegalArgumentException("no action detected");
        String[] allParams = line.split("\\|");
        int uid= Integer.parseInt(allParams[1]);
        long gold= Long.parseLong(allParams[10]);
        long betGold= Long.parseLong(allParams[15]);
        WrapUIdBetGold res= new WrapUIdBetGold(uid, betGold, gold);
        return res;

    }


}
