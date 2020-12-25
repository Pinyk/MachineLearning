package id3tree;

/**
 * @Author: gaoyk
 * @Date: 2020/12/22 18:59
 */
public class TreeNode {
    //父节点
    TreeNode parent;
    //指向父节点的属性
    String parentAttribute;
    String nodeName;
    String[] attributes;
    TreeNode[] childNodes;

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getParentAttribute() {
        return parentAttribute;
    }

    public void setParentAttribute(String parentAttribute) {
        this.parentAttribute = parentAttribute;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public TreeNode[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(TreeNode[] childNodes) {
        this.childNodes = childNodes;
    }
}
