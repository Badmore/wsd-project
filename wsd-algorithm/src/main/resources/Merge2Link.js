function merge2Link( l1,  l2) {

        this.result = new ListNode(Number.MIN_VALUE);
        let p = this.result;
        let p1 = l1;
        let p2 = l2;

        while (p1 !== null && p2 !== null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
                p = p.next;
            }

            if (p1 !== null) {
                p.next = p1;
            } else if (p2 !== null) {
                p.next = p2;
            }

}
let head = ListNode.create([1,3,5,7,9])
let head1 = ListNode.create([2,4,6,8,10])
let result = merge2Link(head, head1);


