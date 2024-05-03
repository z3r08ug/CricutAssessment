package com.z3r08ug.cricutassessment

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinaryTree {
    var root: TreeNode? = null

    fun insert(value: Int) {
        root = insertRec(root, value)
    }

    private fun insertRec(current: TreeNode?, value: Int): TreeNode {
        if (current == null) {
            return TreeNode(value)
        }

        if (value < current.value) {
            current.left = insertRec(current.left, value)
        } else if (value > current.value) {
            current.right = insertRec(current.right, value)
        }

        return current
    }

    fun inOrderTraversal(node: TreeNode?) {
        if (node != null) {
            inOrderTraversal(node.left)
            println(node.value)
            inOrderTraversal(node.right)
        }
    }
}
