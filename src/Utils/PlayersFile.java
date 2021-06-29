package Utils;

import Players.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class PlayersFile extends FileRead{
    private String fileName = "data";

    public void addNewPlayer(Player player) throws IOException {
        String line = player.nickname + "^" + player.timeZone + "^" + player.credits + "^" + player.matchesPlayed;
        writeLine(fileName, line);
    }

    public int existingPlayer(String nickname) throws FileNotFoundException { //-1 if doesn't exist
        int line = 0;
        String currentLine;

        Scanner scanner = new Scanner(new File(fileName + ".txt"));

        do{
            currentLine = scanner.nextLine();

            if (currentLine.contains(nickname)){
                return line;
            } line++;
        } while (scanner.hasNextLine());

        return -1;

    }

    public Player getPlayer(String Nickname, int line) throws FileNotFoundException {
        if (line == -1)
            return null;

        Player player = new Player();

        String lineStr = readLine(fileName, line);
        Vector<Integer> indexes = new Vector<>();
        int index = lineStr.indexOf('^');
        indexes.add(index);

        do {
            index = lineStr.indexOf('^', index + 1);
            indexes.add(index);
        } while (index >= 0);

        player.nickname = lineStr.substring(0, indexes.elementAt(0));
        player.timeZone = lineStr.substring(indexes.elementAt(0)+1, indexes.elementAt(1));
        player.credits = Float.parseFloat(lineStr.substring(indexes.elementAt(1)+1, indexes.elementAt(2)));
        player.matchesPlayed = Integer.parseInt(lineStr.substring(indexes.elementAt(2)+1));

        return player;
    }

    public void savePlayer(String nickname, String timeZone, float credits, int matchesPlayed) throws IOException {
        int line = existingPlayer(nickname), curretLine = 0;
        StringBuilder lineStr = new StringBuilder();
        String originalLine;
        Scanner scanner = new Scanner(new File(fileName + ".txt"));

        do{
            originalLine = scanner.nextLine();

            if (curretLine == line){
                lineStr.append(nickname).append("^").append(timeZone).append("^").append(credits).append("^").append(matchesPlayed).append("\n");
            } else {
                lineStr.append(originalLine).append("\n");
            } curretLine++;
        } while (scanner.hasNextLine());

        overwriteFile(fileName, lineStr.toString());

    }

}
