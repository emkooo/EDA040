 import se.lth.cs.realtime.semaphore.*;
class ThreadTest { public static void main(String[] args) {
Thread t1,t2;
Semaphore s;
s = new CountingSem (1);
t1 = new MyThread("Thread 1",s); t1.start();
t2 = new MyThread("Thread 2",s); t2.start();
} }