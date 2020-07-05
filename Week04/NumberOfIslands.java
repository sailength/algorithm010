/**
 * @author @sailength on 2020/7/6.
 *         200
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {

        if (grid.length == 0 || grid == null) {
            return 0;
        }
        int lengthy = grid.length;
        int lengthx = grid[0].length;

        int num = 0;
        for (int i = 0; i < lengthy; i++) {
            for (int j = 0; j < lengthx; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int x, int y) {
        if (grid.length == 0 || grid == null) {
            return;
        }
        int lengthy = grid.length;
        int lengthx = grid[0].length;
        if (x < 0 || y < 0 || x >= lengthx || y >= lengthy || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }
}
