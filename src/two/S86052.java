/*
    빛의 경로 사이클
    https://programmers.co.kr/learn/courses/30/lessons/86052
 */

package two;

import java.util.*;

class S86052 {
    public int[] solution(String[] grid){
        GridController gc = new GridController(grid);
        gc.inputLightAllModule();
        return gc.getAnswer();
    }
}

class GridController {
    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public static Direction reverse(Direction d){
            if(d == Direction.UP){
                return Direction.DOWN;
            }
            else if(d == Direction.DOWN){
                return Direction.UP;
            }
            else if(d == Direction.LEFT){
                return Direction.RIGHT;
            }
            else{
                return Direction.LEFT;
            }
        }
    }
    enum ModuleType {
        S, L, R
    }

    private Module[][] grid;
    private List<Integer> answer = new ArrayList<>();

    public GridController(String[] strArr){
        grid = new Module[strArr.length][strArr[0].length()];
        int i = 0;
        for(String str : strArr){
            int j = 0;
            char[] charArr = str.toCharArray();
            for(char c : charArr){
                grid[i][j++] = new Module(c);
            }
            i++;
        }
    }

    public int[] getAnswer(){
        Collections.sort(answer);
        int[] ret = new int[answer.size()];
        int index = 0;
        for(Integer i : answer){
            ret[index++] = i;
        }
        return ret;
    }

    public void inputLightAllModule(){
        int rowSize = grid.length;
        int colSize = grid[0].length;

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                inputLightAllDirection(i, j);
            }
        }
    }

    private void inputLightAllDirection(int row, int col){
        for(Direction input : Direction.values()) {
            int length = getLightCycleLength(row, col, input);
            if(length != 0){
                answer.add(length);
            }
        }
    }

    private int getLightCycleLength(int row, int col, Direction input){
        int length = 0;
        int nowRow = row;
        int nowCol = col;
        Direction nextDirection = grid[nowRow][nowCol].getOutDirection(input);
        while(nextDirection != null){
            length++;
            if(nextDirection == Direction.UP){
                nowRow--;
                if(nowRow < 0){
                    nowRow = grid.length - 1;
                }
            }
            else if(nextDirection == Direction.DOWN){
                nowRow++;
                if(nowRow >= grid.length){
                    nowRow = 0;
                }
            }
            else if(nextDirection == Direction.LEFT){
                nowCol--;
                if(nowCol < 0){
                    nowCol = grid[0].length - 1;
                }
            }
            else{
                nowCol++;
                if(nowCol >= grid[0].length){
                    nowCol = 0;
                }
            }
            nextDirection = grid[nowRow][nowCol].getOutDirection(Direction.reverse(nextDirection));
        }
        return length;
    }

    private static class Module {
        Map<Direction, Boolean> isEntered = new HashMap<>();
        private final ModuleType type;

        private Module(char c){
            if(c == 'S'){
                type = ModuleType.S;
            }
            else if (c == 'L'){
                type = ModuleType.L;
            }
            else{
                type = ModuleType.R;
            }

            for(Direction d : Direction.values()){
                isEntered.put(d, false);
            }
        }

        private Direction getOutDirection(Direction input){
            if(isEntered.get(input)){
                return null;
            }
            else{
                isEntered.put(input, true);
                return getOutDirection(input, this.type);
            }
        }

        private static Direction getOutDirection(Direction input, ModuleType type){
            if(type == ModuleType.S){
                if(input == Direction.UP){
                    return Direction.DOWN;
                }
                else if(input == Direction.DOWN){
                    return Direction.UP;
                }
                else if(input == Direction.LEFT){
                    return Direction.RIGHT;
                }
                else{
                    return Direction.LEFT;
                }
            }
            else if(type == ModuleType.L){
                if(input == Direction.UP){
                    return Direction.RIGHT;
                }
                else if(input == Direction.DOWN){
                    return Direction.LEFT;
                }
                else if(input == Direction.LEFT){
                    return Direction.UP;
                }
                else{
                    return Direction.DOWN;
                }
            }
            else{
                if(input == Direction.UP){
                    return Direction.LEFT;
                }
                else if(input == Direction.DOWN){
                    return Direction.RIGHT;
                }
                else if(input == Direction.LEFT){
                    return Direction.DOWN;
                }
                else{
                    return Direction.UP;
                }
            }
        }
    }
}