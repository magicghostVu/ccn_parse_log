package pack;

import utils.FirstGameOfNewUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Fresher on 17/09/2017.
 */
public class DetectBankruptNewUser {


    static String newUserAction = "NewUser";

    static String startGameUserAction= "StartGameUser";

    public static void main(String[] args) throws IOException {
        Stream<Path> allPath = Files.list(Paths.get("./data_log"));
        List<String> allLine = allPath.flatMap(DetectBankruptNewUser::pathToAllString).collect(Collectors.toList());
        Stream<String> allLineNewUser = allLine.parallelStream().filter(line -> line.contains(newUserAction));
        Set<Integer> allUIdNewUser = allLineNewUser.map(DetectBankruptNewUser::lineToUId).collect(Collectors.toSet());



        List<String> allLineStartGameUser= allLine.parallelStream().filter(line->line.contains(startGameUserAction)).collect(Collectors.toList());


        Map<Integer, List<String>> mapStartGameForEachUser= allLineStartGameUser.stream().collect(Collectors.groupingBy(line->{
            String [] allParams= line.split("\\|");
            return Integer.parseInt(allParams[1]);
        }));


        System.out.println();



        /*List<FirstGameOfNewUser> allFirstOfNewUser= allUIdNewUser.parallelStream().map(uid->{

        })*/





        /*Optional<String> f = allLineNewUser.findFirst();

        f.ifPresent(System.out::println);*/

    }


    /*FirstGameOfNewUser findFirstGameOFNewUser(List<String> allLine, int uid){

    }*/




    private static int lineToUId(String line) {
        String[] allParams = line.split("\\|");
        return Integer.parseInt(allParams[1]);
    }

    private static Stream<String> pathToAllString(Path path) {
        try {
            return Files.lines(Paths.get(path.toString()));
        } catch (IOException ioe) {
            System.out.println("exception occur " + ioe.toString());
            return new ArrayList<String>().stream();
        }
    }


}
