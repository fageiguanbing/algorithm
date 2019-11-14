public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) {
        return null;
    }
    if (k <= 0) {
        return null;
    }
    if (k == 1) {
        return head;
    }
    ListNode toHead = new ListNode(0);
    toHead.next = head;
    ListNode temp = toHead;
    while (temp.next != null) {
        temp = reverseSingleK(temp, k);
    }
    return toHead.next;
}

public ListNode reverseSingleK(ListNode before, int k) {
    // 现在在前面的节点
    ListNode pre = before.next;
    // 在后面的节点
    ListNode cur = pre.next;
    // 保存已经反转的异常节点数量(需要再次反转为正序)
    int flag = 0;
    // 交换这 k 个节点
    for (int i = 1; i < k; i++) {
        if (cur == null) {
            flag = i;
            break;
        }
        // 保存下一个要改变next指针的节点
        ListNode temp = cur.next;
        // 反转
        cur.next = pre;
        // 实际上需要起到的效果为 before.next = cur
        pre = cur;
        // 移动当前指针到 下一个要改变next指针的节点
        cur = temp;
    }
    // 正常反转节点指针数量不满足 k 个,需要将已经反转的节点再次反转,保证这部分队尾的剩余节点顺序正常
    if (flag != 0) {
        ListNode ret = pre;
        cur = pre.next;
        for (int i = 2; i < flag; i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        ret.next = null;
        return ret;
    }
    // 将当前反转结果覆盖 before 引用
    else {
        // 反转后ret节点即为最后的节点
        ListNode ret = before.next;
        // 覆盖before
        before.next = pre;
        // 连接后续节点
        ret.next = cur;
        return ret;
    }
}
