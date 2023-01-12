public class Model {
    private Viewer viewer;
    private Levels levels;
    private int[][] desktop;
    private int[][] goalsIndexes;
    private int indexX;
    private int indexY;
    private boolean isOk;
    private int maximumLevel;

    public Model(Viewer viewer){
        this.viewer = viewer;
        isOk = true;
        levels = new Levels();
        desktop = levels.nextLevel();
        maximumLevel = 9;
        initialization();
    }

    private void initialization() {
        goalsIndexes = new int[desktop.length][desktop[0].length];
        for(int i = 0; i < desktop.length; i++) {
            for(int j = 0; j < desktop[i].length; j++) {
               goalsIndexes[i][j] = desktop[i][j];
            }
        }
        if(isErrorStatus()) {
            for (int i = 0; i < desktop.length; i++) {
                for (int j = 0; j < desktop[i].length; j++) {
                    if (desktop[i][j] == 1) {
                        indexX = i;
                        indexY = j;
                        desktop[indexX][indexY] = 1;
                    }
                }
            }
        }
    }

    private void checkGoal() {
        for(int i = 0; i < goalsIndexes.length; i++) {
            for(int j = 0; j < goalsIndexes[i].length; j++) {
                if (goalsIndexes[i][j] == 4) {
                    if(desktop[i][j] == 0) {
                        desktop[i][j] = 4;
                    }
                }
            }
        }
    }

    public boolean isErrorStatus() {
        int countGamer = 0;
        int countBoxes = 0;
        int countGoals = 0;
        for(int i = 0; i < goalsIndexes.length; i++) {
            for(int j = 0; j < goalsIndexes[i].length; j++) {
                if(goalsIndexes[i][j] == 1) {
                    countGamer = countGamer + 1;
                }
                if(goalsIndexes[i][j] == 3) {
                    countBoxes = countBoxes + 1;
                }
                if(goalsIndexes[i][j] == 4) {
                    countGoals = countGoals + 1;
                }
            }
        }
        if(countGamer != 1) {
            isOk = false;
        } else if (countBoxes != countGoals) {
            isOk = false;
        } else if(countGamer == 0 || countBoxes == 0) {
            isOk = false;
        }
        return isOk;
    }

    public void move(String direction) {
        if(direction.equals("Up")) {
            moveUp();
        } else if(direction.equals("Right")) {
            moveRight();
        } else if(direction.equals("Down")) {
            moveDown();
        } else if(direction.equals("Left"))  {
            moveLeft();
        } else {
            return;
        }
        checkGoal();
        viewer.update();
        won();
    }

    private void won() {
        boolean isWon = true;
        for(int i = 0; i < goalsIndexes.length; i++) {
            for (int j = 0; j < goalsIndexes[i].length; j++) {
                if(goalsIndexes[i][j] == 4) {
                    if(desktop[i][j] != 3) {
                        isWon = false;
                    }
                }
            }
        }

        if(isWon) {
            if(levels.getLevel() - 1 == maximumLevel) {
                viewer.showDialogEnd();
                System.exit(0);
            }
             if(viewer.showDialogWon()) {
               desktop = levels.nextLevel();
               initialization();
               viewer.update();
            }
        }
    }

    private void moveUp() {
        if(desktop[indexX - 1][indexY] == 3) {
            if(desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }
        if(desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveRight() {
        if(desktop[indexX][indexY + 1] == 3) {
            if(desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }
        if(desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY +1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveDown() {
        if(desktop[indexX + 1][indexY] == 3) {
            if(desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }
        if(desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveLeft() {
        if(desktop[indexX][indexY - 1] == 3) {
            if(desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }
        if(desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY -1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    public int[][] getDesktop() {
        return desktop;
    }

    public void doAction(String command) {
        switch (command) {
            case "level 1":
                levels.setLevel(1);
                break;
            case "level 2":
                levels.setLevel(2);
                break;
            case "level 3":
                levels.setLevel(3);
                break;
            case "level 4":
                levels.setLevel(4);
                break;
            case "level 5":
                levels.setLevel(5);
                break;
            case "level 6":
                levels.setLevel(6);
                break;
            case "level 7":
                levels.setLevel(7);
                break;
            case "level 8":
                levels.setLevel(8);
                break;
            case "level 9":
                levels.setLevel(9);
                break;
            case "restart":
                levels.setLevel(levels.getLevel() - 1);
                break;
            default:
                levels.setLevel(1);
                break;
        }
        desktop = levels.nextLevel();
        initialization();
        viewer.update();
    }
}
