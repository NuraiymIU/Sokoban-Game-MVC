import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Levels {
    private int level;
    private final String hostName;
    private final int portNumber;
    public Levels() {
        level = 1;
        hostName = "194.152.37.7";
        portNumber = 4445;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }


    public int[][] nextLevel() {
        int[][] desktop = null;
        switch(level) {
            case 1:
                desktop = getFirstLevel();
                break;
            case 2:
                desktop = getSecondLevel();
                break;
            case 3:
                desktop = getThirdLevel();
                break;
            case 4:
                desktop = getFourthLevel();
                break;
            case 5:
                desktop = getFifthLevel();
                break;
            case 6:
                desktop = getSixthLevel();
                break;
            case 7:
                desktop = getLevelsFromServer("7");
                break;
            case 8:
                desktop = getLevelsFromServer("8");
                break;
            case 9:
                desktop = getLevelsFromServer("9");
                break;
            default:
                level = 1;
                desktop = getFirstLevel();
                break;
        }
        level = level + 1;
        return desktop;
    }
    // 0-empty place
    // 1-gamer
    // 2-wall
    // 3-box
    // 4-goal
    private int[][] getFirstLevel() {
        int[][] firstLevel = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 4, 0, 0, 4, 0, 2},
                {2, 0, 0, 0, 3, 3, 2, 2, 1, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 2, 2, 2, 2, 2, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return firstLevel;
    }

    private int[][] getSecondLevel() {
        int[][] secondLevel = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 4, 2, 3, 2, 0, 0, 2},
                {2, 0, 0, 2, 2, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 3, 0, 0, 2, 2, 2, 2},
                {2, 2, 2, 2, 0, 0, 2, 1, 0, 2},
                {2, 4, 4, 0, 0, 0, 3, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return secondLevel;
    }

    private int[][] getThirdLevel() {
        int[][] thirdLevel = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 2, 0, 0, 4, 2},
                {2, 0, 0, 0, 0, 2, 3, 2, 0, 2},
                {2, 0, 4, 0, 3, 0, 0, 2, 0, 2},
                {2, 0, 2, 2, 2, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 1, 0, 2, 0, 2},
                {2, 0, 3, 0, 2, 2, 0, 2, 0, 2},
                {2, 0, 0, 0, 4, 0, 0, 2, 3, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return thirdLevel;
    }

    private int[][] getFourthLevel() {
        int[][] fourthLevel = null;
        try {
            fourthLevel = nextFileLevel("levels/level4.sok");
        } catch (Exception exc) {
            this.level = 1;
            return getFirstLevel();
        }
        return fourthLevel;
    }

    private int[][] getFifthLevel() {
        int[][] fifthLevel = null;
        try {
            fifthLevel = nextFileLevel("levels/level5.sok");
        } catch (Exception exc) {
            this.level = 1;
            return getFirstLevel();
        }
        return fifthLevel;
    }

    private int[][] getSixthLevel() {
        int[][] sixthLevel = null;
        try {
            sixthLevel = nextFileLevel("levels/level6.sok");
        } catch (Exception exc) {
            this.level = 1;
            return getFirstLevel();
        }
        return sixthLevel;
    }

    private int[][] nextFileLevel(String fileName) throws Exception{
        File file = new File(fileName);
        String contentFile = getContentFile(file);
        int[][] desktop = convertStringIntoTwoDimensionArray(contentFile);
        return desktop;
    }

    private int[][] convertStringIntoTwoDimensionArray(String line) {
        int n = line.length();
        int row = 0;
        for(int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if(symbol == '\n') {
                row = row + 1;
            }
        }
        int[][] array = new int[row][];
        int column = 0;
        int index = 0;
        for(int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if(symbol != '\n') {
                column = column + 1;
            } else if(symbol == '\n') {
                array[index] = new int[column];
                index = index + 1;
                column = 0;
            }
        }
        row = 0;
        column = 0;
        for(int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if(symbol != '\n') {
                array[row][column] =Integer.parseInt("" + symbol);
                column = column + 1;
            } else if(symbol == '\n') {
                row = row + 1;
                column = 0;
            }
        }
        return array;
    }

    private String getContentFile(File file) throws Exception{
        try(FileInputStream in = new FileInputStream(file)) {
            int size = (int) file.length();
            char[] array = new char[size];
            int index = 0;
            int unicode;
            while((unicode = in.read()) != -1) {
                char symbol = (char) unicode;
                if(('0' <= symbol && symbol <= '4') || (symbol == '\n')) {
                    array[index] = symbol;
                    index = index + 1;
                }
            }
            String content = new String(array, 0, index);
            return content;
        } catch(FileNotFoundException fnfe) {
            throw new Exception("File Not Found Exception: " + fnfe);
        } catch(IOException ioe) {
            throw new Exception("Basic I/O Exception: " + ioe);
        }
    }

    private int[][] getLevelsFromServer(String level) {
        int[][] desktop = null;
        System.out.println("Server start");
        try (
                Socket socket = new Socket(hostName, portNumber);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            writer.write(level);
            writer.newLine();
            writer.flush();
            desktop = (int[][]) in.readObject();
        } catch (IOException ioe) {
            this.level = 1;
            return getFirstLevel();
        } catch (ClassNotFoundException cnfe) {
            this.level = 1;
            return getFirstLevel();
        }
        return desktop;
    }

}
