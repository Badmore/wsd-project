function mergeKLists(lists) {
    if (!lists || lists.length === 0) return null;

    const pq = [];

    // 将所有节点加入优先队列
    for (const node of lists) {
        let currentNode = node;
        while (currentNode) {
            pq.push(currentNode);
            currentNode = currentNode.next;
        }
    }

    // 对节点进行排序
    pq.sort((a, b) => a.val - b.val);

    const dummy = new ListNode(0);
    let p = dummy;

    // 合并节点
    for (const node of pq) {
        p.next = node;
        p = p.next;
        p.next = null; // 断开链表连接
    }

    return dummy.next;
}

// 测试代码
const lists = [
    ListNode.create([1,2,3,4,5]),
    ListNode.create([6,7,8,9,10]),
    ListNode.create([11,12,13,14,15])
];

const mergedList = mergeKLists(lists);