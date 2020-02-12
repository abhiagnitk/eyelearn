package com.eye.learn.day003;

public class LinkedListADT {
    private Node head;

    public LinkedListADT() {
        this.head = null;
    }

    public void insertHead(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertTail(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(data);
        }
    }

    public void deleteHead() {
        if(head == null) {
            System.out.println("list is empty");
            return;
        } else {
            head = head.next;
        }
    }

    public void deleteTail() {
        if(head == null) {
            System.out.println("list is empty");
            return;
        } else if(head.next == null) {
            head = null;
        } else {
            Node cur = head;
            Node prev = head;
            while(cur.next != null) {
                prev = cur;
                cur = cur.next;
            }

            prev.next = null;
        }
    }

    public void print() {
        Node cur = head;
        while(cur != null) {
            System.out.print(cur.data + "->");
            cur = cur.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public void reverseRec() {
        head = reverseListRec(head);
    }

    public Node reverseListRec(Node head) {
        if(head == null || head.next == null) return head;
        Node restOfList = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return restOfList;
    }

    public void reverseIterative() {
        if(head == null || head.next == null) return;

        Node cur = head;
        Node prev = null;

        while(cur != null) {
            Node nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }

        head = prev;
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
