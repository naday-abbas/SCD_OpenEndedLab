package Lab7;

public class Open_Ended {
    private double balance = 1000.0; // Initial balance

    // Synchronized method to ensure thread safety during deposit
    public synchronized void deposit(double amount) {
        double newBalance = balance + amount;
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
        try {
            Thread.sleep(1000); // Simulate delay for deposit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = newBalance;
        System.out.println("New balance after deposit: " + balance);
    }

    // Synchronized method to ensure thread safety during withdrawal
    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            double newBalance = balance - amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            try {
                Thread.sleep(1000); // Simulate delay for withdrawal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
            System.out.println("New balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient funds for " + Thread.currentThread().getName() + " to withdraw: " + amount);
        }
    }

    // Main method to run the multithreaded banking system
    public static void main(String[] args) {
        Open_Ended bank = new Open_Ended();

        // Creating threads for deposit and withdrawal
        Thread thread1 = new Thread(() -> {
            bank.deposit(500.0); // Deposit amount
        }, "Thread");

        Thread thread2 = new Thread(() -> {
            bank.withdraw(200.0); // Withdraw amount
        }, "Thread");

        Thread thread3 = new Thread(() -> {
            bank.withdraw(300.0); // Withdraw amount
        }, "Thread");

        // Starting the threads
        thread1.start();  
        thread2.start();  
        thread3.start();  

        // Join threads to ensure main thread waits for their completion
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + bank.balance);
    }
}
