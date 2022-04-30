public class Model {
    private Viewer viewer;
    private int [][] desktop;
    private int indexX;
    private int indexY;
    private Levels levels;
    private boolean isOk;
    private int[][] indexesGoal;
    private String direction;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        this.direction = "Down";
        this.levels = new Levels();
        initialization();
    }

    public boolean getStateGame() {
        return isOk;
    }

    private void initialization() {
        direction = "Down";
        isOk = true;
        desktop = levels.nextLevel();
        int countGamer = 0;
        int countBoxes = 0;
        int countGoals = 0;

        for (int a = 0; a < desktop.length; a++) {
            for (int b = 0; b < desktop[a].length; b++) {
                int element = desktop[a][b];
                if (element == 1) {
                    countGamer++;
                    indexX = a;
                    indexY = b;
                } else if (element == 3) {
                    countBoxes++;
                } else if (element == 4) {
                    countGoals++;
                }
            }
        }

        if (countGamer != 1) {
            isOk = false;
        }

        if ((countGoals == 0) || (countBoxes != countGoals)) {
            isOk = false;
        }

        if (levels.getLevel() == 2) {
            viewer.showStartMessage();
        }

        indexesGoal = new int[2][countGoals];

        int column = 0;
        for (int a = 0; a < desktop.length; a++) {
            for (int b = 0; b < desktop[a].length; b++) {
                if (desktop[a][b] == 4) {
                    indexesGoal[0][column] = a;
                    indexesGoal[1][column] = b;
                    column ++;
                }
            }
        }
    }

    public void move(String direction){
        switch (direction) {
            case ("Up"):
                this.direction = "Up";
                moveUp();
                break;
            case ("Right"):
                this.direction = "Right";
                moveRight();
                break;
            case ("Down"):
                this.direction = "Down";
                moveDown();
                break;
            case ("Left"):
                this.direction = "Left";
                moveLeft();
                break;
            case ("Restart"):
                this.direction = "Restart";
                restart();
                break;
            default:
                this.direction = "Down";
                return;
        }
        checkGoal();
        viewer.update();
        won();
    }

    private void checkGoal() {
        for (int column = 0; column < indexesGoal[0].length; column++) {
            int x = indexesGoal[0][column];
            int y = indexesGoal[1][column];
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4;
                break;
            }
        }
    }

    private void won() {
        boolean won = true;
        for (int j = 0; j < indexesGoal[0].length; j++) {
            int x = indexesGoal[0][j];
            int y = indexesGoal[1][j];
            if (desktop[x][y] != 3) {
                won = false;
                break;
            }
        }

        if (won){
            viewer.showWonMessage();
            initialization();
            viewer.update();
        }
    }

    private void restart() {
        levels.setLevel(levels.getLevel() - 1);
        initialization();
        viewer.update();
    }

    private void moveRight() {
        if((desktop[indexX][indexY + 1]) == 3 ){
            if ((desktop[indexX][indexY + 2]) == 0 || (desktop[indexX][indexY + 2]) == 4){
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if ((desktop[indexX][indexY + 1]) == 0 || (desktop[indexX][indexY + 1]) == 4) {
            desktop[indexX][indexY] = 0;
            indexY += 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveLeft() {
        if((desktop[indexX][indexY - 1]) == 3){
            if ((desktop[indexX][indexY - 2]) == 0 || desktop[indexX][indexY - 2] == 4){
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }

        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY -= 1;
            desktop[indexX][indexY] = 1;

        }
    }

    private void moveUp() {
        if((desktop[indexX - 1][indexY]) == 3){
            if ((desktop[indexX - 2][indexY]) == 0 || desktop[indexX - 2][indexY] == 4){
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }

        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX -= 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveDown() {
        if((desktop[indexX + 1][indexY]) == 3) {
            if ((desktop[indexX + 2][indexY]) == 0 || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }

        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX += 1;
            desktop[indexX][indexY] = 1;
        }
    }

    public int[][] getDesktop(){
        return desktop;
    }

    public String getDirection(){
        return direction;
    }
}

