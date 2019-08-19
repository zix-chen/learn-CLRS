public class leetcodeTest {
    public static void main(String[] args) {
        String[][] board = {
        {"5","3",".",".","7",".",".",".","."},
            {"6",".",".","1","9","5",".",".","."},
        {".","9","8",".",".",".",".","6","."},
    {"8",".",".",".","6",".",".",".","3"},
        {"4",".",".","8",".","3",".",".","1"},
        {"7",".",".",".","2",".",".",".","6"},
        {".","6",".",".",".",".","2","8","."},
        {".",".",".","4","1","9",".",".","5"},
        {".",".",".",".","8",".",".","7","9"}
    };
        leetcodeTest test = new leetcodeTest();
        test.isValidSudoku(board);
    }
    public boolean isValidSudoku(String[][] board) {
        int[][] arow = new int[9][9];
        int[][] aclo = new int[9][9];
        int[][] abox = new int[9][9];

        int ptr;
        int ptb;
        int[] ptc = new int[3];
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                ptb = 0;
                for(int t = 0;t<3;t++){
                    ptc[t] = 0;
                }
                for(int x = 0;x<3;x++){
                    ptr = 0;
                    for(int y = 0;y<3;y++){
                        if(!board[i*3+x][j*3+y].equals(".")){
                            char temp1 = board[i*3+x][j*3+y].charAt(0);
                            int temp = temp1-'0';
                            for(int k =0 ;k<ptb;k++){
                                if(abox[i*3+j][k]==temp) return false;
                            }
                            abox[i*3+j][ptb++] = temp;
                            arow[i*3+x][j*3+ptr] = temp;
                            ptr++;
                            aclo[i*3+ptc[y]][j*3+y] = temp;
                            ptc[y]++;
                        }
                    }
                }
            }
        }
        for(int t = 0;t<9;t++){
            for(int i =0;i<3;i++){
                for(int j =0;j<3;j++){
                    int key = arow[t][i*3+j];
                    if(key==0) break;
                    for(int x =0;x<3;x++){
                        if(x==i) continue;
                        for(int y = 0;y<3;y++){
                            int temp = arow[t][x*3+y];
                            if(temp==0)break;
                            else if(temp==key) return false;
                        }
                    }
                }
            }
            for(int i = 0;i<3;i++){
                for(int j=0;j<3;j++){
                    int key = aclo[i*3+j][t];
                    if(key==0) break;
                    for(int x= 0;x<3;x++){
                        if(x==i) continue;
                        for(int y=0;y<3;y++){
                            int temp = aclo[x*3+y][t];
                            //mind the relation of row and clo of aclo.
                            if(temp == 0) break;
                            else if(temp==key) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}