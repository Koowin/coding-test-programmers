package two;

public class S68936 {
    public int[] solution(int[][] arr) {
        Zip z = new Zip(arr);
        z.findZipCount(0, 0, arr.length);
        return z.getAnswer();
    }

    static class Zip{
        private int count0 = 0;
        private int count1 = 0;
        private int[][] arr;

        private Zip(int[][] arr){
            this.arr = arr;
        }

        private void findZipCount(int row, int col, int size){
            if(!isZipable(row,col,size)){
                size /= 2;
                findZipCount(row, col, size);
                findZipCount(row, col + size, size);
                findZipCount(row + size, col, size);
                findZipCount(row + size, col + size, size);
            }
        }

        private boolean isZipable(int row, int col, int size){
            int k = arr[row][col];
            for(int i=row, len1 = row + size;i<len1;i++){
                for(int j=col, len2 = col + size;j<len2;j++){
                    if(arr[i][j] != k){
                        return false;
                    }
                }
            }
            if(k == 0){
                count0++;
            }
            else{
                count1++;
            }
            return true;
        }

        private int[] getAnswer(){
            return new int[] {count0, count1};
        }
    }
}
