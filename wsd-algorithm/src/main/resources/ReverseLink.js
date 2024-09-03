var reverseWithRecurse = function(head) {
    if (head === null || head.next === null) {
        return head;
    }
    var last = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return last;
};

let result = reverseWithRecurse(ListNode.create([1,2,3,4,5]));


----


var reverseWithIterator = function(node) {
    let pre = null;
    let cur = node;
    let nxt = node;

    while(cur!== null) {
        nxt = cur.next;
        cur.next = pre;
        pre = cur;
        cur = nxt;
    }

    return pre;
}

let result = reverseWithIterator(ListNode.create([1,2,3,4,5]));