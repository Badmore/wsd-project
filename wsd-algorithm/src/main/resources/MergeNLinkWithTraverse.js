function mergeKLists(lists) {
    if (!lists || lists.length === 0) return null;
    return mergeKListsRecursively(lists, 0, lists.length - 1);
}

function mergeKListsRecursively(lists, left, right) {
    if (left === right) {
        return lists[left];
    }

    const mid = Math.floor((left + right) / 2);
    const leftList = mergeKListsRecursively(lists, left, mid);
    const rightList = mergeKListsRecursively(lists, mid + 1, right);

    return mergeTwoLists(leftList, rightList);
}

function mergeTwoLists(l1, l2) {
    const dummy = new ListNode(0);
    let p = dummy;

    while (l1 && l2) {
        if (l1.val < l2.val) {
            p.next = l1;
            l1 = l1.next;
        } else {
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
    }

    p.next = l1 || l2; // 将剩余的部分连接到结果链表
    return dummy.next;
}

// 测试代码
const lists = [
    ListNode.create([1,2,3,4,5]),
    ListNode.create([6,7,8,9,10]),
    ListNode.create([11,12,13,14,15])
];

const mergedList = mergeKLists(lists);