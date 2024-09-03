/**
 * 判断给定的链表是否存在环
 * @param {Object} head - 链表的头节点
 * @returns {boolean} - 如果存在环返回 true，否则返回 false
 */
function isCycle(head) {
    let p1 = head;
    let p2 = head;
    while (p2!= null && p2.next!= null) {
        p1 = p1.next;
        p2 = p2.next.next;
        if(p1 === p2) {
            console.log("有环");
            return true;
        }
    }
    return false;
}

let cycle = ListNode.createCyclic([1,3,5,7,2,4], 2);
isCycle(cycle);




/**
 * 获取链表中环的起始节点
 * @param {Object} head - 链表的头节点
 * @returns {Object} - 环的起始节点，如果不存在环则返回 null
 */
function getCycleStart(head) {
    let p1 = head, p2 = head, p3 = head;
    let isCounter = false;
    while (p2!= null && p2.next!= null) {
        p1 = p1.next;
        p2 = p2.next.next;
        if(isCounter) {
            p3 = p3.next;
        }
        if(p1 === p2){
            if(!isCounter) {
                isCounter = true;
            } else {
                console.log(p3)
                return p3;
            }
        }
    }

    return null;
}

let cycle = ListNode.createCyclic([1,3,5,7,2,4], 2);
getCycleStart(cycle);




/**
 * 计算链表中环的长度
 * @param {Object} head - 链表的头节点
 * @returns {number} - 环的长度，如果不存在环则返回 0
 */
function cycleLength(head) {
    let p1 = head, p2 = head;
    let lengthCounter = 0;
    let isCounter = false;
    while (p2!= null && p2.next!= null) {
        p1 = p1.next;
        p2 = p2.next.next;
        if(isCounter) {
            lengthCounter++;
        }
        if(p1 === p2) {
            if(!isCounter) {
                isCounter = true;
            } else {
                return lengthCounter;
            }
        }
    }
    return lengthCounter;
}

let cycle = ListNode.createCyclic([1,3,5,7,2,4], 2);
cycleLength(cycle);