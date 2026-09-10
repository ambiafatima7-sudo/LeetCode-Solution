/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int[] dfs(TreeNode root) {

        if (root == null) {
            return new int[]{0, 0};
        }

        // Left subtree
        int[] left = dfs(root.left);

        // Right subtree
        int[] right = dfs(root.right);

        // Sum of current subtree
        int sum = left[0] + right[0] + root.val;

        // Number of nodes
        int count = left[1] + right[1] + 1;

        // Check average
        if (root.val == sum / count) {
            ans++;
        }

        return new int[]{sum, count};
    }
}