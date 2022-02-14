package two;

public class S86052 {


}

class Grid{
    private static final int STRAIGHT = 0;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private final int[][] modules;

    public Grid(String[] str){
        int rowSize = str.length;
        int columnSize = str[0].length();
        modules = new int[rowSize][columnSize];
        for(int i=0;i<rowSize;i++){
            String s = str[i];
            for(int j=0;j<columnSize;j++){
                switch(s.charAt(j)){
                    case 'S':
                        modules[i][j] = STRAIGHT;
                        break;
                    case 'R':
                        modules[i][j] = RIGHT;
                        break;
                    case 'L':
                        modules[i][j] = LEFT;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
